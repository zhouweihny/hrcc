<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>采购订单</title>
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/css/jquery-weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/css/font-awesome.css?v=4.4.0">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/iscroll/4.2.5/pullToRefresh.css" />
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.css?v=201511171953">
<style>

body.zui, .J_list .weui-panel {
    background: #F8F8F8;
}
#J_code_btn {
    font-size: 14px;
    width: 90px;
}
.zui .J_content .title {
    text-align: center;
}
.zui .J_content .title img {
    display: block;
    margin: 1.2rem auto;
    width: 4rem;
    height: 4rem;
    border-radius: 50%;
    border: 1px solid #ddd;
    padding: 6px;
    box-shadow: 1px 3px 5px #ddd;
}
.zui .J_content .weui-label {
    font-size: .65rem;
}
#J_bind_btn {
    border-radius: 1.5rem;
    margin-top: 0.5rem;
}
.J_list .item .name {
    color: #000;
    font-size: .7rem;
}
.J_list .item .item_r {
    width: 3rem;
    overflow: hidden;
}
.J_list .item .weui-flex__item p, .J_list .item .item_r p {
    margin: 6px 0;
}
.J_list .item .weui-flex__item p {
    padding: 0 10px 0 0;
}
.J_list .item .item_r .num {
    color: #FF125D;
}
.J_list .item .item_r p.des span {
    text-align:justify;
    text-justify:distribute-all-lines;/*ie6-8*/
    text-align-last:justify;/* ie9*/
    -moz-text-align-last:justify;/*ff*/
    -webkit-text-align-last:justify;/*chrome 20+*/
    display: inline-block;
    width: 70%;
}
.J_list .item .weui-panel__hd {
    color: #555;
    font-size: .7rem;
}
.J_list .item .weui-media-box__desc, .J_list .item .weui-panel__ft {
    color: #000;
    font-size: .65rem;
}
.J_list .item .status {
    
}
.J_list .item .status.s_1 {
    color: #1292FF;
}
.J_list .item p.detail .remark {
    color: #FF125D;
    display: inline-block;
    margin-left: 15px;
}
.zui_iscroll_block .item {
    margin-bottom: .7rem;
    background: #fff;
    box-shadow: 0 4px 5px #ddd;
}
.J_zuisubPage {
    z-index: 1000;
}
.J_zuisubPage .weui-popup__modal {
    -webkit-transform: translateX(100%);
    transform: translateX(100%);
}
.J_zuisubPage.weui-popup__container--visible .weui-popup__modal {
    -webkit-transform: translate(0);
    transform: translate(0);
}
#J_wxcontent_S {
    overflow: auto;
}
#J_wxcontent_S:after {
    height: 0;
    -webkit-transform: scaleY(0);
    transform: scaleY(0);
}
.J_status_btn {
    background: #04BE02;
    color: #fff;
    padding: 3px 14px;
    font-size: .5rem;
    border-radius: 3px;
    text-align: center;
    height: 26px;
    line-height: 20px;
}
.color-primary-bg {background-color: #04BE02;}
.color-warning-bg {background-color: #f60;}
.color-danger-bg {background-color: #f6383a;}
.color-blue-bg {background-color: #007BDB;}
.color-blue {color: #007BDB;}
.J_list .item .status.s_0 {color: #04BE02;}
.J_list .item .status.s_1 {color: #f60;}
.J_list .item .status.s_2 {color: #f6383a;}
.J_list .item .status.s_3 {color: #007BDB;}

</style>
</head>
<body class="zui hasFooter">

<header class="J_header">
    <h1 class="J_header-title">采购订单</h1>
</header>

<div class="J_content" id="J_content">
    <div class="J_list">
        <div class="weui-panel weui-panel_access zui_iscroll_block" id="J_wxcontent">
            <div class="zuiDrugList">
                
            </div>
        </div>
    </div>
    <div class="weui-tabbar">
        <a href="javascript:;" class="weui-tabbar__item">
            <div class="weui-tabbar__icon">
                <i class="fa fa-rss"></i>
            </div>
            <p class="weui-tabbar__label">询价单</p>
        </a>
        <a href="javascript:;" class="weui-tabbar__item weui-bar__item--on">
            <div class="weui-tabbar__icon">
                <i class="fa fa-gg-circle"></i>
            </div>
            <p class="weui-tabbar__label">采购订单</p>
        </a>
        <a href="binding.do" class="weui-tabbar__item">
            <div class="weui-tabbar__icon">
                <i class="fa fa-user"></i>
            </div>
            <p class="weui-tabbar__label">个人中心</p>
        </a>
    </div>
</div>

<div id="J_zuisubPage" class="weui-popup__container J_zuisubPage">
    <div class="weui-popup__overlay"></div>
    <div class="weui-popup__modal">
        <header class="J_header">
            <a class="J_header_back customized close-popup" href="javascript:;" id="J_closeZuiSubPage"><i class="fa fa-angle-left"></i></a>
            <h1 class="J_header-title">订单详情</h1>
        </header>

        <div class="J_content">
            <div class="J_list">
                <div class="weui-panel weui-panel_access zui_iscroll_block" id="J_wxcontent_S">
                    <div class="zuiDrugList">
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_subPage">
{{#each this}}
<div class="item">
    <div class="weui-panel__hd">
        <div class="weui-flex">
            <div class="weui-flex__item">{{name}}</div>
            <a href="javascript:;" class="J_status_btn {{retNewColor status 1}}">
                <span>{{retNewColor status 2}}</span>
            </a>
        </div>
    </div>
    <div class="weui-panel__bd">
        <div class="weui-media-box__bd">
            <div class="weui-media-box__desc">
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>规格</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail">{{specifications}}</p>
                    </div>
                </div>
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>厂商</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail">{{factory}}</p>
                    </div>
                </div>
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>数量</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail">{{num}}{{unit}}</p>
                    </div>
                </div>
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>价格</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail price">￥{{price}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="weui-panel__ft">
        <div class="weui-flex">
            <div class="item_r">
                <p class="des"><span>备注</span>：</p>
            </div>
            <div class="weui-flex__item">
                <p class="detail">{{remark}}</p>
            </div>
        </div>
    </div>
</div>
{{/each}}
</textarea>

<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/js/jquery-weui.min.js"></script>
<script src="${request.contextPath}/js/plugins/iscroll/4.2.5/iscroll.js"></script>
<script src="${request.contextPath}/js/plugins/iscroll/4.2.5/pullToRefresh.js?v=201802090925"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.js?v=201802121203"></script>
<script>

srvMap.add("billpage_item", "success.json", "${request.contextPath}/wsp/billpageitem.do");//获取订单
srvMap.add("billdetail", "success.json", "${request.contextPath}/wsp/billdetail.do");//获取订单详情

var clickFlag = 0,
    loadingMore = false,//加载更多
    currentPage = 1,
    pageSize = 10,
    billid = "",
    curBillObj = null;
var W_drugStatus_des = ["有货", "缺货", "断货", "近效期"],
    W_drugStatus_class = ["color-primary-bg", "color-warning-bg", "color-danger-bg", "color-blue-bg"];

refresher.init({
    id: "J_wxcontent",
    item: ".zuiDrugList",
    subPopPage: true,
    pullDownAction: Refresh,
    pullUpAction: Load
});

$(function(){

    getCategory();
})

function Refresh() {
    loadingMore = false;
    currentPage = 1;
    $("#J_wxcontent .zuiDrugList").html("");
    $(".showNoMoreData").hide();
    $(".zui_iscroll_block .pullUp").removeClass("fn-hide_hard");
    getCategory();
}

function Load() {
    if(loadingMore){
        J_wxcontent.refresh();
        $(".zui_iscroll_block .pullUp").addClass("fn-hide_hard");
        return;
    }
    loadingMore = true;
    getCategory();
}

function getCategory(){
    $.AjaxHtml(srvMap.get("billpage_item"), "pageSize="+pageSize+"&currentPage="+currentPage, function(state, html){
        if(state == 'success'){
            if(html){
                $("#J_wxcontent .zuiDrugList").append(html);

                currentPage++;
                loadingMore = false;
            }else{
                currentPage = 0;
                loadingMore = true;
                
                $("#J_wxcontent .zuiDrugList").showNoMoreData();
                $(".zui_iscroll_block .pullUp").addClass("fn-hide_hard");
            }

            $("#J_wxcontent").off("click").on("click", ".item", function(e){
                var _this = $(this);
                curBillObj = _this;
                billid = _this.data("id");
                getSubPage();
            })
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        J_wxcontent.refresh();
    })
}

function getSubPage(){
    $("#J_zuisubPage").popup();
    $("#J_wxcontent_S .zuiDrugList").html("");

    if(clickFlag)
        return false;
    clickFlag = 1;
    createLoading();
    $.PostJson(srvMap.get("billdetail"), "billid="+billid+"&pageSize=9999&currentPage=1", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.totalRows){
                    var dataLen = json.data.data.length;
                    if(dataLen){
                        $("#J_wxcontent_S .zuiDrugList").tempAppend($("#T_subPage").val(), json.data.data);
                    }
                }else{
                    $("#J_wxcontent_S .zuiDrugList").showNullData();
                }
            }else{
                zuiAlert(json.message || "查询订单详情失败");
            }
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        clickFlag = 0;
        unblockLoading();
    })
}

Handlebars.registerHelper('retNewColor', function(status, type) {
    if(status == '2' || status == '3'){
        if(type == '1'){
            return W_drugStatus_class[status];
        }else{
            return W_drugStatus_des[status];
        }
    }
    return '';
});
</script>
</body>
</html>