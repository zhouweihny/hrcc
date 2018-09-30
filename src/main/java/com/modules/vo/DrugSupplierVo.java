package com.modules.vo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>DrugSupplier<br>
 */
public class DrugSupplierVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid

	@com.commons.annotation.Column(value = "code")
	private java.lang.String code; // code

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
	
	@com.commons.annotation.Column(value = "taxrate")
	private Integer taxrate; 

	@com.commons.annotation.Column(value = "supplierid")
	private java.lang.String supplierid; // supplierid

	@com.commons.annotation.Column(value = "supplier")
	private java.lang.String supplier; // supplierid

	@com.commons.annotation.Column(value = "purchaserid")
	private java.lang.String purchaserid; // purchaserid
	@com.commons.annotation.Column(value = "agentid")
	private String agentid;
	@com.commons.annotation.Column(value = "agent")
	private String agent;

	@com.commons.annotation.Column(value = "mobileno")
	private String mobileno;

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

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(java.lang.String specifications) {
		this.specifications = specifications;
	}

	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.String getDosageform() {
		return dosageform;
	}

	public void setDosageform(java.lang.String dosageform) {
		this.dosageform = dosageform;
	}

	public java.lang.String getFactory() {
		return factory;
	}

	public void setFactory(java.lang.String factory) {
		this.factory = factory;
	}

	public java.lang.String getSupplier() {
		return supplier;
	}

	public void setSupplier(java.lang.String supplier) {
		this.supplier = supplier;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public Integer getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Integer taxrate) {
		this.taxrate = taxrate;
	}

}
