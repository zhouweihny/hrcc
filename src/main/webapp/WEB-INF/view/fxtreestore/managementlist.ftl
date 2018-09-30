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
<link href="${request.contextPath}/js/plugins/ztree/css/metroStyle/metroStyle.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<link href="${request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet"> 
<script src="${request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>

<style>
#zuiTree {
    border: 1px solid #ddd;
    width: 100%;
    height: 400px;
    overflow: auto;
}
#supplierid {
    display: inline-block;
}
.select2-container {
    margin-right: 10px;
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
.J_showdetail {
    cursor: pointer;
}
.zChildTr {
    display: none;
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
    padding: 10px;
    border: 1px solid #e7e7e7;
    border-bottom-color: #ddd;
    margin: 0 0 20px;
}
.J_formSearch_D label {
    display: inline-block;
    vertical-align: middle;
    width: 70px;
    margin-bottom: 0;
}
.J_formSearch_D .form-control {
    vertical-align: middle;
}
.select2-container {
    margin-bottom: 8px;
}
#J_formSearch label {
    vertical-align: 3px;
}
.saledate_wrap {
    position: relative;
}
.saledate_wrap input {
    cursor: pointer;
}
.saledate_wrap i {
    position: absolute; bottom: 9px; left: 236px; top: auto; cursor: pointer;
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
.fn-text-right {
    text-align: right;
}
.J_charts {
    width: 100%;
    height: 260px;
    border: 1px solid #ddd;
    padding: 5px 10px;
    margin-bottom: 15px;
}
#J_top_tbl {
    margin: 0!important;
    height: 260px;
}
.J_attribute p:after {
    visibility:hidden;
    display:block;
    font-size:0;
    content:" ";
    clear:both;
    height:0;
}
.J_attribute p {
    zoom:1; /* for IE6 IE7 */
    width: 140px;
}
.J_attribute p .fa {
    font-size: 12px;
    color: #848282;
    float: left;
    padding-top: 2px;
    width: 16px;
}
.J_attribute p span {
    float: left;
    width: 124px;
}
.J_markerWrap {
    width: 129px;
    margin-bottom: 0;
}
.J_markerWrap .J_marker {
    font-size: 12px;
    font-style: normal;
    background: #ce9654;
    color: #fff;
    padding: 4px 5px 3px 5px;
    border-radius: 3px;
    display: inline-block;
    margin-right: 4px;
    margin-top: 5px;
    cursor: default;
}
.J_markerWrap .J_marker_2 {
    background: #DB562D;
}
.J_markerWrap .J_marker_3 {
    background: #bd6dcc;
}
.J_markerWrap .J_marker_4 {
    background: #66B281;
}
.J_markerWrap .J_marker_5 {
    background: #027194;
}
.J_markerWrap .J_marker_6 {
    background: #7496F6;
}
.J_markerWrap .J_marker_6.J_marker_6_1 {
    background: #f574c1;
}
.J_markerWrap .J_marker_6.J_marker_6_2 {
    background: #3b443b;
}
.J_markerWrap .J_marker_7 {
    background: #d01189;
}
.J_markerWrap .J_marker_8 {
    background: #7b5454;
}
.J_markerWrap .J_marker_9 {
    background: #d23118;
}
.J_name {
    margin-bottom: 0;
}
#OrderList_filter {
    display: none;
}
#OrderList_filter label {
    float: left;
    max-width: 100%;
    font-weight: 700;
    vertical-align: 0;
    margin-bottom: 0;
    padding: 8px 14.5px 5px 0;
}
#OrderList_filter label input {
    font-weight: normal;
}
.J_handlerWrap .i-checks {
    display: inline-block;
    margin-bottom: 0;
}
.J_handlerWrap .i-checks label {
    padding-left: 0;
    padding-right: 20px;
}
.J_handlerWrap .i-checks label .icheckbox_square-green {
    margin-right: 3px;
}
#zuiTbl_wrap {
    overflow: auto; 
    -webkit-overflow-scrolling: touch;
}
.J_handlerWrap {
    position: relative;
}
.J_handlerWrap .J_maxScreenBtn {
    position: absolute;
    right: 10px;
    top: 10px;
    color: #676a6c;
}
.J_handlerWrap .J_maxScreenBtn.max {
    color: #DB562D;
}
.J_handlerWrap .J_maxScreenBtn .fa-arrows-alt {
    font-size: 20px;
}
.J_maxContainer {
    position: fixed;
    left: 10px;
    right: 10px;
    height: 100%;
    width: auto;
    top: 0;
    background: #fff;
    z-index: 9999;
    overflow: auto;
    -webkit-overflow-scrolling: touch;
}
.zui_iChecks {
    position: relative;
    display: inline-block;
    z-index: 999;
    margin-right: 20px;
}
.zui_iChecks .zcheck {
    height: auto;
    background: #fff;
    z-index: 99;
}
.zui_iChecks .zcheck label {
    font-weight: normal;
    cursor: pointer;
    display: block;
}
.zui_iChecks .zcheck label .zcheckicon {
    display: inline-block;
    vertical-align: middle;
    margin: 0 3px 0 0;
    padding: 0;
    width: 22px;
    height: 22px;
    background: url(${request.contextPath}/css/plugins/iCheck/green.png) 0 0 no-repeat;
    border: none;
    cursor: pointer;
}
.zui_iChecks .zcheck label:hover .zcheckicon {
    background-position: -24px 0;
}
.zui_iChecks .zcheck label.checked .zcheckicon {
    background-position: -48px 0;
}
.zui_iChecks .checkwrap {
    position: absolute;
    border: 1px solid #ddd;
    padding-left: 10px;
    left: -10px;
    padding-top: 10px;
    background: #fff;
    width: 124px;
}
.zui_iChecks .checkwrap .zoption {
    
}
.zui_iChecks .desLabel label .fa {
    margin-left: 3px;
    vertical-align: 2px;
}
.J_storeFileSplit {
    vertical-align: 2px;
    margin: 0 5px 0 -2px;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>经营品种</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearchS" action="javascript:;">
                        <input value="" type="hidden" name="startdate" id="startdate" />
                        <input value="" type="hidden" name="enddate" id="enddate" />
                        <input value="" type="hidden" name="storeid" id="storeid" />
                        <div class="form-group col-md-3">
                            <label for="zuiStoreId">门店选择：</label>
                            <select id="zuiStoreId" class="form-control"></select>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_startdate">销售日期：</label>
                            <select class="form-control J_zuiStoreDate" id="J_startdate"></select>
                            <em class="J_storeFileSplit">至</em>
                            <select class="form-control J_zuiStoreDate" id="J_enddate"></select>
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-w-m btn-info" onclick="getzTree();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="row m-t">
                        <div class="col-md-3">
                            <ul id="zuiTree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <div id="J_maxContainer" class="">
                                <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                                    <div class="form-group col-md-4">
                                        <label for="drugnameS" style="vertical-align: 0;">药品：</label>
                                        <input class="form-control" value="" type="text" name="name" id="drugname" placeholder="请输入药品名称" style="width: 152px;"/>
                                    </div>
                                    <div class="form-group col-md-4" style="display:none;">
                                        <label for="status">状态：</label>
                                        <select id="status" name="status" class="form-control" style="width: 152px;">
                                            <option value="1" selected="selected">已选择</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <button type="button" class="btn btn-w-m btn-primary" onclick="getDetail('1');"><i class="fa fa-filter" style="display:inline-block;margin-right:3px;"></i>筛 选</button>
                                    </div>
                                </form>
                                <div class="J_handlerWrap">
                                    <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="推荐" />推荐<i></i></label>
                                    </div>
                                    <!-- <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="广告" />广告<i></i></label>
                                    </div> -->
                                    <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="品牌" />品牌<i></i></label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="高毛利" />高毛利<i></i></label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="必备" />必备<i></i></label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="医保" />医保<i></i></label>
                                    </div>
                                    <!-- <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="院外" />院外<i></i></label>
                                    </div> -->
                                    <div class="checkbox i-checks">
                                        <label><input type="checkbox" name="zuiFilter" value="主推" />主推<i></i></label>
                                    </div>

                                    <div class="zui_iChecks" id="zui_iChecks">
                                        <div class="zcheck desLabel">
                                            <label><i class="zcheckicon"></i><span>自有</span><i class="fa fa-sort-desc" aria-hidden="true"></i></label>
                                        </div>
                                        <div class="checkwrap fn-hide">
                                            <div class="zcheck zoption">
                                                <label data-des="zuiziyou"><i class="zcheckicon"></i><span>自有</span></label>
                                            </div>
                                            <div class="zcheck zoption">
                                                <label data-des="自20"><i class="zcheckicon"></i><span>自20</span></label>
                                            </div>
                                            <div class="zcheck zoption">
                                                <label data-des="自80"><i class="zcheckicon"></i><span>自80</span></label>
                                            </div>
                                            <div class="zcheck zoption">
                                                <label data-des="(自20|zuigys)"><i class="zcheckicon"></i><span>自20+供应商</span></label>
                                            </div>
                                            <div class="zcheck zoption">
                                                <label data-des="(自80|zuigys)"><i class="zcheckicon"></i><span>自80+供应商</span></label>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- <div class="checkbox i-checks" data-type="2">
                                        <label><input type="checkbox" name="zuiFilter" value="" id="J_regFilter"/>不包含筛选<i></i></label>
                                    </div> -->

                                    <a href="javascript:;" id="J_maxScreenBtn" class="J_maxScreenBtn" title="最大化"><i class="fa fa-arrows-alt" aria-hidden="true"></i></a>
                                </div>
                                <div id="zuiTbl_wrap">
                                    <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                                        <thead>
                                            <tr role="row">
                                                <th>编码</th>
                                                <th style="padding: 4px;">名称&nbsp;|&nbsp;标签</th>
                                                <th>通用名</th>
                                                <th>规格&nbsp;|&nbsp;厂商</th>
                                                <th>供应商</th>
                                                <th>成本价</th>
                                                <th>建议零售价</th>
                                                <th>毛利率</th>
                                                <th>销售额</th>
                                                <th>销售数量</th>
                                                <th>毛利</th>
                                                <th>销售毛利率</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody id="J_tbl_body"></tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<textarea class="fn-hide" id="T_tbl_body">
{{#each this}}
<tr>
    <td>{{code}}</td>
    <td>
        <p class="J_name">{{name}}</p>
        {{retMarker ggpz pppz gmlpz bbpz ybpz tjpz zypz ywpz ztpz is80 supplier}}
    </td>
    <td>{{common}}</td>
    <td class="J_attribute">
        <p><i class="fa fa-dot-circle-o" aria-hidden="true"></i><span>{{specifications}}</span></p>
        <p><i class="fa fa-dot-circle-o" aria-hidden="true"></i><span>{{factory}}</span></p>
    </td>
    <td>{{supplier}}</td>
    <td class="fn-text-right">{{retFix costprice}}</td>
    <td class="fn-text-right">{{retFix price}}</td>
    <td class="fn-text-right">{{retFixS mlv}}</td>
    <td class="fn-text-right">{{retFix xse}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix ml}}</td>
    <td class="fn-text-right">{{retFixS xsmlv}}</td>
    <td class="fn-text-right">
        {{retNewHandle id checked zypz}}
    </td>
</tr>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_top_tbl">
<tr>
    <td>销售额</td>
    <td class="fn-text-right">{{retFix this.0.xse}}</td>
    <td class="fn-text-right">{{retFix this.1.xse}}</td>
</tr>
<tr>
    <td>毛利</td>
    <td class="fn-text-right">{{retFix this.0.ml}}</td>
    <td class="fn-text-right">{{retFix this.1.ml}}</td>
</tr>
<tr>
    <td>毛利率</td>
    <td class="fn-text-right">{{retFixC this.0.ml this.0.xse}}</td>
    <td class="fn-text-right">{{retFixC this.1.ml this.1.xse}}</td>
</tr>
<tr>
<td>动销品种数</td>
    <td class="fn-text-right">{{ this.0.num}}</td>
    <td class="fn-text-right">{{ this.1.num}}</td>
</tr>
<tr>
    <td>销售占比</td>
    <td class="fn-text-right" colspan="2">{{retFixC this.0.xse this.1.xse}}</td>
</tr>
<tr>
    <td>诊断结果</td>
    <td class="fn-text-right" colspan="2">{{this.0.result}}</td>
</tr>
</textarea>

<textarea class="fn-hide" id="T_zuiStoreId">
<option value=""></option>
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>
<textarea class="fn-hide" id="T_zuiStoreDate">
{{#each this}}
<option value="{{this}}">{{this}}</option>
{{/each}}
</textarea>

<script>

var zuiTree = null,
    clickFlag = 0,
    G_json = null,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    sortdataS = 'stype=&sfield=',
    curTreeId = curStoreId = '',
    startdate = enddate = '',
    p_getStoreId = '',
    p_getFileId = '',
    curSaleTreeName = '',
    zuiTbl = null,
    J_charts_1 = null,
    ajaxCount = 0,
    zui_res = null,
    cur4TreeId = '',
    zuiCheckData = '',
    curFilterType = 0;

var setting = {
    view: {
        selectedMulti: false
    },
    edit: {
        enable: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: zTreeClick
    }
};

var NiceTools = {
    /*
     * 功能:删除数组元素.
     * 参数:dx删除元素的下标.
     * 返回:在原数组上删除后的数组
     */
    removeByIndex : function(arrays , dx){
        if(isNaN(dx)||dx>arrays.length){return false;}
        for(var i=0,n=0;i<arrays.length;i++)
        {
            if(arrays[i]!=arrays[dx])
            {
                arrays[n++]=arrays[i]
            }
        }
        arrays.length-=1
        return arrays;
    },
    //删除指定的item,根据数组中的值
    removeByValue : function(arrays, item ){
        for( var i = 0 ; i < arrays.length ; i++ ){
            if( item == arrays[i] )
            {
                break;
            }
        }
        if( i == arrays.length ){
            return;
        }
        for( var j = i ; j < arrays.length - 1 ; j++ ){
            arrays[ j ] = arrays[ j + 1 ];
        }
        arrays.length--;
        return arrays;
    }
}

;(function($){
    $.fn.autoHide = function(){
        var ele = $(this);
        $(document).on("click", function(e){
            if(ele.is(":visible") && (!$(e.target)[0].isEqualNode(ele[0]) && ele.has(e.target).length === 0)){
                ele.addClass("fn-hide");
            }
            e.stopPropagation();
            return false;
        })
        return this;
    }
})(jQuery);

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        getZuiStore();
    }
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
                    getStoreFileDate();
                })

                getStoreFileDate();
            }else{
                MyFun.to.e(json.message || "查询药店列表失败");
            }
        }
        unBlock(".wrapper");
    })
}

