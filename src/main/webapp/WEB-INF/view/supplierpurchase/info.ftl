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
                     	 <label class="col-sm-3 control-label">supplierid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="supplierid" placeholder="请输入supplierid" class="form-control"  value="<#if data??><#if data.supplierid??>${data.supplierid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">purchaseid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="purchaseid" placeholder="请输入purchaseid" class="form-control"  value="<#if data??><#if data.purchaseid??>${data.purchaseid}</#if></#if>"> 
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
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/supplierpurchase/save.do','refreshSupplierPurchaseList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
