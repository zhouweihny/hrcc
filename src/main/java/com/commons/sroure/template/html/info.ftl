${r'<#include "../tools/select.ftl"  />'} 
        <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal J_judgeChage" id="save_form">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>${r'<#if'} data??>编辑${r'<#else>'}新增${r'</#if>'}</h5>
                        <div class="ibox-tools">
                            <a class="close-link"  data-dismiss="modal" >
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
  					 <#list c.columns as a>
	  					 <#if  a.isPrimarykey>
	 					  	<input  type="hidden" name="${a.field}" value="${r'<#if'} data??>${r'<#if'} data.${a.field}??>${r'${'}data.${a.field}}${r'</#if>'}${r'</#if>'}">
	  					 <#else>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">${a.comment}：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="${a.field}" placeholder="请输入${a.comment}" class="form-control"  value="${r'<#if'} data??>${r'<#if'} data.${a.field}??>${r'${'}<#if a.fieldType=="java.util.Date">(data.${a.field}?string("yyyy-MM-dd HH:mm"))!  
                                     <#elseif a.fieldType=='java.lang.Boolean' >(data.${a.field}?string("true","false"))! 
                               		 <#else>data.${a.field}</#if>}${r'</#if>'}${r'</#if>'}"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	  					 </#if>
					</#list>
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${r'${request.contextPath}'}/${c.className?lower_case}/save.do','refresh${c.className}List')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
