<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>三行药品询报价系统 - 登录</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${request.contextPath}/css/animate.css" rel="stylesheet">
<link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<!-- 全局js -->
<script src="${request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${request.contextPath}/js/jquery.form.js?v=1.0.0"></script>
<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<script src="${request.contextPath}/js/my.js?v=201801031555"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<!--[if lt IE 9]>
<meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
<script>if(window.top !== window.self){ window.top.location = window.location;}</script>

<style>
html, body {
    overflow-x: auto;
}
.zuiwrap {
    width: 100%;
    height: 100%;
    min-height: 660px;
    min-width: 1200px;
    position: relative;
}
.J_login_bg {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    top: 0;
}
.zuiwrap .J_login_logo {
    width: 26%;
    height: 52.1%;
    overflow: hidden;
    margin: 0 auto;
    position: absolute;
    left: 24%;
    top: 24%;
    text-align: center;
}
.zuiwrap .J_login_wrap {
    width: 26%;
    height: 52.1%;
    overflow: hidden;
    margin: 0 auto;
    background: #fff;
    position: absolute;
    left: 49.8%;
    top: 24%;
}
.J_login_logo .J_logo {
    width: 70px;
    margin-top: 32%;
}
.J_login_logo h1 {
    font-size: 26px;
    color: #fff;
    font-family: cursive;
}

.zuiwrap .J_form_wrap {
    text-align: center;
    position: relative;
    padding-top: 10.9%;
}
.zuiwrap .J_form_wrap .item {
    position: relative;
    margin-bottom: 20px;
}
.zuiwrap .J_form_wrap .item.userwrap {
    margin-bottom: 40px;
}
.zuiwrap .J_form_wrap .item label {
    position: absolute;
    left: 36px;
    top: 2px;
    display: block;
    width: 30px;
    height: 33px;
    margin: 0;
    padding: 0;
    line-height: 33px;
}
.zuiwrap .J_form_wrap .item label .fa {
    color: #a2a2a2;
    display: block;
    height: 33px;
    line-height: 33px;
}
.ui-input {
    padding: 6px 9px;
    line-height: 18px;
    width: 165px;
    border: 1px solid #DFDFDF;
}
.ui-input:hover {
    border: 1px solid #BBBBBB;
}
.ui-input:focus {
    /*border-color: rgba(145,194,212,0.8)!important;*/
    border-color: #66afe9!important;
    box-shadow: 0 1px 1px rgba(0,0,0,0.075) inset,0 0 8px rgba(145,194,212,0.6);
    outline: 0 none;
}
.zuiwrap .J_form_wrap .item .ui-input {
    width: 82%;
    padding: 9px 9px 8px 30px;
    font-size: 12px;
}
.zuiwrap .J_rember {
    display: block;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    cursor: pointer;
    text-align: left;
    padding-left: 37px;
    width: 200px;
    color: #9a9a9a;
    margin-bottom: 10px;
}
.zuiwrap .J_rember .check_wrap {
    display: inline-block;   
}
.zuiwrap .J_rember .check_wrap .fa {
    cursor: pointer;
    font-size: 19px;
    display: inline-block;
    vertical-align: middle;
}
.zuiwrap .J_rember .check_wrap .fa-square-o {
    margin-right: 1.4px;
}
.zuiwrap .J_rember .check_wrap .fa-check-square {
    color: #4E8FF7;
}
.zuiwrap .J_rember label {
    cursor: pointer;
    display: inline-block;
    padding: 0 15px 0 5px;
    font-weight: normal;
    font-size: 12px;
}
.fn-hide {
    display: none!important;
}
/**
 * 按钮样式
**/
.zuiwrap .ui-btn {
    border: 1px solid #9A6835;
    background: #9A6835;
    color: #fff;
    display: block;
    width: 82%;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    font-size: 16px;
    letter-spacing: 5px;
    border-radius: 30px;
}
.zuiwrap .ui-btn:hover {
    border: 1px solid #795027;
    background-color: #795027;
}
.zuiwrap .ui-btn:active {
    border: 1px solid #5c5249;
    background-color: #5e544b;
    box-shadow: 1px 1px 3px #5c5249 inset;
}
.zuiwrap .J_cover_wrap {
    background: #F2F6F9;
    border-left: 6px solid #4E8FF7;
    position: absolute;
    top: 7%;
    left: 0;
    width: 100%;
    height: 76px;
}
.J_register {
    position: absolute;
    top: 57%;
    right: 30px;
    color: #ff0000;
    display: block;
    width: 60px;
    height: 40px;
    line-height: 40px;
}
.J_register:hover {
    text-decoration: underline;
    color: #ff0000;
}
.zfooter {
    position: absolute;
    bottom: 20px;
    left: 0;
    width: 100%;
    height: 30px;
    text-align: center;
    margin: 0;
    padding: 0;
}
.zfooter p {
    font-size: 14px;
    color: #fff;
    height: 30px;
    line-height: 30px;
    cursor: default;
}
.modal-content .form-control, .single-line {
    width: 89%;
    display: inline-block;
}
.zrequire {
    color: #ff0000;
    display: inline-block;
    vertical-align: -6px;
    margin-left: 5px;
    font-size: 20px;
    width: 20px;
    cursor: default;
}
.J_login_wrap h2 {
    font-size: 22px;
    font-weight: normal;
    text-align: center;
    color: #000;
    cursor: default;
}
.J_valicode_btn {
    display: inline-block;
    padding: 4px 12px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: 400;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    color: #58666e !important;
    background-color: #fcfdfd;
    background-color: #fff;
    border-color: #dee5e7;
    border-bottom-color: #d8e1e3;
    -webkit-box-shadow: 0 1px 1px rgba(90, 90, 90, 0.1);
    box-shadow: 0 1px 1px rgba(90, 90, 90, 0.1);
    position: absolute;
    right: 9.3%;
    height: 35px;
    top: 1px;
    border-bottom: none;
    border-top: none;
    border-radius: 0;
    border-right: none;
    line-height: 29px;
    text-align: center;
    width: 95px!important;
}
.J_valicode_btn.disable {
    cursor: default;
    color: #cacaca!important;
}
#J_subLoginm {
    margin-top: 15%;
}
.J_returnBtn {
    color: #ff0000;
    height: 40px;
    line-height: 40px;
    text-align: center;
    display: inline-block;
    margin-top: 1%;
}
.zuiwrap .J_form_wrap .item label .fa-mobile-phone {
    font-size: 20px;
}
.modal-open .modal .ibox-content {
    max-height: 460px;
}
</style>

