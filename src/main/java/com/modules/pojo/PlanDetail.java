package com.modules.pojo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>PlanDetail<br>
 */
@com.commons.annotation.Relation("t_plan_detail")
public class PlanDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "planid")
	private java.lang.String planid; // planid

	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid

	@ModelProp(name = "编码", colIndex = 0, nullable = false)
	@com.commons.annotation.Column(value = "code")
	private java.lang.String code; // code

	@com.commons.annotation.Column(value = "common")
	@ModelProp(name = "通用名", colIndex = 1, nullable = true)
	private java.lang.String common; // common

	@com.commons.annotation.Column(value = "name")
	@ModelProp(name = "品名", colIndex = 2, nullable = false)
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "specifications")
	@ModelProp(name = "规格", colIndex = 3, nullable = false)
	private java.lang.String specifications; // specifications

	@com.commons.annotation.Column(value = "unit")
	@ModelProp(name = "单位", colIndex = 4, nullable = true)
	private java.lang.String unit; // unit

	@com.commons.annotation.Column(value = "dosageform")
	@ModelProp(name = "剂型", colIndex = 5, nullable = true)
	private java.lang.String dosageform; // dosageform

	@com.commons.annotation.Column(value = "factory")
	@ModelProp(name = "厂商", colIndex = 6, nullable = false)
	private java.lang.String factory; // factory

	@com.commons.annotation.Column(value = "zunzi")
	@ModelProp(name = "国药准字", colIndex = 7, nullable = true)
	private java.lang.String zunzi; // zunzi

	@ModelProp(name = "数量", colIndex = 8, nullable = true)
	@com.commons.annotation.Column(value = "num")
	private java.lang.Integer num; // num

	@ModelProp(name = "价格", colIndex = 9, nullable = true)
	@com.commons.annotation.Column(value = "price")
	private java.math.BigDecimal price; // price

	@com.commons.annotation.Column(value = "purchaserid")
	private java.lang.String purchaserid; // purchaserid

	@com.commons.annotation.Column(value = "supplierid")
	private java.lang.String supplierid; // supplierid

	@com.commons.annotation.Column(value = "status")
	private java.lang.Integer status; // status

	@com.commons.annotation.Column(value = "sended")
	private java.lang.Boolean sended; // sended

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
	 * @return the planid
	 */
	public java.lang.String getPlanid() {
		return planid;
	}

	/**
	 * @param planid
	 *            the planid to set
	 */
	public void setPlanid(java.lang.String planid) {
		this.planid = planid;
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
	 * @return the price
	 */
	public java.math.BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
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

	/**
	 * @return the sended
	 */
	public java.lang.Boolean getSended() {
		return sended;
	}

	/**
	 * @param sended
	 *            the sended to set
	 */
	public void setSended(java.lang.Boolean sended) {
		this.sended = sended;
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

	public java.lang.String getCommon() {
		return common;
	}

	public void setCommon(java.lang.String common) {
		this.common = common;
	}

	public java.lang.String getDosageform() {
		return dosageform;
	}

	public void setDosageform(java.lang.String dosageform) {
		this.dosageform = dosageform;
	}

	public java.lang.String getZunzi() {
		return zunzi;
	}

	public void setZunzi(java.lang.String zunzi) {
		this.zunzi = zunzi;
	}

}
