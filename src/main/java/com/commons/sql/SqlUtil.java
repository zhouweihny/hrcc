package com.commons.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

import com.commons.annotation.Column;
import com.commons.annotation.Relation;
import com.commons.util.UUIDGenerator;

/**
 * @author Du.Jun
 *
 */
public class SqlUtil {

	/**
	 * 构建动态insert语句
	 * 
	 * @param entity
	 *            实体映射对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static SqlContext buildInsertSql(Object entity) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = entity.getClass();
		Relation r = clazz.getAnnotation(Relation.class);
		StringBuilder sql = new StringBuilder("insert into ").append(r.value()).append(" ");
		List<Object> params = new ArrayList<Object>();
		// 获取属性信息
		LinkedHashMap<Field, Column> cmap = SqlCache.getProperty(clazz);
		sql.append("(");
		StringBuilder args = new StringBuilder();
		args.append("(");
		for (Entry<Field, Column> entry : cmap.entrySet()) {
			Field field = entry.getKey();
			field.setAccessible(true);
			Object value = field.get(entity);
			if (entry.getValue().isId()) {
				if (entry.getValue().generateId() && StringUtils.isEmpty(value)) {
					value = UUIDGenerator.getUUID();
					field.set(entity, value);
				}
			} else if (field.getName().equalsIgnoreCase("updatetime") || field.getName().equalsIgnoreCase("createtime")) {
				if (value == null)
					value = new Date();
			}

			if (value == null) {
				continue;
			}
			sql.append(entry.getValue().value());
			sql.append(",");
			args.append("?");
			params.add(value);
			args.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		args.deleteCharAt(args.length() - 1);
		args.append(")");
		sql.append(")");
		sql.append(" values ");
		sql.append(args);
		return new SqlContext(sql.toString(), params.toArray());
	}

	/**
	 * 构建批量insert语句
	 * 
	 * @param entity
	 *            实体映射对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static SqlBatchContext buildInsertBatchSql(List<?> entities) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = entities.get(0).getClass();
		Relation r = clazz.getAnnotation(Relation.class);
		StringBuilder sql = new StringBuilder("insert into ").append(r.value()).append(" ");
		List<Object[]> params = new ArrayList<Object[]>();
		// 获取属性信息
		LinkedHashMap<Field, Column> cmap = SqlCache.getProperty(clazz);
		sql.append("(");
		StringBuilder args = new StringBuilder();
		args.append("(");
		for (Entry<Field, Column> entry : cmap.entrySet()) {
			Field field = entry.getKey();
			field.setAccessible(true);
			sql.append(entry.getValue().value());
			sql.append(",");
			args.append("?");
			args.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		args.deleteCharAt(args.length() - 1);
		args.append(")");
		sql.append(")");
		sql.append(" values ");
		sql.append(args);

		for (Object entity : entities) {
			List<Object> param = new ArrayList<Object>();
			for (Entry<Field, Column> entry : cmap.entrySet()) {
				Field field = entry.getKey();
				field.setAccessible(true);
				Object value = field.get(entity);
				if (entry.getValue().isId()) {
					if (entry.getValue().generateId() && StringUtils.isEmpty(value))
						value = UUIDGenerator.getUUID();
				} else if (field.getName().equalsIgnoreCase("updatetime") || field.getName().equalsIgnoreCase("createtime")) {
					if (value == null)
						value = new Date();
				}
				param.add(value);
			}
			params.add(param.toArray());
		}

		return new SqlBatchContext(sql, params);
	}

	/**
	 * 构建动态update语句
	 * 
	 * @param entity
	 *            实体映射对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static SqlContext buildUpdateSql(Object entity) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = entity.getClass();
		Relation r = clazz.getAnnotation(Relation.class);
		StringBuilder sql = new StringBuilder("update ").append(r.value()).append(" set ");
		List<Object> params = new ArrayList<Object>();
		// 获取属性信息
		LinkedHashMap<Field, Column> cmap = SqlCache.getProperty(clazz);
		String primaryKey = null;
		Object primaryValue = null;
		for (Entry<Field, Column> entry : cmap.entrySet()) {
			Field field = entry.getKey();
			field.setAccessible(true);
			Object value = field.get(entity);
			if (entry.getValue().isId()) {
				primaryKey = entry.getValue().value();
				primaryValue = value;
				continue;
			} else if (field.getName().equalsIgnoreCase("updatetime")) {
				value = new Date();
			}

			if (value == null) {
				continue;
			}
			sql.append(entry.getValue().value());
			sql.append(" = ?,");
			params.add(value);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where ").append(primaryKey).append(" = ?");
		params.add(primaryValue);
		return new SqlContext(sql.toString(), params.toArray());
	}

	/**
	 * 构建动态update语句
	 * 
	 * @param entity
	 *            实体映射对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static SqlContext buildUpdateSql(Object newEntity, Object oldEntity) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = newEntity.getClass();
		Relation r = clazz.getAnnotation(Relation.class);
		StringBuilder sql = new StringBuilder("update ").append(r.value()).append(" set ");
		List<Object> params = new ArrayList<Object>();
		// 获取属性信息
		LinkedHashMap<Field, Column> cmap = SqlCache.getProperty(clazz);
		for (Entry<Field, Column> entry : cmap.entrySet()) {
			Field field = entry.getKey();
			field.setAccessible(true);
			Object value = field.get(newEntity);
			if (value == null) {
				continue;
			}
			sql.append(entry.getValue().value());
			sql.append(" = ?,");
			params.add(value);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where  1=1 ");
		// 获取属性信息
		for (Entry<Field, Column> entry : cmap.entrySet()) {
			Field field = entry.getKey();
			field.setAccessible(true);
			Object value = field.get(oldEntity);
			if (value == null) {
				continue;
			}
			sql.append(" and ").append(entry.getValue().value()).append(" = ? ");
			params.add(value);
		}
		return new SqlContext(sql.toString(), params.toArray());
	}

	/**
	 * 构建动态delete语句
	 * 
	 * @param entity
	 *            实体映射对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static SqlContext buildDeleteSql(Object entity) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = entity.getClass();
		Relation r = clazz.getAnnotation(Relation.class);
		StringBuilder sql = new StringBuilder("delete ").append(" from ").append(r.value()).append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		// 获取属性信息
		LinkedHashMap<Field, Column> cmap = SqlCache.getProperty(clazz);
		for (Entry<Field, Column> entry : cmap.entrySet()) {
			Field field = entry.getKey();
			field.setAccessible(true);
			Object value = field.get(entity);
			if (value == null) {
				continue;
			}
			sql.append(" and ").append(entry.getValue().value()).append(" = ? ");
			params.add(value);
		}
		return new SqlContext(sql.toString(), params.toArray());
	}

	/**
	 * 构建动态查询语句
	 * 
	 * @param entity
	 *            实体映射对象
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static SqlContext buildQuerySql(Object entity) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = entity.getClass();
		Relation r = clazz.getAnnotation(Relation.class);
		StringBuilder sql = new StringBuilder("select * ").append(" from ").append(r.value()).append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		// 获取属性信息
		LinkedHashMap<Field, Column> cmap = SqlCache.getProperty(clazz);
		for (Entry<Field, Column> entry : cmap.entrySet()) {
			Field field = entry.getKey();
			field.setAccessible(true);
			Object value = field.get(entity);
			if (value == null) {
				continue;
			}
			sql.append(" and ").append(entry.getValue().value()).append(" = ? ");
			params.add(value);
		}
		return new SqlContext(sql.toString(), params.toArray());
	}

	/**
	 * 构建分页查询总数语句
	 * 
	 * @param sqlContext
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String buildPageSql(String dialect, String sql, Page page) throws Exception {
		StringBuilder pagesql = new StringBuilder();
		if ("MySQL".equalsIgnoreCase(dialect))
			pagesql.append(sql).append(" limit ").append((page.getCurrentPage() - 1) * page.getPageSize()).append(" , ").append(page.getPageSize());
		else if ("Oracle".equalsIgnoreCase(dialect)) {
			pagesql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
			pagesql.append(sql);
			pagesql.append(")   tmp_tb where ROWNUM<=");
			pagesql.append((page.getCurrentPage() - 1) * page.getPageSize() + page.getPageSize());
			pagesql.append(") where row_id>");
			pagesql.append((page.getCurrentPage() - 1) * page.getPageSize());
		} else if ("SqlServer".equalsIgnoreCase(dialect)) {
			pagesql.append("SELECT TOP ");
			pagesql.append(page.getPageSize());
			pagesql.append("  * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID) AS RowNumber,* FROM ( ");
			pagesql.append(sql);
			pagesql.append(")  AS A) AS B WHERE RowNumber >  ");
			pagesql.append((page.getCurrentPage() - 1) * page.getPageSize());
		}
		return pagesql.toString();
	}

	/**
	 * 构建分页查询总数语句
	 * 
	 * @param sqlContext
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String buildPageCountSql(String sql) {
		StringBuilder countsql = new StringBuilder("select count(1) ").append(" from (").append(sql.replaceAll("\\s+(?i)order\\s+.*?\\s+(desc |asc )", "")).append(" ) temp_tb ");
		return countsql.toString();
	}

}
