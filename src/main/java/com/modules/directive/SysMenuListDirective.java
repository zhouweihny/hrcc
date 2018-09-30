package com.modules.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commons.sql.Page;
import com.modules.pojo.SysMenu;
import com.modules.service.SysMenuService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("sysMenuListDirective")
public class SysMenuListDirective implements TemplateDirectiveModel {

	@Autowired
	private SysMenuService sysMenuService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<SysMenu> sysMenus;
		try {
			SysMenu entity = new SysMenu();
			if (params.containsKey("parent"))
				entity.setUrl("");

			sysMenus =  sysMenuService.findList(new HashMap<String, Object>(), new Page(false)).getData();
		} catch (Exception e) {
			throw new TemplateException(e.getMessage(), env);
		}
		env.setVariable("sysMenus", ObjectWrapper.DEFAULT_WRAPPER.wrap(sysMenus));
		if (body != null) {
			body.render(env.getOut());
		}

	}
}