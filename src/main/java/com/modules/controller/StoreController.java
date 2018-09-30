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
import com.modules.service.StoreService;
import com.modules.pojo.Store;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("store")
public class StoreController extends BaseController {

	private static Logger logger = Logger.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Store store, HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = (User) session.getAttribute(Constants.USER);
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}

			if (StringUtils.isEmpty(store.getId())) {
				Store tStore = new Store();
				tStore.setStorecode(store.getStorecode());
				tStore.setUserid(userid);
				tStore = storeService.findObject(tStore);
				if (tStore != null) {
					return this.getJsonResult("9999", "该门店编码已存在!");
				}
				store.setUserid(user.getId());
				storeService.save(store);
			} else {
				storeService.update(store);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Store store) {
		try {
			storeService.delete(store);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Store store) {
		ModelAndView modelAndView = new ModelAndView("store/info");
		try {
			if (!StringUtils.isEmpty(store.getId())) {
				Store data = storeService.findObject(store);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Store store) {
		try {
			Store data = storeService.findObject(store);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("store/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String storecode, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("store/table");
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			params.put("companyid", company.getId());
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(storecode)) {
				params.put("storecode", storecode);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			PageResult<Store> data = storeService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "stores")
	@ResponseBody
	public Object drugs(HttpServletRequest request, HttpServletResponse response, String keywords) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(1, 200);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置

			if (!StringUtils.isEmpty(keywords)) {
				params.put("keywords", keywords);
			}
			params.put("companyid", company.getId());
			params.put("userid", user.getId());
			PageResult<Store> data = storeService.findList(params, page);
			return data;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}