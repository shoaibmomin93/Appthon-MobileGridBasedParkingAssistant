package com.example.parklane1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Exit extends Activity {

	Button clear;
	EditText vrn;
	private Socket socket;
	private String serverIpAddress = "192.168.7.101";
	 private static final int REDIRECTED_SERVERPORT = 6000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exit);
		//Connection
		try {
	         InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
	         socket = new Socket(serverAddr, REDIRECTED_SERVERPORT);
	      } catch (UnknownHostException e1) {
	         e1.printStackTrace();
	      } catch (IOException e1) {
	         e1.printStackTrace();
	      }
		//END
		try
		{

		    //abc appState = ((abc)getApplicationContext());
			TextView t1=(TextView) findViewById(R.id.textView21);
		    if(MainActivity.abc==1){
			WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
			String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
			t1.setText(t1.getText()+ "\nYour IP Is : "+ip+"\nPlease Enter this as Manager IP.");
		    } else
		    {
		    	
		    	t1.setText("Exit Operator");
		    	
		    }
		    }
		catch(Exception ex) {
	        ex.printStackTrace();
	    }
		clear =(Button)findViewById(R.id.button21);
		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			vrn=(EditText)findViewById(R.id.editText21);
			String S1=vrn.getText().toString();
			final String str="CLS"+"$"+S1;
			//Sender snd=new Sender(str);
			 try {
	               //EditText et = (EditText) findViewById(R.id.EditText01);
	               //String str = "Hello";//et.getText().toString();
	               PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	               out.println(str);
	               Log.d("Client", "Client sent message");
	            } catch (Exception e) {
	               //tv.setText("Error1");
	               e.printStackTrace();
	            }
	      
			}
		});
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exit, menu);
		return true;
	}

}
