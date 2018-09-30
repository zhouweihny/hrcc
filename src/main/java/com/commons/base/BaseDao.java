package com.commons.base;

import java.util.List;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;

/**
 * 
 * @author Du.Jun
 */
public interface BaseDao<T> {

	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void save(T entity) throws Exception;

	/**
	 * 批量保存对象
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void save(List<T> entities) throws Exception;

	/**
	 * 更新对象 id必须传
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void update(T entity) throws Exception;

	/**
	 * 更新对象
	 * 
	 * @param newEntity
	 *            新对象
	 * @param oldEntity
	 *            旧对象 作为查询条件
	 * @throws Exception
	 */
	public void update(T newEntity, T oldEntity) throws Exception;

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            做查询条件
	 * @throws Exception
	 */
	public void delete(T entity) throws Exception;

	/**
	 * 执行语句
	 * 
	 * @param sqlContext
	 *            执行语句
	 * @throws Exception
	 */
	public void update(SqlContext sqlContext) throws Exception;

	/**
	 * 根据对象内容第一条数据
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T findObject(T entity) throws Exception;

	/**
	 * 根据对象内容查询
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> findList(T entity) throws Exception;

	/**
	 * 根据对象内容做分页查询
	 * 
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public PageResult<T> findList(T entity, Page page) throws Exception;

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param sqlContext
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> E findObject(SqlContext sqlContext, Class<E> clazz) throws Exception;

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param sqlContext
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> E findE(SqlContext sqlContext, Class<E> clazz) throws Exception;

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param sqlContext
	 * @param page
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> List<E> findList(SqlContext sqlContext, Class<E> clazz) throws Exception;

	/**
	 * 自己拼装sqlContext查询
	 * 
	 * @param <E>
	 * 
	 * @param sqlContext
	 * @param page
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <E> PageResult<E> findList(SqlContext sqlContext, Page page, Class<E> clazz) throws Exception;
}