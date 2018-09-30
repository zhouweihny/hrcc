package com.modules.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.commons.base.BaseController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modules.pojo.Agent;
import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.DrugSupplier;
import com.modules.pojo.Erpuser;
import com.modules.pojo.Plan;
import com.modules.pojo.PlanDetail;
import com.modules.pojo.Purchase;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.User;
import com.modules.pojo.UserSupplier;
import com.modules.service.AgentService;
import com.modules.service.BillDetailService;
import com.modules.service.BillService;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.DrugSupplierService;
import com.modules.service.ErpuserService;
import com.modules.service.PlanDetailService;
import com.modules.service.PlanService;
import com.modules.service.PurchaseDetailService;
import com.modules.service.PurchaseService;
import com.modules.service.UserService;
import com.modules.service.UserSupplierService;
import com.modules.vo.apipur.AgentVo;
import com.modules.vo.apipur.BillDetailStatusVo;
import com.modules.vo.apipur.BillDetailVo;
import com.modules.vo.apipur.DrugVo;
import com.modules.vo.apipur.ErpUserVo;
import com.modules.vo.apipur.OrderDetailVo;
import com.modules.vo.apipur.OrderVo;
import com.modules.vo.apipur.PlanDetailVo;
import com.modules.vo.apipur.PurchaseDetailVo;
import com.modules.vo.apipur.SupplierVo;
import com.modules.vo.apipur.order.IoData;
import com.modules.vo.apipur.order.RoData;
import com.modules.vo.apipur.querybill.IqbillData;
import com.modules.vo.apipur.querybill.RqbillData;
import com.modules.vo.apipur.uploadbill.IbData;
import com.modules.vo.apipur.uploadbill.RbData;
import com.modules.vo.apipur.uploadcatalog.IuData;
import com.modules.vo.apipur.uploadcatalog.RuData;
import com.modules.vo.apipur.uploaderagent.IagentData;
import com.modules.vo.apipur.uploaderpuser.IuserData;
import com.modules.vo.apipur.uploadplan.IplData;
import com.modules.vo.apipur.uploadplan.RplData;
import com.modules.vo.apipur.uploadpurchase.IpData;
import com.modules.vo.apipur.uploadpurchase.RpData;
import com.modules.vo.apipur.uploadsupplier.IsData;
import com.modules.vo.apipur.uploadsupplier.RsData;

@Controller
@RequestMapping("apipur")
public class ApiPurController extends BaseController {

	private static Logger logger = Logger.getLogger(ApiPurController.class);

	@Autowired
	private DrugService drugService;

	@Autowired
	private DrugSupplierService drugSupplierService;

	@Autowired
	private UserService userService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private PurchaseDetailService purchaseDetailService;

	@Autowired
	private PlanService planService;

	@Autowired
	private PlanDetailService planDetailService;

	@Autowired
	private BillService billService;

	@Autowired
	private BillDetailService billDetailService;

	@Autowired
	private UserSupplierService userSupplierService;

	@Autowired
	private ErpuserService erpuserService;

