<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxSaleDataList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="id" && stype=="desc">sorting_desc<#elseif sfield =="id" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="id" && stype=="desc">asc<#else>desc</#if>','sfield','id');"    >id</th>
		<th class="<#if sfield=="fileid" && stype=="desc">sorting_desc<#elseif sfield =="fileid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="fileid" && stype=="desc">asc<#else>desc</#if>','sfield','fileid');"    >fileid</th>
		<th class="<#if sfield=="storeid" && stype=="desc">sorting_desc<#elseif sfield =="storeid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="storeid" && stype=="desc">asc<#else>desc</#if>','sfield','storeid');"    >storeid</th>
		<th class="<#if sfield=="orderno" && stype=="desc">sorting_desc<#elseif sfield =="orderno" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="orderno" && stype=="desc">asc<#else>desc</#if>','sfield','orderno');"    >orderno</th>
		<th class="<#if sfield=="drugid" && stype=="desc">sorting_desc<#elseif sfield =="drugid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="drugid" && stype=="desc">asc<#else>desc</#if>','sfield','drugid');"    >drugid</th>
		<th class="<#if sfield=="code" && stype=="desc">sorting_desc<#elseif sfield =="code" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="code" && stype=="desc">asc<#else>desc</#if>','sfield','code');"    >code</th>
		<th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >name</th>
		<th class="<#if sfield=="specifications" && stype=="desc">sorting_desc<#elseif sfield =="specifications" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="specifications" && stype=="desc">asc<#else>desc</#if>','sfield','specifications');"    >specifications</th>
		<th class="<#if sfield=="factory" && stype=="desc">sorting_desc<#elseif sfield =="factory" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="factory" && stype=="desc">asc<#else>desc</#if>','sfield','factory');"    >factory</th>
		<th class="<#if sfield=="unit" && stype=="desc">sorting_desc<#elseif sfield =="unit" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="unit" && stype=="desc">asc<#else>desc</#if>','sfield','unit');"    >unit</th>
		<th class="<#if sfield=="zunzi" && stype=="desc">sorting_desc<#elseif sfield =="zunzi" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="zunzi" && stype=="desc">asc<#else>desc</#if>','sfield','zunzi');"    >zunzi</th>
		<th class="<#if sfield=="qty" && stype=="desc">sorting_desc<#elseif sfield =="qty" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="qty" && stype=="desc">asc<#else>desc</#if>','sfield','qty');"    >qty</th>
		<th class="<#if sfield=="userid" && stype=="desc">sorting_desc<#elseif sfield =="userid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="userid" && stype=="desc">asc<#else>desc</#if>','sfield','userid');"    >userid</th>
		<th class="<#if sfield=="costprice" && stype=="desc">sorting_desc<#elseif sfield =="costprice" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="costprice" && stype=="desc">asc<#else>desc</#if>','sfield','costprice');"    >costprice</th>
		<th class="<#if sfield=="price" && stype=="desc">sorting_desc<#elseif sfield =="price" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="price" && stype=="desc">asc<#else>desc</#if>','sfield','price');"    >price</th>
		<th class="<#if sfield=="saledate" && stype=="desc">sorting_desc<#elseif sfield =="saledate" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="saledate" && stype=="desc">asc<#else>desc</#if>','sfield','saledate');"    >saledate</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >createtime</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >updatetime</th>
		<th class="<#if sfield=="operatorid" && stype=="desc">sorting_desc<#elseif sfield =="operatorid" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="operatorid" && stype=="desc">asc<#else>desc</#if>','sfield','operatorid');"    >operatorid</th>
		<th class="<#if sfield=="disabled" && stype=="desc">sorting_desc<#elseif sfield =="disabled" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleDataList','stype','<#if sfield=="disabled" && stype=="desc">asc<#else>desc</#if>','sfield','disabled');"    >disabled</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.id!""}</td>
		<td>${a.fileid!""}</td>
		<td>${a.storeid!""}</td>
		<td>${a.orderno!""}</td>
		<td>${a.drugid!""}</td>
		<td>${a.code!""}</td>
		<td>${a.name!""}</td>
		<td>${a.specifications!""}</td>
		<td>${a.factory!""}</td>
		<td>${a.unit!""}</td>
		<td>${a.zunzi!""}</td>
		<td>${a.qty!""}</td>
		<td>${a.userid!""}</td>
		<td>${a.costprice!""}</td>
		<td>${a.price!""}</td>
   		<td>${(a.saledate?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.operatorid!""}</td>
   		<td>${(a.disabled?string("true","false"))!}</td>    		
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxsaledata/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxsaledata/delete.do?id=${a.id}','refreshFxSaleDataList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxSaleDataList" page=page />
