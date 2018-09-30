package com.commons.sroure;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author Du.Jun
 *
 */
public class CreateClass {
	private Connection conn;
	private String dialect;

	public CreateClass(Connection conn) throws SQLException {
		this.conn = conn;
		this.dialect = conn.getMetaData().getDatabaseProductName();
	}

	public _Class getClassData(String tableName) throws SQLException {
		_Class classData = new _Class();
		classData.setClassName(this.getClassName(tableName));
		classData.setTableName(tableName);
		List<_Fields> columnList = new ArrayList<_Fields>();
		classData.setColumns(columnList);

		PreparedStatement ps = conn.prepareStatement("select * from " + tableName);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData data = rs.getMetaData();
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet pkRSet = dbMeta.getPrimaryKeys(conn.getCatalog(), conn.getMetaData().getUserName().toUpperCase(), tableName.toUpperCase());
		String primaryKey = null;
		while (pkRSet.next()) {
			primaryKey = getField(pkRSet.getString(4));
			classData.setContainPrimaryKey(true);
			classData.setPrimaryKey(primaryKey);
		}
		for (int i = 1; i <= data.getColumnCount(); i++) {
			String columnName = data.getColumnName(i);// 获得指定列的列名
			String columnTypeName = data.getColumnTypeName(i); // 获得指定列的数据类型名
			String columnClassName = data.getColumnClassName(i); // 对应数据类型的类
			int columnDisplaySize = data.getColumnDisplaySize(i); // 在数据库中类型的最大字符个数
			int precision = data.getPrecision(i); // 某列类型的精确度(类型的长度)
			int scale = data.getScale(i); // 小数点后的位数
			int isNullable = data.isNullable(i); // 是否为空
			_Fields cd = new _Fields();
			if (this.getField(columnName).equals(primaryKey))
				cd.setIsPrimarykey(true);
			cd.setField(this.getField(columnName));
			if (columnClassName.contains("Time"))
				cd.setFieldType("java.util.Date");
			else
				cd.setFieldType(columnClassName);
			cd.setPrecision(precision);
			cd.setScale(scale);
			cd.setColumn(columnName);
			cd.setComment(columnName);
			cd.setColumnType(columnTypeName);
			cd.setIsNullable(isNullable);
			cd.setSize(columnDisplaySize);
			columnList.add(cd);
		}
		rs.close();
		ps.close();

		String sqlcomments = null;

		if ("MySql".equals(dialect)) {
			sqlcomments = "select column_name , column_comment from information_schema.columns where table_name =? and table_schema =  database()";
		} else if ("Oracle".equals(dialect)) {
			sqlcomments = "select column_name,comments from  all_col_comments  where table_name=?";
		}
		if (sqlcomments != null) {
			PreparedStatement psc = conn.prepareStatement("select column_name,comments from  all_col_comments  where table_name=?");
			psc.setString(1, tableName.toUpperCase());
			ResultSet rsc = psc.executeQuery();
			while (rsc.next()) {
				String columnName = rsc.getString(1);
				String comment = rsc.getString(2);
				for (_Fields f : columnList) {
					if (f.getColumn().equalsIgnoreCase(columnName) && !StringUtils.isEmpty(comment)) {
						f.setComment(comment);
					}
				}
			}
			rsc.close();
			psc.close();
		}
		conn.close();
		return classData;
	}

	/**
	 * 根据sql语句生成类
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public _Class getClassData(String sql, String className) throws SQLException {
		_Class classData = new _Class();
		classData.setClassName(className);
		classData.setSql(sql); 
		List<_Fields> columnList = new ArrayList<_Fields>();
		classData.setColumns(columnList);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData data = rs.getMetaData();
		classData.setContainPrimaryKey(false);
		for (int i = 1; i <= data.getColumnCount(); i++) {
			String columnName = data.getColumnName(i);// 获得指定列的列名
			String columnTypeName = data.getColumnTypeName(i); // 获得指定列的数据类型名
			String columnClassName = data.getColumnClassName(i); // 对应数据类型的类
			int columnDisplaySize = data.getColumnDisplaySize(i); // 在数据库中类型的最大字符个数
			int precision = data.getPrecision(i); // 某列类型的精确度(类型的长度)
			int scale = data.getScale(i); // 小数点后的位数
			int isNullable = data.isNullable(i); // 是否为空
			_Fields cd = new _Fields();
			cd.setField(this.getField(columnName));
			if (columnClassName.contains("Time"))
				cd.setFieldType("java.util.Date");
			else
				cd.setFieldType(columnClassName);
			cd.setPrecision(precision);
			cd.setScale(scale);
			cd.setColumn(columnName);
			cd.setComment(columnName);
			cd.setColumnType(columnTypeName);
			cd.setIsNullable(isNullable);
			cd.setSize(columnDisplaySize);
			columnList.add(cd);
		}
		rs.close();
		ps.close();
		conn.close();
		return classData;
	}

	/**
	 * 获取类名
	 * 
	 * @param talbeName
	 * @return
	 */
	private String getClassName(String tableName) {
		String[] split;
		if (tableName.startsWith("t_"))
			split = tableName.substring(2).split("_");
		else
			split = tableName.split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			String tempName = split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length());
			sb.append(tempName);
		}
		return sb.toString();
	}

	/**
	 * 格式化列名获取属性
	 * 
	 * @param colunName
	 * @return
	 */
	public String getField(String colunName) {
		String[] split = colunName.toLowerCase().split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			String tempName = "";
			if (i == 0) {
				tempName = split[0].substring(0, 1).toLowerCase() + split[0].substring(1, split[0].length());
				sb.append(tempName);
				continue;
			}
			tempName = split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length());
			sb.append(tempName);
		}
		return sb.toString();
	}

}