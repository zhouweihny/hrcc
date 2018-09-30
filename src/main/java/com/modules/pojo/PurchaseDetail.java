package com.modules.pojo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>PurchaseDetail<br>
 */
@com.commons.annotation.Relation("t_purchase_detail")
public class PurchaseDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "purchaseid")
	
	
	private java.lang.String purchaseid; // purchaseid
	
	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid
	
	@com.commons.annotation.Column(value = "code")
	@ModelProp(name = "编码", colIndex = 0, nullable = false)
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

	public java.lang.String getDrugid() {
		return drugid;
	}

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
