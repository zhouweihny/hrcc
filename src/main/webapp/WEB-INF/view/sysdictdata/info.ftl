<#include "../tools/select.ftl"  />  
           <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal J_judgeChage" id="save_form">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><#if sysDictdata.id??>编辑<#else>新增</#if></h5>
                        <div class="ibox-tools">
                            <a class="close-link"  data-dismiss="modal" >
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
	 					  	<input  type="hidden" name="id" value="<#if sysDictdata.id??>${data.id}</#if>">
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">字典名称：</label>
                                <div class="col-sm-8">
                                 <input type="text"  class="form-control" readonly  value="<@_sysdict id="${data.dictid!}">${sysDict.name}</@_sysdict>"> 
                                 <input type="hidden" name="dictid"   value="${data.dictid!}"> 
                                </div>
                         </div>        
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">键：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="dkey" placeholder="请输入键" class="form-control"  value="<#if data??><#if data.dkey??>${data.dkey}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="value" placeholder="请输入值" class="form-control"  value="<#if data??><#if data.value??>${data.value}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">remark：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="备注" placeholder="请输入备注" class="form-control"  value="<#if data??><#if data.remark??>${data.remark}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				   
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/sysdictdata/save.do','refreshdatatable','${data.dictid!}')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
