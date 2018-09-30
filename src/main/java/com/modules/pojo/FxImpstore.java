package com.modules.pojo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxImpstore<br>
 */
@com.commons.annotation.Relation("fx_impstore")
public class FxImpstore implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "storeid")
	private java.lang.String storeid; // storeid

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "createtime")
	private java.util.Date createtime; // createtime

	@com.commons.annotation.Column(value = "operatorid")
	private java.lang.String operatorid; // operatorid

	@com.commons.annotation.Column(value = "name")
	@ModelProp(name = "品名", colIndex = 1, nullable = false)
	private java.lang.String name; // name

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "updatetime")
	private java.util.Date updatetime; // updatetime

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@ModelProp(name = "编码", colIndex = 0, nullable = false)
	private java.lang.String code; // code

	@ModelProp(name = "规格", colIndex = 2, nullable = true)
	private java.lang.String specifications; // specifications

	@ModelProp(name = "单位", colIndex = 3, nullable = true)
	private java.lang.String unit; // unit

	@ModelProp(name = "剂型", colIndex = 4, nullable = true)
	private java.lang.String dosageform; // dosageform

	@ModelProp(name = "厂商", colIndex = 5, nullable = false)
	private java.lang.String factory; // factory

	@ModelProp(name = "国药准字", colIndex = 6, nullable = true)
	private java.lang.String zunzi; // zunzi

	@com.commons.annotation.Column(value = "qty")
	@ModelProp(name = "数量", colIndex = 7, nullable = false)
	private java.math.BigDecimal qty; // qty

	@com.commons.annotation.Column(value = "costprice")
	@ModelProp(name = "成本单价", colIndex = 8, nullable = true)
	private java.math.BigDecimal costprice; // costprice

	@com.commons.annotation.Column(value = "amt")
	@ModelProp(name = "金额", colIndex = 9, nullable = true)
	private java.math.BigDecimal amt; // amt

	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid

	@com.commons.annotation.Column(value = "comnameid")
	private java.lang.String comnameid;

	@com.commons.annotation.Column(value = "impfilenameid")
	private java.lang.String impfilenameid;

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
	 * @return the qty
	 */
	public java.math.BigDecimal getQty() {
		return qty;
	}

	/**
	 * @param qty
	 *            the qty to set
	 */
	public void setQty(java.math.BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * @return the costprice
	 */
	public java.math.BigDecimal getCostprice() {
		return costprice;
	}

	/**
	 * @param costprice
	 *            the costprice to set
	 */
	public void setCostprice(java.math.BigDecimal costprice) {
		this.costprice = costprice;
	}

	/**
	 * @return the amt
	 */
	public java.math.BigDecimal getAmt() {
		return amt;
	}

	/**
	 * @param amt
	 *            the amt to set
	 */
	public void setAmt(java.math.BigDecimal amt) {
		this.amt = amt;
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

	public java.lang.String getComnameid() {
		return comnameid;
	}

	public void setComnameid(java.lang.String comnameid) {
		this.comnameid = comnameid;
	}

	public java.lang.String getImpfilenameid() {
		return impfilenameid;
	}

	public void setImpfilenameid(java.lang.String impfilenameid) {
		this.impfilenameid = impfilenameid;
	}

}
