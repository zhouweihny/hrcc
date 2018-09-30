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
                     	 <label class="col-sm-3 control-label">planid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="planid" placeholder="请输入planid" class="form-control"  value="<#if data??><#if data.planid??>${data.planid}</#if></#if>"> 
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
                     	 <label class="col-sm-3 control-label">code：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="code" placeholder="请输入code" class="form-control"  value="<#if data??><#if data.code??>${data.code}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">name：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" placeholder="请输入name" class="form-control"  value="<#if data??><#if data.name??>${data.name}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">specifications：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="specifications" placeholder="请输入specifications" class="form-control"  value="<#if data??><#if data.specifications??>${data.specifications}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">unit：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="unit" placeholder="请输入unit" class="form-control"  value="<#if data??><#if data.unit??>${data.unit}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">factory：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="factory" placeholder="请输入factory" class="form-control"  value="<#if data??><#if data.factory??>${data.factory}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">num：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="num" placeholder="请输入num" class="form-control"  value="<#if data??><#if data.num??>${data.num}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">price：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="price" placeholder="请输入price" class="form-control"  value="<#if data??><#if data.price??>${data.price}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">purchaserid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="purchaserid" placeholder="请输入purchaserid" class="form-control"  value="<#if data??><#if data.purchaserid??>${data.purchaserid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">supplierid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="supplierid" placeholder="请输入supplierid" class="form-control"  value="<#if data??><#if data.supplierid??>${data.supplierid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">status：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="status" placeholder="请输入status" class="form-control"  value="<#if data??><#if data.status??>${data.status}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">sended：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="sended" placeholder="请输入sended" class="form-control"  value="<#if data??><#if data.sended??>${(data.sended?string("true","false"))! 
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
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/plandetail/save.do','refreshPlanDetailList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
