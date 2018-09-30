package com.modules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.commons.base.BaseController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.FxDrugStore;
import com.modules.pojo.FxSaleData;
import com.modules.pojo.Store;
import com.modules.pojo.User;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.FxComnameService;
import com.modules.service.FxDrugStoreService;
import com.modules.service.FxOrderfxService;
import com.modules.service.FxSaleDataService;
import com.modules.service.FxSaleMonthService;
import com.modules.service.StoreService;
import com.modules.service.UserService;
import com.modules.vo.apiana.sales.IuSale;
import com.modules.vo.apipur.uploadsupplier.RsData;

@Controller
@RequestMapping("apiana")
public class ApiAnalysisController extends BaseController {

	private static Logger logger = Logger.getLogger(ApiAnalysisController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	@Autowired
	private StoreService storeService;
//	@Autowired
//	private FxImpsaledataService fxImpsaledataService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private FxDrugStoreService fxDrugStoreService;
	@Autowired
	private FxComnameService fxComnameService;
	@Autowired
	private FxSaleDataService fxSaleDataService;
	@Autowired
	private FxSaleMonthService fxSaleMonthService;
	@Autowired
	private FxOrderfxService fxOrderfxService;

	/**
	 * 上传销售数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadsales")
	@ResponseBody
	public Object uploaderpuser(HttpServletRequest request, HttpServletResponse response) {
		RsData rsData = new RsData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IuSale iuserdata = gson.fromJson(res.toLowerCase(), IuSale.class);
			String username = iuserdata.getUsername();
			String password = iuserdata.getPassword();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				rsData.setResult(false);
				rsData.setMessage("用户名或者密码错误");
				return rsData;
			}
			Store store = new Store();
			store.setUserid(u.getId());
			store.setStorecode(iuserdata.getStorecode());
			store = storeService.findObject(store);
			if (store == null) {
				store = new Store();
				store.setStorecode(iuserdata.getStorecode());
				store.setName(iuserdata.getStorename());
				store.setUserid(u.getId());
				storeService.save(store);
			}

			if (store != null) {
				Catalog catalog = new Catalog();
				catalog.setMyself(true);
				catalog.setUserid(u.getId());
				catalog = catalogService.findObject(catalog);
				List<FxSaleData> datas = iuserdata.getDetails();
				for (FxSaleData fxSaleData : datas) {
					Drug drug = new Drug();
					drug.setCatalogid(catalog.getId());
					drug.setCode(fxSaleData.getCode());
					drug = drugService.findObject(drug);
					FxDrugStore fxDrugStore = new FxDrugStore();
					if (drug == null) {
						drug = new Drug();
						drug.setCode(fxSaleData.getCode());
						drug.setName(fxSaleData.getName());
						drug.setSpecifications(fxSaleData.getSpecifications());
						drug.setFactory(fxSaleData.getFactory());
						drug.setUnit(fxSaleData.getUnit());
						drug.setCatalogid(catalog.getId());
						String comnameid = fxComnameService.compareDrug(fxSaleData.getName());
						drug.setComnameid(comnameid);
						drugService.save(drug);
						fxDrugStore.setDrugid(drug.getId());
						fxDrugStore.setStoreid(store.getId());
						fxDrugStore.setStatus(true);
						fxDrugStore.setZy(true);
						fxDrugStore.setXsrq(fxSaleData.getSaledate());
						fxDrugStore.setUserid(u.getId());
						if (fxSaleData != null)
							fxDrugStore.setBhrq(fxSaleData.getBhrq());
						fxDrugStoreService.save(fxDrugStore);
					}
					if (StringUtils.isBlank(fxDrugStore.getId())) {
						fxDrugStore.setDrugid(drug.getId());
						fxDrugStore.setStoreid(store.getId());
						fxDrugStore = fxDrugStoreService.findObject(fxDrugStore);
						if (fxDrugStore != null) {
							fxDrugStore.setStatus(true);
							fxDrugStore.setZy(true);
							fxDrugStore.setXsrq(fxSaleData.getSaledate());
							if (fxSaleData != null)
								fxDrugStore.setBhrq(fxSaleData.getBhrq());
							fxDrugStoreService.update(fxDrugStore);
						} else {
							fxDrugStore = new FxDrugStore();
							fxDrugStore.setDrugid(drug.getId());
							fxDrugStore.setStoreid(store.getId());
							fxDrugStore.setStatus(true);
							fxDrugStore.setZy(true);
							fxDrugStore.setXsrq(fxSaleData.getSaledate());
							if (fxSaleData != null)
								fxDrugStore.setBhrq(fxSaleData.getBhrq());
							fxDrugStoreService.save(fxDrugStore);
						}
					}
					fxSaleData.setDrugid(drug.getId());
					fxSaleData.setStoreid(store.getId());
					fxSaleData.setUserid(u.getId());
					fxSaleData.setOrderno(iuserdata.getOrderno());
					FxSaleData tfxSaleData = new FxSaleData();
					tfxSaleData.setOrderno(fxSaleData.getOrderno());
					tfxSaleData.setCode(fxSaleData.getCode());
					tfxSaleData.setQty(fxSaleData.getQty());
					tfxSaleData.setStoreid(store.getId());
					tfxSaleData = fxSaleDataService.findObject(tfxSaleData);
					if (tfxSaleData == null) {
						fxSaleDataService.save(fxSaleData);
						fxSaleMonthService.addSaleData(fxSaleData);
					}
				}
			}
			fxOrderfxService.refresh(iuserdata.getOrderno(), store.getId());
			return rsData;
		} catch (Exception e) {
			logger.error(e);
			rsData.setResult(false);
			rsData.setMessage(e.getMessage());
			return rsData;
		}
	}

}
