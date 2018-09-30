<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxComnameList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>名称</th>
		<th class="<#if sfield=="operatorid" && stype=="desc">sorting_desc<#elseif sfield =="operatorid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxComnameList','stype','<#if sfield=="operatorid" && stype=="desc">asc<#else>desc</#if>','sfield','operatorid');"    >操作人</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxComnameList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.name!""}</td>
		<td><#if a.operatorid ??><@_user id=a.operatorid>${user.username}</@_user></#if></td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxcomname/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxcomname/delete.do?id=${a.id}','refreshFxComnameList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxComnameList" page=page />
