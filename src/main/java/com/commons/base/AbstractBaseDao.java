package com.commons.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.commons.sql.DefaultRowMapper;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlBatchContext;
import com.commons.sql.SqlContext;
import com.commons.sql.SqlUtil;

/**
 * @author Du.Jun
 *
 * @param <T>
 */
public abstract class AbstractBaseDao<T> {

	private String dialect;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void save(T entity) throws Exception {
		SqlContext sqlContext = SqlUtil.buildInsertSql(entity);
		jdbcTemplate.update(sqlContext.getSql(), sqlContext.getParams());
	}

	/**
	 * 批量保存对象
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void save(List<T> entities) throws Exception {
		int count;
		if (entities.size() % 1000 == 0)
			count = entities.size() / 1000;
		else
			count = entities.size() / 1000 + 1;
		for (int i = 1; i <= count; i++) {
			if (entities.size() < 1000 * i - 1) {
				SqlBatchContext sqlBatchContext = SqlUtil.buildInsertBatchSql(entities.subList((1000) * (i - 1), entities.size()));
				jdbcTemplate.batchUpdate(sqlBatchContext.toSql(), sqlBatchContext.getParams());
			} else {
				SqlBatchContext sqlBatchContext = SqlUtil.buildInsertBatchSql(entities.subList((1000) * (i - 1), 1000 * i - 1));
				jdbcTemplate.batchUpdate(sqlBatchContext.toSql(), sqlBatchContext.getParams());
			}
		}

	}

	/**
	 * 更新对象 id必须传
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void update(T entity) throws Exception {
		SqlContext sqlContext = SqlUtil.buildUpdateSql(entity);
		jdbcTemplate.update(sqlContext.getSql(), sqlContext.getParams());
	}

	/**
	 * 执行自己拼装sqlContext查询
	 * 
	 * @param <E>
	 * 
	 * @param sqlContext
	 * @return
	 * @throws Exception
	 */
	public void update(SqlContext sqlContext) throws Exception {
		jdbcTemplate.update(sqlContext.getSql(), sqlContext.getParams());
	}

	/**
	 * 更新对象
	 * 
	 * @param newEntity
	 *            新对象
	 * @param oldEntity
	 *            旧对象 作为查询条件
	 * @throws Exception
	 */
	public void update(T newEntity, T oldEntity) throws Exception {
		SqlContext sqlContext = SqlUtil.buildUpdateSql(newEntity, oldEntity);
		jdbcTemplate.update(sqlContext.getSql(), sqlContext.getParams());
	}

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            做查询条件
	 * @throws Exception
	 */
	public void delete(T entity) throws Exception {
		SqlContext sqlContext = SqlUtil.buildDeleteSql(entity);
		jdbcTemplate.update(sqlContext.getSql(), sqlContext.getParams());
	}

	/**
	 * 根据对象内容第一条数据
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T findObject(T entity) throws Exception {
		SqlContext sqlContext = SqlUtil.buildQuerySql(entity);
		List<T> list = jdbcTemplate.query(sqlContext.getSql(), sqlContext.getParams(), new DefaultRowMapper<T>(getClazz()));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 根据对象内容查询
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> findList(T entity) throws Exception {
		SqlContext sqlContext = SqlUtil.buildQuerySql(entity);
		return jdbcTemplate.query(sqlContext.getSql(), sqlContext.getParams(), new DefaultRowMapper<T>(getClazz()));
	}

	/**
	 * 根据对象内容做分页查询
	 * 
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public PageResult<T> findList(T entity, Page page) throws Exception {
		PageResult<T> pageResult = new PageResult<T>();
		SqlContext sqlContext = SqlUtil.buildQuerySql(entity);
		if (page != null && page.isPage()) {
			if (!page.isNotCount()) {
				int count = jdbcTemplate.queryForObject(SqlUtil.buildPageCountSql(sqlContext.getSql()), sqlContext.getParams(), Integer.class);
				dozerBeanMapper.map(page, pageResult);
				pageResult.setTotalRows(count);
			}
			if (StringUtils.isEmpty(dialect)) {
				dialect = getDatabaseProductName(jdbcTemplate);
			}
			pageResult.setData(jdbcTemplate.query(SqlUtil.buildPageSql(dialect, sqlContext.getSql(), page), sqlContext.getParams(), new DefaultRowMapper<T>(getClazz())));
		} else {
			pageResult.setData(jdbcTemplate.query(SqlUtil.buildPageCountSql(sqlContext.getSql()), sqlContext.getParams(), new DefaultRowMapper<T>(getClazz())));
		}

		return pageResult;
	}

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param <E>
	 * 
	 * @param sqlContext
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> E findObject(SqlContext sqlContext, Class<E> clazz) throws Exception {
		List<E> list = jdbcTemplate.query(sqlContext.getSql(), sqlContext.getParams(), new DefaultRowMapper<E>(clazz));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;

	}

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param <E>
	 * 
	 * @param sqlContext
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> E findE(SqlContext sqlContext, Class<E> requiredType) throws Exception {
		E o = jdbcTemplate.queryForObject(sqlContext.getSql(), sqlContext.getParams(), requiredType);
		return o;

	}

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param sqlContext
	 * @param page
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> List<E> findList(SqlContext sqlContext, Class<E> clazz) throws Exception {
		if (clazz.equals(String.class)||clazz.equals(Integer.class))
			return jdbcTemplate.queryForList(sqlContext.getSql(), sqlContext.getParams(), clazz);
		else
			return jdbcTemplate.query(sqlContext.getSql(), sqlContext.getParams(), new DefaultRowMapper<E>(clazz));
	}

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param sqlContext
	 * @param page
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> PageResult<E> findList(SqlContext sqlContext, Page page, Class<E> clazz) throws Exception {
		PageResult<E> pageResult = new PageResult<E>();
		if (page != null && page.isPage()) {
			if (!page.isNotCount()) {
				int count = jdbcTemplate.queryForObject(SqlUtil.buildPageCountSql(sqlContext.getSql()), sqlContext.getParams(), Integer.class);
				dozerBeanMapper.map(page, pageResult);
				pageResult.setTotalRows(count);
			}
			if (StringUtils.isEmpty(dialect)) {
				dialect = getDatabaseProductName(jdbcTemplate);
			}
			pageResult.setData(jdbcTemplate.query(SqlUtil.buildPageSql(dialect, sqlContext.getSql(), page), sqlContext.getParams(), new DefaultRowMapper<E>(clazz)));
		} else {
			pageResult.setData(jdbcTemplate.query(sqlContext.getSql(), sqlContext.getParams(), new DefaultRowMapper<E>(clazz)));
		}

		return pageResult;
	}

	/**
	 * 获取Class
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getClazz() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (Class<T>) params[0];

	}

	/**
	 * 获取数据库类型
	 * 
	 * @param jdbcTemplate
	 * @return
	 * @throws SQLException
	 */
	public String getDatabaseProductName(JdbcTemplate jdbcTemplate) throws SQLException {
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		DatabaseMetaData md = conn.getMetaData();
		String dialect = md.getDatabaseProductName();
		conn.close();
		return dialect;
	}

}