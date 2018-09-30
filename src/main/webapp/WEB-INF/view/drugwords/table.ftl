<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugwordsList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
	    <th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugwordsList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >药品同义词</th>
		<th class="<#if sfield=="code" && stype=="desc">sorting_desc<#elseif sfield =="code" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugwordsList','stype','<#if sfield=="code" && stype=="desc">asc<#else>desc</#if>','sfield','code');"    >编码</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.name!""}</td>
		<td>${a.code!""}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/drugwords/byid.do?code=${a.code!""}','info-form')">新增</a> <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/drugwords/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/drugwords/delete.do?id=${a.id}','refreshDrugwordsList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugwordsList" page=page />
