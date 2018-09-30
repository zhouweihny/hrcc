package com.modules.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.commons.base.BaseController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.Space;
import com.modules.pojo.SpaceCatalog;
import com.modules.pojo.User;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.SpaceCatalogService;
import com.modules.service.SpaceService;
import com.modules.service.UserService;
import com.modules.vo.IcData;
import com.modules.vo.IeData;
import com.modules.vo.IuData;

@Controller
@RequestMapping("api")
public class InterController extends BaseController {

	private static Logger logger = Logger.getLogger(InterController.class);

	@Autowired
	private DrugService drugService;

	@Autowired
	private SpaceService spaceService;

	@Autowired
	private UserService userService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private SpaceCatalogService spaceCatalogService;

	@RequestMapping("createcatalog")
	@ResponseBody
	public Object createcatalog(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IcData icData = gson.fromJson(res, IcData.class);
			String spaceid = icData.getSpaceid();
			String username = icData.getUsername();
			String password = icData.getPassword();
			Catalog catalog = icData.getCatalog();

			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				return this.getJsonResult("9999", "用户名或者密码错误");
			}
			Space space = new Space();
			space.setUserid(u.getId());
			space.setId(spaceid);
			space = spaceService.findObject(space);
			if (space == null) {
				return this.getJsonResult("9999", "对码项目不存在!");
			}

			if (StringUtils.isBlank(catalog.getIremark()))
				return this.getJsonResult("9999", "iremark不能为空!");
			Catalog c = new Catalog();
			c.setIremark(catalog.getIremark());
			c.setUserid(u.getId());
			c = catalogService.findObject(c);
			if (c == null) {
				if (StringUtils.isBlank(catalog.getName()))
					return this.getJsonResult("9999", "目录名称不能为空!");
				catalog.setUserid(u.getId());
				catalogService.save(catalog);
				SpaceCatalog spaceCatalog = new SpaceCatalog();
				spaceCatalog.setSpaceid(spaceid);
				spaceCatalog.setCatalogid(catalog.getId());
				spaceCatalogService.save(spaceCatalog);
			}
			catalog = c;
			return this.getJsonResult(catalog.getId());
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping("upload")
	@ResponseBody
	public Object upload(HttpServletRequest request, HttpServletResponse response) {
		try {

			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IuData iuData = gson.fromJson(res, IuData.class);
			String spaceid = iuData.getSpaceid();
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
				return this.getJsonResult("9999", "用户名或者密码错误");
			}
			Space space = new Space();
			space.setUserid(u.getId());
			space.setId(spaceid);
			space = spaceService.findObject(space);
			if (space == null) {
				return this.getJsonResult("9999", "对码项目不存在!");
			}
			Catalog catalog = new Catalog();
			catalog.setId(catalogid);
			catalog.setUserid(u.getId());
			catalog = catalogService.findObject(catalog);
			if (catalog == null) {
				return this.getJsonResult("9999", "药品目录不存在!");
			}
			for (Drug drug : drugs) {
				drug.setCatalogid(catalogid);
			}
			drugService.save(drugs);
			drugService.removeRepeat(catalogid);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping("export")
	@ResponseBody
	public Object export(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new GsonBuilder().create();
			String res = IOUtils.toString(request.getInputStream(), "UTF-8");
			IeData iedata = gson.fromJson(res, IeData.class);

			String spaceid = iedata.getSpaceid();
			String catalogid = iedata.getCatalogid();
			String username = iedata.getUsername();
			String password = iedata.getPassword();
			List<String> codes = iedata.getCodes();

			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);
			if (u == null) {
				return this.getJsonResult("9999", "用户名或者密码错误");
			}
			Space space = new Space();
			space.setUserid(u.getId());
			space.setId(spaceid);
			space = spaceService.findObject(space);
			if (space == null) {
				return this.getJsonResult("9999", "对码项目不存在!");
			}
			Catalog catalog = new Catalog();
			catalog.setId(catalogid);
			catalog.setUserid(u.getId());
			catalog = catalogService.findObject(catalog);
			if (catalog == null) {
				return this.getJsonResult("9999", "药品目录不存在!");
			}
			return this.getJsonResult(spaceService.findcheckedDrug(spaceid, catalogid, codes));

		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}
