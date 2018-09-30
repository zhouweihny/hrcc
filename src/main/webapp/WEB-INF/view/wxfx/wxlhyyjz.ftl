<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>进展评估</title>
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/jquery-weui/css/jquery-weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/css/font-awesome.css?v=4.4.0">
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/mobiscroll/mobiscroll.zcore.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/js/iscroll/4.2.5/pullToRefresh.css">
<link rel="stylesheet" href="${request.contextPath}/js/wx/css/wxzui_common.css?v=201808212115">
<style>
.weui-label {
    width: 3.5rem;
    color: #555;
}
.J_router_search_detail .J_content .weui-cell .weui-cell__hd {
    border-right: 0;
}
.J_rootname {
    background: #f9f9f9;
    border-right: 1px solid #e7e7e7;
}
#J_zWrap .data-table .t_r table {
    width: 450px;
}
#J_zWrap th, #J_zWrap td {
    font-size: .6rem;
}
</style>
</head>
<body class="zui nofooter">

<section class="fn-hide J_router_main">
    <header class="J_header">
        <a class="J_header_back" href="javascript:;"><i class="fa fa-angle-left"></i></a>
        <h1 class="J_header-title">进展评估</h1>
        <a class="J_header_right_icon" href="#/J_router_search">筛选</a>
    </header>

    <div class="J_content" id="wrapper">
        <div class="J_main_dateWrap" id="J_showbefore">
            <span>周进展</span>
            <i class="fa fa-caret-down" aria-hidden="true" title=""></i>
        </div>
        <div id="J_zWrap">
            
        </div>
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

    <div class="J_content">
        
    </div>
</section>

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
</div>
</textarea>

