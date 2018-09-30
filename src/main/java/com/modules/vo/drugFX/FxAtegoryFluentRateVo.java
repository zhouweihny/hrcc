package com.modules.vo.drugFX;

import java.math.BigDecimal;

public class FxAtegoryFluentRateVo {
	private static final long serialVersionUID = -3245478690489182643L;
	private  String   treename1; 	
	private  String   treename2;  	
	private  String   treename3;  	
	private  String   treename4; 	
	private BigDecimal totalAmt;
	private BigDecimal fluentRate;
	private BigDecimal fluentAmt;
	public String getTreename1() {
		return treename1;
	}
	public void setTreename1(String treename1) {
		this.treename1 = treename1;
	}
	public String getTreename2() {
		return treename2;
	}
	public void setTreename2(String treename2) {
		this.treename2 = treename2;
	}
	public String getTreename3() {
		return treename3;
	}
	public void setTreename3(String treename3) {
		this.treename3 = treename3;
	}
	public String getTreename4() {
		return treename4;
	}
	public void setTreename4(String treename4) {
		this.treename4 = treename4;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getFluentRate() {
		return fluentRate;
	}
	public void setFluentRate(BigDecimal fluentRate) {
		this.fluentRate = fluentRate;
	}
	public BigDecimal getFluentAmt() {
		return fluentAmt;
	}
	public void setFluentAmt(BigDecimal fluentAmt) {
		this.fluentAmt = fluentAmt;
	}
	
	
}
