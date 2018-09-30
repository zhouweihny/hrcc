<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="PurchaseDetailList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>编码</th>
		<th>名称</th>
		<th>规格</th>
		<th>单位</th>
	    <th>厂商</th>
	    <th>数量</th>
	    <th>上次采购公司</th>
		<#list  supplierPurchases as a>
		<th>
		 <@_user id=a.supplierid><#if user.scompany ??>${user.scompany!""} <#else> ${user.company!""}  </#if></@_user><#if a.status==1><#else>(未回复)</#if>
	    </th>
	  	</#list> 
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
		<td><@_billdetail lastdrugcompany="true"  purchaserid=Session.COMPANY.id  drugid=a.drugid><#if company.scompany ??>${company.scompany!""}<#else> ${company.company!""}</#if></@_billdetail></td>
		<#list  supplierPurchases as s>
		   	<td style="text-align:right"><@_supplierpurchasedetail purchasedetailid=a.id supplierid=s.supplierid >
				<a href="javascript:;" class="J_addToCart" onclick="addToCart(this, '${a.id!""}')" data-id='${a.id!""}' data-drugid='${a.drugid!""}' data-code='${a.code!""}' data-name='${a.name!""}' data-specifications='${a.specifications!""}' data-unit='${a.unit!""}' data-dosageform='${a.dosageform!""}' data-factory='${a.factory!""}' data-num='${a.num!""}' data-price='${supplierPurchaseDetail.price!""}' data-supplierid='${s.supplierid!""}' data-supplier='<@_user id=s.supplierid>${user.company!""}</@_user>'>${supplierPurchaseDetail.price!""}</a>
		        <#if supplierPurchaseDetail.remark ??><#if supplierPurchaseDetail.remark!="">备注:${supplierPurchaseDetail.remark}</#if></#if>
		   	</@_supplierpurchasedetail></td>
	  	</#list> 
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="PurchaseDetailList" page=page />