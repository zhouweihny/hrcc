<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
	   	<th><#if status==1>月份</#if><#if status==2>季度</#if></th>
		<th>毛利</th>
		<th>同比</th>
		<th>环比</th>
	    <th>日均毛利</th>
		<th>累计毛利</th>
	</tr>
	</thead>
 	<tbody>
	<#list   data as a>
	<tr class="gradeX">
		<td>${a.yf}</td>
		<td style="text-align: right;">${a.ml?string("0.00")}</td>
		<td style="text-align: right;"><#if a.tb??>${a.tb}%</#if></td>
		<td style="text-align: right;"><#if a.hb??>${a.hb}%</#if></td>
        <td style="text-align: right;"><#if a.tb??>${a.rjml?string("0.00")}</#if></td>
		<td style="text-align: right;">${a.ljml?string("0.00")}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
