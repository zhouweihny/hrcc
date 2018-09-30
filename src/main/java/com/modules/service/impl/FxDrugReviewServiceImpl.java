package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.FxDrugReviewService;
import com.modules.vo.drugFX.FxDrugReviewVo;
import com.modules.dao.FxDrugReviewDao;
import com.modules.pojo.FxComname;
import com.modules.pojo.FxDrugReview;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxDrugReviewServiceImpl implements FxDrugReviewService {

	@Autowired
	private FxDrugReviewDao fxDrugReviewDao;

	public void save(FxDrugReview entity) throws Exception {
		fxDrugReviewDao.save(entity);
	}

	public void save(List<FxDrugReview> entities) throws Exception {
		fxDrugReviewDao.save(entities);
	}

	public void update(FxDrugReview entity) throws Exception {
		fxDrugReviewDao.update(entity);
	}

	public void update(FxDrugReview newEntity, FxDrugReview oldEntity) throws Exception {
		fxDrugReviewDao.update(newEntity, oldEntity);
	}

	public void delete(FxDrugReview entity) throws Exception {
		fxDrugReviewDao.delete(entity);
	}

	public FxDrugReview findObject(FxDrugReview entity) throws Exception {
		return fxDrugReviewDao.findObject(entity);
	}

	public List<FxDrugReview> findList(FxDrugReview entity) throws Exception {
		return fxDrugReviewDao.findList(entity);
	}

	public PageResult<FxDrugReview> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_drug_review where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxDrugReviewDao.findList(sqlContext, page, FxDrugReview.class);
	}

	@Override
	public PageResult<FxDrugReviewVo> findByDrugReview(String companyid, String drugname, Integer status,
			Integer currentPage, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		Page page = new Page(currentPage, pageSize);
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select a.id,a.code,a.common,a.name,a.specifications,a.unit,a.dosageform,a.factory,a.zunzi,a.costprice,a.price,a.comnameid,c.company company,   ");
				sql.append(" CASE  WHEN d.`status`=0 THEN '不通过' WHEN d.`status`=1 THEN '通过' ELSE '待审核' END statusname   ");
				sql.append(" from t_drug a  ");
				sql.append(" left join t_catalog b on a.catalogid = b.id  ");
				sql.append(" left join  t_user c on  b.userid=c.id  ");
				sql.append(" left join fx_drug_review d on a.id =d.drugid  ");
				sql.append(" where 1=1 ");
				if(StringUtils.isNotBlank(companyid))
					sql.append("  and  c.company like '%"+ companyid +"%'  ");
				
				if(StringUtils.isNotBlank(drugname))
					sql.append(" and  a.name='"+ drugname +"'  ");
				
				if(status !=null)
				{
					if(status==0 || status ==1)
					sql.append(" and  d.`status`="+ status );
					else 
						sql.append("  and d.`status` is null  ");
				}
		
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxDrugReviewDao.findList(sqlContext, page, FxDrugReviewVo.class);
	}

}
