package com.modules.vo.apipur;

import java.math.BigDecimal;
import java.util.Date;

public class BillDetailVo {

	private java.lang.String billid;

	private String erpusername;

	private String erpsucode;

	private String storecode;

	private String code; // code

	private Integer num; // num

	private String mobile;

	private String contractno;

	private String contractnum;

	private Date contracttime;

	private BigDecimal price;

	public java.lang.String getBillid() {
		return billid;
	}

	public void setBillid(java.lang.String billid) {
		this.billid = billid;
	}

	public String getErpusername() {
		return erpusername;
	}

	public void setErpusername(String erpusername) {
		this.erpusername = erpusername;
	}

	public String getErpsucode() {
		return erpsucode;
	}

	public void setErpsucode(String erpsucode) {
		this.erpsucode = erpsucode;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getContractnum() {
		return contractnum;
	}

	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}

	public Date getContracttime() {
		return contracttime;
	}

	public void setContracttime(Date contracttime) {
		this.contracttime = contracttime;
	}

}