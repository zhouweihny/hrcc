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
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<link href="${request.contextPath}/js/plugins/jquery.smartWizard/smart_wizard.css" rel="stylesheet">
<link href="${request.contextPath}/js/plugins/jquery.smartWizard/smart_wizard_theme_arrows.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/jquery.smartWizard/jquery.smartWizard.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>

<style>
.fn-hide {
    display: none!important;
}
.sw-theme-dots > ul.step-anchor > li > a:after {
    left: 40.5%;
}
.sw-theme-arrows .sw-toolbar {
    position: absolute;
    right: 20px;
    top: 6px;
}
.zuiFrame {
    margin-top: 20px;
    min-height: 550px;
}
.wrapper-content {
    margin-bottom: 0!important;
}
.sw-theme-arrows > ul.step-anchor > li.active > a {
    border-color: #5cb85c !important;
    color: #fff !important;
    background: #5cb85c !important;   
}
.sw-theme-arrows > ul.step-anchor > li.active > a:after {
    border-left: 30px solid #5cb85c !important;
}
.sw-theme-arrows > ul.step-anchor > li.done > a {
    border-color: #fff !important;
    color: #000 !important;
    background: #fff !important;
}
.sw-theme-arrows > ul.step-anchor > li.done > a:after {
    border-left: 30px solid #fff;
}
</style>

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content zuiContent">
        <!-- SmartWizard html -->
        <div id="smartwizard">
            <ul>
                <li class="active"><a href="#step-1">品种目录<br /><small>上传品种目录</small></a></li>
                <li class="done"><a href="#step-2">对码项目<br /><small>创建对码项目，添加品种目录</small></a></li>
                <li class="done"><a href="#step-3">品种对码<br /><small>对码品种</small></a></li>
                <li class="done"><a href="#step-4">复核导出<br /><small>复核已对品种、导出结果</small></a></li>
            </ul>
            
            <div>
                <div id="step-1" class="">
                    <iframe src="${request.contextPath}/catalog/list.do" class="zuiFrame" width="100%" height="100%"  marginwidth="0" marginheight="0" scrolling="no" framespacing="0" vspace="0" hspace="0" frameborder="0"></iframe>
                </div>
                <div id="step-2" class="">
                    <iframe src="javascript:;" class="zuiFrame" width="100%" height="100%"  marginwidth="0" marginheight="0" scrolling="no" framespacing="0" vspace="0" hspace="0" frameborder="0"></iframe>
                </div>
                <div id="step-3" class="">
                    <iframe src="javascript:;" class="zuiFrame" width="100%" height="100%"  marginwidth="0" marginheight="0" scrolling="no" framespacing="0" vspace="0" hspace="0" frameborder="0"></iframe>
                </div>
                <div id="step-4" class="">
                    <iframe src="javascript:;" class="zuiFrame" width="100%" height="100%"  marginwidth="0" marginheight="0" scrolling="no" framespacing="0" vspace="0" hspace="0" frameborder="0"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

var ajaxCount = 0,
    zurl = [
        '${request.contextPath}/catalog/list.do?_v='+(new Date().getTime()),
        '${request.contextPath}/space/list.do?_v='+(new Date().getTime()),
        '${request.contextPath}/space/varietycontrol.do?_v='+(new Date().getTime()),
        '${request.contextPath}/space/varietycontrols.do?_v='+(new Date().getTime())
    ]

$(function(){
    $("#smartwizard").css({"height": $(window).height() - 100});

    createBlock(".zuiContent", '正在努力加载数据...');
    $(".zuiFrame").on("load", function(){
        $(this).contents().find("html").addClass("innerIframe");
        initStep();
        unBlock(".zuiContent");
    })

    setInterval(function(){
        setIframeH();
    }, 1000)
})

function setIframeH(){
    var _this = $("#smartwizard .step-content:visible .zuiFrame"),
        _oldH = _this.data("zheight"),
        mainheight = _this.contents().find("body").height() + 80;
    if(_oldH !== mainheight){
        _this.data("zheight", mainheight);
        _this.height(mainheight);
    }
}

function initStep(){
    $("#smartwizard").css({"height": "auto"});

    // Step show event 
    $("#smartwizard").on("showStep", function(e, anchorObject, stepNumber, stepDirection, stepPosition) {
       //alert("You are on step "+stepNumber+" now");
        if(stepPosition === 'first'){
           $("#prev-btn").addClass('disabled');
        }else if(stepPosition === 'final'){
           $("#next-btn").addClass('disabled');
        }else{
           $("#prev-btn").removeClass('disabled');
           $("#next-btn").removeClass('disabled');
        }

        $(".zuiFrame").eq(stepNumber).attr({"src": zurl[stepNumber]});

        createBlock(".zuiContent", '正在努力加载数据...');
        $(".zuiFrame").off("load").on("load", function(){
            $(this).contents().find("html").addClass("innerIframe");
            unBlock(".zuiContent");
        })
    });

    // Smart Wizard
    $('#smartwizard').smartWizard({ 
        selected: 0, 
        theme: 'arrows',
        transitionEffect:'fade',
        showStepURLhash: false,
        useURLhash: true,
        toolbarSettings: {
            toolbarPosition: 'top', // none, top, bottom, both
        }, 
        lang: {
            next: "下一步",
            previous: "上一步"
        },
        contentCache: false
    });
}

</script>
</body>
</html>