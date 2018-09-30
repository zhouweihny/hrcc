package com.modules.dao.impl;

import org.springframework.stereotype.Repository;
import com.modules.dao.FactoryDao;
import com.modules.pojo.Factory;
import com.commons.base.AbstractBaseDao;
import com.commons.cache.MyCache;
import com.commons.cache.MyCache.Type;
import java.util.List;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;

/**
 * 
 * @author Du.Jun
 */
@Repository 
public class FactoryDaoImpl extends AbstractBaseDao<Factory> implements FactoryDao {
	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @throws Exception
	 */
	@MyCache(value = "t_factory", type = Type.UPDATE)
	@Override
	public void save(Factory entity) throws Exception {
		super.save(entity);
	}

	/**
	 * 批量保存对象
	 * 
	 * @param entity
	 * @throws Exception
	 */
	@MyCache(value = "t_factory", type = Type.UPDATE)
	@Override
	public void save(List<Factory> entities) throws Exception {
		super.save(entities);
	}

	/**
	 * 更新对象 id必须传
	 * 
	 * @param entity
	 * @throws Exception
	 */
	@MyCache(value = "t_factory", type = Type.UPDATE)
	@Override
	public void update(Factory entity) throws Exception {
		super.update(entity);
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
	@MyCache(value = "t_factory", type = Type.UPDATE)
	@Override
	public void update(Factory newEntity, Factory oldEntity) throws Exception {
		super.update(newEntity, oldEntity);
	}

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            做查询条件
	 * @throws Exception
	 */
	@MyCache(value = "t_factory", type = Type.UPDATE)
	@Override
	public void delete(Factory entity) throws Exception {
		super.delete(entity);
	}

	/**
	 * 根据对象内容第一条数据
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@MyCache(value = "t_factory", type = Type.QUERY)
	@Override
	public Factory findObject(Factory entity) throws Exception {
		return super.findObject(entity);
	}

	/**
	 * 根据对象内容查询
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@MyCache(value = "t_factory", type = Type.QUERY)
	@Override
	public List<Factory> findList(Factory entity) throws Exception {

		return super.findList(entity);
	}

	/**
	 * 根据对象内容做分页查询
	 * 
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@MyCache(value = "t_factory", type = Type.QUERY)
	@Override
	public PageResult<Factory> findList(Factory entity, Page page) throws Exception {
		return super.findList(entity, page);
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
	@MyCache( type = Type.QUERY)
	@Override
	public <E> E findObject(SqlContext sqlContext, Class<E> clazz) throws Exception {
		return super.findObject(sqlContext, clazz);

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
	@MyCache( type = Type.QUERY)
	@Override
	public <E> List<E> findList(SqlContext sqlContext, Class<E> clazz) throws Exception {
		return super.findList(sqlContext, clazz);
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
	@MyCache(type = Type.QUERY)
	@Override
	public <E> PageResult<E> findList(SqlContext sqlContext, Page page, Class<E> clazz) throws Exception {
		return super.findList(sqlContext, page, clazz);
	}
}