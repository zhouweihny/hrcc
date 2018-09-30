package com.commons.sql;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.jdbc.core.RowMapper;

import com.commons.annotation.Column;

/**
 * 通用类型映射转换
 * 
 * @author Du.Jun
 *
 */
public class DefaultRowMapper<T> implements RowMapper<T> {

	/** 转换的目标对象 */
	private Class<T> clazz;

	public DefaultRowMapper(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T mapRow(ResultSet resultSet, int i) throws SQLException {
		T entity;
		try {
			entity = (T) clazz.newInstance();
			LinkedHashMap<Field, Column> cmap = SqlCache.getProperty(clazz);
			for (Entry<Field, Column> entry : cmap.entrySet()) {
				Field field = entry.getKey();
				field.setAccessible(true);
				try {
					if (resultSet.findColumn(entry.getValue().value()) > 0) {
						if (field.getType().getName().equals("java.lang.Integer"))
							field.set(entity, resultSet.getInt(entry.getValue().value()));
						else if (field.getType().getName().equals("java.lang.Double"))
							field.set(entity, resultSet.getDouble(entry.getValue().value()));
						else if (field.getType().getName().equals("java.lang.Short"))
							field.set(entity, resultSet.getShort(entry.getValue().value()));
						else if (field.getType().getName().equals("java.lang.Long"))
							field.set(entity, resultSet.getLong(entry.getValue().value()));
						else if (field.getType().getName().equals("java.lang.String"))
							field.set(entity, resultSet.getString(entry.getValue().value()));
						else if (field.getType().getName().equals("java.lang.Boolean"))
							field.set(entity, resultSet.getBoolean(entry.getValue().value()));
						else if (field.getType().getName().equals("java.util.Date"))
							field.set(entity, resultSet.getTimestamp(entry.getValue().value()));
						else {
							field.set(entity, resultSet.getObject(entry.getValue().value()));
						}
					}
				} catch (SQLException e) {
//					e.printStackTrace();
				}
			}
			return entity;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}