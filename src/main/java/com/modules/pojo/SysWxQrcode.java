package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>SysWxQrcode<br>
 */
@com.commons.annotation.Relation("sys_wx_qrcode")
public class SysWxQrcode implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "wxid")
	private java.lang.String wxid; // type

	@com.commons.annotation.Column(value = "scenestr")
	private java.lang.String scenestr; // scenestr

	@com.commons.annotation.Column(value = "expireSeconds")
	private java.lang.Integer expireseconds; // expireSeconds

	@com.commons.annotation.Column(value = "ticket")
	private java.lang.String ticket; // ticket

	@com.commons.annotation.Column(value = "url")
	private java.lang.String url; // url

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

	@com.commons.annotation.Column(value = "departmentid")
	private java.lang.String departmentid; // departmentid
	
	@com.commons.annotation.Column(value = "used")
	private java.lang.Boolean used; // used

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
	 * @return the wxid
	 */
	public java.lang.String getWxid() {
		return wxid;
	}

	/**
	 * @param wxid
	 *            the wxid to set
	 */
	public void setWxid(java.lang.String wxid) {
		this.wxid = wxid;
	}

	/**
	 * @return the scenestr
	 */
	public java.lang.String getScenestr() {
		return scenestr;
	}

	/**
	 * @param scenestr
	 *            the scenestr to set
	 */
	public void setScenestr(java.lang.String scenestr) {
		this.scenestr = scenestr;
	}

	/**
	 * @return the expireseconds
	 */
	public java.lang.Integer getExpireseconds() {
		return expireseconds;
	}

	/**
	 * @param expireseconds
	 *            the expireseconds to set
	 */
	public void setExpireseconds(java.lang.Integer expireseconds) {
		this.expireseconds = expireseconds;
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

	public java.lang.String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(java.lang.String departmentid) {
		this.departmentid = departmentid;
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

	public java.lang.String getTicket() {
		return ticket;
	}

	public void setTicket(java.lang.String ticket) {
		this.ticket = ticket;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.Boolean getUsed() {
		return used;
	}

	public void setUsed(java.lang.Boolean used) {
		this.used = used;
	}

}
