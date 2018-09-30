<#include "../tools/page.ftl"  />       
<#include "../tools/select.ftl"  /> 
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="BillList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
	   <th  class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('BillList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th>采购订单</th>
		<th>采购订单号</th>
		<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")>	
		<th>ERP订单号</th>
		</#if>	
		<th>门店</th>
		<td>收货地址</td>
		<td>品种数</td>
		<#if Session.COMPANY.roleids?seq_contains("B1CC34941707470D9BF361D1CEF2B08E")==false>
		<th>供应商</th>
		</#if>
		<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")==false ||Session.COMPANY.roleids?seq_contains("05D8DCFAB55440F88EBCBC8C015BC690")==false  >
 		<th>客户</th>
 		</#if>
 	<!--	<#if Session.COMPANY.roleids?seq_contains("D776C89900D74CACA93834FE68880540")==false >
	    <th>代理</th>
	    </#if>
	  -->  
		<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03") >
		<td>采购员</td>
		</#if>
		<th>订单状态</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX" data-planid="${a.planid!""}" data-createtime="${(a.createtime?string("yyyy-MM-dd HH:mm"))!}">
	    <td>${a.name!""}</td>
	    <td>${a.no!""}</td>
	    <#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")>	
	    <td>${a.erpid!""}</td>
	    </#if>
	    <#if (a.storecode!"")!="">
		<@_store storecode=a.storecode userid=a.purchaserid>
		<td>${store.name!""}</td>
	    <td>${store.address!""}</td>
	    </@_store> 
	    <#else>
	    <td></td>
	    <td></td>
	    </#if>
	    <td>${a.num}</td>
		<#if Session.COMPANY.roleids?seq_contains("B1CC34941707470D9BF361D1CEF2B08E")==false>
		<td><#if a.supplierid ??><@_user id=a.supplierid>${user.company!""}</@_user></#if></td>
		</#if>
		<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")==false>
		<td><#if a.purchaserid ??><@_user id=a.purchaserid>${user.company!""}</@_user></#if></td>
		</#if>
		<!--	<#if Session.COMPANY.roleids?seq_contains("D776C89900D74CACA93834FE68880540")==false>
		<td><#if a.agentid ??><@_agent id=a.agentid!"">${agent.name!""}</@_agent></#if></td>
		</#if>
		-->
		<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03") >
		<td>${a.erpusername!""}</td>
		</#if>
		
		<td><#if a.status==-1 && Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03") ><a onclick="javascript:MyFun.objOperate('${request.contextPath}/bill/send.do?id=${a.id}','确定发送?','refreshBillList')" >发送</a><#elseif  Session.COMPANY.roleids?seq_contains("B1CC34941707470D9BF361D1CEF2B08E") && a.status==1 ><a href="javascript:;" onclick="sureBill('${a.id}');">确认</a><#elseif Session.COMPANY.roleids?seq_contains("B1CC34941707470D9BF361D1CEF2B08E") && a.status==2 ><a onclick="javascript:MyFun.objOperate('${request.contextPath}/bill/save.do?id=${a.id}&status=3','确定发货?','refreshBillList')" >发货</a><#else><@getDictData code="bill.status" key=a.status?string  /></#if></td>
  		<td><a  href="${request.contextPath}/billdetail/list.do?billid=${a.id}" >详情</a>   <a href="${request.contextPath}/bill/exportfile.do?billid=${a.id}" >下载</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="BillList" page=page />
