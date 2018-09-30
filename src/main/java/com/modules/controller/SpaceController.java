package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.AutocompareService;
import com.modules.service.CatalogService;
import com.modules.service.DrugDrugDelService;
import com.modules.service.DrugDrugService;
import com.modules.service.SpaceCatalogService;
import com.modules.service.SpaceService;
import com.modules.vo.DrugDrugVo;
import com.modules.vo.DrugVo;
import com.modules.vo.SpaceCatalogVo;
import com.modules.pojo.Autocompare;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.DrugDrug;
import com.modules.pojo.DrugDrugDel;
import com.modules.pojo.Space;
import com.modules.pojo.SpaceCatalog;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("space")
public class SpaceController extends BaseController {

	private static Logger logger = Logger.getLogger(SpaceController.class);

	@Autowired
	private SpaceService spaceService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private SpaceCatalogService spaceCatalogService;

	@Autowired
	private DrugDrugService drugDrugService;

	@Autowired
	private AutocompareService autocompareService;

	@Autowired
	private DrugDrugDelService drugDrugDelService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Space space, HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = (User) session.getAttribute(Constants.USER);
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			if (StringUtils.isEmpty(space.getId())) {
				space.setUserid(userid);
				spaceService.save(space);
			} else {
				spaceService.update(space);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Space space) {
		try {
			if (!StringUtils.isEmpty(space.getId()))
				spaceService.delete(space);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Space space) {
		ModelAndView modelAndView = new ModelAndView("space/info");
		try {
			if (!StringUtils.isEmpty(space.getId())) {
				Space data = spaceService.findObject(space);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Space space) {
		try {
			Space data = spaceService.findObject(space);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("space/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		User user = (User) session.getAttribute(Constants.USER);
		ModelAndView modelAndView = new ModelAndView("space/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			params.put("userid", userid);
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Space> data = spaceService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "spaces")
	@ResponseBody
	public Object spaces(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			Space space = new Space();
			space.setUserid(userid);
			List<Space> spaces = spaceService.findList(space);
			return this.getJsonResult(spaces);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "catalogs")
	@ResponseBody
	public Object catalogs(HttpServletRequest request, HttpServletResponse response, String spaceid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			Catalog catalog = new Catalog();
			catalog.setUserid(userid);
			List<Catalog> catalogs = catalogService.findList(catalog);
			SpaceCatalog spaceCatalog = new SpaceCatalog();
			if (!StringUtils.isEmpty(spaceid))
				spaceCatalog.setSpaceid(spaceid);
			List<SpaceCatalog> spaceCatalogs = spaceCatalogService.findList(spaceCatalog);
			List<SpaceCatalogVo> list = new ArrayList<SpaceCatalogVo>();
			for (Catalog c : catalogs) {
				for (SpaceCatalog sc : spaceCatalogs) {
					if (sc.getCatalogid().equals(c.getId())) {
						if (!sc.getStandard()) {
							SpaceCatalogVo spaceCatalogVo = new SpaceCatalogVo();
							spaceCatalogVo.setCatalogid(c.getId());
							spaceCatalogVo.setName(c.getName());
							list.add(spaceCatalogVo);
						}
					}
				}

			}
			return this.getJsonResult(list);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "drugs")
	@ResponseBody
	public Object drugs(HttpServletRequest request, HttpServletResponse response, String code, String name, String factory, String spaceid, String catalogid, Integer compared, Boolean showcompared,
			Boolean checked, String stype, String sfield, Integer currentPage, Integer pageSize) {
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("spaceid", spaceid);
			params.put("catalogid", catalogid);

			if (!StringUtils.isEmpty(showcompared)) {
				params.put("showcompared", showcompared);
			}
			if (!StringUtils.isEmpty(checked)) {
				params.put("checked", checked);
			}

			if (!StringUtils.isEmpty(compared)) {
				params.put("compared", compared);
			}
			if (!StringUtils.isEmpty(code)) {
				params.put("code", code);
			}
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			if (!StringUtils.isEmpty(factory)) {
				params.put("factory", factory);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<DrugVo> data = spaceService.findDrugs(params, page);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "querycomparedrugs")
	@ResponseBody
	public Object querycomparedrugs(HttpServletRequest request, HttpServletResponse response, String drugid, String name, String factory, String specifications, String spaceid, Integer currentPage,
			Integer pageSize) {
		long start = System.currentTimeMillis();
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();

			if (!StringUtils.isEmpty(drugid))
				params.put("drugid", drugid);
			if (!StringUtils.isEmpty(spaceid))
				params.put("spaceid", spaceid);
			if (!StringUtils.isEmpty(name))
				params.put("name", name);
			if (!StringUtils.isEmpty(factory))
				params.put("factory", factory);
			if (!StringUtils.isEmpty(specifications))
				params.put("specifications", specifications);

			params.put("smart", false);

			PageResult<Drug> data = spaceService.findcomparedrugs(params, page);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", data);
			map.put("time", System.currentTimeMillis() - start);

			return this.getJsonResult(map);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "compare")
	@ResponseBody
	public Object compare(HttpServletRequest request, HttpServletResponse response, String drugid, String sdrugid, String spaceid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			DrugDrug drugdrug = new DrugDrug();
			drugdrug.setDrugid(drugid);
			drugdrug.setSpaceid(spaceid);
			drugDrugService.delete(drugdrug);
			drugdrug.setSdrugid(sdrugid);
			drugdrug.setOperatorid(user.getId());
			drugdrug.setNum(0);
			drugdrug.setChecked(false);
			drugdrug.setUserid(user.getId());
			drugDrugService.save(drugdrug);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "ignore")
	@ResponseBody
	public Object ignore(HttpServletRequest request, HttpServletResponse response, String drugid, String spaceid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			DrugDrug drugdrug = new DrugDrug();
			drugdrug.setDrugid(drugid);
			drugdrug.setSpaceid(spaceid);
			drugDrugService.delete(drugdrug);
			drugdrug.setOperatorid(user.getId());
			drugdrug.setNum(1);
			drugDrugService.save(drugdrug);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "checked")
	@ResponseBody
	public Object checked(HttpServletRequest request, HttpServletResponse response, String drugid, String sdrugid, String spaceid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			DrugDrug drugdrug = new DrugDrug();
			drugdrug.setDrugid(drugid);
			drugdrug.setSpaceid(spaceid);
			drugdrug = drugDrugService.findObject(drugdrug);
			if (drugdrug != null && drugdrug.getNum() == 0) {
				drugdrug.setChecked(true);
				drugdrug.setChecker(user.getId());
				drugDrugService.update(drugdrug);
			}
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "delcompare")
	@ResponseBody
	public Object delcompare(HttpServletRequest request, HttpServletResponse response, String drugid, String spaceid) {
		try {
			User user = (User) session.getAttribute(Constants.USER);
			DrugDrug drugdrug = new DrugDrug();
			drugdrug.setDrugid(drugid);
			drugdrug.setSpaceid(spaceid);
			drugdrug.setNum(0);
			drugdrug = drugDrugService.findObject(drugdrug);
			DrugDrug tdd = new DrugDrug();
			tdd.setId(drugdrug.getId());
			drugDrugService.delete(tdd);
			DrugDrugDel drugDrugDel = dozerBeanMapper.map(drugdrug, DrugDrugDel.class);
			drugDrugDel.setOperatorid(user.getId());
			drugDrugDelService.save(drugDrugDel);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "findcompared")
	@ResponseBody
	public Object compared(HttpServletRequest request, HttpServletResponse response, String drugid, String spaceid) {
		try {
			return this.getJsonResult(spaceService.findcompared(drugid, spaceid));
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "exportfile")
	public void export(HttpServletRequest request, HttpServletResponse response, String spaceid, String catalogid, Boolean checked) {
		try {
			Page page = new Page(1, 2000);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("spaceid", spaceid);
			params.put("catalogid", catalogid);
			if (!StringUtils.isEmpty(checked)) {
				params.put("checked", checked);
			}
			PageResult<DrugDrugVo> data = spaceService.findcatalogdrugs(params, page);
			try {
				response.reset();
				response.setContentType("application/csv;charset=GBK");
				response.setHeader("Content-Disposition", "attachment;filename=Data" + System.currentTimeMillis() + ".csv");
				response.setCharacterEncoding("GBK");
				PrintWriter out = response.getWriter();
				out.println("编码,参照编码,品名,规格,单位,剂型,厂商,参照品名,参照规格,参照单位,参照剂型,参照厂商");
				for (int i = 1; i <= data.getTotalPages(); i++) {
					PageResult<DrugDrugVo> data2 = spaceService.findcatalogdrugs(params, new Page(i, 2000, false));
					for (DrugDrugVo v : data2.getData()) {
						StringBuilder str = new StringBuilder();
						str.append("\"" + v.getCode()).append("\",");
						str.append("\"" + v.getScode()).append("\",");
						str.append("\"" + v.getName()).append("\",");
						str.append("\"" + v.getSpecifications()).append("\",");
						str.append("\"" + v.getUnit()).append("\",");
						str.append("\"" + v.getDosageform()).append("\",");
						str.append("\"" + v.getFactory()).append("\",");
						str.append("\"" + v.getSname()).append("\",");
						str.append("\"" + v.getSspecifications()).append("\",");
						str.append("\"" + v.getSunit()).append("\",");
						str.append("\"" + v.getSdosageform()).append("\",");
						str.append("\"" + v.getSfactory()).append("\"");
						out.println(str.toString().replaceAll("null", ""));
					}
				}
				out.flush();
				out.close();
			} catch (Exception e) {
				logger.error(e);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping(value = "modcatalog")
	public ModelAndView modcatalog(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("space/modcatalog");
		return modelAndView;
	}

	@RequestMapping(value = "varietycontrol")
	public ModelAndView varietycontrol(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("space/varietycontrol");
		return modelAndView;
	}

	@RequestMapping(value = "similarDrug")
	public ModelAndView similarDrug(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("space/similarDrug");
		return modelAndView;
	}

	@RequestMapping(value = "checkcompare")
	@ResponseBody
	public Object checkcompare(HttpServletRequest request, HttpServletResponse response, String spaceid, String catalogid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(1, 1);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("userid", user.getId());
			// 参数设置
			params.put("stype", "desc");
			params.put("sfield", "startdate");
			params.put("spaceid", spaceid);
			params.put("catalogid", catalogid);
			PageResult<Autocompare> data = autocompareService.findList(params, page);
			if (data.getData().size() == 0 || data.getData().get(0).getEnddate() != null) {
				return this.getJsonResult(true);
			} else {
				return this.getJsonResult(false);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "autocompare")
	@ResponseBody
	public Object autocompare(HttpServletRequest request, HttpServletResponse response, final String spaceid, final String catalogid, String flag) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(1, 1);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("userid", user.getId());
			// 参数设置
			params.put("stype", "desc");
			params.put("sfield", "startdate");
			params.put("spaceid", spaceid);
			params.put("catalogid", catalogid);
			PageResult<Autocompare> data = autocompareService.findList(params, page);
			if (data.getData().size() == 0 || data.getData().get(0).getEnddate() != null) {
				new Thread() {
					public void run() {
						try {
							Autocompare autocompare = new Autocompare();
							autocompare.setCatalogid(catalogid);
							autocompare.setSpaceid(spaceid);
							autocompare.setStartdate(new Date());
							autocompareService.save(autocompare);
							autocompareService.autoCompare(autocompare);
						} catch (Exception e) {
							logger.error(e);
						}
					}

				}.start();
			} else {
				return this.getJsonResult("9999", "该自动对照仍未完成!");
			}

		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "zworkflow")
	public ModelAndView zworkflow(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("space/zworkflow");
		return modelAndView;
	}

	@RequestMapping(value = "varietycontrols")
	public ModelAndView varietycontrols(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("space/varietycontrols");
		return modelAndView;
	}

	@RequestMapping(value = "varietyreview")
	public ModelAndView varietyreview(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("space/varietyreview");
		return modelAndView;
	}
}