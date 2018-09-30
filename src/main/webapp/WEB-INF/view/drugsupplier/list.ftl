<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> - 数据表格</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
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
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<style>
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
#zsupplierid {
    display: inline-block;
}
.select2-container {
    margin-right: 10px;
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
                        <div class="form-group col-md-3">
                            <label for="J_name">药品：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入药品" name="name">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">代理：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入代理" name="agent">
                        </div>
                      	<div class="form-group col-md-3">
                            <label for="J_name">配送公司：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入配送公司" name="company">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','DrugSupplierList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t m-b">
                        <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/drugsupplier/byid.do','info-form')">新增</a>

                        <span class="zpliter"></span>
                        
                        <a class="btn btn-primary" id="J_excelUpload">上传</a>
                        <a class="J_demoDownload" href="${request.contextPath}/DrugSupplier.xls" target="_blank">示例下载</a>
                        <a class="J_demoDownload" href="javascript:;" id="J_demoImgShwo">示例图片查看</a>
                        <div id="J_demoImg" class="fn-hide" ><img src="${request.contextPath}/DrugSupplier.png"></div>
                        <span class="ztips">（请按照示例表格格式上传数据！）</span>
                    </div>
                    <div id="DrugSupplierList" data-url="${request.contextPath}/drugsupplier/table.do">
                    </div>    
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<script>

MyFun.appendAjaxHtml=function(url,divid,callback){
      var arguments1=Array.prototype.slice.call(arguments).slice(3);
      if( $("#"+divid) ){
          var _$divid = $("#"+divid);
          _$divid.empty();
          var htmlobj=$.ajax({url:url,async:false});
          _$divid.append(htmlobj.responseText); 
          
          if(_$divid.find("form").hasClass("J_judgeChage")){
              //如果需要判断是否修改
              var str = _$divid.find("form").formSerialize();
              _$divid.find("form").data("zui_oldFormStr", str);
          }
      }else{
          var parentdiv=$('<div id="' +divid+  '" ></div>');  
          var htmlobj=$.ajax({url:url,async:false});
          parentdiv.append(htmlobj.responseText); 
          $(document.body).append(parentdiv);  
      }
  if(callback){
    var cn = eval('('+callback+')');
    if(typeof cn == 'function'){
        cn(arguments1);
    }
  }

  $(".select2-container").css({"zIndex": "1"});
  $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
      $(".select2-container").css({"zIndex": "9999"});
      $("#J_top_tbl").html("");
  })
};

var clickFlag = 0;

  function refreshDrugSupplierList(){
   	MyFun.ajaxRefreshTable("DrugSupplierList");
  }

  $(function(){
     MyFun.ajaxRefreshTable("DrugSupplierList");

     setTimeout(function(){
         initExcelUpload();
     }, 300)

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

  })

function initExcelUpload(){
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${request.contextPath}/js/plugins/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: "${request.contextPath}/drugsupplier/uploadxls.do",
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
         MyFun.to.i("上传成功");
            refreshDrugSupplierList();

        }else{
            MyFun.to.i(json.message || "文件上传失败");
        }
    });

    uploader.on("error", function(type){
        if(type == 'F_DUPLICATE'){
            MyFun.to.i("请勿上传重复文件");
        }else if(type == 'Q_EXCEED_NUM_LIMIT'){
            MyFun.to.i("文件上传已达上限，将默认只上传第一个文件");
        }else if(type == 'Q_TYPE_DENIED'){
            MyFun.to.i("文件类型选择错误");
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
</script>
</body>
</html>

