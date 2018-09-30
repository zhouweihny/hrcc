package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxTreeMeta<br>
 */
@com.commons.annotation.Relation("fx_tree_meta")
public class FxTreeMeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "storetypeid")
	private java.lang.String storetypeid; // storetypeid

	@com.commons.annotation.Column(value = "treeid")
	private java.lang.String treeid; // treeid

	@com.commons.annotation.Column(value = "metaid")
	private java.lang.String metaid; // metaid

	@com.commons.annotation.Column(value = "methodid")
	private java.lang.String methodid; // methodid

	@com.commons.annotation.Column(value = "storeid")
	private java.lang.String storeid; // storeid

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@com.commons.annotation.Column(value = "scope")
	private java.lang.Integer scope; // scope

	@com.commons.annotation.Column(value = "standard")
	private java.lang.Boolean standard; // standard

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
	 * @return the treeid
	 */
	public java.lang.String getTreeid() {
		return treeid;
	}

	/**
	 * @param treeid
	 *            the treeid to set
	 */
	public void setTreeid(java.lang.String treeid) {
		this.treeid = treeid;
	}

	/**
	 * @return the metaid
	 */
	public java.lang.String getMetaid() {
		return metaid;
	}

	/**
	 * @param metaid
	 *            the metaid to set
	 */
	public void setMetaid(java.lang.String metaid) {
		this.metaid = metaid;
	}

	/**
	 * @return the methodid
	 */
	public java.lang.String getMethodid() {
		return methodid;
	}

	/**
	 * @param methodid
	 *            the methodid to set
	 */
	public void setMethodid(java.lang.String methodid) {
		this.methodid = methodid;
	}

	/**
	 * @return the storeid
	 */
	public java.lang.String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid
	 *            the storeid to set
	 */
	public void setStoreid(java.lang.String storeid) {
		this.storeid = storeid;
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
	 * @return the scope
	 */
	public java.lang.Integer getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(java.lang.Integer scope) {
		this.scope = scope;
	}

	/**
	 * @return the standard
	 */
	public java.lang.Boolean getStandard() {
		return standard;
	}

	/**
	 * @param standard
	 *            the standard to set
	 */
	public void setStandard(java.lang.Boolean standard) {
		this.standard = standard;
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

	public java.lang.String getStoretypeid() {
		return storetypeid;
	}

	public void setStoretypeid(java.lang.String storetypeid) {
		this.storetypeid = storetypeid;
	}

}
