<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>品类排名</title>
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/jquery-weui/css/jquery-weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/css/font-awesome.css?v=4.4.0">
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/mobiscroll/mobiscroll.zcore.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/iscroll/4.2.5/pullToRefresh.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/css/wxzui_common.css?v=1">
<style>
.weui-label {
    width: 3.5rem;
    color: #555;
}
.J_router_search_detail .J_content .weui-cell .weui-cell__hd {
    border-right: 0;
}
#J_restForm .fa {
    font-size: 1.1rem;
}
.J_pname {
    background: #f9f9f9;
}
.J_main_dateWrap .J_showbefore {
    position: absolute;
    right: 0;
    width: 60px;
    height: 40px;
    line-height: 40px;
    top: 0;
}
.J_router_search_detail .J_content .weui-cell .weui-cell__hd {
    border-right: 0;
}
.J_rootname {
    background: #f9f9f9;
    border-right: 1px solid #e7e7e7;
}
body.zui {
    background: #fff;
}
.J_content {
    overflow-y: auto;
}
.J_content .tit {
    text-align: center;
    font-size: .8rem;
    color: #4e809a;
    font-weight: 400;
    margin: 0 15%;
    padding: 10px 0;
}
.swiper-container {
    width: 100%;
    margin: 0 auto;
}
.swiper-slide {
    text-align: center;
    font-size: 18px;
    background: #fff;
}
.J_content .J_section {
    padding: 10px 0;
    width: 100%;
    background: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
}
.J_content .J_section:nth-child(even) {
    background: #F6F7F9;
}
.J_content .des {
    width: 100%;
    font-size: .6rem;
    color: #607D8F;
    padding: 0 .8rem;
}
.J_content .des h3 {
    text-align: center;
}
.J_content .des p {
    font-size: .53rem;
    margin-top: 6px;
    text-align: left;
    text-indent: 1rem;
}
.J_content .zbgwrap {
    width: 40%;
}
.J_content .zbgwrap img {
    width: 100%;
    height: 100%;
}
.J_content .tips {
    background: #2D9569;
    padding: 25px 0;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
}
.J_content .tips h3 {
    width: 20%;
    text-align: center;
}
.J_noword {
    height: 30px;
    line-height: 30px;
    text-align: center;
    margin-top: 3rem;
}
.J_main_dateWrap {
    position: relative;
}
.J_main_dateWrap .J_showbefore {
    position: absolute;
    right: 0;
    width: 60px;
    height: 40px;
    line-height: 40px;
    top: 0;
}
.J_leftTit span {
    font-size: .55rem;
    color: #6f6f6f;
}
.J_router_main .J_dataTbl tbody tr:nth-of-type(odd) {
    background-color: #fff;
}
.J_router_main .J_dataTbl tbody tr:nth-of-type(even) {
    background-color: #f9f9f9;
}
.J_dataTbl tbody tr td {
    border-bottom: 0;
}
</style>
</head>
<body class="zui nofooter">

<section class="fn-hide J_router_main">
    <header class="J_header">
        <a class="J_header_back" href="javascript:;"><i class="fa fa-angle-left"></i></a>
        <h1 class="J_header-title">品类排名</h1>
        <a class="J_header_right_icon" href="#/J_router_search">筛选</a>
    </header>

    <div class="J_content" id="wrapper">
        <div class="J_main_dateWrap">
            <span></span>至<span></span>
            <a href="javascript:;" id="J_showbefore" class="J_showbefore">
                <span>全部</span>
                <i class="fa fa-caret-down" aria-hidden="true" title=""></i>
            </a>
        </div>
        <table class="J_dataTbl" id="OrderList">
            <thead>
                <tr role="row">
                    <th>节点</th>
                    <th>销售额</th>
                    <th>购买频次</th>
                </tr>
            </thead>
            <tbody id="J_tbl_body"></tbody>
        </table>
    </div>
</section>

<section class="fn-hide J_router_search">
    <header class="J_header">
        <a class="J_header_back" href="javascript:;"><i class="fa fa-angle-left"></i></a>
        <h1 class="J_header-title">筛选</h1>
    </header>

    <div class="J_content">
        
    </div>

    <div class="J_btnwrap">
        <a href="javascript:;" class="zbtn" id="J_subData">查询</a>
    </div>
</section>

<section class="fn-hide J_router_detail">
    <header class="J_header">
        <a class="J_header_back" href="javascript:;"><i class="fa fa-angle-left"></i></a>
        <h1 class="J_header-title">详情</h1>
    </header>

    <div class="J_content" id="J_swiperWrap">
            
    </div>
