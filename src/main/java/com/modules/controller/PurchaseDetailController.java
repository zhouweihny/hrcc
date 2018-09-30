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

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.PurchaseDetailService;
import com.modules.service.StoreService;
import com.modules.service.SupplierPurchaseDetailService;
import com.modules.service.SupplierPurchaseService;
import com.modules.vo.PurchaseDetailVo;
import com.modules.vo.SupplierPurchaseDetailPirceVo;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.Store;
import com.modules.pojo.SupplierPurchase;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("purchasedetail")
public class PurchaseDetailController extends BaseController {

	private static Logger logger = Logger.getLogger(PurchaseDetailController.class);

	@Autowired
	private PurchaseDetailService purchaseDetailService;
	@Autowired
	private SupplierPurchaseDetailService supplierPurchaseDetailService;
	@Autowired
	private SupplierPurchaseService supplierPurchaseService;
	@Autowired
	private StoreService storeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(PurchaseDetail purchaseDetail, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(purchaseDetail.getId())) {
				purchaseDetailService.save(purchaseDetail);
			} else {
				purchaseDetailService.update(purchaseDetail);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(PurchaseDetail purchaseDetail) {
		try {
			purchaseDetailService.delete(purchaseDetail);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(PurchaseDetail purchaseDetail) {
		ModelAndView modelAndView = new ModelAndView("purchasedetail/info");
		try {
			if (!StringUtils.isEmpty(purchaseDetail.getId())) {
				PurchaseDetail data = purchaseDetailService.findObject(purchaseDetail);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(PurchaseDetail purchaseDetail) {
		try {
			PurchaseDetail data = purchaseDetailService.findObject(purchaseDetail);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String purchaseid, String storecode) {
		User user = (User) session.getAttribute(Constants.USER);
		String userid = user.getParentid();
		if (StringUtils.isEmpty(userid)) {
			userid = user.getId();
		}
		ModelAndView modelAndView = new ModelAndView("purchasedetail/list");
		modelAndView.addObject("purchaseid", purchaseid);
		modelAndView.addObject("storecode", storecode);

		Store store = new Store();
		store.setStorecode(storecode);
		store.setUserid(userid);
		try {
			store = storeService.findObject(store);
			modelAndView.addObject("storename", store.getName());
		} catch (Exception e) {
			logger.error(e);
		}

		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String code, String purchaseid, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("purchasedetail/table");
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(purchaseid)) {
				params.put("purchaseid", purchaseid);
			}
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
			SupplierPurchase supplierPurchase = new SupplierPurchase();
			supplierPurchase.setPurchaseid(purchaseid);
			List<SupplierPurchase> supplierPurchases = supplierPurchaseService.findList(supplierPurchase);
			Collections.sort(supplierPurchases, new Comparator<SupplierPurchase>() {
				public int compare(SupplierPurchase o1, SupplierPurchase o2) {
					if (o1.getStatus() < o2.getStatus()) {
						return 1;
					}
					if (o1.getStatus() == o2.getStatus()) {
						return 0;
					}
					return -1;
				}
			});

			PageResult<PurchaseDetailVo> data = purchaseDetailService.findPurchaseDetailVoList(params, page);
			modelAndView.addObject("page", data);
			modelAndView.addObject("supplierPurchases", supplierPurchases);

		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "detailprice", method = RequestMethod.POST)
	@ResponseBody
	public Object detailprice(String puchasedetailid) {
		try {
			List<SupplierPurchaseDetailPirceVo> list = supplierPurchaseDetailService.findprice(puchasedetailid);
			return this.getJsonResult(list);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}