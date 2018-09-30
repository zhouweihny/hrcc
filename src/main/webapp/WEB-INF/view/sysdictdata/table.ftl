<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysDictdataList" aria-describedby="DataTables_Table_0_info">
<thead>
<tr role="row">
<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">键</th>
<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">值</th>
<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">备注</th>
<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
</tr>
</thead>
<tbody>
<#list  page.data as a>
<tr class="gradeX">
<td>  ${a.dkey!""}</td>
<td>  ${a.value!""}</td>
<td>  ${a.remark!""}</td>
<td>  <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/sysdictdata/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/sysdictdata/delete.do?id=${a.id}','refreshdatatable','${a.dictid}')">删除</a></td>
</tr>
</#list> 
</tbody>
</table>
