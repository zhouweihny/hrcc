package com.commons.util.wx.bean.res;


public class AccessTokenResponse extends BaseResponse {

	public String access_token;// 获取到的凭证
	
	public Integer expires_in;// 凭证有效时间，单位：秒

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
	
	
}
