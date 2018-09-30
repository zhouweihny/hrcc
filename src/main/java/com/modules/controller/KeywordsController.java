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
import com.commons.util.KeyWordsUtil;
import com.commons.util.excel.ImportExcel2ListUtil;
import com.modules.service.KeywordsService;
import com.modules.pojo.Keywords;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("keywords")
public class KeywordsController extends BaseController {

	private static Logger logger = Logger.getLogger(KeywordsController.class);

	@Autowired
	private KeywordsService keywordsService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Keywords keywords, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			keywords.setWord(keywords.getWord().trim());
			if (StringUtils.isEmpty(keywords.getId())) {
				keywords.setOperatorid(user.getId());
				keywordsService.save(keywords);
			} else {
				keywords.setOperatorid(user.getId());
				keywordsService.update(keywords);
			}
			keywordsService.refreshIK();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Keywords keywords) {
		try {
			if (!StringUtils.isEmpty(keywords.getId()) || !StringUtils.isEmpty(keywords.getWord())) {
				if (!StringUtils.isEmpty(keywords.getWord()))
					keywords.setWord(keywords.getWord().trim());
				keywordsService.delete(keywords);
				keywordsService.refreshIK();
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Keywords keywords) {
		ModelAndView modelAndView = new ModelAndView("keywords/info");
		try {
			if (!StringUtils.isEmpty(keywords.getId())) {
				Keywords data = keywordsService.findObject(keywords);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Keywords keywords) {
		try {
			Keywords data = keywordsService.findObject(keywords);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("keywords/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String word, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("keywords/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(word)) {
				params.put("word", word);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Keywords> data = keywordsService.findList(params, page);
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
				if (list.size() > 0)
					keywordsService.upload(user.getId(), l);
			}
			keywordsService.removeRepeat();
			keywordsService.refreshIK();
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}

		return this.getJsonResult("0000", "上传成功");

	}

	@RequestMapping("scankeywords")
	@ResponseBody
	public Object scankeywords(String word, Boolean smart) {
		try {
			if (StringUtils.isEmpty(smart))
				smart = false;
			return this.getJsonResult(KeyWordsUtil.segDrugname(word, smart));
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
}