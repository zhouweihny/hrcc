${r'<#include "../tools/page.ftl"  />'}       
${r'<#assign sfield="">'}        
${r'<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>'}       
${r'<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>'}
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="${c.className}List" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
	<#list c.columns as a>
	<#if a.isPrimarykey><#assign primarykey=a></#if>
		<th class="${r'<#if sfield=="'}${a.field}${r'" && stype=="desc">sorting_desc<#elseif sfield =="'}${a.field}${r'" && stype=="asc">sorting_asc<#else>sorting</#if>'}" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('${c.className}List','stype','${r'<#if sfield=="'}${a.field}" && stype=="desc">asc${r'<#else>desc</#if>'}','sfield','${a.field}');"    >${a.comment}</th>
  	</#list>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	${r'<#list  page.data as a>'}
	<tr class="gradeX">
 	<#list c.columns as a>
	<#if a.fieldType=='java.util.Date' >
   		<td>${r'${(a.'}${a.field}${r'?string("yyyy-MM-dd HH:mm"))!}'}</td>
	<#elseif a.fieldType=='java.lang.Boolean' >
   		<td>${r'${(a.'}${a.field}${r'?string("true","false"))!}'}</td>    		
  	<#else>	
		<td>${r'${a.'}${a.field}${r'!""}'}</td>
	</#if>
  	</#list> 
  		<td><#if primarykey?? ><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${r'${request.contextPath}'}/${c.className?lower_case}/byid.do?id=${r'${a'}.${primarykey.field}}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${r'${request.contextPath}'}/${c.className?lower_case}/delete.do?id=${r'${a'}.${primarykey.field}}','refresh${c.className}List')">删除</a></#if></td>
    </tr>
    ${r'</#list>'} 
    </tbody>
    </table>
${r'<@MyPage tableid="'}${c.className}${r'List" page=page />'}
