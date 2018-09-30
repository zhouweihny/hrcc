package com.modules.vo;

public class IerData {

	@com.commons.annotation.Column(value = "code")
	private String code;
	@com.commons.annotation.Column(value = "scode")
	private String scode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

}
