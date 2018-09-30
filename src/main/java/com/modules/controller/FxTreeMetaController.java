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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.google.gson.Gson;
import com.modules.service.FxMetaService;
import com.modules.service.FxTreeMetaDatasService;
import com.modules.service.FxTreeMetaService;
import com.modules.service.StoreService;
import com.modules.vo.TreeMetaDataVo;
import com.modules.vo.TreeMetaVo;
import com.modules.pojo.FxMeta;
import com.modules.pojo.FxTreeMeta;
import com.modules.pojo.FxTreeMetaDatas;
import com.modules.pojo.Store;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxtreemeta")
public class FxTreeMetaController extends BaseController {

	private static Logger logger = Logger.getLogger(FxTreeMetaController.class);

	@Autowired
	private FxTreeMetaService fxTreeMetaService;

	@Autowired
	private FxMetaService fxmetaService;

	@Autowired
	private FxTreeMetaDatasService fxTreeMetaDatasService;

	@Autowired
	private StoreService storeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxTreeMeta fxTreeMeta, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxTreeMeta.getId())) {
				fxTreeMetaService.save(fxTreeMeta);
			} else {
				fxTreeMetaService.update(fxTreeMeta);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxTreeMeta fxTreeMeta) {
		try {
			fxTreeMetaService.delete(fxTreeMeta);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxTreeMeta fxTreeMeta) {
		ModelAndView modelAndView = new ModelAndView("fxtreemeta/info");
		try {
			if (!StringUtils.isEmpty(fxTreeMeta.getId())) {
				FxTreeMeta data = fxTreeMetaService.findObject(fxTreeMeta);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxTreeMeta fxTreeMeta) {
		try {
			FxTreeMeta data = fxTreeMetaService.findObject(fxTreeMeta);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtreemeta/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxtreemeta/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxTreeMeta> data = fxTreeMetaService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "metabytree", method = RequestMethod.POST)
	@ResponseBody
	public Object metabytree(String treeid, String storeid, String storetypeid) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		String companyid = company.getId();
		try {
			List<FxMeta> metas = fxmetaService.findList(new FxMeta());
			List<TreeMetaVo> treemetas = new ArrayList<TreeMetaVo>();
			for (FxMeta meta : metas) {
				TreeMetaVo treemeta = fxTreeMetaService.findByTreepath(treeid, meta.getId(), companyid, storeid, storetypeid);
				if (treemeta != null) {
					treemetas.add(treemeta);
				}
			}
			return this.getJsonResult(treemetas);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "modifytreemeta", method = RequestMethod.POST)
	@ResponseBody
	public Object modifytreemeta(String jsonStr, String storeid) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		String companyid = company.getId();
		try {
			Gson gson = new Gson();
			TreeMetaVo treeMetaVo = gson.fromJson(jsonStr, TreeMetaVo.class);
			FxTreeMeta fxTreeMeta = new FxTreeMeta();
			fxTreeMeta.setTreeid(treeMetaVo.getTreeid());
			fxTreeMeta.setMetaid(treeMetaVo.getMetaid());
			fxTreeMeta = fxTreeMetaService.findObject(fxTreeMeta);
			if (fxTreeMeta == null || treeMetaVo.getId() == null) {
				fxTreeMeta = new FxTreeMeta();
				if (company.getUsername().equalsIgnoreCase(Constants.ADMIN))
					fxTreeMeta.setStandard(true);
				fxTreeMeta.setMetaid(treeMetaVo.getMetaid());
				fxTreeMeta.setStoretypeid(treeMetaVo.getStoretypeid());
				fxTreeMeta.setRemark(treeMetaVo.getRemark());
				fxTreeMeta.setScope(treeMetaVo.getScope());
				fxTreeMeta.setStoreid(storeid);
				fxTreeMeta.setUserid(companyid);
				fxTreeMeta.setTreeid(treeMetaVo.getTreeid());
				fxTreeMetaService.save(fxTreeMeta);
				if (treeMetaVo.getTreeMetaDatas() != null && treeMetaVo.getTreeMetaDatas().size() != 0) {
					for (TreeMetaDataVo treeMetaDataVo : treeMetaVo.getTreeMetaDatas()) {
						FxTreeMetaDatas fxTreeMetaDatas = new FxTreeMetaDatas();
						fxTreeMetaDatas.setTreemetaid(fxTreeMeta.getId());
						fxTreeMetaDatas.setMetadataid(treeMetaDataVo.getMetadataid());
						fxTreeMetaDatas.setRemark(treeMetaDataVo.getRemark());
						fxTreeMetaDatas.setVal(treeMetaDataVo.getVal());
						fxTreeMetaDatasService.save(fxTreeMetaDatas);

					}
				}

			} else {
				if (company.getUsername().equalsIgnoreCase(Constants.ADMIN))
					fxTreeMeta.setStandard(true);
				fxTreeMeta.setMetaid(treeMetaVo.getMetaid());
				fxTreeMeta.setStoretypeid(treeMetaVo.getStoretypeid());
				fxTreeMeta.setRemark(treeMetaVo.getRemark());
				fxTreeMeta.setScope(treeMetaVo.getScope());
				fxTreeMeta.setTreeid(treeMetaVo.getTreeid());
				fxTreeMetaService.update(fxTreeMeta);

				if (treeMetaVo.getTreeMetaDatas() != null && treeMetaVo.getTreeMetaDatas().size() != 0) {
					for (TreeMetaDataVo treeMetaDataVo : treeMetaVo.getTreeMetaDatas()) {
						FxTreeMetaDatas fxTreeMetaDatas = new FxTreeMetaDatas();
						fxTreeMetaDatas.setTreemetaid(fxTreeMeta.getId());
						fxTreeMetaDatas.setMetadataid(treeMetaDataVo.getMetadataid());
						fxTreeMetaDatas = fxTreeMetaDatasService.findObject(fxTreeMetaDatas);
						if (fxTreeMetaDatas != null) {
							fxTreeMetaDatas.setRemark(treeMetaDataVo.getRemark());
							fxTreeMetaDatas.setVal(treeMetaDataVo.getVal());
							fxTreeMetaDatasService.update(fxTreeMetaDatas);
						} else {
							fxTreeMetaDatas = new FxTreeMetaDatas();
							fxTreeMetaDatas.setTreemetaid(fxTreeMeta.getId());
							fxTreeMetaDatas.setMetadataid(treeMetaDataVo.getMetadataid());
							fxTreeMetaDatas.setRemark(treeMetaDataVo.getRemark());
							fxTreeMetaDatas.setVal(treeMetaDataVo.getVal());
							fxTreeMetaDatasService.save(fxTreeMetaDatas);
						}
					}
				}
			}
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "modifystoretreemeta", method = RequestMethod.POST)
	@ResponseBody
	public Object modifytreemeta(String treeid, String storeid, String metaid) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		String companyid = company.getId();
		try {
			Store store = new Store();
			store.setId(storeid);
			store = storeService.findObject(store);
			TreeMetaVo vo = fxTreeMetaService.findByTreepath(treeid, metaid, companyid, storeid, store.getTypeid());
			vo.setTreeid(treeid);
			return this.getJsonResult(vo);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}
}