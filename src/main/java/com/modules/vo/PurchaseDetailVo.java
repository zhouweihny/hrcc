package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>PurchaseDetail<br>
 */
public class PurchaseDetailVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "purchaseid")
	private java.lang.String purchaseid; // purchaseid
	@com.commons.annotation.Column(value = "code")
	private java.lang.String code; // code
	
	
	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // code

	@com.commons.annotation.Column(value = "common")
	private java.lang.String common; // common

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "specifications")
	private java.lang.String specifications; // specifications

	@com.commons.annotation.Column(value = "unit")
	private java.lang.String unit; // unit

	@com.commons.annotation.Column(value = "dosageform")
	private java.lang.String dosageform; // dosageform

	@com.commons.annotation.Column(value = "factory")
	private java.lang.String factory; // factory

	@com.commons.annotation.Column(value = "zunzi")
	private java.lang.String zunzi; // zunzi

	@com.commons.annotation.Column(value = "num")
	private java.lang.Integer num; // num

	@com.commons.annotation.Column(value = "price")
	private java.math.BigDecimal price; // price

	@com.commons.annotation.Column(value = "customprice")
	private java.math.BigDecimal customprice; // customprice

	@com.commons.annotation.Column(value = "bjnum")
	private java.lang.Integer bjnum; // 报价数量

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

	/**
	 * @return the common
	 */
	public java.lang.String getCommon() {
		return common;
	}

	/**
	 * @param common
	 *            the common to set
	 */
	public void setCommon(java.lang.String common) {
		this.common = common;
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
	 * @return the specifications
	 */
	public java.lang.String getSpecifications() {
		return specifications;
	}

	/**
	 * @param specifications
	 *            the specifications to set
	 */
	public void setSpecifications(java.lang.String specifications) {
		this.specifications = specifications;
	}

	/**
	 * @return the unit
	 */
	public java.lang.String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	/**
	 * @return the dosageform
	 */
	public java.lang.String getDosageform() {
		return dosageform;
	}

	/**
	 * @param dosageform
	 *            the dosageform to set
	 */
	public void setDosageform(java.lang.String dosageform) {
		this.dosageform = dosageform;
	}

	/**
	 * @return the factory
	 */
	public java.lang.String getFactory() {
		return factory;
	}

	/**
	 * @param factory
	 *            the factory to set
	 */
	public void setFactory(java.lang.String factory) {
		this.factory = factory;
	}

	/**
	 * @return the zunzi
	 */
	public java.lang.String getZunzi() {
		return zunzi;
	}

	/**
	 * @param zunzi
	 *            the zunzi to set
	 */
	public void setZunzi(java.lang.String zunzi) {
		this.zunzi = zunzi;
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

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public java.math.BigDecimal getCustomprice() {
		return customprice;
	}

	public void setCustomprice(java.math.BigDecimal customprice) {
		this.customprice = customprice;
	}

	public java.lang.Integer getBjnum() {
		return bjnum;
	}

	public void setBjnum(java.lang.Integer bjnum) {
		this.bjnum = bjnum;
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

	public java.lang.String getDrugid() {
		return drugid;
	}

	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
	}

}
