package com.modules.vo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>DmStatusVo<br>
 */

public class DmStatusVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "catalogid")
	private java.lang.String catalogid; // catalogid
	
	@com.commons.annotation.Column(value = "name")
	private java.lang.String name; // name

	@com.commons.annotation.Column(value = "status")
	private java.lang.Integer status; // status

	@com.commons.annotation.Column(value = "checked")
	private java.lang.Boolean checked; // checked

	@com.commons.annotation.Column(value = "num")
	private java.lang.Long num; // num

	/**
	 * @return the catalogid
	 */
	public java.lang.String getCatalogid() {
		return catalogid;
	}

	/**
	 * @param catalogid
	 *            the catalogid to set
	 */
	public void setCatalogid(java.lang.String catalogid) {
		this.catalogid = catalogid;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	/**
	 * @return the checked
	 */
	public java.lang.Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(java.lang.Boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the num
	 */
	public java.lang.Long getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(java.lang.Long num) {
		this.num = num;
	}

}
