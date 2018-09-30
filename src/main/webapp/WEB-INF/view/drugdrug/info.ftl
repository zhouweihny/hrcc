<#include "../tools/select.ftl"  /> 
        <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal J_judgeChage" id="save_form">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><#if data??>编辑<#else>新增</#if></h5>
                        <div class="ibox-tools">
                            <a class="close-link"  data-dismiss="modal" >
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
	 					  	<input  type="hidden" name="id" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">spaceid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="spaceid" placeholder="请输入spaceid" class="form-control"  value="<#if data??><#if data.spaceid??>${data.spaceid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">sdrugid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="sdrugid" placeholder="请输入sdrugid" class="form-control"  value="<#if data??><#if data.sdrugid??>${data.sdrugid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">drugid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="drugid" placeholder="请输入drugid" class="form-control"  value="<#if data??><#if data.drugid??>${data.drugid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">disabled：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="disabled" placeholder="请输入disabled" class="form-control"  value="<#if data??><#if data.disabled??>${(data.disabled?string("true","false"))! 
                               		 }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">operatorid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="operatorid" placeholder="请输入operatorid" class="form-control"  value="<#if data??><#if data.operatorid??>${data.operatorid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">createtime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="createtime" placeholder="请输入createtime" class="form-control"  value="<#if data??><#if data.createtime??>${(data.createtime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">updatetime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="updatetime" placeholder="请输入updatetime" class="form-control"  value="<#if data??><#if data.updatetime??>${(data.updatetime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/drugdrug/save.do','refreshDrugDrugList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
