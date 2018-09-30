package com.modules.vo;

import java.io.Serializable;

public class Wx implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "code")
	private java.lang.String code; // code

	@com.commons.annotation.Column(value = "devname")
	private java.lang.String devname; // devname

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "appid")
	private java.lang.String appid; // appid

	@com.commons.annotation.Column(value = "appsecret")
	private java.lang.String appsecret; // appsecret

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "authenticationtime")
	private java.util.Date authenticationtime; // authenticationtime

	@com.commons.annotation.Column(value = "url")
	private java.lang.String url; // url

	@com.commons.annotation.Column(value = "token")
	private java.lang.String token; // token

	@com.commons.annotation.Column(value = "encodingaesKey")
	private java.lang.String encodingaeskey; // encodingaesKey

	@com.commons.annotation.Column(value = "encrypttype")
	private java.lang.String encrypttype; // encrypttype

	@com.commons.annotation.Column(value = "jsconfigdebug")
	private java.lang.Boolean jsconfigdebug; // jsconfigdebug

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

	@com.commons.annotation.Column(value = "wxmsgid")
	private java.lang.String wxmsgid; // wxmsgid

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	@com.commons.annotation.Column(value = "operatorid")
	private java.lang.String operatorid; // operatorid

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "createtime")
	private java.util.Date createtime; // createtime

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "updatetime")
	private java.util.Date updatetime; // updatetime

	@com.commons.annotation.Column(value = "selected")
	private Integer selected;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getDevname() {
		return devname;
	}

	public void setDevname(java.lang.String devname) {
		this.devname = devname;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getAppid() {
		return appid;
	}

	public void setAppid(java.lang.String appid) {
		this.appid = appid;
	}

	public java.lang.String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(java.lang.String appsecret) {
		this.appsecret = appsecret;
	}

	public java.util.Date getAuthenticationtime() {
		return authenticationtime;
	}

	public void setAuthenticationtime(java.util.Date authenticationtime) {
		this.authenticationtime = authenticationtime;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.String getToken() {
		return token;
	}

	public void setToken(java.lang.String token) {
		this.token = token;
	}

	public java.lang.String getEncodingaeskey() {
		return encodingaeskey;
	}

	public void setEncodingaeskey(java.lang.String encodingaeskey) {
		this.encodingaeskey = encodingaeskey;
	}

	public java.lang.String getEncrypttype() {
		return encrypttype;
	}

	public void setEncrypttype(java.lang.String encrypttype) {
		this.encrypttype = encrypttype;
	}

	public java.lang.Boolean getJsconfigdebug() {
		return jsconfigdebug;
	}

	public void setJsconfigdebug(java.lang.Boolean jsconfigdebug) {
		this.jsconfigdebug = jsconfigdebug;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public java.lang.String getWxmsgid() {
		return wxmsgid;
	}

	public void setWxmsgid(java.lang.String wxmsgid) {
		this.wxmsgid = wxmsgid;
	}

	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	public java.lang.String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(java.lang.String operatorid) {
		this.operatorid = operatorid;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

}