</head>

<body>

<div class="zuiwrap">
    <img src="${request.contextPath}/img/login_bg_s.png" class="J_login_bg">
    <div class="J_login_logo">
        <img src="${request.contextPath}/img/logo.png" class="J_logo">
        <h1>南昌三行科技</h1>
    </div>
    <div class="J_login_wrap">
        <h2>三行药品询报价系统</h2>
        <div class="J_count_wrap">
            <div class="J_form_wrap">
                <div class="J_cover_wrap"></div>
                <div class="item userwrap">
                    <label for="zusername"><i class="fa fa-user-o" aria-hidden="true"></i></label>
                    <input type="text" id="zusername" name="username" class="ui-input" placeholder="用户名" >
                </div>
                <div class="item">
                    <label for="zpassword"><i class="fa fa-key" aria-hidden="true"></i></label>
                    <input type="password" id="zpassword" name="password" class="ui-input" placeholder="密码" >
                </div>
                <div class="J_rember" id="J_rember">
                    <div class="check_wrap">
                        <i class="fa fa-square-o fn-hide" aria-hidden="true"></i>
                        <i class="fa fa-check-square" aria-hidden="true"></i>
                    </div>
                    <label>记住用户名和密码</label>
                </div>
                <a id="J_subLogin" class="ui-btn" href="javascript:;" hidefocus="true">登录</a>
                <a id="J_register" class="J_register"><small>注 册</small></a>
                <a id="J_ret_mobile" class="J_returnBtn"><small>手机账号登录</small></a>

                <form class="fn-hide" role="form" action="login.do" method="post" id="J_subform">
                    <input type="text" name="username">
                    <input type="password" name="password">
                </form>
            </div>
        </div>

        <div class="J_mobile_wrap fn-hide">
            <div class="J_form_wrap">
                <div class="item userwrap">
                    <label for="zusernamem"><i class="fa fa-mobile-phone" aria-hidden="true"></i></label>
                    <input type="text" id="zusernamem" name="zusernamem" class="ui-input" placeholder="请输入手机号码" maxlength="11">
                </div>
                <div class="item">
                    <label for="zpassword"><i class="fa fa-key" aria-hidden="true"></i></label>
                    <input type="text" id="zpasswordm" name="zpasswordm" class="ui-input" placeholder="请输入短信验证码" maxlength="4">
                    <a id="J_valicode_btn" class="J_valicode_btn" href="javascript:;" hidefocus="true">获取验证码</a>
                </div>
                <a id="J_subLoginm" class="ui-btn" href="javascript:;" hidefocus="true">登录</a>
                <a id="J_ret_count" class="J_returnBtn"><small>返回账号登录</small></a>
            </div>
        </div>
    </div>

    <div class="zfooter">
        <p>©版权所有:南昌三行信息技术有限公司</p>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<script>
