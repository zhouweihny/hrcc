package com.modules.vo.apipur;

import java.math.BigDecimal;

public class DrugVo {

	@com.commons.annotation.Column(value = "code")
	private java.lang.String code; // code

	@com.commons.annotation.Column(value = "common")
	private java.lang.String common; // common

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "specifications")
	private java.lang.String specifications; // specifications

	@com.commons.annotation.Column(value = "unit")
	private java.lang.String unit; // unit

	@com.commons.annotation.Column(value = "factory")
	private java.lang.String factory; // factory

	@com.commons.annotation.Column(value = "zunzi")
	private java.lang.String zunzi; // zunzi

	@com.commons.annotation.Column(value = "erpusername")
	private java.lang.String erpusername;

	@com.commons.annotation.Column(value = "taxrate")
	private Integer taxrate;

	@com.commons.annotation.Column(value = "mobile")
	private String mobile;

	@com.commons.annotation.Column(value = "erpsucode")
	private String erpsucode;

	@com.commons.annotation.Column(value = "price")
	private BigDecimal price;

	@com.commons.annotation.Column(value = "suremark")
	private String suremark;

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getCommon() {
		return common;
	}

	public void setCommon(java.lang.String common) {
		this.common = common;
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

	public java.lang.String getFactory() {
		return factory;
	}

	public void setFactory(java.lang.String factory) {
		this.factory = factory;
	}

	public java.lang.String getZunzi() {
		return zunzi;
	}

	public void setZunzi(java.lang.String zunzi) {
		this.zunzi = zunzi;
	}

	public java.lang.String getErpusername() {
		return erpusername;
	}

	public void setErpusername(java.lang.String erpusername) {
		this.erpusername = erpusername;
	}

	public Integer getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Integer taxrate) {
		this.taxrate = taxrate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getErpsucode() {
		return erpsucode;
	}

	public void setErpsucode(String erpsucode) {
		this.erpsucode = erpsucode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSuremark() {
		return suremark;
	}

	public void setSuremark(String suremark) {
		this.suremark = suremark;
	}

 
}
