<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 数据表格</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico"> <link href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Data Tables -->
    <link href="${request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <!-- 全局js -->
    <script src="${request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
  	 <script src="${request.contextPath}/js/jquery.form.js?v=1.0.0"></script>
    <!-- 自定义js -->
    <script src="${request.contextPath}/js/content.js?v=1.0.0"></script>
    <script src="${request.contextPath}/js/my.js?v=1.0.0"></script>
    <link href="${request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
 	<link href="${request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
 	<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
 	<script src="${request.contextPath}/js/plugins/layer/layer.min.js"></script>
 	<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
  	<script src="${request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
  	<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
	<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
	<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
	<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
	<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>	
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
	
	  <style>
        #J_excelUpload {
            margin: 0;
            padding: 0;
            height: 32px;
            line-height: 32px;
            width: 70px;
        }
        #J_excelUpload .webuploader-pick {
            cursor: pointer;
        }
        #J_excelUpload input[type="file"] {
            opacity: 0;
        }
        .J_demoDownload {
            display: inline-block;
            vertical-align: middle;
            text-decoration: underline;
            margin-left: 8px;
        }
        .J_demoDownload:hover {
            text-decoration: underline;
            color: #FF8245;
        }
        .ztips {
            display: inline-block;
            margin-left: 8px;
            color: red;
            vertical-align: middle;
            cursor: default;
            font-size: 12px;
        }
.J_autoCom_wrap {
    display: block;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    cursor: pointer;
}
.J_autoCom_wrap .check_wrap {
    display: inline-block;   
}
.J_autoCom_wrap .check_wrap .fa {
    cursor: pointer;
    font-size: 19px;
    display: inline-block;
    vertical-align: middle;
}
.J_autoCom_wrap .check_wrap .fa-square-o {
    margin-right: 3px;
}
.J_autoCom_wrap label {
    cursor: pointer;
    display: inline-block;
    padding: 0 15px 0 5px;
}
.fn-hide {
    display: none!important;
}
@media (min-width: 768px){
    .modal-dialog {
        width: 800px;
    }
}
.zdisable {
    color: #a7a7a7;
}
.zpliter {
    display: inline-block;
    width: 15px;
    border-right: 1px solid #ada5a5;
    margin-right: 15px;
    height: 24px;
    vertical-align: middle;
    cursor: default;
}
#J_excelUpload {
  margin: 0;
  padding: 0;
  height: 30px;
  line-height: 30px;
  width: 54px;
  margin-top: -3px;
}
#J_excelUpload .webuploader-pick {
    cursor: pointer;
}
#J_excelUpload input[type="file"] {
    opacity: 0;
}
    </style>
	
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>基本 <small>分类，查找</small></h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <input  type="hidden" value="desc" name="stype" >
						<input  type="hidden" value="createtime" name="sfield" >
                    
                        <div class="form-group col-md-3">
                            <label for="J_name">品名：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入品名" name="name">
                        </div>
                        
                       	<div class="form-group col-md-3">
							<label for="J_name">状态：</label>
							<select   name="status" class="form-control input-sm">
								<option value="">所有</option>  
								<option value="0">停用</option>  
								<option value="1">启用</option>  
							</select>	 
						</div>
                        
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','DrugPromotionList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t m-b">
                         <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/drugpromotion/byid.do','info-form')">新增</a>
                         
                        <span class="zpliter"></span>

                        <a class="btn btn-primary" id="J_excelUpload">上传</a>
                        <a class="J_demoDownload" href="${request.contextPath}/promotion.xls" target="_blank">示例下载</a>
                        <a class="J_demoDownload" href="javascript:;" id="J_demoImgShwo">示例图片查看</a>
                        <div id="J_demoImg" style="display: none;" ><img src="${request.contextPath}/promotion.png"></div>
                        <span class="ztips">（请按照示例表格格式上传数据！）</span>
                    </div>
                    
                    <div id="DrugPromotionList" data-url="${request.contextPath}/drugpromotion/table.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>
<div id="info-formS" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>选择客户</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl" aria-describedby="DataTables_Table_0_info">
                            
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white m-r" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-danger" id="J_sendBtn">发送</button>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_top_tpl">
<thead>
    <tr role="row">
        <th>客户</th>
        <th style="text-align: center;">
            <div class="J_autoCom_wrap" id="J_selectAll" title="全选">
                <div class="check_wrap">
                    <i class="fa fa-square-o" aria-hidden="true"></i>
                    <i class="fa fa-check-square-o fn-hide" aria-hidden="true"></i>
                </div>
            </div>
        </th>
    </tr>
