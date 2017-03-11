package com.example.serversocket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Config extends Activity {
	static int flrno, limit;
	EditText flr, slot;
	Button save;
	EntryTable et = new EntryTable(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);
		flr = (EditText) findViewById(R.id.configeditText2);
		slot = (EditText) findViewById(R.id.configeditText1);
		save = (Button) findViewById(R.id.saveBtn);

	}

	public void Save(View view) {
		String f = flr.getText().toString();
		String s = slot.getText().toString();
		flrno = Integer.parseInt(f);
		limit = Integer.parseInt(s);
		for (int i = 1; i <= flrno; i++) {
			String str = "F" + i;
			long id = et.insertData(str, "0");
			if (id < 0)
				Debugg.msg(Config.this, "Insertion Failed");
			else
				Debugg.msg(Config.this, "Insertion Successful");
		}

	}

	public void view(View v) {
		
		  String x=et.getAllData(); Debugg.msg(Config.this, x);
		 
		/*String count = et.getSlotData("F1");

		Debugg.msg(this, count);*/

	}

}
