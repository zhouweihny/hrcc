package com.commons.util.wx.bean.msg;

import com.commons.util.wx.bean.BaseMsg;

public class LocationMsg extends BaseMsg {

	private String location_X;// 地理位置维度
	private String location_Y;// 地理位置经度
	private String scale;// 地图缩放大小
	private String label;// 地理位置信息
	private String msgId;// 消息id，64位整型
	/**
	 * @return the location_X
	 */
	public String getLocation_X() {
		return location_X;
	}
	/**
	 * @param location_X the location_X to set
	 */
	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}
	/**
	 * @return the location_Y
	 */
	public String getLocation_Y() {
		return location_Y;
	}
	/**
	 * @param location_Y the location_Y to set
	 */
	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}
	/**
	 * @return the scale
	 */
	public String getScale() {
		return scale;
	}
	/**
	 * @param scale the scale to set
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
	
}
