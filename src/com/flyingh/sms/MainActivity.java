package com.flyingh.sms;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText phone;
	private EditText msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		phone = (EditText) findViewById(R.id.phone);
		phone.setText(R.string.defaultPhone);
		msg = (EditText) findViewById(R.id.msg);
		msg.setText(R.string.defaultMsg);
	}

	public void sendSms(View view) {
		SmsManager.getDefault().sendTextMessage(phone.getText().toString(),
				null, msg.getText().toString(), null, null);
		Toast.makeText(this, R.string.successInfo, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
