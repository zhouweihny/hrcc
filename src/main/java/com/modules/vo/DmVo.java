package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>Drug<br>
 */
public class DmVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String catalogid; // catalogid

	private java.lang.String name; // name

	private Integer num;

	private Integer dm;

	private Integer ndm;

	private Integer ignore;

	private Integer checked;

	public java.lang.String getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(java.lang.String catalogid) {
		this.catalogid = catalogid;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getDm() {
		return dm;
	}

	public void setDm(Integer dm) {
		this.dm = dm;
	}

	public Integer getNdm() {
		return ndm;
	}

	public void setNdm(Integer ndm) {
		this.ndm = ndm;
	}

	public Integer getIgnore() {
		return ignore;
	}

	public void setIgnore(Integer ignore) {
		this.ignore = ignore;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

}
