package com.modules.vo.saleanalysis;

import java.math.BigDecimal;

public class SaleReport {

	
	@com.commons.annotation.Column(value = "yf")
	private String yf;

	@com.commons.annotation.Column(value = "xse")
	private BigDecimal xse;

	private BigDecimal rjxse;

	private BigDecimal tb;

	private BigDecimal hb;

	private BigDecimal ljxse;

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public BigDecimal getXse() {
		return xse;
	}

	public void setXse(BigDecimal xse) {
		this.xse = xse;
	}

	public BigDecimal getRjxse() {
		return rjxse;
	}

	public void setRjxse(BigDecimal rjxse) {
		this.rjxse = rjxse;
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

	public BigDecimal getLjxse() {
		return ljxse;
	}

	public void setLjxse(BigDecimal ljxse) {
		this.ljxse = ljxse;
	}

}
