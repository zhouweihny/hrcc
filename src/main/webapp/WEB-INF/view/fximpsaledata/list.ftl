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
<script src="${request.contextPath}/js/my.js?v=1.0.9"></script>
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
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>

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
#supplierid {
    display: inline-block;
}
.select2-container {
    margin-right: 10px;
}
.table-striped>tbody>tr:nth-of-type(even) {
    background-color: #f9f9f9;
}
.table-striped>tbody>tr:nth-of-type(odd) {
    background-color: #fff;
}
.tab-pane .dataTables-example {
    margin-top: 15px!important;
}
.modal-open .modal .ibox-content {
    max-height: 500px;
}
.modal.in .modal-dialog {
    width: 700px;
}
#J_imptype, #J_imptypeYear {
    display: inline-block;
    vertical-align: middle;
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
	                    <input  type="hidden" value="desc" name="stype" >
	                    <input  type="hidden" value="createtime" name="sfield" >
	                    <div class="form-group col-md-4">
	                        <label for="J_name">品种名：</label>
	                        <input type="text" class="form-control" id="J_name" placeholder="输入品种名" name="name">
	                    </div>
	                    <div class="form-group col-md-2">
	                        <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','FxImpsaledataList');"><i class="fa fa-search"></i>查 询</button>
	                    </div>
	                </form>
	                <div class="m-t m-b">
                        <select id="J_imptypeYear" name="J_imptypeYear" class="form-control m-r" style="width: 90px;"  >
                            
                        </select>
                        <select id="J_imptype" class="form-control m-r" style="width: 90px;"  >
                            <option value=""></option>
                            <option value="1">全年</option>
                            <option value="2">一季度</option>
                            <option value="3">二季度</option>
                            <option value="4">三季度</option>
                            <option value="5">四季度</option>
                            <option value="6">月度</option>
                        </select>
                        <select id="supplierid" name="supplierid" class="form-control m-r" style="width: 242px;" ></select>
	                    <a class="btn btn-primary" id="J_excelUpload">上传</a>
	                    <a class="J_demoDownload" href="${request.contextPath}/saledrugDemo.xls" target="_blank">示例下载</a>
	                    <a class="J_demoDownload" href="javascript:;" id="J_demoImgShwo">示例图片查看</a>
	                    <div id="J_demoImg" class="fn-hide" ><img src="${request.contextPath}/saledrugDemo.png"></div>
                        <div id="J_demoImgS" class="fn-hide" ><img src="${request.contextPath}/storedrugDemo.png"></div>
	                    <span class="ztips">（请按照示例表格格式上传数据！）</span>
	                </div>
	                <div id="FxImpsaledataList" data-url="${request.contextPath}/fximpfilename/table.do"></div>
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
                    <h5>分析统计<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <ul class="nav nav-tabs" id="myTabs">
                        <li role="presentation" class="active"><a href="#home">数量统计</a></li>
                        <li role="presentation"><a href="#profile">购买频率</a></li>
                        <li role="presentation"><a href="#Messages">利润排名</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl_1" aria-describedby="DataTables_Table_0_info">
                                <thead>
                                    <tr role="row">
                                        <th>大类数</th>
                                        <th>中类数</th>
                                        <th>小类数</th>
                                        <th>通用名</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td>合计</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl_2" aria-describedby="DataTables_Table_0_info">
                                <thead>
                                    <tr role="row">
                                        <th>品类名称</th>
                                        <th>购物频率</th>
                                        <th>排名</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                </tbody>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="Messages" aria-labelledby="dropdown1-tab">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl_3" aria-describedby="DataTables_Table_0_info">
                                <thead>
                                    <tr role="row">
                                        <th>品类名称</th>
                                        <th>利润排名（占比）</th>
                                        <th>销售额排名（占比）</th>
                                        <th>贡献</th>
                                        <th>排名</th>
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
</div>