function getStoreFileDate(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxsalemonth/monthsbystoreid.do", "storeid="+p_getStoreId, function(state, json){
        if(state == 'success'){
            if(json){
                $(".J_zuiStoreDate").temp($("#T_zuiStoreDate").val(), json.data);
                var len = json.data.length;
                if(len){
                    var _index = (len<2) ? 0 : (len<3) ? 1 : 2;

                    $("#J_startdate").find("option:eq("+_index+")").attr({"selected":"selected"});
                    $("#J_enddate").find("option:eq(0)").attr({"selected":"selected"});
                    startdate = $("#J_startdate").val();
                    enddate = $("#J_enddate").val();
                    $("#startdate").val(startdate);
                    $("#enddate").val(enddate);

                    $(".J_zuiStoreDate").select2({
                        placeholder: "请选择销售日期",
                        allowClear: false,
                        language: "zh-CN",
                        dropdownAutoWidth: false
                    });

                    $("#J_startdate").on("select2:select", function(e){
                        startdate = $("#J_startdate").val();
                        $("#startdate").val(startdate);
                        judgeFileDate();
                    })
                    $("#J_enddate").on("select2:select", function(e){
                        enddate = $("#J_enddate").val();
                        $("#enddate").val(enddate);
                    })
                    judgeFileDate();
                }else{
                    MyFun.to.e(json.message || "暂无药店销售数据");
                    startdate = "";
                    enddate = "";
                    $("#startdate").val("");
                    $("#enddate").val("");
                    $("#J_startdate").val(null).trigger("change");
                    $("#J_enddate").val(null).trigger("change");
                }
            }else{
                MyFun.to.e(json.message || "查询药店销售日期失败");
            }
        }
        unBlock(".wrapper");

        setTimeout(function(){
            initZuiStorePage();
        }, 300)
    })
}

