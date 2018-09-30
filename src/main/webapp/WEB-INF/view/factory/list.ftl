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
    <script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>

    <style>
        #J_excelUpload .webuploader-pick {
            cursor: pointer;
        }
        #J_excelUpload input[type="file"] {
            opacity: 0;
        }
        .J_autoCom_wrap {
            cursor: pointer;
            width: 80px;
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
            margin-bottom: 0;
            vertical-align: middle;
        }
        .fn-hide {
            display: none!important;
        }
    </style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>厂商字典</h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="J_name">名称：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入名称" name="name">
                        </div>
                            <div class="form-group col-md-3">
                            <label for="">编码：</label>
                            <input type="text" class="form-control" placeholder="输入编码" name="code">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','FactoryList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                         <a data-toggle="modal" class="btn btn-primary fn-mr-20"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/factory/byid.do','info-form')">新 增</a>
                         <a class="btn btn-danger fn-mr-20" id="J_merge">合 并</a>
                         <a class="btn btn-success fn-mr-20" id="J_excelUpload">上 传</a>
                         <button type="button" class="btn btn-default fn-mr-20" onclick="exportFile();">导出</button>
                         <button type="button" class="btn btn-default" onclick="exportNIFile();">导出不存在厂商</button>
                    </div>
                    <div id="FactoryList" data-url="${request.contextPath}/factory/table.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>



<script>
var clickFlag = 0;
  function refreshFactoryList(){
   	MyFun.ajaxRefreshTable("FactoryList");
  }

  $(function(){
     MyFun.ajaxRefreshTable("FactoryList");
      initExcelUpload();

    $("#J_merge").on("click", function(){
        mergeFactory();
    })

    $("#J_name").on("keyup", function(){
        var _self = $(this);
        _self.val(_self.val().replace(/(^\s*)|(\s*$)/g,""));
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
        server: "${request.contextPath}/factory/uploadxls.do",
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
            refreshCatalogList();

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
  

function exportFile(){
    var  url= "${request.contextPath}/factory/exportfile.do" ;
    window.open(url, "_blank");
}


function exportNIFile(){
    var  url= "${request.contextPath}/factory/exportnifactory.do" ;
    window.open(url, "_blank");
}

var checkAllClick = 0;
function zuiCheckAll(obj){
    if(checkAllClick)
        return false;
    checkAllClick = 1;
    var _obj = $(obj),
        checkAllFlag = _obj.find(".fa-square-o").hasClass("fn-hide") ? false : true,
        checkItems = _obj.parents(".dataTable").find("tbody .check_wrap");
    
    if(checkAllFlag){
        //全选
        checkItems.find(".fa-square-o").addClass("fn-hide").siblings(".fa-check-square-o").removeClass("fn-hide");

    }else{
        //取消全选
        checkItems.find(".fa-square-o").removeClass("fn-hide").siblings(".fa-check-square-o").addClass("fn-hide");

    }
    _obj.find(".fa").toggleClass("fn-hide");
    checkAllClick = 0;
}
function zuiCheckSelf(obj){
    var _obj = $(obj),
        _parents = _obj.parents(".dataTable"),
        checkItems = _parents.find("tbody .check_wrap");
    _obj.find(".fa").toggleClass("fn-hide");
    
    var _len = checkItems.find(".fa-square-o.fn-hide").length,
        _lenAll = checkItems.find(".fa-square-o").length;
    if(_len == _lenAll){
        _parents.find("thead .check_wrap").find(".fa-square-o").addClass("fn-hide").siblings(".fa-check-square-o").removeClass("fn-hide");
    }else{
        _parents.find("thead .check_wrap").find(".fa-square-o").removeClass("fn-hide").siblings(".fa-check-square-o").addClass("fn-hide");
    }
}

var zajaxClick = 0;
function mergeFactory(){
    var checkItems = $(".dataTable").find("tbody .check_wrap .fa-square-o"),
        ids = [];
    checkItems.each(function(){
        if($(this).hasClass("fn-hide")){
            ids.push($(this).data("id"));
        }
    })
    if((!ids.length) || (ids.length < 2)){
        MyFun.to.i("请至少选择两项");
        return false;
    }

    if(zajaxClick)
        return false;
    zajaxClick = 1;
    //合并厂商
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/factory/merge.do", "ids="+ids.join(","), function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "合并厂商成功");
                refreshFactoryList();
            }else{
                MyFun.to.e(json.message || "合并厂商失败");
            }
        }
        unBlock(".wrapper");
        zajaxClick = 0;
    })
}

</script>
</body>
</html>

