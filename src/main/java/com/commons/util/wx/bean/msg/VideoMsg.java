package com.commons.util.wx.bean.msg;

import com.commons.util.wx.bean.BaseMsg;

public class VideoMsg extends BaseMsg {

	private String mediaID; // 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
	private String format; // 语音格式：amr
	private String recognition; // 语音识别结果，UTF8编码
	private String msgID; // 消息id，64位整型

	/**
	 * @return the mediaID
	 */
	public String getMediaID() {
		return mediaID;
	}

	/**
	 * @param mediaID
	 *            the mediaID to set
	 */
	public void setMediaID(String mediaID) {
		this.mediaID = mediaID;
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
	 * @return the recognition
	 */
	public String getRecognition() {
		return recognition;
	}

	/**
	 * @param recognition
	 *            the recognition to set
	 */
	public void setRecognition(String recognition) {
		this.recognition = recognition;
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
