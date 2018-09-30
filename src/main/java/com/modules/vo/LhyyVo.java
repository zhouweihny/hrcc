package com.modules.vo;

import java.math.BigDecimal;

import com.commons.annotation.Column;

public class LhyyVo {

	private String lv1;

	private String lv2;

	@Column(value = "treeid")
	private String treeid;

	@Column(value = "zbs")
	private Integer zbs;

	@Column(value = "bs")
	private Integer bs;

	@Column(value = "xse")
	private BigDecimal xse;

	@Column(value = "kdj")
	private BigDecimal kdj;

	@Column(value = "avdj")
	private BigDecimal avdj;

	public String getLv1() {
		return lv1;
	}

	public void setLv1(String lv1) {
		this.lv1 = lv1;
	}

	public String getLv2() {
		return lv2;
	}

	public void setLv2(String lv2) {
		this.lv2 = lv2;
	}

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}

	public Integer getZbs() {
		return zbs;
	}

	public void setZbs(Integer zbs) {
		this.zbs = zbs;
	}

	public Integer getBs() {
		return bs;
	}

	public void setBs(Integer bs) {
		this.bs = bs;
	}

	public BigDecimal getXse() {
		return xse;
	}

	public void setXse(BigDecimal xse) {
		this.xse = xse;
	}

	public BigDecimal getKdj() {
		return kdj;
	}

	public void setKdj(BigDecimal kdj) {
		this.kdj = kdj;
	}

	public BigDecimal getAvdj() {
		return avdj;
	}

	public void setAvdj(BigDecimal avdj) {
		this.avdj = avdj;
	}

}
