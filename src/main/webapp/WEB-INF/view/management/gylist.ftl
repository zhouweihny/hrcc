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
<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<link href="${request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

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
div.dataTables_info {
    padding-top: 5px;
    margin-left: 15px;
    display: inline-block;
}
.table-striped>tbody>tr:nth-of-type(odd) {
    background-color: #fff;
}
.table-striped>tbody>tr:nth-of-type(even) {
    background-color: #f9f9f9;
}
.dataTables_wrapper {
    position: relative;
}
.dataTables_wrapper .dataTables_processing {
    position: absolute;
    left: 0;
    top: 0;
    background: rgba(255, 255, 255, .3);
    width: 100%;
    height: 100%;
    z-index: 99;
    text-align: center;
    line-height: 260px;
    font-size: 22px;
}
.modal-dialog {
    width: 1100px!important;
}
.modal-open .modal .ibox-content {
    /*max-height: 550px;*/
}
.modal-dialog .ibox.float-e-margins {
    margin-bottom: 10px;
}
.J_formSearch_D {
    background: #F5F5F6;
    padding: 10px 0;
    border: 1px solid #e7e7e7;
    border-bottom-color: #ddd;
    margin: 20px 0;
}
.J_formSearch_D label {
    display: inline-block;
    vertical-align: middle;
    width: 45px;
    margin-bottom: 0;
}
.J_formSearch_D .form-control {
    vertical-align: middle;
}
.OrderList_paginate_jump {
    float: right;
    height: 25px;
    line-height: 25px;
    margin-left: 15px;
}
.OrderList_paginate_jump span {
    cursor: default;
}
.OrderList_paginate_jump input {
    width: 38px;
    height: 27px;
    line-height: 27px;
    margin: 0 3px;
    font-size: 14px;
    text-align: center;
    border: 1px solid #ddd;
    border-radius: 4px;
}
.OrderList_paginate_jump .btn {
    margin-left: 10px;
}
.modal-open .modal .ibox-content {
    max-height: 500px;
}
.modal.in .modal-dialog {
    width: 700px;
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
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>品种目录</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline " id="J_formSearch" action="javascript:;">
                        <div class="row">
                            <div class="form-group col-md-3">
                                <label for="J_name">品名：</label>
                                <input type="text" class="form-control" id="J_name" placeholder="输入品名" name="name">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="J_name">厂商：</label>
                                <input type="text" class="form-control" id="J_name" placeholder="输入厂商" name="factory">
                            </div>
                            <div class="form-group col-md-2">
                                <label for="J_name">未对照树：</label>
                                <select name="treeid" class="form-control">
                                    <option value="">所有</option>
                                    <option value="18346A0044A148D1A818627B25510E59">病种配方树</option>
                                    <option value="654CA2E6C2164A148F287B57A4483AF7">品种分类树</option>
                                </select>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="J_name">通用是否对照：</label>
                                <select name="dz" class="form-control">
                                    <option value="">所有</option>
                                    <option value="0">未对照</option>
                                    <option value="1">已对照</option>
                                </select>
                                <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','DrugList');" style="float:right;"><i class="fa fa-search"></i>查 询</button>
                            </div>
                        </div>
                    </form>
                    <div class="m-t">
                        <button type="button" class="btn btn-w-m btn-danger" onclick="javascript:MyFun.objOperate('${request.contextPath}/fxcomname/autocompare.do','确定比对?','refreshDrugList')" style=" height: 32px;vertical-align: -2px;">通用名比对</button>
                        <span class="zpliter"></span>
                        <a class="btn btn-primary" id="J_excelUpload">上传</a>
                        <a class="J_demoDownload" href="${request.contextPath}/gyssl.xls" target="_blank">示例下载</a>
                        <a class="J_demoDownload" href="javascript:;" id="J_demoImgShwo">示例图片查看</a>
                        <div id="J_demoImg" class="fn-hide" ><img src="${request.contextPath}/gyssl.png"></div>
                        <span class="ztips">（请按照示例表格格式上传数据！）</span>
                    </div>
                    <div id="DrugList" data-url="${request.contextPath}/management/gytable.do"></div>    
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
                    <h5>通用名绑定<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container" id="J_formBind">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>名称</th>
                                    <th>规格</th>
                                    <th>单位</th>
                                    <th>剂型</th>
                                    <th>厂商</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                        <form class="form-inline row J_formSearch_D" id="J_formSearch_D" action="javascript:;">
                            <div class="form-group col-md-3">
                                <label for="spaceid">名称：</label>
                                <input type="text" class="form-control" placeholder="输入药品名称" name="name">
                            </div>
                            <div class="form-group col-md-9">
                                <button type="button" class="btn btn-w-m btn-info" id="J_searchBtn_D"><i class="fa fa-search"></i>查 询</button>
                            </div>
                        </form>
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>名称</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_tree_tbl">
{{#each this}}
<tr class="gradeX">
    <td>{{name}}</td>
    <td>
        <a href="javascript:;" onclick="bindCom('{{id}}')">绑定</a>
    </td>
</tr>
{{/each}}
</textarea>

<script>
var clickFlag = 0,
    zuiTblS = null;

function refreshDrugList(){
    MyFun.ajaxRefreshTable("DrugList");
}

$(function(){
    MyFun.ajaxRefreshTable("DrugList");

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

    $("#J_searchBtn_D").on("click", function(){
        if(zuiTblS){
            zuiTblS.destroy();
            zuiTblS = null;
        }
        getzPageS();
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
        server: "${request.contextPath}/management/gyupload.do",
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
            refreshDrugList();

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

function getzPageS(){
    $('#J_bottom_tbl tbody').html("");

    zuiTblS = $('#J_bottom_tbl').DataTable( {
        language: {
            sProcessing: "正在努力加载数据..."
        },
        dom: '<"top">rt<"bottom"flpi><"clear">',
        ordering: false,//关闭表格的排序功能
        serverSide: true,  //启用服务器端分页
        processing: true,  //隐藏加载提示,自行处理
        searching: false, // 禁用搜索
        ajax: function (data, callback, settings) {
            var formData = $("#J_formSearch_D").formSerialize();
            formData += '&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

            //ajax请求数据
            $.ajax({
                type: "post",
                url: "${request.contextPath}/fxcomname/fxcomnames.do",
                cache: false,  //禁用缓存
                data: formData,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    //封装返回数据
                    var returnData = {};
                    returnData.draw = data.draw;
                    if(result && result.totalRows){
                        returnData.recordsTotal = result.totalRows;//返回数据全部记录
                        returnData.recordsFiltered = result.totalRows;
                        returnData.data = result.data;//返回的数据列表
                    }else{
                        returnData.recordsTotal = 0;
                        returnData.recordsFiltered = 0;
                        returnData.data = [];
                    }

                    //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                    //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                    callback(returnData);
                },
                error: function(e) {
                    if(e.statusText == 'timeout'){
                        MyFun.to.e("连接超时！");
                    }else if(e.status == 500){
                        MyFun.to.e("服务器错误");
                    }else{
                        MyFun.to.e("系统错误，请稍后重试");
                    }
                },
                complete: function(xhr, textStatus){
                    
                }
            });
        },
        columns: [
            { "data": "name", "width":"280px" },
            { "data": "", "width":"70px" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row) {
                    return '<a href="javascript:;" class="J_compare" onclick="bindCom(\''+row.id+'\');">绑定</a>';
                },
                "targets": 1
            }
        ],
        drawCallback: function(settings){
            //增加跳转到某页
            if(!$("#J_bottom_tbl_paginate_jump").length){
                var _strArr = [
                    '<div id="J_bottom_tbl_paginate_jump" class="OrderList_paginate_jump">',
                    '<span>到第</span>',
                    '<input class="input-txt" maxlength="4" value="">',
                    '<span>页</span>',
                    '<a class="btn btn-white">确定</a>',
                    '</div>'
                ]
                $("#J_bottom_tbl_paginate").before(_strArr.join(""));
                $("#J_bottom_tbl_paginate_jump").off("click").on("click", ".btn", function(){
                    var _val = $(this).siblings(".input-txt").val();
                    gotoPage('2', _val);
                    return false;
                }).off("keyup").on("keyup", ".input-txt", function(event){
                    this.value=this.value.replace(/[^0-9]/g,'');
                    if(event.keyCode==13){
                        var _val = $(this).val();
                        gotoPage('2', _val);
                        return false;
                    }
                })
            }
        }
    });
}

function bindCom(id){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/binding.do", "comnameid="+id+"&drugid="+curDrugId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "绑定通用名成功");

                // $("#info-formS").modal('hide');
                refreshDrugList();
            }else{
                MyFun.to.e(json.message || "绑定通用名失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function bindComoname(obj){
    var _selft = $(obj),
        name = _selft.data("name"),
        specifications = _selft.data("specifications"),
        unit = _selft.data("unit"),
        dosageform = _selft.data("dosageform"),
        factory = _selft.data("factory");
    curDrugId = _selft.data("id");
    
    $("#J_formBind").removeClass("fn-hide");
    $("#J_ztreeBind").addClass("fn-hide");

    var _tr = '<tr><td>'+(name || "")+'</td><td>'+(specifications || "")+'</td><td>'+(unit || "")+'</td><td>'+(dosageform || "")+'</td><td>'+(factory || "")+'</td></tr>';
    $("#J_top_tbl tbody").html(_tr);

    $("#info-formS").modal('show');
    $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        if(zuiTblS){
            zuiTblS.destroy();
            zuiTblS = null;
        }

        $("#J_formSearch_D").resetForm();
        $("#J_tree_tbl tbody").html("");
    })

    $('#info-formS').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        getzPageS();
    })
}

function gotoPage(type, page){
    if(!page)
        return false;
    page = parseInt(page, 10) - 1;
    if(page < 0)
        page = 0;
    if(type == '1'){
        zuiTbl.page(page).draw(false);
        $("#OrderList_paginate_jump .input-txt").val("");
    }else if(type == '2'){
        zuiTblS.page(page).draw(false);
        $("#J_bottom_tbl_paginate_jump .input-txt").val("");
    }
}
</script>
</body>
</html>

