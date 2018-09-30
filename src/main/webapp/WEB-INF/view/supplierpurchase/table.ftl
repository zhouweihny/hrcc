<#include "../tools/page.ftl"  />    
<#include "../tools/select.ftl"  />      
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SupplierPurchaseList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
        <th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('SupplierPurchaseList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th>计划单</th>
		<th>公司名</th>
		<th>品种数</th>
		<th>标价数</th>
		<th>状态</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
	    <td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
		<td><@_purchase id=a.purchaseid>${purchase.name} </td><td><@_user id=purchase.userid >${user.company!""}</@_user></td>	<td>${purchase.num}</td></@_purchase>
		<td><@_supplierpurchase countdetail='true' purchaseid=a.purchaseid supplierid=a.supplierid >${num}</@_supplierpurchase></td>
		<td><@getDictData code='supplier.purchase.status' key=a.status?string /></td>
  		<td><a href="${request.contextPath}/supplierpurchase/detaillist.do?purchaseid=${a.purchaseid}" >详情</a>  <a href="${request.contextPath}/supplierpurchase/exportfile.do?purchaseid=${a.purchaseid}" >下载</a><#if a.status==0> <a href="javascript:;" class="J_uploadBtn" onclick="showUploader(this);" data-purchaseid="${a.purchaseid}" >上传</a></#if></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SupplierPurchaseList" page=page />
