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
                     	 <label class="col-sm-3 control-label">wxid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="wxid" placeholder="请输入wxid" class="form-control"  value="<#if data??><#if data.wxid??>${data.wxid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">categoryid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="categoryid" placeholder="请输入categoryid" class="form-control"  value="<#if data??><#if data.categoryid??>${data.categoryid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">title：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="title" placeholder="请输入title" class="form-control"  value="<#if data??><#if data.title??>${data.title}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">author：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="author" placeholder="请输入author" class="form-control"  value="<#if data??><#if data.author??>${data.author}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">description：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="description" placeholder="请输入description" class="form-control"  value="<#if data??><#if data.description??>${data.description}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">editor：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="editor" placeholder="请输入editor" class="form-control"  value="<#if data??><#if data.editor??>${data.editor}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">content：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="content" placeholder="请输入content" class="form-control"  value="<#if data??><#if data.content??>${data.content}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">cover：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="cover" placeholder="请输入cover" class="form-control"  value="<#if data??><#if data.cover??>${data.cover}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">clicks：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="clicks" placeholder="请输入clicks" class="form-control"  value="<#if data??><#if data.clicks??>${data.clicks}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">createuserid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="createuserid" placeholder="请输入createuserid" class="form-control"  value="<#if data??><#if data.createuserid??>${data.createuserid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">publishuserid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="publishuserid" placeholder="请输入publishuserid" class="form-control"  value="<#if data??><#if data.publishuserid??>${data.publishuserid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">publishdate：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="publishdate" placeholder="请输入publishdate" class="form-control"  value="<#if data??><#if data.publishdate??>${(data.publishdate?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">topdate：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="topdate" placeholder="请输入topdate" class="form-control"  value="<#if data??><#if data.topdate??>${(data.topdate?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
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
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/cmscontent/save.do','refreshCmsContentList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
