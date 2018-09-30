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
import com.modules.service.DrugService;
import com.modules.service.FxImpstoreService;
import com.modules.dao.FxImpstoreDao;
import com.modules.pojo.Drug;
import com.modules.pojo.FxImpstore;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxImpstoreServiceImpl implements FxImpstoreService {

	@Autowired
	private FxImpstoreDao fxImpstoreDao;
	
	@Autowired
	private DrugService drugService;

	public void save(FxImpstore entity) throws Exception {
		fxImpstoreDao.save(entity);
	}

	public void save(List<FxImpstore> entities) throws Exception {
		fxImpstoreDao.save(entities);
	}

	public void update(FxImpstore entity) throws Exception {
		fxImpstoreDao.update(entity);
	}

	public void update(FxImpstore newEntity, FxImpstore oldEntity) throws Exception {
		fxImpstoreDao.update(newEntity, oldEntity);
	}

	public void delete(FxImpstore entity) throws Exception {
		fxImpstoreDao.delete(entity);
	}

	public FxImpstore findObject(FxImpstore entity) throws Exception {
		return fxImpstoreDao.findObject(entity);
	}

	public List<FxImpstore> findList(FxImpstore entity) throws Exception {
		return fxImpstoreDao.findList(entity);
	}

	public PageResult<FxImpstore> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_impstore where 1=1 ");
		
		if (params.containsKey("userid")) {
			sql.append(" and userid=? ");
			ps.add(params.get("userid"));
		}
		
		if (params.containsKey("salefileid")) {
			sql.append(" and impfilenameid in (select id from fx_impfilename where extend=? and type='库存数据' ) ");
			ps.add(params.get("salefileid"));
		}
		
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxImpstoreDao.findList(sqlContext, page, FxImpstore.class);
	}

	@Override
	public void upload(String catalogid, String userid, List<FxImpstore> fxImpstores) {
		// TODO Auto-generated method stub
		Drug drug;
		for (FxImpstore sale : fxImpstores) {
			
			drug = new Drug();
			drug.setCatalogid(catalogid);
			drug.setCode(sale.getCode());
			try {
				drug = drugService.findObject(drug);
				
				if (drug == null) {
					drug = new Drug();
					drug.setName(sale.getName());					
					drug.setDosageform(sale.getDosageform());
					drug.setFactory(sale.getFactory());
					drug.setSpecifications(sale.getSpecifications());
					drug.setUnit(sale.getUnit());
					drug.setZunzi(sale.getZunzi());					
					drugService.save(drug);
				} 

				sale.setDrugid(drug.getId());
				fxImpstoreDao.save(sale);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
