<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SdrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="id" && stype=="desc">sorting_desc<#elseif sfield =="id" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="id" && stype=="desc">asc<#else>desc</#if>','sfield','id');"    >id</th>
		<th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >name</th>
		<th class="<#if sfield=="factory" && stype=="desc">sorting_desc<#elseif sfield =="factory" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="factory" && stype=="desc">asc<#else>desc</#if>','sfield','factory');"    >factory</th>
		<th class="<#if sfield=="comnameid" && stype=="desc">sorting_desc<#elseif sfield =="comnameid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="comnameid" && stype=="desc">asc<#else>desc</#if>','sfield','comnameid');"    >comnameid</th>
		<th class="<#if sfield=="xse" && stype=="desc">sorting_desc<#elseif sfield =="xse" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="xse" && stype=="desc">asc<#else>desc</#if>','sfield','xse');"    >xse</th>
		<th class="<#if sfield=="disable" && stype=="desc">sorting_desc<#elseif sfield =="disable" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="disable" && stype=="desc">asc<#else>desc</#if>','sfield','disable');"    >disable</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >createtime</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SdrugList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >updatetime</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.id!""}</td>
		<td>${a.name!""}</td>
		<td>${a.factory!""}</td>
		<td>${a.comnameid!""}</td>
		<td>${a.xse!""}</td>
   		<td>${(a.disable?string("true","false"))!}</td>    		
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/sdrug/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/sdrug/delete.do?id=${a.id}','refreshSdrugList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SdrugList" page=page />
