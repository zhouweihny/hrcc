package com.commons.util.wx.bean.res;

public class UserInfoRequest {

	public String access_token;// 调用接口凭证
	public String openid;// 普通用户的标识，对当前公众号唯一
	public String lang="zh_CN";// 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
