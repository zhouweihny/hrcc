package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>FxDrugReview<br>
 */
@com.commons.annotation.Relation("fx_drug_review")
public class FxDrugReview implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="drugid")
	private  java.lang.String   drugid; //drugid   
	
	
    @com.commons.annotation.Column(value="status")
	private  java.lang.Integer   status; //status   
	
	
    @com.commons.annotation.Column(value="reviewer")
	private  java.lang.String   reviewer; //reviewer   
	
	
    @com.commons.annotation.Column(value="remark")
	private  java.lang.String   remark; //remark   
	
	
    @com.commons.annotation.Column(value="disabled")
	private  java.lang.Boolean   disabled; //disabled   
	
	
    @com.commons.annotation.Column(value="operatorid")
	private  java.lang.String   operatorid; //operatorid   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="createtime")
	private  java.util.Date   createtime; //createtime   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="updatetime")
	private  java.util.Date   updatetime; //updatetime   

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}
	/**
	 * @return the drugid
	 */
	public java.lang.String getDrugid() {
		return drugid;
	}

	/**
	 * @param drugid the drugid to set
	 */
	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
	}
	/**
	 * @return the status
	 */
	public java.lang.Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	/**
	 * @return the reviewer
	 */
	public java.lang.String getReviewer() {
		return reviewer;
	}

	/**
	 * @param reviewer the reviewer to set
	 */
	public void setReviewer(java.lang.String reviewer) {
		this.reviewer = reviewer;
	}
	/**
	 * @return the remark
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
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
	 * @param disabled the disabled to set
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
	 * @param operatorid the operatorid to set
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
	 * @param createtime the createtime to set
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
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}



}

