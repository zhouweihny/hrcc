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
import com.modules.service.StoreService;
import com.modules.pojo.Store;

@Component("storeDirective")
public class StoreDirective implements TemplateDirectiveModel {

	@Autowired
	private StoreService storeService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数

		if (params.containsKey("id")) {
			java.lang.String primarykey = ((TemplateScalarModel) params.get("id")).getAsString();
			try {
				Store entity = new Store();
				entity.setId(primarykey);
				Store store = storeService.findObject(entity);
				if (store == null)
					store = new Store();
				env.setVariable("store", ObjectWrapper.DEFAULT_WRAPPER.wrap(store));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (params.containsKey("storecode") && params.containsKey("userid")) {
			java.lang.String code = ((TemplateScalarModel) params.get("storecode")).getAsString();

			java.lang.String userid = ((TemplateScalarModel) params.get("userid")).getAsString();
			try {
				Store entity = new Store();
				entity.setUserid(userid);
				entity.setStorecode(code);
				Store store = storeService.findObject(entity);
				if (store == null)
					store = new Store();
				env.setVariable("store", ObjectWrapper.DEFAULT_WRAPPER.wrap(store));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}