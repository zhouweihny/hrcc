package com.commons.util.wx.bean.res;

public class OpenIdRequest {

	public String appid; // 公众号的唯一标识

	public String appsecret;// 公众号的appsecret

	public String code; // 填写第一步获取的code参数

	public String grant_type = "authorization_code"; // 填写为authorization_code

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

}
