package com.commons.util.wx.bean.event;

public class ScanEvent extends BaseEvent {
	public String eventKey;// 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	public String ticket;// 二维码的ticket，可用来换取二维码图片

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

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}
}
