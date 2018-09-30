package com.modules.vo.apipur.uploadcatalog;

import java.util.List;

import com.modules.vo.apipur.DrugVo;

public class IuData {

	private String username;
	private String password;
	private List<DrugVo> drugvos;

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

	public List<DrugVo> getDrugvos() {
		return drugvos;
	}

	public void setDrugvos(List<DrugVo> drugvos) {
		this.drugvos = drugvos;
	}

}
