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
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.excel.ImportExcelUtil;
import com.modules.service.PurchaseDetailService;
import com.modules.service.SupplierPurchaseService;
import com.modules.vo.PurchaseDetailExcelVo;
import com.modules.vo.PurchaseDetailVo;
import com.modules.pojo.PlanDetail;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.SupplierPurchase;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("supplierpurchase")
public class SupplierPurchaseController extends BaseController {

	private static Logger logger = Logger.getLogger(SupplierPurchaseController.class);

	@Autowired
	private SupplierPurchaseService supplierPurchaseService;

	@Autowired
	private PurchaseDetailService purchaseDetailService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SupplierPurchase supplierPurchase, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(supplierPurchase.getId())) {
				supplierPurchaseService.save(supplierPurchase);
			} else {
				supplierPurchaseService.update(supplierPurchase);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SupplierPurchase supplierPurchase) {
		try {
			supplierPurchaseService.delete(supplierPurchase);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SupplierPurchase supplierPurchase) {
		ModelAndView modelAndView = new ModelAndView("supplierpurchase/info");
		try {
			if (!StringUtils.isEmpty(supplierPurchase.getId())) {
				SupplierPurchase data = supplierPurchaseService.findObject(supplierPurchase);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SupplierPurchase supplierPurchase) {
		try {
			SupplierPurchase data = supplierPurchaseService.findObject(supplierPurchase);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("supplierpurchase/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String supplier, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("supplierpurchase/table");
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("supplierid", company.getId());

			if (user.getRoleids().indexOf("F717B4EEF241460891F0E1D3AB84F74E") > -1) {

				// if (user.getRoleid().equals("F717B4EEF241460891F0E1D3AB84F74E")) {
				params.put("contactid", user.getId());
			}

			// 参数设置
			if (!StringUtils.isEmpty(supplier)) {
				params.put("supplier", supplier);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<SupplierPurchase> data = supplierPurchaseService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "detaillist")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String purchaseid) {
		ModelAndView modelAndView = new ModelAndView("supplierpurchase/detaillist");
		modelAndView.addObject("purchaseid", purchaseid);
		return modelAndView;
	}

	@RequestMapping(value = "detailtable")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response, String purchaseid, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("supplierpurchase/detailtable");
		User user = (User) session.getAttribute(Constants.USER);
		String userid = user.getParentid();
		if (StringUtils.isEmpty(userid)) {
			userid = user.getId();
		}
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("purchaseid", purchaseid);
			PageResult<PurchaseDetail> data = purchaseDetailService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		modelAndView.addObject("supplierid", userid);
		return modelAndView;

	}

	/**
	 * excel导入目录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/uploadxls")
	@ResponseBody
	public Object uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile, String purchaseid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		String userid = user.getParentid();
		if (StringUtils.isEmpty(userid)) {
			userid = user.getId();
		}
		InputStream xls;
		try {
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<PurchaseDetailExcelVo> planDetails;
			if (name.toLowerCase().endsWith("xlsx")) {
				planDetails = ImportExcelUtil.importExcel2007(PurchaseDetailExcelVo.class, xls);
			} else {
				planDetails = ImportExcelUtil.importExcel2003(PurchaseDetailExcelVo.class, xls);
			}
			supplierPurchaseService.upload(userid, purchaseid, planDetails);
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

	@RequestMapping(value = "exportfile")
	public void export(HttpServletRequest request, HttpServletResponse response, String purchaseid) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("purchaseid", purchaseid);
			Page page = new Page(1, 2000);
			PageResult<PurchaseDetail> data = purchaseDetailService.findList(params, page);
			try {
				response.reset();
				response.setContentType("application/csv;charset=GBK");
				response.setHeader("Content-Disposition", "attachment;filename=Data" + System.currentTimeMillis() + ".csv");
				response.setCharacterEncoding("GBK");
				PrintWriter out = response.getWriter();
				out.println("编码,通用名,品名,规格,单位,剂型,厂商,国药准字,数量,价格,备注");
				for (int i = 1; i <= data.getTotalPages(); i++) {
					PageResult<PurchaseDetail> data2 = purchaseDetailService.findList(params, new Page(i, 2000, false));
					for (PurchaseDetail v : data2.getData()) {
						StringBuilder str = new StringBuilder();
						str.append("\"" + v.getCode()).append("\"\t,");
						str.append("\"" + v.getCommon()).append("\",");
						str.append("\"" + v.getName()).append("\",");
						str.append("\"" + v.getSpecifications()).append("\",");
						str.append("\"" + v.getUnit()).append("\",");
						str.append("\"" + v.getDosageform()).append("\",");
						str.append("\"" + v.getFactory()).append("\",");
						str.append("\"" + v.getZunzi()).append("\",");
						str.append("\"" + v.getNum()).append("\",");
						str.append("\"\",");
						str.append("\"\",");
						out.println(str.toString().replaceAll("null", ""));
					}
				}
				out.flush();
				out.close();
			} catch (Exception e) {
				logger.error(e);
			}
		} catch (

		Exception e) {
			logger.error(e);
		}
	}

}