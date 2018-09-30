package com.modules.vo.saleanalysis;

import java.math.BigDecimal;
import java.util.List;

public class TreeAnalysisVo {

	@com.commons.annotation.Column(value = "treeid")
	private String treeid;

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

	@com.commons.annotation.Column(value = "minprice")
	private BigDecimal minprice;
	@com.commons.annotation.Column(value = "maxprice")
	private BigDecimal maxprice;

	private List<TreeAnalysisVo> items;

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
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

	public List<TreeAnalysisVo> getItems() {
		return items;
	}

	public void setItems(List<TreeAnalysisVo> items) {
		this.items = items;
	}

	public BigDecimal getMinprice() {
		return minprice;
	}

	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}

	public BigDecimal getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}

}
