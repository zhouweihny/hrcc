package com.commons.util.wx.bean.event;

import com.commons.util.wx.bean.BaseMsg;

public class BaseEvent extends BaseMsg {

	public String event;

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

}
