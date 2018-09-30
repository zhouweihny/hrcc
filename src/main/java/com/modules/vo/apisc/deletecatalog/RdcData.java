package com.modules.vo.apisc.deletecatalog;


public class RdcData {

	private Boolean result = true;

	private String message;

	private String accesstoken;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the accesstoken
	 */
	public String getAccesstoken() {
		return accesstoken;
	}

	/**
	 * @param accesstoken
	 *            the accesstoken to set
	 */
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

}
