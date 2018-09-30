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
<script src="${request.contextPath}/js/plugins/echarts/echarts.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">

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
.J_charts {
    width: 100%;
    height: 400px;
    border: 1px solid #ddd;
    padding: 5px 10px;
    margin-bottom: 15px;
}
.J_storeFileSplit {
    vertical-align: 2px;
    margin: 0 5px 0 0;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>价格带分析</h5>
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
                            <button type="button" class="btn btn-w-m btn-info" onclick="getzTree();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-md-3">
                            <ul id="zuiTree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <div class="J_chartsWrap">
                                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                                <div id="J_charts_1" class="J_charts"></div>
                            </div>
                        </div>
                    </div>
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
    J_charts_1 = J_charts_2 = null;

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
        beforeClick: zbeforeClick,
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
    getzTree();
}

function getzTree(){
    var wh = window.parent.W_p_getWindowWh(),
        _w = parseFloat(wh.w * 0.71, 10).toFixed(2),
        _h = parseFloat((wh.h - 64) * 0.8, 10).toFixed(2);
    $("#zuiTree").css({"height": _h});

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/tree.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                var arr = [];
                if(json.code == '0000'){
                    //只展示品种分类树
                    curTreeId = "654CA2E6C2164A148F287B57A4483AF7";
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

                var zuiNodes = zuiTree.transformToArray(zuiTree.getNodes());
                var firstNode = zuiNodes[2];
                curTreeId = firstNode.id;
                curSaleTreeName = firstNode.name;

                zuiTree.expandNode(firstNode);
                zuiTree.selectNode(firstNode);

                getDetail();

            }else{
                MyFun.to.e(json.message || "查询品种分类树失败");
            }
        }
        unBlock(".wrapper");
    })
}

function zbeforeClick(treeId, treeNode, dclickFlag) {
    if(clickFlag)
        return false;
}
function zTreeClick(event, treeId, treeNode) {
    curStoreId = $("#supplierid").val() || '';
    curTreeId = treeNode.id;
    curSaleTreeName = treeNode.name;
    getDetail();
}

function getDetail(){
    clickFlag = 1;
    J_charts_1 = echarts.init(document.getElementById('J_charts_1'));

    J_charts_1.showLoading({
        text: '正在努力加载...'
    });

    $.PostJson("${request.contextPath}/fxsaleanalysis/priceband.do", "storeid="+p_getStoreId+"&startdate="+startdate+"&enddate="+enddate+"&treeid="+curTreeId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                
                if(json.data.length){
                    var arr1 = [],
                        arr2 = [],
                        arr3 = [];
                    for(var i=0,len=json.data.length; i<len; i++){
                        var price = json.data[i].price || 0,
                            xse = json.data[i].xse || 0,
                            pzs = json.data[i].pzs || 0;
                        arr1.push(price);
                        arr2.push(parseFloat(xse, 10));
                        arr3.push(parseInt(pzs, 10));
                    }

                    // 指定图表的配置项和数据
                    var option = {
                        xAxis: {
                            name: "价格（元）",
                            nameLocation: "center",
                            nameGap: 35,
                            nameTextStyle: {
                                fontSize: 16
                            },
                            type: 'category',
                            data: arr1
                        },
                        yAxis: [
                            {
                                name: "销售额",
                                nameGap: 25,
                                nameTextStyle: {
                                    fontSize: 16
                                },
                                axisLine: {
                                    lineStyle: {
                                        color: "#d14a61"
                                    }
                                },
                                type: 'value'
                            },
                            {
                                name: "品种数",
                                nameGap: 25,
                                nameTextStyle: {
                                    fontSize: 16
                                },
                                type: 'value',
                                splitLine: {
                                    show: false
                                },
                                max: function(value) {
                                    var buffer = 10;
                                    if(value.max > 10){
                                        buffer = value.max + 10;
                                    }
                                    return buffer;
                                }
                            }
                        ],
                        series: [
                            {
                                name: "销售额",
                                data: arr2,
                                type: 'line',
                                smooth: true,
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                }
                            },
                            {
                                name: "品种数",
                                data: arr3,
                                type: 'line',
                                smooth: true,
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                },
                                yAxisIndex:1,
                                barWidth: '10px'
                            }
                        ],
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['销售额','品种数']
                        }
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    J_charts_1.setOption(option);
                }else{
                    MyFun.to.i("暂无数据");
                    J_charts_1.clear();
                }

            }else{
                MyFun.to.e(json.message || "查询价格图表失败");
                J_charts_1.clear();
            }
        }
        clickFlag = 0;
        J_charts_1.hideLoading();
    })
}

</script>
</body>
</html>