package com.modules.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.AutocompareService;
import com.modules.service.DrugDrugService;
import com.modules.service.SpaceService;
import com.modules.vo.DrugVo;
import com.modules.dao.AutocompareDao;
import com.modules.pojo.Autocompare;
import com.modules.pojo.Drug;
import com.modules.pojo.DrugDrug;

/**
 * 
 * @author Du.Jun
 */
@Service

public class AutocompareServiceImpl implements AutocompareService {

	@Autowired
	private AutocompareDao autocompareDao;
	@Autowired
	private SpaceService spaceService;
	@Autowired
	private DrugDrugService drugDrugService;

	@Transactional(rollbackFor = Exception.class)
	public void save(Autocompare entity) throws Exception {
		autocompareDao.save(entity);
	}
	@Transactional(rollbackFor = Exception.class)
	public void save(List<Autocompare> entities) throws Exception {
		autocompareDao.save(entities);
	}
	@Transactional(rollbackFor = Exception.class)
	public void update(Autocompare entity) throws Exception {
		autocompareDao.update(entity);
	}
	@Transactional(rollbackFor = Exception.class)
	public void update(Autocompare newEntity, Autocompare oldEntity) throws Exception {
		autocompareDao.update(newEntity, oldEntity);
	}
	@Transactional(rollbackFor = Exception.class)
	public void delete(Autocompare entity) throws Exception {
		autocompareDao.delete(entity);
	}

	public Autocompare findObject(Autocompare entity) throws Exception {
		return autocompareDao.findObject(entity);
	}

	public List<Autocompare> findList(Autocompare entity) throws Exception {
		return autocompareDao.findList(entity);
	}

	public PageResult<Autocompare> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_autocompare where 1=1 ");
		if (params.containsKey("userid")) {
			sql.append("and spaceid in(select id from t_space where  userid=?  ) ");
			ps.add(params.get("userid"));
		}

		if (params.containsKey("spaceid")) {
			sql.append(" and spaceid =?");
			ps.add(params.get("spaceid"));
		}

		if (params.containsKey("catalogid")) {
			sql.append(" and catalogid =?");
			ps.add(params.get("catalogid"));
		}

		if (params.containsKey("startdate")) {
			sql.append(" and startdate  >=?");
			ps.add(params.get("startdate"));
		}
		if (params.containsKey("enddate")) {
			sql.append(" and startdate <=?");
			ps.add(params.get("enddate"));
		}

		if (params.containsKey("finished")) {
			if (Boolean.valueOf(params.get("finished").toString())) {
				sql.append(" and enddate is not null");
			} else {
				sql.append(" and enddate is   null");
			}
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return autocompareDao.findList(sqlContext, page, Autocompare.class);
	}

	@Override
	public void autoCompare(Autocompare autocompare) throws Exception {
		// TODO Auto-generated method stub
		Page page = new Page(1, 100);
		HashMap<String, Object> params1 = new HashMap<String, Object>();
		params1.put("spaceid", autocompare.getSpaceid());
		params1.put("catalogid", autocompare.getCatalogid());
		PageResult<DrugVo> data;
		data = spaceService.findDrugs(params1, page);
		for (int i = 1; i <= data.getTotalPages(); i++) {
			PageResult<DrugVo> pagedata = spaceService.findDrugs(params1, new Page(i, 100, false));
			for (DrugVo d : pagedata.getData()) {
				Drug drug = new Drug();
				drug.setFactory(d.getFactory());
				drug.setName(d.getName());
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("drugid", d.getId());
				params.put("spaceid", autocompare.getSpaceid());
				params.put("smart", false);
				PageResult<Drug> list = spaceService.findcomparedrugs(params, page);
				if (list.getData().size() > 0) {
					DrugDrug drugdrug = new DrugDrug();
					drugdrug.setDrugid(d.getId());
					drugdrug.setSpaceid(autocompare.getSpaceid());
					List<DrugDrug> dds = drugDrugService.findList(drugdrug);
					if (dds.size() == 0) {
						// 根据标准品种效验 对照结果
//						Sdrug d1 = spaceService.findcompareSdrugs(list.getData().get(0));
//						Sdrug d2 = spaceService.findcompareSdrugs(drug);
//						if (d1 != null && d2 != null)
//							if (d1.getId().equals(d2.getId())) {
								drugdrug.setSdrugid(list.getData().get(0).getId());
								drugdrug.setChecked(false);
								drugdrug.setNum(0);
								drugDrugService.save(drugdrug);
//							}
					}
				}
			}
		}
		autocompare.setEnddate(new Date());
		this.update(autocompare);
	}
}
