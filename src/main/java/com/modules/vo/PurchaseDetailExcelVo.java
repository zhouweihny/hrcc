package com.modules.vo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>PlanDetail<br>
 */
public class PurchaseDetailExcelVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ModelProp(name = "编码", colIndex = 0, nullable = false)
	private java.lang.String code; // code

	@ModelProp(name = "通用名", colIndex = 1, nullable = true)
	private java.lang.String common; // common

	@ModelProp(name = "品名", colIndex = 2, nullable = false)
	private java.lang.String name; // name

	@ModelProp(name = "规格", colIndex = 3, nullable = false)
	private java.lang.String specifications; // specifications

	@com.commons.annotation.Column(value = "unit")
	@ModelProp(name = "单位", colIndex = 4, nullable = true)
	private java.lang.String unit; // unit

	@ModelProp(name = "剂型", colIndex = 5, nullable = true)
	private java.lang.String dosageform; // dosageform

	@ModelProp(name = "厂商", colIndex = 6, nullable = false)
	private java.lang.String factory; // factory

	@ModelProp(name = "国药准字", colIndex = 7, nullable = true)
	private java.lang.String zunzi; // zunzi

	@ModelProp(name = "数量", colIndex = 8, nullable = true)
	private java.lang.Integer num; // num

	@ModelProp(name = "价格", colIndex = 9, nullable = true)
	private java.math.BigDecimal price; // price

	@ModelProp(name = "备注", colIndex = 10, nullable = true)
	private java.lang.String remark; // remark

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

	public java.lang.String getZunzi() {
		return zunzi;
	}

	public void setZunzi(java.lang.String zunzi) {
		this.zunzi = zunzi;
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

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

}
