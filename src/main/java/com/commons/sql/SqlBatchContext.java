package com.commons.sql;

import java.util.List;

/**
 * @author Du.Jun
 *
 */
public class SqlBatchContext {

	/** 执行的sql */
	private StringBuilder sql;

	/** 参数，对应sql中的?号 */
	private List<Object[]> params;

	public SqlBatchContext() {

	}

	public SqlBatchContext(StringBuilder sql, List<Object[]> params) {
		this.sql = sql;
		this.params = params;
	}

	/**
	 * @return the sql
	 */
	public StringBuilder getSql() {
		return sql;
	}

	/**
	 * @param sql
	 *            the sql to set
	 */
	public void setSql(StringBuilder sql) {
		this.sql = sql;
	}

	/**
	 * @return the params
	 */
	public List<Object[]> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(List<Object[]> params) {
		this.params = params;
	}

	public String toSql() {
		return this.sql.toString();
	}

}