function judgeFileDate(){
    var s1 = startdate,
        s2 = enddate,
        obj2 = $("#J_enddate"),
        opt2 = obj2.find("option");
    s1 = zdate2Int(s1);
    s2 = zdate2Int(s2);

    if(s2 < s1){
        obj2.val(startdate).trigger("change");
        enddate = startdate;
        $("#enddate").val(enddate);
    }

    //选择开始日期，重置结束日期
    opt2.each(function(i){
        var _this = $(this),
            _val = _this.val();
        _val = zdate2Int(_val);
        if(_val < s1){
            _this.attr({"disabled": "disabled"});
        }else{
            _this.removeAttr("disabled");
        }
    })
    obj2.select2();
}
// 2018-03 --- 201803
function zdate2Int(date){
    return parseInt(date.split("-").join(""), 10);
}

function initZuiStorePage(){
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green'
    });

    $('.i-checks').on('ifChecked', function(event){
        var _type = $(this).data("type");
        if(_type && _type == '2')
            curFilterType = 1;
        showFilter();
    });
    $('.i-checks').on('ifUnchecked', function(event){
        var _type = $(this).data("type");
        if(_type && _type == '2')
            curFilterType = 0;
        showFilter();
    });

    var _zui_iChecks = $("#zui_iChecks"),
        _checkwrap = _zui_iChecks.find(".checkwrap"),
        _desLabel = _zui_iChecks.find(".desLabel"),
        _lable = _checkwrap.find("label");
    _desLabel.on("click", function(e){
        stopEvent(e);
        _checkwrap.toggleClass("fn-hide").autoHide();
        return false;
    })
    _lable.on("click", function(e){
        stopEvent(e);
        var _this = $(this),
            cdata = _this.data("des");
        if(_this.hasClass("checked")){
            zuiCheckData = "";
            _desLabel.find("label").removeClass("checked");
            _this.removeClass("checked");
        }else{
            //单选
            _lable.removeClass("checked");

            zuiCheckData = cdata;
            _this.addClass("checked");
            _desLabel.find("label").addClass("checked");
        }
        showFilter();
    })

    setTimeout(function(){
        getzTree();
    }, 300)

    $("#J_maxScreenBtn").on("click", function(){
        var _J_maxContainer = $("#J_maxContainer"),
            _this = $(this);
        if(_this.hasClass("max")){
            _this.removeClass("max").attr("title", "最大化");
            _J_maxContainer.removeClass("J_maxContainer");
            $("body").css({"overflow-y":"auto"});
        }else{
            _this.addClass("max").attr("title", "最小化")
            _J_maxContainer.addClass("J_maxContainer");
            $("body").css({"overflow-y":"hidden"});
        }
    })
}

