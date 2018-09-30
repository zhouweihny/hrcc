<#include "../tools/select.ftl" />
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
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<style>
.J_gobackwrap {
    position: relative;
}

.J_gobackwrap a {
    margin: 10px;
    border: 1px solid #ddd;
    width: 100px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    display: block;
}
.J_gobackwrap h4 {
    border: 1px solid #ddd;
    padding: 12px 10px 11px;
    margin: 0;
    position: absolute;
    top: 0;
    left: 109px;
    cursor: default;
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
div.dataTables_info {
    padding-top: 5px;
    margin-left: 15px;
    display: inline-block;
}
.wrapper .table.dataTables-example.dataTable {
    width: 2000px;
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
</style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="J_gobackwrap">
        <a href="javascript:;" hidefocus="true" onclick="history.back();">
            <i class="fa fa-chevron-left" aria-hidden="true"></i>
            <span>返回上一级</span>
        </a>
        <h4><@_fximpfilename id=salefileid>${fxImpfilename.name!""}</@_fximpfilename> - 库存</h4>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-4">
                            <label for="J_name">品种名：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入品种名" name="name">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="getzPage();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div id="FxImpsaledataListWrap" class="m-t">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>编码</th>
                                    <th>品名</th>
                                    <th>规格</th>
                                    <th>单位</th>
                                    <th>剂型</th>
                                    <th>厂商</th>
                                    <th>国药准字</th>
                                    <th>数量</th>
                                    <th>成本单价</th>
                                    <th>金额</th>
                                    <th>创建日期</th>
                                    <th>业务日期</th>
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

<script>
var zuiTbl = null,
    curSaleDataId = '${salefileid!""}';

$(function() {
    getzPage();
})

function getzPage(){
    if(zuiTbl){
        zuiTbl.draw();
    }else{
        zuiTbl = $('#OrderList').DataTable( {
            language: {
                sProcessing: "正在努力加载数据..."
            },
            dom: '<"top">rt<"bottom"flpi><"clear">',
            ordering:false,//关闭表格的排序功能
            serverSide: true,  //启用服务器端分页
            processing: true,  //隐藏加载提示,自行处理
            searching: false, // 禁用搜索
            ajax: function (data, callback, settings) {
                var formData = $("#J_formSearch").formSerialize();
                formData += "&salefileid="+curSaleDataId+'&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

                //ajax请求数据
                $.ajax({
                    type: "post",
                    url: "${request.contextPath}/fximpstore/details.do",
                    cache: false,  //禁用缓存
                    data: formData,  //传入组装的参数
                    dataType: "json",
                    success: function (result) {
                        if(result.code == '0000'){
                            //封装返回数据
                            var returnData = {};
                            returnData.draw = data.draw;
                            if(result.data && result.data.totalRows){
                                returnData.recordsTotal = result.data.totalRows;//返回数据全部记录
                                returnData.recordsFiltered = result.data.totalRows;
                                returnData.data = result.data.data;//返回的数据列表
                            }else{
                                returnData.recordsTotal = 0;
                                returnData.recordsFiltered = 0;
                                returnData.data = [];
                            }
                            //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                            //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                            callback(returnData);
                        }else{
                            MyFun.to.e(result.message || "获取库存详情失败");
                            var returnData = {};
                            returnData.draw = data.draw;
                            returnData.recordsTotal = 0;
                            returnData.recordsFiltered = 0;
                            returnData.data = [];
                            callback(returnData);
                        }

                    }
                });
            },
            columns: [
                { "data": "code" },
                { "data": "name" },
                { "data": "specifications" },
                { "data": "unit" },
                { "data": "dosageform" },
                { "data": "factory" },
                { "data": "zunzi" },
                { "data": "qty" },
                { "data": "costprice" },
                { "data": "amt" },
                { "data": "createtime" },
                { "data": "updatetime" }
            ],
            columnDefs: [
                
            ],
            drawCallback: function(settings){
                //增加跳转到某页
                if(!$("#OrderList_paginate_jump").length){
                    var _strArr = [
                        '<div id="OrderList_paginate_jump" class="OrderList_paginate_jump">',
                        '<span>到第</span>',
                        '<input class="input-txt" maxlength="4" value="">',
                        '<span>页</span>',
                        '<a class="btn btn-white">确定</a>',
                        '</div>'
                    ]
                    $("#OrderList_paginate").before(_strArr.join(""));
                    $("#OrderList_paginate_jump").off("click").on("click", ".btn", function(){
                        var _val = $(this).siblings(".input-txt").val();
                        gotoPage('1', _val);
                        return false;
                    }).off("keyup").on("keyup", ".input-txt", function(event){
                        this.value=this.value.replace(/[^0-9]/g,'');
                        if(event.keyCode==13){
                            var _val = $(this).val();
                            gotoPage('1', _val);
                            return false;
                        }
                    })
                }

                if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){

                }else{
                    var _w = $(window).width() - $(".zui-navbar").width() - 45;
                    $("#FxImpsaledataListWrap").css({"width": _w, "paddingBottom": "30px"}).niceScroll();
                }
            }
        });
    }
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