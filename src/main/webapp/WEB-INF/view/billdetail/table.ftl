<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="BillDetailList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>编码</th>
		<th>品名</th>
		<th>规格</th>
		<th>单位</th>
		<th>产地</th>
		<th>数量</th>
		<th>单价</th>
		<th>状态</th>
		<th>备注</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.code!""}</td>
		<td>${a.name!""}</td>
		<td>${a.specifications!""}</td>
		<td>${a.unit!""}</td>
		<td>${a.factory!""}</td>
		<td>${a.num!""}</td>
		<td>${a.price!""}</td>
		<td>
			<#if a.status==0 >
			<span class="status s_0">有货</span>
			</#if>
			<#if a.status==1 >
			<span class="status s_1">缺货</span>
			</#if>
			<#if a.status==2 >
			<span class="status s_2">断货</span>
			</#if>
			<#if a.status==3 >
			<span class="status s_3">近效期</span>
			</#if>
		</td>
		<td>
			<#if Session.COMPANY.roleids?seq_contains("B1CC34941707470D9BF361D1CEF2B08E")>
			    <#if a.remark ?? && a.remark!="">
				<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/billdetail/byid.do?id=${a.id}','info-form')">${a.remark!""}</a>
			   	<#else>
			   	<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/billdetail/byid.do?id=${a.id}','info-form')">添加备注</a>
			   	</#if>
			<#else>
				${a.remark!""}
			</#if>	
		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="BillDetailList" page=page />
