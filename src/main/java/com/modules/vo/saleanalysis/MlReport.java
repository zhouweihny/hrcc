package com.modules.vo.saleanalysis;

import java.math.BigDecimal;

public class MlReport {

	@com.commons.annotation.Column(value = "yf")
	private String yf;

	@com.commons.annotation.Column(value = "ml")
	private BigDecimal ml;

	private BigDecimal rjml;

	private BigDecimal ljml;

	private BigDecimal tb;

	private BigDecimal hb;

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public BigDecimal getMl() {
		return ml;
	}

	public void setMl(BigDecimal ml) {
		this.ml = ml;
	}

	public BigDecimal getRjml() {
		return rjml;
	}

	public void setRjml(BigDecimal rjml) {
		this.rjml = rjml;
	}

	public BigDecimal getLjml() {
		return ljml;
	}

	public void setLjml(BigDecimal ljml) {
		this.ljml = ljml;
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

}