</section>

<textarea class="fn-hide" id="T_tbl_body">
{{#each this}}
<tr>
    <td class="J_leftTit">
        <a href="javascript:;" class="J_showDetail" data-id="{{treeid}}" data-tit="{{lv2}}">{{lv2}}</a>
        <br>
        <span>（{{lv1}}）</span>
    </td>
    <td class="fn-text-right">{{retFixSD xse}}</td>
    <td class="fn-text-right">{{zbs}}</td>
</tr>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_swiperWrap">
{{#each this}}
<section class="J_section">
    <div class="des">
        <h3>【{{name}}】</h3>
        <p>{{text}}</p>
    </div>
</section>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_search">
<div class="weui-cells">
    <div class="weui-cell">
        <div class="weui-cell__hd">
            <label for="" class="weui-label">门店选择：</label>
        </div>
        <div class="weui-cell__bd">
            <select class="weui-select" name="select1" id="zuiStoreId"></select>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd">
            <label for="" class="weui-label">开始日期：</label>
        </div>
        <div class="weui-cell__bd">
            <select class="weui-select J_zuiStoreDate" id="J_startdate"></select>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd">
            <label for="" class="weui-label">结束日期：</label>
        </div>
        <div class="weui-cell__bd">
            <select class="weui-select J_zuiStoreDate" id="J_enddate"></select>
        </div>
    </div>
</div>
</textarea>

<textarea class="fn-hide" id="T_zuiStoreId">
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>
<textarea class="fn-hide" id="T_zuiStoreDate">
{{#each this}}
<option value="{{this}}">{{this}}</option>
{{/each}}
</textarea>

<div id="J_statusPop" class="weui-popup__container popup-bottom">
    <div class="weui-popup__overlay"></div>
    <div class="weui-popup__modal">
        <div class="weui-cells weui-cells_radio">
            <label class="weui-cell weui-check__label" for="x11">
                <div class="weui-cell__bd">
                    <p>全部</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" class="weui-check" name="radio1" id="x11" checked="checked" value="1" data-des="全部">
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
            <label class="weui-cell weui-check__label" for="x12">
                <div class="weui-cell__bd">
                    <p>前十五排名</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" name="radio1" class="weui-check" id="x12" value="15" data-des="前十五">
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
            <label class="weui-cell weui-check__label" for="x13">
                <div class="weui-cell__bd">
                    <p>前三十排名</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" name="radio1" class="weui-check" id="x13" value="30" data-des="前三十">
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
            <label class="weui-cell weui-check__label" for="x14">
                <div class="weui-cell__bd">
                    <p>前五十排名</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" name="radio1" class="weui-check" id="x14" value="50" data-des="前五十">
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
        </div>
    </div>
</div>

<script src="${request.contextPath}/js/wx/js/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/wx/js/jquery-weui/js/jquery-weui.min.js"></script>
<script src="${request.contextPath}/js/wx/js/handlebars.js"></script>
<script src="${request.contextPath}/js/wx/js/swiper.js"></script>
<script src="${request.contextPath}/js/wx/js/mobiscroll/mobiscroll.zcore.js"></script>
<script src="${request.contextPath}/js/wx/js/lrz.all.bundle.js"></script>
<script src="${request.contextPath}/js/wx/js/director.min.js"></script>
<script src="${request.contextPath}/js/wx/js/iscroll/4.2.5/iscroll.js"></script>
<script src="${request.contextPath}/js/wx/js/iscroll/4.2.5/pullToRefresh.js"></script>
<script src="${request.contextPath}/js/wx/js/wxzui_common.js"></script>
<script>

srvMap.add("json", "${request.contextPath}/js/wx/json/tableData3.json", "${request.contextPath}/kcsj/wx");

var clickFlag = 0,
    pageHeight = $(window).height() - 65,
    zrouter = null,
    cdate = '',
    curSection = null,
    startdate = '',
    enddate = '',
    orgTempJson = [],
    orgTempJsonS = [],
    curOrgList = [],
    zInterTimer = null,
    tempDetailJson = null,
    tempStoreJson = [],
    tempStoreDateJson = [],
    p_getStoreId = '',
    sortdata = 'stype=desc&sfield=frequency',
    G_json = null,
    curRankType = parseInt(Util.lStorage.getParam("curRankType") || 1, 10),
    curWordId = '',
    curWordTit = '';

$(function(){

    var routerObj = {
        '/J_router_detail': {
            on: function(){
                curSection = $(".J_router_detail .J_content");
                setTimeout(function(){
                    showDetail();
                }, 30)
            },
            after: function(){
                curSection.html('');
                curSection = $(".J_router_main .J_content");
            },
            once: function(){
                var _h = $(window).height(),
                    _h1 = $(".J_header").height(),
                    _h2 = _h - _h1 - 12;
                $("#J_swiperWrap").css({"height": _h2, "overflow": "auto"});
            }
        },
        '/J_router_search': {
            once: function(){
                setTimeout(function(){
                    showSearch();
                }, 30)
            },
            on: function(){
                curSection = $(".J_router_search .J_content");
            },
            after: function(){
                curSection = $(".J_router_main .J_content");
            }
        },
        '/J_router_main': {
            once: function(){
                setTimeout(function(){
                    initPage();
                }, 30)
            },
            on: function(){
                curSection = $(".J_router_main .J_content");
            }
        }
    }
    initZrouter(routerObj, function(router){
        zrouter = router;
    });
})

function initPage(){
    $("#wrapper").css({"height": pageHeight});

    var curDate = new Date(),
        _m = ((curDate.getMonth()+1)<10) ? ("0"+(curDate.getMonth()+1)) : (curDate.getMonth()+1),
        firstdate = curDate.getFullYear() + "-"+ _m + "-01";

    startdate = firstdate;
    enddate = getCurDate(curDate);

    $("#J_showbefore").off("click").on("click", function(){
        $("#J_statusPop").popup();
    })
    $("#J_statusPop").off("click").on("click", "input[name='radio1']", function(){
        var _this = $(this),
            _txt = _this.data("des");
            _val = parseInt(_this.val(), 10);
        if(_val == 1){
            _val = G_json.length;
        }
        $("#J_showbefore span").text(_txt);
        $("#J_tbl_body").html('');
        $.closePopup();

        curRankType = _val;
        Util.lStorage.setParam("curRankType", _val);

        setTimeout(function(){
            var G_jsonS = $.extend([], G_json);
            G_jsonS = G_jsonS.splice(0, _val);
            $("#J_tbl_body").temp($("#T_tbl_body").val(), G_jsonS);
            initSbtns();
        }, 30)
    })

    if(curRankType){
        var _obj = $("#J_statusPop").find("input[value='"+curRankType+"']"),
            _txt = _obj.data("des");
        $("#J_showbefore span").text(_txt);
        $("#J_statusPop").find("input[name='radio1']").removeAttr("checked");
        _obj.prop("checked",true);
    }

    getZuiStore();
}

function initzBtns(){
    curSection.find(".zcell").off("click").on("click", function(){
        tempDetailJson = $(this).data("json");
        zrouter.setRoute('J_router_detail');
    })
}

function showDetail(){
    $(".J_router_detail .J_header-title").text('详情');
    createLoading();
    $.PostJson("${request.contextPath}/fxlhyydc/byid.do", "treeid="+curWordId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data && json.data.content){
                    var content = json.data.content;
                    content = JSON.parse(content);
                    $("#J_swiperWrap").temp($("#T_swiperWrap").val(), content.list);
                    $(".J_router_detail .J_header-title").text(curWordTit);
                }else{
                    zuiTotast("暂未编辑文章", 2);
                    $("#J_swiperWrap").html('<p class="J_noword">暂未编辑文章</p>');
                }
            }else{
                MyFun.to.e(json.message || "查询文章信息失败");
            }
        }
        unblockLoading();
    })
}

function showSearch(){
    curOrgList = [];
    curSection.html($("#T_search").val());

    p_getStoreId = tempStoreJson[0].id;
    $("#zuiStoreId").temp($("#T_zuiStoreId").val(), tempStoreJson);

    $("#zuiStoreId").off("change").on("change", function(){
        p_getStoreId = $("#zuiStoreId").val();
        getStoreFileDate(1);
    })

    if(tempStoreDateJson.length){
        $(".J_zuiStoreDate").temp($("#T_zuiStoreDate").val(), tempStoreDateJson);
        var _index = (len<2) ? 0 : (len<3) ? 1 : 2;
        $("#J_startdate").find("option:eq("+_index+")").attr({"selected":"selected"});
        $("#J_enddate").find("option:eq(0)").attr({"selected":"selected"});
        $(".J_main_dateWrap span:eq(0)").text(startdate);
        $(".J_main_dateWrap span:eq(1)").text(enddate);
        $(".J_main_dateWrap").removeClass("fn-hide");
    }

    $("#J_startdate").off("change").on("change", function(){
        startdate = $("#J_startdate").val();
        judgeFileDate();
    })
    $("#J_enddate").off("change").on("change", function(){
        enddate = $("#J_enddate").val();
    })
    judgeFileDate();

    $("#J_restForm").off("click").on("click", function(){
        showSearch();
    })

    $("#J_subData").off("click").on("click", function(){
        window.history.back();
        setTimeout(function(){
            getDetail();
        }, 200)
        return false;
    })
}

function getZuiStore(){
    createLoading();
    $.PostJson("${request.contextPath}/store/stores.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.totalRows !== 0){
                p_getStoreId = $("#zuiStoreId").val();
                tempStoreJson = json.data;
                p_getStoreId = tempStoreJson[0].id;

                getStoreFileDate();
            }else{
                zuiAlert(json.message || "查询药店列表失败");
            }
        }
        unblockLoading();
    })
}

