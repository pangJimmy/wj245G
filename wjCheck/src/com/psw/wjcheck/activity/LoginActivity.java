package com.psw.wjcheck.activity;

import com.psw.wjcheck.R;
import com.psw.wjcheck.rfid245G.RfidManager;

import android.app.Activity;
import android.os.Bundle;
/**
 * 登录界面
 * @author mac
 *
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login) ;
		RfidManager rfid = new RfidManager() ;
		try {
			Thread.sleep(2000) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rfid.start() ;
	}
}
