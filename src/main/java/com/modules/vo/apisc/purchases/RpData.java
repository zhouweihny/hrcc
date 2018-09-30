package com.modules.vo.apisc.purchases;

import java.math.BigDecimal;
import java.util.List;

import com.modules.vo.apisc.PurchaseVo;

public class RpData {

	private Boolean result = true;

	private String message;

	private List<PurchaseVo> purchases;

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

	public List<PurchaseVo> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseVo> purchases) {
		this.purchases = purchases;
	}


	
}
