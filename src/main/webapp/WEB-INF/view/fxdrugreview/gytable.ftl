<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>编码</th>
		<th>名称</th>
		<th>通用名</th>
        <th>树节点</th>
		<th>规格</th>
		<th>单位</th>
		<th>厂商</th>
		<th>进货单价</th>
		<th>建议零售价</th>
		<th>供应商名称</th>
		<th>状态</th>
		<th style="text-align:right;">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.code!""}</td>
		<td>${a.name!""}</td>
		<td><#if a.comnameid ??><@_fxcomname id="${a.comnameid}"><#if fxComname??>${fxComname.name!""}</#if></@_fxcomname></#if></td>
		<td><#if a.comnameid ??><@_fxcomnametree comnameid="${a.comnameid}"><#list treepaths as path>${path!""} <br></#list></@_fxcomnametree></#if></td>
		<td>${a.specifications!""}</td>
		<td>${a.unit!""}</td>
		<td>${a.factory!""}</td>
		<td>${a.costprice!""}</td>
		<td>${a.price!""}</td>
		<td>${a.company!""}</td>
		<td>${a.statusname!""}</td>
		<td style="text-align:right;">
			<#if a.statusname=="待审核">
			<a href="javascript:;" onclick="auditDrug(this, '1');" data-id='${a.id!""}' style="display:block;">通过</a>
			<a href="javascript:;" onclick="auditDrug(this, '0');" data-id='${a.id!""}' style="display:block;margin-top:5px;">不通过</a>
			</#if>
		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugList" page=page />
