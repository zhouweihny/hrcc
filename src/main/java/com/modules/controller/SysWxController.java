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

import java.util.HashMap;
import java.util.Map;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.res.BaseResponse;
import com.commons.util.wx.menu.Menu;
import com.commons.util.wx.menu.Menu.Button;
import com.commons.util.wx.menu.Menu.SubButton;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxMenuService;
import com.modules.service.SysWxService;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.SysWxMenu;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswx")
public class SysWxController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxController.class);

	@Autowired
	private SysWxService sysWxService;
	@Autowired
	private SysWxMenuService sysWxMenuService;
	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWx sysWx, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWx.getId())) {
				sysWxService.save(sysWx);
			} else {
				sysWxService.update(sysWx);
			}

			sysWxAccesstokenService.refresh(sysWx);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWx sysWx) {
		try {
			sysWx = sysWxService.findObject(sysWx);

			SysWx tsysWx = new SysWx();
			tsysWx.setId(sysWx.getId());
			sysWxService.delete(tsysWx);
			SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
			sysWxAccesstoken.setWxid(sysWx.getId());
			sysWxAccesstokenService.delete(sysWxAccesstoken);

		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWx sysWx) {
		ModelAndView modelAndView = new ModelAndView("syswx/info");
		try {
			if (!StringUtils.isEmpty(sysWx.getId())) {
				SysWx data = sysWxService.findObject(sysWx);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWx sysWx) {
		try {
			SysWx data = sysWxService.findObject(sysWx);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswx/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswx/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			User user = (User) this.session.getAttribute(Constants.USER);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				params.put("userid", user.getId());
			// 参数设置
			PageResult<SysWx> data = sysWxService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "bandmsg", method = RequestMethod.GET)
	@ResponseBody
	public Object bandmsg(SysWx sysWx) {
		ModelAndView modelAndView = new ModelAndView("syswx/bandmsg");
		try {
			if (!StringUtils.isEmpty(sysWx.getId())) {
				SysWx data = sysWxService.findObject(sysWx);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping("syncmenu")
	@ResponseBody
	public Object syncmenu(String wxid, HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(wxid)) {
				SysWx sysWx = new SysWx();
				sysWx.setId(wxid);
				sysWx = sysWxService.findObject(sysWx);
				params.put("wxid", wxid);
				PageResult<SysWxMenu> page = sysWxMenuService.findList(params, new Page(false));
				Menu wxmenu = new Menu();
				for (SysWxMenu menu : page.getData()) {
					if (StringUtils.isEmpty(menu.getParentid())) {
						Button button = wxmenu.new Button();
						button.setKey(menu.getId());
						button.setName(menu.getName());
						button.setType(menu.getType());
						// button.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
						// + sysWx.getAppid() + "&redirect_uri=" + menu.getUrl()
						// +
						// "&response_type=code&scope=snsapi_base&state=000#wechat_redirect");
						button.setUrl(menu.getUrl());
						wxmenu.addButton(button);
					}
				}
				for (SysWxMenu menu : page.getData()) {
					if (wxmenu.getButton() != null) {
						for (Button button : wxmenu.getButton()) {
							if (menu.getParentid().equals(button.getKey())) {
								SubButton subButton = wxmenu.new SubButton();
								subButton.setKey(menu.getId());
								subButton.setName(menu.getName());
								subButton.setType(menu.getType());
								// subButton.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
								// + sysWx.getAppid() + "&redirect_uri=" +
								// menu.getUrl()
								// +
								// "&response_type=code&scope=snsapi_base&state=000#wechat_redirect");
								subButton.setUrl(menu.getUrl());
								button.addSubButton(subButton);
							}
						}
					}
				}
				SysWxAccesstoken entity = new SysWxAccesstoken();
				entity.setWxid(wxid);
				SysWxAccesstoken sysWxAccesstoken = sysWxAccesstokenService.findObject(entity);
				if (sysWxAccesstoken != null) {
					BaseResponse res = WxUtil.syncMenu(wxmenu, sysWxAccesstoken.getAccesstoken());
					if (res.errcode != 0) {
						return this.getJsonResult("9999", res.errmsg);
					}
				} else {
					return this.getJsonResult("9999", "微信Accesstoken不存在");
				}

			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

}