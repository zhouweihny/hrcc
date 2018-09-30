package com.commons.shirosession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 集成websession管理器
 * 
 * @author Du.Jun
 *
 */
public class MySessionManager extends DefaultWebSessionManager {

	private static Log log = LogFactory.getLog(MySessionManager.class);

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		return super.getSessionId(request, response);
	}

	@Override
	protected void onStart(Session session, SessionContext context) {
		// 判断是否是http请求
		if (!WebUtils.isHttp(context)) {
			log.debug("SessionContext argument is not HTTP compatible or does not have an HTTP request/response " + "pair. No session ID cookie will be set.");
			return;

		}
		super.onStart(session, context);
	}
}