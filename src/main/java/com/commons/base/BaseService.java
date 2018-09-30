package com.commons.base;

import java.util.List;
import java.util.Map;

import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * @author Du.Jun
 *
 * @param <T>
 */
public interface BaseService<T> {

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
	 * 根据参数做分页查询
	 * 
	 * @param params
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public  PageResult<T> findList(Map<String, Object> params, Page page) throws Exception;

}