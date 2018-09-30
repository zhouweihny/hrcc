package com.modules.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>Drug<br>
 */
@com.commons.annotation.Relation("t_drug")
public class Drug implements Serializable {

	private static final long serialVersionUID = 1L;
	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "catalogid")
	private java.lang.String catalogid; // catalogid

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@com.commons.annotation.Column(value = "code")
	@ModelProp(name = "编码", colIndex = 0, nullable = false)
	private java.lang.String code; // code

	@com.commons.annotation.Column(value = "common")
	@ModelProp(name = "通用名", colIndex = 1, nullable = true)
	private java.lang.String common; // common

	@com.commons.annotation.Column(value = "name")
	@ModelProp(name = "品名", colIndex = 2, nullable = false)
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "specifications")
	@ModelProp(name = "规格", colIndex = 3, nullable = false)
	private java.lang.String specifications; // specifications

	@com.commons.annotation.Column(value = "unit")
	@ModelProp(name = "单位", colIndex = 4, nullable = true)
	private java.lang.String unit; // unit

	@com.commons.annotation.Column(value = "dosageform")
	@ModelProp(name = "剂型", colIndex = 5, nullable = true)
	private java.lang.String dosageform; // dosageform

	@com.commons.annotation.Column(value = "factory")
	@ModelProp(name = "厂商", colIndex = 6, nullable = false)
	private java.lang.String factory; // factory

	@com.commons.annotation.Column(value = "zunzi")
	@ModelProp(name = "国药准字", colIndex = 7, nullable = true)
	private java.lang.String zunzi; // zunzi

	@com.commons.annotation.Column(value = "erpusername")
	@ModelProp(name = "采购员", colIndex = 8, nullable = true)
	private java.lang.String erpusername;

	@com.commons.annotation.Column(value = "taxrate")
	@ModelProp(name = "税率", colIndex = 9, nullable = true)
	private Integer taxrate;

	@com.commons.annotation.Column(value = "costprice")
	@ModelProp(name = "成本价", colIndex = 10, nullable = true)
	private BigDecimal costprice;

	@com.commons.annotation.Column(value = "price")
	@ModelProp(name = "建议零售价", colIndex = 11, nullable = true)
	private BigDecimal price;

	@com.commons.annotation.Column(value = "operatorid")
	private java.lang.String operatorid; // operatorid

	@com.commons.annotation.Column(value = "comnameid")
	private java.lang.String comnameid;

	@com.commons.annotation.Column(value = "comnameuserid")
	private java.lang.String comnameuserid;

	@com.commons.annotation.Column(value = "xssx")
	private java.lang.String xssx;

	@com.commons.annotation.Column(value = "zy")
	private Boolean zy;
	@com.commons.annotation.Column(value = "ll")
	private Boolean ll;

	@com.commons.annotation.Column(value = "ywflag")
	private Boolean ywflag;
	@com.commons.annotation.Column(value = "ybflag")
	private Boolean ybflag;
	@com.commons.annotation.Column(value = "bbflag")
	private Boolean bbflag;
	@com.commons.annotation.Column(value = "ppflag")
	private Boolean ppflag;
	@com.commons.annotation.Column(value = "ggflag")
	private Boolean ggflag;
	@com.commons.annotation.Column(value = "sdrugid")
	private String sdrugid;

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "createtime")
	private java.util.Date createtime; // createtime

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "updatetime")
	private java.util.Date updatetime; // updatetime

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * @return the catalogid
	 */
	public java.lang.String getCatalogid() {
		return catalogid;
	}

	/**
	 * @param catalogid the catalogid to set
	 */
	public void setCatalogid(java.lang.String catalogid) {
		this.catalogid = catalogid;
	}

	/**
	 * @return the code
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * @return the common
	 */
	public java.lang.String getCommon() {
		return common;
	}

	/**
	 * @param common the common to set
	 */
	public void setCommon(java.lang.String common) {
		this.common = common;
	}

	/**
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * @return the specifications
	 */
	public java.lang.String getSpecifications() {
		return specifications;
	}

	/**
	 * @param specifications the specifications to set
	 */
	public void setSpecifications(java.lang.String specifications) {
		this.specifications = specifications;
	}

	/**
	 * @return the unit
	 */
	public java.lang.String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	/**
	 * @return the dosageform
	 */
	public java.lang.String getDosageform() {
		return dosageform;
	}

	/**
	 * @param dosageform the dosageform to set
	 */
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

	/**
	 * @return the operatorid
	 */
	public java.lang.String getOperatorid() {
		return operatorid;
	}

	/**
	 * @param operatorid the operatorid to set
	 */
	public void setOperatorid(java.lang.String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * @return the createtime
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updatetime
	 */
	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public java.lang.String getComnameid() {
		return comnameid;
	}

	public void setComnameid(java.lang.String comnameid) {
		this.comnameid = comnameid;
	}

	public java.lang.String getComnameuserid() {
		return comnameuserid;
	}

	public void setComnameuserid(java.lang.String comnameuserid) {
		this.comnameuserid = comnameuserid;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCostprice() {
		return costprice;
	}

	public void setCostprice(BigDecimal costprice) {
		this.costprice = costprice;
	}

	public Boolean getYwflag() {
		return ywflag;
	}

	public void setYwflag(Boolean ywflag) {
		this.ywflag = ywflag;
	}

	public Boolean getYbflag() {
		return ybflag;
	}

	public void setYbflag(Boolean ybflag) {
		this.ybflag = ybflag;
	}

	public Boolean getBbflag() {
		return bbflag;
	}

	public void setBbflag(Boolean bbflag) {
		this.bbflag = bbflag;
	}

	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	public Boolean getPpflag() {
		return ppflag;
	}

	public void setPpflag(Boolean ppflag) {
		this.ppflag = ppflag;
	}

	public Boolean getGgflag() {
		return ggflag;
	}

	public void setGgflag(Boolean ggflag) {
		this.ggflag = ggflag;
	}

	public String getSdrugid() {
		return sdrugid;
	}

	public void setSdrugid(String sdrugid) {
		this.sdrugid = sdrugid;
	}

	public java.lang.String getXssx() {
		return xssx;
	}

	public void setXssx(java.lang.String xssx) {
		this.xssx = xssx;
	}

	public Boolean getZy() {
		return zy;
	}

	public void setZy(Boolean zy) {
		this.zy = zy;
	}

	public Boolean getLl() {
		return ll;
	}

	public void setLl(Boolean ll) {
		this.ll = ll;
	}

}
