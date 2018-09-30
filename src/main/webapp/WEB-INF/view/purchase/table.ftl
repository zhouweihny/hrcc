<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="PurchaseList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('PurchaseList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th>询价单</th>
		<th>询价单号</th>
		<th>门店</th>
		<td>收货地址</td>
		<td>品种数</td>
		<th>询价单状态</th>
		<th>发送状态</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
	    <td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.name!""}</td>
		<td>${a.no!""}</td>
		<@_store storecode=a.storecode userid=Session.COMPANY.id>
		<td>${store.name!""}</td>
	    <td>${store.address!""}</td>
	    </@_store> 
	   	<td>${a.num!""}</td>
		<td>${a.remark!""}</td>
	    <td><#if a.send==0  > <#else>已发送</#if></td>
  		<td style="width: 95px;"> <a href="javascript:;" onclick="sendPurchase('${a.id!""}');">发送</a> <a  href="${request.contextPath}/purchasedetail/list.do?purchaseid=${a.id}&storecode=${a.storecode}" >详情</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/purchase/delete.do?id=${a.id}','refreshPurchaseList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="PurchaseList" page=page />
