package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxTreeMetaDatas<br>
 */
@com.commons.annotation.Relation("fx_tree_meta_datas")
public class FxTreeMetaDatas implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "treemetaid")
	private java.lang.String treemetaid; // treemetaid

	@com.commons.annotation.Column(value = "metadataid")
	private java.lang.String metadataid; // metadataid

	@com.commons.annotation.Column(value = "val")
	private java.lang.String val; // val

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	@com.commons.annotation.Column(value = "operatorid")
	private java.lang.String operatorid; // operatorid

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "createtime")
	private java.util.Date createtime; // createtime

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "updatetime")
	private java.util.Date updatetime; // updatetime

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * @return the treemetaid
	 */
	public java.lang.String getTreemetaid() {
		return treemetaid;
	}

	/**
	 * @param treemetaid
	 *            the treemetaid to set
	 */
	public void setTreemetaid(java.lang.String treemetaid) {
		this.treemetaid = treemetaid;
	}

	/**
	 * @return the metadataid
	 */
	public java.lang.String getMetadataid() {
		return metadataid;
	}

	/**
	 * @param metadataid
	 *            the metadataid to set
	 */
	public void setMetadataid(java.lang.String metadataid) {
		this.metadataid = metadataid;
	}

	/**
	 * @return the val
	 */
	public java.lang.String getVal() {
		return val;
	}

	/**
	 * @param val
	 *            the val to set
	 */
	public void setVal(java.lang.String val) {
		this.val = val;
	}

	/**
	 * @return the remark
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * @return the disabled
	 */
	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the operatorid
	 */
	public java.lang.String getOperatorid() {
		return operatorid;
	}

	/**
	 * @param operatorid
	 *            the operatorid to set
	 */
	public void setOperatorid(java.lang.String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * @return the createtime
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updatetime
	 */
	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime
	 *            the updatetime to set
	 */
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

}
