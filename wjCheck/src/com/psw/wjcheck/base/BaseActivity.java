package com.psw.wjcheck.base;

import com.psw.wjcheck.R;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 公用Activity父类,
 * @author mac
 *
 */
public class BaseActivity extends Activity implements OnClickListener{
	public TextView tvTitle ; //标题
	public ImageButton btnBack ;//返回
	public ProgressBar progress ;//转动圈
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
		tvTitle = (TextView) findViewById(R.id.textView_title) ;
		btnBack = (ImageButton) findViewById(R.id.imageView_back) ;
		progress = (ProgressBar) findViewById(R.id.progressBar_find_tag) ;
		if(btnBack != null){
			btnBack.setOnClickListener(this) ;
		}
	}
	
	//设置标题
	public void setCustomTitle(String title) {
		if(tvTitle != null){
			tvTitle.setText(title) ;
			//tvTitle.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
		}

	}
	
	//设置转圈是否在转动
	public void setProgressBarVisible(int value){
		progress.setVisibility(value) ;
	}
	
	
	/**
	 * 显示提示信息
	 * @param msg
	 */
	public void ToastInfo(String msg){
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT) ;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView_back:
			finish() ;
			break;

		default:
			break;
		}
		
	}
}