<textarea class="fn-hide" id="T_tbl_body">
<div class="data-table">
    <div class="t_l">
        <table>
            <tbody>
                <tr>
                    <th rowspan="2" class="J_t_l_tit">节点</th>
                </tr>
            </tbody>
        </table>
        <div class="t_l_freeze">
            <table>
                {{#each this}}
                <tr>
                    <td class="J_leftTit">
                        {{lv2}}
                        <br>
                        <span>（{{lv1}}）</span>
                    </td>
                </tr>
                {{/each}}
            </table>
        </div>
    </div>
    <div class="t_r">
        <div class="t_r_t">
            <table>
                <tbody>
                    <tr role="row">
                        <th>时间</th>
                        <th>订单数</th>
                        <th>联合用药<br>订单数</th>
                        <th>销售额</th>
                        <th>平均客<br>单价</th>
                        <th>联合用药平均客单价</th>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="t_r_content" id="t_r_content">
            <table>
                <tbody>
                    {{#each this}}
                    <tr class="gradeX">
                        <td>前一周</td>
                        <td class="fn-text-right">{{zbs1}}</td>
                        <td class="fn-text-right">{{bs1}}</td>
                        <td class="fn-text-right">{{retFixSD xse1}}</td>
                        <td class="fn-text-right">{{retFixSD kdj1}}</td>
                        <td class="fn-text-right">{{retFixSD avdj1}}</td>
                    </tr>
                    <tr class="gradeX">
                        <td>前二周</td>
                        <td class="fn-text-right">{{zbs2}}</td>
                        <td class="fn-text-right">{{bs2}}</td>
                        <td class="fn-text-right">{{retFixSD xse2}}</td>
                        <td class="fn-text-right">{{retFixSD kdj2}}</td>
                        <td class="fn-text-right">{{retFixSD avdj2}}</td>
                    </tr>
                    <tr class="gradeX">
                        <td>前三周</td>
                        <td class="fn-text-right">{{zbs3}}</td>
                        <td class="fn-text-right">{{bs3}}</td>
                        <td class="fn-text-right">{{retFixSD xse3}}</td>
                        <td class="fn-text-right">{{retFixSD kdj3}}</td>
                        <td class="fn-text-right">{{retFixSD avdj3}}</td>
                    </tr>
                    <tr class="gradeX">
                        <td>前四周</td>
                        <td class="fn-text-right">{{zbs4}}</td>
                        <td class="fn-text-right">{{bs4}}</td>
                        <td class="fn-text-right">{{retFixSD xse4}}</td>
                        <td class="fn-text-right">{{retFixSD kdj4}}</td>
                        <td class="fn-text-right">{{retFixSD avdj4}}</td>
                    </tr>
                    {{/each}}
                </tbody>
            </table>
        </div>
    </div>
</div>
</textarea>

<textarea class="fn-hide" id="T_zuiStoreId">
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_tbl_bodyS">
<div class="data-table">
    <div class="t_l">
        <table>
            <tbody>
                <tr>
                    <th rowspan="2" class="J_t_l_tit">节点</th>
                </tr>
            </tbody>
        </table>
        <div class="t_l_freeze">
            <table>
                {{#each this}}
                <tr>
                    <td class="J_leftTit">
                        {{lv2}}
                        <br>
                        <span>（{{lv1}}）</span>
                    </td>
                </tr>
                {{/each}}
            </table>
        </div>
    </div>
    <div class="t_r">
        <div class="t_r_t">
            <table>
                <tbody>
                    <tr role="row">
                        <th>时间</th>
                        <th>订单数</th>
                        <th>联合用药<br>订单数</th>
                        <th>销售额</th>
                        <th>平均客<br>单价</th>
                        <th>联合用药平均客单价</th>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="t_r_content" id="t_r_content">
            <table>
                <tbody>
                    {{#each this}}
                    <tr class="gradeX">
                        <td>前一月</td>
                        <td class="fn-text-right">{{zbs1}}</td>
                        <td class="fn-text-right">{{bs1}}</td>
                        <td class="fn-text-right">{{retFixSD xse1}}</td>
                        <td class="fn-text-right">{{retFixSD avdj1}}</td>
                        <td class="fn-text-right">{{retFixSD kdj1}}</td>
                    </tr>
                    <tr class="gradeX">
                        <td>前二月</td>
                        <td class="fn-text-right">{{zbs2}}</td>
                        <td class="fn-text-right">{{bs2}}</td>
                        <td class="fn-text-right">{{retFixSD xse2}}</td>
                        <td class="fn-text-right">{{retFixSD avdj2}}</td>
                        <td class="fn-text-right">{{retFixSD kdj2}}</td>
                    </tr>
                    <tr class="gradeX">
                        <td>前三月</td>
                        <td class="fn-text-right">{{zbs3}}</td>
                        <td class="fn-text-right">{{bs3}}</td>
                        <td class="fn-text-right">{{retFixSD xse3}}</td>
                        <td class="fn-text-right">{{retFixSD avdj3}}</td>
                        <td class="fn-text-right">{{retFixSD kdj3}}</td>
                    </tr>
                    {{/each}}
                </tbody>
            </table>
        </div>
    </div>
</div>
</textarea>

<div id="J_statusPop" class="weui-popup__container popup-bottom">
    <div class="weui-popup__overlay"></div>
    <div class="weui-popup__modal">
        <div class="weui-cells weui-cells_radio">
            <label class="weui-cell weui-check__label" for="x11">
                <div class="weui-cell__bd">
                    <p>周进展</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" class="weui-check" name="radio1" id="x11" checked="checked" value="1" data-des="周进展">
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
            <label class="weui-cell weui-check__label" for="x12">
                <div class="weui-cell__bd">
                    <p>月进展</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" name="radio1" class="weui-check" id="x12" value="2" data-des="月进展">
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
<script src="${request.contextPath}/js/wx/js/iscroll/5.1.3/iscroll-probe.min.js"></script>
<script src="${request.contextPath}/js/wx/js/wxzui_common.js?v=201808212114"></script>
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
    G_json = null,
    curRankTypeS = parseInt(Util.lStorage.getParam("curRankTypeS") || 1, 10);

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

    $("#J_showbefore").off("click").on("click", function(){
        $("#J_statusPop").popup();
    })
    $("#J_statusPop").off("click").on("click", "input[name='radio1']", function(){
        var _this = $(this),
            _txt = _this.data("des");
            _val = parseInt(_this.val(), 10);
        $("#J_showbefore span").text(_txt);
        $("#J_tbl_body").html('');
        $.closePopup();

        curRankTypeS = _val;
        Util.lStorage.setParam("curRankTypeS", _val);

        setTimeout(function(){
            getDetail();
        }, 30)
    })

    if(curRankTypeS){
        var _obj = $("#J_statusPop").find("input[value='"+curRankTypeS+"']"),
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
    if(!tempDetailJson){
        showResDes(pageHeight, curSection);
        return false;
    }
    curSection.css({"height": pageHeight, "overflow-y": "auto"});
    curSection.temp($("#T_detail").val(), tempDetailJson);
    $(".J_router_detail .J_header-title").text(tempDetailJson.goodsname);
}

function showSearch(){
    curOrgList = [];
    curSection.html($("#T_search").val());

    p_getStoreId = tempStoreJson[0].id;
    $("#zuiStoreId").temp($("#T_zuiStoreId").val(), tempStoreJson);

    $("#zuiStoreId").off("change").on("change", function(){
        p_getStoreId = $("#zuiStoreId").val();
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

                setTimeout(function(){
                    getDetail();
                }, 200)
            }else{
                zuiAlert(json.message || "查询药店列表失败");
            }
        }
        unblockLoading();
    })
}

function getDetail(){
    $("#J_tbl_body").html("");
    createLoading();

    if(curRankTypeS == 1){
        $.PostJson("${request.contextPath}/fxorderfx/zztable.do", "storeid="+p_getStoreId, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    if(json.data && json.data.length){
                        $("#J_zWrap").temp($("#T_tbl_body").val(), json.data);
                        initzScroll(function(){
                            var _J_leftTit = $(".J_leftTit"),
                                _t_r_content = $(".t_r_content tr");
                            _J_leftTit.each(function(i){
                                var _this = $(this),
                                    _h = 0;
                                for(var k=4*i; k<(4*(i+1)); k++){
                                    _h += _t_r_content.eq(k).height();
                                }
                                _this.css({"height": _h});
                            })
                        });
                    }else{
                        $("#J_zWrap").html('<p style="text-align: center;height: 150px;line-height: 150px;">暂无数据</p>');
                    }
                }else{
                    zuiAlert(json.message || "查询失败");
                }
            }
            unblockLoading();
        })
    }else{
        $.PostJson("${request.contextPath}/fxorderfx/zmtable.do", "storeid="+p_getStoreId, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    if(json.data && json.data.length){
                        $("#J_zWrap").temp($("#T_tbl_bodyS").val(), json.data);
                        initzScroll(function(){
                            var _J_leftTit = $(".J_leftTit"),
                                _t_r_content = $(".t_r_content tr");
                            _J_leftTit.each(function(i){
                                var _this = $(this),
                                    _h = 0;
                                for(var k=3*i; k<(3*(i+1)); k++){
                                    _h += _t_r_content.eq(k).height();
                                }
                                _this.css({"height": _h});
                            })
                        });
                    }else{
                        $("#J_zWrap").html('<p style="text-align: center;height: 150px;line-height: 150px;">暂无数据</p>');
                    }
                }else{
                    zuiAlert(json.message || "查询失败");
                }
            }
            unblockLoading();
        })
    }
}

Handlebars.registerHelper('retFixSD', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = Math.round(_val)
    return _val;
});

</script>
</body>
</html>