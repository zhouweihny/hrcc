package com.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Du.Jun <b>功能：</b>Bill<br>
 */
@com.commons.annotation.Relation("t_bill")
public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "no")
	private java.lang.String no;

	@com.commons.annotation.Column(value = "planid")
	private java.lang.String planid;

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name;

	@com.commons.annotation.Column(value = "code")
	private java.lang.String code; // code

	@com.commons.annotation.Column(value = "storecode")
	private java.lang.String storecode;

	@com.commons.annotation.Column(value = "agentid")
	private java.lang.String agentid; // agentid

	@com.commons.annotation.Column(value = "purchaserid")
	private java.lang.String purchaserid; // purchaserid

	@com.commons.annotation.Column(value = "supplierid")
	private java.lang.String supplierid; // supplierid

	@com.commons.annotation.Column(value = "erpsucode")
	private String erpsucode;

	@com.commons.annotation.Column(value = "status")
	private java.lang.Integer status; // status

	@com.commons.annotation.Column(value = "num")
	private java.lang.Integer num; // num

	@com.commons.annotation.Column(value = "erpid")
	private java.lang.String erpid;

	@com.commons.annotation.Column(value = "erpusername")
	private java.lang.String erpusername;

	@com.commons.annotation.Column(value = "mobile")
	private java.lang.String mobile;

	@com.commons.annotation.Column(value = "arrivaltime")
	private Date arrivaltime;

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

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getStorecode() {
		return storecode;
	}

	public void setStorecode(java.lang.String storecode) {
		this.storecode = storecode;
	}

	/**
	 * @return the code
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}

	/**
	 * @return the purchaserid
	 */
	public java.lang.String getPurchaserid() {
		return purchaserid;
	}

	/**
	 * @param purchaserid
	 *            the purchaserid to set
	 */
	public void setPurchaserid(java.lang.String purchaserid) {
		this.purchaserid = purchaserid;
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
	 * @return the status
	 */
	public java.lang.Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
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

	public java.lang.Integer getNum() {
		return num;
	}

	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	public String getErpsucode() {
		return erpsucode;
	}

	public void setErpsucode(String erpsucode) {
		this.erpsucode = erpsucode;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public Date getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(Date arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

}
