package com.modules.vo.drugFX;

import java.math.BigDecimal;

public class FxDrugReviewVo {
	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id")
	private String id;
	@com.commons.annotation.Column(value = "code")
	private String code;
	@com.commons.annotation.Column(value = "common")
	private String common;
	@com.commons.annotation.Column(value = "name")
	private String name;
	@com.commons.annotation.Column(value = "specifications")
	private String specifications;
	@com.commons.annotation.Column(value = "unit")
	private String unit;
	@com.commons.annotation.Column(value = "dosageform")
	private String dosageform;
	@com.commons.annotation.Column(value = "factory")
	private String factory;
	@com.commons.annotation.Column(value = "zunzi")
	private String zunzi;
	@com.commons.annotation.Column(value = "costprice")
	private BigDecimal costprice;
	@com.commons.annotation.Column(value = "price")
	private BigDecimal price;
	@com.commons.annotation.Column(value = "comnameid")
	private String comnameid;
	@com.commons.annotation.Column(value = "company")
	private String company;
	@com.commons.annotation.Column(value = "statusname")
	private String statusname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDosageform() {
		return dosageform;
	}
	public void setDosageform(String dosageform) {
		this.dosageform = dosageform;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getZunzi() {
		return zunzi;
	}
	public void setZunzi(String zunzi) {
		this.zunzi = zunzi;
	}
	public BigDecimal getCostprice() {
		return costprice;
	}
	public void setCostprice(BigDecimal costprice) {
		this.costprice = costprice;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getComnameid() {
		return comnameid;
	}
	public void setComnameid(String comnameid) {
		this.comnameid = comnameid;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	
	
}
