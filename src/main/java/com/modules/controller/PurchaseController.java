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
import com.commons.util.excel.ImportExcelUtil;
import com.modules.service.PurchaseService;
import com.modules.service.SupplierPurchaseService;
import com.modules.service.UserService;
import com.modules.pojo.Purchase;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.SupplierPurchase;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("purchase")
public class PurchaseController extends BaseController {

	private static Logger logger = Logger.getLogger(PurchaseController.class);

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private SupplierPurchaseService supplierPurchaseService;

	@Autowired
	private UserService userService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Purchase purchase, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(purchase.getId())) {
				purchaseService.save(purchase);
			} else {
				purchaseService.update(purchase);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Purchase purchase) {
		try {
			purchaseService.delete(purchase);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Purchase purchase) {
		ModelAndView modelAndView = new ModelAndView("purchase/info");
		try {
			if (!StringUtils.isEmpty(purchase.getId())) {
				Purchase data = purchaseService.findObject(purchase);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Purchase purchase) {
		try {
			Purchase data = purchaseService.findObject(purchase);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "send")
	@ResponseBody
	public Object pbyid(String purchaseid, HttpServletRequest request, HttpServletResponse response, String supplierids) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		try {
			String[] ids = supplierids.split(",");
			purchaseService.send(purchaseid, user.getId(), ids);
			purchaseService.sendNoitce(purchaseid, user.getId(), supplierids.split(","));
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String planid) {
		ModelAndView modelAndView = new ModelAndView("purchase/list");
		modelAndView.addObject("planid", planid);
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String planid, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);
		ModelAndView modelAndView = new ModelAndView("purchase/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			params.put("userid", company.getId());
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(planid)) {
				params.put("planid", planid);
			}

			// 采购采购员
			if (user.getRoleids().indexOf("9334387B790C42658B7BE0655E155592") > -1) {
				if (!StringUtils.isEmpty(user.getErpusername()))
					params.put("erpusername", user.getErpusername());
			}

			PageResult<Purchase> data = purchaseService.findList(params, page);
			modelAndView.addObject("page", data);
			for (Purchase purchase : data.getData()) {
				SupplierPurchase supplierPurchase = new SupplierPurchase();
				supplierPurchase.setPurchaseid(purchase.getId());
				List<SupplierPurchase> supplierPurchases = supplierPurchaseService.findList(supplierPurchase);
				StringBuffer sb = new StringBuffer();
				for (SupplierPurchase sp : supplierPurchases) {
					User s = new User();
					s.setId(sp.getSupplierid());
					s = userService.findObject(s);
					if (StringUtils.isEmpty(s.getScompany())) {
						sb.append(s.getCompany());
					} else {
						sb.append(s.getScompany());
					}
					if (sp.getStatus() == 1)
						sb.append("(<font  color=\"red\">").append(supplierPurchaseService.findCount(purchase.getId(), sp.getSupplierid())).append("条报价</font>)");
					sb.append("/");
				}
				if (sb.length() > 0)
					purchase.setRemark(sb.substring(0, sb.length() - 1));

			}
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
	public Object uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile, String storecode, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		InputStream xls;
		try {
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<PurchaseDetail> purchaseDetails;
			if (name.toLowerCase().endsWith("xlsx")) {
				purchaseDetails = ImportExcelUtil.importExcel2007(PurchaseDetail.class, xls);
			} else {
				purchaseDetails = ImportExcelUtil.importExcel2003(PurchaseDetail.class, xls);
			}
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			purchaseService.upload(name, storecode, userid, purchaseDetails);
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

	@RequestMapping(value = "shoppingCart")
	public ModelAndView shoppingCart(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("purchase/shoppingCart");
		return modelAndView;
	}
}