function getStoreFileDate(type){
    createLoading();
    $.PostJson("${request.contextPath}/fxsalemonth/monthsbystoreid.do", "storeid="+p_getStoreId, function(state, json){
        if(state == 'success'){
            if(json){
                tempStoreDateJson = json.data;
                var len = json.data.length;
                if(len){
                    /**
                     * 切换门店，获取当前门店销售日期
                    **/
                    $(".J_main_dateWrap").removeClass("fn-hide");
                    if(type){
                        $(".J_zuiStoreDate").temp($("#T_zuiStoreDate").val(), tempStoreDateJson);
                    }
                    var _index = (len<2) ? 0 : (len<3) ? 1 : 2;
                    startdate = tempStoreDateJson[_index];
                    enddate = tempStoreDateJson[0];
                    if(type){
                        $("#J_startdate").find("option:eq("+_index+")").attr({"selected":"selected"});
                        $("#J_enddate").find("option:eq(0)").attr({"selected":"selected"});
                    }
                    $(".J_main_dateWrap span:eq(0)").text(startdate);
                    $(".J_main_dateWrap span:eq(1)").text(enddate);

                    if(!type){
                        setTimeout(function(){
                            getDetail();
                        }, 200)
                    }
                }else{
                    zuiAlert(json.message || "暂无药店销售数据");
                    startdate = "";
                    enddate = "";
                    $(".J_zuiStoreDate").temp($("#T_zuiStoreDate").val(), tempStoreDateJson);
                    $(".J_main_dateWrap").addClass("fn-hide");
                }
            }else{
                MyFun.to.e(json.message || "查询药店销售日期失败");
            }
        }
        unblockLoading();
    })
}

