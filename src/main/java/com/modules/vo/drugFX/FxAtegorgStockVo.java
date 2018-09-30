package com.modules.vo.drugFX;

import java.math.BigDecimal;

public class FxAtegorgStockVo {

	private  String   treename1; 	
	private  String   treename2;  	
	private  String   treename3;  	
	private  String   treename4; 	
	private BigDecimal totalAmt;
	private BigDecimal stockQty;
	private Integer stocknum;
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
	public BigDecimal getStockQty() {
		return stockQty;
	}
	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}
	public Integer getStocknum() {
		return stocknum;
	}
	public void setStocknum(Integer stocknum) {
		this.stocknum = stocknum;
	}
	
	
}
