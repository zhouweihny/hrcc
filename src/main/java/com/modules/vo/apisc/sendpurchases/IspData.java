package com.modules.vo.apisc.sendpurchases;

import java.util.List;

import com.modules.vo.apisc.PurchaseVo;


public class IspData {

	private String username;
	private String password;

	private List<PurchaseVo> purchases;

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

	public List<PurchaseVo> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseVo> purchases) {
		this.purchases = purchases;
	}

}
