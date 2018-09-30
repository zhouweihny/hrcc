package com.modules.vo.apipur.uploaderpuser;

import java.util.List;
import com.modules.vo.apipur.ErpUserVo;

public class IuserData {

	private String username;
	private String password;

	private List<ErpUserVo> erpusers;

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

	public List<ErpUserVo> getErpusers() {
		return erpusers;
	}

	public void setErpusers(List<ErpUserVo> erpusers) {
		this.erpusers = erpusers;
	}

}
