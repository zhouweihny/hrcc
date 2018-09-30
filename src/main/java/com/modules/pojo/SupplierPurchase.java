package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>SupplierPurchase<br>
 */
@com.commons.annotation.Relation("t_supplier_purchase")
public class SupplierPurchase implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "supplierid")
	private java.lang.String supplierid; // supplierid

	@com.commons.annotation.Column(value = "purchaseid")
	private java.lang.String purchaseid; // purchaseid

	@com.commons.annotation.Column(value = "status")
	private java.lang.Integer status; // status

	@com.commons.annotation.Column(value = "send")
	private java.lang.Integer send; // send

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "expirydate")
	private java.util.Date expirydate; // expirydate

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

	/**
	 * @return the purchaseid
	 */
	public java.lang.String getPurchaseid() {
		return purchaseid;
	}

	/**
	 * @param purchaseid
	 *            the purchaseid to set
	 */
	public void setPurchaseid(java.lang.String purchaseid) {
		this.purchaseid = purchaseid;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.Integer getSend() {
		return send;
	}

	public void setSend(java.lang.Integer send) {
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

	public java.util.Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(java.util.Date expirydate) {
		this.expirydate = expirydate;
	}

}
