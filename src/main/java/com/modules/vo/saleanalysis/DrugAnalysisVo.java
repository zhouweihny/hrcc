package com.modules.vo.saleanalysis;

import java.math.BigDecimal;

public class DrugAnalysisVo {

	@com.commons.annotation.Column(value = "drugid")
	private String drugid;

	@com.commons.annotation.Column(value = "treeid")
	private String treeid;

	@com.commons.annotation.Column(value = "name")
	private String name;

	@com.commons.annotation.Column(value = "code")
	private String code;

	@com.commons.annotation.Column(value = "orderno")
	private String orderno;

	@com.commons.annotation.Column(value = "factory")
	private String factory;

	@com.commons.annotation.Column(value = "specifications")
	private String specifications;

	@com.commons.annotation.Column(value = "xse")
	private BigDecimal xse;

	@com.commons.annotation.Column(value = "xszb")
	private BigDecimal xszb;

	@com.commons.annotation.Column(value = "pjsj")
	private BigDecimal pjsj;

	@com.commons.annotation.Column(value = "xssl")
	private BigDecimal xssl;

	@com.commons.annotation.Column(value = "price")
	private BigDecimal price;

	@com.commons.annotation.Column(value = "ml")
	private BigDecimal ml;

	@com.commons.annotation.Column(value = "mlv")
	private BigDecimal mlv;

	@com.commons.annotation.Column(value = "pc")
	private Integer pc;

	private Integer ordernum;

	@com.commons.annotation.Column(value = "orderml")
	private BigDecimal orderml;

	@com.commons.annotation.Column(value = "orderxse")
	private BigDecimal orderxse;

	@com.commons.annotation.Column(value = "ordermlv")
	private BigDecimal ordermlv;

	@com.commons.annotation.Column(value = "ywflag")
	private Boolean ywflag;

	@com.commons.annotation.Column(value = "ybflag")
	private Boolean ybflag;

	@com.commons.annotation.Column(value = "bbflag")
	private Boolean bbflag;

	@com.commons.annotation.Column(value = "ztflag")
	private Boolean ztflag;

	private Integer num;

	private Boolean is80;

	public String getDrugid() {
		return drugid;
	}

	public void setDrugid(String drugid) {
		this.drugid = drugid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public java.lang.String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(java.lang.String specifications) {
		this.specifications = specifications;
	}

	public BigDecimal getXse() {
		return xse;
	}

	public void setXse(BigDecimal xse) {
		this.xse = xse;
	}

	public BigDecimal getXszb() {
		return xszb;
	}

	public void setXszb(BigDecimal xszb) {
		this.xszb = xszb;
	}

	public BigDecimal getPjsj() {
		return pjsj;
	}

	public void setPjsj(BigDecimal pjsj) {
		this.pjsj = pjsj;
	}

	public BigDecimal getXssl() {
		return xssl;
	}

	public void setXssl(BigDecimal xssl) {
		this.xssl = xssl;
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

	public Integer getPc() {
		return pc;
	}

	public void setPc(Integer pc) {
		this.pc = pc;
	}

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public BigDecimal getOrderml() {
		return orderml;
	}

	public void setOrderml(BigDecimal orderml) {
		this.orderml = orderml;
	}

	public BigDecimal getOrdermlv() {
		return ordermlv;
	}

	public void setOrdermlv(BigDecimal ordermlv) {
		this.ordermlv = ordermlv;
	}

	public BigDecimal getOrderxse() {
		return orderxse;
	}

	public void setOrderxse(BigDecimal orderxse) {
		this.orderxse = orderxse;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
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

	public Boolean getIs80() {
		return is80;
	}

	public void setIs80(Boolean is80) {
		this.is80 = is80;
	}

	public Boolean getZtflag() {
		return ztflag;
	}

	public void setZtflag(Boolean ztflag) {
		this.ztflag = ztflag;
	}

}
