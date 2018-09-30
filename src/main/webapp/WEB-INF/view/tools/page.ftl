<#macro MyPage  tableid page>
<#if (page.totalPages>0)> 
 <div class="row">
  	 <div class="col-sm-6">
     	<div class="dataTables_length" id="DataTables_Table_0_length" style="display: inline-block; vertical-align: middle; height: 35px;line-height: 35px;">
           <label>
	            <select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="form-control input-sm" onchange="javascript:MyFun.ajaxPageTable('${tableid}',this.value,1)">
	                <option value="10" 	<#if page.pageSize==10> selected</#if>>10</option>
	                <option value="25"  <#if page.pageSize==25> selected</#if>>25</option>
	                <option value="30"  <#if page.pageSize==30> selected</#if>>30</option>
	                <option value="40" 	<#if page.pageSize==40> selected</#if>>40</option>
	            </select>
	        </label>
	    </div>
	    <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria-live="polite" aria-relevant="all" style="display: inline-block;vertical-align: middle;height: 35px;line-height: 35px;padding: 0; margin-left: 20px;">显示 ${(page.currentPage-1)*page.pageSize+1}到  <#if ((page.currentPage*page.pageSize+page.pageSize)>page.totalRows) >  ${page.totalRows}<#else>${page.currentPage*page.pageSize}</#if>项，共 ${page.totalRows} 项</div>
     </div>
		<div class="col-sm-6">
		    <div id="paginate_jump" class="paginate_jump"><span>到第</span><input class="input-txt" maxlength="4" value="" id="pagejumpinput"><span>页</span><a class="btn btn-white" href="javascript:MyFun.ajaxJumpPageTable('${tableid}','')" >确定</a></div>
		
			<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
                                        <ul class="pagination">
                                            
                                               <#if page.currentPage==1>
                                                <li class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_previous">
                                               	<a href="javascript:;">上一页</a>
                                               	</li>
                                                <#else>
                                                <li class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_previous">
                                                <a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.currentPage-1}');">上一页</a>
                                                </li>
                                               </#if> 
                                            
                                            
                                            <#if (page.totalPages<=6)> 
                                       			<#list 1..page.totalPages as t>
													 <li class="paginate_button <#if t=page.currentPage>active</#if>" aria-controls="DataTables_Table_0" tabindex="0">
                                               		 <a href="javascript:MyFun.ajaxPageTable('${tableid}','','${t}');">${t}</a>
                                           			 </li>		
												</#list>
											<#else>	
												<#if (page.currentPage<5)>
													<#list 1..5 as t>
													 <li class="paginate_button <#if t=page.currentPage>active</#if>" aria-controls="DataTables_Table_0" tabindex="0">
                                               		 <a href="javascript:MyFun.ajaxPageTable('${tableid}','','${t}');">${t}</a>
                                           			 </li>		
													</#list>
												    <li id="DataTables_Table_0_ellipsis" class="paginate_button disabled" aria-controls="DataTables_Table_0" tabindex="0">
													<a href="javascript:;">…</a>
													</li>
													 <li class="paginate_button" aria-controls="DataTables_Table_0" tabindex="0">
                                               		 <a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.totalPages}');">${page.totalPages}</a>
                                           			 </li>	
                                           		 <#elseif (page.currentPage>page.totalPages-4) >	 
                                           		 	<li class="paginate_button" aria-controls="DataTables_Table_0" tabindex="0">
                                               		<a href="javascript:MyFun.ajaxPageTable('${tableid}','','1');">1</a>
                                           			</li>	
                                           			<li id="DataTables_Table_0_ellipsis" class="paginate_button disabled" aria-controls="DataTables_Table_0" tabindex="0">
													<a href="javascript:;">…</a>
													</li>
                                           		 	<#list 4..0 as t>
													 <li class="paginate_button <#if page.totalPages-t=page.currentPage>active</#if>" aria-controls="DataTables_Table_0" tabindex="0">
                                               		 <a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.totalPages-t}');">${page.totalPages-t}</a>
                                           			 </li>		
													</#list>
												 <#else>	 
                                           			<li class="paginate_button" aria-controls="DataTables_Table_0" tabindex="0">
                                               		<a href="javascript:MyFun.ajaxPageTable('${tableid}','','1');">1</a>
                                           			</li>
                                           			<li id="DataTables_Table_0_ellipsis" class="paginate_button disabled" aria-controls="DataTables_Table_0" tabindex="0">
													<a href="javascript:;">…</a>
													</li>
													 <li class="paginate_button" aria-controls="DataTables_Table_0" tabindex="0">
                                               		<a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.currentPage-1}');">${page.currentPage-1}</a>
                                           			</li>	
                                           			<li class="paginate_button active" aria-controls="DataTables_Table_0" tabindex="0">
                                               		<a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.currentPage}');">${page.currentPage}</a>
                                           			</li>	
                                           		    <li class="paginate_button" aria-controls="DataTables_Table_0" tabindex="0">
                                               		<a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.currentPage+1}');">${page.currentPage+1}</a>
                                           			</li>	
													<li id="DataTables_Table_0_ellipsis" class="paginate_button disabled" aria-controls="DataTables_Table_0" tabindex="0">
													<a href="javascript:;">…</a>
													</li>
													<li class="paginate_button" aria-controls="DataTables_Table_0" tabindex="0">
                                               		<a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.totalPages}');">${page.totalPages}</a>
                                           			</li>		
												</#if>
											</#if>
                                  
                                               <#if page.currentPage==page.totalPages>
                                                 <li class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_next">
                                               		 <a href="javascript:;">下一页</a>
                                                 </li>		 
                                                <#else>
                                                <li class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_next">
                                                	 <a href="javascript:MyFun.ajaxPageTable('${tableid}','','${page.currentPage+1}');">下一页</a>
                                                </li>	 
                                               </#if> 
                                                
                                            </li>
                                        </ul>
			 </div>
		 </div>
	 </div>
</div>
</#if>
</#macro>