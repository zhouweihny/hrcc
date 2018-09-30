package com.modules.vo.saleanalysis;

import java.math.BigDecimal;

public class ComAnalysisVo {

	@com.commons.annotation.Column(value = "comnameid")
	private String comnameid;

	@com.commons.annotation.Column(value = "name")
	private String name;

	@com.commons.annotation.Column(value = "pgs")
	private Integer pgs;

	@com.commons.annotation.Column(value = "xse")
	private BigDecimal xse;

	@com.commons.annotation.Column(value = "xszb")
	private BigDecimal xszb;

	@com.commons.annotation.Column(value = "pjsj")
	private BigDecimal pjsj;

	@com.commons.annotation.Column(value = "xssl")
	private BigDecimal xssl;

	@com.commons.annotation.Column(value = "ml")
	private BigDecimal ml;

	@com.commons.annotation.Column(value = "mlv")
	private BigDecimal mlv;

	public String getComnameid() {
		return comnameid;
	}

	public void setComnameid(String comnameid) {
		this.comnameid = comnameid;
	}

	public Integer getPgs() {
		return pgs;
	}

	public void setPgs(Integer pgs) {
		this.pgs = pgs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
