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
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<link href="${request.contextPath}/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
<link href="${request.contextPath}/js/plugins/bootstrap-multiselect/bootstrap-multiselect.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>

<style>
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
/*.modal-dialog {
    width: 1100px!important;
}*/
.modal-open .modal .ibox-content {
    /*max-height: 550px;*/
}
.modal-dialog .ibox.float-e-margins {
    margin-bottom: 10px;
}

.J_blank {
    display: inline-block;
    vertical-align: middle;
    height: 10px;
    width: 20px;
}
.J_blank_1 {
    width: 45px;
}
.J_blank_2 {
    width: 70px;
}
.J_showLess {
    display: inline-block;
    margin-left: 2px;
    font-size: 16px;
    vertical-align: -1px;
    cursor: pointer;
    width: 20px;
    height: 20px;
    text-align: center;
    line-height: 20px;
}
.fn-text-right {
    text-align: right;
}
.fn-hideS {
    display: none!important;
}
.select2-container {
    margin-bottom: 0;
}
#zuiTree {
    border: 1px solid #23b7e5;
    width: 272px;
    height: 260px;
    overflow: auto;
    background: #fff;
    z-index: 999;
    margin-top: -1px;
}
#spaceidS {
    cursor: pointer;
}
#menuContent {
    z-index: 99999;
}
.multiselect-native-select .dropdown-toggle {
    text-align: left;
}
.multiselect-native-select .btn-group,
.multiselect-native-select .dropdown-toggle,
.multiselect-native-select .multiselect-container {
    width: 100%;
}
.multiselect-native-select .multiselect-container label.checkbox {
    display: inline-block;
    width: 100%;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>销售分析</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                        <input value="" type="hidden" name="startdate" id="startdate" />
                        <input value="" type="hidden" name="enddate" id="enddate" />
                        <input value="" type="hidden" name="storeid" id="storeid" />
                        <div class="form-group col-md-3">
                            <label for="zuiStoreId">门店选择：</label>
                            <select id="zuiStoreId" class="form-control"></select>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="J_startdate">销售日期：</label>
                            <select class="form-control J_zuiStoreDate" id="J_startdate"></select>
                            <em class="J_storeFileSplit">至</em>
                            <select class="form-control J_zuiStoreDate" id="J_enddate"></select>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="spaceid">分类：</label>
                            <select id="J_type" name="type" style="width: 172px">
                                <option value="">请选择</option>
                                <option value="1">品种</option>
                                <option value="2">通用名</option>
                                <option value="3" selected="selected">品类树</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="searDatas();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div id="DrugList" data-url="${request.contextPath}/fxsaleanalysis/table.do" class="fn-hide"></div>

                    <div id="OrderListWrap">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>名称</th>
                                    <th class="J_sortBtn sorting" data-stype="desc" data-sfield="pgs">品规数</th>
                                    <th class="J_sortBtn sorting_desc" data-stype="asc" data-sfield="xse">销售额</th>
                                    <th class="J_sortBtn sorting" data-stype="desc" data-sfield="xszb">销售占比</th>
                                    <th class="J_sortBtn sorting" data-stype="desc" data-sfield="xssl">销售数量</th>
                                    <th class="J_sortBtn sorting" data-stype="desc" data-sfield="pjsj">平均售价</th>
                                    <th class="J_sortBtn sorting" data-stype="desc" data-sfield="ml">毛利</th>
                                    <th class="J_sortBtn sorting" data-stype="desc" data-sfield="mlv">毛利率</th>
                                    <th class="fn-text-right">操作</th>
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

<div id="info-form" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>新增培训<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container form-horizontal" id="J_formwrap">
                        
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveForm();">保存</button>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_formwrap">
<div class="form-group">
    <label class="col-sm-3 control-label">任务名：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" placeholder="输入任务名" name="taskname">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">培训师：</label>
    <div class="col-sm-8">
        <select id="J_pxname" class="form-control"></select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">店员：</label>
    <div class="col-sm-8">
        <select id="J_dyname" class="form-control" multiple="multiple"></select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">开始时间：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" name="startdate" id="startdateS" readonly placeholder="请输入开始时间">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">结束时间：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" name="enddate" id="enddateS" readonly placeholder="请输入结束时间">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">备注：</label>
    <div class="col-sm-8">
        <input type="text" name="remark" placeholder="请输入备注" class="form-control"  value=""> 
    </div>
</div>
</textarea>

<textarea class="fn-hide" id="T_OrderList">
{{#each this}}
<tr data-id="{{treeid}}" data-type="1">
    <td>
        {{name}}
        {{#if items}}<i class="fa fa-plus-square-o J_showLess" aria-hidden="true"></i>{{/if}}
    </td>
    <td class="fn-text-right">{{pgs}}</td>
    <td class="fn-text-right">{{retFixA xse}}</td>
    <td class="fn-text-right">{{retFixB xszb}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix pjsj}}</td>
    <td class="fn-text-right">{{retFixA ml}}</td>
    <td class="fn-text-right">{{retFixS mlv}}</td>
    <td class="fn-text-right"><a href="javascript:;" onclick="addNewPx('{{treeid}}');">培训任务</a></td>
</tr>

{{#if items}}
{{#each items}}
<tr class="J_sub_{{../treeid}} fn-hide" data-id="{{treeid}}" data-type="2">
    <td>
        {{retBlank 1}}
        {{name}}
        {{#if items}}<i class="fa fa-plus-square-o J_showLess" aria-hidden="true"></i>{{/if}}
    </td>
    <td class="fn-text-right">{{pgs}}</td>
    <td class="fn-text-right">{{retFixA xse}}</td>
    <td class="fn-text-right">{{retFixB xszb}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix pjsj}}</td>
    <td class="fn-text-right">{{retFixA ml}}</td>
    <td class="fn-text-right">{{retFixS mlv}}</td>
    <td class="fn-text-right"><a href="javascript:;" onclick="addNewPx('{{treeid}}');">培训任务</a></td>
</tr>

{{#if items}}
{{#each items}}
<tr class="J_sub_{{../../../treeid}} J_sub_{{../../treeid}} fn-hide" data-id="{{treeid}}" data-type="3">
    <td>
        {{retBlank 2}}
        {{name}}
        {{#if items}}<i class="fa fa-plus-square-o J_showLess" aria-hidden="true"></i>{{/if}}
    </td>
    <td class="fn-text-right">{{pgs}}</td>
    <td class="fn-text-right">{{retFixA xse}}</td>
    <td class="fn-text-right">{{retFixB xszb}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix pjsj}}</td>
    <td class="fn-text-right">{{retFixA ml}}</td>
    <td class="fn-text-right">{{retFixS mlv}}</td>
    <td class="fn-text-right"><a href="javascript:;" onclick="addNewPx('{{treeid}}');">培训任务</a></td>
</tr>

{{#if items}}
{{#each items}}
<tr class="J_sub_{{../../../../../treeid}} J_sub_{{../../../../treeid}} J_sub_{{../../treeid}} fn-hide" data-type="4">
    <td>{{retBlank 3}}{{name}}</td>
    <td class="fn-text-right">{{pgs}}</td>
    <td class="fn-text-right">{{retFixA xse}}</td>
    <td class="fn-text-right">{{retFixB xszb}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix pjsj}}</td>
    <td class="fn-text-right">{{retFixA ml}}</td>
    <td class="fn-text-right">{{retFixS mlv}}</td>
    <td class="fn-text-right"><a href="javascript:;" onclick="addNewPx('{{treeid}}');">培训任务</a></td>
</tr>
{{/each}}
{{/if}}

{{/each}}
{{/if}}

{{/each}}
{{/if}}

{{/each}}
</textarea>

<textarea class="fn-hide" id="T_zuiStoreId">
<option value=""></option>
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>
<textarea class="fn-hide" id="T_zuiStoreIdS">
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

MyFun.ajaxRefreshTable=function(divid,url,callback){
    if(!url)
        url= $('#'+divid).data("url");
    url= MyFun.urlFun(url,"_",new Date().getTime());
    var data = "{";
    var str =url.split("?")[1] ;   
    var strs = str.split("&");   
    for(var i = 0; i < strs.length; i ++) {   
        data += "'"+strs[i].split("=")[0]+"'" + ":"+"'"+strs[i].split("=")[1]+"'"+",";
    }
    data += "}";
    data = eval('('+data+')');

    $('#'+divid).empty();
    createBlock(".wrapper");
    $.ajax({
        type: "post",
        url: url.split("?")[0],
        dataType: "json",
        data:data,
        success: function(data){

        },
        complete:function(XMLHttpRequest,status){ 
            $('#'+divid).append(XMLHttpRequest.responseText);    
            $('#'+divid).data("url",url);
            if(typeof callback === 'function')
                callback();
            unBlock(".wrapper");
        }
    });
};

var clickFlag = 0,
    curTreeId = "",
    tempJson = {};

// 获取当日与当月1号
var curDate = new Date(),
    cdate = getCurDate(curDate),
    _m = ((curDate.getMonth()+1)<10) ? ("0"+(curDate.getMonth()+1)) : (curDate.getMonth()+1),
    firstdate = curDate.getFullYear() + "-"+ _m + "-01",
    dayx = new Date( curDate.getFullYear() , _m , 0 );
    lastdate =  curDate.getFullYear() + "-"+ _m + "-"+ dayx.getDate();

function refreshDrugList(){
    MyFun.ajaxRefreshTable("DrugList");
}

var indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    zuiTbl = null,
    p_getFileId = '',
    startdate = enddate = '',
    curType = '3',
    sortdata = 'stype=desc&sfield=xse';

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
    $("#J_type").select2({
        placeholder: "请选择分类",
        allowClear: false,
        language: "zh-CN",
        dropdownAutoWidth: false
    });

    $("#J_type").on("select2:select", function(e){
        var _val = $(this).val();
        curType = _val;
        if(curType == '2'){
            $("#J_name_wrap").removeClass("fn-hideS");
        }else{
            $("#J_name_wrap").addClass("fn-hideS")
            $("#J_name").val('');
        }
        searDatas();
    })

    $("#OrderList thead").on("click", "th.J_sortBtn", function(){
        var _this = $(this),
            sfield = _this.data("sfield"),
            stype = _this.data("stype");

        _this.siblings("th.J_sortBtn").attr({"class": "J_sortBtn sorting"}).data("stype", 'desc');
        _this.attr({"class": "J_sortBtn sorting_"+stype});
        sortdata = "stype="+stype+"&sfield="+sfield;

        _this.data("stype", stype == 'desc' ? 'asc' : 'desc');

        getzPage();
    })

    getzPage();
}

function searDatas(){
    if(curType == '3'){
        $("#DrugList").addClass("fn-hide");
        $("#OrderListWrap").removeClass("fn-hide");

        getzPage();
    }else{
        $("#DrugList").removeClass("fn-hide");
        $("#OrderListWrap").addClass("fn-hide");
        MyFun.search('J_formSearch','DrugList');
    }
}

function getzPage(){
    createBlock(".wrapper", '正在努力加载数据...');
    $("#J_tbl_body").html('');
    $.PostJson("${request.contextPath}/fxsaleanalysis/table.do", sortdata+"&startdate="+startdate+"&enddate="+enddate+"&type=3"+"&storeid="+p_getStoreId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                $("#J_tbl_body").temp($("#T_OrderList").val(), json.data);

                $(".J_showLess").off("click").on("click", function(){
                    var _this = $(this),
                        _tr = _this.parent().parent(),
                        _trId = _tr.data("id"),
                        _trType = _tr.data("type");
                    if(_this.hasClass("fa-minus-square-o")){
                        //收起
                        _this.removeClass("fa-minus-square-o").addClass("fa-plus-square-o");

                        $(".J_sub_"+_trId).each(function(){
                            var self = $(this);
                            self.find(".J_showLess").removeClass("fa-minus-square-o").addClass("fa-plus-square-o");
                        })
                        $(".J_sub_"+_trId).addClass("fn-hide");
                    }else{
                        _this.addClass("fa-minus-square-o").removeClass("fa-plus-square-o");

                        $(".J_sub_"+_trId).each(function(){
                            var self = $(this),
                                _class = self.attr("class"),
                                _type = self.data("type");

                            if(_trType+1 === _type ){
                                self.removeClass("fn-hide");
                            }
                        })
                    }
                })

            }else{
                MyFun.to.e(json.message || "查询品类树报表失败");
            }
        }
        unBlock(".wrapper");
    })
}

Handlebars.registerHelper('retBlank', function(value){
    var buffer = '';
    if(value == 1){
        buffer =  '<em class="J_blank"></em>';
    }else if(value == 2){
        buffer =  '<em class="J_blank J_blank_1"></em>';
    }else if(value == 3){
        buffer =  '<em class="J_blank J_blank_2"></em>';
    }
    return new Handlebars.SafeString(buffer);
});

Handlebars.registerHelper('retFix', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    if(_val.indexOf('.') !== -1){
        _val = parseFloat(value, 10);
        _val = _val.toFixed(2);
    }
    return _val;
});

Handlebars.registerHelper('retFixA', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    if(_val.indexOf('.') !== -1){
        _val = parseFloat(value, 10);
        _val = _val.toFixed(2);
    }
    var intSum = _val.substring(0, _val.indexOf(".")).replace( /\B(?=(?:\d{3})+$)/g, ',' );//取到整数部分
    var dot = _val.substring(_val.length, _val.indexOf("."))//取到小数部分搜索

    return intSum + dot;
});

Handlebars.registerHelper('retFixS', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    if(_val.indexOf('.') !== -1){
        _val = parseFloat(value, 10);
        _val = _val.toFixed(2);
    }
    return _val+'%';
});

Handlebars.registerHelper('retFixB', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    if(_val.indexOf('.') !== -1){
        _val = parseFloat(value, 10);
        _val = zuiaccDiv(_val, 100);
        _val = _val.toFixed(3);
    }else{
        _val = parseFloat(value, 10);
        _val = zuiaccDiv(_val, 100);
    }
    return _val+'%';
});

//除法函数，用来得到精确的除法结果  
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。  
//调用：accDiv(arg1,arg2)  
//返回值：arg1除以arg2的精确结果  
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

function addNewPx(treeid){
    $(".select2-container").css({"zIndex": "1"});
    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $(".select2-container").css({"zIndex": "9999"});
        curTreeId = "";
        $("#J_formwrap").html("");
    })
    $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        curTreeId = treeid;
        $("#J_formwrap").html($("#T_formwrap").val());

        getZuiPx();
    })
}

function getZuiPx(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/pxs/pxses.do", "", function(state, json){
        if(state == 'success'){
            if(json){
                $("#J_pxname").temp($("#T_zuiStoreIdS").val(), json);
                $("#J_pxname").select2({
                    placeholder: "请选择培训师",
                    allowClear: false,
                    language: "zh-CN",
                    dropdownAutoWidth: false
                });

            }else{
                MyFun.to.e(json.message || "查询培训师列表失败");
            }
        }
        getZuiDy();
    })
}

function getZuiDy(){
    $.PostJson("${request.contextPath}/storedy/storeydys.do", "storeid="+p_getStoreId, function(state, json){
        if(state == 'success'){
            if(json){
                $("#J_dyname").temp($("#T_zuiStoreIdS").val(), json);
                $("#J_dyname").multiselect("destroy").multiselect({
                    nonSelectedText: '请选择',
                    nSelectedText: '个已选择',
                    allSelectedText: '全选',
                    includeSelectAllOption: true,
                    selectAllText: '全选',
                    selectAllValue: '',
                    onChange: function(){
                        
                    },
                    onSelectAll: function(){
                        
                    },
                    onDeselectAll: function(){
                        
                    }
                });

            }else{
                MyFun.to.e(json.message || "查询店员列表失败");
            }
        }
        initBtns();
        unBlock(".modal-dialog");
    })
}

function initBtns(){
    //日期范围限制
    $("#startdateS").val(cdate);
    $("#enddateS").val(lastdate);
    var start = {
        elem: '#startdateS',
        format: 'YYYY-MM-DD',
        min: '2017-01-01',
        max: lastdate,
        start: cdate,  //开始日期
        istime: false,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#enddateS',
        format: 'YYYY-MM-DD',
        min: cdate,
        start: lastdate,
        istime: false,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
}
/**
 * 获取当前时间
 * type为true表示带时分秒
**/
function getCurDate(cDate, type){
    if(type && type == '2'){
        cDate = cDate.formatDD( "yyyy-MM-DD hh:mm:ss");
    }else if(type){
        cDate = cDate.formatDD( "yyyy-MM-DD hh:mm");
    }else{
        cDate = cDate.formatDD( "yyyy-MM-DD");
    }
    return cDate;
}
Date.prototype.formatDD = function( formatStr){ 
    var date = this;
    var str = formatStr; 
    str=str.replace(/yyyy|YYYY/,date.getFullYear()); 
    str=str.replace(/yy|YY/,(date.getYear() % 100)>9?(date.getYear() % 100).toString():"0" + (date.getYear() % 100)); 
    str=str.replace(/MM/,date.getMonth()>8?(date.getMonth()+1).toString():"0" + (date.getMonth()+1)); 
    str=str.replace(/M/g,date.getMonth()+1); 
    str=str.replace(/dd|DD/,date.getDate()>9?date.getDate().toString():"0" + date.getDate()); 
    str=str.replace(/d|D/g,date.getDate()); 
    str=str.replace(/hh|HH/,date.getHours()>9?date.getHours().toString():"0" + date.getHours()); 
    str=str.replace(/h|H/g,date.getHours()); 
    str=str.replace(/mm/,date.getMinutes()>9?date.getMinutes().toString():"0" + date.getMinutes()); 
    str=str.replace(/m/g,date.getMinutes()); 
    str=str.replace(/ss|SS/,date.getSeconds()>9?date.getSeconds().toString():"0" + date.getSeconds()); 
    str=str.replace(/s|S/g,date.getSeconds()); 
    return str; 
}

function saveForm(){
    var storeid = p_getStoreId,
        treeid = curTreeId,
        _J_formwrapObj = $("#J_formwrap"),
        taskname = _J_formwrapObj.find("input[name='taskname']").val(),
        pxname = $("#J_pxname").val(),
        dyname = $("#J_dyname").val() || [],
        startdate = _J_formwrapObj.find("input[name='startdate']").val(),
        enddate = _J_formwrapObj.find("input[name='enddate']").val(),
        remark = _J_formwrapObj.find("input[name='remark']").val();

    if(!storeid){
        MyFun.to.i("请选择门店");
        return false;
    }
    if(!treeid){
        MyFun.to.i("请选择树节点");
        return false;
    }
    if(!taskname){
        MyFun.to.i("请输入任务名");
        return false;
    }
    if(!pxname){
        MyFun.to.i("请选择培训师");
        return false;
    }
    if(!dyname.length){
        MyFun.to.i("请选择店员");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;

    var str = "id=&storeid="+storeid+"&treeid="+treeid+"&jobid="+taskname+"&pxname="+pxname+"&dyname="+dyname.join(",")+"&startdate="+startdate+"&enddate="+enddate+"&remark="+remark;

    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtreetask/add.do", str, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "操作成功");

                $("#info-form").modal('hide');
                getDetail();
            }else{
                MyFun.to.e(json.message || "操作失败");
            }
        }
        unBlock(".modal-dialog");
        clickFlag = 0;
    })
}
</script>
</body>
</html>