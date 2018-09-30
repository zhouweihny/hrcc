<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SpaceList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SpaceList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >名称</th>
		<th class="<#if sfield=="remark" && stype=="desc">sorting_desc<#elseif sfield =="remark" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SpaceList','stype','<#if sfield=="remark" && stype=="desc">asc<#else>desc</#if>','sfield','remark');"    >备注</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SpaceList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.name!""}</td>
		<td>${a.remark!""}</td>
		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td><a data-toggle="modal"  data-target="#info-formS" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/space/modcatalog.do?id=${a.id!""}','info-formS')">添加目录</a> <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/space/byid.do?id=${a.id!""}','info-form')">修改</a><#if  a.myself > <#else> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/space/delete.do?id=${a.id!""}','refreshSpaceList')">删除</a> </#if></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SpaceList" page=page />
