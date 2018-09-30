package com.modules.vo.apipur;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetailVo {

	private java.lang.String code; // code

	private java.lang.String name; // name

	private java.lang.String specifications; // specifications

	private java.lang.String unit; // unit

	private java.lang.String factory; // factory

	private String batchno;

	private java.lang.String invoicecode; // 发票号

	private Date productionDate; // 生产日期

	private Date expirationDate;// 有效期

	private java.lang.Integer num; // num

	private BigDecimal price;
	
	private BigDecimal amount;//总金额

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

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public java.lang.String getInvoicecode() {
		return invoicecode;
	}

	public void setInvoicecode(java.lang.String invoicecode) {
		this.invoicecode = invoicecode;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}