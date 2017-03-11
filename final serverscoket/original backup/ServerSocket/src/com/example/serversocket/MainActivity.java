package com.example.serversocket;

import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements DataDisplay,
		SensorEventListener {
	EntryTable et = new EntryTable(this);
	static String msg = "";
	TextView serverMessage;
	Thread m_objThreadClient;
	Socket clientSocket;
	private SensorManager mSensorManager;
	private Sensor mSensor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		serverMessage = (TextView) findViewById(R.id.textView1);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(MainActivity.this, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.values[0] == 1) {

		} else {

			Debugg.msg(this, "Inserted");
			// Listener

			String count = et.getSlotData("F1");
			int cnt = Integer.parseInt(count);
			// Debugg.msg(this, count);
			if (cnt >= 5) {
			
				callF2();
			} 
			else {

				cnt++;
				Integer cti = (Integer) cnt;
				String ct = cti.toString();
				int c = et.update(ct, "F1");
			}
		}

	}

	

	public void Start(View view) {
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			messageDisplay(msg.obj.toString());
		}
	};

	public void messageDisplay(String servermessage) {
		serverMessage.setText("" + servermessage);
	}

	public void connect(View view) {
		// ShowParking();
		new Thread(new Runnable() {
		      public void run() {
		           
		        	getSlotF1();
		    		getSlotF2();
		    		getSlotF3();}
		        
		    }).start();
		
		MyServer server = new MyServer();
		server.setEventListener(this);
		server.startListening();
		

	}

	public void Display(String message) {
		msg = message;
		Decrement();
		Increment();
		/*
		 * getSlotF1(); getSlotF2(); getSlotF3();
		 */
		/*
		 * new Thread(new Runnable() { public void run() {
		 * 
		 * } }).start();
		 */
		// ShowParking();

		serverMessage.setText("" + message);
		/*
		 * msg=message; Decrement();
		 */

	}

	public void ConfigOpen(View view) {
		Intent myIntent = new Intent(MainActivity.this, Config.class);
		// myIntent.putExtra("key", value); //Optional parameters
		MainActivity.this.startActivity(myIntent);

	}

	public void Decrement() {
		getSlotF1();
		getSlotF2();
		getSlotF3();
		if (msg.equals("1:DELETE")) {

			String str = msg.substring(0, 1);
			String Flr = "F" + str;

			String count = et.getSlotData(Flr);

			Debugg.msg(this, count);
			if (count.equals("0")) {
				Debugg.msg(this, "Floor is Empty");
			} else {
				int cnt = Integer.parseInt(count);
				cnt--;
				Integer cti = (Integer) cnt;
				String ct = cti.toString();

				int c = et.update(ct, Flr);

				Debugg.msg(this, "Slot Cleared" + c);
			}
		} else if (msg.equals("2:DELETE")) {

			String str = msg.substring(0, 1);
			String Flr = "F" + str;

			String count = et.getSlotData(Flr);

			Debugg.msg(this, count);
			if (count.equals("0")) {
				Debugg.msg(this, "Floor is Empty");
			} else {
				int cnt = Integer.parseInt(count);
				cnt--;
				Integer cti = (Integer) cnt;
				String ct = cti.toString();

				int c = et.update(ct, Flr);

				Debugg.msg(this, "Slot Cleared" + c);
			}
		} else if (msg.equals("3:DELETE")) {

			String str = msg.substring(0, 1);
			String Flr = "F" + str;

			String count = et.getSlotData(Flr);

			Debugg.msg(this, count);
			if (count.equals("0")) {
				Debugg.msg(this, "Floor is Empty");
			} else {
				int cnt = Integer.parseInt(count);
				cnt--;
				Integer cti = (Integer) cnt;
				String ct = cti.toString();

				int c = et.update(ct, Flr);

				Debugg.msg(this, "Slot Cleared" + c);
			}
		}

	}

	public void getSlotF1() {
		// Debugg.msg(this ,"odiudhvdu");
		String count = et.getSlotData("F1");
		int cnt = Integer.parseInt(count);
		if (cnt < 5) {
			MyServer.a = true;
			// Debugg.msg(this , msg);
		}
		else {MyServer.a = false;}
	}

	public void getSlotF2() {
		// Debugg.msg(this ,"odiudhvdu");
		String count = et.getSlotData("F2");
		int cnt = Integer.parseInt(count);
		if (cnt < 5) {
			MyServer.b = true;
			// Debugg.msg(this , msg);
		}
		else {MyServer.b = false;}
	}

	public void getSlotF3() {
		// Debugg.msg(this ,"odiudhvdu");
		String count = et.getSlotData("F3");
		int cnt = Integer.parseInt(count);
		if (cnt < 5) {
			MyServer.c = true;
			// Debugg.msg(this , msg);
		}
		else {MyServer.c = false;}
	}
	
	public void Increment() {
		getSlotF1();
		getSlotF2();
		getSlotF3();
		if (msg.equals("INSERT")) {
			String count = et.getSlotData("F1");
			int cnt = Integer.parseInt(count);
			// Debugg.msg(this, count);
			if (cnt >= 5) {
			
				callF2();
			} 
			else {

				cnt++;
				Integer cti = (Integer) cnt;
				String ct = cti.toString();
				int c = et.update(ct, "F1");
			}
		}
		}
	public void callF2() {
		// TODO Auto-generated method stub
		String count = et.getSlotData("F2");
		int cnt = Integer.parseInt(count);
		// Debugg.msg(this, count);
		if (cnt >= 5) {
		
			callF3();
		} 
		else {

			cnt++;
			Integer cti = (Integer) cnt;
			String ct = cti.toString();
			int c = et.update(ct, "F2");
		}
	}

	private void callF3() {
		// TODO Auto-generated method stub
		String count = et.getSlotData("F3");
		int cnt = Integer.parseInt(count);
		// Debugg.msg(this, count);
		if (cnt >= 5) {
		
			Debugg.msg(this, "parking full");
		} 
		else {

			cnt++;
			Integer cti = (Integer) cnt;
			String ct = cti.toString();
			int c = et.update(ct, "F3");
		}
		
	}

}