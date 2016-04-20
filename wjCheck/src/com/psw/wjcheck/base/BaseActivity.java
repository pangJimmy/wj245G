package com.psw.wjcheck.base;

import android.app.Activity;
import android.widget.Toast;
/**
 * 公用Activity父类,
 * @author mac
 *
 */
public class BaseActivity extends Activity {

	
	
	/**
	 * 显示提示信息
	 * @param msg
	 */
	public void ToastInfo(String msg){
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT) ;
	}
}
