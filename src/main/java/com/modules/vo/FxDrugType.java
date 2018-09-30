package com.modules.vo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>FxDrugType<br>
 */

public class FxDrugType implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="code")
	private  java.lang.String   code; //code   
	
	
    @com.commons.annotation.Column(value="id")
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="name")
	private  java.lang.String   name; //name   
	
	
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
	
	
    @com.commons.annotation.Column(value="comname")
	private  java.lang.String   comname; //name   
	
	
    @com.commons.annotation.Column(value="comnameid")
	private  java.lang.String   comnameid; //id   
	
	
    @com.commons.annotation.Column(value="treeid")
	private  java.lang.String   treeid; //id   
	
	
    @com.commons.annotation.Column(value="treename")
	private  java.lang.String   treename; //name   
	
	
    @com.commons.annotation.Column(value="path")
	private  java.lang.String   path; //path   
	
	
    @com.commons.annotation.Column(value="treename1")
	private  java.lang.String   treename1; //treename1   
	
	
    @com.commons.annotation.Column(value="treename2")
	private  java.lang.String   treename2; //treename2   
	
	
    @com.commons.annotation.Column(value="treename3")
	private  java.lang.String   treename3; //treename3   
	
	
    @com.commons.annotation.Column(value="treename4")
	private  java.lang.String   treename4; //treename4   
	
	
    @com.commons.annotation.Column(value="qty")
	private  java.math.BigDecimal   qty; //qty   
	
	
    @com.commons.annotation.Column(value="price")
	private  java.math.BigDecimal   price; //price   
	
	
    @com.commons.annotation.Column(value="costprice")
	private  java.math.BigDecimal   costprice; //costprice   
    
    
    @com.commons.annotation.Column(value="flevel")
   	private Long  flevel; //costprice   

    
    
	public Long getFlevel() {
		return flevel;
	}

	public void setFlevel(Long flevel) {
		this.flevel = flevel;
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
	
	
	/**
	 * @return the path
	 */
	public java.lang.String getPath() {
		return path;
	}

	public java.lang.String getComname() {
		return comname;
	}

	public void setComname(java.lang.String comname) {
		this.comname = comname;
	}

	public java.lang.String getComnameid() {
		return comnameid;
	}

	public void setComnameid(java.lang.String comnameid) {
		this.comnameid = comnameid;
	}

	public java.lang.String getTreeid() {
		return treeid;
	}

	public void setTreeid(java.lang.String treeid) {
		this.treeid = treeid;
	}

	public java.lang.String getTreename() {
		return treename;
	}

	public void setTreename(java.lang.String treename) {
		this.treename = treename;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(java.lang.String path) {
		this.path = path;
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
	 * @return the treename4
	 */
	public java.lang.String getTreename4() {
		return treename4;
	}

	/**
	 * @param treename4 the treename4 to set
	 */
	public void setTreename4(java.lang.String treename4) {
		this.treename4 = treename4;
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



}

