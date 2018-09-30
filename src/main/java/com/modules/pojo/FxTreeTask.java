package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>FxTreeTask<br>
 */
@com.commons.annotation.Relation("fx_tree_task")
public class FxTreeTask implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="code")
	private  java.lang.String   code; //code   
	
	
    @com.commons.annotation.Column(value="name")
	private  java.lang.String   name; //name   
	
	
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
	
	
    @com.commons.annotation.Column(value="storeid")
	private  java.lang.String   storeid; //storeid   
	
	
    @com.commons.annotation.Column(value="treeid")
	private  java.lang.String   treeid; //treeid   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="begintime")
	private  java.util.Date   begintime; //begintime   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="endtime")
	private  java.util.Date   endtime; //endtime   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="writetime")
	private  java.util.Date   writetime; //writetime   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="readtime")
	private  java.util.Date   readtime; //readtime   
	
	
    @com.commons.annotation.Column(value="userid")
	private  java.lang.String   userid; //userid   
	
	
    @com.commons.annotation.Column(value="trainerid")
	private  java.lang.String   trainerid; //trainerid   
	
	
    @com.commons.annotation.Column(value="clerkid")
	private  java.lang.String   clerkid; //clerkid   
	
	
    @com.commons.annotation.Column(value="jobid")
	private  java.lang.String   jobid; //jobid   
	
	
    @com.commons.annotation.Column(value="remarks")
	private  java.lang.String   remarks; //remarks   

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
	 * @return the code
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
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
	/**
	 * @return the storeid
	 */
	public java.lang.String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(java.lang.String storeid) {
		this.storeid = storeid;
	}
	/**
	 * @return the treeid
	 */
	public java.lang.String getTreeid() {
		return treeid;
	}

	/**
	 * @param treeid the treeid to set
	 */
	public void setTreeid(java.lang.String treeid) {
		this.treeid = treeid;
	}
	/**
	 * @return the begintime
	 */
	public java.util.Date getBegintime() {
		return begintime;
	}

	/**
	 * @param begintime the begintime to set
	 */
	public void setBegintime(java.util.Date begintime) {
		this.begintime = begintime;
	}
	/**
	 * @return the endtime
	 */
	public java.util.Date getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	/**
	 * @return the writetime
	 */
	public java.util.Date getWritetime() {
		return writetime;
	}

	/**
	 * @param writetime the writetime to set
	 */
	public void setWritetime(java.util.Date writetime) {
		this.writetime = writetime;
	}
	/**
	 * @return the readtime
	 */
	public java.util.Date getReadtime() {
		return readtime;
	}

	/**
	 * @param readtime the readtime to set
	 */
	public void setReadtime(java.util.Date readtime) {
		this.readtime = readtime;
	}
	/**
	 * @return the userid
	 */
	public java.lang.String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}
	/**
	 * @return the trainerid
	 */
	public java.lang.String getTrainerid() {
		return trainerid;
	}

	/**
	 * @param trainerid the trainerid to set
	 */
	public void setTrainerid(java.lang.String trainerid) {
		this.trainerid = trainerid;
	}
	/**
	 * @return the clerkid
	 */
	public java.lang.String getClerkid() {
		return clerkid;
	}

	/**
	 * @param clerkid the clerkid to set
	 */
	public void setClerkid(java.lang.String clerkid) {
		this.clerkid = clerkid;
	}
	/**
	 * @return the jobid
	 */
	public java.lang.String getJobid() {
		return jobid;
	}

	/**
	 * @param jobid the jobid to set
	 */
	public void setJobid(java.lang.String jobid) {
		this.jobid = jobid;
	}
	/**
	 * @return the remarks
	 */
	public java.lang.String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(java.lang.String remarks) {
		this.remarks = remarks;
	}



}

