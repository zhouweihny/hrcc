package  com.modules.directive;
 

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
import  com.modules.service.SupplierPurchaseDetailService;
import  com.modules.pojo.SupplierPurchaseDetail;

@Component("supplierPurchaseDetailDirective")
public class SupplierPurchaseDetailDirective implements TemplateDirectiveModel {

	@Autowired
	private SupplierPurchaseDetailService  supplierPurchaseDetailService;
 

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数
			
		if(params.containsKey("id")){
		  java.lang.String  primarykey =  ((TemplateScalarModel) params.get("id")).getAsString();
			try {
				SupplierPurchaseDetail entity = new SupplierPurchaseDetail();
				entity.setId(primarykey);
				SupplierPurchaseDetail	supplierPurchaseDetail = supplierPurchaseDetailService.findObject(entity);
				if (supplierPurchaseDetail == null)
					supplierPurchaseDetail = new SupplierPurchaseDetail();
				env.setVariable("supplierPurchaseDetail", ObjectWrapper.DEFAULT_WRAPPER.wrap(supplierPurchaseDetail));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}
		
		
		if(params.containsKey("purchasedetailid")&&params.containsKey("supplierid")){
			  java.lang.String  purchasedetailid =  ((TemplateScalarModel) params.get("purchasedetailid")).getAsString();
			  java.lang.String  supplierid =  ((TemplateScalarModel) params.get("supplierid")).getAsString();
				try {
					SupplierPurchaseDetail entity = new SupplierPurchaseDetail();
					entity.setPurchasedetailid(purchasedetailid);
					entity.setSupplierid(supplierid);
					SupplierPurchaseDetail	supplierPurchaseDetail = supplierPurchaseDetailService.findObject(entity);
					if (supplierPurchaseDetail == null)
						supplierPurchaseDetail = new SupplierPurchaseDetail();
					env.setVariable("supplierPurchaseDetail", ObjectWrapper.DEFAULT_WRAPPER.wrap(supplierPurchaseDetail));
				} catch (Exception e) {
					throw new TemplateException(e.getMessage(), env);
				}
			}
		
		
	
		
	
		

		if (body != null) {
			body.render(env.getOut());
		}

	}

}