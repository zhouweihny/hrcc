package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>DrugPromotion<br>
 */
@com.commons.annotation.Relation("t_drug_promotion")
public class DrugPromotion implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="drugid")
	private  java.lang.String   drugid; //drugid   
	
	
    @com.commons.annotation.Column(value="supplierid")
	private  java.lang.String   supplierid; //supplierid   
	
	
    @com.commons.annotation.Column(value="content")
	private  java.lang.String   content; //content   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="startdate")
	private  java.util.Date   startdate; //startdate   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="enddate")
	private  java.util.Date   enddate; //enddate   
	
	
    @com.commons.annotation.Column(value="remark")
	private  java.lang.String   remark; //remark   
	
	
    @com.commons.annotation.Column(value="status")
	private  java.lang.Integer   status; //status   
	
	
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
	 * @return the supplierid
	 */
	public java.lang.String getSupplierid() {
		return supplierid;
	}

	/**
	 * @param supplierid the supplierid to set
	 */
	public void setSupplierid(java.lang.String supplierid) {
		this.supplierid = supplierid;
	}
	/**
	 * @return the content
	 */
	public java.lang.String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	/**
	 * @return the startdate
	 */
	public java.util.Date getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(java.util.Date startdate) {
		this.startdate = startdate;
	}
	/**
	 * @return the enddate
	 */
	public java.util.Date getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(java.util.Date enddate) {
		this.enddate = enddate;
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

