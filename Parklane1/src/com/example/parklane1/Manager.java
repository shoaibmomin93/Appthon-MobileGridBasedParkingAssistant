package com.example.parklane1;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.text.format.Formatter;
import android.view.Menu;
import android.widget.TextView;

public class Manager extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_manager);
		
		TextView t1 =(TextView)findViewById(R.id.textView1);
		try
		{

		    //abc appState = ((abc)getApplicationContext());
		    
			WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
			String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
			t1.setText("Your IP Is:\n"+ip+"\nPlease Enter this as Manager\nIP in other systems.");
		}
		catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manager, menu);
		return true;
	}

}
