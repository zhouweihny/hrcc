package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>SpaceCatalogVo<br>
 */
public class SpaceCatalogVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name; // name

	private String catalogid; // catalogid

	private Boolean checked; // checked

	private Boolean standard; // standard

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(java.lang.String catalogid) {
		this.catalogid = catalogid;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public java.lang.Boolean getStandard() {
		return standard;
	}

	public void setStandard(java.lang.Boolean standard) {
		this.standard = standard;
	}

}
