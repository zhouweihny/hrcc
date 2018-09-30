<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxTreeMetaDataList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="id" && stype=="desc">sorting_desc<#elseif sfield =="id" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="id" && stype=="desc">asc<#else>desc</#if>','sfield','id');"    >id</th>
		<th class="<#if sfield=="treemetaid" && stype=="desc">sorting_desc<#elseif sfield =="treemetaid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="treemetaid" && stype=="desc">asc<#else>desc</#if>','sfield','treemetaid');"    >treemetaid</th>
		<th class="<#if sfield=="metadataid" && stype=="desc">sorting_desc<#elseif sfield =="metadataid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="metadataid" && stype=="desc">asc<#else>desc</#if>','sfield','metadataid');"    >metadataid</th>
		<th class="<#if sfield=="val" && stype=="desc">sorting_desc<#elseif sfield =="val" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="val" && stype=="desc">asc<#else>desc</#if>','sfield','val');"    >val</th>
		<th class="<#if sfield=="msg" && stype=="desc">sorting_desc<#elseif sfield =="msg" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="msg" && stype=="desc">asc<#else>desc</#if>','sfield','msg');"    >msg</th>
		<th class="<#if sfield=="remark" && stype=="desc">sorting_desc<#elseif sfield =="remark" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="remark" && stype=="desc">asc<#else>desc</#if>','sfield','remark');"    >remark</th>
		<th class="<#if sfield=="disabled" && stype=="desc">sorting_desc<#elseif sfield =="disabled" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="disabled" && stype=="desc">asc<#else>desc</#if>','sfield','disabled');"    >disabled</th>
		<th class="<#if sfield=="operatorid" && stype=="desc">sorting_desc<#elseif sfield =="operatorid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="operatorid" && stype=="desc">asc<#else>desc</#if>','sfield','operatorid');"    >operatorid</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >createtime</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeMetaDataList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >updatetime</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.id!""}</td>
		<td>${a.treemetaid!""}</td>
		<td>${a.metadataid!""}</td>
		<td>${a.val!""}</td>
		<td>${a.msg!""}</td>
		<td>${a.remark!""}</td>
   		<td>${(a.disabled?string("true","false"))!}</td>    		
		<td>${a.operatorid!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxtreemetadata/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxtreemetadata/delete.do?id=${a.id}','refreshFxTreeMetaDataList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxTreeMetaDataList" page=page />
