package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.StringEscapeUtils;
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
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.material.ArticleMaterial;
import com.commons.util.wx.bean.material.ArticleMaterial.Article;
import com.commons.util.wx.bean.material.MaterialResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxMaterialTwService;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.SysWxMaterialTw;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxmaterialtw")
public class SysWxMaterialTwController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxMaterialTwController.class);

	@Autowired
	private SysWxMaterialTwService sysWxMaterialTwService;

	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWxMaterialTw sysWxMaterialTw, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWxMaterialTw.getId())) {
				sysWxMaterialTwService.save(sysWxMaterialTw);
			} else {
				sysWxMaterialTwService.update(sysWxMaterialTw);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxMaterialTw sysWxMaterialTw) {
		try {
			sysWxMaterialTwService.delete(sysWxMaterialTw);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxMaterialTw sysWxMaterialTw) {
		ModelAndView modelAndView = new ModelAndView("syswxmaterialtw/info");
		try {
			if (!StringUtils.isEmpty(sysWxMaterialTw.getId())) {
				SysWxMaterialTw data = sysWxMaterialTwService.findObject(sysWxMaterialTw);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxMaterialTw sysWxMaterialTw) {
		try {
			SysWxMaterialTw data = sysWxMaterialTwService.findObject(sysWxMaterialTw);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxmaterialtw/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxmaterialtw/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			User user = (User) this.session.getAttribute(Constants.USER);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				params.put("userid", user.getId());
			PageResult<SysWxMaterialTw> data = sysWxMaterialTwService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping("updatecontent")
	@ResponseBody
	public Object ass(String content, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			SysWxMaterialTw sysWxMaterialTw = new SysWxMaterialTw();
			sysWxMaterialTw.setId(id);
			sysWxMaterialTw = sysWxMaterialTwService.findObject(sysWxMaterialTw);
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			JsonParser parser = new JsonParser();
			List<Article> articles = new ArrayList<Article>();
			JsonArray jarray = parser.parse(content).getAsJsonArray();
			for (JsonElement obj : jarray) {
				Article article = gson.fromJson(obj, Article.class);
				article.setContent(article.getContent());
				articles.add(article);
			}
			ArticleMaterial articleMaterial = new ArticleMaterial();
			articleMaterial.setArticles(articles);
			sysWxMaterialTw.setContent(gson.toJson(articleMaterial));
			SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
			sysWxAccesstoken.setWxid(sysWxMaterialTw.getWxid());
			sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
			
		
			
			MaterialResponse materialResponse = WxUtil.addNews(sysWxAccesstoken.getAccesstoken(), gson.toJson(articleMaterial));
			if (!StringUtils.isEmpty(materialResponse.getMedia_id())) {
				if (!StringUtils.isEmpty(sysWxMaterialTw.getMediaid()))
					WxUtil.delMaterial(sysWxAccesstoken.getAccesstoken(), sysWxMaterialTw.getMediaid());
				sysWxMaterialTw.setMediaid(materialResponse.getMedia_id());
				sysWxMaterialTwService.update(sysWxMaterialTw);
			} else {
				return this.getJsonResult("9999", materialResponse.getErrmsg());
			}

		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "/tuwenedit")
	public ModelAndView tuwenedit(String id, String wxid, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxmaterialtw/tuwen_editS");
		SysWxMaterialTw sysWxMaterialTw = new SysWxMaterialTw();
		sysWxMaterialTw.setId(id);
		try {
			Gson gson = new Gson();
			SysWxMaterialTw data = sysWxMaterialTwService.findObject(sysWxMaterialTw);
			modelAndView.addObject("data", data);
			if (!StringUtils.isEmpty(data.getContent())) {
				ArticleMaterial articleMaterial = gson.fromJson(data.getContent(), ArticleMaterial.class);
				List<Article> articles = articleMaterial.getArticles();
				modelAndView.addObject("articles", gson.toJson(articles));
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

}