package com.modules.directive;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.modules.service.SysRoleService;
import com.modules.pojo.SysRole;

@Component("sysRoleListDirective")
public class SysRoleListDirective implements TemplateDirectiveModel {

	@Autowired
	private SysRoleService sysRoleService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		// 处理传入的参数
		try {
			env.setVariable("sysRoles", ObjectWrapper.DEFAULT_WRAPPER.wrap(sysRoleService.findList(new SysRole())));
		} catch (Exception e) {
			throw new TemplateException(e.getMessage(), env);
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}