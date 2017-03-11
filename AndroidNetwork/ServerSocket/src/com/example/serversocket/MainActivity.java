package com.example.serversocket;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements DataDisplay {
TextView serverMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverMessage=(TextView)findViewById(R.id.textView1);
    }

   public void connect(View view)
   {
	    MyServer server= new MyServer();
	   	 server.setEventListener(this);
	   	 server.startListening();

   }
   public void Display(String message)
   {
	   serverMessage.setText(""+message);
   }
}
