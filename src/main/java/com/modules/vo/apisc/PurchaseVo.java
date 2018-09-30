package com.modules.vo.apisc;

import java.util.List;

public class PurchaseVo {

	public String id;

	public String name;

	public String purchaserid;

	public String remark;

	public List<PurchaseDetailVo> purchaseDetails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurchaserid() {
		return purchaserid;
	}

	public void setPurchaserid(String purchaserid) {
		this.purchaserid = purchaserid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PurchaseDetailVo> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetailVo> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

}