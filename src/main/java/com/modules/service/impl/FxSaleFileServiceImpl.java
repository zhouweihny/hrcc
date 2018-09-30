package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.FxSaleFileService;
import com.modules.dao.FxSaleFileDao;
import com.modules.pojo.FxSaleData;
import com.modules.pojo.FxSaleFile;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxSaleFileServiceImpl implements FxSaleFileService {

	@Autowired
	private FxSaleFileDao fxSaleFileDao;

	public void save(FxSaleFile entity) throws Exception {
		fxSaleFileDao.save(entity);
	}

	public void save(List<FxSaleFile> entities) throws Exception {
		fxSaleFileDao.save(entities);
	}

	public void update(FxSaleFile entity) throws Exception {
		fxSaleFileDao.update(entity);
	}

	public void update(FxSaleFile newEntity, FxSaleFile oldEntity) throws Exception {
		fxSaleFileDao.update(newEntity, oldEntity);
	}

	public void delete(FxSaleFile entity) throws Exception {
		fxSaleFileDao.delete(entity);
	}

	public FxSaleFile findObject(FxSaleFile entity) throws Exception {
		return fxSaleFileDao.findObject(entity);
	}

	public List<FxSaleFile> findList(FxSaleFile entity) throws Exception {
		return fxSaleFileDao.findList(entity);
	}

	public PageResult<FxSaleFile> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_sale_file where 1=1 ");

		if (params.containsKey("storeid")) {
			sql.append(" and storeid=? ");
			ps.add(params.get("storeid"));
		}

		if (params.containsKey("userid")) {
			sql.append(" and userid=? ");
			ps.add(params.get("userid"));
		}

		if (params.containsKey("status")) {
			sql.append(" and status=? ");
			ps.add(params.get("status"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxSaleFileDao.findList(sqlContext, page, FxSaleFile.class);
	}

	@Override
	public void addProcessed(FxSaleFile saleFile, FxSaleData saleData) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("update  fx_sale_file set processed=processed+1  ");
		if (saleData.getSaledate() != null) {
			if (saleFile.getStartdate() == null || saleFile.getStartdate().getTime() > saleData.getSaledate().getTime()) {
				sql.append("  ,startdate=? ");
				ps.add(saleData.getSaledate());
				saleFile.setStartdate(saleData.getSaledate());
			}
			if (saleFile.getEnddate() == null || saleFile.getEnddate().getTime() < saleData.getSaledate().getTime()) {
				sql.append("  ,enddate=? ");
				ps.add(saleData.getSaledate());
				saleFile.setEnddate(saleData.getSaledate());
			}
		}
		sql.append("  where id=? ");
		ps.add(saleFile.getId());
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		fxSaleFileDao.update(sqlContext);
	}

	@Override
	public void addError(String fileid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("update  fx_sale_file set error=error+1 where id=? ");
		ps.add(fileid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		fxSaleFileDao.update(sqlContext);
	}

}
