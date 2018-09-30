package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>DrugDrugDel<br>
 */
@com.commons.annotation.Relation("t_drug_drug_del")
public class DrugDrugDel implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "spaceid")
	private java.lang.String spaceid; // spaceid

	@com.commons.annotation.Column(value = "sdrugid")
	private java.lang.String sdrugid; // sdrugid

	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid

	@com.commons.annotation.Column(value = "num")
	private java.lang.Integer num; // num

	@com.commons.annotation.Column(value = "checked")
	private java.lang.Boolean checked; // checked

	@com.commons.annotation.Column(value = "checker")
	private java.lang.String checker; // checker

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

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
	 * @return the spaceid
	 */
	public java.lang.String getSpaceid() {
		return spaceid;
	}

	/**
	 * @param spaceid
	 *            the spaceid to set
	 */
	public void setSpaceid(java.lang.String spaceid) {
		this.spaceid = spaceid;
	}

	/**
	 * @return the sdrugid
	 */
	public java.lang.String getSdrugid() {
		return sdrugid;
	}

	/**
	 * @param sdrugid
	 *            the sdrugid to set
	 */
	public void setSdrugid(java.lang.String sdrugid) {
		this.sdrugid = sdrugid;
	}

	/**
	 * @return the drugid
	 */
	public java.lang.String getDrugid() {
		return drugid;
	}

	/**
	 * @param drugid
	 *            the drugid to set
	 */
	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
	}

	/**
	 * @return the num
	 */
	public java.lang.Integer getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	/**
	 * @return the checked
	 */
	public java.lang.Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(java.lang.Boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the checker
	 */
	public java.lang.String getChecker() {
		return checker;
	}

	/**
	 * @param checker
	 *            the checker to set
	 */
	public void setChecker(java.lang.String checker) {
		this.checker = checker;
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

	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
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
