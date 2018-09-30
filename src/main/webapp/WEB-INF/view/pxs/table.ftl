<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="PxsList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('PxsList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >名称</th>
		<th class="<#if sfield=="phone" && stype=="desc">sorting_desc<#elseif sfield =="phone" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('PxsList','stype','<#if sfield=="phone" && stype=="desc">asc<#else>desc</#if>','sfield','phone');"    >联系方式</th>
		<th class="<#if sfield=="remark" && stype=="desc">sorting_desc<#elseif sfield =="remark" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('PxsList','stype','<#if sfield=="remark" && stype=="desc">asc<#else>desc</#if>','sfield','remark');"    >备注</th>
		<th class="<#if sfield=="status" && stype=="desc">sorting_desc<#elseif sfield =="status" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('PxsList','stype','<#if sfield=="status" && stype=="desc">asc<#else>desc</#if>','sfield','status');"    >状态</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.name!""}</td>
		<td>${a.phone!""}</td>
		<td>${a.remark!""}</td>
		<td>${a.status!""}</td>
  		<td><a onclick="modFormdata(this, '${a.id!""}');" data-dyname='${a.name!""}' data-phone='${a.phone!""}' data-remark='${a.remark!""}' data-state='${a.status!""}'>修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/pxs/delete.do?id=${a.id}','refreshPxsList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="PxsList" page=page />
