package com.commons.util.wx.bean.event;

import com.commons.util.wx.bean.BaseMsg;

public class SubscribeEvent extends BaseMsg {
	public String eventKey;// 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	public String ticket;// 二维码的ticket，可用来换取二维码图片

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey
	 *            the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
