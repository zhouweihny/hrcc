<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
	    <th>序号</th>
		<th>编码</th>
		<th>名称</th>
		<th>树节点</th>
		<th>规格</th>
		<th>厂商</th>
		<th>提示</th>
	</tr>
	</thead>
 	<tbody>
	<#list   data as a>
	<tr class="gradeX">
		<td>${a_index+1}</td>
		<td>${a.code!""}</td>
		<td>${a.name!""}</td>
		<td>${a.tree!""}</td>
		<td>${a.specifications!""}</td>
		<td>${a.factory!""}</td>
		<td>${a.remark!""}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
