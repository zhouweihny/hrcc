package com.modules.vo.apisc.export;

import java.util.List;

import com.modules.pojo.DrugDrug;
import com.modules.pojo.DrugDrugDel;

public class ReData {

	private Boolean result = true;

	private String message;

	private List<DrugDrug> drugdrugs;

	private List<DrugDrug> drugdrugdels;

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

	public List<DrugDrug> getDrugdrugs() {
		return drugdrugs;
	}

	public void setDrugdrugs(List<DrugDrug> drugdrugs) {
		this.drugdrugs = drugdrugs;
	}

	public List<DrugDrug> getDrugdrugdels() {
		return drugdrugdels;
	}

	public void setDrugdrugdels(List<DrugDrug> drugdrugdels) {
		this.drugdrugdels = drugdrugdels;
	}

}
