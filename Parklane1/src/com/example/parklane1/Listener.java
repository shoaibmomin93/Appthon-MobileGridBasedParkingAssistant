package com.example.parklane1;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Listener extends Service /*implements DataDisplay*/ {
	
	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		//startService(new Intent(this, Listener.class));
		
		 /*
			 MyServer server= new MyServer();
			 server.setEventListener( this);
			 server.startListening();
	   	     Debugg.msg(this, "Server is Listening");*/
	
		return START_STICKY;
		 
	}

	 @Override
	 public void onCreate() {
	 	// TODO Auto-generated method stub
	 	  
	 	
	 	  
	 	super.onCreate();
	 }

	 @SuppressLint("ShowToast")
	 @Override
	   public void onDestroy() {
	          // TODO Auto-generated method stub
	         
	          super.onDestroy();
	   }

	/*@Override
	public void Display(String message) {
		// TODO Auto-generated method stub
		 Debugg.msg(this, ""+message);
	}*/

	
}
