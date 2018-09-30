package com.modules.vo.apipur.uploadpurchase;

import java.util.List;

import com.modules.vo.apipur.PurchaseDetailVo;

public class IpData {

	private String username;
	private String password;

	private List<PurchaseDetailVo> purchasedetailvos;

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

	public List<PurchaseDetailVo> getPurchasedetailvos() {
		return purchasedetailvos;
	}

	public void setPurchasedetailvos(List<PurchaseDetailVo> purchasedetailvos) {
		this.purchasedetailvos = purchasedetailvos;
	}

}