<div id="info-formSF" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>库存导入<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <div class="J_storeImpwrap">
                            <a class="J_demoDownload" href="${request.contextPath}/storedrugDemo.xls" target="_blank">示例下载</a>
                            <a class="J_demoDownload" href="javascript:;" id="J_demoImgShwoS">示例图片查看</a>
                            <span class="ztips">（请按照示例表格格式上传数据！）</span>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl_4" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>门店</th>
                                    <th>销售文件名</th>
                                    <th>销售季度</th>
                                    <th>销售上传时间</th>
                                    <th style="text-align: right;">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td style="text-align: right;"><a class="btn btn-primary" id="J_excelUploadS">导入</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_tpl_1">
{{#each this}}
<tr>
    <td>{{treename1}}</td>
    <td>{{num2}}</td>
    <td>{{num3}}</td>
    <td>{{num4}}</td>
</tr>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_tpl_2">
{{#each this}}
<tr>
    <td>{{treename1}}</td>
    <td>{{rank}}</td>
    <td>{{indexAdd @index}}</td>
</tr>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_tpl_3">
{{#each this}}
<tr>
    <td>{{treename1}}</td>
    <td>{{profit}}</td>
    <td>{{sale}}</td>
    <td>{{contribution}}</td>
    <td>{{rank}}</td>
</tr>
{{/each}}
</textarea>

<script>
var clickFlag = 0,
    btnClickFlag = 0,
    ajax1 = 0,
    ajax2 = 0,
    ajax3 = 0,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    indexsType = getQueryString("type") ? getQueryString("type") : "",
    curStoreId = '',
    curStoreIdS = '',
    curSaleDataId = '',
    curTypeYear = '',
    curType = '';

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        curStoreId = window.parent.W_p_getStoreId();
        $("#supplierid").remove();
        $("#J_formSearch").append('<input  type="hidden" value="'+curStoreId+'" name="storeid" >');
    }else{
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
        $("#supplierid").on("select2:unselect", function(e){
            curStoreId = '';
        })
        $("#supplierid").on("select2:select", function(e){
            curStoreId = $("#supplierid").val();
        })
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

    var _curYear = new Date().getFullYear(),
        _str = '<option></option>',
        _tempYear = _curYear;
    curTypeYear = _curYear;
    _str += '<option value="'+_curYear+'" selected="selected">'+_curYear+'</option>';
    for(var i=1; i<11; i++){
        _tempYear -= 1;
        _str += '<option value="'+_tempYear+'">'+_tempYear+'</option>';
    }
    
    $("#J_imptypeYear").html(_str).select2({
        placeholder: "请选择年份",
        allowClear: true,
        language: "zh-CN",
        dropdownAutoWidth: false
    });
    $("#J_imptypeYear").on("select2:unselect", function(e){
        curTypeYear = '';
    })
    $("#J_imptypeYear").on("select2:select", function(e){
        curTypeYear = $("#J_imptypeYear").find("option:selected").text();
    })

    $("#J_imptype").select2({
        placeholder: "请选择季度",
        allowClear: true,
        language: "zh-CN",
        dropdownAutoWidth: false
    });
    $("#J_imptype").on("select2:unselect", function(e){
        curType = '';
    })
    $("#J_imptype").on("select2:select", function(e){
        curType = $("#J_imptype").find("option:selected").text();
    })

    $('#myTabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    })

    $('#myTabs a').on('shown.bs.tab', function (e) {
        var index = $(this).parent().index();
        switch(index){
            case 1: 
                if(!ajax2){
                    ajax2 = 1;
                    getTabPage2();
                }
                break;
            case 2: 
                if(!ajax3){
                    ajax2 = 1;
                    getTabPage3();
                }
                break;
        }
    })

    MyFun.search('J_formSearch','FxImpsaledataList');
    setTimeout(function(){
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
        server: "${request.contextPath}/fximpsaledata/uploadxls.do",
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
        data.storeid = curStoreId;
        data.imptype = "（"+curTypeYear+"）"+curType;
    });

    uploader.on("uploadStart", function(file){
        if(!curTypeYear){
            uploader.reset();
            MyFun.to.i("请选择年份");
            return false;
        }

        if(!curType){
            uploader.reset();
            MyFun.to.i("请选择季度");
            return false;
        }

        if(!curStoreId){
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
        console.log(Math.round( percentage * 100 ) + '%');
    })

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
 	MyFun.ajaxRefreshTable("FxImpsaledataList");
}

function showAnalysis(id){
    curSaleDataId = id;
    $(".select2-container").css({"zIndex": "1"});
    $("#info-formS").modal('show');
    $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $(".select2-container").css({"zIndex": "9999"});
        ajax1 = ajax2 = ajax3 = 0;
        $('#myTabs li:eq(0) a').tab('show');
        $("#J_top_tbl_1 tbody, #J_top_tbl_2 tbody, #J_top_tbl_3 tbody").html('');
    })

    $('#info-formS').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        getTabPage();
    })
}

function getTabPage(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxdrugtype/typecount.do", "planid="+curSaleDataId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#J_top_tbl_1 tbody").temp($("#T_tpl_1").val(), json.data);

                    var total1 = total2 = total3 = 0;
                    for(var i=0,len=json.data.length; i<len; i++){
                        total1 += parseInt((json.data[i].num2 || 0), 10);
                        total2 += parseInt((json.data[i].num3 || 0), 10);
                        total3 += parseInt((json.data[i].num4 || 0), 10);
                    }
                    var _tr = $("#J_top_tbl_1 tfoot").find("tr");
                    _tr.find("td:eq(1)").text(total1);
                    _tr.find("td:eq(2)").text(total2);
                    _tr.find("td:eq(3)").text(total3);
                }else{
                    $("#J_top_tbl_1 tbody").html('<tr><td colspan="4">暂无统计信息</td></tr>');
                }
            }else{
                MyFun.to.e(json.message || "查询数量统计失败");
            }
        }
        ajax1 = 1;
        unBlock(".modal-dialog");
    })
}

