package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>User<br>
 */
@com.commons.annotation.Relation("t_user")
public class UserVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "username")
	private java.lang.String username; // username

	@com.commons.annotation.Column(value = "password")
	private java.lang.String password; // password

	@com.commons.annotation.Column(value = "email")
	private java.lang.String email; // email

	@com.commons.annotation.Column(value = "mobile")
	private java.lang.String mobile; // mobile

	@com.commons.annotation.Column(value = "qq")
	private java.lang.String qq; // qq

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "company")
	private java.lang.String company; // company

	@com.commons.annotation.Column(value = "scompany")
	private java.lang.String scompany; // company

	@com.commons.annotation.Column(value = "parentid")
	private java.lang.String parentid; // parentid

	@com.commons.annotation.Column(value = "supplierid")
	private java.lang.String supplierid; // supplierid

	@com.commons.annotation.Column(value = "newflag")
	private java.lang.Boolean newflag; // newflag

	@com.commons.annotation.Column(value = "mobileno")
	private java.lang.String mobileno; // mobileno

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

	@com.commons.annotation.Column(value = "usersupplierid")
	private java.lang.String usersupplierid; // mobileno

	@com.commons.annotation.Column(value = "used")
	private Boolean used;

	private Boolean checked;

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
	 * @return the username
	 */
	public java.lang.String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public java.lang.String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public java.lang.String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public java.lang.String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the qq
	 */
	public java.lang.String getQq() {
		return qq;
	}

	/**
	 * @param qq
	 *            the qq to set
	 */
	public void setQq(java.lang.String qq) {
		this.qq = qq;
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

	public java.lang.String getCompany() {
		return company;
	}

	public void setCompany(java.lang.String company) {
		this.company = company;
	}

	public java.lang.Boolean getNewflag() {
		return newflag;
	}

	public void setNewflag(java.lang.Boolean newflag) {
		this.newflag = newflag;
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

	public java.lang.String getParentid() {
		return parentid;
	}

	public void setParentid(java.lang.String parentid) {
		this.parentid = parentid;
	}

	public java.lang.String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(java.lang.String supplierid) {
		this.supplierid = supplierid;
	}

	public java.lang.String getScompany() {
		return scompany;
	}

	public void setScompany(java.lang.String scompany) {
		this.scompany = scompany;
	}

	public java.lang.String getMobileno() {
		return mobileno;
	}

	public void setMobileno(java.lang.String mobileno) {
		this.mobileno = mobileno;
	}

	public java.lang.String getUsersupplierid() {
		return usersupplierid;
	}

	public void setUsersupplierid(java.lang.String usersupplierid) {
		this.usersupplierid = usersupplierid;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
