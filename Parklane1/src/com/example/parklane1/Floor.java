package com.example.parklane1;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.Formatter;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Floor extends Activity {
	FloorTable table = new FloorTable(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floor);

		try {

			TextView t1 = (TextView) findViewById(R.id.textView41);
			// abc appState = ((abc)getApplicationContext());
			if (MainActivity.abc == 1) {
				WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
				String ip = Formatter.formatIpAddress(wm.getConnectionInfo()
						.getIpAddress());
				t1.setText(t1.getText() + "\nYour IP Is : " + ip
						+ "\nPlease Enter this as Manager IP.");
			}

			else {

				t1.setText("Floor Operator");

			}
			Button bt1 = (Button) findViewById(R.id.button41);

			bt1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					SharedPreferences sharedPref = com.example.parklane1.Floor.this
							.getPreferences(Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPref.edit();
					EditText et1 = (EditText) findViewById(R.id.editText1);
					EditText et2 = (EditText) findViewById(R.id.editText41);
					String S1 = et1.getText().toString();
					String S2 = et2.getText().toString();
					int pkslot = Integer.parseInt(S2);
					editor.putString(S2, S1);
					editor.commit();
					// EditText et2=(EditText)findViewById(R.id.editText14);
					// S2=et2.getText().toString();
					// S1=sharedPref.getString(S2, "None");
					TextView t1 = (TextView) findViewById(R.id.textView41);
					t1.setText("Floor Operator\n" + "Done, Enter Next");

					// Mohammed Method
					WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
					String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
					int MockFloorNo = 1;

					long id = table.insertData(S1, ip, pkslot, MockFloorNo);

					if (id < 0)
						Debugg.msg(Floor.this, "Insertion Failed");
					else
						Debugg.msg(Floor.this, "Insertion Successful");

				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.floor, menu);
		return true;
	}

}
