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
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.FxDrugStoreService;
import com.modules.pojo.FxDrugStore;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxdrugstore")
public class FxDrugStoreController extends BaseController {

	private static Logger logger = Logger.getLogger(FxDrugStoreController.class);

	@Autowired
	private FxDrugStoreService fxDrugStoreService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxDrugStore fxDrugStore, HttpServletRequest request, HttpServletResponse response) {
		try {
			FxDrugStore tfxDrugStore = fxDrugStoreService.findObject(fxDrugStore);

			if (tfxDrugStore == null) {
				fxDrugStore.setStatus(false);
				fxDrugStoreService.save(fxDrugStore);
			} else {
				tfxDrugStore.setStatus(false);
				fxDrugStoreService.update(fxDrugStore);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxDrugStore fxDrugStore) {
		try {
			if (fxDrugStore.getStatus() != null && fxDrugStore.getStatus()) {
				fxDrugStore.setStatus(true);
				fxDrugStoreService.update(fxDrugStore);
			} else {
				fxDrugStoreService.delete(fxDrugStore);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxDrugStore fxDrugStore) {
		ModelAndView modelAndView = new ModelAndView("fxdrugstore/info");
		try {
			if (!StringUtils.isEmpty(fxDrugStore.getId())) {
				FxDrugStore data = fxDrugStoreService.findObject(fxDrugStore);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxDrugStore fxDrugStore) {
		try {
			FxDrugStore data = fxDrugStoreService.findObject(fxDrugStore);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxdrugstore/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxdrugstore/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxDrugStore> data = fxDrugStoreService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}