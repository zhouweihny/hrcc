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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.StoreService;
import com.modules.service.UserStoreService;
import com.modules.pojo.Store;
import com.modules.pojo.User;
import com.modules.pojo.UserStore;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("userstore")
public class UserStoreController extends BaseController {

	private static Logger logger = Logger.getLogger(UserStoreController.class);

	@Autowired
	private UserStoreService userStoreService;
	@Autowired
	private StoreService storeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(UserStore userStore, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(userStore.getId())) {
				userStoreService.save(userStore);
			} else {
				userStoreService.update(userStore);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(UserStore userStore) {
		try {
			userStoreService.delete(userStore);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(UserStore userStore) {
		ModelAndView modelAndView = new ModelAndView("userstore/info");
		try {
			if (!StringUtils.isEmpty(userStore.getId())) {
				UserStore data = userStoreService.findObject(userStore);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(UserStore userStore) {
		try {
			UserStore data = userStoreService.findObject(userStore);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("userstore/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("userstore/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<UserStore> data = userStoreService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "adduserstore")
	@ResponseBody
	public Object pbyid(String userid, String storeids) {
		try {
			UserStore userStore = new UserStore();
			userStore.setUserid(userid);
			userStoreService.delete(userStore);
			List<UserStore> uses = new ArrayList<UserStore>();
			for (String storeid : storeids.split(",")) {
				UserStore us = new UserStore();
				us.setUserid(userid);
				us.setStoreid(storeid);
				uses.add(us);
			}
			userStoreService.save(uses);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "storesbyuserid")
	@ResponseBody
	public Object storesbyuserid(String userid) {
		User company = (User) this.session.getAttribute(Constants.COMPANY);
		try {
			UserStore userStore = new UserStore();
			userStore.setUserid(userid);
			List<UserStore> storec = userStoreService.findList(userStore);

			Store store = new Store();
			store.setUserid(company.getId());
			List<Store> stores = storeService.findList(store);

			for (Store s : stores) {
				for (UserStore us : storec) {
					if (us.getStoreid().equals(s.getId())) {
						s.setDisabled(true);
						break;
					}
				}
			}

			return this.getJsonResult(stores);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}