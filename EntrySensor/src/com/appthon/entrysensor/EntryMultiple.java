package com.appthon.entrysensor;

import android.os.Bundle; 
import android.app.Activity;
import android.view.Menu;




import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//import com.example.park_o_matic.Client_Screen;

import android.net.ConnectivityManager;
import android.net.sip.SipSession.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EntryMultiple extends Activity implements SensorEventListener {
TextView serverMessage;
Thread m_objThreadClient;
Socket clientSocket;
private SensorManager mSensorManager;
private Sensor mSensor;
EditText et;
int c=0;
    
/*public void testdialog(View v)
{
	Builder builder=new AlertDialog.Builder(getApplicationContext());
	builder.setMessage("YOU MORON");
	builder.setCancelable(true);
	builder.setPositiveButton("i agree", new okonClickListener());
	
	
	dialog.show();
}*/
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_multiple);
       // serverMessage=(TextView)findViewById(R.id.textView1);
       // et=(EditText) findViewById(R.id.editText1);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }
    protected void onResume() {
  	  super.onResume();
  	  mSensorManager.registerListener(EntryMultiple.this, mSensor,
  	    SensorManager.SENSOR_DELAY_NORMAL);
  	 }

  	 protected void onPause() {
  	  super.onPause();
  	  mSensorManager.unregisterListener(this);
  	 }

  	 public void onAccuracyChanged(Sensor sensor, int accuracy) {
  	 }

  	 public void onSensorChanged(SensorEvent event) {
  		 
  		 //if(c!=0){
  	  if (event.values[0] == 1) {
  	   //iv.setImageResource(R.drawable.near);
  		//c++;
  	  } else {

  		m_objThreadClient=new Thread(new Runnable() {
  			  private Handler mHandler;

  			public void run()
  		       {
  		          try 
  		           {//EditText et1 =(EditText)findViewById(R.id.editText1);
  		        	  ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

  		        	//wifi
  		        	android.net.NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
  		        	if(!(wifi==android.net.NetworkInfo.State.CONNECTED))
  		        		Toast.makeText(EntryMultiple.this, "Please connect WiFi", Toast.LENGTH_LONG).show();
  		        	else
  		        	{
  		           WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
  		           int a =wifiMgr.getWifiState();
  		           WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
  		           int ip = wifiInfo.getIpAddress();
  		           String ipAddress = Formatter.formatIpAddress(ip);
  		           String[] ip1=ipAddress.split("\\.");
  		           String ip4=ip1[0]+"."+ip1[1]+"."+ip1[2]+".1";
  					clientSocket= new Socket(ip4,2001);
  					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
  				    oos.writeObject("INSERT");
  				    Message serverMessage= Message.obtain();
  				    ObjectInputStream ois =new ObjectInputStream(clientSocket.getInputStream());
  				    String strMessage = (String)ois.readObject();
  				    serverMessage.obj=strMessage;
  	                mHandler.sendMessage(serverMessage); 
  				    oos.close();
  				    ois.close();
  				   } 
  		           }
  		           catch (Exception e) 
  		           {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}
  					 
  		         }
  				});
  		
  		 m_objThreadClient.start();
  		
  	   	  
  	  }
  	 }
  		 
public void Start(View view)
{
}

Handler mHandler = new Handler() {
	@Override
	public void handleMessage(Message msg) {
		messageDisplay(msg.obj.toString());
	}
};
public void messageDisplay(String servermessage)
{
	serverMessage.setText(""+servermessage);
}
   
}




