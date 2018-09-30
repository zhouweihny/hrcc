package com.modules.vo.apipur.querybill;

import java.util.List;
import com.modules.vo.apipur.BillDetailStatusVo;

public class RqbillData {

	private Boolean result = true;

	private String message;

	private List<BillDetailStatusVo> detailstatus;

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

	public List<BillDetailStatusVo> getDetailstatus() {
		return detailstatus;
	}

	public void setDetailstatus(List<BillDetailStatusVo> detailstatus) {
		this.detailstatus = detailstatus;
	}

}
