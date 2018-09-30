package com.modules.vo;

public class ComnameVo {

	@com.commons.annotation.Column(value = "id")
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "fwflag")
	private Boolean fwflag;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public Boolean getFwflag() {
		return fwflag;
	}

	public void setFwflag(Boolean fwflag) {
		this.fwflag = fwflag;
	}

}
