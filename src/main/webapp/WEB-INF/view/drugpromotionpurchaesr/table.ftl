<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugPromotionPurchaesrList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="id" && stype=="desc">sorting_desc<#elseif sfield =="id" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionPurchaesrList','stype','<#if sfield=="id" && stype=="desc">asc<#else>desc</#if>','sfield','id');"    >id</th>
		<th class="<#if sfield=="promotionid" && stype=="desc">sorting_desc<#elseif sfield =="promotionid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionPurchaesrList','stype','<#if sfield=="promotionid" && stype=="desc">asc<#else>desc</#if>','sfield','promotionid');"    >promotionid</th>
		<th class="<#if sfield=="purchaserid" && stype=="desc">sorting_desc<#elseif sfield =="purchaserid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionPurchaesrList','stype','<#if sfield=="purchaserid" && stype=="desc">asc<#else>desc</#if>','sfield','purchaserid');"    >purchaserid</th>
		<th class="<#if sfield=="operatorid" && stype=="desc">sorting_desc<#elseif sfield =="operatorid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionPurchaesrList','stype','<#if sfield=="operatorid" && stype=="desc">asc<#else>desc</#if>','sfield','operatorid');"    >operatorid</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionPurchaesrList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >createtime</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionPurchaesrList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >updatetime</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.id!""}</td>
		<td>${a.promotionid!""}</td>
		<td>${a.purchaserid!""}</td>
		<td>${a.operatorid!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/drugpromotionpurchaesr/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/drugpromotionpurchaesr/delete.do?id=${a.id}','refreshDrugPromotionPurchaesrList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugPromotionPurchaesrList" page=page />
