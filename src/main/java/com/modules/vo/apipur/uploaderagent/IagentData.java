package com.modules.vo.apipur.uploaderagent;

import java.util.List;
import com.modules.vo.apipur.AgentVo;

public class IagentData {

	private String username;
	private String password;

	private List<AgentVo> agentvos;

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

	public List<AgentVo> getAgentvos() {
		return agentvos;
	}

	public void setAgentvos(List<AgentVo> agentvos) {
		this.agentvos = agentvos;
	}

}
