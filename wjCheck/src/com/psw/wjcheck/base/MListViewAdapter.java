package com.psw.wjcheck.base;

import java.util.List;

import com.psw.wjcheck.R;
import com.psw.wjcheck.entity.CardInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 标签信息显示适配器
 * @author mac
 *
 */
public class MListViewAdapter extends BaseAdapter {
	
	private Context context  ;
	private List<CardInfo> listInfo ;
	private LayoutInflater inflater ;  //布局注入器
	
	public MListViewAdapter(Context context ,List<CardInfo> listInfo){
		this.context = context ;
		this.listInfo = listInfo ;
		inflater = LayoutInflater.from(context) ;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	//textView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null; 
		if(convertView == null){
			holder = new ViewHolder() ;
			View view = inflater.inflate(R.layout.listview_item, null) ;
			holder.tvTagID = (TextView) view.findViewById(R.id.item_tagID) ;
			holder.tvCarID = (TextView) view.findViewById(R.id.item_carID) ;
			holder.tvDengour = (TextView) view.findViewById(R.id.item_dengour) ;
			convertView = view ;
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag() ;
		}
		CardInfo info = listInfo.get(position) ;
		//添加数据
		holder.tvTagID.setText(info.getTagId()) ;
		holder.tvCarID.setText(info.getCarID()) ;
		holder.tvDengour.setText(info.getDengouresName()) ;
		return convertView;
	}
	
	//listView item
	private class ViewHolder{
		TextView tvTagID ;
		TextView tvCarID ;
		TextView tvDengour ;
	}

}
