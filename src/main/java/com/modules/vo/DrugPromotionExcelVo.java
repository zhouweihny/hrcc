package com.modules.vo;

import java.util.Date;

import com.commons.util.excel.ModelProp;

public class DrugPromotionExcelVo {

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

	@ModelProp(name = "内容", colIndex = 5, nullable = false)
	private java.lang.String content; // content

	@ModelProp(name = "开始时间", colIndex = 6, nullable = false)
	private Date startdate; // content

	@ModelProp(name = "结束时间", colIndex = 7, nullable = false)
	private Date enddate; // content

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

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}
