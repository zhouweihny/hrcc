package com.modules.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.UserWxService;
import com.modules.vo.Wx;
import com.modules.pojo.User;

@Component("sysWxListDirective")
public class SysWxListDirective implements TemplateDirectiveModel {

	@Autowired
	private UserWxService userWxService;
	@Autowired
	public HttpSession session;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			User user = (User) session.getAttribute(Constants.USER);
			HashMap<String, Object> ps = new HashMap<String, Object>();
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				ps.put("userid", user.getId());
			ps.put("selected", 1);
			PageResult<Wx> data = userWxService.findWxList(ps, new Page(false));
			env.setVariable("sysWxs", ObjectWrapper.DEFAULT_WRAPPER.wrap(data.getData()));
		} catch (Exception e) {
			throw new TemplateException(e.getMessage(), env);
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}