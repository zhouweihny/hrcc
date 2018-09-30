package com.modules.vo.apisc.bill;

import java.util.List;

import com.modules.vo.apisc.BillDetailVo;

public class RbData {

	private Boolean result = true;

	private String message;

	public List<BillDetailVo> billDetails;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BillDetailVo> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetailVo> billDetails) {
		this.billDetails = billDetails;
	}

}
