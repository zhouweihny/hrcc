package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>Store<br>
 */
@com.commons.annotation.Relation("t_store")
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@com.commons.annotation.Column(value = "storecode")
	private java.lang.String storecode; // storecode

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "address")
	private java.lang.String address; // address

	@com.commons.annotation.Column(value = "typeid")
	private java.lang.String typeid; // typeid

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

	@com.commons.annotation.Column(value = "iremark")
	private java.lang.String iremark; // iremark

	@com.commons.annotation.Column(value = "status")
	private java.lang.Integer status; // status

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	@com.commons.annotation.Column(value = "zyq")
	private java.lang.Boolean zyq; // zy
	
	@com.commons.annotation.Column(value = "llq")
	private java.lang.Boolean llq; // ll

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
	 * @param id the id to set
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
	 * @param userid the userid to set
	 */
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	/**
	 * @return the storecode
	 */
	public java.lang.String getStorecode() {
		return storecode;
	}

	/**
	 * @param storecode the storecode to set
	 */
	public void setStorecode(java.lang.String storecode) {
		this.storecode = storecode;
	}

	/**
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public java.lang.String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getTypeid() {
		return typeid;
	}

	public void setTypeid(java.lang.String typeid) {
		this.typeid = typeid;
	}

	/**
	 * @return the remark
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * @return the iremark
	 */
	public java.lang.String getIremark() {
		return iremark;
	}

	/**
	 * @param iremark the iremark to set
	 */
	public void setIremark(java.lang.String iremark) {
		this.iremark = iremark;
	}

	/**
	 * @return the status
	 */
	public java.lang.Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	/**
	 * @return the disabled
	 */
	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
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
	 * @param operatorid the operatorid to set
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
	 * @param createtime the createtime to set
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
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public java.lang.Boolean getZyq() {
		return zyq;
	}

	public void setZyq(java.lang.Boolean zyq) {
		this.zyq = zyq;
	}

	public java.lang.Boolean getLlq() {
		return llq;
	}

	public void setLlq(java.lang.Boolean llq) {
		this.llq = llq;
	}

	 
}
