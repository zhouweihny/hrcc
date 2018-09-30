package com.modules.vo.apipur.uploadbill;

import java.util.List;

import com.modules.vo.apipur.BillDetailVo;

public class IbData {

	private String username;
	private String password;

	private List<BillDetailVo> billdetailvos;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BillDetailVo> getBilldetailvos() {
		return billdetailvos;
	}

	public void setBilldetailvos(List<BillDetailVo> billdetailvos) {
		this.billdetailvos = billdetailvos;
	}

}
