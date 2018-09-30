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
	 						<input  type="hidden" id="treeid" name="treeid" value="<#if data??><#if data.treeid??>${data.treeid}</#if></#if>">
	 					  	
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">treeid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="treeid" placeholder="请输入treeid" class="form-control"  value="<#if data??><#if data.treeid??>${data.treeid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                            
                            
                       <div class="form-group">
                     	 <label class="col-sm-3 control-label">上级菜单：</label>
                                <div class="col-sm-8">
                                    <div id="menuContent" class="menuContent">
 										<input <#if data??>readonly</#if> required   value="<#if data??><@_syswx id="${data.wxid!}" >${sysWx.name!}</@_syswx><#list data.path?split("/") as id><#if id!=data.id >/<@_syswxmenu id="${id!}" >${sysWxMenu.name!}</@_syswxmenu></#if></#list></#if>" type="text" name="treeMenu" id="treeMenu" class="form-control" readonly  />
  										 <ul id="treeDemo" class="ztree"></ul>
									</div>
                                    <span class="help-block m-b-none"></span>
                                </div>
                       </div>    
                            
                            
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">metaid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="metaid" placeholder="请输入metaid" class="form-control"  value="<#if data??><#if data.metaid??>${data.metaid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">methodid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="methodid" placeholder="请输入methodid" class="form-control"  value="<#if data??><#if data.methodid??>${data.methodid}</#if></#if>"> 
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
                     	 <label class="col-sm-3 control-label">scope：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="scope" placeholder="请输入scope" class="form-control"  value="<#if data??><#if data.scope??>${data.scope}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">standard：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="standard" placeholder="请输入standard" class="form-control"  value="<#if data??><#if data.standard??>${(data.standard?string("true","false"))! 
                               		 }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">remark：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="remark" placeholder="请输入remark" class="form-control"  value="<#if data??><#if data.remark??>${data.remark}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/fxtreemeta/save.do','refreshFxTreeMetaList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
        
        
        
               <style>
.menuContent {
    position: relative;
    z-index: 9999;
}
.menuContent .ztree {
    margin-top: 0;
    border: 1px solid #a9a9a9;
    background: #f0f6e4;
    width: 220px;
    height: 360px;
    overflow-y: scroll;
    overflow-x: auto;
    position: absolute;
    left: 21px;
    top: 25px;
    display: none;
}
</style>
        
<script>
    var setting = {
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };


    var zNodes =[
        <#if (fxtrees?size > 0)>
      	<#list  fxtrees as tree >
              {id:"${tree.id}", pId:'<#if tree.parentid?? && tree.parentid!=''>${tree.parentid}<#else>${tree.wxid}</#if>', name:"${tree.name}",type:"wxmenu",wxid:"${tree.wxid}"},
        </#list>
        </#if> 
    ];

    function beforeClick(treeId, treeNode) {
     	if(treeNode.level==2) {
         MyFun.to.e("不能选中二级菜单");
         return false;
 		}    
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        v = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $("#treeMenu");
        cityObj.attr("value", v);
        $('#awxid').val(treeNode.wxid);
        if(treeNode.type=='wxmenu')
       	 	$('#parentid').val(treeNode.id);
        hideMenu();
    }

    function showMenu() {
        $("#menuContent .ztree").css({"display": "block"});
        $(document).bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent .ztree").css({"display": "none"});
        $(document).unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if(!(event.target.id == 'treeMenu' || event.target.id == 'treeDemo' || $(event.target).parents("#menuContent").length>0)){
            hideMenu();
        }
    }

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        $("#treeMenu").click(function(){
            showMenu();
        })
    });


</script>
