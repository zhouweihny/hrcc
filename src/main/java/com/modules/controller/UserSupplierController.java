package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.excel.ImportExcelUtil;
import com.modules.service.UserService;
import com.modules.service.UserSupplierService;
import com.modules.vo.DrugSupplierExcelVo;
import com.modules.vo.UserVo;
import com.modules.pojo.User;
import com.modules.pojo.UserSupplier;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("usersupplier")
public class UserSupplierController extends BaseController {

	private static Logger logger = Logger.getLogger(UserSupplierController.class);

	@Autowired
	private UserSupplierService userSupplierService;

	@Autowired
	private UserService userService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(UserSupplier userSupplier, HttpServletRequest request, HttpServletResponse response) {
		User u = (User) session.getAttribute(Constants.COMPANY);
		try {
			if (StringUtils.isEmpty(userSupplier.getId())) {
				userSupplier.setUserid(u.getId());
				userSupplier.setSend(false);
				userSupplierService.save(userSupplier);

			} else {

				userSupplierService.update(userSupplier);

			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(UserSupplier userSupplier) {
		try {
			User u = (User) session.getAttribute(Constants.COMPANY);
			if (org.apache.commons.lang3.StringUtils.isNotBlank(userSupplier.getId()))
				userSupplierService.delete(userSupplier);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(UserSupplier userSupplier) {
		ModelAndView modelAndView = new ModelAndView("usersupplier/info");
		try {
			if (!StringUtils.isEmpty(userSupplier.getId())) {
				UserSupplier data = userSupplierService.findObject(userSupplier);
				modelAndView.addObject("data", data);
				if (!StringUtils.isEmpty(data.getContactid())) {
					User user = new User();
					user.setId(data.getContactid());
					user = userService.findObject(user);

				}
			} else {
				modelAndView.addObject("supplierid", userSupplier.getSupplierid());

			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(UserSupplier userSupplier) {
		try {
			UserSupplier data = userSupplierService.findObject(userSupplier);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("usersupplier/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String used, String keyword, String status, String erp, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("usersupplier/table");
		try {
			User u = (User) session.getAttribute(Constants.COMPANY);
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(keyword)) {
				params.put("keyword", keyword);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			if (!StringUtils.isEmpty(status)) {
				params.put("status", status);
			}
			if (!StringUtils.isEmpty(used)) {
				params.put("used", used);
			}
			params.put("userid", u.getId());
			params.put("roleid", "B1CC34941707470D9BF361D1CEF2B08E");
			PageResult<UserSupplier> data = userSupplierService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "usedsuppliers")
	@ResponseBody
	public Object usedsuppliers(HttpServletRequest request, HttpServletResponse response) {
		try {

			User u = (User) session.getAttribute(Constants.COMPANY);
			Page page = new Page(false);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("used", "1");
			params.put("userid", u.getId());
			params.put("roleid", "B1CC34941707470D9BF361D1CEF2B08E");
			PageResult<UserSupplier> data = userSupplierService.findList(params, page);

			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "mysuppliers")
	@ResponseBody
	public Object mysuppliers(HttpServletRequest request, HttpServletResponse response, String keyword) {
		try {
			User u = (User) session.getAttribute(Constants.COMPANY);
			Page page = new Page(false);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			params.put("userid", u.getId());
			params.put("keyword", keyword);
			PageResult<UserSupplier> data = userSupplierService.findList(params, page);
			return data;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "suppliers")
	@ResponseBody
	public Object suppliers(HttpServletRequest request, HttpServletResponse response, String keyword) {
		try {
			User u = (User) session.getAttribute(Constants.COMPANY);
			PageResult<User> data = userSupplierService.findSupplierList(keyword);
			return data;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
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
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<UserSupplier> userSupplierExcelVos;
			if (name.toLowerCase().endsWith("xlsx")) {
				userSupplierExcelVos = ImportExcelUtil.importExcel2007(UserSupplier.class, xls);
			} else {
				userSupplierExcelVos = ImportExcelUtil.importExcel2003(UserSupplier.class, xls);
			}
			userSupplierService.upload(user.getId(), userSupplierExcelVos);
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

}