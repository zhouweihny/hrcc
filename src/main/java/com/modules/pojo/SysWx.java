package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>SysWx<br>
 */
@com.commons.annotation.Relation("sys_wx")
public class SysWx implements Serializable {

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

	@com.commons.annotation.Column(value = "domain")
	private java.lang.String domain; // domain

	@com.commons.annotation.Column(value = "ispromotion")
	private java.lang.Boolean ispromotion; // ispromotion

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

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * @return the devname
	 */
	public java.lang.String getDevname() {
		return devname;
	}

	/**
	 * @param devname
	 *            the devname to set
	 */
	public void setDevname(java.lang.String devname) {
		this.devname = devname;
	}

	/**
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * @return the appid
	 */
	public java.lang.String getAppid() {
		return appid;
	}

	/**
	 * @param appid
	 *            the appid to set
	 */
	public void setAppid(java.lang.String appid) {
		this.appid = appid;
	}

	/**
	 * @return the appsecret
	 */
	public java.lang.String getAppsecret() {
		return appsecret;
	}

	/**
	 * @param appsecret
	 *            the appsecret to set
	 */
	public void setAppsecret(java.lang.String appsecret) {
		this.appsecret = appsecret;
	}

	/**
	 * @return the authenticationtime
	 */
	public java.util.Date getAuthenticationtime() {
		return authenticationtime;
	}

	/**
	 * @param authenticationtime
	 *            the authenticationtime to set
	 */
	public void setAuthenticationtime(java.util.Date authenticationtime) {
		this.authenticationtime = authenticationtime;
	}

	/**
	 * @return the url
	 */
	public java.lang.String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	/**
	 * @return the token
	 */
	public java.lang.String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(java.lang.String token) {
		this.token = token;
	}

	/**
	 * @return the encodingaeskey
	 */
	public java.lang.String getEncodingaeskey() {
		return encodingaeskey;
	}

	/**
	 * @param encodingaeskey
	 *            the encodingaeskey to set
	 */
	public void setEncodingaeskey(java.lang.String encodingaeskey) {
		this.encodingaeskey = encodingaeskey;
	}

	/**
	 * @return the encrypttype
	 */
	public java.lang.String getEncrypttype() {
		return encrypttype;
	}

	/**
	 * @param encrypttype
	 *            the encrypttype to set
	 */
	public void setEncrypttype(java.lang.String encrypttype) {
		this.encrypttype = encrypttype;
	}

	/**
	 * @return the jsconfigdebug
	 */
	public java.lang.Boolean getJsconfigdebug() {
		return jsconfigdebug;
	}

	/**
	 * @param jsconfigdebug
	 *            the jsconfigdebug to set
	 */
	public void setJsconfigdebug(java.lang.Boolean jsconfigdebug) {
		this.jsconfigdebug = jsconfigdebug;
	}

	/**
	 * @return the remark
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * @return the wxmsgid
	 */
	public java.lang.String getWxmsgid() {
		return wxmsgid;
	}

	/**
	 * @param wxmsgid
	 *            the wxmsgid to set
	 */
	public void setWxmsgid(java.lang.String wxmsgid) {
		this.wxmsgid = wxmsgid;
	}

	public java.lang.Boolean getIspromotion() {
		return ispromotion;
	}

	public void setIspromotion(java.lang.Boolean ispromotion) {
		this.ispromotion = ispromotion;
	}

	/**
	 * @return the disabled
	 */
	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the operatorid
	 */
	public java.lang.String getOperatorid() {
		return operatorid;
	}

	/**
	 * @param operatorid
	 *            the operatorid to set
	 */
	public void setOperatorid(java.lang.String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * @return the createtime
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updatetime
	 */
	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime
	 *            the updatetime to set
	 */
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public java.lang.String getDomain() {
		return domain;
	}

	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}

}
