package com.modules.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Du.Jun <b>功能：</b>Drug<br>
 */
public class DrugVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "catalogid")
	private java.lang.String catalogid; // catalogid

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

	@com.commons.annotation.Column(value = "comnameid")
	private java.lang.String comnameid; // comnameid

	@com.commons.annotation.Column(value = "compared")
	private Integer compared;

	@com.commons.annotation.Column(value = "compareid")
	private String compareid;// 对照品种id

	@com.commons.annotation.Column(value = "comparecode")
	private java.lang.String comparecode; // code

	@com.commons.annotation.Column(value = "comparecommon")
	private java.lang.String comparecommon; // common

	@com.commons.annotation.Column(value = "comparename")
	private java.lang.String comparename; // name

	@com.commons.annotation.Column(value = "comparespecifications")
	private java.lang.String comparespecifications; // specifications

	@com.commons.annotation.Column(value = "compareunit")
	private java.lang.String compareunit; // unit

	@com.commons.annotation.Column(value = "comparedosageform")
	private java.lang.String comparedosageform; // dosageform

	@com.commons.annotation.Column(value = "comparefactory")
	private java.lang.String comparefactory; // factory

	@com.commons.annotation.Column(value = "comparezunzi")
	private java.lang.String comparezunzi; // zunzi

	@com.commons.annotation.Column(value = "num")
	private Integer num; // num

	@com.commons.annotation.Column(value = "checked")
	private Boolean checked;

	@com.commons.annotation.Column(value = "costprice")
	private BigDecimal costprice;

	@com.commons.annotation.Column(value = "price")
	private BigDecimal price;

	@com.commons.annotation.Column(value = "supplier")
	private String supplier;

	@com.commons.annotation.Column(value = "supplierid")
	private String supplierid;

	private String treeid;

	private String remark;

	private String tree;

	private BigDecimal xse;

	private BigDecimal ml;

	private BigDecimal mlv;

	private BigDecimal xsmlv;

	private BigDecimal xssl;

	private Integer score;

	private BigDecimal pjmlv;

	@com.commons.annotation.Column(value = "gmpc")
	private Integer gmpc;

	private BigDecimal gmlv;
	
	@com.commons.annotation.Column(value = "xssx")
	private String xssx;
	
	@com.commons.annotation.Column(value = "zy")
	private Boolean zy;
	
	@com.commons.annotation.Column(value = "ll")
	private Boolean ll;
	

	@com.commons.annotation.Column(value = "ggpz")
	private Boolean ggpz;
	@com.commons.annotation.Column(value = "pppz")
	private Boolean pppz;

	private Boolean gmlpz;

	@com.commons.annotation.Column(value = "ybpz")
	private Boolean ybpz;

	private Boolean tjpz;

	private Boolean zypz;

	@com.commons.annotation.Column(value = "bbpz")
	private Boolean bbpz;

	@com.commons.annotation.Column(value = "ywpz")
	private Boolean ywpz;

	@com.commons.annotation.Column(value = "ztpz")
	private Boolean ztpz;

	private Boolean is80;

	private Boolean ttpz;

	private BigDecimal comxse;
	
	@com.commons.annotation.Column(value = "sdrugid")
	private String sdrugid;

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param catalogid
	 *            the catalogid to set
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
	 * @param code
	 *            the code to set
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
	 * @param common
	 *            the common to set
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
	 * @param name
	 *            the name to set
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
	 * @param specifications
	 *            the specifications to set
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
	 * @param unit
	 *            the unit to set
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
	 * @param dosageform
	 *            the dosageform to set
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

	public Integer getCompared() {
		return compared;
	}

	public void setCompared(Integer compared) {
		this.compared = compared;
	}

	public String getCompareid() {
		return compareid;
	}

	public void setCompareid(String compareid) {
		this.compareid = compareid;
	}

	public java.lang.String getComparecode() {
		return comparecode;
	}

	public void setComparecode(java.lang.String comparecode) {
		this.comparecode = comparecode;
	}

	public java.lang.String getComparecommon() {
		return comparecommon;
	}

	public void setComparecommon(java.lang.String comparecommon) {
		this.comparecommon = comparecommon;
	}

	public java.lang.String getComparename() {
		return comparename;
	}

	public void setComparename(java.lang.String comparename) {
		this.comparename = comparename;
	}

	public java.lang.String getComparespecifications() {
		return comparespecifications;
	}

	public void setComparespecifications(java.lang.String comparespecifications) {
		this.comparespecifications = comparespecifications;
	}

	public java.lang.String getCompareunit() {
		return compareunit;
	}

	public void setCompareunit(java.lang.String compareunit) {
		this.compareunit = compareunit;
	}

	public java.lang.String getComparedosageform() {
		return comparedosageform;
	}

	public void setComparedosageform(java.lang.String comparedosageform) {
		this.comparedosageform = comparedosageform;
	}

	public java.lang.String getComparefactory() {
		return comparefactory;
	}

	public void setComparefactory(java.lang.String comparefactory) {
		this.comparefactory = comparefactory;
	}

	public java.lang.String getComparezunzi() {
		return comparezunzi;
	}

	public void setComparezunzi(java.lang.String comparezunzi) {
		this.comparezunzi = comparezunzi;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}

	public BigDecimal getCostprice() {
		return costprice;
	}

	public void setCostprice(BigDecimal costprice) {
		this.costprice = costprice;
	}

	public BigDecimal getXse() {
		return xse;
	}

	public void setXse(BigDecimal xse) {
		this.xse = xse;
	}

	public BigDecimal getMl() {
		return ml;
	}

	public void setMl(BigDecimal ml) {
		this.ml = ml;
	}

	public BigDecimal getMlv() {
		return mlv;
	}

	public void setMlv(BigDecimal mlv) {
		this.mlv = mlv;
	}

	public BigDecimal getXssl() {
		return xssl;
	}

	public void setXssl(BigDecimal xssl) {
		this.xssl = xssl;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public BigDecimal getPjmlv() {
		return pjmlv;
	}

	public void setPjmlv(BigDecimal pjmlv) {
		this.pjmlv = pjmlv;
	}

	public Integer getGmpc() {
		return gmpc;
	}

	public void setGmpc(Integer gmpc) {
		this.gmpc = gmpc;
	}

	public BigDecimal getGmlv() {
		return gmlv;
	}

	public void setGmlv(BigDecimal gmlv) {
		this.gmlv = gmlv;
	}

	public Boolean getGgpz() {
		return ggpz;
	}

	public void setGgpz(Boolean ggpz) {
		this.ggpz = ggpz;
	}

	public Boolean getPppz() {
		return pppz;
	}

	public void setPppz(Boolean pppz) {
		this.pppz = pppz;
	}

	public Boolean getGmlpz() {
		return gmlpz;
	}

	public void setGmlpz(Boolean gmlpz) {
		this.gmlpz = gmlpz;
	}

	public Boolean getYbpz() {
		return ybpz;
	}

	public void setYbpz(Boolean ybpz) {
		this.ybpz = ybpz;
	}

	public Boolean getTjpz() {
		return tjpz;
	}

	public void setTjpz(Boolean tjpz) {
		this.tjpz = tjpz;
	}

	public Boolean getZypz() {
		return zypz;
	}

	public void setZypz(Boolean zypz) {
		this.zypz = zypz;
	}

	public Boolean getBbpz() {
		return bbpz;
	}

	public void setBbpz(Boolean bbpz) {
		this.bbpz = bbpz;
	}

	public Boolean getYwpz() {
		return ywpz;
	}

	public void setYwpz(Boolean ywpz) {
		this.ywpz = ywpz;
	}

	public BigDecimal getXsmlv() {
		return xsmlv;
	}

	public void setXsmlv(BigDecimal xsmlv) {
		this.xsmlv = xsmlv;
	}

	public Boolean getIs80() {
		return is80;
	}

	public void setIs80(Boolean is80) {
		this.is80 = is80;
	}

	public Boolean getZtpz() {
		return ztpz;
	}

	public void setZtpz(Boolean ztpz) {
		this.ztpz = ztpz;
	}

	public java.lang.String getComnameid() {
		return comnameid;
	}

	public void setComnameid(java.lang.String comnameid) {
		this.comnameid = comnameid;
	}

	public BigDecimal getComxse() {
		return comxse;
	}

	public void setComxse(BigDecimal comxse) {
		this.comxse = comxse;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public Boolean getTtpz() {
		return ttpz;
	}

	public void setTtpz(Boolean ttpz) {
		this.ttpz = ttpz;
	}

	public String getSdrugid() {
		return sdrugid;
	}

	public void setSdrugid(String sdrugid) {
		this.sdrugid = sdrugid;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
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
