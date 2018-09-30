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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.excel.ImportExcelUtil;
import com.modules.service.DrugPromotionPurchaesrService;
import com.modules.service.DrugPromotionService;
import com.modules.vo.DrugPromotionExcelVo;
import com.modules.vo.UserVo;
import com.modules.pojo.DrugPromotion;
import com.modules.pojo.DrugPromotionPurchaesr;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("drugpromotion")
public class DrugPromotionController extends BaseController {

	private static Logger logger = Logger.getLogger(DrugPromotionController.class);

	@Autowired
	private DrugPromotionService drugPromotionService;

	@Autowired
	private DrugPromotionPurchaesrService drugPromotionPurchaesrService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(DrugPromotion drugPromotion, HttpServletRequest request, HttpServletResponse response) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			if (StringUtils.isEmpty(drugPromotion.getId())) {
				drugPromotion.setSupplierid(company.getId());
				drugPromotionService.save(drugPromotion);
			} else {
				drugPromotionService.update(drugPromotion);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(DrugPromotion drugPromotion) {
		try {
			drugPromotionService.delete(drugPromotion);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(DrugPromotion drugPromotion) {
		ModelAndView modelAndView = new ModelAndView("drugpromotion/info");
		try {
			if (!StringUtils.isEmpty(drugPromotion.getId())) {
				DrugPromotion data = drugPromotionService.findObject(drugPromotion);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(DrugPromotion drugPromotion) {
		try {
			DrugPromotion data = drugPromotionService.findObject(drugPromotion);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("drugpromotion/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, Integer status, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("drugpromotion/table");
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置

			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			params.put("supplierid", company.getId());

			if (!StringUtils.isEmpty(status)) {
				params.put("status", status);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<DrugPromotion> data = drugPromotionService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "purchaselist")
	public ModelAndView purchaselist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("drugpromotion/purchaselist");
		return modelAndView;
	}

	@RequestMapping(value = "purchasetable")
	public ModelAndView purchaselist(HttpServletRequest request, HttpServletResponse response, String name, String supplier, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("drugpromotion/purchasetable");
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			params.put("purchaserid", company.getId());
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(supplier)) {
				params.put("supplier", supplier);
			}
			params.put("status", 1);
			params.put("valid", true);
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<DrugPromotion> data = drugPromotionService.findList(params, page);
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
		User company = (User) session.getAttribute(Constants.COMPANY);
		InputStream xls;
		try {
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<DrugPromotionExcelVo> drugPromotionExcelVos;
			if (name.toLowerCase().endsWith("xlsx")) {
				drugPromotionExcelVos = ImportExcelUtil.importExcel2007(DrugPromotionExcelVo.class, xls);
			} else {
				drugPromotionExcelVos = ImportExcelUtil.importExcel2003(DrugPromotionExcelVo.class, xls);
			}
			drugPromotionService.upload(drugPromotionExcelVos, company.getId());
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

	/**
	 * 获取已发送采购商
	 * 
	 * @return
	 */
	@RequestMapping(value = "/purchasers")
	@ResponseBody
	public Object purchasers(String promotionid, HttpServletRequest request, HttpServletResponse response) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			return drugPromotionService.selected(company.getId(), promotionid);
		} catch (Exception e) {
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	/**
	 * 推送至采购商
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sendpurchasers")
	@ResponseBody
	public Object sendpurchasers(String purchaserids, String promotionid, HttpServletRequest request, HttpServletResponse response) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			List<DrugPromotionPurchaesr> drugPromotionPurchaesrs = new ArrayList<DrugPromotionPurchaesr>();
			String[] ids = purchaserids.split(",");

			List<UserVo> purchasers = drugPromotionService.selected(company.getId(), promotionid);

			loop: for (String id : ids) {
				for (UserVo user : purchasers) {
					if (user.getId().equals(id) && user.getChecked()) {
						continue loop;
					}
				}
				DrugPromotionPurchaesr drugPromotionPurchaesr = new DrugPromotionPurchaesr();
				drugPromotionPurchaesr.setPromotionid(promotionid);
				drugPromotionPurchaesr.setPurchaserid(id);
				drugPromotionPurchaesrs.add(drugPromotionPurchaesr);
			}
			drugPromotionPurchaesrService.save(drugPromotionPurchaesrs);

			DrugPromotion drugPromotion = new DrugPromotion();
			drugPromotion.setId(promotionid);
			drugPromotion.setStatus(1);
			drugPromotionService.update(drugPromotion);

			return this.getJsonResult();

		} catch (Exception e) {
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}