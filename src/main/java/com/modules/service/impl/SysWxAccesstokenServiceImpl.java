package com.modules.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.res.AccessTokenRequest;
import com.commons.util.wx.bean.res.AccessTokenResponse;
import com.modules.service.SysWxAccesstokenService;
import com.modules.dao.SysWxAccesstokenDao;
import com.modules.dao.SysWxDao;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxAccesstokenServiceImpl implements SysWxAccesstokenService {

	@Autowired
	private SysWxAccesstokenDao sysWxAccesstokenDao;

	@Autowired
	private SysWxDao sysWxDao;

	public void save(SysWxAccesstoken entity) throws Exception {
		sysWxAccesstokenDao.save(entity);
	}

	public void save(List<SysWxAccesstoken> entities) throws Exception {
		sysWxAccesstokenDao.save(entities);
	}

	public void update(SysWxAccesstoken entity) throws Exception {
		sysWxAccesstokenDao.update(entity);
	}

	public void update(SysWxAccesstoken newEntity, SysWxAccesstoken oldEntity) throws Exception {
		sysWxAccesstokenDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxAccesstoken entity) throws Exception {
		sysWxAccesstokenDao.delete(entity);
	}

	public SysWxAccesstoken findObject(SysWxAccesstoken entity) throws Exception {
		return sysWxAccesstokenDao.findObject(entity);
	}

	public List<SysWxAccesstoken> findList(SysWxAccesstoken entity) throws Exception {
		return sysWxAccesstokenDao.findList(entity);
	}

	public PageResult<SysWxAccesstoken> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_accesstoken where 1=1 ");
		if (params.containsKey("wxid")) {
			sql.append(" and wxid = ?");
			ps.add(params.get("wxid"));
		}
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxAccesstokenDao.findList(sqlContext, page, SysWxAccesstoken.class);
	}

	@Override
	public void refresh(SysWx entity) throws Exception {
		List<SysWx> wxs = sysWxDao.findList(entity);
		for (SysWx wx : wxs) {
			AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
			accessTokenRequest.setAppid(wx.getAppid());
			accessTokenRequest.setSecret(wx.getAppsecret());
			AccessTokenResponse accessTokenResponse = WxUtil.getAccessToken(accessTokenRequest);
			if (accessTokenResponse != null && accessTokenResponse.errcode == 0) {
				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(wx.getId());
				sysWxAccesstokenDao.delete(sysWxAccesstoken);
				sysWxAccesstoken.setAccesstoken(accessTokenResponse.access_token);
				sysWxAccesstokenDao.save(sysWxAccesstoken);
			} else {
				throw new Exception(accessTokenResponse.getErrmsg());
			}
		}
	}
}
