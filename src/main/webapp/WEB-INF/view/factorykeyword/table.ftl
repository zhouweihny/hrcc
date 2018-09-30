<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FactoryKeywordList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>厂商</th>
		<td>关键词</th>
		<td>操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td class="J_fac_keywords" data-code='${a.code!""}'>${a.factorys!""}</td>
		<td>${a.word!""}</td>
		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/factorykeyword/byid.do?code=${a.code!""}','info-form')">新增</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/factorykeyword/delete.do?code=${a.code}','refreshFactoryKeywordList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FactoryKeywordList" page=page />
