        <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal J_judgeChage" id="save_form">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>权限管理</h5>
                        <div class="ibox-tools">
                            <a class="close-link"  data-dismiss="modal" >
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
	 					  	<input  type="hidden" id="roleid" name="roleid" value="${roleid}">
	 					  	<input  type="hidden" id="menuids" name="menuids" value="">
	 					  	
	   				    <ul id="tree" class="ztree" style="width:560px; overflow:auto;"></ul>
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="save()">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
<script>
		var zTree;

    	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
              		pIdKey: "parentid",
				}
			}
		};

		var zNodes =[
			<#list menus as menu>	
				{ id:'${menu.id}', parentid:'${menu.parentid}', name:'${menu.name}',url:'${menu.url}',<#list ownmenus as own><#if menu.id=own.menuid>checked:true,</#if></#list> open:true},
			</#list>
		];
		
		$(document).ready(function(){
		 	 zTree=	$.fn.zTree.init($("#tree"), setting, zNodes);
		});
		
		
		function save(){
		    var menuids='';
		    $.each(zTree.getCheckedNodes(true), function (index, obj) {
                if(obj.url!='')
                  menuids=menuids+','+obj.id;
            });
		    $('#menuids').val(menuids);
		    MyFun.objSave("save_form","${request.contextPath}/sysrole/bindmenus.do");
		}
		
</script>


 