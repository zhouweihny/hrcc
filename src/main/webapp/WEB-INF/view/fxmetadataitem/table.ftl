<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxMetadataitemList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="id" && stype=="desc">sorting_desc<#elseif sfield =="id" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="id" && stype=="desc">asc<#else>desc</#if>','sfield','id');"    >id</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >createtime</th>
		<th class="<#if sfield=="operatorid" && stype=="desc">sorting_desc<#elseif sfield =="operatorid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="operatorid" && stype=="desc">asc<#else>desc</#if>','sfield','operatorid');"    >operatorid</th>
		<th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >name</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >updatetime</th>
		<th class="<#if sfield=="disabled" && stype=="desc">sorting_desc<#elseif sfield =="disabled" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="disabled" && stype=="desc">asc<#else>desc</#if>','sfield','disabled');"    >disabled</th>
		<th class="<#if sfield=="metadataid" && stype=="desc">sorting_desc<#elseif sfield =="metadataid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="metadataid" && stype=="desc">asc<#else>desc</#if>','sfield','metadataid');"    >metadataid</th>
		<th class="<#if sfield=="condition" && stype=="desc">sorting_desc<#elseif sfield =="condition" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="condition" && stype=="desc">asc<#else>desc</#if>','sfield','condition');"    >condition</th>
		<th class="<#if sfield=="message" && stype=="desc">sorting_desc<#elseif sfield =="message" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="message" && stype=="desc">asc<#else>desc</#if>','sfield','message');"    >message</th>
		<th class="<#if sfield=="parameter" && stype=="desc">sorting_desc<#elseif sfield =="parameter" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataitemList','stype','<#if sfield=="parameter" && stype=="desc">asc<#else>desc</#if>','sfield','parameter');"    >parameter</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.id!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.operatorid!""}</td>
		<td>${a.name!""}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.disabled?string("true","false"))!}</td>    		
		<td>${a.metadataid!""}</td>
		<td>${a.condition!""}</td>
		<td>${a.message!""}</td>
		<td>${a.parameter!""}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxmetadataitem/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxmetadataitem/delete.do?id=${a.id}','refreshFxMetadataitemList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table> 
<@MyPage tableid="FxMetadataitemList" page=page />
