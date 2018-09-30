/**
 * @Title: Result.java
 * @Package com.base
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年7月31日 下午1:05:11
 * @version V1.0
 */

package com.commons.base;

/**
 * @author Du.Jun
 *
 */
public class JsonResult {

	private String code = "0000";

	private String message = "";

	private Boolean result = true;

	private Object data;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the result
	 */
	public Boolean getResult() {
		return code == "0000" ? this.result : false;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}

}
