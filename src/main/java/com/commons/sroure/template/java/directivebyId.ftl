package  ${basePackage}.${directivePackage};
 
  <#list c.columns as a>
	 <#if a.isPrimarykey><#assign primarykey=a></#if>
   </#list>

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
<#if  primarykey.fieldType=="java.lang.Integer" > 
import freemarker.template.TemplateNumberModel;
</#if>
import freemarker.template.TemplateScalarModel;
import  ${basePackage}.${servicePackage}.${c.className}Service;
import  ${basePackage}.${pojoPackage}.${c.className};

@Component("${c.className?uncap_first}Directive")
public class ${c.className}Directive implements TemplateDirectiveModel {

	@Autowired
	private ${c.className}Service  ${c.className?uncap_first}Service;
 

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数
			
		if(params.containsKey("${primarykey.field}")){
		 <#if  primarykey.fieldType=="java.lang.String" > 
		  ${primarykey.fieldType}  primarykey =  ((TemplateScalarModel) params.get("${primarykey.field}")).getAsString();
		 </#if> 
		 <#if  primarykey.fieldType=="java.lang.Integer" > 
		  	${primarykey.fieldType}  primarykey =  ((TemplateNumberModel) params.get("${primarykey.field}")).getAsNumber().intValue()
		 </#if> 
			try {
				${c.className} entity = new ${c.className}();
				entity.setId(primarykey);
				${c.className}	${c.className?uncap_first} = ${c.className?uncap_first}Service.findObject(entity);
				if (${c.className?uncap_first} == null)
					${c.className?uncap_first} = new ${c.className}();
				env.setVariable("${c.className?uncap_first}", ObjectWrapper.DEFAULT_WRAPPER.wrap(${c.className?uncap_first}));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}