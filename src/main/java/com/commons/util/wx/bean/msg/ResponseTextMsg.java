package com.commons.util.wx.bean.msg;

public class ResponseTextMsg {

	public String toUserName;// 是 接收方帐号（收到的OpenID）
	public String fromUserName;// 是 开发者微信号
	public Long createTime;// 是 消息创建时间 （整型）
	public String msgType = "text";// 是 text
	public String content;// 是 图文消息个数，限制为8条以内

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