</thead>
<tbody>
    {{#each this}}
    <tr>
        <td>{{company}}</td>
        <td style="text-align: center;">
            {{#if_eq checked compare = true }}
            <div class="J_autoCom_wrap zdisable" data-id="{{id}}" title="已发送">
                <div class="check_wrap">
                    <i class="fa fa-square-o fn-hide" aria-hidden="true"></i>
                    <i class="fa fa-check-square-o" aria-hidden="true"></i>
                </div>
            </div>
            {{else}}
            <div class="J_autoCom_wrap" data-id="{{id}}" title="选择">
                <div class="check_wrap">
                    <i class="fa fa-square-o" aria-hidden="true"></i>
                    <i class="fa fa-check-square-o fn-hide" aria-hidden="true"></i>
                </div>
            </div>
            {{/if_eq}}
        </td>
    </tr>
    {{/each}}
</tbody>
</textarea>

<script>
var clickFlag = 0,
    btnClickFlag = 0,
    zpurchaseId = "";

  function refreshDrugPromotionList(){
   	MyFun.ajaxRefreshTable("DrugPromotionList");
  }

  $(function(){
 	initExcelUpload();
 	$("#J_demoImgShwo").on("click", function(){
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: '100%',
            offset: '160px',
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: $('#J_demoImg')
        });
    })
	MyFun.search('J_formSearch','DrugPromotionList');


    $("#J_sendBtn").on("click", function(){
        layer.confirm("确定发送给客户？", {btn: ['确定','取消'] }, function(){
            layer.closeAll('dialog');
            sendPurchaseList();
            
        }, function(){
        } );
    })
  })
  
  function initExcelUpload(){
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${request.contextPath}/js/plugins/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: "${request.contextPath}/drugpromotion/uploadxls.do",
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id: $("#J_excelUpload"), // id
            multiple: false  // false  单选 
        },
        fileVal:"uploadfile",//设置文件上传域的name
        formData: {
            "group": "药品目录上传"
        },
        // fileNumLimit: window._IE ? "1" : "5",
        fileNumLimit: '1',
        // paste: document.body,//ctrl+v粘贴
        duplicate: true, //可重复上传同一文件
        timeout: 0,//关闭超时，默认2分钟
        // 只允许选择excel文件。只有IE会识别
        // http://www.w3school.com.cn/media/media_mimeref.asp
        accept: {
          title: 'Excel',
          extensions: 'xls,xlsx',
          mimeTypes: 'application/excel'
        }
    });

    uploader.on("filesQueued", function(files){
        
    })

    //当某个文件的分块在发送前触发，主要用来询问是否要添加附带参数，大文件在开起分片上传的前提下此事件可能会触发多次。
    uploader.on('uploadBeforeSend', function (obj, data, headers) {
        
    });

    uploader.on("uploadStart", function(file){
        if(clickFlag)
            return false;
        clickFlag = 1;
        createBlock(".wrapper", '正在上传...');
        console.log("正在上传");
    })

    // 当有文件添加进来的时候
    uploader.on( 'uploadSuccess', function( file, json ) {
        if(json && json.code == '0000'){
            refreshDrugPromotionList();
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
          clickFlag = 0;
        unBlock(".wrapper");
        console.log("结束");
    });
  }

function sendPurchase(zid){
  if(!zid){
      MyFun.to.e("数据错误");
      return false;
  }
  $(".select2-container").css({"zIndex": "1"});
  zpurchaseId = zid;
  $("#info-formS").modal('show');
  $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
      $(".select2-container").css({"zIndex": "9999"});
      $("#J_top_tbl").html("");
  })
  $('#info-formS').off('shown.bs.modal').on('shown.bs.modal', function (e) {
      getUsedsuppliers();
  })
}

function getUsedsuppliers(){
    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock("#J_top_tbl", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/drugpromotion/purchasers.do", "promotionid="+zpurchaseId, function(state, json){
        if(state == 'success'){
            if(json && json.length){
                $("#J_top_tbl").temp($("#T_top_tpl").val(), json);

                var _J_autoCom_wrap = $("#J_top_tbl tbody .J_autoCom_wrap").not(".zdisable");
                _J_autoCom_wrap.each(function(){
                    var _this = $(this);
                    _this.off("click").on("click", function(){
                        _this.find(".fa").toggleClass("fn-hide");

                        judgeSelectAll(_J_autoCom_wrap, json.length);
                    })
                })
                $("#J_selectAll").off("click").on("click", function(){
                    var _self = $(this);
                    _self.find(".fa").toggleClass("fn-hide");
                    var selectAll = _self.find(".fa-check-square-o").hasClass("fn-hide") ? false : true;
                    if(selectAll){
                        _J_autoCom_wrap.each(function(){
                            $(this).find(".fa-check-square-o").removeClass("fn-hide").siblings(".fa-square-o").addClass("fn-hide");
                        })
                    }else{
                        _J_autoCom_wrap.each(function(){
                            $(this).find(".fa-check-square-o").addClass("fn-hide").siblings(".fa-square-o").removeClass("fn-hide");
                        })
                    }
                    
                })
            }else{
                MyFun.to.e(json.message || "查询所有客户失败");
            }
        }
        clickFlag = 0;
        unBlock("#J_top_tbl");
    })
}


function sendPurchaseList(){

    var supplierids = [];

    $("#J_top_tbl tbody .J_autoCom_wrap").each(function(){
        var _this = $(this),
            id = _this.data("id");
        if(_this.hasClass("zdisable"))
            return;
        if(_this.find(".fa-square-o").hasClass("fn-hide")){
            supplierids.push(id);
        }
    })

    if(!supplierids.length){
        MyFun.to.i("请选择客户");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock("#J_top_tbl", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/drugpromotion/sendpurchasers.do", "promotionid="+zpurchaseId+"&purchaserids="+supplierids.join(","), function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "发送成功");
                $("#info-formS").modal('hide');
                refreshDrugPromotionList();
            }else{
                MyFun.to.e(json.message || "发送失败");
            }
        }
        clickFlag = 0;
        unBlock("#J_top_tbl");
    })
}

function judgeSelectAll(obj, len){
    var count = 0;
    obj.each(function(){
        var _this = $(this),
            _selct = _this.find(".fa-square-o");
        if(_selct.hasClass("fn-hide")){
            count++;
        }
    })
    if(count == len){
        $("#J_selectAll").find(".fa-check-square-o").removeClass("fn-hide").siblings(".fa-square-o").addClass("fn-hide");
    }else{
        $("#J_selectAll").find(".fa-check-square-o").addClass("fn-hide").siblings(".fa-square-o").removeClass("fn-hide");
    }
}

</script>
</body>
</html>