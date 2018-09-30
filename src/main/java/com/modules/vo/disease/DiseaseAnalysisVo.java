package com.modules.vo.disease;

import java.math.BigDecimal;
import java.util.List;

public class DiseaseAnalysisVo {

	@com.commons.annotation.Column(value = "ptreeid")
	private String ptreeid; // 父节点

	@com.commons.annotation.Column(value = "name")
	private String name; // 疾病

	@com.commons.annotation.Column(value = "id")
	private String id;

	@com.commons.annotation.Column(value = "frequency")
	private Integer frequency;// 频次

	private BigDecimal proportion;// 占比

	private List<DiseaseAnalysisVo> items;

	public String getPtreeid() {
		return ptreeid;
	}

	public void setPtreeid(String ptreeid) {
		this.ptreeid = ptreeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public List<DiseaseAnalysisVo> getItems() {
		return items;
	}

	public void setItems(List<DiseaseAnalysisVo> items) {
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

}
