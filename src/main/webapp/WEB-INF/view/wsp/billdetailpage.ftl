<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>订单详情</title>
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
.J_list .item .weui-flex__item p.price {
    color: #FF125D;
}
</style>
</head>
<body class="zui">

<header class="J_header">
    <a class="J_header_back" href="javascript:;"><i class="fa fa-angle-left"></i></a>
    <h1 class="J_header-title">订单详情</h1>
</header>

<div class="J_content" id="J_content">
    <div class="J_list">
        <div class="weui-panel weui-panel_access zui_iscroll_block" id="J_wxcontent">
            <div class="zuiDrugList">
                
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_wxcontent">
{{#each this}}
<div class="item">
    <div class="weui-panel__hd">
        <div class="weui-flex">
            <div class="weui-flex__item">{{name}}</div>
            <span class="status s_1">已接收</span>
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
                <p class="detail">{{updatetime}}</p>
            </div>
        </div>
    </div>
</div>
{{/each}}
</textarea>

<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/js/jquery-weui.min.js"></script>
<script src="${request.contextPath}/js/plugins/iscroll/4.2.5/iscroll.js"></script>
<script src="${request.contextPath}/js/plugins/iscroll/4.2.5/pullToRefresh.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.js?v=201802121203"></script>
<script>

srvMap.add("billdetail", "success.json", "${request.contextPath}/ws/billdetail.do");//获取订单详情

var clickFlag = 0,
    loadingMore = false,//加载更多
    currentPage = 1,
    pageSize = 10,
    billid = "";

refresher.init({
    id: "J_wxcontent",
    item: ".zuiDrugList",
    pullDownAction: Refresh,
    pullUpAction: Load
});

$(function(){

    billid = getQueryString("billid") || "";
    
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
    $.PostJson(srvMap.get("billdetail"), "billid="+billid+"&pageSize="+pageSize+"&currentPage="+currentPage, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.totalRows){
                    var dataLen = json.data.data.length;
                    if(dataLen){
                        $("#J_wxcontent .zuiDrugList").tempAppend($("#T_wxcontent").val(), json.data.data);
                    }

                    if($("#J_wxcontent .item").length === json.data.totalRows){
                        currentPage = 0;
                        loadingMore = true;
                        
                        $("#J_wxcontent .zuiDrugList").showNoMoreData();
                        $(".zui_iscroll_block .pullUp").addClass("fn-hide_hard");

                    }else{
                        currentPage++;
                        loadingMore = false;
                    }

                    /*$("#J_wxcontent").off("click").on("click", ".item", function(){
                        var _id = $(this).data("id"),
                            _price = $(this).data("price"),
                            _name = $(this).data("name");
                        window.location.href = 'drugcontent.do?id='+_id+'&price='+_price+'&name='+_name;
                    })*/
                }else{
                    $("#J_wxcontent .zuiDrugList").showNullData();
                    $(".zui_iscroll_block .pullUp").addClass("fn-hide_hard");
                }
            }else{
                zuiAlert(json.message || "查询订单详情失败");
            }
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        J_wxcontent.refresh();
    })
}

</script>
</body>
</html>