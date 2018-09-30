package com.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxDrugStore<br>
 */
@com.commons.annotation.Relation("fx_drug_store")
public class FxDrugStore implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid

	@com.commons.annotation.Column(value = "storeid")
	private java.lang.String storeid; // storeid

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@com.commons.annotation.Column(value = "sort")
	private java.lang.Integer sort; // sort

	@com.commons.annotation.Column(value = "zy")
	private java.lang.Boolean zy; // eliminate

	@com.commons.annotation.Column(value = "status")
	private java.lang.Boolean status; // status

	@com.commons.annotation.Column(value = "xsrq")
	private Date xsrq;

	@com.commons.annotation.Column(value = "bhrq")
	private Date bhrq;

	@com.commons.annotation.Column(value = "kc")
	private Integer kc;

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
	 * @param id the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * @return the drugid
	 */
	public java.lang.String getDrugid() {
		return drugid;
	}

	/**
	 * @param drugid the drugid to set
	 */
	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
	}

	/**
	 * @return the storeid
	 */
	public java.lang.String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(java.lang.String storeid) {
		this.storeid = storeid;
	}

	/**
	 * @return the sort
	 */
	public java.lang.Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
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

	public java.lang.Boolean getZy() {
		return zy;
	}

	public void setZy(java.lang.Boolean zy) {
		this.zy = zy;
	}

	public java.lang.Boolean getStatus() {
		return status;
	}

	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}

	public Date getBhrq() {
		return bhrq;
	}

	public void setBhrq(Date bhrq) {
		this.bhrq = bhrq;
	}

	public Date getXsrq() {
		return xsrq;
	}

	public void setXsrq(Date xsrq) {
		this.xsrq = xsrq;
	}

	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	public Integer getKc() {
		return kc;
	}

	public void setKc(Integer kc) {
		this.kc = kc;
	}

}
