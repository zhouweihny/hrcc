package com.commons.sroure;

import java.util.List;

/**
 * @author Du.Jun
 *
 */
public class _Class {

	private String className; // 类名
	private String tableName; // 表名
	private Boolean containPrimaryKey;// 是否含有主键
	private String primaryKey;// 主键名称
	private String sql;// 查询语句
	private List<_Fields> columns;

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the columns
	 */
	public List<_Fields> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<_Fields> columns) {
		this.columns = columns;
	}

	/**
	 * @return the containPrimaryKey
	 */
	public Boolean getContainPrimaryKey() {
		return containPrimaryKey;
	}

	/**
	 * @param containPrimaryKey
	 *            the containPrimaryKey to set
	 */
	public void setContainPrimaryKey(Boolean containPrimaryKey) {
		this.containPrimaryKey = containPrimaryKey;
	}

	/**
	 * @return the primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey
	 *            the primaryKey to set
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql
	 *            the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

}
