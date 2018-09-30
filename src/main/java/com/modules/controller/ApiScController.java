package com.modules.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.commons.base.BaseController;
import com.commons.util.UUIDGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modules.pojo.Accesstoken;
import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.DrugDrug;
import com.modules.pojo.DrugDrugDel;
import com.modules.pojo.DrugTemp;
import com.modules.pojo.Purchase;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.Space;
import com.modules.pojo.SpaceCatalog;
import com.modules.pojo.SupplierPurchase;
import com.modules.pojo.SupplierPurchaseDetail;
import com.modules.pojo.User;
import com.modules.service.AccesstokenService;
import com.modules.service.BillDetailService;
import com.modules.service.BillService;
import com.modules.service.CatalogService;
import com.modules.service.DrugDrugDelService;
import com.modules.service.DrugDrugService;
import com.modules.service.DrugService;
import com.modules.service.DrugTempService;
import com.modules.service.PurchaseDetailService;
import com.modules.service.PurchaseService;
import com.modules.service.SpaceCatalogService;
import com.modules.service.SpaceService;
import com.modules.service.SupplierPurchaseDetailService;
import com.modules.service.SupplierPurchaseService;
import com.modules.service.UserService;
import com.modules.service.UserSupplierService;
import com.modules.vo.apisc.BillDetailVo;
import com.modules.vo.apisc.PurchaseDetailVo;
import com.modules.vo.apisc.PurchaseVo;
import com.modules.vo.apisc.accesstoken.IaData;
import com.modules.vo.apisc.accesstoken.RaData;
import com.modules.vo.apisc.bill.IbData;
import com.modules.vo.apisc.bill.RbData;
import com.modules.vo.apisc.createcatalog.IcData;
import com.modules.vo.apisc.createcatalog.RcData;
import com.modules.vo.apisc.deletecatalog.IdcData;
import com.modules.vo.apisc.deletecatalog.RdcData;
import com.modules.vo.apisc.export.IeData;
import com.modules.vo.apisc.export.ReData;
import com.modules.vo.apisc.purchasers.IprData;
import com.modules.vo.apisc.purchasers.RprData;
import com.modules.vo.apisc.purchasers.RprData.Purchaser;
import com.modules.vo.apisc.purchases.IpData;
import com.modules.vo.apisc.purchases.RpData;
import com.modules.vo.apisc.sendpurchases.IspData;
import com.modules.vo.apisc.sendpurchases.RspData;
import com.modules.vo.apisc.upload.IuData;
import com.modules.vo.apisc.upload.RuData;

@Controller
@RequestMapping("apisc")
public class ApiScController extends BaseController {

	private static Logger logger = Logger.getLogger(ApiScController.class);

	@Autowired
	private DrugService drugService;

	@Autowired
	private DrugTempService drugTempService;

	@Autowired
	private SpaceService spaceService;

	@Autowired
	private UserService userService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private SpaceCatalogService spaceCatalogService;

	@Autowired
	private DrugDrugService drugDrugService;

	@Autowired
	private DrugDrugDelService drugDrugDelService;

	@Autowired
	private AccesstokenService accesstokenService;

	@Autowired
	private UserSupplierService userSupplierService;

	@Autowired
	private SupplierPurchaseService supplierPurchaseService;

	@Autowired
	private SupplierPurchaseDetailService supplierPurchaseDetailService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private PurchaseDetailService purchaseDetailService;

	@Autowired
	private BillService billService;