function showFilter(){
    var arr = [];
    $('input[name="zuiFilter"]:checked').not("#J_regFilter").each(function() {  
        arr.push($(this).val());
    });
    if(zuiCheckData)
        arr.push(zuiCheckData);

    if(curFilterType){
        //不包含筛选
        if(arr.length)
            $("#OrderList_filter input").val('^((?!'+arr.join("|")+').)*$').keyup();
        else
            $("#OrderList_filter input").val('').keyup();
    }else{
        $("#OrderList_filter input").val(arr.join(" ")).keyup();
    }
}

function getzTree(){
    var wh = window.parent.W_p_getWindowWh(),
        _w = parseFloat(wh.w * 0.71, 10).toFixed(2),
        _h = parseFloat(wh.h * 0.8, 10).toFixed(2);
    $("#zuiTree").css({"height": _h});

    $("#OrderList_wrapper").html("");

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtreestore/customtree.do", "storeid="+p_getStoreId+"&startdate="+startdate+"&enddate="+enddate, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                var arr = [];
                if(json.code == '0000'){
                    for (var i=0, l=json.data.length; i<l; i++) {
                        var attr = {};
                        attr.id = json.data[i].id;
                        attr.pId = json.data[i].parentid;
                        attr.name = json.data[i].name;
                        arr.push(attr);
                    }
                }

                $.fn.zTree.init($("#zuiTree"), setting, arr);
                zuiTree = $.fn.zTree.getZTreeObj("zuiTree");

                var firstNode = zuiTree.getNodes()[0];
                zuiTree.expandNode(firstNode);

            }else{
                MyFun.to.e(json.message || "查询自定义经营目录失败");
            }
        }
        unBlock(".wrapper");
    })
}

