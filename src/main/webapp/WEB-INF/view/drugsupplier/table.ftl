<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugSupplierList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>编码</th>
		<th>名称</th>
		<th>规格</th>
		<th>单位</th>
		<th>厂商</th>
		<th>手机号</th>
		<th>供应商</th>
	    <th>代理</th>
	    <th>备注</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
	    <@_drug id=a.drugid>
		<td>${drug.code!""}</td>
		<td>${drug.name!""}</td>
		<td>${drug.specifications!""}</td>
		<td>${drug.unit!""}</td>
		<td>${drug.factory!""}</td>
		</@_drug>	
		<td>${a.mobile !""}</td>	
		<td><#if a.erpsucode ?? ><@_usersupplier code=a.erpsucode userid=Session.COMPANY.id >${userSupplier.name!""}<#if userSupplier.supplierid ??><#else>(未对照)</#if></@_usersupplier></#if></td>
		<td><#if a.mobile ?? ><@_agent mobileno=a.mobile purchaserid=Session.COMPANY.id >${agent.name!""}</@_agent></#if></td>
		<td>${a.remark!""}</td>
  		<td><a   onclick="javascript:MyFun.objDel('${request.contextPath}/drugsupplier/delete.do?id=${a.id}','refreshDrugSupplierList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugSupplierList" page=page />
