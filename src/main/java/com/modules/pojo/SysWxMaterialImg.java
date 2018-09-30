package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>SysWxMaterialImg<br>
 */
@com.commons.annotation.Relation("sys_wx_material_img")
public class SysWxMaterialImg implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "wxid")
	private java.lang.String wxid; // wxid

	@com.commons.annotation.Column(value = "igroup")
	private java.lang.String igroup; // group

	@com.commons.annotation.Column(value = "mediaid")
	private java.lang.String mediaid; // mediaid

	@com.commons.annotation.Column(value = "path")
	private java.lang.String path; // path

	@com.commons.annotation.Column(value = "url")
	private java.lang.String url; // url

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

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

	public java.lang.String getIgroup() {
		return igroup;
	}

	public void setIgroup(java.lang.String igroup) {
		this.igroup = igroup;
	}

	/**
	 * @return the mediaid
	 */
	public java.lang.String getMediaid() {
		return mediaid;
	}

	/**
	 * @param mediaid
	 *            the mediaid to set
	 */
	public void setMediaid(java.lang.String mediaid) {
		this.mediaid = mediaid;
	}

	/**
	 * @return the path
	 */
	public java.lang.String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(java.lang.String path) {
		this.path = path;
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

}
