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
import com.modules.service.BillDetailService;
import com.modules.pojo.BillDetail;
import com.modules.pojo.User;

@Component("billDetailDirective")
public class BillDetailDirective implements TemplateDirectiveModel {

	@Autowired
	private BillDetailService billDetailService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数

		if (params.containsKey("id")) {
			java.lang.String primarykey = ((TemplateScalarModel) params.get("id")).getAsString();
			try {
				BillDetail entity = new BillDetail();
				entity.setId(primarykey);
				BillDetail billDetail = billDetailService.findObject(entity);
				if (billDetail == null)
					billDetail = new BillDetail();
				env.setVariable("billDetail", ObjectWrapper.DEFAULT_WRAPPER.wrap(billDetail));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (params.containsKey("lastdrugcompany")) {
			java.lang.String purchaserid = ((TemplateScalarModel) params.get("purchaserid")).getAsString();
			java.lang.String drugid = ((TemplateScalarModel) params.get("drugid")).getAsString();
			try {

				User user = billDetailService.lastDrugCompany(purchaserid, drugid);
				if (user == null)
					user = new User();
				env.setVariable("company", ObjectWrapper.DEFAULT_WRAPPER.wrap(user));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}