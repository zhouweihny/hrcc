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
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.excel.ImportExcel2ListUtil;
import com.modules.service.DrugwordsService;
import com.modules.pojo.Drugwords;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("drugwords")
public class DrugwordsController extends BaseController {

	private static Logger logger = Logger.getLogger(DrugwordsController.class);

	@Autowired
	private DrugwordsService drugwordsService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Drugwords drugwords, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			if (StringUtils.isEmpty(drugwords.getId())) {
				drugwords.setOperatorid(user.getId());
				drugwordsService.save(drugwords);
			} else {
				drugwords.setOperatorid(user.getId());
				drugwordsService.update(drugwords);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Drugwords drugwords) {
		try {
			if (!StringUtils.isEmpty(drugwords.getId()))
				drugwordsService.delete(drugwords);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Drugwords drugwords) {
		ModelAndView modelAndView = new ModelAndView("drugwords/info");
		try {
			if (!StringUtils.isEmpty(drugwords.getId())) {
				Drugwords data = drugwordsService.findObject(drugwords);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Drugwords drugwords) {
		try {
			Drugwords data = drugwordsService.findObject(drugwords);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("drugwords/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String code, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("drugwords/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(code)) {
				params.put("code", code);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Drugwords> data = drugwordsService.findList(params, page);
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
		User user = (User) session.getAttribute(Constants.USER);
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
				drugwordsService.upload(user.getId(),l);
			}
			drugwordsService.removeRepeat();
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}

		return this.getJsonResult("0000", "上传成功");

	}
}