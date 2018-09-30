package com.modules.vo.apipur;

import java.util.Date;
import java.util.List;

public class OrderVo {

	private String orderno;//

	private Date orderdate; // 销售日期

	private String deliverycode;// 配送公司编码

	private String delivery; // 配送公司

	private String storecode;// 门店编码

	private String store;// 门店名称

	private List<OrderDetailVo> orderdetails;

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public String getDeliverycode() {
		return deliverycode;
	}

	public void setDeliverycode(String deliverycode) {
		this.deliverycode = deliverycode;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public List<OrderDetailVo> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<OrderDetailVo> orderdetails) {
		this.orderdetails = orderdetails;
	}

}
