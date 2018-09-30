package com.modules.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>Drug<br>
 */
public class GyDrugExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ModelProp(name = "编码", colIndex = 0, nullable = false)
	private java.lang.String code; // code

	@ModelProp(name = "品名", colIndex = 1, nullable = false)
	private java.lang.String name; // name

	@ModelProp(name = "规格", colIndex = 2, nullable = false)
	private java.lang.String specifications; // specifications

	@ModelProp(name = "单位", colIndex = 3, nullable = true)
	private java.lang.String unit; // unit

	@ModelProp(name = "厂商", colIndex = 4, nullable = false)
	private java.lang.String factory; // factory

	@ModelProp(name = "成本价", colIndex = 5, nullable = true)
	private BigDecimal costprice;

	@ModelProp(name = "建议零售价", colIndex = 6, nullable = true)
	private BigDecimal price;

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

}
