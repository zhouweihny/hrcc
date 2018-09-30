package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>FxBudgettable<br>
 */
@com.commons.annotation.Relation("fx_budgettable")
public class FxBudgettable implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="storeid")
	private  java.lang.String   storeid; //storeid   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="createtime")
	private  java.util.Date   createtime; //createtime   
	
	
    @com.commons.annotation.Column(value="operatorid")
	private  java.lang.String   operatorid; //operatorid   
	
	
    @com.commons.annotation.Column(value="name")
	private  java.lang.String   name; //name   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="updatetime")
	private  java.util.Date   updatetime; //updatetime   
	
	
    @com.commons.annotation.Column(value="disabled")
	private  java.lang.Boolean   disabled; //disabled   
	
	
    @com.commons.annotation.Column(value="userid")
	private  java.lang.String   userid; //userid   
	
	
    @com.commons.annotation.Column(value="code")
	private  java.lang.String   code; //code   
	
	
    @com.commons.annotation.Column(value="specifications")
	private  java.lang.String   specifications; //specifications   
	
	
    @com.commons.annotation.Column(value="unit")
	private  java.lang.String   unit; //unit   
	
	
    @com.commons.annotation.Column(value="dosageform")
	private  java.lang.String   dosageform; //dosageform   
	
	
    @com.commons.annotation.Column(value="factory")
	private  java.lang.String   factory; //factory   
	
	
    @com.commons.annotation.Column(value="zunzi")
	private  java.lang.String   zunzi; //zunzi   
	
	
    @com.commons.annotation.Column(value="qty")
	private  java.math.BigDecimal   qty; //qty   
	
	
    @com.commons.annotation.Column(value="costprice")
	private  java.math.BigDecimal   costprice; //costprice   
	
	
    @com.commons.annotation.Column(value="price")
	private  java.math.BigDecimal   price; //price   
	
	
    @com.commons.annotation.Column(value="drugid")
	private  java.lang.String   drugid; //drugid   
	
	
    @com.commons.annotation.Column(value="comname")
	private  java.lang.String   comname; //comname   
	
	
    @com.commons.annotation.Column(value="fpath")
	private  java.lang.String   fpath; //fpath   
	
	
    @com.commons.annotation.Column(value="treename1")
	private  java.lang.String   treename1; //treename1   
	
	
    @com.commons.annotation.Column(value="treename2")
	private  java.lang.String   treename2; //treename2   
	
	
    @com.commons.annotation.Column(value="treename3")
	private  java.lang.String   treename3; //treename3   
	
	
    @com.commons.annotation.Column(value="avgprice")
	private  java.math.BigDecimal   avgprice; //avgprice   
	
	
    @com.commons.annotation.Column(value="impfileid")
	private  java.lang.String   impfileid; //impfileid   
	
	
    @com.commons.annotation.Column(value="amt")
	private  java.math.BigDecimal   amt; //amt   

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
	 * @return the comname
	 */
	public java.lang.String getComname() {
		return comname;
	}

	/**
	 * @param comname the comname to set
	 */
	public void setComname(java.lang.String comname) {
		this.comname = comname;
	}
	/**
	 * @return the fpath
	 */
	public java.lang.String getFpath() {
		return fpath;
	}

	/**
	 * @param fpath the fpath to set
	 */
	public void setFpath(java.lang.String fpath) {
		this.fpath = fpath;
	}
	/**
	 * @return the treename1
	 */
	public java.lang.String getTreename1() {
		return treename1;
	}

	/**
	 * @param treename1 the treename1 to set
	 */
	public void setTreename1(java.lang.String treename1) {
		this.treename1 = treename1;
	}
	/**
	 * @return the treename2
	 */
	public java.lang.String getTreename2() {
		return treename2;
	}

	/**
	 * @param treename2 the treename2 to set
	 */
	public void setTreename2(java.lang.String treename2) {
		this.treename2 = treename2;
	}
	/**
	 * @return the treename3
	 */
	public java.lang.String getTreename3() {
		return treename3;
	}

	/**
	 * @param treename3 the treename3 to set
	 */
	public void setTreename3(java.lang.String treename3) {
		this.treename3 = treename3;
	}
	/**
	 * @return the avgprice
	 */
	public java.math.BigDecimal getAvgprice() {
		return avgprice;
	}

	/**
	 * @param avgprice the avgprice to set
	 */
	public void setAvgprice(java.math.BigDecimal avgprice) {
		this.avgprice = avgprice;
	}
	/**
	 * @return the impfileid
	 */
	public java.lang.String getImpfileid() {
		return impfileid;
	}

	/**
	 * @param impfileid the impfileid to set
	 */
	public void setImpfileid(java.lang.String impfileid) {
		this.impfileid = impfileid;
	}
	/**
	 * @return the amt
	 */
	public java.math.BigDecimal getAmt() {
		return amt;
	}

	/**
	 * @param amt the amt to set
	 */
	public void setAmt(java.math.BigDecimal amt) {
		this.amt = amt;
	}



}

