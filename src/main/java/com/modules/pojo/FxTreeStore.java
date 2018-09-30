package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxTreeStore<br>
 */
@com.commons.annotation.Relation("fx_tree_store")
public class FxTreeStore implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "treeid")
	private java.lang.String treeid; // treeid

	@com.commons.annotation.Column(value = "storeid")
	private java.lang.String storeid; // storeid

	@com.commons.annotation.Column(value = "sort")
	private java.lang.Integer sort; // sort

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

	public java.lang.String getTreeid() {
		return treeid;
	}

	public void setTreeid(java.lang.String treeid) {
		this.treeid = treeid;
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
	 * @return the sort
	 */
	public java.lang.Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
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
