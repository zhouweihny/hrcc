package com.modules.vo.apisc;

import java.math.BigDecimal;

public class PurchaseDetailVo {

	private String id;

	private String purchaseid;

	private java.lang.String drugid;

	private java.lang.String code; // code

	private java.lang.String name; // name

	private java.lang.String specifications; // specifications

	private java.lang.String unit; // unit

	private java.lang.String factory; // factory

	private java.lang.Integer num; // num

	private BigDecimal price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPurchaseid() {
		return purchaseid;
	}

	public java.lang.String getDrugid() {
		return drugid;
	}

	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
	}

	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
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

	public java.lang.Integer getNum() {
		return num;
	}

	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}