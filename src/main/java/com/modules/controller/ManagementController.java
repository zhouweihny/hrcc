package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.excel.ImportExcelUtil;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.vo.GyDrugExcel;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("management")
public class ManagementController extends BaseController {

	private static Logger logger = Logger.getLogger(ManagementController.class);

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private DrugService drugService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("management/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String treeid, String name, String dz, String factory, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		ModelAndView modelAndView = new ModelAndView("management/table");
		try {

			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			String userid = user.getId();

			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				catalog = new Catalog();
				catalog.setName("经营品种");
				catalog.setMyself(true);
				catalog.setRemark("经营品种");
				catalog.setUserid(userid);
				catalogService.save(catalog);
			}
			params.put("catalogid", catalog.getId());
			params.put("userid", userid);
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(factory)) {
				params.put("factory", factory);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			if (!StringUtils.isEmpty(dz)) {
				params.put("dz", dz);
			}

			if (!StringUtils.isEmpty(treeid)) {
				params.put("treeid", treeid);
			}

			PageResult<Drug> data = drugService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	/**
	 * excel导入目录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/uploadxls")
	@ResponseBody
	public Object uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		InputStream xls;
		try {

			String userid = user.getId();
			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				catalog = new Catalog();
				catalog.setName("经营品种");
				catalog.setMyself(true);
				catalog.setUserid(userid);
				catalog.setRemark("经营品种");
				catalogService.save(catalog);
			}
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<Drug> drugs;
			if (name.toLowerCase().endsWith("xlsx")) {
				drugs = ImportExcelUtil.importExcel2007(Drug.class, xls);
			} else {
				drugs = ImportExcelUtil.importExcel2003(Drug.class, xls);
			}
			catalogService.upload(catalog.getId(), drugs);
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

	@RequestMapping(value = "gylist")
	public ModelAndView gylist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("management/gylist");
		return modelAndView;
	}

	@RequestMapping(value = "gytable")
	public ModelAndView gyplist(HttpServletRequest request, HttpServletResponse response, String treeid, String name, String dz, String factory, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		ModelAndView modelAndView = new ModelAndView("management/gytable");
		try {

			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			String userid = user.getId();

			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				catalog = new Catalog();
				catalog.setName("经营品种");
				catalog.setMyself(true);
				catalog.setRemark("经营品种");
				catalog.setUserid(userid);
				catalogService.save(catalog);
			}
			params.put("catalogid", catalog.getId());
			params.put("userid", userid);
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(factory)) {
				params.put("factory", factory);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			if (!StringUtils.isEmpty(dz)) {
				params.put("dz", dz);
			}

			if (!StringUtils.isEmpty(treeid)) {
				params.put("treeid", treeid);
			}

			PageResult<Drug> data = drugService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	/**
	 * excel导入目录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gyupload")
	@ResponseBody
	public Object gyupload(@RequestParam("uploadfile") MultipartFile uploadfile, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		InputStream xls;
		try {

			String userid = user.getId();
			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				catalog = new Catalog();
				catalog.setName("经营品种");
				catalog.setMyself(true);
				catalog.setUserid(userid);
				catalog.setRemark("经营品种");
				catalogService.save(catalog);
			}
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<GyDrugExcel> drugExcels;
			if (name.toLowerCase().endsWith("xlsx")) {
				drugExcels = ImportExcelUtil.importExcel2007(GyDrugExcel.class, xls);
			} else {
				drugExcels = ImportExcelUtil.importExcel2003(GyDrugExcel.class, xls);
			}
			List<Drug> drugs = new ArrayList<Drug>();

			for (GyDrugExcel de : drugExcels) {
				drugs.add(dozerBeanMapper.map(de, Drug.class));
			}

			catalogService.upload(catalog.getId(), drugs);
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

}