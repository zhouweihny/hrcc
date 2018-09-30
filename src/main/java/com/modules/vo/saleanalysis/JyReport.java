package com.modules.vo.saleanalysis;

import java.math.BigDecimal;

public class JyReport {

	@com.commons.annotation.Column(value = "yf")
	private String yf;

	@com.commons.annotation.Column(value = "jycs")
	private BigDecimal jycs;

	@com.commons.annotation.Column(value = "pjkdj")
	private BigDecimal pjkdj;

	private BigDecimal rjjycs;

	private BigDecimal tb;

	private BigDecimal hb;

	private BigDecimal ljjycs;

	public BigDecimal getPjkdj() {
		return pjkdj;
	}

	public void setPjkdj(BigDecimal pjkdj) {
		this.pjkdj = pjkdj;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public BigDecimal getJycs() {
		return jycs;
	}

	public void setJycs(BigDecimal jycs) {
		this.jycs = jycs;
	}

	public BigDecimal getRjjycs() {
		return rjjycs;
	}

	public void setRjjycs(BigDecimal rjjycs) {
		this.rjjycs = rjjycs;
	}

	public BigDecimal getTb() {
		return tb;
	}

	public void setTb(BigDecimal tb) {
		this.tb = tb;
	}

	public BigDecimal getHb() {
		return hb;
	}

	public void setHb(BigDecimal hb) {
		this.hb = hb;
	}

	public BigDecimal getLjjycs() {
		return ljjycs;
	}

	public void setLjjycs(BigDecimal ljjycs) {
		this.ljjycs = ljjycs;
	}

}
