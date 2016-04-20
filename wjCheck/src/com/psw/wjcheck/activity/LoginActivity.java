package com.psw.wjcheck.activity;

import com.psw.wjcheck.R;
import com.psw.wjcheck.rfid245G.RfidManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 登录界面
 * @author mac
 *
 */
public class LoginActivity extends Activity implements OnClickListener{
	
	private Button btnStartRead ;
	private Button btnSettings;
	private Button btnExit ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login) ;
		initView() ;
		
	}

	private void initView(){
		btnStartRead = (Button) findViewById(R.id.button_start_rfid) ;
		btnSettings = (Button) findViewById(R.id.button_sys_settings) ;
		btnExit = (Button) findViewById(R.id.button_exit) ;
		
		btnStartRead.setOnClickListener(this) ;
		btnSettings.setOnClickListener(this) ;
		btnExit.setOnClickListener(this) ;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_start_rfid:
			Intent toStart = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(toStart) ;
			break;
		case R.id.button_sys_settings:
			
			break;
		case R.id.button_exit:
			
			break;
			

		default:
			break;
		}
		
	}
}
