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
import com.modules.service.SupplierPurchaseService;
import com.modules.pojo.SupplierPurchase;

@Component("supplierPurchaseDirective")
public class SupplierPurchaseDirective implements TemplateDirectiveModel {

	@Autowired
	private SupplierPurchaseService supplierPurchaseService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数

		if (params.containsKey("id")) {
			java.lang.String primarykey = ((TemplateScalarModel) params.get("id")).getAsString();
			try {
				SupplierPurchase entity = new SupplierPurchase();
				entity.setId(primarykey);
				SupplierPurchase supplierPurchase = supplierPurchaseService.findObject(entity);
				if (supplierPurchase == null)
					supplierPurchase = new SupplierPurchase();
				env.setVariable("supplierPurchase", ObjectWrapper.DEFAULT_WRAPPER.wrap(supplierPurchase));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (params.containsKey("countdetail")&&params.containsKey("purchaseid") && params.containsKey("supplierid")) {
			java.lang.String purchaseid = ((TemplateScalarModel) params.get("purchaseid")).getAsString();
			java.lang.String supplierid = ((TemplateScalarModel) params.get("supplierid")).getAsString();
			try {
				Integer count = supplierPurchaseService.findCount(purchaseid, supplierid);
				env.setVariable("num", ObjectWrapper.DEFAULT_WRAPPER.wrap(count));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}