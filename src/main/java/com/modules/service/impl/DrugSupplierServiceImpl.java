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
import com.modules.service.AgentService;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.DrugSupplierService;
import com.modules.service.UserSupplierService;
import com.modules.vo.DrugSupplierExcelVo;
import com.modules.vo.DrugSupplierVo;
import com.modules.dao.DrugSupplierDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.DrugSupplier;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugSupplierServiceImpl implements DrugSupplierService {

	@Autowired
	private DrugSupplierDao drugSupplierDao;

	@Autowired
	private DrugService drugService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private AgentService agentService;

	@Autowired
	private UserSupplierService userSupplierService;

	public void save(DrugSupplier entity) throws Exception {
		drugSupplierDao.save(entity);
	}

	public void save(List<DrugSupplier> entities) throws Exception {
		drugSupplierDao.save(entities);
	}

	public void update(DrugSupplier entity) throws Exception {
		drugSupplierDao.update(entity);
	}

	public void update(DrugSupplier newEntity, DrugSupplier oldEntity) throws Exception {
		drugSupplierDao.update(newEntity, oldEntity);
	}

	public void delete(DrugSupplier entity) throws Exception {
		drugSupplierDao.delete(entity);
	}

	public DrugSupplier findObject(DrugSupplier entity) throws Exception {
		return drugSupplierDao.findObject(entity);
	}

	public List<DrugSupplier> findList(DrugSupplier entity) throws Exception {
		return drugSupplierDao.findList(entity);
	}

	public PageResult<DrugSupplier> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select ds.* from t_drug_supplier ds left join t_drug d on ds.drugid=d.id  left join t_user_supplier us on ds.erpsucode=us.code  and ds.purchaserid=us.userid     left join t_agent a on  a.mobileno=ds.mobile and a.userid=ds.purchaserid where 1=1 ");

		if (params.containsKey("name")) {
			sql.append(" and d.name like  ? ");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("agent")) {
			sql.append(" and a.name like  ? ");
			ps.add("%" + params.get("agent") + "%");
		}

		if (params.containsKey("company")) {
			sql.append(" and us.name like  ? ");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("purchaserid")) {
			sql.append(" and ds.purchaserid =?  ");
			ps.add(params.get("purchaserid"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugSupplierDao.findList(sqlContext, page, DrugSupplier.class);
	}

	@Override
	public PageResult<DrugSupplierVo> findDrugSupplierVoList(Map<String, Object> params, Page page) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select ds.id,us.supplierid,d.name,d.code,d.factory,d.specifications,d.unit,d.dosageform,u.company as supplier,a.name as agent,a.id as agentid,a.mobileno as mobileno "
				+ "from t_drug_supplier ds left join t_user_supplier us on us.code=ds.erpsucode and us.userid=ds.purchaserid "
				+ "left join t_drug d on ds.drugid =d.id left join t_user u on us.supplierid=u.id left join  t_agent a on a.mobileno=ds.mobile and a.purchaserid=ds.purchaserid where 1=1 ");

		if (params.containsKey("name")) {
			sql.append(" and d.name like  ? ");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("agent")) {
			sql.append(" and a.name like  ? ");
			ps.add("%" + params.get("agent") + "%");
		}

		if (params.containsKey("company")) {
			sql.append(" and u.company like  ? ");
			ps.add("%" + params.get("company") + "%");
		}

		if (params.containsKey("purchaserid")) {
			sql.append(" and ds.purchaserid =?  ");
			ps.add(params.get("purchaserid"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugSupplierDao.findList(sqlContext, page, DrugSupplierVo.class);
	}

	@Override
	public void upload(String supplierid, String purchaserid, List<DrugSupplierExcelVo> list) throws Exception {
		// TODO Auto-generated method stub
		Catalog catalog = new Catalog();
		catalog.setMyself(true);
		catalog.setUserid(purchaserid);
		catalog = catalogService.findObject(catalog);
		for (DrugSupplierExcelVo v : list) {
			Drug drug = new Drug();
			drug.setCode(v.getCode());
			drug.setCatalogid(catalog.getId());
			drug = drugService.findObject(drug);
			if (drug != null) {
				DrugSupplier drugSupplier = new DrugSupplier();
				drugSupplier.setPurchaserid(purchaserid);
				drugSupplier.setDrugid(drug.getId());
				drugSupplier = drugSupplierDao.findObject(drugSupplier);
				if (drugSupplier == null) {
					drugSupplier = new DrugSupplier();
					drugSupplier.setPurchaserid(purchaserid);
					drugSupplier.setDrugid(drug.getId());
					drugSupplier.setErpsucode(v.getErpsucode());
					drugSupplier.setMobile(v.getMobileno());
					drugSupplierDao.save(drugSupplier);
				} else {
					drugSupplier.setPurchaserid(purchaserid);
					drugSupplier.setDrugid(drug.getId());
					drugSupplier.setErpsucode(v.getErpsucode());
					drugSupplier.setMobile(v.getMobileno());
					drugSupplierDao.update(drugSupplier);
				}
			}

		}
	}

}
