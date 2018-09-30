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
                     	 <label class="col-sm-3 control-label">fileid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="fileid" placeholder="请输入fileid" class="form-control"  value="<#if data??><#if data.fileid??>${data.fileid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">storeid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="storeid" placeholder="请输入storeid" class="form-control"  value="<#if data??><#if data.storeid??>${data.storeid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">orderno：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="orderno" placeholder="请输入orderno" class="form-control"  value="<#if data??><#if data.orderno??>${data.orderno}</#if></#if>"> 
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
                     	 <label class="col-sm-3 control-label">factory：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="factory" placeholder="请输入factory" class="form-control"  value="<#if data??><#if data.factory??>${data.factory}</#if></#if>"> 
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
                     	 <label class="col-sm-3 control-label">zunzi：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="zunzi" placeholder="请输入zunzi" class="form-control"  value="<#if data??><#if data.zunzi??>${data.zunzi}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">qty：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="qty" placeholder="请输入qty" class="form-control"  value="<#if data??><#if data.qty??>${data.qty}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">userid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="userid" placeholder="请输入userid" class="form-control"  value="<#if data??><#if data.userid??>${data.userid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">costprice：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="costprice" placeholder="请输入costprice" class="form-control"  value="<#if data??><#if data.costprice??>${data.costprice}</#if></#if>"> 
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
                     	 <label class="col-sm-3 control-label">saledate：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="saledate" placeholder="请输入saledate" class="form-control"  value="<#if data??><#if data.saledate??>${(data.saledate?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
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
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">operatorid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="operatorid" placeholder="请输入operatorid" class="form-control"  value="<#if data??><#if data.operatorid??>${data.operatorid}</#if></#if>"> 
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
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/fxsaledata/save.do','refreshFxSaleDataList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
