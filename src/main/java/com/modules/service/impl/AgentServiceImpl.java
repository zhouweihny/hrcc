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
import com.modules.service.UserService;
import com.modules.dao.AgentDao;
import com.modules.dao.UserRoleDao;
import com.modules.pojo.Agent;
import com.modules.pojo.User;
import com.modules.pojo.UserRole;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentDao agentDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleDao userRoleDao;

	public void save(Agent entity) throws Exception {
		User user = new User();
		user.setMobile(entity.getMobileno());
		User tuser = userService.findObject(user);

		if (tuser == null) {
			List<String> roleids = new ArrayList<String>();
			roleids.add("D776C89900D74CACA93834FE68880540");
			user.setUsername(entity.getMobileno());
			user.setRoleids(roleids);
			user.setMobile(entity.getMobileno());
			userService.save(user);
			entity.setUserid(user.getId());
		} else {
			entity.setUserid(tuser.getId());
			if (tuser.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") < 0) {
				UserRole userRole = new UserRole();
				userRole.setRoleid("D776C89900D74CACA93834FE68880540");
				userRole.setUserid(tuser.getId());
				userRoleDao.save(userRole);
			}
		}
		agentDao.save(entity);
	}

	public void save(List<Agent> entities) throws Exception {
		agentDao.save(entities);
	}

	public void update(Agent entity) throws Exception {
		Agent a = new Agent();
		a.setId(entity.getId());
		a = agentDao.findObject(a);
		
		//如果手机号有变更
		if (!a.getMobileno().equals(entity.getMobileno())) {

			// 老手机号处理
			Agent ta = new Agent();
			ta.setMobileno(a.getMobileno());
			ta.setPurchaserid(a.getPurchaserid());
			a = agentDao.findObject(ta);

			ta = new Agent();
			ta.setUserid(a.getUserid());
			List<Agent> agents = agentDao.findList(ta);
			if (agents.size() == 1) {
				User user = new User();
				user.setId(a.getUserid());
				user = userService.findObject(user);
				if (user.getRoleids().size() == 1) {
					User tuser = new User();
					tuser.setId(user.getId());
					userService.delete(user);
				} else {
					UserRole userRole = new UserRole();
					userRole.setRoleid("D776C89900D74CACA93834FE68880540");
					userRole.setUserid(user.getId());
					userRoleDao.delete(userRole);
				}
			}

			// 新手机号处理
			User user = new User();
			user.setMobile(entity.getMobileno());
			User tuser = userService.findObject(user);

			if (tuser == null) {
				List<String> roleids = new ArrayList<String>();
				roleids.add("D776C89900D74CACA93834FE68880540");
				user.setUsername(entity.getMobileno());
				user.setRoleids(roleids);
				user.setMobile(entity.getMobileno());
				userService.save(user);
				entity.setUserid(user.getId());
			} else {
				entity.setUserid(tuser.getId());
				if (tuser.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") < 0) {
					UserRole userRole = new UserRole();
					userRole.setRoleid("D776C89900D74CACA93834FE68880540");
					userRole.setUserid(tuser.getId());
					userRoleDao.save(userRole);
				}
			}

		}
		agentDao.update(entity);
	}

	public void update(Agent newEntity, Agent oldEntity) throws Exception {
		agentDao.update(newEntity, oldEntity);
	}

	public void delete(Agent entity) throws Exception {
		Agent a = new Agent();
		a.setId(entity.getId());
		a = agentDao.findObject(a);
		if (a != null) {
			Agent ta = new Agent();
			ta.setUserid(a.getUserid());
			List<Agent> agents = agentDao.findList(ta);
			if (agents.size() == 1) {
				User user = new User();
				user.setId(a.getUserid());
				user = userService.findObject(user);
				if (user.getRoleids().size() == 1) {
					User tuser = new User();
					tuser.setId(user.getId());
					userService.delete(user);
				} else {
					UserRole userRole = new UserRole();
					userRole.setRoleid("D776C89900D74CACA93834FE68880540");
					userRole.setUserid(user.getId());
					userRoleDao.delete(userRole);
				}
			}
			agentDao.delete(entity);
		}
	}

	public Agent findObject(Agent entity) throws Exception {
		return agentDao.findObject(entity);
	}

	public List<Agent> findList(Agent entity) throws Exception {
		return agentDao.findList(entity);
	}

	public PageResult<Agent> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_agent where 1=1 ");
		if (params.containsKey("keywords")) {
			sql.append(" and mobileno like ? or name like ?");
			ps.add("%" + params.get("keywords") + "%");
			ps.add("%" + params.get("keywords") + "%");
		}
		
		
		if (params.containsKey("name")) {
			sql.append(" and name like ?");
			ps.add("%" + params.get("name") + "%");
		}
		
		if (params.containsKey("keywords")) {
			sql.append(" and mobileno like ? ");
			ps.add("%" + params.get("mobileno") + "%");
		}
		
		if (params.containsKey("factory")) {
			sql.append(" and factory like ? ");
			ps.add("%" + params.get("factory") + "%");
		}

		if (params.containsKey("purchaserid")) {
			sql.append(" and purchaserid = ?  ");
			ps.add(params.get("purchaserid"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return agentDao.findList(sqlContext, page, Agent.class);
	}

}
