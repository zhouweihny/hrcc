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
import com.modules.service.DrugSupplierService;
import com.modules.vo.DrugSupplierExcelVo;
import com.modules.vo.DrugSupplierVo;
import com.modules.pojo.DrugSupplier;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("drugsupplier")
public class DrugSupplierController extends BaseController {

	private static Logger logger = Logger.getLogger(DrugSupplierController.class);

	@Autowired
	private DrugSupplierService drugSupplierService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(DrugSupplier drugSupplier, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(drugSupplier.getId())) {
				User company = (User) session.getAttribute(Constants.COMPANY);
				drugSupplier.setPurchaserid(company.getId());
				drugSupplierService.save(drugSupplier);
			} else {
				drugSupplierService.update(drugSupplier);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(DrugSupplier drugSupplier) {
		try {
			drugSupplierService.delete(drugSupplier);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(DrugSupplier drugSupplier) {
		ModelAndView modelAndView = new ModelAndView("drugsupplier/info");
		try {
			if (!StringUtils.isEmpty(drugSupplier.getId())) {
				DrugSupplier data = drugSupplierService.findObject(drugSupplier);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(DrugSupplier drugSupplier) {
		try {
			DrugSupplier data = drugSupplierService.findObject(drugSupplier);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("drugsupplier/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String company, String agent, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("drugsupplier/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			User user = (User) session.getAttribute(Constants.COMPANY);
			params.put("purchaserid", user.getId());
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			if (!StringUtils.isEmpty(company)) {
				params.put("company", company);
			}

			if (!StringUtils.isEmpty(agent)) {
				params.put("agent", agent);
			}

			PageResult<DrugSupplier> data = drugSupplierService.findList(params, page);
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
	public Object uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile, String supplierid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		InputStream xls;
		try {
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<DrugSupplierExcelVo> drugSupplierExcelVos;
			if (name.toLowerCase().endsWith("xlsx")) {
				drugSupplierExcelVos = ImportExcelUtil.importExcel2007(DrugSupplierExcelVo.class, xls);
			} else {
				drugSupplierExcelVos = ImportExcelUtil.importExcel2003(DrugSupplierExcelVo.class, xls);
			}
			drugSupplierService.upload(supplierid, user.getId(), drugSupplierExcelVos);
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

}