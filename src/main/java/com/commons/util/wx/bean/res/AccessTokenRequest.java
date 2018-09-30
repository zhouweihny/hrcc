package com.commons.util.wx.bean.res;

public class AccessTokenRequest {
	public String grant_type = "client_credential"; // 获取access_token填写client_credential
	public String appid;// 是 第三方用户唯一凭证
	public String secret;// 第三方用户唯一凭证密钥，即appsecret

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