function zTreeClick(event, treeId, treeNode) {
    if(treeNode.level == 3){
        //点击通用名
        cur4TreeId = treeNode.id;
        var p_node = treeNode.getParentNode();
        curTreeId = p_node.id;
        curSaleTreeName = p_node.name;
    }else{
        curTreeId = treeNode.id;
        curSaleTreeName = treeNode.name;
        cur4TreeId = '';
    }

    $("#J_treeNodeId").val(treeNode.id);
    curStoreId = $("#supplierid").val() || '';

    getDetail();
}

function getDetail(type){
    // $('.i-checks').iCheck('uncheck');
    if(!curTreeId){
        MyFun.to.i("请选择树节点");
        return false;
    }

    createBlock(".wrapper", '正在努力加载数据...');
    var formData = $("#J_formSearch").formSerialize();
    formData += "&startdate="+startdate+"&enddate="+enddate+'&treeid='+curTreeId+'&storeid='+p_getStoreId;
    if(cur4TreeId)
        formData += "&comnameid="+cur4TreeId;
    $.PostJson("${request.contextPath}/fxtreestore/customdrug.do", formData, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(zuiTbl){
                    zuiTbl.destroy();
                    zuiTbl = null;
                }

                zui_res = json.data.result || '';

                if(json.data.data && json.data.data.length){
                    /*var tempArr = [],
                        tempArr2 = [];
                    for(var i=0,len=json.data.data.length; i<len; i++){
                        if(json.data.data[i].tjpz)
                            tempArr.push(json.data.data[i]);
                        else
                            tempArr2.push(json.data.data[i]);
                    }
                    tempArr = tempArr.concat(tempArr2);*/

                    $("#J_tbl_body").temp($("#T_tbl_body").val(), json.data.data);

                    zuiTbl = $('#OrderList').DataTable({
                        language: {
                            sProcessing: "正在努力加载数据...",
                            sSearch: "筛选："
                        },
                        dom: '<"top">frt<"bottom">lpi<"clear">',
                        ordering:true, //打开表格的排序功能
                        order: [], //默认不排序
                        // order: [[ 1, "desc" ]], //初始化以第二列排序
                        serverSide: false,  //启用服务器端分页
                        processing: true,  //隐藏加载提示,自行处理
                        searching: true, // 开启搜索
                        search: {
                            smart: true,
                            regex: true
                        },
                        columnDefs: [
                            {"orderable": false, "targets": 3},
                            {"orderable": false, "targets": 5},
                            {"orderable": false, "targets": 12}
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
                        }
                    });
                }else{
                    $("#J_tbl_body").html('<tr><td colspan="13" style="text-align: center;">暂无数据</td></tr>');
                }

            }else{
                if(zuiTbl){
                    zuiTbl.destroy();
                    zuiTbl = null;
                }
                $("#J_tbl_body").html('<tr><td colspan="13" style="text-align: center;">暂无数据</td></tr>');
                MyFun.to.e(json.message || "查询自定义经营目录失败");
            }
        }
        showFilter();
        unBlock(".wrapper");
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

