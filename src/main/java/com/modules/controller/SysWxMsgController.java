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
import com.commons.util.wx.bean.msg.ResponseArticleMsg;
import com.commons.util.wx.bean.msg.ResponseArticleMsg.Article;
import com.commons.util.wx.bean.msg.ResponseTextMsg;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.modules.service.SysWxMsgService;
import com.modules.pojo.SysWxMsg;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxmsg")
public class SysWxMsgController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxMsgController.class);

	@Autowired
	private SysWxMsgService sysWxMsgService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWxMsg sysWxMsg, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWxMsg.getId())) {
				sysWxMsgService.save(sysWxMsg);
			} else {
				sysWxMsgService.update(sysWxMsg);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxMsg sysWxMsg) {
		try {
			sysWxMsgService.delete(sysWxMsg);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxMsg sysWxMsg) {
		ModelAndView modelAndView = new ModelAndView("syswxmsg/info");
		try {
			if (!StringUtils.isEmpty(sysWxMsg.getId())) {
				SysWxMsg data = sysWxMsgService.findObject(sysWxMsg);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxMsg sysWxMsg) {
		try {
			SysWxMsg data = sysWxMsgService.findObject(sysWxMsg);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxmsg/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String wxid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxmsg/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(wxid)) {
				params.put("wxid", wxid);
			}
			User user = (User) this.session.getAttribute(Constants.USER);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				params.put("userid", user.getId());
			PageResult<SysWxMsg> data = sysWxMsgService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping("updatecontent")
	@ResponseBody
	public Object ass(String content, String type, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			if ("2".equals(type)) {
				JsonParser parser = new JsonParser();
				List<Article> articles = new ArrayList<Article>();
				JsonArray jarray = parser.parse(content).getAsJsonArray();
				for (JsonElement obj : jarray) {
					Article article = gson.fromJson(obj, Article.class);
					articles.add(article);
				}
				ResponseArticleMsg articleMsg = new ResponseArticleMsg();
				articleMsg.setArticles(articles);
				articleMsg.setArticleCount(articles.size());
				SysWxMsg sysWxMsg = new SysWxMsg();
				sysWxMsg.setId(id);
				sysWxMsg.setContent(gson.toJson(articleMsg));
				sysWxMsgService.update(sysWxMsg);
			} else if ("1".equals(type)) {
				ResponseTextMsg textMsg = new ResponseTextMsg();
				textMsg.setContent(content);
				SysWxMsg sysWxMsg = new SysWxMsg();
				sysWxMsg.setId(id);
				sysWxMsg.setContent(gson.toJson(textMsg));
				sysWxMsgService.update(sysWxMsg);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "/tuwenedit")
	public ModelAndView tuwenedit(String id, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxmsg/tuwen_edit");
		SysWxMsg sysWxMsg = new SysWxMsg();
		sysWxMsg.setId(id);
		try {
			Gson gson = new Gson();
			SysWxMsg data = sysWxMsgService.findObject(sysWxMsg);
			modelAndView.addObject("data", data);
			if (!StringUtils.isEmpty(data.getContent())) {
				ResponseArticleMsg articleMsg = gson.fromJson(data.getContent(), ResponseArticleMsg.class);
				List<Article> articles = articleMsg.getArticles();
				modelAndView.addObject("articles", gson.toJson(articles));
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/wenbenedit")
	public ModelAndView wenbenedit(String id, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxmsg/wenben_edit");
		SysWxMsg sysWxMsg = new SysWxMsg();
		sysWxMsg.setId(id);
		try {
			Gson gson = new Gson();
			SysWxMsg data = sysWxMsgService.findObject(sysWxMsg);
			modelAndView.addObject("data", data);
			if (!StringUtils.isEmpty(data.getContent())) {
				ResponseTextMsg textMsg = gson.fromJson(data.getContent(), ResponseTextMsg.class);
				modelAndView.addObject("text", textMsg.getContent());
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}
 
}