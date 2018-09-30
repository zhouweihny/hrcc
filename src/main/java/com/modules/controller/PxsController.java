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
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.PxsService;
import com.modules.pojo.Pxs;
import com.modules.pojo.StoreDy;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("pxs")
public class PxsController extends BaseController {

	private static Logger logger = Logger.getLogger(PxsController.class);

	@Autowired
	private PxsService pxsService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Pxs pxs, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(pxs.getId())) {
				pxsService.save(pxs);
			} else {
				pxsService.update(pxs);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Pxs pxs) {
		try {
			pxsService.delete(pxs);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Pxs pxs) {
		ModelAndView modelAndView = new ModelAndView("pxs/info");
		try {
	  		if (!StringUtils.isEmpty(pxs.getId())) {
				Pxs data = pxsService.findObject(pxs);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Pxs pxs) {
		try {
			Pxs data = pxsService.findObject(pxs);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("pxs/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("pxs/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Pxs> data = pxsService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}
	
	@RequestMapping(value = "pxses")
	@ResponseBody
	public Object drugs(HttpServletRequest request, HttpServletResponse response) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Pxs pxs= new Pxs();
			
			List<Pxs> data = pxsService.findList(pxs);
			return data;
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}