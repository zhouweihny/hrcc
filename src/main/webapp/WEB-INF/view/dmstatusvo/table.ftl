<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DmStatusVoList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th  >药品目录</th>
		<th  >品种数</th>
    	<th  >已对照</th>
		<th  >未对照</th>
	    <th  >未复核</th>
		<th  >已忽略</th>


	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.name!""}</td>
		<td>${a.num}</td>
		<td>${a.dm}</td>
		<td><font color="red">${a.ndm}<font></td>
		<td><font color="red">${a.dm-a.checked}<font></td>    	
	    <td>${a.ignore}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DmStatusVoList" page=page />
