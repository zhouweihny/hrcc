<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="PlanList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('PlanList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th>计划单</th>
		<th>门店</th>
		<th>收货地址</th>
		<th>品种数</th>
		<th>采购单</th>
		<th>询价单</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
	    <td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.name!""}</td>
		<@_store storecode=a.storecode userid=Session.COMPANY.id>
		<td>${store.name!""}</td>
	    <td>${store.address!""}</td>
	    </@_store> 
	   	<td> ${a.num!""} </td>
		<td><#if  a.billnum!=0><a href="/bill/list.do?planid=${a.id}" target="_blank">${a.billnum!""}</a＞</#if></td>
	 	<td><#if  a.purchasenum!=0><a href="/purchase/list.do?planid=${a.id}" target="_blank">${a.purchasenum!""}</a＞</#if></td>
  		<td><a href="${request.contextPath}/plandetail/list.do?planid=${a.id}" >详情</a><#if a.billnum==0 && a.purchasenum==0 > <a    onclick="javascript:MyFun.objOperate('${request.contextPath}/plan/create.do?planid=${a.id}','确定生成?','refreshPlanList')" >生成</a></#if></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="PlanList" page=page />
