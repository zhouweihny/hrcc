package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>BillDetail<br>
 */
public class BillVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String supplierid; // supplierid

	private java.lang.String supplier;

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

}
