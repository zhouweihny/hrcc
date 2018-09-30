<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxTreeTaskList" aria-describedby="DataTables_Table_0_info">
	<thead>
		<tr role="row">
			<th class="<#if sfield=="storeid" && stype=="desc">sorting_desc<#elseif sfield =="storeid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="storeid" && stype=="desc">asc<#else>desc</#if>','sfield','storeid');"    >门店</th>
			<th class="<#if sfield=="treeid" && stype=="desc">sorting_desc<#elseif sfield =="treeid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="treeid" && stype=="desc">asc<#else>desc</#if>','sfield','treeid');"    >树节点</th>
			<th class="<#if sfield=="begintime" && stype=="desc">sorting_desc<#elseif sfield =="begintime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="begintime" && stype=="desc">asc<#else>desc</#if>','sfield','begintime');"    >开始日期</th>
			<th class="<#if sfield=="endtime" && stype=="desc">sorting_desc<#elseif sfield =="endtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="endtime" && stype=="desc">asc<#else>desc</#if>','sfield','endtime');"    >结束日期</th>
			<th class="<#if sfield=="writetime" && stype=="desc">sorting_desc<#elseif sfield =="writetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="writetime" && stype=="desc">asc<#else>desc</#if>','sfield','writetime');"    >填写时间</th>
			<th class="<#if sfield=="readtime" && stype=="desc">sorting_desc<#elseif sfield =="readtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="readtime" && stype=="desc">asc<#else>desc</#if>','sfield','readtime');"    >阅读时间</th>
			<th class="<#if sfield=="trainerid" && stype=="desc">sorting_desc<#elseif sfield =="trainerid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="trainerid" && stype=="desc">asc<#else>desc</#if>','sfield','trainerid');"    >培训师</th>
			<th class="<#if sfield=="clerkid" && stype=="desc">sorting_desc<#elseif sfield =="clerkid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="clerkid" && stype=="desc">asc<#else>desc</#if>','sfield','clerkid');"    >员工</th>
			<th class="<#if sfield=="jobid" && stype=="desc">sorting_desc<#elseif sfield =="jobid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="jobid" && stype=="desc">asc<#else>desc</#if>','sfield','jobid');"    >任务名</th>
			<th class="<#if sfield=="remarks" && stype=="desc">sorting_desc<#elseif sfield =="remarks" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxTreeTaskList','stype','<#if sfield=="remarks" && stype=="desc">asc<#else>desc</#if>','sfield','remarks');"    >备注</th>
			<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
		</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_store id=a.storeid userid=Session.COMPANY.id>${store.name!""}</@_store></td>
		<td><@_fxtree id=a.treeid!"000">${fxTree.name!""}</@_fxtree></td>
   		<td>${(a.begintime?string("yyyy-MM-dd"))!}</td>
   		<td>${(a.endtime?string("yyyy-MM-dd"))!}</td>
   		<td>${(a.writetime?string("yyyy-MM-dd"))!}</td>
   		<td>${(a.readtime?string("yyyy-MM-dd"))!}</td>
		<td><@_pxs id=a.trainerid!"000" >${pxs.name!""}</@_pxs></td>
		<td><@_storedy id=a.clerkid!"000" >${storeDy.name!""}</@_storedy></td>
		<td>${a.jobid!""}</td>
		<td>${a.remarks!""}</td>
  		<td>
  			<a onclick="modFormdata(this, '${a.id!""}');" data-storeid='${a.storeid!""}' data-treeid='${a.treeid!""}' data-startdate='${(a.begintime?string("yyyy-MM-dd"))!}' data-enddate='${(a.endtime?string("yyyy-MM-dd"))!}' data-trainerid='${a.trainerid!""}' data-clerkid='${a.clerkid!""}' data-jobid='${a.jobid!""}' data-remark='${a.remarks!""}'>修改</a>
  			<a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxtreetask/delete.do?id=${a.id}','refreshFxTreeTaskList')">删除</a>
  		</td>
    </tr>
    </#list> 
    </tbody>
</table>
<@MyPage tableid="FxTreeTaskList" page=page />
