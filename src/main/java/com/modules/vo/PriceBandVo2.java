package com.modules.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Du.Jun <b>功能：</b>Drug<br>
 */
public class PriceBandVo2 implements Serializable {

	public PriceBandVo2() {
	}

	public PriceBandVo2(String jgd, BigDecimal xszb, BigDecimal dpzb, BigDecimal mlzb, Integer pzs) {
		this.xszb = xszb;
		this.dpzb = dpzb;
		this.jgd = jgd;
		this.mlzb = mlzb;
		this.pzs = pzs;
	}

	private static final long serialVersionUID = 1L;

	private String jgd;

	private BigDecimal xszb;

	private BigDecimal dpzb;

	private BigDecimal mlzb;

	private Integer pzs;

	public String getJgd() {
		return jgd;
	}

	public void setJgd(String jgd) {
		this.jgd = jgd;
	}

	public BigDecimal getXszb() {
		return xszb;
	}

	public void setXszb(BigDecimal xszb) {
		this.xszb = xszb;
	}

	public BigDecimal getDpzb() {
		return dpzb;
	}

	public void setDpzb(BigDecimal dpzb) {
		this.dpzb = dpzb;
	}

	public BigDecimal getMlzb() {
		return mlzb;
	}

	public void setMlzb(BigDecimal mlzb) {
		this.mlzb = mlzb;
	}

	public Integer getPzs() {
		return pzs;
	}

	public void setPzs(Integer pzs) {
		this.pzs = pzs;
	}

}
