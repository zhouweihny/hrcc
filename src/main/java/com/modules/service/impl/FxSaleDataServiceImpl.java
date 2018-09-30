package com.modules.service.impl;

import java.util.List;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.FxSaleDataService;
import com.modules.dao.FxSaleDataDao;
import com.modules.pojo.FxSaleData;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxSaleDataServiceImpl implements FxSaleDataService {

	@Autowired
	private FxSaleDataDao fxSaleDataDao;

	public void save(FxSaleData entity) throws Exception {
		entity.setCostprice(entity.getCostprice().setScale(2, RoundingMode.HALF_UP));
		entity.setPrice(entity.getPrice().setScale(2, RoundingMode.HALF_UP));
		fxSaleDataDao.save(entity);
	}

	public void save(List<FxSaleData> entities) throws Exception {
		fxSaleDataDao.save(entities);
	}

	public void update(FxSaleData entity) throws Exception {
		fxSaleDataDao.update(entity);
	}

	public void update(FxSaleData newEntity, FxSaleData oldEntity) throws Exception {
		fxSaleDataDao.update(newEntity, oldEntity);
	}

	public void delete(FxSaleData entity) throws Exception {
		fxSaleDataDao.delete(entity);
	}

	public FxSaleData findObject(FxSaleData entity) throws Exception {
		return fxSaleDataDao.findObject(entity);
	}

	public List<FxSaleData> findList(FxSaleData entity) throws Exception {
		return fxSaleDataDao.findList(entity);
	}

	public PageResult<FxSaleData> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_sale_data where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxSaleDataDao.findList(sqlContext, page, FxSaleData.class);
	}

}
