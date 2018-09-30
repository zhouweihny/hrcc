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
<script src="${request.contextPath}/js/my.js?v=1.0.2"></script>
<link href="${request.contextPath}/css/animate.css" rel="stylesheet">
<link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/jquery.nicescroll/jquery.nicescroll.min.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">

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
.wrapper .table.dataTables-example.dataTable {
    width: 2000px;
}
#supplierid {
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
                    <h5>采购计划</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <input  type="hidden" value="desc" name="stype" >
                        <input  type="hidden" value="createtime" name="sfield" >
                        <div class="form-group col-md-3">
                            <label for="J_name">品种名：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入计划单" name="name">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','FxImpstoreList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t m-b">
                        <select id="supplierid" name="supplierid" class="form-control m-r" style="width: 242px;" ></select>
                        <a class="btn btn-primary" id="J_excelUpload">上传</a>
                        <a class="J_demoDownload" href="${request.contextPath}/storedrugDemo.xls" target="_blank">示例下载</a>
                        <a class="J_demoDownload" href="javascript:;" id="J_demoImgShwo">示例图片查看</a>
                        <div id="J_demoImg" class="fn-hide" ><img src="${request.contextPath}/storedrugDemo.png"></div>
                        <span class="ztips">（请按照示例表格格式上传数据！）</span>
                    </div>
                    <div id="FxImpstoreList" data-url="${request.contextPath}/fximpstore/table.do"></div>
               </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<script>
var clickFlag = 0,
    btnClickFlag = 0;

$(function(){

    setTimeout(function(){
        MyFun.search('J_formSearch','FxImpstoreList');

        if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){

        }else{
            var _w = $(window).width() - $(".zui-navbar").width() - 45;
            $("#FxImpstoreList").css({"width": _w, "paddingBottom": "30px"}).niceScroll();
        }

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

        $('#supplierid').select2({
            ajax: {
                url: "${request.contextPath}/store/stores.do",
                type : "POST",
                dataType: 'json',
                delay: 250,
                data: function(params) {
                    return {
                        keywords: params.term, // search term
                        page: params.page
                    };
                },
                processResults: function(data, params) {
                    params.page = params.page || 1;
                    var _cdata = data.data,
                        // _cArr = [{"id": "-1", "text": "全部"}];
                        _cArr = [];
                    if(_cdata.length){
                        for(var i=0, len=_cdata.length; i<len; i++){
                            var attr = {};
                            attr.id = _cdata[i].id;
                            attr.text = _cdata[i].storecode + ' - ' + _cdata[i].name;
                            _cArr.push(attr);
                        }
                    }
                    return {
                        results: _cArr,
                        pagination: {
                            more: (params.page * 30) < data.data.totalRows
                        }
                    };
                },
                cache: true
            },
            escapeMarkup: function(markup) {
                return markup;
            }, // let our custom formatter work
            minimumInputLength: 0,
            placeholder: "请输入门店名称",
            allowClear: true,
            language: "zh-CN",
            dropdownAutoWidth: true
        })

        initExcelUpload();
    }, 300)
})


function initExcelUpload(){
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${request.contextPath}/js/plugins/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: "${request.contextPath}/fximpstore/uploadxls.do",
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
        data.storeid = $("#supplierid").val();
    });

    uploader.on("uploadStart", function(file){
        var storeid = $("#supplierid").val();
        if(!storeid){
            uploader.reset();
            MyFun.to.i("请选择门店");
            return false;
        }

        if(clickFlag)
            return false;
        clickFlag = 1;
        createBlock(".wrapper", '正在上传...');
        console.log("正在上传");
    })

    // 当有文件添加进来的时候
    uploader.on( 'uploadSuccess', function( file, json ) {
        if(json && json.code == '0000'){
            MyFun.to.s(json.message || "文件上传成功");
            refreshFxImpsaledataList();
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

function refreshFxImpsaledataList(){
    MyFun.ajaxRefreshTable("FxImpstoreList");
}
</script>
</body>
</html>