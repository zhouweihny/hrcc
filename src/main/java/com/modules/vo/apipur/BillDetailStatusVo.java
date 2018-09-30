package com.modules.vo.apipur;

import java.util.Date;

public class BillDetailStatusVo {

	@com.commons.annotation.Column(value = "erpid")
	private String erpid;

	@com.commons.annotation.Column(value = "code")
	private String code;

	@com.commons.annotation.Column(value = "status")
	private String status;

	@com.commons.annotation.Column(value = "remark")
	private String remark;

	@com.commons.annotation.Column(value = "billstatus")
	private String billstatus;

	@com.commons.annotation.Column(value = "arrivaltime")
	private Date arrivaltime;

	@com.commons.annotation.Column(value = "updatetime")
	private Date updatetime;

	public String getErpid() {
		return erpid;
	}

	public void setErpid(String erpid) {
		this.erpid = erpid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public Date getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(Date arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}