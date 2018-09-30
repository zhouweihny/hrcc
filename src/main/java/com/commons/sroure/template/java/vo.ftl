package ${basePackage}.${pojoPackage};

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>${c.className}<br>
 */
<#if c.tableName??>@com.commons.annotation.Relation("${c.tableName}")</#if>
public class ${c.className} implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	<#list c.columns as a>
	
	<#if a.fieldType=="java.util.Date" >@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")</#if>
    @com.commons.annotation.Column(value="${a.field}"<#if a.isPrimarykey >,isId=true, generateId = true</#if>)
	private  ${a.fieldType}   ${a.field}; //${a.comment}   
    </#list>

	<#list c.columns as a>
	/**
	 * @return the ${a.field}
	 */
	public ${a.fieldType} get${a.field?cap_first}() {
		return ${a.field};
	}

	/**
	 * @param ${a.field} the ${a.field} to set
	 */
	public void set${a.field?cap_first}(${a.fieldType} ${a.field}) {
		this.${a.field} = ${a.field};
	}
    </#list>



}

