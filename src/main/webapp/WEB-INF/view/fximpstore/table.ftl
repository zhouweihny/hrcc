<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxImpstoreList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>编码</th>
		<th>门店</th>
		<th>品名</th>
		<th>规格</th>
		<th>单位</th>
		<th>剂型</th>
		<th>厂商</th>
		<th>国药准字</th>
		<th>数量</th>
		<th>成本单价</th>
		<th>金额</th>
		<th>创建日期</th>
		<th>业务日期</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.code!""}</td>
		<@_store id=a.storeid userid=Session.COMPANY.id>
		<td>${store.name!""}</td>
		</@_store>
		<td>${a.name!""}</td>
		<td>${a.specifications!""}</td>
		<td>${a.unit!""}</td>
		<td>${a.dosageform!""}</td>
		<td>${a.factory!""}</td>
		<td>${a.zunzi!""}</td>
		<td>${a.qty!""}</td>
		<td>${a.costprice!""}</td>
		<td>${a.amt!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd"))!}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxImpstoreList" page=page />