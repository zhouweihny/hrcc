package com.modules.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.commons.base.Constants;
import com.modules.pojo.Accesstoken;
import com.modules.pojo.User;
import com.modules.service.AccesstokenService;
import com.modules.service.UserService;

/**
 * @author Du.Jun
 * 
 */
@Repository
public class WebInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AccesstokenService accesstokenService;
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getContextPath();
		if (request.getSession().getAttribute(Constants.USER) == null) {
			if (request.getParameter("accesstoken") != null) {
				Accesstoken accesstoken = new Accesstoken();
				accesstoken.setAccesstoken(request.getParameter("accesstoken"));
				accesstoken = accesstokenService.findObject(accesstoken);
				if (accesstoken != null) {
					User user = new User();
					user.setId(accesstoken.getUserid());
					user = userService.findObject(user);
					if (user != null) {
						request.getSession().setAttribute(Constants.USER, user);
						return super.preHandle(request, response, handler);
					}
				}
			}
			if (request.getHeader("x-requested-with") == null) {
				response.sendRedirect(path + "/login.do");
			} else {
				response.setHeader("sessionstatus", "timeout");
			}
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
