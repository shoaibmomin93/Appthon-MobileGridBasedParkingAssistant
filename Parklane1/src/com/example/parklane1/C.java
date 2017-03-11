package com.example.parklane1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class C extends Activity{
	Button save,del;
	EditText ipAdd,no;
	ManagerTable table=new ManagerTable(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c);
		ipAdd =(EditText)findViewById(R.id.xeditText2);
		no =(EditText)findViewById(R.id.xeditText1);
		save =(Button)findViewById(R.id.xbutton1);
		del =(Button)findViewById(R.id.xbutton2);
		
		
		
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String ip=ipAdd.getText().toString();
				String temp=no.getText().toString();
				int num=Integer.parseInt(temp);
				
				long id=table.insertData(MainActivity.loc, ip,0,0, num);
				
				if (id < 0)
					Debugg.msg(C.this, "Insertion Failed");
				else
					Debugg.msg(C.this, "Insertion Successful");
				
			}
		});
		
		
		
	}

}