	@Autowired
	private BillDetailService billDetailService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@RequestMapping("testuser")
	@ResponseBody
	public Object testuser(HttpServletRequest request, HttpServletResponse response) {
		RaData raData = new RaData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IaData iaData = gson.fromJson(res, IaData.class);
			String username = iaData.getUsername();
			String password = iaData.getPassword();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				raData.setResult(false);
				raData.setMessage("用户名或密码错误!");
				return raData;
			}
			raData.setResult(true);
			return raData;
		} catch (Exception e) {
			logger.error(e);
			raData.setResult(false);
			raData.setMessage(e.getMessage());
			return raData;
		}

	}

	@RequestMapping("accesstoken")
	@ResponseBody
	public Object accesstoken(HttpServletRequest request, HttpServletResponse response) {
		RaData raData = new RaData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IaData iaData = gson.fromJson(res, IaData.class);
			String username = iaData.getUsername();
			String password = iaData.getPassword();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {					 
				raData.setResult(false);
				raData.setMessage("用户名或密码错误!");
				return raData;
			}
			String accesstokenStr = UUIDGenerator.getUUID();
			Accesstoken accesstoken = new Accesstoken();
			accesstoken.setAccesstoken(accesstokenStr);
			accesstoken.setUserid(u.getId());
			Date expirytime = new Date();
			expirytime.setTime(System.currentTimeMillis() + 1000 * 60 * 60 * 2);
			accesstoken.setExpirytime(expirytime);
			accesstokenService.save(accesstoken);

			raData.setResult(true);
			raData.setAccesstoken(accesstokenStr);

			return raData;

		} catch (Exception e) {
			logger.error(e);
			raData.setResult(false);
			raData.setMessage(e.getMessage());
			return raData;
		}

	}

	@RequestMapping("createcatalog")
	@ResponseBody
	public Object createcatalog(HttpServletRequest request, HttpServletResponse response) {
		RcData rcData = new RcData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IcData icData = gson.fromJson(res, IcData.class);
			String username = icData.getUsername();
			String password = icData.getPassword();
			Catalog catalog = icData.getCatalog();

			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				rcData.setResult(false);
				rcData.setMessage("用户名或密码错误!");
				return rcData;
			}
			// 获取经营品种对码目录
			Space space = new Space();
			space.setUserid(u.getId());
			space.setMyself(true);
			space = spaceService.findObject(space);

			Catalog c = new Catalog();
			c.setUserid(u.getId());
			if (catalog.getMyself() != null && catalog.getMyself()) {
				c.setMyself(true);
			} else {
				c.setId(catalog.getId());
			}
			c = catalogService.findObject(c);
			if (c == null) {
				catalog.setUserid(u.getId());
				catalogService.save(catalog);
				SpaceCatalog spaceCatalog = new SpaceCatalog();
				spaceCatalog.setSpaceid(space.getId());
				spaceCatalog.setCatalogid(catalog.getId());
				spaceCatalogService.save(spaceCatalog);
				rcData.setCatalog(catalog);
			} else {
				rcData.setCatalog(c);
			}
			rcData.setResult(true);
			return rcData;
		} catch (Exception e) {
			logger.error(e);
			rcData.setResult(false);
			rcData.setMessage(e.getMessage());
			return rcData;
		}

	}

	@RequestMapping("upload")
	@ResponseBody
	public Object upload(HttpServletRequest request, HttpServletResponse response) {
		RuData ruData = new RuData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IuData iuData = gson.fromJson(res, IuData.class);
			String catalogid = iuData.getCatalogid();
			String username = iuData.getUsername();
			String password = iuData.getPassword();
			List<Drug> drugs = iuData.getDrugs();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				ruData.setResult(false);
				ruData.setMessage("用户名或者密码错误");
				return ruData;
			}
			Catalog catalog = new Catalog();
			catalog.setId(catalogid);
			catalog.setUserid(u.getId());
			catalog = catalogService.findObject(catalog);
			if (catalog == null) {
				ruData.setResult(false);
				ruData.setMessage("药品目录不存在!");
				return ruData;
			}
			List<DrugTemp> tdrugs = new ArrayList<DrugTemp>();
			for (Drug drug : drugs) {
				tdrugs.add(dozerBeanMapper.map(drug, DrugTemp.class));
			}
			drugTempService.save(tdrugs);
			drugService.tempTo(catalogid);
			drugService.removeRepeat(catalogid);
			ruData.setResult(true);
			return ruData;
		} catch (Exception e) {
			logger.error(e);
			ruData.setResult(false);
			ruData.setMessage(e.getMessage());
			return ruData;
		}

	}

	@RequestMapping("export")
	@ResponseBody
	public Object export(HttpServletRequest request, HttpServletResponse response) {
		ReData reData = new ReData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IeData iedata = gson.fromJson(res, IeData.class);
			String username = iedata.getUsername();
			String password = iedata.getPassword();
			String catalogid = iedata.getCatalogid();
			Date lastExportTime = iedata.getLastExportTime();

			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				reData.setResult(false);
				reData.setMessage("用户名或者密码错误");
				return reData;
			}
			Space space = new Space();
			space.setUserid(u.getId());
			space.setMyself(true);
			space = spaceService.findObject(space);
			reData.setDrugdrugs(drugDrugService.findcheckedbycatalogid(space.getId(), catalogid, lastExportTime));
			reData.setDrugdrugdels(drugDrugDelService.findbycatalogid(space.getId(), catalogid, lastExportTime));
			reData.setResult(true);
			return reData;
		} catch (Exception e) {
			logger.error(e);
			reData.setResult(false);
			reData.setMessage(e.getMessage());
			return reData;
		}
	}

	/**
	 * 获取申请采购商
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("purchasers")
	@ResponseBody
	public Object purchasers(HttpServletRequest request, HttpServletResponse response) {
		RprData rprData = new RprData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IprData iprData = gson.fromJson(res, IprData.class);
			String username = iprData.getUsername();
			String password = iprData.getPassword();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				rprData.setResult(false);
				rprData.setMessage("用户名或者密码错误");
				return rprData;
			}
			List<User> users = userSupplierService.findPurchaserList(u.getId(), false);

			List<Purchaser> purchasers = new ArrayList<Purchaser>();
			for (User ut : users) {
				Purchaser purchaser = rprData.new Purchaser();
				purchasers.add(purchaser);
				purchaser.setName(ut.getCompany());
				purchaser.setPuchaserid(ut.getId());
			}

			rprData.setPurchasers(purchasers);
			rprData.setResult(true);
			return rprData;
		} catch (Exception e) {
			logger.error(e);
			rprData.setResult(false);
			rprData.setMessage(e.getMessage());
			return rprData;
		}
	}

	/**
	 * 获取申请询价单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("purchases")
	@ResponseBody
	public Object getpurchases(HttpServletRequest request, HttpServletResponse response) {
		RpData rpData = new RpData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IpData ipdata = gson.fromJson(res, IpData.class);
			String username = ipdata.getUsername();
			String password = ipdata.getPassword();
			String purchaserid = ipdata.getPurchaserid();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				rpData.setResult(false);
				rpData.setMessage("用户名或者密码错误");
				return rpData;
			}
			List<SupplierPurchase> supplierPurchases = supplierPurchaseService.findList(purchaserid, u.getId(), false);
			List<PurchaseVo> purchases = new ArrayList<PurchaseVo>();
			for (SupplierPurchase sp : supplierPurchases) {
				Purchase purchase = new Purchase();
				purchase.setId(sp.getPurchaseid());
				purchase = purchaseService.findObject(purchase);
				PurchaseDetail purchaseDetail = new PurchaseDetail();
				purchaseDetail.setPurchaseid(sp.getPurchaseid());
				List<PurchaseDetail> purchaseDetails = purchaseDetailService.findList(purchaseDetail);
				List<PurchaseDetailVo> purchaseDetailVos = new ArrayList<PurchaseDetailVo>();
				PurchaseVo purchaseVo = dozerBeanMapper.map(purchase, PurchaseVo.class);
				purchases.add(purchaseVo);
				purchaseVo.setPurchaseDetails(purchaseDetailVos);
				for (PurchaseDetail pd : purchaseDetails)
					purchaseDetailVos.add(dozerBeanMapper.map(pd, PurchaseDetailVo.class));

			}
			rpData.setPurchases(purchases);
			rpData.setResult(true);
			for (SupplierPurchase sp : supplierPurchases) {
				sp.setSend(1);
				supplierPurchaseService.update(sp);
			}
			return rpData;
		} catch (Exception e) {
			logger.error(e);
			rpData.setResult(false);
			rpData.setMessage(e.getMessage());
			return rpData;
		}
	}

	/**
	 * 发送询价单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sendpurchases")
	@ResponseBody
	public Object sendpurchases(HttpServletRequest request, HttpServletResponse response) {
		RspData reData = new RspData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IspData ispData = gson.fromJson(res, IspData.class);
			String username = ispData.getUsername();
			String password = ispData.getPassword();
			List<PurchaseVo> purchases = ispData.getPurchases();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				reData.setResult(false);
				reData.setMessage("用户名或者密码错误");
				return reData;
			}
			for (PurchaseVo p : purchases) {
				SupplierPurchase supplierPurchase = new SupplierPurchase();
				supplierPurchase.setPurchaseid(p.getId());
				supplierPurchase = supplierPurchaseService.findObject(supplierPurchase);
				if (supplierPurchase != null) {
					for (PurchaseDetailVo pd : p.getPurchaseDetails()) {
						SupplierPurchaseDetail supplierPurchaseDetail = new SupplierPurchaseDetail();
						supplierPurchaseDetail.setPurchasedetailid(pd.getPurchaseid());
						supplierPurchaseDetail = supplierPurchaseDetailService.findObject(supplierPurchaseDetail);
						if (supplierPurchaseDetail != null) {
							SupplierPurchaseDetail spd = new SupplierPurchaseDetail();
							spd.setId(supplierPurchaseDetail.getId());
							spd.setPrice(pd.getPrice());
							supplierPurchaseDetailService.update(supplierPurchaseDetail);
						} else {
							SupplierPurchaseDetail spd = new SupplierPurchaseDetail();
							spd.setPrice(pd.getPrice());
							spd.setSupplierid(u.getId());
							spd.setPurchasedetailid(pd.getId());
							supplierPurchaseDetailService.save(spd);
						}
					}
					supplierPurchase.setStatus(1);
					supplierPurchaseService.update(supplierPurchase);
				}
			}
			reData.setResult(true);
			return reData;
		} catch (Exception e) {
			logger.error(e);
			reData.setResult(false);
			reData.setMessage(e.getMessage());
			return reData;
		}
	}

	/**
	 * 获取采购单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sendbills")
	@ResponseBody
	public Object sendbills(HttpServletRequest request, HttpServletResponse response) {
		RbData rbData = new RbData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IbData ibData = gson.fromJson(res, IbData.class);
			String username = ibData.getUsername();
			String password = ibData.getPassword();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				rbData.setResult(false);
				rbData.setMessage("用户名或者密码错误");
				return rbData;
			}

			List<BillDetailVo> bdvs = new ArrayList<BillDetailVo>();
			rbData.setBillDetails(bdvs);
			BillDetail tbillDetail = new BillDetail();
			tbillDetail.setSupplierid(u.getId());
			tbillDetail.setSended(false);
			List<BillDetail> billDetails = billDetailService.findList(tbillDetail);
			for (BillDetail bd : billDetails) {
				Bill bill = new Bill();
				bill.setId(bd.getBillid());
				bill = billService.findObject(bill);
				BillDetailVo bdv = dozerBeanMapper.map(bd, BillDetailVo.class);
				bdv.setBillcode(bill.getCode());
				bdvs.add(bdv);
				BillDetail tbd = new BillDetail();
				tbd.setSended(true);
				tbd.setId(bd.getId());
				billDetailService.update(tbd);
			}

			rbData.setResult(true);
			return rbData;
		} catch (Exception e) {
			logger.error(e);
			rbData.setResult(false);
			rbData.setMessage(e.getMessage());
			return rbData;
		}
	}

	/**
	 * 获取采购单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updatebills")
	@ResponseBody
	public Object updatebills(HttpServletRequest request, HttpServletResponse response) {
		RbData rbData = new RbData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IbData ibData = gson.fromJson(res, IbData.class);
			String username = ibData.getUsername();
			String password = ibData.getPassword();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				rbData.setResult(false);
				rbData.setMessage("用户名或者密码错误");
				return rbData;
			}
			rbData.setResult(true);
			return rbData;
		} catch (Exception e) {
			logger.error(e);
			rbData.setResult(false);
			rbData.setMessage(e.getMessage());
			return rbData;
		}
	}

	/**
	 * 删除询价单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("deletecatalog")
	@ResponseBody
	public Object deletecatalog(HttpServletRequest request, HttpServletResponse response) {
		RdcData reData = new RdcData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IdcData idcData = gson.fromJson(res, IdcData.class);
			String username = idcData.getUsername();
			String password = idcData.getPassword();
			List<String> ids = idcData.getIds();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				reData.setResult(false);
				reData.setMessage("用户名或者密码错误");
				return reData;
			}
			Space space = new Space();
			space.setUserid(u.getId());
			space.setMyself(true);
			space = spaceService.findObject(space);
			for (String id : ids) {
				Drug drug = new Drug();
				drug.setId(id);
				drugService.delete(drug);
				DrugDrug drugdrug = new DrugDrug();
				drugdrug.setDrugid(id);
				drugdrug.setSpaceid(space.getId());
				drugdrug.setNum(0);
				drugdrug = drugDrugService.findObject(drugdrug);
				if (drugdrug != null) {
					DrugDrug tdd = new DrugDrug();
					tdd.setId(drugdrug.getId());
					drugDrugService.delete(tdd);
					DrugDrugDel drugDrugDel = dozerBeanMapper.map(drugdrug, DrugDrugDel.class);
					drugDrugDel.setOperatorid(user.getId());
					drugDrugDelService.save(drugDrugDel);
				}
			}
			reData.setResult(true);
			return reData;
		} catch (Exception e) {
			logger.error(e);
			reData.setResult(false);
			reData.setMessage(e.getMessage());
			return reData;
		}
	}
}
