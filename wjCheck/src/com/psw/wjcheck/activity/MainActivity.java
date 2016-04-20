package com.psw.wjcheck.activity;

import java.util.ArrayList;
import java.util.List;

import com.psw.wjcheck.R;
import com.psw.wjcheck.base.BaseActivity;
import com.psw.wjcheck.base.MListViewAdapter;
import com.psw.wjcheck.entity.CardInfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
/**
 * 主界面显示标签信息
 * @author mac
 *
 */
public class MainActivity extends BaseActivity {
	
	private ListView listViewCardInfo ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main) ;
		//设置标题
		super.setCustomTitle(getString(R.string.card_info)) ;
		//
		initView() ;
	}
	
	private void initView(){
		listViewCardInfo = (ListView) findViewById(R.id.listView_tag_info) ;
		CardInfo cardInfo = new CardInfo() ;
		cardInfo.setCarID("京A12345") ;
		cardInfo.setDengouresName("甲醇") ;
		cardInfo.setTagId("814403") ;
		
		
		CardInfo cardInfo1 = new CardInfo() ;
		cardInfo1.setCarID("京A12345") ;
		cardInfo1.setDengouresName("甲醇") ;
		cardInfo1.setTagId("814403") ;
		
		
		CardInfo cardInfo2 = new CardInfo() ;
		cardInfo2.setCarID("京A12345") ;
		cardInfo2.setDengouresName("甲醇") ;
		cardInfo2.setTagId("814403") ;
		//测试数据
		List<CardInfo> list = new ArrayList<CardInfo>() ;
		list.add(cardInfo) ;
		list.add(cardInfo1) ;
		list.add(cardInfo2) ;
		listViewCardInfo.setAdapter(new MListViewAdapter(this, list)) ;
	}
}
