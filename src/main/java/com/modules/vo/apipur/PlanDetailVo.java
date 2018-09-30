package com.modules.vo.apipur;

import java.math.BigDecimal;

public class PlanDetailVo {

	private String planid;

	private String erpusername;

	private String storecode;

	private String code; // code

	private Integer num; // num

	private BigDecimal price;

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getErpusername() {
		return erpusername;
	}

	public void setErpusername(String erpusername) {
		this.erpusername = erpusername;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}