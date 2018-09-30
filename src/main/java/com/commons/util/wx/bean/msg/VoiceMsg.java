package com.commons.util.wx.bean.msg;

import com.commons.util.wx.bean.BaseMsg;

public class VoiceMsg extends BaseMsg {

	public String mediaId;// 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	public String format;// 语音格式，如amr，speex等
	public String msgID;// 消息id，64位整型

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId
	 *            the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the msgID
	 */
	public String getMsgID() {
		return msgID;
	}

	/**
	 * @param msgID
	 *            the msgID to set
	 */
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

}
