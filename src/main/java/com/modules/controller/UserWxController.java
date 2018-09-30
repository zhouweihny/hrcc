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
import com.commons.base.BaseController;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.UserWxService;
import com.modules.pojo.UserWx;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("userwx")
public class UserWxController extends BaseController {

	private static Logger logger = Logger.getLogger(UserWxController.class);

	@Autowired
	private UserWxService userWxService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(UserWx userWx, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(userWx.getId())) {
				userWxService.save(userWx);
			} else {
				userWxService.update(userWx);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(UserWx userWx) {
		try {
			userWxService.delete(userWx);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

}