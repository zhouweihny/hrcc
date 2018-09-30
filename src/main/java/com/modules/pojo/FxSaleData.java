package com.modules.pojo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxSaleData<br>
 */
@com.commons.annotation.Relation("fx_sale_data")
public class FxSaleData implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "fileid")
	private java.lang.String fileid; // fileid

	@com.commons.annotation.Column(value = "storeid")
	private java.lang.String storeid; // storeid

	@ModelProp(name = "订单号", colIndex = 0, nullable = false)
	@com.commons.annotation.Column(value = "orderno")
	private java.lang.String orderno; // orderno

	@ModelProp(name = "销售日期", colIndex = 1, nullable = false)
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "saledate")
	private java.util.Date saledate; // saledate

	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid

	@ModelProp(name = "编码", colIndex = 2, nullable = false)
	@com.commons.annotation.Column(value = "code")
	private java.lang.String code; // code

	@ModelProp(name = "名称", colIndex = 3, nullable = false)
	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@ModelProp(name = "规格", colIndex = 4, nullable = false)
	@com.commons.annotation.Column(value = "specifications")
	private java.lang.String specifications; // specifications

	@ModelProp(name = "单位", colIndex = 5, nullable = true)
	@com.commons.annotation.Column(value = "unit")
	private java.lang.String unit; // unit

	@ModelProp(name = "厂商", colIndex = 6, nullable = false)
	@com.commons.annotation.Column(value = "factory")
	private java.lang.String factory; // factory

	@com.commons.annotation.Column(value = "zunzi")
	private java.lang.String zunzi; // zunzi

	@ModelProp(name = "销售数量", colIndex = 7, nullable = false)
	@com.commons.annotation.Column(value = "qty")
	private java.math.BigDecimal qty; // qty

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@ModelProp(name = "销售成本", colIndex = 8, nullable = false)
	@com.commons.annotation.Column(value = "costprice")
	private java.math.BigDecimal costprice; // costprice

	@ModelProp(name = "销售金额", colIndex = 9, nullable = false)
	@com.commons.annotation.Column(value = "price")
	private java.math.BigDecimal price; // price

	@ModelProp(name = "补货日期", colIndex = 10, nullable = true)
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "bhrq")
	private java.util.Date bhrq; // bhrq

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "createtime")
	private java.util.Date createtime; // createtime

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "updatetime")
	private java.util.Date updatetime; // updatetime

	@com.commons.annotation.Column(value = "operatorid")
	private java.lang.String operatorid; // operatorid

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	private String xxsx;

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
	 * @return the fileid
	 */
	public java.lang.String getFileid() {
		return fileid;
	}

	/**
	 * @param fileid the fileid to set
	 */
	public void setFileid(java.lang.String fileid) {
		this.fileid = fileid;
	}

	/**
	 * @return the storeid
	 */
	public java.lang.String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(java.lang.String storeid) {
		this.storeid = storeid;
	}

	/**
	 * @return the orderno
	 */
	public java.lang.String getOrderno() {
		return orderno;
	}

	/**
	 * @param orderno the orderno to set
	 */
	public void setOrderno(java.lang.String orderno) {
		this.orderno = orderno;
	}

	/**
	 * @return the drugid
	 */
	public java.lang.String getDrugid() {
		return drugid;
	}

	/**
	 * @param drugid the drugid to set
	 */
	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
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
	 * @return the factory
	 */
	public java.lang.String getFactory() {
		return factory;
	}

	/**
	 * @param factory the factory to set
	 */
	public void setFactory(java.lang.String factory) {
		this.factory = factory;
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
	 * @return the zunzi
	 */
	public java.lang.String getZunzi() {
		return zunzi;
	}

	/**
	 * @param zunzi the zunzi to set
	 */
	public void setZunzi(java.lang.String zunzi) {
		this.zunzi = zunzi;
	}

	/**
	 * @return the qty
	 */
	public java.math.BigDecimal getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(java.math.BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * @return the userid
	 */
	public java.lang.String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	/**
	 * @return the costprice
	 */
	public java.math.BigDecimal getCostprice() {
		return costprice;
	}

	/**
	 * @param costprice the costprice to set
	 */
	public void setCostprice(java.math.BigDecimal costprice) {
		this.costprice = costprice;
	}

	/**
	 * @return the price
	 */
	public java.math.BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the saledate
	 */
	public java.util.Date getSaledate() {
		return saledate;
	}

	/**
	 * @param saledate the saledate to set
	 */
	public void setSaledate(java.util.Date saledate) {
		this.saledate = saledate;
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
	 * @return the disabled
	 */
	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	public java.util.Date getBhrq() {
		return bhrq;
	}

	public void setBhrq(java.util.Date bhrq) {
		this.bhrq = bhrq;
	}

	public String getXxsx() {
		return xxsx;
	}

	public void setXxsx(String xxsx) {
		this.xxsx = xxsx;
	}

}
