package com.modules.vo.apipur.uploadsupplier;

import java.util.List;

import com.modules.vo.apipur.SupplierVo;

public class IsData {

	private String username;
	private String password;

	private List<SupplierVo> suppliervos;

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

	public List<SupplierVo> getSuppliervos() {
		return suppliervos;
	}

	public void setSuppliervos(List<SupplierVo> suppliervos) {
		this.suppliervos = suppliervos;
	}

	 

}
