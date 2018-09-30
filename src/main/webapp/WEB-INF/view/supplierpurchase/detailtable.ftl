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
	    <th>报价</th>
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
		<@_supplierpurchasedetail purchasedetailid=a.id supplierid=supplierid ><td> ${supplierPurchaseDetail.price!""}</td><td> ${supplierPurchaseDetail.remark!""}</td></@_supplierpurchasedetail>
		
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="PurchaseDetailList" page=page />