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
import freemarker.template.TemplateScalarModel;
import com.modules.service.SysRoleService;
import com.modules.pojo.SysRole;

@Component("sysRoleDirective")
public class SysRoleDirective implements TemplateDirectiveModel {

	@Autowired
	private SysRoleService sysRoleService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数
		if (params.containsKey("id")) {
			if (params.get("id") != null) {
				java.lang.String primarykey = ((TemplateScalarModel) params.get("id")).getAsString();
				try {
					SysRole entity = new SysRole();
					entity.setId(primarykey);
					SysRole sysRole = sysRoleService.findObject(entity);
					if (sysRole == null)
						sysRole = new SysRole();
					env.setVariable("sysRole", ObjectWrapper.DEFAULT_WRAPPER.wrap(sysRole));
				} catch (Exception e) {
					throw new TemplateException(e.getMessage(), env);
				}
			}else{
				env.setVariable("sysRole", ObjectWrapper.DEFAULT_WRAPPER.wrap(new SysRole()));
			}
		}else{
			env.setVariable("sysRole", ObjectWrapper.DEFAULT_WRAPPER.wrap(new SysRole()));
		}
		
		if (body != null) {
			body.render(env.getOut());
		}

	}
}