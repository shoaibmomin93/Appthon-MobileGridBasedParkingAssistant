package com.example.parklane1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
static int abc=0;
static String loc="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//starting service
		Intent serviceIntent = new Intent();
        serviceIntent.setAction("com.example.parklane1.Listener");
        startService(serviceIntent);
        //Also calling server from here 
        
        
		
		Button bt1=(Button)findViewById(R.id.button1);
		Button bt2=(Button)findViewById(R.id.button2);
		Button bt3=(Button)findViewById(R.id.button3);
		Button bt4=(Button)findViewById(R.id.button4);
		CheckBox CB4=(CheckBox)findViewById(R.id.checkBox1);
		Boolean B1;
        bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(com.example.parklane1.MainActivity.this,com.example.parklane1.Entry.class );
            	com.example.parklane1.MainActivity.this.startActivity(intent);
            		
			}
		});


bt2.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(com.example.parklane1.MainActivity.this,com.example.parklane1.Exit.class );
    	com.example.parklane1.MainActivity.this.startActivity(intent);
    		
	}
});


bt3.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(com.example.parklane1.MainActivity.this,com.example.parklane1.Floor.class );
    	com.example.parklane1.MainActivity.this.startActivity(intent);
    		
	}
});



CB4.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(((CheckBox)findViewById(R.id.checkBox1)).isChecked())
		{
			abc=1;
			EditText et2=(EditText)findViewById(R.id.editText2);
			et2.setVisibility(View.GONE);
		
		}
		else
		{
			abc=0;
			EditText et2=(EditText)findViewById(R.id.editText2);
			et2.setVisibility(View.VISIBLE);
		}
	}
});
bt4.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		EditText lc=(EditText)findViewById(R.id.MainEditText1);
		loc =lc.getText().toString();
		Intent myIntent = new Intent(MainActivity.this, ManagerSetup.class);
		// myIntent.putExtra("key", value); //Optional parameters
		MainActivity.this.startActivity(myIntent);
	}
});

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	
	

}
