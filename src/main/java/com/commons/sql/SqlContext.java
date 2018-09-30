package com.commons.sql;

/**
 * @author Du.Jun
 *
 */
public class SqlContext {

	/** 执行的sql */
	private String sql;

	/** 参数，对应sql中的?号 */
	private Object[] params;

	public SqlContext() {

	}

	public SqlContext(String sql, Object[] params) {
		this.sql = sql;
		this.params = params;
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

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}

}
