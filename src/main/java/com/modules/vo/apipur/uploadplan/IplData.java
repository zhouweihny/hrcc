package com.modules.vo.apipur.uploadplan;

import java.util.List;

import com.modules.vo.apipur.PlanDetailVo;

public class IplData {

	private String username;
	private String password;

	private List<PlanDetailVo> plandetailvos;

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

	public List<PlanDetailVo> getPlandetailvos() {
		return plandetailvos;
	}

	public void setPlandetailvos(List<PlanDetailVo> plandetailvos) {
		this.plandetailvos = plandetailvos;
	}

	 
}