function getTabPage2(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxdrugtype/salelevel.do", "planid="+curSaleDataId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#J_top_tbl_2 tbody").temp($("#T_tpl_2").val(), json.data);
                }else{
                    $("#J_top_tbl_2 tbody").html('<tr><td colspan="2">暂无统计信息</td></tr>');
                }
            }else{
                MyFun.to.e(json.message || "查询购买频率失败");
            }
        }
        ajax2 = 1;
        unBlock(".modal-dialog");
    })
}

function getTabPage3(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxdrugtype/salelevel.do", "planid="+curSaleDataId, function(state, json){
        if(state == 'success'){
            var json = {"code": "0000", "message": "", "result": true, "data": [{"treename1": "感冒", "profit": "1", "sale": "2", "contribution": "1.6", "rank": "1"}, {"treename1": "消化", "profit": "2", "sale": "1", "contribution": "1.8", "rank": "2"}, {"treename1": "妇科", "profit": "3", "sale": "3", "contribution": "2.0", "rank": "3"} ] };
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#J_top_tbl_3 tbody").temp($("#T_tpl_3").val(), json.data);
                }else{
                    $("#J_top_tbl_3 tbody").html('<tr><td colspan="4">暂无统计信息</td></tr>');
                }
            }else{
                MyFun.to.e(json.message || "查询利润排名失败");
            }
        }
        ajax3 = 1;
        unBlock(".modal-dialog");
    })
}

/**
 * 返回索引
 */
Handlebars.registerHelper("indexAdd", function(txt,fn) {
    var buffer = txt+1;
    return buffer;
});

function impStore(obj, salefileid, storeid){
    curStoreIdS = storeid;
    curSaleDataId = salefileid;

    var _this = $(obj),
        _tr = _this.parent().parent(),
        storename = _tr.find("td:eq(2)").text() || '',
        salefile = _tr.find("td:eq(1)").text() || '',
        imptype = _tr.find("td:eq(3)").text() || '',
        createtime = _tr.find("td:eq(0)").text() || '';

    $(".select2-container").css({"zIndex": "1"});
    $("#info-formSF").modal('show');
    $('#info-formSF').off('hidden.bs.modal').off('shown.bs.modal').on('hidden.bs.modal', function (e) {
        $(".select2-container").css({"zIndex": "9999"});
    }).on('shown.bs.modal', function(e){
        $("#J_top_tbl_4 tbody tr").find("td").eq(0).html(storename).end().end()
                                    .find("td").eq(1).html(salefile).end().end()
                                    .find("td").eq(2).html(imptype).end().end()
                                    .find("td").eq(3).html(createtime);

        $("#J_demoImgShwoS").off("click").on("click", function(){
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area: '100%',
                offset: '60px',
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: $('#J_demoImgS')
            });
        })

        initExcelUploadS();
    })
}

function initExcelUploadS(){
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
            id: $("#J_excelUploadS"), // id
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
        data.salefileid = curSaleDataId;
        data.storeid = curStoreIdS;
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
            MyFun.to.s(json.message || "文件上传成功");
            $("#info-formSF").modal('hide');
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

function recalculat(id, storeid){
    layer.confirm("计算可能需要一定时间，是否确定重新计算？", {
        btn: ['确定','取消'] //按钮
    }, function(){
        layer.closeAll('dialog');
        MyFun.to.s("正在重新计算，请稍后再查看");

        $.ajax({
            url: "${request.contextPath}/fxdrugtype/recalculate.do",
            type: "POST",
            data : "salefile="+id+"&storeid="+storeid +"&_=" + (new Date()).getTime(),
            cache: false,
            dataType: "json",
            timeout: 300000,
            beforeSend: function (xhr) {
                xhr.overrideMimeType("text/plain; charset=utf-8");
            },
            success: function(json) {
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "重新预计算成功");
                }else{
                    MyFun.to.e(json.message || "重新预计算失败");
                }
            },
            error: function(e) {
                if(e.statusText == 'timeout'){
                    
                }else if(e.status == 500){
                    MyFun.to.e("服务器错误");
                }else{
                    
                }
            },
            complete:function(XMLHttpRequest,status){ 
                
            }
        });
    })
}
</script>
</body>
</html>