Handlebars.registerHelper('retFix', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = _val.toFixed(2);

    var intSum = _val.substring(0, _val.indexOf(".")).replace( /\B(?=(?:\d{3})+$)/g, ',' );//取到整数部分
    var dot = _val.substring(_val.length, _val.indexOf("."))//取到小数部分搜索

    return intSum + dot;
});

Handlebars.registerHelper('retFixS', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = _val.toFixed(2);
    return _val+'%';
});

Handlebars.registerHelper('retFixC', function(value, value2){
    var _val = value+'',
        _val2 = value2+'';
    if(!_val || _val == 'null' || _val == 'undefined' || !_val2 || _val2 == 'null' || _val2 == 'undefined'){
        return '';
    }
    
    var val = zuiaccDiv(_val, _val2);
    val = zuiaccMul(val, 100);
    val = val.toFixed(2);
    return val+'%';
});

function zuiaccMul(arg1,arg2) {  
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length}catch(e){}  
    try{m+=s2.split(".")[1].length}catch(e){}  
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);  
}  

function zuiaccDiv(arg1,arg2){  
    var t1=0,t2=0,r1,r2;  
    try{t1=arg1.toString().split(".")[1].length}catch(e){}  
    try{t2=arg2.toString().split(".")[1].length}catch(e){}  
    with(Math){  
        r1=Number(arg1.toString().replace(".",""));  
        r2=Number(arg2.toString().replace(".",""));  
        return (r1/r2)*pow(10,t2-t1);  
    }  
}

