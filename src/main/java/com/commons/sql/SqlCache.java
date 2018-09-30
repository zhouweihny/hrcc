package com.commons.sql;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.commons.annotation.Column;

/**
 * 用于缓存数据对象
 * @author Du.Jun
 *
 */
public class SqlCache {

	/**
	 * 缓存数据库对象映射关系
	 */
	public static HashMap<Class<?>, LinkedHashMap<Field, Column>> cache = null;

	/**
	 * 根据实体获取数据库字段映射关系
	 * @param clazz
	 * @return
	 */
	public static LinkedHashMap<Field, Column> getProperty(Class<?> clazz) {
		if (cache == null) {
			cache = new HashMap<Class<?>, LinkedHashMap<Field, Column>>();
		}
		if (!cache.containsKey(clazz)) {
			Field[] fields = clazz.getDeclaredFields();
			LinkedHashMap<Field, Column> map = new LinkedHashMap<Field, Column>();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = (Column) field.getAnnotation(Column.class);
					map.put(field, column);
				}
			}
			cache.put(clazz, map);
		}
		return cache.get(clazz);
	}

	/**
	 * 清楚缓存
	 */
	public static void clear() {
		cache.clear();
	}

}
