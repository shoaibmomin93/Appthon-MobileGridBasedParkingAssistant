package com.example.parklane1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerSetup extends Activity {

	Button enter,floor,exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managersetup);
		
	}
	public void A(View view)
	{
		Intent myIntent = new Intent(ManagerSetup.this, A.class);
		// myIntent.putExtra("key", value); //Optional parameters
		ManagerSetup.this.startActivity(myIntent);
	}
	public void B(View view)
	{
		Intent myIntent = new Intent(ManagerSetup.this, B.class);
		// myIntent.putExtra("key", value); //Optional parameters
		ManagerSetup.this.startActivity(myIntent);
	}
	public void C(View view)
	{
		Intent myIntent = new Intent(ManagerSetup.this, C.class);
		// myIntent.putExtra("key", value); //Optional parameters
		ManagerSetup.this.startActivity(myIntent);
	}
	public void D(View view)
	{
		Intent myIntent = new Intent(ManagerSetup.this, MyServer.class);
		// myIntent.putExtra("key", value); //Optional parameters
		ManagerSetup.this.startActivity(myIntent);
	}
	
}
