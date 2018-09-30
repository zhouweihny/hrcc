package com.modules.vo;

import java.io.Serializable;
import java.util.List;

public class TreeMetaVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8164782744418468157L;

	private java.lang.String id; // id

	private java.lang.String treeid; // treeid

	private java.lang.String metaid; // metaid

	private String code;

	private String metaname;

	private String storetypeid;

	private java.lang.Integer scope; // scope

	private java.lang.String remark; // remark

	private List<TreeMetaDataVo> treeMetaDatas;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getTreeid() {
		return treeid;
	}

	public void setTreeid(java.lang.String treeid) {
		this.treeid = treeid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public java.lang.String getMetaid() {
		return metaid;
	}

	public void setMetaid(java.lang.String metaid) {
		this.metaid = metaid;
	}

	public java.lang.Integer getScope() {
		return scope;
	}

	public void setScope(java.lang.Integer scope) {
		this.scope = scope;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public List<TreeMetaDataVo> getTreeMetaDatas() {
		return treeMetaDatas;
	}

	public void setTreeMetaDatas(List<TreeMetaDataVo> treeMetaDatas) {
		this.treeMetaDatas = treeMetaDatas;
	}

	public String getMetaname() {
		return metaname;
	}

	public void setMetaname(String metaname) {
		this.metaname = metaname;
	}

	public String getStoretypeid() {
		return storetypeid;
	}

	public void setStoretypeid(String storetypeid) {
		this.storetypeid = storetypeid;
	}

}
