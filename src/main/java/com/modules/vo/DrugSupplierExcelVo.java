package com.modules.vo;

import java.io.Serializable;

import com.commons.util.excel.ModelProp;

/**
 * 
 * @author Du.Jun <b>功能：</b>PlanDetail<br>
 */
public class DrugSupplierExcelVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ModelProp(name = "编码", colIndex = 0, nullable = false)
	private java.lang.String code; // code

	@ModelProp(name = "品名", colIndex = 1, nullable = true)
	private java.lang.String name; // name

	@ModelProp(name = "ERP供应商编码", colIndex = 2, nullable = false)
	private java.lang.String erpsucode; // erpsucode

	@ModelProp(name = "手机号", colIndex = 3, nullable = true)
	private java.lang.String mobileno; // unit

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

 
	public java.lang.String getErpsucode() {
		return erpsucode;
	}

	public void setErpsucode(java.lang.String erpsucode) {
		this.erpsucode = erpsucode;
	}

	public java.lang.String getMobileno() {
		return mobileno;
	}

	public void setMobileno(java.lang.String mobileno) {
		this.mobileno = mobileno;
	}

}
