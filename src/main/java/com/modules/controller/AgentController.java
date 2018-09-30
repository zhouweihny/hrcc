package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.AgentService;
import com.modules.pojo.Agent;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("agent")
public class AgentController extends BaseController {

	private static Logger logger = Logger.getLogger(AgentController.class);

	@Autowired
	private AgentService agentService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Agent agent, HttpServletRequest request, HttpServletResponse response) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			if (StringUtils.isEmpty(agent.getId())) {
				Agent ta = new Agent();
				ta.setPurchaserid(company.getId());
				ta.setMobileno(agent.getMobileno());
				ta = agentService.findObject(ta);
				if (ta == null) {
					agent.setPurchaserid(company.getId());
					agentService.save(agent);
				} else {
					return this.getJsonResult("9999", "手机号已被使用");
				}
			} else {
				agentService.update(agent);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Agent agent) {
		try {
			agentService.delete(agent);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Agent agent) {
		ModelAndView modelAndView = new ModelAndView("agent/info");
		try {
			if (!StringUtils.isEmpty(agent.getId())) {
				Agent data = agentService.findObject(agent);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Agent agent) {
		try {
			Agent data = agentService.findObject(agent);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("agent/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response,String mobileno,String name,String factory, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("agent/table");
		try {
			User user = (User) session.getAttribute(Constants.USER);
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}

			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			params.put("purchaserid", userid);
			
			if (!StringUtils.isEmpty(mobileno)) {
				params.put("mobileno", mobileno);

			}
			if (!StringUtils.isEmpty(name) ) {
				params.put("name", name);
			}
			if (!StringUtils.isEmpty(factory) ) {
				params.put("factory", factory);
			}
			

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Agent> data = agentService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "agents")
	@ResponseBody
	public Object drugs(HttpServletRequest request, HttpServletResponse response, String keywords) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(1, 200);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			params.put("purchaserid", userid);
			if (!StringUtils.isEmpty(keywords)) {
				params.put("keywords", keywords);
			}
			PageResult<Agent> data = agentService.findList(params, page);
			return data;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}