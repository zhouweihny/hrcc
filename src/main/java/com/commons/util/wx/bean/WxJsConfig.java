/**
 * @Title: WxJsConfig.java
 * @Package com.util.weixin.bean
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年11月8日 下午4:15:13
 * @version V1.0
 */

package com.commons.util.wx.bean;

import java.util.List;

/**
 * @author Du.Jun
 *
 */
public class WxJsConfig {

	public Boolean debug;

	public String appId;

	public Long timestamp;

	public String nonceStr;

	public String signature;

	public List<String> jsApiList;

	/**
	 * @return the debug
	 */
	public Boolean getDebug() {
		return debug;
	}

	/**
	 * @param debug
	 *            the debug to set
	 */
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the nonceStr
	 */
	public String getNonceStr() {
		return nonceStr;
	}

	/**
	 * @param nonceStr
	 *            the nonceStr to set
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the jsApiList
	 */
	public List<String> getJsApiList() {
		return jsApiList;
	}

	/**
	 * @param jsApiList
	 *            the jsApiList to set
	 */
	public void setJsApiList(List<String> jsApiList) {
		this.jsApiList = jsApiList;
	}

}
