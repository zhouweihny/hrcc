package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>SchemeComname<br>
 */
@com.commons.annotation.Relation("t_scheme_comname")
public class SchemeComname implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="schemeid")
	private  java.lang.String   schemeid; //schemeid   
	
	
    @com.commons.annotation.Column(value="comnameid")
	private  java.lang.String   comnameid; //comnameid   

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
	 * @return the schemeid
	 */
	public java.lang.String getSchemeid() {
		return schemeid;
	}

	/**
	 * @param schemeid the schemeid to set
	 */
	public void setSchemeid(java.lang.String schemeid) {
		this.schemeid = schemeid;
	}
	/**
	 * @return the comnameid
	 */
	public java.lang.String getComnameid() {
		return comnameid;
	}

	/**
	 * @param comnameid the comnameid to set
	 */
	public void setComnameid(java.lang.String comnameid) {
		this.comnameid = comnameid;
	}



}

