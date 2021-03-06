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

<style>
#zuiTree {
    border: 1px solid #ddd;
    width: 100%;
    height: 400px;
    overflow: auto;
    margin-top: 5px;
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
    vertical-align: -2px;
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
.select2-container {
    margin-bottom: 0;
}
.J_storeFileSplit {
    vertical-align: -2px;
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
                    <h5>病种配方树报表</h5>
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
                        <div class="form-group col-md-3">
                            <label for="J_startdate">销售日期：</label>
                            <select class="form-control J_zuiStoreDate" id="J_startdate"></select>
                            <em class="J_storeFileSplit">至</em>
                            <select class="form-control J_zuiStoreDate" id="J_enddate"></select>
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-w-m btn-info" onclick="getDetail();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-md-3">
                            <ul id="zuiTree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                                <thead>
                                    <tr role="row">
                                        <th width="6%">序号</th>
                                        <th width="10%">编码</th>
                                        <th class="J_sortBtn sorting" data-stype="desc" data-sfield="name">名称</th>
                                        <th width="9%">规格</th>
                                        <th width="9%">厂商</th>
                                        <th width="12.5%" class="J_sortBtn sorting_desc" data-stype="asc" data-sfield="xse">销售额</th>
                                        <th class="J_sortBtn sorting" data-stype="desc" data-sfield="xssl">销售数量</th>
                                        <th class="J_sortBtn sorting" data-stype="desc" data-sfield="pjsj">平均售价</th>
                                        <th class="J_sortBtn sorting" data-stype="desc" data-sfield="ml">毛利</th>
                                        <th width="9.5%" class="J_sortBtn sorting" data-stype="desc" data-sfield="mlv">毛利率</th>
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

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<textarea class="fn-hide" id="T_tbl_body">
{{#each this}}
<tr>
    <td>{{indexAdd @index}}</td>
    <td>{{code}}</td>
    <td>{{name}}</td>
    <td>{{specifications}}</td>
    <td>{{factory}}</td>
    <td class="fn-text-right">{{retFix xse}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix pjsj}}</td>
    <td class="fn-text-right">{{retFix ml}}</td>
    <td class="fn-text-right">{{retFixS mlv}}</td>
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

var zuiTree = null,
    clickFlag = 0,
    G_json = null,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    sortdataS = 'stype=desc&sfield=xse',
    curTreeId = curStoreId = '',
    startdate = enddate = '',
    p_getStoreId = '',
    p_getFileId = '',
    curSaleTreeName = '';

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
    $("#OrderList thead").on("click", "th.J_sortBtn", function(){
        var _this = $(this),
            sfield = _this.data("sfield"),
            stype = _this.data("stype");

        _this.siblings("th.J_sortBtn").attr({"class": "J_sortBtn sorting"}).data("stype", 'desc');
        _this.attr({"class": "J_sortBtn sorting_"+stype});
        sortdataS = "stype="+stype+"&sfield="+sfield;

        _this.data("stype", stype == 'desc' ? 'asc' : 'desc');

        getDetail();
    })

    setTimeout(function(){
        getzTree();
    }, 300)
}

function getzTree(){
    var wh = window.parent.W_p_getWindowWh(),
        _w = parseFloat(wh.w * 0.71, 10).toFixed(2),
        _h = parseFloat(wh.h * 0.8, 10).toFixed(2);
    $("#zuiTree").css({"height": _h});
    $("#OrderList").css("cssText", "width: "+_w+"px !important;table-layout: fixed;").parent().addClass("table-responsive");

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/tree.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                var arr = [];
                if(json.code == '0000'){
                    //只展示病种配方树
                    curTreeId = "18346A0044A148D1A818627B25510E59";
                    for (var i=0, l=json.data.length; i<l; i++) {
                        if(json.data[i].path.indexOf(curTreeId) !== -1){
                            var attr = {};
                            attr.id = json.data[i].id;
                            attr.pId = json.data[i].parentid;
                            attr.name = json.data[i].name;
                            arr.push(attr);
                        }
                    }
                }

                $.fn.zTree.init($("#zuiTree"), setting, arr);
                zuiTree = $.fn.zTree.getZTreeObj("zuiTree");

                /*var node = zuiTree.getNodeByParam('id', curTreeId, null);
                zuiTree.expandNode(node);
                zuiTree.selectNode(node);
                curSaleTreeName = node.name;

                getDetail();*/
                var firstNode = zuiTree.getNodes()[0];
                zuiTree.expandNode(firstNode);

                $("#J_tbl_body").html('');

            }else{
                MyFun.to.e(json.message || "查询病种配方树失败");
            }
        }
        unBlock(".wrapper");
    })
}

function zTreeClick(event, treeId, treeNode) {
    $("#J_treeNodeId").val(treeNode.id);
    curStoreId = $("#supplierid").val() || '';

    if(clickFlag)
        return false;
    clickFlag = 1;
    curTreeId = treeNode.id;
    curSaleTreeName = treeNode.name;
    getDetail();
}

function getDetail(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/analysis/salebytree2.do", sortdataS+"&id="+curTreeId+"&storeid="+p_getStoreId+"&startdate="+startdate+"&enddate="+enddate, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                
                if(json.data && json.data.length)
                    $("#J_tbl_body").temp($("#T_tbl_body").val(), json.data);
                else
                    $("#J_tbl_body").html('<tr><td colspan="10" style="text-align: center;">暂无数据</td></tr>');

            }else{
                MyFun.to.e(json.message || "查询病种配方树失败");
            }
        }
        clickFlag = 0;
        unBlock(".wrapper");
    })
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
    _val = _val.toFixed(1);
    return _val+'%';
});
</script>
</body>
</html>