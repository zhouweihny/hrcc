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
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.UUIDGenerator;
import com.commons.util.excel.ImportExcel2ListUtil;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.FactoryService;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.Factory;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("factory")
public class FactoryController extends BaseController {

	private static Logger logger = Logger.getLogger(FactoryController.class);

	@Autowired
	private FactoryService factoryService;

	@Autowired
	private DrugService drugService;

	@Autowired
	private CatalogService catalogService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Factory factory, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			if (StringUtils.isEmpty(factory.getId())) {
				factory.setOperatorid(user.getId());
				if (StringUtils.isEmpty(factory.getCode()))
					factory.setCode(UUIDGenerator.getUUID());
				factoryService.save(factory);
			} else {
				factory.setOperatorid(user.getId());
				factoryService.update(factory);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("add")
	@ResponseBody
	public Object add(String drugid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Drug drug = new Drug();
			drug.setId(drugid);
			drug = drugService.findObject(drug);
			Factory factory = new Factory();
			factory.setName(drug.getFactory());
			factory.setOperatorid(user.getId());
			if (StringUtils.isEmpty(factory.getCode()))
				factory.setCode(UUIDGenerator.getUUID());
			factoryService.save(factory);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Factory factory) {
		try {
			if (!StringUtils.isEmpty(factory.getId()))
				factoryService.delete(factory);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Factory factory) {
		ModelAndView modelAndView = new ModelAndView("factory/info");
		try {
			if (!StringUtils.isEmpty(factory.getId())) {
				Factory data = factoryService.findObject(factory);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "merge", method = RequestMethod.POST)
	@ResponseBody
	public Object merge(String ids) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			String[] list = ids.split(",");
			factoryService.merge(user.getId(), Arrays.asList(list));
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Factory factory) {
		try {

			Factory data = factoryService.findObject(factory);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("factory/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String code, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("factory/table");
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
			PageResult<Factory> data = factoryService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "glist")
	@ResponseBody
	public Object glist(HttpServletRequest request, HttpServletResponse response, String name, String code, String stype, String sfield, Integer currentPage, Integer pageSize) {
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
			PageResult<Factory> data = factoryService.findList(params, page);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

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
				factoryService.upload(user.getId(), l);
			}
			factoryService.removeRepeat();
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}

		return this.getJsonResult("0000", "上传成功");

	}

	@RequestMapping(value = "exportfile")
	public void export(HttpServletRequest request, HttpServletResponse response, String spaceid, String catalogid) {
		try {
			response.reset();
			response.setContentType("application/csv;charset=GBK");
			response.setHeader("Content-Disposition", "attachment;filename=Data" + System.currentTimeMillis() + ".csv");
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			for (int i = 1; i <= 9999; i++) {
				PageResult<Factory> data = factoryService.findFactoryCode(new Page(i, 2000, false));
				if (data.getData().size() == 0)
					break;
				for (Factory v : data.getData()) {
					String[] words = v.getName().split(",");
					StringBuilder str = new StringBuilder();
					for (String word : words) {
						str.append("\"").append(word).append("\",");
					}
					out.println(str.toString().replaceAll("null", ""));
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping(value = "exportnifactory")
	public void exportfile(HttpServletRequest request, HttpServletResponse response, String spaceid, String catalogid) {
		try {
			response.reset();
			response.setContentType("application/csv;charset=GBK");
			response.setHeader("Content-Disposition", "attachment;filename=Data" + System.currentTimeMillis() + ".csv");
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			for (int i = 1; i <= 9999; i++) {
				PageResult<Factory> data = factoryService.findNIFactory(new Page(i, 2000, false));
				if (data.getData().size() == 0)
					break;
				for (Factory v : data.getData()) {
					StringBuilder str = new StringBuilder();
					str.append("\"").append(v.getName()).append("\",");
					out.println(str.toString().replaceAll("null", ""));
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping(value = "nlist")
	public ModelAndView nglist(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		String userid = user.getParentid();
		ModelAndView modelAndView = new ModelAndView("factory/nlist");
		Catalog catalog = new Catalog();
		catalog.setUserid(userid);
		try {
			List<Catalog> catalogs = catalogService.findList(catalog);
			modelAndView.addObject("catalogs", catalogs);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "ntable")
	public ModelAndView nplist(HttpServletRequest request, HttpServletResponse response, String name, String catalogid, String factory, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("factory/ntable");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			if (!StringUtils.isEmpty(catalogid)) {
				params.put("catalogid", catalogid);
			}
			if (!StringUtils.isEmpty(factory)) {
				params.put("factory", factory);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Drug> data = drugService.findnList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}