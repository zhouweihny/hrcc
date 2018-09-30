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
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.UUIDGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modules.service.FxLhyydcService;
import com.modules.service.SchemeComnameService;
import com.modules.service.SchemeService;
import com.modules.vo.SchemeVo;
import com.modules.pojo.FxLhyydc;
import com.modules.pojo.Scheme;
import com.modules.pojo.SchemeComname;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxlhyydc")
public class FxLhyydcController extends BaseController {

	private static Logger logger = Logger.getLogger(FxLhyydcController.class);

	@Autowired
	private FxLhyydcService fxLhyydcService;

	@Autowired
	private SchemeService schemeService;

	@Autowired
	private SchemeComnameService schemeComnameService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxLhyydc fxLhyydc, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxLhyydc.getId())) {
				fxLhyydcService.save(fxLhyydc);
			} else {
				fxLhyydcService.update(fxLhyydc);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxLhyydc fxLhyydc) {
		try {
			fxLhyydcService.delete(fxLhyydc);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxLhyydc fxLhyydc) {
		ModelAndView modelAndView = new ModelAndView("fxlhyydc/info");
		try {
			if (!StringUtils.isEmpty(fxLhyydc.getId())) {
				FxLhyydc data = fxLhyydcService.findObject(fxLhyydc);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxLhyydc fxLhyydc) {
		try {
			FxLhyydc data = fxLhyydcService.findObject(fxLhyydc);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxlhyydc/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield,
			Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxlhyydc/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxLhyydc> data = fxLhyydcService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "zlist", method = RequestMethod.GET)
	public ModelAndView zlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxlhyydc/zlist");
		return modelAndView;
	}

	@RequestMapping(value = "savelhyy", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(String jsonstr) {
		try {
			Gson gson = new GsonBuilder().create();
			SchemeVo schemeVo = gson.fromJson(jsonstr.toLowerCase(), SchemeVo.class);
			Integer i = 1;
			for (String comnamestr : schemeVo.getComnames()) {
				String[] comnames = comnamestr.split(",");
				Scheme scheme = new Scheme();
				scheme.setId(UUIDGenerator.getUUID());
				scheme.setName(schemeVo.getName() + i);
				scheme.setTreeid(schemeVo.getTreeid());
				List<SchemeComname> schemeComnames = new ArrayList<SchemeComname>();
				for (String comanmeid : comnames) {
					SchemeComname schemeComname = new SchemeComname();
					schemeComname.setComnameid(comanmeid);
					schemeComname.setSchemeid(scheme.getId());
					schemeComnames.add(schemeComname);
				}
				schemeService.save(scheme);
				schemeComnameService.save(schemeComnames);
			}
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}