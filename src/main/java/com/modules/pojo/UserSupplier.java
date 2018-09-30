package com.modules.pojo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>UserSupplier<br>
 */
@com.commons.annotation.Relation("t_user_supplier")
public class UserSupplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@com.commons.annotation.Column(value = "supplierid")
	private java.lang.String supplierid; // supplierid

	@com.commons.annotation.Column(value = "contactid")
	private java.lang.String contactid; // contactid

	@com.commons.annotation.Column(value = "send")
	private java.lang.Boolean send; // send

	@com.commons.annotation.Column(value = "used")
	private java.lang.Boolean used; // used

	@com.commons.annotation.Column(value = "code")
	@ModelProp(name = "编码", colIndex = 0, nullable = false)
	private String code;

	@com.commons.annotation.Column(value = "name")
	@ModelProp(name = "名称", colIndex = 1, nullable = false)
	private String name;

	@com.commons.annotation.Column(value = "mobile")
	@ModelProp(name = "手机号", colIndex = 2, nullable = true)
	private String mobile;

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
	 * @return the userid
	 */
	public java.lang.String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	/**
	 * @return the supplierid
	 */
	public java.lang.String getSupplierid() {
		return supplierid;
	}

	/**
	 * @param supplierid
	 *            the supplierid to set
	 */
	public void setSupplierid(java.lang.String supplierid) {
		this.supplierid = supplierid;
	}

	public java.lang.String getContactid() {
		return contactid;
	}

	public void setContactid(java.lang.String contactid) {
		this.contactid = contactid;
	}

	public java.lang.Boolean getSend() {
		return send;
	}

	public void setSend(java.lang.Boolean send) {
		this.send = send;
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

	public java.lang.Boolean getUsed() {
		return used;
	}

	public void setUsed(java.lang.Boolean used) {
		this.used = used;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
