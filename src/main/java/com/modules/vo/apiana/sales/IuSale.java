package com.modules.vo.apiana.sales;

import java.util.List;

import com.modules.pojo.FxSaleData;

public class IuSale {

	public String username;

	public String password;

	public String storename;

	public String storecode;

	public String orderno;

	public List<FxSaleData> details;

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

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public List<FxSaleData> getDetails() {
		return details;
	}

	public void setDetails(List<FxSaleData> details) {
		this.details = details;
	}

}
