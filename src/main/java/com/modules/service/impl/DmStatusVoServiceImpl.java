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
import com.modules.service.DmStatusVoService;
import com.modules.service.SpaceCatalogService;
import com.modules.dao.DmStatusVoDao;
import com.modules.pojo.SpaceCatalog;
import com.modules.vo.DmStatusVo;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DmStatusVoServiceImpl implements DmStatusVoService {

	@Autowired
	private DmStatusVoDao dmStatusVoDao;

	@Autowired
	private SpaceCatalogService spaceCatalogService;

	public void save(DmStatusVo entity) throws Exception {
		dmStatusVoDao.save(entity);
	}

	public void save(List<DmStatusVo> entities) throws Exception {
		dmStatusVoDao.save(entities);
	}

	public void update(DmStatusVo entity) throws Exception {
		dmStatusVoDao.update(entity);
	}

	public void update(DmStatusVo newEntity, DmStatusVo oldEntity) throws Exception {
		dmStatusVoDao.update(newEntity, oldEntity);
	}

	public void delete(DmStatusVo entity) throws Exception {
		dmStatusVoDao.delete(entity);
	}

	public DmStatusVo findObject(DmStatusVo entity) throws Exception {
		return dmStatusVoDao.findObject(entity);
	}

	public List<DmStatusVo> findList(DmStatusVo entity) throws Exception {
		return dmStatusVoDao.findList(entity);
	}

	public PageResult<DmStatusVo> findList(Map<String, Object> params, Page page) throws Exception {
		SpaceCatalog spaceCatalog = new SpaceCatalog();
		spaceCatalog.setSpaceid(params.get("spaceid").toString());
		List<SpaceCatalog> list = spaceCatalogService.findList(spaceCatalog);

		StringBuffer ids = new StringBuffer();
		ids.append("'1111111111111111'");
		for (SpaceCatalog s : list) {
			if (!s.getStandard()) {
				ids.append(",'").append(s.getCatalogid()).append("'");
			}
		}
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select  d.catalogid as catalogid ,c.name as name ,IFNULL(dd.num, 2)   as status ,IFNULL(dd.checked,0) checked,count(1) as num from   t_drug d left join t_catalog c on c.id=d.catalogid left join  t_drug_drug dd on d.id=dd.drugid and dd.spaceid=? where   d.catalogid in("
						+ ids + ")  group BY d.catalogid ,c.name,dd.num,dd.checked  ");
		ps.add(params.get("spaceid"));
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return dmStatusVoDao.findList(sqlContext, page, DmStatusVo.class);
	}

}
