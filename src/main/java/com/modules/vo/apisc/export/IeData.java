package com.modules.vo.apisc.export;

import java.util.Date;

public class IeData {

	private String username;
	private String password;
	private String catalogid;

	private Date lastExportTime;

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

	public String getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(String catalogid) {
		this.catalogid = catalogid;
	}

	public Date getLastExportTime() {
		return lastExportTime;
	}

	public void setLastExportTime(Date lastExportTime) {
		this.lastExportTime = lastExportTime;
	}

}
