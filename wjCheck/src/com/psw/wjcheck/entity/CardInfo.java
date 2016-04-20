package com.psw.wjcheck.entity;
/**
 * 卡片信息
 * @author mac
 *
 */
public class CardInfo {
	private String tagId ;  //标签ID
	private String carID ;	//车牌
	private String dengouresName ; //危险品名称
	
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getCarID() {
		return carID;
	}
	public void setCarID(String carID) {
		this.carID = carID;
	}
	public String getDengouresName() {
		return dengouresName;
	}
	public void setDengouresName(String dengouresName) {
		this.dengouresName = dengouresName;
	}
	
	
}
