package com.modules.vo.apisc.purchasers;

import java.util.List;

public class RprData {

	private Boolean result = true;

	private String message;

	private List<Purchaser> purchasers;

	public Boolean getResult() {
		return result;
	}

	public List<Purchaser> getPurchasers() {
		return purchasers;
	}

	public void setPurchasers(List<Purchaser> purchasers) {
		this.purchasers = purchasers;
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

	public class Purchaser {

		public String name;
		public String puchaserid;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPuchaserid() {
			return puchaserid;
		}

		public void setPuchaserid(String puchaserid) {
			this.puchaserid = puchaserid;
		}

	}

}
