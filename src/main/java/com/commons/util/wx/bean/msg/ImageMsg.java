package com.commons.util.wx.bean.msg;

import com.commons.util.wx.bean.BaseMsg;

public class ImageMsg extends BaseMsg {

	private String picUrl; // 图片链接（由系统生成）
	private String mediaId; // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String msgId;
	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}
	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
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