var clickFlag = 0,
    errorMsg = "",
    loginType = '1',
    timer = null,
    waits = 59;
$(function(){

    <#if message ??>
        errorMsg = '${message}';
        MyFun.to.e(errorMsg);
    </#if>

    waits = parseInt($.cookie('J_formLogin_data_waits') || '59', 10);
    if(waits !== 59)
        countTimer();

    var cusername = $.cookie('J_formLogin_data_username'),
        cpassword = $.cookie('J_formLogin_data_pwd');
    
    if(cusername && cusername !== 'null'){
        //保存了用户名密码，自动填充
        $("#zusername").val(cusername);
        if(errorMsg){
            $("#zpassword").val("").focus();
        }else{
            $("#zpassword").val(cpassword);
        }
    }else{
        if(errorMsg)
            $("#J_rember").find(".fa").toggleClass("fn-hide");
        setTimeout(function(){
            $("#zusername").val("");
            $("#zpassword").val("");
        }, 10)
    }

    /*var _newTop = zuiaccMul(zuiaccDiv($(window).height(), 780), 140);

    $(".zuiwrap").css({"paddingTop": _newTop});*/

    $("#J_rember").off("click").on("click", function(e){
        $("#J_rember").find(".fa").toggleClass("fn-hide");
    })

    $("#J_subLogin").on("click", function(){
        var username = $("#zusername").val(),
            password = $("#zpassword").val();

        if(!username){
            MyFun.to.i("请输入用户名");
            $("#zusername").focus();
            return false;
        }
        if(!password){
            MyFun.to.i("请输入密码");
            $("#zpassword").focus();
            return false;
        }
        if(clickFlag)
            return false;
        clickFlag = 1;

        var showcompared = $("#J_rember").find(".fa-square-o").hasClass("fn-hide") ? false : true;
        if(showcompared){
            //不保存用户名密码
            //设置、更新cookie
            $.cookie('J_formLogin_data_username', null);
            $.cookie('J_formLogin_data_pwd', null);
        }else{
            $.cookie('J_formLogin_data_username', username);
            $.cookie('J_formLogin_data_pwd', password);
        }

        $("#J_subform input:eq(0)").val(username);
        $("#J_subform input:eq(1)").val(password);
        $("#J_subform").submit();
    })

    $(".J_form_wrap .ui-input").on("keyup", function(e){
        if(e.which==13){
            if(loginType == '1'){
                $("#J_subLogin").click();
            }else if(loginType == '2'){
                $("#J_subLoginm").click();
            }
        }
    })

    $("#J_register").on("click", function(){
        $.ajax({
            url: "${request.contextPath}/register.do",
            type: "GET",
            data : "&_=" + (new Date()).getTime(),
            cache: false,
            dataType: "html",
            timeout: 60000,
            beforeSend: function (xhr) {
                xhr.overrideMimeType("text/plain; charset=utf-8");
            },
            success: function(html) {
                $("#info-form").html(html).modal('show');

                $("#J_saveRegister").off("click").on("click", function(){
                    var password = $("#password").val(),
                        passwordS = $("#passwordS").val(),
                        username = $("#dusername").val(),
                        scompany = $("#J_scompany").val(),
                        company = $("#J_company").val();

                    if(!username){
                        MyFun.to.i("请输入用户名");
                        return false;
                    }
                    if(!password){
                        MyFun.to.i("请输入密码");
                        return false;
                    }
                    if(password !== passwordS){
                        MyFun.to.i("两次密码输入不一致");
                        return false;
                    }
                    if(!company){
                        MyFun.to.i("请输入公司名称");
                        return false;
                    }
                    if(!scompany){
                        MyFun.to.i("请输入公司简称");
                        return false;
                    }

                    if(clickFlag)
                        return false;
                    clickFlag = 1;
                    var data = $("#save_form").formSerialize();
                    $.PostJson("${request.contextPath}/register.do", data, function(state, json){
                        if(state == 'success'){
                            if(json && json.code == '0000'){
                                MyFun.to.s(json.message || "注册成功");
                            }else{
                                MyFun.to.e(json.message || "注册失败");
                                        clickFlag = 0;
                                        return;
                            }
                        }
                        clickFlag = 0;

                        $("#info-form").modal('hide');
                        setTimeout(function(){
                            window.location.href = '${request.contextPath}/index.do';
                        }, 200)
                    })
                })
            },
            error: function(e) {
                alert(e + "获取注册页面失败");
            },
            complete:function(XMLHttpRequest,status){ 
                
            }
        });
    })

    var _J_count_wrap = $(".J_count_wrap"),
        _J_mobile_wrap = $(".J_mobile_wrap");
    $("#J_ret_mobile").on("click", function(){
        _J_count_wrap.addClass("fn-hide");
        _J_mobile_wrap.removeClass("fn-hide");
        loginType = '2';
    })
    $("#J_ret_count").on("click", function(){
        _J_count_wrap.removeClass("fn-hide");
        _J_mobile_wrap.addClass("fn-hide");
        loginType = '1';
    })

    $("#J_valicode_btn").on("click", function(){
        if($(this).hasClass("disable"))
            return false;
        valicode_btn();
    })
    $("#J_subLoginm").on("click", function(){
        subLoginm();
    })
})

