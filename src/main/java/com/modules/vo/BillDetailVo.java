package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>BillDetail<br>
 */
public class BillDetailVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String puchasedetailid;

	private java.lang.String drugid; // drugid

	private java.lang.String code; // code

	private String storecode;

	private String storename;

	private String common;

	private java.lang.String name; // name

	private java.lang.String specifications; // specifications

	private java.lang.String unit; // unit

	private java.lang.String factory; // factory

	private java.lang.String dosageform;

	private java.lang.Integer num; // num

	private java.math.BigDecimal price; // price

	private java.lang.String supplierid; // supplierid

	private java.lang.String supplier;

	public java.lang.String getDrugid() {
		return drugid;
	}

	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
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

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public java.lang.String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(java.lang.String supplierid) {
		this.supplierid = supplierid;
	}

	public java.lang.String getDosageform() {
		return dosageform;
	}

	public void setDosageform(java.lang.String dosageform) {
		this.dosageform = dosageform;
	}

	public java.lang.String getPuchasedetailid() {
		return puchasedetailid;
	}

	public void setPuchasedetailid(java.lang.String puchasedetailid) {
		this.puchasedetailid = puchasedetailid;
	}

	public java.lang.String getSupplier() {
		return supplier;
	}

	public void setSupplier(java.lang.String supplier) {
		this.supplier = supplier;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

}
