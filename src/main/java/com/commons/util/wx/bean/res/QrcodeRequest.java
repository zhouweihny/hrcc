package com.commons.util.wx.bean.res;

public class QrcodeRequest {

	public String access_token;// 调用接口凭证
	public Integer sceneid; // sceneid 
	public Boolean type; // type  true  永久 false 临时
	public String scenestr; // scenestr
	public Integer expireseconds; // expireSeconds

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Integer getSceneid() {
		return sceneid;
	}

	public void setSceneid(Integer sceneid) {
		this.sceneid = sceneid;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public String getScenestr() {
		return scenestr;
	}

	public void setScenestr(String scenestr) {
		this.scenestr = scenestr;
	}

	public Integer getExpireseconds() {
		return expireseconds;
	}

	public void setExpireseconds(Integer expireseconds) {
		this.expireseconds = expireseconds;
	}

}
