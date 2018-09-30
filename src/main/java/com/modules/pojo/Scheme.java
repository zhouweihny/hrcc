package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>Scheme<br>
 */
@com.commons.annotation.Relation("t_scheme")
public class Scheme implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="name")
	private  java.lang.String   name; //name   
	
	
    @com.commons.annotation.Column(value="treeid")
	private  java.lang.String   treeid; //treeid   

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



}