function zuiaccMul(arg1,arg2) {  
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length}catch(e){}  
    try{m+=s2.split(".")[1].length}catch(e){}  
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
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

function valicode_btn(){
    var zusernamem = $("#zusernamem").val();
    if(!zusernamem){
        MyFun.to.i("请输入手机号码");
        return false;
    }
    if(zusernamem.length !== 11){
        MyFun.to.i("手机号码格式错误");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;

    createBlock(".zuiwrap", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/sendcode.do", "mobileno="+zusernamem, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s("验证码已发送至您的手机，请注意查收");

                countTimer();
            }else{
                MyFun.to.e(json.message || "获取手机验证码失败");
            }
        }
        clickFlag = 0;
        unBlock(".zuiwrap");
    })
}

function countTimer(){
    $("#J_valicode_btn").addClass("disable").text(waits+'"');
    timer = setInterval(function(){
        waits--;
        $.cookie('J_formLogin_data_waits', waits);
        if(waits == 0){
            stopTimer();
        }else{
            $("#J_valicode_btn").text(waits+'"');
        }
    }, 1000)
}

function stopTimer(){
    if(timer)
        clearInterval(timer);
    timer = null;
    waits = 59;
    $.cookie('J_formLogin_data_waits', 59);
    $("#J_valicode_btn").removeClass("disable").text("获取验证码");
}

function subLoginm(){
    var zusernamem = $("#zusernamem").val(),
        zpasswordm = $("#zpasswordm").val();
    if(!zusernamem){
        MyFun.to.i("请输入手机号码");
        return false;
    }
    if(!zpasswordm){
        MyFun.to.i("请输入短信验证码");
        return false;
    }
    createBlock(".zuiwrap", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/verifycode.do", "mobileno="+zusernamem+"&code="+zpasswordm, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s("验证通过，正在为您跳转...");
                stopTimer();
                window.location.href = 'index.do';
            }else{
                MyFun.to.e(json.message || "验证失败");
            }
        }
        unBlock(".zuiwrap");
    })
}
</script>

</body>
</html>