package com.modules.vo;

import java.math.BigDecimal;

public class PriceBandVo {

	private BigDecimal xse;

	private BigDecimal ml;

	private Integer pzs;

	private String price;

	public BigDecimal getXse() {
		return xse;
	}

	public void setXse(BigDecimal xse) {
		this.xse = xse;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getPzs() {
		return pzs;
	}

	public void setPzs(Integer pzs) {
		this.pzs = pzs;
	}

	public BigDecimal getMl() {
		return ml;
	}

	public void setMl(BigDecimal ml) {
		this.ml = ml;
	}

}
