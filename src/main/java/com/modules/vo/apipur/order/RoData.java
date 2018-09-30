package com.modules.vo.apipur.order;

import java.util.List;

import com.modules.vo.apipur.OrderVo;

public class RoData {

	private Boolean result = true;

	private String message;

	private List<OrderVo> orders;

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

	public List<OrderVo> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderVo> orders) {
		this.orders = orders;
	}

}
