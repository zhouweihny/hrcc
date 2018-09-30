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
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>

<style>
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
#J_imptype {
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
                    <h5>分析统计</h5>
                </div>
                <div class="ibox-content">
                    <ul class="nav nav-tabs m-t" id="myTabs">
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
                                        <th>排名</th>
                                        <th>品类名称</th>
                                        <th>购物频率</th>
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

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

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
    <td>{{indexAdd @index}}</td>
    <td>{{treename1}}</td>
    <td>{{rank}}</td>
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
    ajax1 = 0,
    ajax2 = 0,
    ajax3 = 0,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    indexsType = getQueryString("type") ? getQueryString("type") : "",
    curStoreId = '',
    curSaleDataId = '';

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        curStoreId = window.parent.W_p_getStoreId();
        curSaleDataId = window.parent.W_p_getSaleFileId();
        
    }

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

    getTabPage();

})

function getTabPage(){
    createBlock(".wrapper", '正在努力加载数据...');
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
        unBlock(".wrapper");
    })
}

function getTabPage2(){
    createBlock(".wrapper", '正在努力加载数据...');
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
        unBlock(".wrapper");
    })
}

function getTabPage3(){
    createBlock(".wrapper", '正在努力加载数据...');
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
        unBlock(".wrapper");
    })
}

/**
 * 返回索引
 */
Handlebars.registerHelper("indexAdd", function(txt,fn) {
    var buffer = txt+1;
    return buffer;
});
</script>
</body>
</html>