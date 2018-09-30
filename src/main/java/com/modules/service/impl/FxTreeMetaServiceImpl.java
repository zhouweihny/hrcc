package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.FxTreeMetaDatasMsgService;
import com.modules.service.FxTreeMetaService;
import com.modules.service.StoreService;
import com.modules.service.UserStoreService;
import com.modules.vo.TreeMetaDataMsgVo;
import com.modules.vo.TreeMetaDataVo;
import com.modules.vo.TreeMetaVo;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.modules.dao.FxMetaDao;
import com.modules.dao.FxMetaDatasDao;
import com.modules.dao.FxMethodDao;
import com.modules.dao.FxTreeDao;
import com.modules.dao.FxTreeMetaDao;
import com.modules.dao.FxTreeMetaDatasDao;
import com.modules.dao.UserDao;
import com.modules.pojo.FxMeta;
import com.modules.pojo.FxMetaDatas;
import com.modules.pojo.FxMethod;
import com.modules.pojo.FxTree;
import com.modules.pojo.FxTreeMeta;
import com.modules.pojo.FxTreeMetaDatas;
import com.modules.pojo.FxTreeMetaDatasMsg;
import com.modules.pojo.Store;
import com.modules.pojo.User;
import com.modules.pojo.UserStore;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxTreeMetaServiceImpl implements FxTreeMetaService {

	@Autowired
	private FxTreeDao fxTreeDao;

	@Autowired
	private FxTreeMetaDao fxTreeMetaDao;

	@Autowired
	private FxTreeMetaDatasDao fxTreeMetaDatasDao;

	@Autowired
	private FxMetaDao fxMetaDao;

	@Autowired
	private FxMetaDatasDao fxMetaDatasDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private CacheManager ehCacheManager;

	@Autowired
	private StoreService storeService;

	@Autowired
	private FxTreeMetaDatasMsgService fxTreeMetaDatasMsgService;

	private static String cacheName = "TreeMeta";

	public void save(FxTreeMeta entity) throws Exception {
		fxTreeMetaDao.save(entity);
	}

	public void save(List<FxTreeMeta> entities) throws Exception {
		fxTreeMetaDao.save(entities);
	}

	public void update(FxTreeMeta entity) throws Exception {
		fxTreeMetaDao.update(entity);
	}

	public void update(FxTreeMeta newEntity, FxTreeMeta oldEntity) throws Exception {
		fxTreeMetaDao.update(newEntity, oldEntity);
	}

	public void delete(FxTreeMeta entity) throws Exception {
		fxTreeMetaDao.delete(entity);
	}

	public FxTreeMeta findObject(FxTreeMeta entity) throws Exception {
		return fxTreeMetaDao.findObject(entity);
	}

	public List<FxTreeMeta> findList(FxTreeMeta entity) throws Exception {
		return fxTreeMetaDao.findList(entity);
	}

	public PageResult<FxTreeMeta> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree_meta where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeMetaDao.findList(sqlContext, page, FxTreeMeta.class);
	}

	public TreeMetaVo findByTreepathNCode(String treeId, String code, String userid, String storeid, String storetypeid) throws Exception {
		if (!ehCacheManager.cacheExists(cacheName)) {
			ehCacheManager.addCache(cacheName);
		}
		FxMeta fxMeta = new FxMeta();
		fxMeta.setCode(code);
		fxMeta = fxMetaDao.findObject(fxMeta);
		String metaid = fxMeta.getId();

		Cache cache = ehCacheManager.getCache(cacheName);
		Element e = cache.get(treeId + metaid + userid + storeid + storetypeid);
		if (e == null) {
			TreeMetaVo treeMetaVo = findByTreepath2(treeId, metaid, userid, storeid, storetypeid);
			cache.put(new Element(treeId + metaid + userid + storeid, treeMetaVo));
			return treeMetaVo;
		} else {
			return (TreeMetaVo) e.getObjectValue();
		}
	}

	public TreeMetaVo findByTreepath(String treeId, String metaid, String userid, String storeid, String storetypeid) throws Exception {
		if (!ehCacheManager.cacheExists(cacheName)) {
			ehCacheManager.addCache(cacheName);
		}
		Cache cache = ehCacheManager.getCache(cacheName);
		Element e = cache.get(treeId + metaid + userid + storeid + storetypeid);
		if (e == null) {
			TreeMetaVo treeMetaVo = findByTreepath2(treeId, metaid, userid, storeid, storetypeid);
			cache.put(new Element(treeId + metaid + userid + storeid, treeMetaVo));
			return treeMetaVo;
		} else {
			return (TreeMetaVo) e.getObjectValue();
		}
	}

	private TreeMetaVo findByTreepath2(String treeId, String metaid, String userid, String storeid, String storetypeid) throws Exception {
		User user = new User();
		user.setId(userid);
		user = userDao.findObject(user);

		Boolean isadmin = false;

		if (user.getUsername().equalsIgnoreCase(Constants.ADMIN)) {
			isadmin = true;

		}

		FxTree fxtree = new FxTree();
		fxtree.setId(treeId);
		fxtree = fxTreeDao.findObject(fxtree);
		TreeMetaVo treemetavo = new TreeMetaVo();
		FxTreeMeta fxTreeMeta = null;
		List<FxTreeMeta> fxTreeMetas = null;

		String[] treeids = fxtree.getPath().split("/");
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();

		if (StringUtils.isNotBlank(storeid) && isadmin == false) {

			sql.append("select * from fx_tree_meta where metaid=? and storetypeid=? and treeid in(''");
			ps.add(metaid);
			ps.add(storetypeid);
			for (String treeid : treeids) {
				sql.append(",'").append(treeid).append("'");
			}
			sql.append(")");

			sql.append(" and storeid =?");
			ps.add(storeid);

			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeMetas = fxTreeMetaDao.findList(sqlContext, FxTreeMeta.class);

			loop: for (int i = treeids.length - 1; i >= 0; i--) {
				for (FxTreeMeta ftm : fxTreeMetas) {
					if (i != treeids.length - 1) {
						if (ftm.getScope() == 1 && ftm.getTreeid().equals(treeids[i])) {
							fxTreeMeta = ftm;
							break loop;
						}
					} else {
						if (ftm.getTreeid().equals(treeids[i])) {
							fxTreeMeta = ftm;
							break loop;
						}
					}
				}
			}
		}
		if (fxTreeMeta == null && isadmin == false) {
			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append("select * from fx_tree_meta where treeid in(''");
			for (String treeid : treeids) {
				sql.append(",'").append(treeid).append("'");
			}
			sql.append(")");

			sql.append(" and userid =?");
			ps.add(userid);
			sql.append(" and metaid =?");
			ps.add(metaid);
			sql.append(" and storetypeid=?");
			ps.add(storetypeid);

			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeMetas = fxTreeMetaDao.findList(sqlContext, FxTreeMeta.class);

			loop: for (int i = treeids.length - 1; i >= 0; i--) {
				for (FxTreeMeta ftm : fxTreeMetas) {
					if (i != treeids.length - 1) {
						if (ftm.getScope() == 1 && ftm.getTreeid().equals(treeids[i])) {
							fxTreeMeta = ftm;
							break loop;
						}
					} else {
						if (ftm.getTreeid().equals(treeids[i])) {
							fxTreeMeta = ftm;
							break loop;
						}
					}
				}
			}
		}

		if (fxTreeMeta == null) {
			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append("select * from fx_tree_meta where treeid in(''");
			for (String treeid : treeids) {
				sql.append(",'").append(treeid).append("'");
			}
			sql.append(")");

			sql.append(" and standard =true");

			sql.append(" and metaid =?");
			ps.add(metaid);
			sql.append(" and storetypeid=?");
			ps.add(storetypeid);

			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeMetas = fxTreeMetaDao.findList(sqlContext, FxTreeMeta.class);

			loop: for (int i = treeids.length - 1; i >= 0; i--) {
				for (FxTreeMeta ftm : fxTreeMetas) {
					if (i != treeids.length - 1) {
						if (ftm.getScope() == 1 && ftm.getTreeid().equals(treeids[i])) {
							fxTreeMeta = ftm;
							break loop;
						}
					} else {
						if (ftm.getTreeid().equals(treeids[i])) {
							fxTreeMeta = ftm;
							break loop;
						}
					}
				}
			}
		}

		FxMeta fxMeta = new FxMeta();
		fxMeta.setId(metaid);
		fxMeta = fxMetaDao.findObject(fxMeta);

		treemetavo.setMetaname(fxMeta.getName());
		treemetavo.setCode(fxMeta.getCode());
		treemetavo.setMetaid(metaid);
		if (fxTreeMeta != null) {
			treemetavo.setScope(fxTreeMeta.getScope());
			treemetavo.setRemark(fxTreeMeta.getRemark());
		}
		FxMetaDatas fxMetaDatas = new FxMetaDatas();
		fxMetaDatas.setMetaid(metaid);
		List<FxMetaDatas> fmds = fxMetaDatasDao.findList(fxMetaDatas);

		List<TreeMetaDataVo> tdmvs = new ArrayList<TreeMetaDataVo>();
		treemetavo.setTreeMetaDatas(tdmvs);

		for (FxMetaDatas fmd : fmds) {
			TreeMetaDataVo tdmv = new TreeMetaDataVo();
			tdmv.setMetadataname(fmd.getName());
			tdmv.setCode(fmd.getCode()); 
			tdmv.setMetadataid(fmd.getId());
			tdmvs.add(tdmv);
		}

		if (fxTreeMeta != null) {
			FxTreeMetaDatas fxTreeMetaDatas = new FxTreeMetaDatas();
			fxTreeMetaDatas.setTreemetaid(fxTreeMeta.getId());
			List<FxTreeMetaDatas> ftmds = fxTreeMetaDatasDao.findList(fxTreeMetaDatas);

			if (fxTreeMeta.getTreeid().equals(treeId)) {
				treemetavo.setId(fxTreeMeta.getId());
			}

			for (TreeMetaDataVo tdmv : tdmvs) {
				loop: for (FxTreeMetaDatas ftmd : ftmds) {
					if (tdmv.getMetadataid().equals(ftmd.getMetadataid())) {
						tdmv.setVal(ftmd.getVal());

						FxTreeMetaDatasMsg fxTreeMetaDatasMsg = new FxTreeMetaDatasMsg();
						fxTreeMetaDatasMsg.setTreemetadatasid(ftmd.getId());

						// List<FxTreeMetaDatasMsg> msgs =
						// fxTreeMetaDatasMsgService.findList(fxTreeMetaDatasMsg);
						// List<TreeMetaDataMsgVo> msgvos = new ArrayList<TreeMetaDataMsgVo>();
						// for (FxTreeMetaDatasMsg msg : msgs) {
						// TreeMetaDataMsgVo msgvo = new TreeMetaDataMsgVo();
						// msgvo.setTreemetadatasid(ftmd.getId());
						// msgvo.setMsg(msg.getMsg());
						// msgvo.setCondition(msg.getCondition());
						// msgvo.setRemark(msg.getRemark());
						// msgvos.add(msgvo);
						// }
						// tdmv.setMsgs(msgvos);
						tdmv.setRemark(ftmd.getRemark());
						break loop;
					}
				}

			}
			return treemetavo;
		}
		return treemetavo;
	}

	@Override
	public void refreshCache(String companyid) throws Exception {
		// TODO Auto-generated method stub
		if (!ehCacheManager.cacheExists(cacheName)) {
			ehCacheManager.addCache(cacheName);
		}
		Cache cache = ehCacheManager.getCache(cacheName);
		Store us = new Store();
		us.setUserid(companyid);
		List<Store> userStores = storeService.findList(us);
		List<FxMeta> fxMetas = fxMetaDao.findList(new FxMeta());
		List<FxTree> fxTrees = fxTreeDao.findList(new FxTree());
		for (Store userStore : userStores) {
			for (FxTree tree : fxTrees) {
				if (tree.getPath().indexOf("654CA2E6C2164A148F287B57A4483AF7") > -1) {
					for (FxMeta meta : fxMetas) {
						TreeMetaVo treeMetaVo = this.findByTreepath2(tree.getId(), meta.getId(), companyid, userStore.getId(), userStore.getTypeid());
						cache.put(new Element(tree.getId() + meta.getId() + companyid + userStore.getId(), treeMetaVo));
					}
				}
			}
		}
	}

}
