<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxLhyydcList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="id" && stype=="desc">sorting_desc<#elseif sfield =="id" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="id" && stype=="desc">asc<#else>desc</#if>','sfield','id');"    >id</th>
		<th class="<#if sfield=="treeid" && stype=="desc">sorting_desc<#elseif sfield =="treeid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="treeid" && stype=="desc">asc<#else>desc</#if>','sfield','treeid');"    >treeid</th>
		<th class="<#if sfield=="title" && stype=="desc">sorting_desc<#elseif sfield =="title" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="title" && stype=="desc">asc<#else>desc</#if>','sfield','title');"    >title</th>
		<th class="<#if sfield=="author" && stype=="desc">sorting_desc<#elseif sfield =="author" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="author" && stype=="desc">asc<#else>desc</#if>','sfield','author');"    >author</th>
		<th class="<#if sfield=="description" && stype=="desc">sorting_desc<#elseif sfield =="description" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="description" && stype=="desc">asc<#else>desc</#if>','sfield','description');"    >description</th>
		<th class="<#if sfield=="editor" && stype=="desc">sorting_desc<#elseif sfield =="editor" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="editor" && stype=="desc">asc<#else>desc</#if>','sfield','editor');"    >editor</th>
		<th class="<#if sfield=="content" && stype=="desc">sorting_desc<#elseif sfield =="content" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="content" && stype=="desc">asc<#else>desc</#if>','sfield','content');"    >content</th>
		<th class="<#if sfield=="cover" && stype=="desc">sorting_desc<#elseif sfield =="cover" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="cover" && stype=="desc">asc<#else>desc</#if>','sfield','cover');"    >cover</th>
		<th class="<#if sfield=="clicks" && stype=="desc">sorting_desc<#elseif sfield =="clicks" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="clicks" && stype=="desc">asc<#else>desc</#if>','sfield','clicks');"    >clicks</th>
		<th class="<#if sfield=="createuserid" && stype=="desc">sorting_desc<#elseif sfield =="createuserid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="createuserid" && stype=="desc">asc<#else>desc</#if>','sfield','createuserid');"    >createuserid</th>
		<th class="<#if sfield=="publishuserid" && stype=="desc">sorting_desc<#elseif sfield =="publishuserid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="publishuserid" && stype=="desc">asc<#else>desc</#if>','sfield','publishuserid');"    >publishuserid</th>
		<th class="<#if sfield=="publishdate" && stype=="desc">sorting_desc<#elseif sfield =="publishdate" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="publishdate" && stype=="desc">asc<#else>desc</#if>','sfield','publishdate');"    >publishdate</th>
		<th class="<#if sfield=="topdate" && stype=="desc">sorting_desc<#elseif sfield =="topdate" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="topdate" && stype=="desc">asc<#else>desc</#if>','sfield','topdate');"    >topdate</th>
		<th class="<#if sfield=="status" && stype=="desc">sorting_desc<#elseif sfield =="status" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="status" && stype=="desc">asc<#else>desc</#if>','sfield','status');"    >status</th>
		<th class="<#if sfield=="disabled" && stype=="desc">sorting_desc<#elseif sfield =="disabled" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="disabled" && stype=="desc">asc<#else>desc</#if>','sfield','disabled');"    >disabled</th>
		<th class="<#if sfield=="operatorid" && stype=="desc">sorting_desc<#elseif sfield =="operatorid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="operatorid" && stype=="desc">asc<#else>desc</#if>','sfield','operatorid');"    >operatorid</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >createtime</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxLhyydcList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >updatetime</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.id!""}</td>
		<td>${a.treeid!""}</td>
		<td>${a.title!""}</td>
		<td>${a.author!""}</td>
		<td>${a.description!""}</td>
		<td>${a.editor!""}</td>
		<td>${a.content!""}</td>
		<td>${a.cover!""}</td>
		<td>${a.clicks!""}</td>
		<td>${a.createuserid!""}</td>
		<td>${a.publishuserid!""}</td>
   		<td>${(a.publishdate?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.topdate?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.status!""}</td>
   		<td>${(a.disabled?string("true","false"))!}</td>    		
		<td>${a.operatorid!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxlhyydc/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxlhyydc/delete.do?id=${a.id}','refreshFxLhyydcList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxLhyydcList" page=page />
