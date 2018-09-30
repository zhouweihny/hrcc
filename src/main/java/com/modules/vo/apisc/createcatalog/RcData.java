package com.modules.vo.apisc.createcatalog;

import com.modules.pojo.Catalog;

public class RcData {

	private Boolean result = true;

	private String message;

	private Catalog catalog;

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

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}
