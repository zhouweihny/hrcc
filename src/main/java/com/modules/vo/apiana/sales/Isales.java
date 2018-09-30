package com.modules.vo.apiana.sales;

import java.math.BigDecimal;
import java.util.Date;

public class Isales {

	private String name; // name

	private String code; // code

	private String specifications; // specifications

	private String unit; // unit

	private String dosageform; // dosageform

	private String factory; // factory

	private String zunzi; // zunzi

	private BigDecimal qty; // qty

	private BigDecimal costprice; // costprice

	private BigDecimal price; // price

	private Date saledate; // bizdate

	private String orderno;

	private String xssx;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
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

	public Date getSaledate() {
		return saledate;
	}

	public void setSaledate(Date saledate) {
		this.saledate = saledate;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

}
