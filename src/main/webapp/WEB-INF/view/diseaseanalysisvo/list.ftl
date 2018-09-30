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
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">


<style>
.saledate_wrap {
    position: relative;
}
.saledate_wrap input {
    cursor: pointer;
}
.saledate_wrap i {
    position: absolute; bottom: 9px; left: 236px; top: auto; cursor: pointer;
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
.modal-dialog {
    width: 1100px!important;
}
.modal-open .modal .ibox-content {
    max-height: 550px;
}
.modal-dialog .ibox.float-e-margins {
    margin-bottom: 10px;
}
.zuiTreeCheckAll {
    float: right;
}
.zuiTreeCheck .fa {
    display: none;
    width: 25px;
    height: 25px;
    text-align: center;
    line-height: 25px;
    font-size: 22px;
    cursor: pointer;
    vertical-align: middle;
}
.zuiTreeCheck .fa-square-o {
    display: inline-block;
    font-size: 24px;
}
.zuiTreeCheck.check .fa-check-square-o,
.zuiTreeCheck.halfcheck .fa-check-square {
    display: inline-block;
}
.zuiTreeCheck.check .fa-square-o, .zuiTreeCheck.check .fa-check-square,
.zuiTreeCheck.halfcheck .fa-square-o, .zuiTreeCheck.halfcheck .fa-check-square-o {
    display: none;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>消费者分析</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
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
                            <button type="button" class="btn btn-w-m btn-info" onclick="getdisdata();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    
                    <div class="m-t">
                        <a href="javascript:;" class="btn btn-primary" onclick="addCustomList();">保 存</a>
                    </div>

                    <div id="OrderListWrap">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>疾病</th>
                                    <th class="J_sortBtn sorting_desc" data-stype="asc" data-sfield="frequency">频次</th>
                                    <th class="J_sortBtn sorting" data-stype="desc" data-sfield="proportion">占比</th>
                                    <th class="fn-clear">
                                        全选
                                        <div class="zuiTreeCheck zuiTreeCheckAll">
                                            <i class="fa fa-square-o" aria-hidden="true"></i>
                                            <i class="fa fa-check-square" aria-hidden="true"></i>
                                            <i class="fa fa-check-square-o" aria-hidden="true"></i>
                                        </div>
                                    </th>
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
<div id="info-form" class="modal fade" aria-hidden="true" ></div>
<div id="info-formS" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>销售详情<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_tree_tbl" aria-describedby="DataTables_Table_0_info">
                        <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>编码</th>
                                <th class="J_sortBtn sorting" data-stype="desc" data-sfield="name">名称</th>
                                <th>规格</th>
                                <th>厂商</th>
                                <th class="J_sortBtn sorting_desc" data-stype="asc" data-sfield="xse">销售额</th>
                                <th class="J_sortBtn sorting" data-stype="desc" data-sfield="xssl">销售数量</th>
                                <th class="J_sortBtn sorting" data-stype="desc" data-sfield="pjsj">平均售价</th>
                                <th class="J_sortBtn sorting" data-stype="desc" data-sfield="ml">毛利</th>
                                <th class="J_sortBtn sorting" data-stype="desc" data-sfield="mlv">毛利率</th>
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

<textarea class="fn-hide" id="T_options">
{{#each this}}
<option value="{{id}}">{{pathname}}</option>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_OrderList">
{{#each this}}
<tr data-id="{{id}}" data-type="1">
    <td>
        {{name}}
        {{#if items}}<i class="fa fa-plus-square-o J_showLess" aria-hidden="true"></i>{{/if}}
    </td>
    <td class="fn-text-right">{{frequency}}</td>
    <td class="fn-text-right">{{retFixS proportion}}</td>
    <td class="fn-text-right">
        <div class="zuiTreeCheck">
            <i class="fa fa-square-o" aria-hidden="true"></i>
            <i class="fa fa-check-square" aria-hidden="true"></i>
            <i class="fa fa-check-square-o" aria-hidden="true"></i>
        </div>
    </td>
    <td class="fn-text-right"><a href="javascript:;" onclick="showTreeSale('{{id}}');">销售详情</a></td>
</tr>

{{#if items}}
{{#each items}}
<tr class="J_sub_{{../id}} fn-hide" data-id="{{id}}" data-type="2" data-pid="{{../id}}">
    <td>
        {{retBlank 1}}
        {{name}}
        {{#if items}}<i class="fa fa-plus-square-o J_showLess" aria-hidden="true"></i>{{/if}}
    </td>
    <td class="fn-text-right">{{frequency}}</td>
    <td class="fn-text-right">{{retFixS proportion}}</td>
    <td class="fn-text-right">
        <div class="zuiTreeCheck">
            <i class="fa fa-square-o" aria-hidden="true"></i>
            <i class="fa fa-check-square" aria-hidden="true"></i>
            <i class="fa fa-check-square-o" aria-hidden="true"></i>
        </div>
    </td>
    <td class="fn-text-right"><a href="javascript:;" onclick="showTreeSale('{{treeid}}');">销售详情</a></td>
</tr>

{{#if items}}
{{#each items}}
<tr class="J_sub_{{../../../id}} J_sub_{{../../id}} fn-hide" data-id="{{id}}" data-type="3" data-pid="{{../../../id}},{{../../id}}">
    <td>
        {{retBlank 2}}
        {{name}}
        {{#if items}}<i class="fa fa-plus-square-o J_showLess" aria-hidden="true"></i>{{/if}}
    </td>
    <td class="fn-text-right">{{frequency}}</td>
    <td class="fn-text-right">{{retFixS proportion}}</td>
    <td class="fn-text-right">
        <div class="zuiTreeCheck">
            <i class="fa fa-square-o" aria-hidden="true"></i>
            <i class="fa fa-check-square" aria-hidden="true"></i>
            <i class="fa fa-check-square-o" aria-hidden="true"></i>
        </div>
    </td>
    <td class="fn-text-right"><a href="javascript:;" onclick="showTreeSale('{{treeid}}');">销售详情</a></td>
</tr>

{{#if items}}
{{#each items}}
<tr class="J_sub_{{../../../../../id}} J_sub_{{../../../../id}} J_sub_{{../../id}} fn-hide" data-type="4" data-pid="{{../../../../../id}},{{../../../../id}},{{../../id}}">
    <td>{{retBlank 3}}{{name}}</td>
    <td class="fn-text-right">{{frequency}}</td>
    <td class="fn-text-right">{{retFixS proportion}}</td>
    <td class="fn-text-right">
        <div class="zuiTreeCheck">
            <i class="fa fa-square-o" aria-hidden="true"></i>
            <i class="fa fa-check-square" aria-hidden="true"></i>
            <i class="fa fa-check-square-o" aria-hidden="true"></i>
        </div>
    </td>
    <td class="fn-text-right"><a href="javascript:;" onclick="showTreeSale('{{treeid}}');">销售详情</a></td>
</tr>
{{/each}}
{{/if}}

{{/each}}
{{/if}}

{{/each}}
{{/if}}

{{/each}}
</textarea>

<textarea class="fn-hide" id="T_tree_tbl">
{{#each this}}
<tr>
    <td>{{indexAdd @index}}</td>
    <td>{{code}}</td>
    <td>{{name}}</td>
    <td>{{specifications}}</td>
    <td>{{factory}}</td>
    <td class="fn-text-right" style="min-width: 80px;">{{retFixE xse}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix pjsj}}</td>
    <td class="fn-text-right">{{retFix ml}}</td>
    <td class="fn-text-right">{{retFixM mlv}}</td>
</tr>
{{/each}}
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
var indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    p_getStoreId = '',
    p_getFileId = '',
    startdate = enddate = '',
    sortdata = 'stype=desc&sfield=frequency',
    sortdataS = 'stype=desc&sfield=xse',
    cstartdate = cenddate = '',
    curSaleTreeId = '',
    clickFlag = 0;

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

    createBlock(".wrapper");
    $.ajax({
        type: "post",
        url: url.split("?")[0],
        dataType: "json",
        data:data,
        success: function(data){

        },
        complete:function(XMLHttpRequest,status){ 
            $('#'+divid).empty();
            $('#'+divid).append(XMLHttpRequest.responseText);    
            $('#'+divid).data("url",url);
            if(typeof callback === 'function')
                callback();
            unBlock(".wrapper");
        }
    });
};  

function refreshDiseaseAnalysisVoList(){
    MyFun.ajaxRefreshTable("DiseaseAnalysisVoList");
}

$(function(){
    if(indexsFlag && indexsFlag == '1'){
        var _h = parseFloat(window.parent.W_p_getWindowWh().h * 0.7, 10).toFixed(2);
        $(".modal-dialog .ibox-content").css("cssText", "height: "+_h+"px !important;");
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
            // initZuiStorePage();
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
    getzPage();

    $("#OrderList thead").off("click").on("click", "th.J_sortBtn", function(){
        var _this = $(this),
            sfield = _this.data("sfield"),
            stype = _this.data("stype");

        _this.siblings("th.J_sortBtn").attr({"class": "J_sortBtn sorting"}).data("stype", 'desc');
        _this.attr({"class": "J_sortBtn sorting_"+stype});
        sortdata = "stype="+stype+"&sfield="+sfield;

        _this.data("stype", stype == 'desc' ? 'asc' : 'desc');

        getzPage();
    })

    $("#J_tree_tbl thead").off("click").on("click", "th.J_sortBtn", function(){
        var _this = $(this),
            sfield = _this.data("sfield"),
            stype = _this.data("stype");

        _this.siblings("th.J_sortBtn").attr({"class": "J_sortBtn sorting"}).data("stype", 'desc');
        _this.attr({"class": "J_sortBtn sorting_"+stype});
        sortdataS = "stype="+stype+"&sfield="+sfield;

        _this.data("stype", stype == 'desc' ? 'asc' : 'desc');

        getzPageS();
    })
}

function getdisdata(){
    getzPage();

    $("#OrderList thead").off("click").on("click", "th.J_sortBtn", function(){
        var _this = $(this),
            sfield = _this.data("sfield"),
            stype = _this.data("stype");

        _this.siblings("th.J_sortBtn").attr({"class": "J_sortBtn sorting"}).data("stype", 'desc');
        _this.attr({"class": "J_sortBtn sorting_"+stype});
        sortdata = "stype="+stype+"&sfield="+sfield;

        _this.data("stype", stype == 'desc' ? 'asc' : 'desc');

        getzPage();
    })

    $("#J_tree_tbl thead").off("click").on("click", "th.J_sortBtn", function(){
        var _this = $(this),
            sfield = _this.data("sfield"),
            stype = _this.data("stype");

        _this.siblings("th.J_sortBtn").attr({"class": "J_sortBtn sorting"}).data("stype", 'desc');
        _this.attr({"class": "J_sortBtn sorting_"+stype});
        sortdataS = "stype="+stype+"&sfield="+sfield;

        _this.data("stype", stype == 'desc' ? 'asc' : 'desc');

        getzPageS();
    })
}

function getzPage(){
    createBlock(".wrapper", '正在努力加载数据...');
    $("#J_tbl_body").html('');
    var datas = $("#J_formSearch").formSerialize();
    $.PostJson("${request.contextPath}/analysis/table.do", sortdata+"&"+datas, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                $("#J_tbl_body").temp($("#T_OrderList").val(), json.data);

                $("#J_tbl_body .J_showLess").off("click").on("click", function(){
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

                initCheckComp();

                getCheckedTree();

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
    _val = parseFloat(value, 10);
    _val = _val.toFixed(2);
    return _val;
});

Handlebars.registerHelper('retFixE', function(value){
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

Handlebars.registerHelper('retFixM', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = _val.toFixed(1);
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

function showTreeSale(treeid){
    curSaleTreeId = treeid;
    $(".select2-container").css({"zIndex": "1"});
    $("#info-formS").modal('show');
    $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $(".select2-container").css({"zIndex": "9999"});
        $("#J_tree_tbl tbody").html("");
    })

    $('#info-formS').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        getzPageS();
    })
}

function getzPageS(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/analysis/salebytree.do", sortdataS+"&treeid="+curSaleTreeId+"&storeid="+p_getStoreId+"&startdate="+startdate+"&enddate="+enddate, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data && json.data.length)
                    $("#J_tree_tbl tbody").temp($("#T_tree_tbl").val(), json.data);
                else
                    $("#J_tree_tbl tbody").html('<tr><td colspan="10" style="text-align: center;">暂无数据</td></tr>')
            }else{
                MyFun.to.e(json.message || "查询销量详情失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function initCheckComp(){
    var _zuiTreeCheck = $(".zuiTreeCheck").not(".zuiTreeCheckAll");
    _zuiTreeCheck.off("click");

    _zuiTreeCheck.each(function(){
        var _this = $(this),
            _tr = _this.parent().parent(),
            _trId = _tr.data("id"),
            _trType = _tr.data("type");
                                    
        _this.on("click", function(){
            if(_this.hasClass("check") || _this.hasClass("halfcheck")){
                //取消选中
                _this.removeClass("check").removeClass("halfcheck");

                //子节点全不选
                $(".J_sub_"+_trId).find(".zuiTreeCheck").removeClass("check").removeClass("halfcheck");

            }else{
                //选中
                _this.removeClass("halfcheck").addClass("check");

                //子节点全选
                $(".J_sub_"+_trId).find(".zuiTreeCheck").removeClass("halfcheck").addClass("check");

            }
            //父节点控制
            setPTrCheck(_tr, _trType);

        })
    })

    $('.zuiTreeCheckAll').off("click").on("click", function(){
        var _this = $(this),
            _allTr = $("#J_tbl_body").find("tr");

        if(_this.hasClass("check") || _this.hasClass("halfcheck")){
            //取消选中
            _this.removeClass("check").removeClass("halfcheck");
            _allTr.find(".zuiTreeCheck").removeClass("check").removeClass("halfcheck");

        }else{
            //选中
            _this.removeClass("halfcheck").addClass("check");
            //子节点全选
            _allTr.find(".zuiTreeCheck").removeClass("halfcheck").addClass("check");
        }
    })
}

function setPTrCheck(_tr, _trType){
    var _pid = _tr.data("pid"),
        _tbody = _tr.parent();
    if(_pid){
        _pid = _pid.split(",");

        for(var i=0,len=_pid.length; i<len; i++){
            var _pTr = _tbody.parent().find("tr[data-id='"+_pid[i]+"']"),
                _subTr = _tbody.parent().find("tr[data-pid='"+_pid[i]+"']"),
                _subTrCheck = 0;

            _subTr.each(function(){
                var _self = $(this);
                if(_self.find(".zuiTreeCheck").hasClass("check"))
                    _subTrCheck++;
            })

            if(_subTrCheck == 0){
                _pTr.find(".zuiTreeCheck").removeClass("halfcheck").removeClass("check");
                return false;
            }

            if(_subTrCheck == _subTr.length)
                _pTr.find(".zuiTreeCheck").removeClass("halfcheck").addClass("check");
            else
                _pTr.find(".zuiTreeCheck").removeClass("check").addClass("halfcheck");
        }
    }

    var checkAllCount = 0;
    _tbody.find(".zuiTreeCheck").each(function(){
        var _this = $(this);
        if(_this.hasClass("check"))
            checkAllCount++;
    })

    if(checkAllCount == _tbody.find("tr").length){
        $('.zuiTreeCheckAll').removeClass("halfcheck").addClass("check");
        return false;
    }

    if(checkAllCount == 0){
        $('.zuiTreeCheckAll').removeClass("halfcheck").removeClass("check");
        return false;
    }

    $('.zuiTreeCheckAll').addClass("halfcheck").removeClass("check");
}

function addCustomList(){
    var treeids = [];
    $(".zuiTreeCheck").not(".zuiTreeCheckAll").each(function(){
        var _this = $(this);
        if(_this.hasClass("check") || _this.hasClass("halfcheck")){
            treeids.push(_this.parent().parent().data("id"));
        }
    })
    if(!treeids.length){
        MyFun.to.i("请选择树节点");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtreestore/addlist.do", "storeid="+p_getStoreId+"&treeids="+treeids.join(","), function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "自定义树成功");

                setTimeout(function(){
                    getzPage();
                }, 500)
            }else{
                MyFun.to.e(json.message || "自定义树失败");
            }
        }
        unBlock(".wrapper");
        clickFlag = 0;
    })
}

function getCheckedTree(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtreestore/selected.do", "storeid="+p_getStoreId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                
                var treeids = json.data;
                if(!treeids.length)
                    return false;

                var pTreeIds = [];

                for(var i=0, len=treeids.length; i<len; i++){
                    if($(".J_sub_"+treeids[i]).length){
                        //是父节点，先不做处理
                        pTreeIds.push(treeids[i]);
                    }else{
                        $("#J_tbl_body").find("tr[data-id='"+treeids[i]+"']").find(".zuiTreeCheck").addClass("check");
                    }
                }

                for(var k=0, klen=pTreeIds.length; k<klen; k++){
                    var checkCount = 0,
                        _check = $(".J_sub_"+pTreeIds[k]).find(".zuiTreeCheck"),
                        _checkLen = _check.length;
                    _check.each(function(){
                        if($(this).hasClass("check"))
                            checkCount++;
                    })

                    if(checkCount == _checkLen)
                        $("#J_tbl_body").find("tr[data-id='"+pTreeIds[k]+"']").find(".zuiTreeCheck").addClass("check");
                    else
                        $("#J_tbl_body").find("tr[data-id='"+pTreeIds[k]+"']").find(".zuiTreeCheck").addClass("halfcheck");
                }

                var checkAllCount = 0;
                $("#J_tbl_body").find(".zuiTreeCheck").each(function(){
                    var _this = $(this);
                    if(_this.hasClass("check"))
                        checkAllCount++;
                })

                if(checkAllCount == $("#J_tbl_body").find("tr").length)
                    $('.zuiTreeCheckAll').removeClass("halfcheck").addClass("check");
                else
                    $('.zuiTreeCheckAll').addClass("halfcheck").removeClass("check");

            }else{
                MyFun.to.e(json.message || "获取自定义树失败");
            }
        }
        unBlock(".wrapper");
    })
}

</script>
</body>
</html>