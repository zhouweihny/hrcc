<#macro select id datas headKey="" headText=""   defaultValue="" key="" text="">
	 <select id="${id}" name="${id}" class="form-control input-sm">
    <#-- 判断是否需要显示提示性的<option> -->  
    <#if headText!="">  
        <option value="${headKey}">${headText}</option>  
    </#if>  
		  <#list datas as data>
			  <#if key!="">
				  <#if defaultValue == data[key]?string>
				 	<option value="${data[key]}" selected>${data[text]}</option>
				  <#else>
				   <option value="${data[key]}">${data[text]}</option>
				 </#if>
			  <#else>
				  <#if defaultValue == data>
				 	<option value="${data}" selected>${data}</option>
				 <#else>
				 <option value="${data}">${data}</option>
			  </#if>
			 </#if> 
		  </#list>
	 </select>
</#macro>

<#macro dictSelect id code  headKey="" headText="" defaultValue="">  
    <select id="${id}" name="${id}" class="form-control input-sm">  
    <#-- 判断是否需要显示提示性的<option> -->  
    <#if headText!="">  
        <option value="${headKey}">${headText}</option>  
    </#if>  
  <@_sysdictdatalist code=code >   
   <#list sysDictdatas as data>  
                <#if defaultValue==data.dkey?string>  
                    <option value="${data.dkey}" selected>${data.value}</option>  
                <#else>  
                    <option value="${data.dkey}">${data.value}</option>  
                </#if>  
   </#list>  
     </@_sysdictdatalist>  
    </select>  
</#macro>  


<#macro getDictData   code  key >  
  <@_sysdictdatalist code=code >   
   <#list sysDictdatas as data>  
                <#if key==data.dkey?string>
                   ${data.value}
                </#if>  
   </#list>  
  </@_sysdictdatalist>  
</#macro>  

<#macro menuSelect id   parent  headKey="" headText="" defaultValue="">  
    <select id="${id}" name="${id}" class="form-control input-sm" >  
    <#-- 判断是否需要显示提示性的<option> -->  
    <#if headText!="">  
        <option value="${headKey}">${headText}</option>  
    </#if>  
  <@_sysmenulist parent=parent >   
   <#list sysMenus as data>  
                <#if defaultValue==data.id?string>  
                    <option value="${data.id}" selected><#list data.path?split("/") as id>/<@_sysmenu id="${id!}" >${sysMenu.name!}</@_sysmenu>
     									</#list></option>  
                <#else>  
                    <option value="${data.id}"><#list data.path?split("/") as id>/<@_sysmenu id="${id!}" >${sysMenu.name!}</@_sysmenu></#list></option>  
                </#if>  
   </#list>  
     </@_sysmenulist>  
    </select>  
</#macro>  
 