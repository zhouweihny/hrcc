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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.excel.ImportExcel2ListUtil;
import com.modules.service.DosageformService;
import com.modules.pojo.Dosageform;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("dosageform")
public class DosageformController extends BaseController {

	private static Logger logger = Logger.getLogger(DosageformController.class);

	@Autowired
	private DosageformService dosageformService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Dosageform dosageform, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(dosageform.getId())) {
				dosageformService.save(dosageform);
			} else {
				dosageformService.update(dosageform);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Dosageform dosageform) {
		try {
			if (!StringUtils.isEmpty(dosageform.getId()))
				dosageformService.delete(dosageform);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Dosageform dosageform) {
		ModelAndView modelAndView = new ModelAndView("dosageform/info");
		try {
			if (!StringUtils.isEmpty(dosageform.getId())) {
				Dosageform data = dosageformService.findObject(dosageform);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Dosageform dosageform) {
		try {
			Dosageform data = dosageformService.findObject(dosageform);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("dosageform/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("dosageform/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Dosageform> data = dosageformService.findList(params, page);
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
		InputStream xls;
		try {
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<List<String>> list;
			if (name.toLowerCase().endsWith("xlsx")) {
				list = ImportExcel2ListUtil.importExcel2007(xls);
			} else {
				list = ImportExcel2ListUtil.importExcel2003(xls);
			}
			for (List<String> l : list) {
				dosageformService.upload(l);
			}
			dosageformService.removeRepeat();
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}

		return this.getJsonResult("0000", "上传成功");

	}

}