function checkDrug(drugid, obj){
    if(!drugid){
        MyFun.to.i("数据错误，请选择其他数据");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;

    var type = $(obj).data("type"),
        data = "storeid="+p_getStoreId+"&drugid="+drugid,
        url = '${request.contextPath}/fxdrugstore/save.do',
        resDes = '',
        resDesOppo = '',
        resType = 0;

    if(type == 1){
        url = '${request.contextPath}/fxdrugstore/delete.do';
        resDes = '淘汰';
        resDesOppo = '选择';
        resType = 2;
        data += '&eliminate=true';

    }else if(type == 2){
        resDes = '选择';
        resDesOppo = '淘汰';
        resType = 1;
        data += '&eliminate=false';

    }else if(type == 3){
        url = '${request.contextPath}/fxdrugstore/delete.do';
        resDes = '取消选择';
        resDesOppo = '选择';
        resType = 4;

    }else if(type == 4){
        resDes = '选择';
        resDesOppo = '取消选择';
        resType = 3;

    }

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson(url, data, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || resDes+"药品成功");
                // zuiTbl.draw(false);

                $(obj).data("type", resType).text(resDesOppo);
            }else{
                MyFun.to.e(json.message || resDes+"药品失败");
            }
        }
        clickFlag = 0;
        unBlock(".wrapper");
    })
}

Handlebars.registerHelper('retMarker', function(ggpz, pppz, gmlpz, bbpz, ybpz, tjpz, zypz, ywpz, ztpz, isEight, supplier){
    // var ggpz = pppz = gmlpz = bbpz = ybpz = tjpz = zypz = ywpz = ztpz = '1';
    // isEight = true;
    if(!(ggpz || pppz || gmlpz || bbpz || ybpz || tjpz || zypz || ywpz || ztpz)){
        return '';
    }
    var arr = ['<p class="J_markerWrap">'];
    if(tjpz)
        arr.push('<i class="J_marker J_marker_5">推荐</i>');
    // if(ggpz)
    //    arr.push('<i class="J_marker J_marker_1">广告</i>');
    if(pppz)
        arr.push('<i class="J_marker J_marker_2">品牌</i>');
    if(gmlpz)
        arr.push('<i class="J_marker J_marker_3">高毛利</i>');
    if(ybpz)
        arr.push('<i class="J_marker J_marker_4">医保</i>');
    if(zypz){
        var _val = isEight+'';
        if(_val == 'null'){
            arr.push('<i class="J_marker J_marker_6">自有<s style="display:none;">zuiziyou</s></i>');
        }else if(_val == 'false'){
            arr.push('<i class="J_marker J_marker_6 J_marker_6_2">自20<s style="display:none;">zuiziyou</s></i>');
        }else{
            arr.push('<i class="J_marker J_marker_6 J_marker_6_1">自80<s style="display:none;">zuiziyou</s></i>');
        }
    }
    if(bbpz)
        arr.push('<i class="J_marker J_marker_7">必备</i>');
    // if(ywpz)
    //    arr.push('<i class="J_marker J_marker_8">院外</i>');
    if(ztpz)
        arr.push('<i class="J_marker J_marker_9">主推</i>');
    if(supplier && supplier !== '连锁总部')
        arr.push('<i class="J_marker" style="display:none;">zuigys</i>');
    arr.push('</p>');
    return new Handlebars.SafeString(arr.join(''));
});

Handlebars.registerHelper('retNewHandle', function(id, checked, zypz){
    var buffer = '';
    if(zypz){
        if(checked)
            buffer = '<a href="javascript:;" class="J_check" data-type="1" onclick="checkDrug(\''+id+'\', this);">淘汰</a>';
        else
            buffer = '<a href="javascript:;" class="J_check" data-type="2" onclick="checkDrug(\''+id+'\', this);">选择</a>';
    }else{
        if(checked)
            buffer = '<a href="javascript:;" class="J_check" data-type="3" onclick="checkDrug(\''+id+'\', this);">取消选择</a>';
        else
            buffer = '<a href="javascript:;" class="J_check" data-type="4" onclick="checkDrug(\''+id+'\', this);">选择</a>';
    }
    
    return new Handlebars.SafeString(buffer);
});

</script>
</body>
</html>