function getDetail(){
    $("#J_tbl_body").html("");

    createLoading();
    $.PostJson("${request.contextPath}/fxorderfx/table2.do", sortdata+"&storeid="+p_getStoreId+"&startdate="+startdate+"&enddate="+enddate, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data && json.data.length){
                    G_json = json.data;
                    if(curRankType && curRankType !== 1){
                        var tempArr = $.extend([], G_json);
                        $("#J_tbl_body").temp($("#T_tbl_body").val(), tempArr.splice(0, curRankType));
                    }else{
                        $("#J_tbl_body").temp($("#T_tbl_body").val(), json.data);
                    }
                    initSbtns();
                }else{
                    $("#J_tbl_body").html('<tr><td colspan="7" style="text-align: center;">暂无数据</td></tr>');
                }
            }else{
                zuiAlert(json.message || "查询失败");
            }
        }
        unblockLoading();
    })
}

function initSbtns(){
    $("#J_tbl_body").off("click").on("click", ".J_showDetail", function(){
        curWordId = $(this).data("id");
        curWordTit = $(this).data("tit");
        zrouter.setRoute('J_router_detail');
    })
}

Handlebars.registerHelper('retFixS', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = _val.toFixed(2);
    return _val+'%';
});

Handlebars.registerHelper('retFixSD', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = Math.round(_val)
    return _val;
});

function judgeFileDate(){
    var s1 = startdate,
        s2 = enddate,
        obj2 = $("#J_enddate"),
        opt2 = obj2.find("option");
    s1 = zdate2Int(s1);
    s2 = zdate2Int(s2);

    if(s2 < s1){
        // obj2.val(startdate).trigger("change");
        console.log("99")
        obj2.val(startdate).find("option[value='"+startdate+"']").attr({"selected": "selected"}).siblings("option").removeAttr("selected");
        enddate = startdate;
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
}
// 2018-03 --- 201803
function zdate2Int(date){
    return parseInt(date.split("-").join(""), 10);
}
</script>
</body>
</html>