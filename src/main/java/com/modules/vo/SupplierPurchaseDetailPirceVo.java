package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>PurchaseDetail<br>
 */
public class SupplierPurchaseDetailPirceVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "supplierid")
	private java.lang.String supplierid; // supplierid

	@com.commons.annotation.Column(value = "supplier")
	private java.lang.String supplier; // supplier

	@com.commons.annotation.Column(value = "purchasedetailid")
	private java.lang.String purchasedetailid; // purchasedetailid

	@com.commons.annotation.Column(value = "price")
	private java.math.BigDecimal price; // price

	public java.lang.String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(java.lang.String supplierid) {
		this.supplierid = supplierid;
	}

	public java.lang.String getSupplier() {
		return supplier;
	}

	public void setSupplier(java.lang.String supplier) {
		this.supplier = supplier;
	}

	public java.lang.String getPurchasedetailid() {
		return purchasedetailid;
	}

	public void setPurchasedetailid(java.lang.String purchasedetailid) {
		this.purchasedetailid = purchasedetailid;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	
	

}
