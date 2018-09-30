package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>Purchase<br>
 */
@com.commons.annotation.Relation("t_purchase")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "no")
	private java.lang.String no; // no

	@com.commons.annotation.Column(value = "planid")
	private java.lang.String planid; // planid

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@com.commons.annotation.Column(value = "storecode")
	private java.lang.String storecode;// 门店编码

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "erpusername")
	private java.lang.String erpusername; // erpusername

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

	@com.commons.annotation.Column(value = "iremark")
	private java.lang.String iremark; // iremark

	@com.commons.annotation.Column(value = "send")
	private java.lang.Integer send; // status

	@com.commons.annotation.Column(value = "status")
	private java.lang.Integer status; // status

	@com.commons.annotation.Column(value = "num")
	private java.lang.Integer num; // num

	@com.commons.annotation.Column(value = "erpid")
	private java.lang.String erpid;

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

	public java.lang.String getNo() {
		return no;
	}

	public void setNo(java.lang.String no) {
		this.no = no;
	}

	public java.lang.String getPlanid() {
		return planid;
	}

	public void setPlanid(java.lang.String planid) {
		this.planid = planid;
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

	public java.lang.String getStorecode() {
		return storecode;
	}

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
	 * @param name
	 *            the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
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
	 * @return the iremark
	 */
	public java.lang.String getIremark() {
		return iremark;
	}

	/**
	 * @param iremark
	 *            the iremark to set
	 */
	public void setIremark(java.lang.String iremark) {
		this.iremark = iremark;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

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

	public java.lang.Integer getSend() {
		return send;
	}

	public void setSend(java.lang.Integer send) {
		this.send = send;
	}

	public java.lang.Integer getNum() {
		return num;
	}

	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	public java.lang.String getErpid() {
		return erpid;
	}

	public void setErpid(java.lang.String erpid) {
		this.erpid = erpid;
	}

	public java.lang.String getErpusername() {
		return erpusername;
	}

	public void setErpusername(java.lang.String erpusername) {
		this.erpusername = erpusername;
	}

}
