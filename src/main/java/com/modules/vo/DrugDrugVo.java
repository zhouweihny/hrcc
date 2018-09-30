package com.modules.vo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun <b>功能：</b>Drug<br>
 */
@com.commons.annotation.Relation("t_drug")
public class DrugDrugVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

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

	@com.commons.annotation.Column(value = "dosageform")
	private java.lang.String dosageform; // dosageform

	@com.commons.annotation.Column(value = "factory")
	private java.lang.String factory; // factory

	@com.commons.annotation.Column(value = "zunzi")
	private java.lang.String zunzi; // zunzi

	@com.commons.annotation.Column(value = "scode")
	private java.lang.String scode; // code

	@com.commons.annotation.Column(value = "scommon")
	private java.lang.String scommon; // common

	@com.commons.annotation.Column(value = "sname")
	private java.lang.String sname; // name

	@com.commons.annotation.Column(value = "sspecifications")
	private java.lang.String sspecifications; // specifications

	@com.commons.annotation.Column(value = "sunit")
	private java.lang.String sunit; // unit

	@com.commons.annotation.Column(value = "sdosageform")
	private java.lang.String sdosageform; // dosageform

	@com.commons.annotation.Column(value = "sfactory")
	private java.lang.String sfactory; // factory

	@com.commons.annotation.Column(value = "szunzi")
	private java.lang.String szunzi; // zunzi

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

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

	public java.lang.String getScode() {
		return scode;
	}

	public void setScode(java.lang.String scode) {
		this.scode = scode;
	}

	public java.lang.String getScommon() {
		return scommon;
	}

	public void setScommon(java.lang.String scommon) {
		this.scommon = scommon;
	}

	public java.lang.String getSname() {
		return sname;
	}

	public void setSname(java.lang.String sname) {
		this.sname = sname;
	}

	public java.lang.String getSspecifications() {
		return sspecifications;
	}

	public void setSspecifications(java.lang.String sspecifications) {
		this.sspecifications = sspecifications;
	}

	public java.lang.String getSunit() {
		return sunit;
	}

	public void setSunit(java.lang.String sunit) {
		this.sunit = sunit;
	}

	public java.lang.String getSdosageform() {
		return sdosageform;
	}

	public void setSdosageform(java.lang.String sdosageform) {
		this.sdosageform = sdosageform;
	}

	public java.lang.String getSfactory() {
		return sfactory;
	}

	public void setSfactory(java.lang.String sfactory) {
		this.sfactory = sfactory;
	}

	public java.lang.String getSzunzi() {
		return szunzi;
	}

	public void setSzunzi(java.lang.String szunzi) {
		this.szunzi = szunzi;
	}


 

 
}
