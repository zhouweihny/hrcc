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
.J_list .item .weui-flex__item p.price {
    color: #FF125D;
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
.weui-mask_transparent, .weui-toast.weui-toast--visible {
    z-index: 10001;
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
.J_list .item .weui-flex__item .detail .fa {
    vertical-align: middle;
    display: inline-block;
}
.J_list .item .J_ftime .item_r {
    width: 4.8rem;
}
.J_list .item .J_ftime .weui-flex__item .detail {
    position: relative;
}
.J_list .item .weui-flex__item .detail .fa-calendar {
    position: absolute;
    left: 0;
    top: 0;
    display: block;
    width: 22px;
    text-align: center;
    height: 24px;
    line-height: 24px;
}
.J_list .item .J_ftime .J_date {
    font-family: \5fae\8f6f\96c5\9ed1, Verdana, Arial, \5b8b\4f53, sans-serif;
    display: inline-block;
    width: 100%;
    height: 22px;
    padding-left: 26px;
}
.J_list .item:active .J_date, .J_list .item .J_ftime .J_date:active {
    background: #ececec;
}
.zui .J_header .J_header_right_icon .fa {
    font-size: 1rem;
}
.zui .J_ftime_wrap {
    background: #fff;
    margin-top: 10px;
}
.zui .J_ftime_wrap .J_ftime {
    padding: 10px 15px;
}
.zui .J_ftime_wrap .J_ftime .item_r {
    width: 4.8rem;
}
#J_wxcontent_S:after {
    height: 0;
    -webkit-transform: scaleY(0);
    transform: scaleY(0);
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
.J_sureGoods_btn {
    color: #019589;
}
.zui .J_header .J_header_right_icon {
    width: 3.6rem;
}
</style>
</head>
<body class="zui hasFooter">

<header class="J_header">
    <a class="J_header_back customized close-popup" href="billpage.do" id="J_closeZuiSubPage"><i class="fa fa-angle-left"></i></a>
    <h1 class="J_header-title">订单详情</h1>
    <a class="J_header_right_icon J_sureGoods_btn" href="javascript:;" id="J_sureGoods_btn">
        发货
    </a>
</header>

<div class="J_content" id="J_content">
    <div class="J_list">
        <div class="weui-panel weui-panel_access zui_iscroll_block" id="J_wxcontent_S">

            <div class="J_ftime_wrap">
                <div class="weui-flex J_ftime">
                    <div class="item_r">
                        <p class="des">预计到货时间：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail">
                            <i class="fa fa-calendar" aria-hidden="true"></i>
                            <input class="J_date" type="text" value="" data-toggle='date'>
                        </p>
                    </div>
                </div>
            </div>
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
        <a href="billpage.do" class="weui-tabbar__item weui-bar__item--on">
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

<textarea class="fn-hide" id="T_subPage">
{{#each this}}
<div class="item">
    <div class="weui-panel__hd">
        <div class="weui-flex">
            <div class="weui-flex__item">{{name}}</div>
            <a href="javascript:;" class="J_status_btn {{retNewColor status 1}}" data-id="{{id}}" data-name="{{name}}" data-type="{{status}}">
                <span>{{retNewColor status 2}}</span>
                <i class="fa fa-caret-down" aria-hidden="true"></i>
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
                <div class="weui-flex J_remark" data-id="{{id}}" data-name="{{name}}">
                    <div class="item_r">
                        <p class="des"><span>备注</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail">
                            {{#if remark}}
                            {{remark}}
                            {{else}}
                            <i class="fa fa-edit" aria-hidden="true"></i>
                            {{/if}}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
{{/each}}
</textarea>

<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/js/jquery-weui.min.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.js?v=201802121203"></script>
<script>

srvMap.add("billpage_item", "success.json", "${request.contextPath}/ws/billpageitem.do");//获取订单
srvMap.add("arrivaltime", "success.json", "${request.contextPath}/bill/arrivaltime.do");//获取订单
srvMap.add("billdetail", "success.json", "${request.contextPath}/ws/billdetail.do");//获取订单详情
srvMap.add("save", "success.json", "${request.contextPath}/bill/save.do");//确认订单
srvMap.add("update", "success.json", "${request.contextPath}/billdetail/update.do");//确认订单

var clickFlag = 0,
    loadingMore = false,//加载更多
    currentPage = 1,
    pageSize = 10,
    billid = "",
    calendarFirstClick = 1;
    calendarPickFlag = 0;
    billAvtime = null,
    curBillObj = null,
    curBillStatus = '0';
var W_drugStatus_des = ["有货", "缺货", "断货", "近效期"],
    W_drugStatus_color = ["#04BE02", "#f60", "#f6383a", "#007BDB"]
    W_drugStatus_class = ["color-primary-bg", "color-warning-bg", "color-danger-bg", "color-blue-bg"];

$(function(){

    billid = getQueryString("billid") || "";
    curBillStatus = '1';//推送新订单

    getSubPage();

})

function getSubPage(){
    $("#J_wxcontent_S .zuiDrugList").html("");
    calendarPickFlag = 1;

    var _J_sureGoods_btn = $("#J_sureGoods_btn");
    if(curBillStatus == '1'){
        _J_sureGoods_btn.removeClass("fn-hide").text("确认订单");
    }else if(curBillStatus == '2'){
        _J_sureGoods_btn.removeClass("fn-hide").text("发货");
    }else{
        _J_sureGoods_btn.addClass("fn-hide");
    }

    if(curBillStatus == '1'){
        $(".J_ftime_wrap").removeClass("fn-hide");
        $(".J_date").val("").calendar({
            minDate: getCurDate(new Date()),
            maxDate: getDateArea(new Date(), 14),
            dateFormat: 'yyyy-mm-dd',
            onChange: function(p, values, displayValues){
                calendarPickFlag = 1;
                if(!calendarFirstClick){
                    setFtime(values);
                }
            },
            onDayClick: function(p, dayContainer, year, month, day){
                calendarFirstClick = 0;
            },
            onOpen: function(e){
                calendarPickFlag = 0;
            },
            onClose: function(e){
                calendarFirstClick = 0;
                if(!calendarPickFlag){
                    $(".J_date").val("");
                    billAvtime = null;
                }
            }
        });
    }else if(curBillStatus == '2' || curBillStatus == '3'){
        $(".J_ftime_wrap").removeClass("fn-hide");
        try{
            $(".J_date").calendar("destroy");
        }catch(e){
            console.log("日历控件未初始化，无法销毁");
        }
        $(".J_date").val(billAvtime).attr({'readonly': "readonly"});
    }else{
        $(".J_ftime_wrap").addClass("fn-hide");
    }

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

                        initSubPageBtns();
                    }
                }else{
                    $("#J_wxcontent_S").showNullData();
                    $("#J_sureGoods_btn").remove();
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

function initSubPageBtns(){
    $(".J_status_btn").off("click").on("click", function(){
        var _this = $(this);
        $.actions({
            title: "选择操作",
            onClose: function() {
                console.log("close");
            },
            actions: [{
                    text: "有货",
                    className: "color-primary",
                    onClick: function() {
                        setDrugStatus(_this, "0");
                    }
                },
                {
                    text: "缺货",
                    className: "color-warning",
                    onClick: function() {
                        setDrugStatus(_this, "1");
                    }
                },
                {
                    text: "断货",
                    className: 'color-danger',
                    onClick: function() {
                        setDrugStatus(_this, "2");
                    }
                },
                {
                    text: "近效期",
                    className: 'color-blue',
                    onClick: function() {
                        setDrugStatus(_this, "3");
                    }
                }
            ]
        });
    })

    $("#J_sureGoods_btn").off("click").on("click", function(){
        if(curBillStatus == '1'){
            sureBill($(this));
        }else if(curBillStatus == '2'){
            saveBill();
        }
    })

    $(".J_remark").off("clkick").on("click", function(){
        var _this = $(this),
            id = _this.data("id"),
            laleb = _this.find("p.detail"),
            name = _this.data("name"),
            oldText = laleb.text().replace(/^\s\s*/, '') || '',
            type = _this.parent().parent().parent().parent().find(".J_status_btn").data("type");

        $.prompt({
            title: name,
            text: '请输入备注信息',
            input: oldText,
            empty: true, // 是否允许为空
            onOK: function (input) {
                input = input.replace(/^\s\s*/, '') || '';
                if(clickFlag)
                    return false;
                clickFlag = 1;
                createLoading();
                $.PostJson(srvMap.get("update"), "id="+id+"&status="+type+"&remark="+input, function(state, json){
                    if(state == 'success'){
                        if(json && json.code == '0000'){
                            zuiTotast(json.message || "设置备注成功");
                            laleb.text(input);
                        }else{
                            zuiAlert(json.message || "设置备注失败");
                        }
                    }else{
                        zuiAlert("网络错误，请稍后重试");
                    }
                    clickFlag = 0;
                    unblockLoading();
                })
            },
            onCancel: function () {
            
            }
        });
    })
}

function setDrugStatus(obj, type){

    var id = obj.data("id"),
        laleb = obj.parent().parent().parent().find(".weui-panel__bd .J_remark p.detail"),
        name = obj.data("name"),
        oldText = laleb.text().replace(/^\s\s*/, '') || '';

    if(type == '0'){
        oldText = '';
        laleb.text(oldText);
    }

    if(clickFlag)
        return false;
    clickFlag = 1;
    createLoading();
    $.PostJson(srvMap.get("update"), "id="+id+"&status="+type+"&remark="+oldText, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                zuiTotast(json.message || "设置药品状态成功");
                
                obj.css({"background": W_drugStatus_color[type]}).data("type", type).find("span").text(W_drugStatus_des[type]);
            }else{
                zuiAlert(json.message || "设置药品状态失败");
            }
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        clickFlag = 0;
        unblockLoading();
    })
}

function setFtime(date){
    if(date && date.length){
        var cdate = date[0];
    }
    if(clickFlag)
        return false;
    clickFlag = 1;
    createLoading();
    $.PostJson(srvMap.get("arrivaltime"), "billid="+billid+"&arrivaltime="+cdate, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                zuiTotast(json.message || "设置预计到货时间成功");
                billAvtime = cdate;
            }else{
                zuiAlert(json.message || "设置预计到货时间失败");
            }
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        clickFlag = 0;
        unblockLoading();
    })
}

function saveBill(){
    if(clickFlag)
        return false;
    clickFlag = 1;
    createLoading();
    $.PostJson(srvMap.get("save"), "id="+billid+"&status=3", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                zuiTotast(json.message || "发货成功");
            }else{
                zuiAlert(json.message || "发货失败");
            }
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        clickFlag = 0;
        unblockLoading();
    })
}

Handlebars.registerHelper('retNewColor', function(status, type) {
    if(!status){
        if(type == '1'){
                return;
            }else{
                return "有货";
            }
    }else{
        if(type == '1'){
            return W_drugStatus_class[status];
        }else{
            return W_drugStatus_des[status];
        }
    }
});

function sureBill(obj){
    if(!billAvtime){
        zuiTotast("请选择预计到货时间", '2');
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;
    createLoading();
    $.PostJson(srvMap.get("save"), "id="+billid+"&status=2", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                zuiTotast(json.message || "确认订单成功");

                curBillStatus = '2';
                obj.text("发货");

                $(".J_date").calendar("destroy");
            }else{
                zuiAlert(json.message || "确认订单失败");
            }
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        clickFlag = 0;
        unblockLoading();
    })
}
</script>
</body>
</html>