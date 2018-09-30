package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxTree<br>
 */
@com.commons.annotation.Relation("fx_tree")
public class FxTree implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "parentid")
	private java.lang.String parentid; // parentid

	@com.commons.annotation.Column(value = "path")
	private java.lang.String path; // path

	@com.commons.annotation.Column(value = "sort")
	private Integer sort; // sort

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	@com.commons.annotation.Column(value = "analysis")
	private java.lang.Boolean analysis; // analysis

	@com.commons.annotation.Column(value = "userid")
	private String userid;

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
	 * @return the parentid
	 */
	public java.lang.String getParentid() {
		return parentid;
	}

	/**
	 * @param parentid
	 *            the parentid to set
	 */
	public void setParentid(java.lang.String parentid) {
		this.parentid = parentid;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public java.lang.Boolean getAnalysis() {
		return analysis;
	}

	public void setAnalysis(java.lang.Boolean analysis) {
		this.analysis = analysis;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
