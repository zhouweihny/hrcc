package com.modules.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.FxOrderfxService;
import com.modules.service.FxTreeService;
import com.modules.vo.LhyyVo;
import com.modules.dao.FxOrderfxDao;
import com.modules.pojo.FxOrderfx;
import com.modules.pojo.FxTree;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxOrderfxServiceImpl implements FxOrderfxService {

	@Autowired
	private FxOrderfxDao fxOrderfxDao;

	@Autowired
	private FxTreeService fxTreeService;

	public void save(FxOrderfx entity) throws Exception {
		fxOrderfxDao.save(entity);
	}

	public void save(List<FxOrderfx> entities) throws Exception {
		fxOrderfxDao.save(entities);
	}

	public void update(FxOrderfx entity) throws Exception {
		fxOrderfxDao.update(entity);
	}

	public void update(FxOrderfx newEntity, FxOrderfx oldEntity) throws Exception {
		fxOrderfxDao.update(newEntity, oldEntity);
	}

	public void delete(FxOrderfx entity) throws Exception {
		fxOrderfxDao.delete(entity);
	}

	public FxOrderfx findObject(FxOrderfx entity) throws Exception {
		return fxOrderfxDao.findObject(entity);
	}

	public List<FxOrderfx> findList(FxOrderfx entity) throws Exception {
		return fxOrderfxDao.findList(entity);
	}

	public PageResult<FxOrderfx> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_orderfx where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxOrderfxDao.findList(sqlContext, page, FxOrderfx.class);
	}

	public void fxOrderno(String orderno, String storeid, Date rq, BigDecimal xse, String userid) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		ps.add(storeid);
		ps.add(orderno);
		sql.append(
				"select s.orderno,sc.id schemeid,sc.treeid treeid    from fx_sale_data s,t_drug d,t_scheme_comname scc ,t_scheme sc  ");
		sql.append(" where s.drugid=d.id  and s.storeid=?     and s.orderno=? and d.comnameid=scc.comnameid ");
		sql.append(" and sc.id=scc.schemeid group by s.orderno,sc.name,sc.treeid   having count(1)>1");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<FxOrderfx> orderfxs = fxOrderfxDao.findList(sqlContext, FxOrderfx.class);

		if (orderfxs.size() == 0) {
			sql = new StringBuilder();
			sql.append("select orderno, ctr.treeid treeid from  fx_sale_data s,t_drug d  ,fx_comname_tree ctr  ");
			sql.append(
					"where s.drugid=d.id  and s.storeid=? and     s.orderno=? and d.comnameid=ctr.comnameid and ctr.fwflag=true   ");
			sql.append("group  by orderno , ctr.treeid ");
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			orderfxs = fxOrderfxDao.findList(sqlContext, FxOrderfx.class);
		}

		for (FxOrderfx fxOrderfx : orderfxs) {
			fxOrderfx.setXse(xse);
			fxOrderfx.setRq(rq);
			fxOrderfx.setUserid(userid);
			fxOrderfx.setStoreid(storeid);
		}

		fxOrderfxDao.save(orderfxs);
	}

	@Override
	public void refresh(Date rq) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select orderno ,saledate rq,userid,storeid ,sum(price) xse from fx_sale_data  where saledate=?   GROUP BY orderno,userid,storeid,saledate  ");
		ps.add(rq);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<FxOrderfx> orderfxs = fxOrderfxDao.findList(sqlContext, FxOrderfx.class);
		for (FxOrderfx orderfx : orderfxs) {
			this.fxOrderno(orderfx.getOrderno(), orderfx.getStoreid(), orderfx.getRq(), orderfx.getXse(),
					orderfx.getUserid());
		}
	}

	@Override
	public void refresh(String orderno, String storeid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select orderno ,saledate rq,userid,storeid ,sum(price) xse from fx_sale_data  where orderno=? and storeid=?  GROUP BY orderno,userid,storeid,saledate  ");

		ps.add(orderno);
		ps.add(storeid);

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<FxOrderfx> orderfxs = fxOrderfxDao.findList(sqlContext, FxOrderfx.class);
		for (FxOrderfx orderfx : orderfxs) {
			this.fxOrderno(orderfx.getOrderno(), orderfx.getStoreid(), orderfx.getRq(), orderfx.getXse(),
					orderfx.getUserid());
		}
	}

	@Override
	public List<LhyyVo> lhyyfx(Date startdate, Date enddate, String storeid) throws Exception {
		// TODO Auto-generated method stub

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select a.treeid,a.zbs,b.bs,a.xse,a.kdj,b.avdj from  ");
		sql.append(
				" (select treeid,count(1) zbs,avg(xse) kdj,sum(xse)  xse  from fx_orderfx  t   where rq>=? and rq<=? and storeid=? GROUP BY treeid) a left join ");
		sql.append(
				" (select treeid,count(1) bs,avg(xse) avdj   from fx_orderfx  t where t.schemeid is not null and rq>=? and rq<=? and storeid=?  GROUP BY treeid ) b ");
		sql.append(" on  a.treeid=b.treeid  order by a.zbs desc    ");
		ps.add(startdate);
		ps.add(enddate);
		ps.add(storeid);
		ps.add(startdate);
		ps.add(enddate);
		ps.add(storeid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<LhyyVo> list = fxOrderfxDao.findList(sqlContext, LhyyVo.class);
		List<FxTree> trees = fxTreeService.findByPath("18346A0044A148D1A818627B25510E59");

		loop: for (LhyyVo vo : list) {
			if (StringUtils.isNotBlank(vo.getTreeid()))
				for (FxTree t1 : trees) {
					if (vo.getTreeid().equals(t1.getId())) {
						vo.setLv2(t1.getName());
						for (FxTree t2 : trees) {
							if (t1.getParentid().equals(t2.getId())) {
								vo.setLv1(t2.getName());
								continue loop;
							}
						}
					}
				}
		}
		return list;
	}

	@Override
	public List<LhyyVo> lhyyfx2(Date startdate, Date enddate, String storeid) throws Exception {
		// TODO Auto-generated method stub

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select a.treeid,a.zbs,b.bs,a.xse,a.kdj,b.avdj from  ");
		sql.append(
				" (select treeid,count(1) zbs,avg(xse) kdj,sum(xse)  xse  from fx_orderfx  t   where rq>=? and rq<=? and storeid=? GROUP BY treeid) a left join ");
		sql.append(
				" (select treeid,count(1) bs,avg(xse) avdj   from fx_orderfx  t where t.schemeid is not null and rq>=? and rq<=? and storeid=?  GROUP BY treeid ) b ");
		sql.append(" on  a.treeid=b.treeid  order by a.zbs desc    ");
		ps.add(startdate);
		ps.add(enddate);
		ps.add(storeid);
		ps.add(startdate);
		ps.add(enddate);
		ps.add(storeid);

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<LhyyVo> list = fxOrderfxDao.findList(sqlContext, LhyyVo.class);
		List<FxTree> trees = fxTreeService.findByPath("18346A0044A148D1A818627B25510E59");

		loop: for (LhyyVo vo : list) {
			if (StringUtils.isNotBlank(vo.getTreeid()))
				for (FxTree t1 : trees) {
					if (vo.getTreeid().equals(t1.getId())) {
						vo.setLv2(t1.getName());
						for (FxTree t2 : trees) {
							if (t1.getParentid().equals(t2.getId())) {
								vo.setLv1(t2.getName());
								continue loop;
							}
						}
					}
				}
		}

		Iterator<LhyyVo> iter = list.iterator();
		while (iter.hasNext()) {
			LhyyVo vo = iter.next();
			if (vo.getLv2() == null) {
				iter.remove();

			}
		}
		return list;
	}

	@Override
	public List<LhyyVo> lhyyfx(Date startdate, Date enddate, String storeid, String treeid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select a.treeid,a.zbs,b.bs,a.xse,a.kdj,b.avdj from  ");
		sql.append(
				" (select treeid,count(1) zbs,avg(xse) kdj,sum(xse)  xse  from fx_orderfx  t   where rq>=? and rq<=? and storeid=? GROUP BY treeid) a left join  ");
		sql.append(
				" (select treeid,count(1) bs,avg(xse) avdj   from fx_orderfx  t where t.schemeid is not null and rq>=? and rq<=? and storeid=? and t.treeid=?  GROUP BY treeid ) b ");
		sql.append(" on  a.treeid=b.treeid    order by a.zbs desc   ");
		ps.add(startdate);
		ps.add(enddate);
		ps.add(storeid);
		ps.add(startdate);
		ps.add(enddate);
		ps.add(storeid);
		ps.add(treeid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<LhyyVo> list = fxOrderfxDao.findList(sqlContext, LhyyVo.class);
		List<FxTree> trees = fxTreeService.findByPath("18346A0044A148D1A818627B25510E59");

		loop: for (LhyyVo vo : list) {
			if (StringUtils.isNotBlank(vo.getTreeid()))
				for (FxTree t1 : trees) {
					if (vo.getTreeid().equals(t1.getId())) {
						vo.setLv2(t1.getName());
						for (FxTree t2 : trees) {
							if (t1.getParentid().equals(t2.getId())) {
								vo.setLv1(t2.getName());
								continue loop;
							}
						}
					}
				}
		}
		return list;
	}
}
