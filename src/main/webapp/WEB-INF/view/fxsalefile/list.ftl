<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> - 数据表格</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="shortcut icon" href="favicon.ico">
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
<script src="${request.contextPath}/js/my.js?v=2.0.0"></script>
<link href="${request.contextPath}/css/animate.css" rel="stylesheet">
<link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/layer/5.0.9/laydate.js"></script>

<style>
#J_excelUpload, #J_excelUploadS {
    margin: 0;
    padding: 0;
    height: 32px;
    line-height: 32px;
    width: 70px;
}
#J_excelUpload .webuploader-pick, #J_excelUploadS .webuploader-pick {
    cursor: pointer;
}
#J_excelUpload input[type="file"], #J_excelUploadS input[type="file"] {
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
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>销售数据导入</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="storeidS">销售日期：</label>
                            <input placeholder="销售日期" class="form-control layer-date" id="zuiSaleDate" name="zdate" readonly style="vertical-align:3px;cursor:pointer;">
                            <input value="" type="hidden" name="startdate" id="startdate" />
                            <input value="" type="hidden" name="enddate" id="enddate" />
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','FxSaleFileList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                        <select id="zuiStoreId" class="form-control" style="width: 172px;display: inline-block;"></select>
                        <span class="zpliter"></span>
                        <a class="btn btn-primary" id="J_excelUpload">上传</a>
                        <a class="J_demoDownload" href="${request.contextPath}/xssjsl.xls" target="_blank">示例下载</a>
                        <a class="J_demoDownload" href="javascript:;" id="J_demoImgShwo">示例图片查看</a>
                        <div id="J_demoImg" class="fn-hide" ><img src="${request.contextPath}/xssjsl.png"></div>
                        <span class="ztips">（请按照示例表格格式上传数据！）</span>
                    </div>
                    <div id="FxSaleFileList" data-url="${request.contextPath}/fxsalefile/table.do"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<textarea class="fn-hide" id="T_zuiStoreId">
<option value=""></option>
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>

<script>
var clickFlag = 0,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    indexsType = getQueryString("type") ? getQueryString("type") : "",
    startdate = enddate = '',
    p_getStoreId = '',
    p_getFileId = '';

function refreshFxSaleFileList(){
    MyFun.ajaxRefreshTable("FxSaleFileList");
}

$(function(){
    // curStoreId = window.parent.W_p_getStoreId();
    getZuiStore();
})

function getZuiStore(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/store/stores.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.totalRows !== 0){
                $("#zuiStoreId").temp($("#T_zuiStoreId").val(), json.data).find("option:eq(1)").attr({"selected":"selected"});
                p_getStoreId = $("#zuiStoreId").val();
                $("#storeid").val(p_getStoreId);

                $("#zuiStoreId").select2({
                    placeholder: "请选择门店",
                    allowClear: false,
                    language: "zh-CN",
                    dropdownAutoWidth: false
                });

                $("#zuiStoreId").on("select2:unselect", function(e){
                    p_getStoreId = '';
                    $("#storeid").val('');
                })
                $("#zuiStoreId").on("select2:select", function(e){
                    p_getStoreId = $("#zuiStoreId").val();
                    $("#storeid").val(p_getStoreId);
                })
            }else{
                MyFun.to.e(json.message || "查询药店列表失败");
            }
        }
        unBlock(".wrapper");

        //年月选择器，默认最近三个月
        var cdate = new Date(),
            s1 = cdate.formatDD("yyyy-MM"),
            s2 = '',
            m2 = cdate.getMonth()-2;
        if(m2<0){
            m2 = 13+m2;
            m2 = (m2<10 ? "0"+m2:m2);
            s2 = (cdate.getFullYear() - 1)+"-"+m2;
        }else if(m2==0){
            m2 = (m2==0 ? 1:m2);
            m2 = (m2<10 ? "0"+m2:m2);
            s2 = cdate.getFullYear()+"-"+m2;
        }else{
            m2++;
            m2 = (m2<10 ? "0"+m2:m2);
            s2 = cdate.getFullYear()+"-"+m2;
        }
        p_getFileId = s2+' - '+s1;
        startdate = s2;
        enddate = s1;
        $("#startdate").val(startdate);
        $("#enddate").val(enddate);
        laydate.render({
            elem: '#zuiSaleDate',
            type: 'month',
            range: true,
            max: 0,
            btns: ['confirm'],
            value: p_getFileId,
            done: function(value, date, endDate){
                p_getFileId = value;
                var _sp = value.split(" - ");
                startdate = _sp[0];
                enddate = _sp[1];
                $("#startdate").val(startdate);
                $("#enddate").val(enddate);
            }
        });

        setTimeout(function(){
            initZuiStorePage();
        }, 300)
    })
}

function initZuiStorePage(){
    MyFun.search('J_formSearch','FxSaleFileList');

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
}

function initExcelUpload(){
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${request.contextPath}/js/plugins/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: "${request.contextPath}/fxsalefile/upload.do",
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
        data.storeid = p_getStoreId;
    });

    uploader.on("uploadStart", function(file){
        if(!p_getStoreId){
            uploader.reset();
            MyFun.to.i("请选择门店");
            return false;
        }

        if(clickFlag)
            return false;
        clickFlag = 1;
        // createBlock(".wrapper", '正在上传...');
        createBlockUpload(".wrapper");
        console.log("正在上传");
    })

    uploader.on("uploadProgress", function( file, percentage ) {
        /**
         * 此处percentage只是文件上传完成的进度
         * 并不包括文件在后台的处理时间
        **/
        // console.log(Math.round( percentage * 100 ) + '%');
    })

    uploader.on( 'uploadSuccess', function( file, json ) {
        if(json && json.code == '0000'){
            MyFun.to.s(json.message || "文件上传成功");
            refreshFxSaleFileList();
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