	@Autowired
	private AgentService agentService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	/**
	 * 上传供应商信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploaderpuser")
	@ResponseBody
	public Object uploaderpuser(HttpServletRequest request, HttpServletResponse response) {
		RsData rsData = new RsData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IuserData iuserdata = gson.fromJson(res.toLowerCase(), IuserData.class);
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
			List<ErpUserVo> erpusers = iuserdata.getErpusers();
			for (ErpUserVo erpuservo : erpusers) {
				Erpuser erpuser = dozerBeanMapper.map(erpuservo, Erpuser.class);
				erpuser.setUserid(u.getId());
				Erpuser t = new Erpuser();
				t.setUserid(u.getId());
				t.setUsername(erpuservo.getUsername());
				t = erpuserService.findObject(t);
				if (t == null) {
					erpuserService.save(erpuser);
				} else {
					erpuser.setId(t.getId());
					erpuserService.update(erpuser);
				}
			}
			return rsData;
		} catch (Exception e) {
			logger.error(e);
			rsData.setResult(false);
			rsData.setMessage(e.getMessage());
			return rsData;
		}
	}

	/**
	 * 上传供应商信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadsupplier")
	@ResponseBody
	public Object uploadsupplier(HttpServletRequest request, HttpServletResponse response) {
		RsData rsData = new RsData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IsData isdata = gson.fromJson(res.toLowerCase(), IsData.class);
			String username = isdata.getUsername();
			String password = isdata.getPassword();
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
			List<SupplierVo> suppliers = isdata.getSuppliervos();
			for (SupplierVo supplier : suppliers) {
				UserSupplier userSupplier = dozerBeanMapper.map(supplier, UserSupplier.class);
				userSupplier.setUserid(u.getId());
				UserSupplier t = new UserSupplier();
				t.setUserid(u.getId());
				t.setCode(userSupplier.getCode());
				t = userSupplierService.findObject(t);
				if (t == null) {
					userSupplier.setSend(false);
					userSupplier.setUsed(false);
					userSupplierService.save(userSupplier);
				} else {
					userSupplier.setId(t.getId());
					userSupplierService.update(userSupplier);
				}
			}
			return rsData;
		} catch (Exception e) {
			logger.error(e);
			rsData.setResult(false);
			rsData.setMessage(e.getMessage());
			return rsData;
		}
	}

	/**
	 * 上传供应商信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadagent")
	@ResponseBody
	public Object uploadagent(HttpServletRequest request, HttpServletResponse response) {
		RsData rsData = new RsData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IagentData iagentData = gson.fromJson(res.toLowerCase(), IagentData.class);
			String username = iagentData.getUsername();
			String password = iagentData.getPassword();
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
			List<AgentVo> agentvos = iagentData.getAgentvos();
			for (AgentVo agentvo : agentvos) {
				Agent agent = dozerBeanMapper.map(agentvo, Agent.class);
				agent.setMobileno(agentvo.getMobile());
				agent.setPurchaserid(u.getId());
				Agent t = new Agent();
				t.setPurchaserid(u.getId());
				t.setMobileno(agentvo.getMobile());
				t = agentService.findObject(t);
				if (t == null) {
					agentService.save(agent);
				} else {
					agent.setId(t.getId());
					agentService.update(agent);
				}
			}
			return rsData;
		} catch (Exception e) {
			logger.error(e);
			rsData.setResult(false);
			rsData.setMessage(e.getMessage());
			return rsData;
		}
	}

	@RequestMapping("uploadcatalog")
	@ResponseBody
	public Object upload(HttpServletRequest request, HttpServletResponse response) {
		RuData ruData = new RuData();
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IuData iuData = gson.fromJson(res.toLowerCase(), IuData.class);
			String username = iuData.getUsername();
			String password = iuData.getPassword();
			List<DrugVo> drugvos = iuData.getDrugvos();
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
			catalog.setUserid(u.getId());
			catalog.setMyself(true);
			catalog = catalogService.findObject(catalog);
			if (catalog == null) {
				ruData.setResult(false);
				ruData.setMessage("药品目录不存在!");
				return ruData;
			}
			for (DrugVo drugvo : drugvos) {
				Drug drug = dozerBeanMapper.map(drugvo, Drug.class);
				drug.setCatalogid(catalog.getId());
				Drug tdrug = new Drug();
				tdrug.setCatalogid(catalog.getId());
				tdrug.setCode(drug.getCode());
				tdrug = drugService.findObject(tdrug);
				if (tdrug == null) {
					drugService.save(drug);
				} else {
					drug.setId(tdrug.getId());
					drugService.update(drug);
				}
				if (!StringUtils.isEmpty(drugvo.getErpsucode())) {
					DrugSupplier drugSupplier = new DrugSupplier();
					drugSupplier.setDrugid(drug.getId());
					drugSupplier = drugSupplierService.findObject(drugSupplier);
					if (drugSupplier == null) {
						drugSupplier = new DrugSupplier();
						drugSupplier.setDrugid(drug.getId());
						drugSupplier.setErpsucode(drugvo.getErpsucode());
						drugSupplier.setMobile(drugvo.getMobile());
						drugSupplier.setPurchaserid(u.getId());
						drugSupplier.setRemark(drugvo.getSuremark());
						drugSupplierService.save(drugSupplier);
					} else {
						drugSupplier.setDrugid(drug.getId());
						drugSupplier.setErpsucode(drugvo.getErpsucode());
						drugSupplier.setMobile(drugvo.getMobile());
						drugSupplier.setPurchaserid(u.getId());
						drugSupplier.setRemark(drugvo.getSuremark());
						drugSupplierService.update(drugSupplier);
					}
				}
			}
			ruData.setResult(true);
			return ruData;
		} catch (Exception e) {
			logger.error(e);
			ruData.setResult(false);
			ruData.setMessage(e.getMessage());
			return ruData;
		}
	}

	/**
	 * 上传采购计划
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadplan")
	@ResponseBody
	public Object uploadplan(HttpServletRequest request, HttpServletResponse response) {
		RplData rplData = new RplData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IplData ipldata = gson.fromJson(res.toLowerCase().replaceAll("plandetails", "planDetails"), IplData.class);
			String username = ipldata.getUsername();
			String password = ipldata.getPassword();
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);

			if (u == null) {
				rplData.setResult(false);
				rplData.setMessage("用户名或者密码错误");
				return rplData;
			}

			Catalog catalog = new Catalog();
			catalog.setUserid(u.getId());
			catalog.setMyself(true);
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				rplData.setResult(false);
				rplData.setMessage("药品目录不存在!");
				return rplData;
			}

			List<PlanDetailVo> plandetailvos = ipldata.getPlandetailvos();
			for (PlanDetailVo plandetailvo : plandetailvos) {
				PlanDetail plandetail = dozerBeanMapper.map(plandetailvo, PlanDetail.class);
				Plan plan = new Plan();
				plan.setErpid(plandetail.getPlanid());
				plan.setUserid(u.getId());
				plan = planService.findObject(plan);
				if (plan == null) {
					plan = new Plan();
					plan.setErpid(plandetail.getPlanid());
					plan.setErpusername(plandetailvo.getErpusername());
					plan.setUserid(u.getId());
					plan.setStorecode(plandetailvo.getStorecode());
					planService.save(plan);
				}
				PlanDetail t = new PlanDetail();
				t.setPlanid(plan.getId());
				t.setCode(plandetail.getCode());
				t = planDetailService.findObject(t);
				if (t == null) {
					Drug d = new Drug();
					d.setCode(plandetail.getCode());
					d.setCatalogid(catalog.getId());
					d = drugService.findObject(d);
					if (d != null) {
						plandetail.setDrugid(d.getId());
						plandetail.setFactory(d.getFactory());
						plandetail.setSpecifications(d.getSpecifications());
						plandetail.setUnit(d.getUnit());
						plandetail.setName(d.getName());
					}
					planDetailService.save(plandetail);
				}
			}
			return rplData;
		} catch (Exception e) {
			logger.error(e);
			rplData.setResult(false);
			rplData.setMessage(e.getMessage());
			return rplData;
		}
	}

	/**
	 * 上传采购询价单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadpurchases")
	@ResponseBody
	public Object getpurchases(HttpServletRequest request, HttpServletResponse response) {
		RpData rpData = new RpData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IpData ipdata = gson.fromJson(res.toLowerCase().replaceAll("purchasedetails", "purchaseDetails"), IpData.class);
			String username = ipdata.getUsername();
			String password = ipdata.getPassword();
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

			Catalog catalog = new Catalog();
			catalog.setUserid(u.getId());
			catalog.setMyself(true);
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				rpData.setResult(false);
				rpData.setMessage("药品目录不存在!");
				return rpData;
			}

			List<PurchaseDetailVo> purchasedetailvos = ipdata.getPurchasedetailvos();
			for (PurchaseDetailVo purvo : purchasedetailvos) {
				PurchaseDetail purchaseDetail = dozerBeanMapper.map(purvo, PurchaseDetail.class);
				Purchase purchase = new Purchase();
				purchase.setErpid(purvo.getPurchaseid());
				purchase.setUserid(u.getId());
				purchase = purchaseService.findObject(purchase);
				if (purchase == null) {
					purchase = new Purchase();
					purchase.setErpid(purvo.getPurchaseid());
					purchase.setUserid(u.getId());
					purchase.setErpusername(purvo.getErpusername());
					purchase.setStorecode(purvo.getStorecode());
					purchaseService.save(purchase);
				}
				PurchaseDetail t = new PurchaseDetail();
				t.setPurchaseid(purchase.getId());
				t.setCode(purvo.getCode());
				t = purchaseDetailService.findObject(purchaseDetail);
				if (t == null) {
					Drug d = new Drug();
					d.setCode(purchaseDetail.getCode());
					d.setCatalogid(catalog.getId());
					d = drugService.findObject(d);
					if (d != null) {
						purchaseDetail.setDrugid(d.getId());
						purchaseDetail.setFactory(d.getFactory());
						purchaseDetail.setSpecifications(d.getSpecifications());
						purchaseDetail.setUnit(d.getUnit());
						purchaseDetail.setName(d.getName());
					}
					purchaseDetailService.save(purchaseDetail);
				}
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
	 * 上传采购订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadbill")
	@ResponseBody
	public Object uploadbill(HttpServletRequest request, HttpServletResponse response) {
		RbData rbData = new RbData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IbData ibdata = gson.fromJson(res.toLowerCase(), IbData.class);
			String username = ibdata.getUsername();
			String password = ibdata.getPassword();
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

			Catalog catalog = new Catalog();
			catalog.setUserid(u.getId());
			catalog.setMyself(true);
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				rbData.setResult(false);
				rbData.setMessage("药品目录不存在!");
				return rbData;
			}

			List<BillDetailVo> billdetailvos = ibdata.getBilldetailvos();
			for (BillDetailVo billdetailvo : billdetailvos) {
				BillDetail billdetail = dozerBeanMapper.map(billdetailvo, BillDetail.class);
				Bill bill = new Bill();
				bill.setPurchaserid(u.getId());
				bill.setErpid(billdetailvo.getBillid());

				if (!StringUtils.isEmpty(billdetailvo.getMobile())) {
					bill.setMobile(billdetailvo.getMobile());
				}

				bill = billService.findObject(bill);

				if (!StringUtils.isEmpty(billdetail.getCode())) {
					Drug d = new Drug();
					d.setCode(billdetail.getCode());
					d.setCatalogid(catalog.getId());
					d = drugService.findObject(d);
					UserSupplier userSupplier = new UserSupplier();
					userSupplier.setCode(billdetailvo.getErpsucode());
					userSupplier.setUserid(u.getId());
					userSupplier = userSupplierService.findObject(userSupplier);
					if (bill == null) {
						bill = new Bill();
						bill.setPurchaserid(u.getId());
						bill.setErpid(billdetailvo.getBillid());
						bill.setName("ERP" + billdetailvo.getBillid());
						bill.setErpusername(billdetailvo.getErpusername());
						bill.setErpsucode(billdetailvo.getErpsucode());
						if (userSupplier != null) {
							bill.setSupplierid(userSupplier.getSupplierid());
						}
						if (StringUtils.isEmpty(billdetailvo.getMobile())) {
							DrugSupplier drugSupplier = new DrugSupplier();
							drugSupplier.setDrugid(d.getId());
							drugSupplier = drugSupplierService.findObject(drugSupplier);
							if (drugSupplier != null) {
								Agent agent = new Agent();
								agent.setMobileno(drugSupplier.getMobile());
								agent.setPurchaserid(u.getId());
								agent = agentService.findObject(agent);
								if (agent != null) {
									bill.setAgentid(agent.getId());
								}
							}
						} else {
							bill.setMobile(billdetailvo.getMobile());
							Agent agent = new Agent();
							agent.setPurchaserid(u.getId());
							agent.setMobileno(billdetailvo.getMobile());
							agent = agentService.findObject(agent);
							if (agent != null) {
								bill.setAgentid(agent.getId());
							}
						}
						bill.setStatus(-1);
						billService.save(bill);
					}
					BillDetail t = new BillDetail();
					t.setBillid(bill.getId());
					t.setCode(billdetailvo.getCode());
					t = billDetailService.findObject(t);
					if (t == null) {
						if (d != null) {
							billdetail.setDrugid(d.getId());
							billdetail.setBillid(bill.getId());
							billdetail.setFactory(d.getFactory());
							billdetail.setSpecifications(d.getSpecifications());
							billdetail.setUnit(d.getUnit());
							billdetail.setName(d.getName());
							billdetail.setStatus(0);
						}
						billDetailService.save(billdetail);
						billService.updateNum(bill.getId());
					}
				}
			}
			return rbData;
		} catch (Exception e) {
			logger.error(e);
			rbData.setResult(false);
			rbData.setMessage(e.getMessage());
			return rbData;
		}
	}

	/**
	 * 上传采购订单状态 (合同号 数量 时间)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("uploadbilldetalstatus")
	@ResponseBody
	public Object uploadbilldetalstatus(HttpServletRequest request, HttpServletResponse response) {
		RbData rbData = new RbData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IbData ibdata = gson.fromJson(res.toLowerCase(), IbData.class);
			String username = ibdata.getUsername();
			String password = ibdata.getPassword();
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

			Catalog catalog = new Catalog();
			catalog.setUserid(u.getId());
			catalog.setMyself(true);
			catalog = catalogService.findObject(catalog);

			if (catalog == null) {
				rbData.setResult(false);
				rbData.setMessage("药品目录不存在!");
				return rbData;
			}

			List<BillDetailVo> billdetailvos = ibdata.getBilldetailvos();
			for (BillDetailVo billdetailvo : billdetailvos) {

				billDetailService.updateContractStatus(billdetailvo.getBillid(), billdetailvo.getCode(), billdetailvo.getContractno(), billdetailvo.getContractnum(), billdetailvo.getContracttime());

			}
			return rbData;
		} catch (Exception e) {
			logger.error(e);
			rbData.setResult(false);
			rbData.setMessage(e.getMessage());
			return rbData;
		}
	}

	/**
	 * 查询采购订单状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("querybill")
	@ResponseBody
	public Object querybill(HttpServletRequest request, HttpServletResponse response) {
		RqbillData rbData = new RqbillData();
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IqbillData ibdata = gson.fromJson(res.toLowerCase(), IqbillData.class);
			String username = ibdata.getUsername();
			String password = ibdata.getPassword();
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
			List<BillDetailStatusVo> billdetailvos = billDetailService.querybilldetailstatus(u.getId(), ibdata.getUpdatetime());
			rbData.setDetailstatus(billdetailvos);
			return rbData;
		} catch (Exception e) {
			logger.error(e);
			rbData.setResult(false);
			rbData.setMessage(e.getMessage());
			return rbData;
		}
	}

	@RequestMapping("order")
	@ResponseBody
	public Object export(HttpServletRequest request, HttpServletResponse response) {
		RoData reData = new RoData();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IoData iedata = gson.fromJson(res.toLowerCase(), IoData.class);
			String username = iedata.getUsername();
			String password = iedata.getPassword();
			String orderdatestr = iedata.getOrderdate();
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

			List<OrderVo> orders = new ArrayList<OrderVo>();
			OrderVo order = new OrderVo();
			orders.add(order);
			order.setDelivery("汇仁医药");
			order.setDeliverycode("0001");
			order.setStore("汇仁堂福州路店");
			order.setStorecode("0001");
			order.setOrderno("201247401");
			order.setOrderdate(formatter.parse(orderdatestr));
			List<OrderDetailVo> orderdetails = new ArrayList<OrderDetailVo>();
			order.setOrderdetails(orderdetails);
			OrderDetailVo od = new OrderDetailVo();
			od.setCode("001");
			od.setName("汇仁肾宝");
			od.setFactory("汇仁医药");
			od.setSpecifications("0.7g*126片/瓶");
			od.setNum(100);
			od.setAmount(new BigDecimal("24100"));
			od.setExpirationDate(formatter.parse("2018-12-22"));
			od.setProductionDate(formatter.parse("2016-12-22"));
			od.setBatchno("247481");
			od.setInvoicecode("98754137741");
			od.setPrice(new BigDecimal("241"));
			orderdetails.add(od);

			OrderDetailVo od2 = new OrderDetailVo();
			od2.setCode("002");
			od2.setName("六味地黄丸");
			od2.setFactory("汇仁医药");
			od2.setSpecifications("60颗/瓶");
			od2.setNum(100);
			od2.setAmount(new BigDecimal("24100"));
			od2.setExpirationDate(formatter.parse("2018-12-22"));
			od2.setProductionDate(formatter.parse("2016-12-22"));
			od2.setBatchno("247482");
			od2.setInvoicecode("98754137741");
			od2.setPrice(new BigDecimal("18"));
			orderdetails.add(od2);

			OrderVo order2 = new OrderVo();
			orders.add(order2);
			order2.setDelivery("汇仁医药");
			order2.setDeliverycode("0001");
			order2.setStore("汇仁堂福州路店");
			order2.setStorecode("0001");
			order2.setOrderno("201247402");
			order2.setOrderdate(formatter.parse(orderdatestr));
			List<OrderDetailVo> orderdetails2 = new ArrayList<OrderDetailVo>();
			order2.setOrderdetails(orderdetails2);
			OrderDetailVo od3 = new OrderDetailVo();
			od3.setCode("001");
			od3.setName("汇仁肾宝");
			od3.setFactory("汇仁医药");
			od3.setSpecifications("0.7g*126片/瓶");
			od3.setNum(100);
			od3.setAmount(new BigDecimal("24100"));
			od3.setExpirationDate(formatter.parse("2018-12-22"));
			od3.setProductionDate(formatter.parse("2016-12-22"));
			od3.setBatchno("247481");
			od3.setInvoicecode("98754137741");
			od3.setPrice(new BigDecimal("241"));
			orderdetails2.add(od3);

			OrderDetailVo od4 = new OrderDetailVo();
			od4.setCode("002");
			od4.setName("六味地黄丸");
			od4.setFactory("汇仁医药");
			od4.setSpecifications("60颗/瓶");
			od4.setNum(100);
			od4.setAmount(new BigDecimal("24100"));
			od4.setExpirationDate(formatter.parse("2018-12-22"));
			od4.setProductionDate(formatter.parse("2016-12-22"));
			od4.setBatchno("247482");
			od4.setInvoicecode("98754137741");
			od4.setPrice(new BigDecimal("18"));
			orderdetails2.add(od4);
			reData.setResult(true);
			reData.setOrders(orders);
			return reData;
		} catch (Exception e) {
			logger.error(e);
			reData.setResult(false);
			reData.setMessage(e.getMessage());
			return reData;
		}
	}

}
