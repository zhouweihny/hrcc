package com.commons.util.wx.bean.event;

import com.commons.util.wx.bean.BaseMsg;

public class ViewEvent extends BaseMsg {

	private String eventKey;// 事件KEY值，设置的跳转URL

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

}
