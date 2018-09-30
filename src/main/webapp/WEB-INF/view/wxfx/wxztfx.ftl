<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>手机绑定</title>
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/css/jquery-weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/css/font-awesome.css?v=4.4.0">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.css?v=201511171953">
<style>

body.zui {
    background: #fff;
}
#J_code_btn {
    font-size: 14px;
    width: 90px;
}
.zui .J_content {
    height: auto;
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
#J_bind_btn, #J_bind_btnS {
    border-radius: 1.5rem;
    margin-top: 0.5rem;
}
.zui .J_contetn .weui-cells {
    margin-left: 10px;
    margin-right: 10px;
}
.zui .J_hasBind {
    text-align: center;
    margin-top: 20%;
}
.zui .J_hasBind .J_tip_icon i {
    display: block;
    margin: 1.2rem auto;
    width: 4rem;
    height: 4rem;
    box-shadow: 1px 3px 5px #ddd;
    border-radius: 50%;
}
.zui .J_hasBind .J_tip {
    height: 60px;
    line-height: 60px;
}
.zui .J_hasBind .J_tip .J_num {
    font-weight: bold;
    font-size: .72rem;
}
.J_login_form .J_returnBtn {
    color: #ff0000;
    height: 40px;
    line-height: 40px;
    text-align: center;
    display: block;
    margin-top: 6px;
}
.J_login_form .J_returnBtn small {
    text-decoration: underline;
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
.J_zuisubPage .weui-popup__modal {
    background: #fff;
}
</style>
</head>
<body class="zui">

<header class="J_header">
    <a class="J_header_back customized" href="javascript:;" id="J_closeZuiSubPage"><i class="fa fa-angle-left"></i></a>
    <h1 class="J_header-title">账号绑定</h1>
</header>

<div class="J_content">
    <div class="J_login_form" id="J_mobile_form">
        <div class="title">
            <img src="${request.contextPath}/img/logo.png">
        </div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">账号</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" value="" type="text" placeholder="请输入账号" id="mobilenoS">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="password" placeholder="请输入密码" id="mcodeS">
                </div>
            </div>
        </div>

        <div class="zui-btn-wrap">
            <a href="javascript:;" class="weui-btn weui-btn_primary" id="J_bind_btnS">立即绑定</a>
        </div>

    </div>
</div>

<div class="J_hasBind fn-hide">
    <div class="J_tip_icon">
        <i class="weui-icon-warn weui-icon_msg-primary"></i>
    </div>
    <div class="J_tip">
        <p>您已绑定，账号为：<span class="J_num">${username!""}</span></p>
        <p>手机号：<span class="J_num">${mobileno!""}</span></p>
    </div>
    <!-- <div class="weui-tabbar">
        <a href="javascript:;" class="weui-tabbar__item">
            <div class="weui-tabbar__icon">
                <i class="fa fa-rss"></i>
            </div>
            <p class="weui-tabbar__label">询价单</p>
        </a>
        <a href="billpage.do" class="weui-tabbar__item">
            <div class="weui-tabbar__icon">
                <i class="fa fa-gg-circle"></i>
            </div>
            <p class="weui-tabbar__label">采购订单</p>
        </a>
        <a href="javascript:;" class="weui-tabbar__item weui-bar__item--on">
            <div class="weui-tabbar__icon">
                <i class="fa fa-user"></i>
            </div>
            <p class="weui-tabbar__label">个人中心</p>
        </a>
    </div> -->
</div>

<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/js/jquery-weui.min.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.js?v=201802121203"></script>
<script>


srvMap.add("sendcode", "success.json", "${request.contextPath}/wxfx/sendcode.do");//获取验证码
srvMap.add("bindClerk", "success.json", "${request.contextPath}/wxfx/verifycode.do");//绑定
srvMap.add("banduser", "success.json", "${request.contextPath}/wxfx/banduser.do");//绑定

var clickFlag = 0,
    counter = 59,
    counterInter = null,
    redirect = "";
var bind_username = '${username!""}',//是否已绑定
    bind_mobileno = '${mobileno!""}';

$(function(){

    redirect = getQueryString("redirect") || "";

    if(bind_username){
        hasBind();
    }else{

        $("#J_bind_btnS").click(function(){
            bindClerkS();
        })
    }
})

function hasBind(){
    $(".J_content").addClass("fn-hide");
    $(".J_hasBind").removeClass("fn-hide");
    $(".J_header-title").text("个人中心");
    setTitle("个人中心");
}

function bindClerkS(){
    var mobileno = $("#mobilenoS").val(),
        mcode = $("#mcodeS").val();
    if(!mobileno){
        zuiTotast("请输入账号", "3");
        return false;
    }
    if(!mcode){
        zuiTotast("请输入密码", "3");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;
    createLoading();
    $.PostJson(srvMap.get("banduser"), "username="+mobileno+"&password="+mcode, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                zuiTotast("绑定账号成功", "", function(){
                    console.log("跳转")
                    if(redirect.indexOf("clearsession") !== -1){
                        redirect = delUrlParam(redirect, 'clearsession');
                    }
                    window.location.href = redirect;
                })
            }else{
                zuiAlert(json.message||"绑定账号失败");
            }
        }else{
            zuiAlert("网络错误，请稍后重试");
        }
        unblockLoading();
        clickFlag = 0;
    })
}
</script>
</body>
</html>