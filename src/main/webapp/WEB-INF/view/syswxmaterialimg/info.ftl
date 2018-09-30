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
                  		  <label class="col-sm-3 control-label">公共号：</label>
                                <div class="col-sm-8">
                                  <@_syswxlist>
                                    <#if data??>
 									<@select id="awxid" datas=sysWxs defaultValue="${data.wxid}" key="id" text="name" />
 									<#else>
 									<@select id="awxid" datas=sysWxs key="id" text="name" />
 									</#if>
  								  </@_syswxlist>                                
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">分组：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="group" placeholder="请输入分组" class="form-control group"  value="<#if data??><#if data.group??>${data.group}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                        </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="remark" placeholder="请输入备注" class="form-control remark"  value="<#if data??><#if data.remark??>${data.remark}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">图片：</label>
                                <div class="col-sm-8">
                                    <div class="wu-example">
									    <div class="btns">
									        <div id="picker">选择文件</div>
									    </div>
									    <!--用来存放文件信息-->
									    <div id="thelist" class="uploader-list"></div>
									</div>
                                </div>
                            </div>
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" id="J_zsave">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
        
        
        
  <script>
var uploader = null; 
$(function(){
	setTimeout(function(){
		initExcelUpload();
	}, 300)
	
	
	$("#J_zsave").on("click", function(){
		//上传
		uploader.upload();
	})
});

function initExcelUpload(){
	// 初始化Web Uploader
	uploader = WebUploader.create({
	    // swf文件路径
	    swf: '${request.contextPath}/js/plugins/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: '${request.contextPath}/syswxmaterialimg/save.do',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
			id: $("#picker"), // id
			multiple: false  // false  单选 
        },
	    fileVal:"uploadfile",//设置文件上传域的name
	    formData: {
	    	"group": "图文编辑"
	    },
	    // fileNumLimit: window._IE ? "1" : "5",
	    fileNumLimit: '1',
	    paste: document.body,//ctrl+v粘贴
	    duplicate: true, //可重复上传同一文件
	    // 只允许选择excel文件。只有IE会识别
	    // http://www.w3school.com.cn/media/media_mimeref.asp
 		accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
        }
	});

    uploader.on( 'beforeFileQueued', function( file ) {
    	uploader.reset();
    });
	
	// 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        $("#thelist").html(file.name);
    });
	
	//当某个文件的分块在发送前触发，主要用来询问是否要添加附带参数，大文件在开起分片上传的前提下此事件可能会触发多次。
	uploader.on('uploadBeforeSend', function (obj, data, headers) {
	    data.group = $(".group").val();
	    data.remark = $(".remark").val();
	    data.wxid = $("#awxid").val();
	});

	uploader.on("uploadStart", function(file){
		console.log("正在上传");
	})

	// 当有文件添加进来的时候
	uploader.on( 'uploadSuccess', function( file, json ) {
	    if(json && json.code == '0000'){
	     	$("#info-form").modal('hide');
	    	refreshSysWxMaterialImgList();
	    }else{
    		MyFun.to.i(json.message || "文件上传失败");
	    }
	});

	uploader.on("error", function(type){
		if(type == 'F_DUPLICATE'){
			MyFun.to.i("请勿上传重复文件");
		}else if(type == 'Q_EXCEED_NUM_LIMIT'){
			MyFun.to.i("文件上传已达上限，将默认只上传第一个文件");
		}else{
			MyFun.to.i("文件上传失败，失败类型："+type);
		}
	})
	
	// 完成上传完了，成功或者失败。
	uploader.on( 'uploadComplete', function( file ) {
		//防止堵塞
		uploader.removeFile( file );
    	console.log("结束");
	});
}

</script>
