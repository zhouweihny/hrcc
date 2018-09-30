<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxMetadataList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>分类Id</th>
		<th>规则名称</th>
		<th class="<#if sfield=="avg" && stype=="desc">sorting_desc<#elseif sfield =="avg" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataList','stype','<#if sfield=="avg" && stype=="desc">asc<#else>desc</#if>','sfield','avg');"    >平均值</th>
		<th>品种数</th>
		<th>类路径</th>
		<th>方法名</th>
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th class="<#if sfield=="updatetime" && stype=="desc">sorting_desc<#elseif sfield =="updatetime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataList','stype','<#if sfield=="updatetime" && stype=="desc">asc<#else>desc</#if>','sfield','updatetime');"    >更新时间</th>
		<th class="<#if sfield=="disabled" && stype=="desc">sorting_desc<#elseif sfield =="disabled" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxMetadataList','stype','<#if sfield=="disabled" && stype=="desc">asc<#else>desc</#if>','sfield','disabled');"    >状态</th>
		<th>用户Id</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.treeid!""}</td>
		<td>${a.name!""}</td>
		<td>${a.avg!""}</td>
		<td>${a.drugnum!""}</td>
		<td>${a.classname!""}</td>
		<td>${a.methodname!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.disabled?string("冻结","正常"))!}</td>    		
		<td>${a.userid!""}</td>
  		<td>
  			<a href="javascript:;"  data-id='${a.id!""}' onclick="modifyItem(this)">修改</a>
  			<a onclick="javascript:MyFun.objDel('${request.contextPath}/fxmetadata/delete.do?id=${a.id}','refreshFxMetadataList')">删除</a>
  		</td>
    </tr>
    </#list> 
    </tbody>
    </table> 
<@MyPage tableid="FxMetadataList" page=page />
