package com.flyingh.sms;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
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
		SmsManager smsManager = SmsManager.getDefault();
		String phoneNumber = phone.getText().toString();
		if (isNotPhone(phoneNumber)) {
			phone.setTextColor(ColorStateList.valueOf(Color.RED));
			showMsg(R.string.wrongNumber);
			return;
		}
		String msgStr = msg.getText().toString();
		if (isEmpty(msgStr)) {
			showMsg(R.string.msgEmpty);
			return;
		}
		for (String str : smsManager.divideMessage(msgStr)) {
			smsManager.sendTextMessage(phoneNumber, null, str, null, null);
		}
		showMsg(R.string.successInfo);
	}

	private boolean isEmpty(String msgStr) {
		return msgStr == null || msgStr.trim().length() == 0;
	}

	private void showMsg(int stringId) {
		Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
	}

	private boolean isNotPhone(String phoneNumber) {
		return !PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
