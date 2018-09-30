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
import com.modules.service.DrugPromotionService;
import com.modules.vo.DrugPromotionExcelVo;
import com.modules.vo.UserVo;
import com.modules.dao.CatalogDao;
import com.modules.dao.DrugDao;
import com.modules.dao.DrugPromotionDao;
import com.modules.dao.DrugPromotionPurchaesrDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.DrugPromotion;
import com.modules.pojo.DrugPromotionPurchaesr;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugPromotionServiceImpl implements DrugPromotionService {

	@Autowired
	private DrugPromotionDao drugPromotionDao;
	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private DrugDao drugDao;
	@Autowired
	private DrugPromotionPurchaesrDao drugPromotionPurchaesrDao;

	public void save(DrugPromotion entity) throws Exception {
		drugPromotionDao.save(entity);
	}

	public void save(List<DrugPromotion> entities) throws Exception {
		drugPromotionDao.save(entities);
	}

	public void update(DrugPromotion entity) throws Exception {
		drugPromotionDao.update(entity);
	}

	public void update(DrugPromotion newEntity, DrugPromotion oldEntity) throws Exception {
		drugPromotionDao.update(newEntity, oldEntity);
	}

	public void delete(DrugPromotion entity) throws Exception {
		drugPromotionDao.delete(entity);
	}

	public DrugPromotion findObject(DrugPromotion entity) throws Exception {
		return drugPromotionDao.findObject(entity);
	}

	public List<DrugPromotion> findList(DrugPromotion entity) throws Exception {
		return drugPromotionDao.findList(entity);
	}

	public PageResult<DrugPromotion> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select dp.* from t_drug_promotion dp ,t_drug d  where 1=1 and dp.drugid=d.id  ");

		if (params.containsKey("name")) {
			ps.add("%" + params.get("name") + "%");
			sql.append(" and name like ? ");
		}

		if (params.containsKey("status")) {
			ps.add(params.get("status"));
			sql.append(" and status =? ");
		}

		if (params.containsKey("valid")) {
			if ((Boolean) params.get("valid"))
				sql.append(" and    sysdate() between  startdate and enddate ");
		}

		if (params.containsKey("supplierid")) {
			ps.add(params.get("supplierid"));
			sql.append(" and dp.supplierid =? ");
		}

		if (params.containsKey("purchaserid")) {
			ps.add(params.get("purchaserid"));
			sql.append(" and dp.id in(select promotionid from t_drug_promotion_purchaesr  where purchaserid =? ) ");
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugPromotionDao.findList(sqlContext, page, DrugPromotion.class);
	}

	@Override
	public void upload(List<DrugPromotionExcelVo> drugPromotionExcelVos, String userid) throws Exception {
		// TODO Auto-generated method stub
		Catalog catalog = new Catalog();
		catalog.setUserid(userid);
		catalog.setMyself(true);
		List<Catalog> catalogs = catalogDao.findList(catalog);
		if (catalogs.size() == 0) {
			catalogDao.save(catalog);
		}
		catalog = catalogs.get(0);
		for (DrugPromotionExcelVo drugPromotionExcelVo : drugPromotionExcelVos) {
			Drug drug = new Drug();
			drug.setCatalogid(catalog.getId());
			drug.setCode(drugPromotionExcelVo.getCode());
			drug = drugDao.findObject(drug);
			if (drug == null) {
				drug = new Drug();
				drug.setCode(drugPromotionExcelVo.getCode());
				drug.setName(drugPromotionExcelVo.getName());
				drug.setUnit(drugPromotionExcelVo.getUnit());
				drug.setFactory(drugPromotionExcelVo.getFactory());
				drug.setSpecifications(drugPromotionExcelVo.getSpecifications());
				drug.setCatalogid(catalog.getId());
				drugDao.save(drug);

			}
			DrugPromotion drugPromotion = new DrugPromotion();
			drugPromotion.setDrugid(drug.getId());
			drugPromotion.setSupplierid(userid);
			drugPromotion.setStatus(0);
			drugPromotion.setContent(drugPromotionExcelVo.getContent());
			drugPromotion.setStartdate(drugPromotionExcelVo.getStartdate());
			drugPromotion.setEnddate(drugPromotionExcelVo.getEnddate());
			drugPromotionDao.save(drugPromotion);
		}

	}

	@Override
	public List<UserVo> selected(String supplierid, String promotionid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select u.* from t_user_supplier us,t_user u where supplierid =? and us.userid=u.id");
		ps.add(supplierid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<UserVo> uservos = drugPromotionDao.findList(sqlContext, UserVo.class);
		DrugPromotionPurchaesr drugPromotionPurchaesr = new DrugPromotionPurchaesr();
		drugPromotionPurchaesr.setPromotionid(promotionid);
		List<DrugPromotionPurchaesr> list = drugPromotionPurchaesrDao.findList(drugPromotionPurchaesr);
		for (UserVo uservo : uservos) {
			uservo.setChecked(false);
			for (DrugPromotionPurchaesr dpp : list) {
				if (dpp.getPurchaserid().equals(uservo.getId())) {
					uservo.setChecked(true);
					break;
				}
			}
		}
		return uservos;
	}

}
