<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxSaleMonthList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="id" && stype=="desc">sorting_desc<#elseif sfield =="id" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="id" && stype=="desc">asc<#else>desc</#if>','sfield','id');"    >id</th>
		<th class="<#if sfield=="drugid" && stype=="desc">sorting_desc<#elseif sfield =="drugid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="drugid" && stype=="desc">asc<#else>desc</#if>','sfield','drugid');"    >drugid</th>
		<th class="<#if sfield=="storeid" && stype=="desc">sorting_desc<#elseif sfield =="storeid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="storeid" && stype=="desc">asc<#else>desc</#if>','sfield','storeid');"    >storeid</th>
		<th class="<#if sfield=="xse" && stype=="desc">sorting_desc<#elseif sfield =="xse" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="xse" && stype=="desc">asc<#else>desc</#if>','sfield','xse');"    >xse</th>
		<th class="<#if sfield=="cb" && stype=="desc">sorting_desc<#elseif sfield =="cb" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="cb" && stype=="desc">asc<#else>desc</#if>','sfield','cb');"    >cb</th>
		<th class="<#if sfield=="ml" && stype=="desc">sorting_desc<#elseif sfield =="ml" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="ml" && stype=="desc">asc<#else>desc</#if>','sfield','ml');"    >ml</th>
		<th class="<#if sfield=="mlv" && stype=="desc">sorting_desc<#elseif sfield =="mlv" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="mlv" && stype=="desc">asc<#else>desc</#if>','sfield','mlv');"    >mlv</th>
		<th class="<#if sfield=="userid" && stype=="desc">sorting_desc<#elseif sfield =="userid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="userid" && stype=="desc">asc<#else>desc</#if>','sfield','userid');"    >userid</th>
		<th class="<#if sfield=="month" && stype=="desc">sorting_desc<#elseif sfield =="month" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="month" && stype=="desc">asc<#else>desc</#if>','sfield','month');"    >month</th>
		<th class="<#if sfield=="pc" && stype=="desc">sorting_desc<#elseif sfield =="pc" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="pc" && stype=="desc">asc<#else>desc</#if>','sfield','pc');"    >pc</th>
		<th class="<#if sfield=="xssl" && stype=="desc">sorting_desc<#elseif sfield =="xssl" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="xssl" && stype=="desc">asc<#else>desc</#if>','sfield','xssl');"    >xssl</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >createtime</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleMonthList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >updatetime</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.id!""}</td>
		<td>${a.drugid!""}</td>
		<td>${a.storeid!""}</td>
		<td>${a.xse!""}</td>
		<td>${a.cb!""}</td>
		<td>${a.ml!""}</td>
		<td>${a.mlv!""}</td>
		<td>${a.userid!""}</td>
   		<td>${(a.month?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.pc!""}</td>
		<td>${a.xssl!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxsalemonth/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxsalemonth/delete.do?id=${a.id}','refreshFxSaleMonthList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxSaleMonthList" page=page />
