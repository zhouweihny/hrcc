package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>FxLhyydc<br>
 */
@com.commons.annotation.Relation("fx_lhyydc")
public class FxLhyydc implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="treeid")
	private  java.lang.String   treeid; //treeid   
	
	
    @com.commons.annotation.Column(value="title")
	private  java.lang.String   title; //title   
	
	
    @com.commons.annotation.Column(value="author")
	private  java.lang.String   author; //author   
	
	
    @com.commons.annotation.Column(value="description")
	private  java.lang.String   description; //description   
	
	
    @com.commons.annotation.Column(value="editor")
	private  java.lang.String   editor; //editor   
	
	
    @com.commons.annotation.Column(value="content")
	private  java.lang.String   content; //content   
	
	
    @com.commons.annotation.Column(value="cover")
	private  java.lang.String   cover; //cover   
	
	
    @com.commons.annotation.Column(value="clicks")
	private  java.lang.Integer   clicks; //clicks   
	
	
    @com.commons.annotation.Column(value="createuserid")
	private  java.lang.String   createuserid; //createuserid   
	
	
    @com.commons.annotation.Column(value="publishuserid")
	private  java.lang.String   publishuserid; //publishuserid   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="publishdate")
	private  java.util.Date   publishdate; //publishdate   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="topdate")
	private  java.util.Date   topdate; //topdate   
	
	
    @com.commons.annotation.Column(value="status")
	private  java.lang.Integer   status; //status   
	
	
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
	 * @return the title
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public java.lang.String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}
	/**
	 * @return the description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	/**
	 * @return the editor
	 */
	public java.lang.String getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(java.lang.String editor) {
		this.editor = editor;
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
	 * @return the cover
	 */
	public java.lang.String getCover() {
		return cover;
	}

	/**
	 * @param cover the cover to set
	 */
	public void setCover(java.lang.String cover) {
		this.cover = cover;
	}
	/**
	 * @return the clicks
	 */
	public java.lang.Integer getClicks() {
		return clicks;
	}

	/**
	 * @param clicks the clicks to set
	 */
	public void setClicks(java.lang.Integer clicks) {
		this.clicks = clicks;
	}
	/**
	 * @return the createuserid
	 */
	public java.lang.String getCreateuserid() {
		return createuserid;
	}

	/**
	 * @param createuserid the createuserid to set
	 */
	public void setCreateuserid(java.lang.String createuserid) {
		this.createuserid = createuserid;
	}
	/**
	 * @return the publishuserid
	 */
	public java.lang.String getPublishuserid() {
		return publishuserid;
	}

	/**
	 * @param publishuserid the publishuserid to set
	 */
	public void setPublishuserid(java.lang.String publishuserid) {
		this.publishuserid = publishuserid;
	}
	/**
	 * @return the publishdate
	 */
	public java.util.Date getPublishdate() {
		return publishdate;
	}

	/**
	 * @param publishdate the publishdate to set
	 */
	public void setPublishdate(java.util.Date publishdate) {
		this.publishdate = publishdate;
	}
	/**
	 * @return the topdate
	 */
	public java.util.Date getTopdate() {
		return topdate;
	}

	/**
	 * @param topdate the topdate to set
	 */
	public void setTopdate(java.util.Date topdate) {
		this.topdate = topdate;
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

