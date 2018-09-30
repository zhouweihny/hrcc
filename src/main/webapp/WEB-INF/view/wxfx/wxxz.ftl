<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>插件下载</title>
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/css/jquery-weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.css?v=201511171953">
<style>

body.zui {
    background: #fff;
}
.J_content {
    padding: 0 10px;
    margin-top: 20px;
}
.J_content p.des {
    padding: 10px;
    margin: 10px;
    border: 1px solid #e5e5e5;
    font-size: .7rem;
}
.J_content .weui-btn_primary {
    font-size: .8rem;
    margin: 20px 10px;
}
.J_content p.link {
    height: 34px;
    line-height: 32px;
    background: #ccc;
    border: 1px solid #9a9999;
    margin: 10px;
    font-size: .65rem;
    text-align: center;
    text-decoration: underline;
    color: #333;
}

</style>
</head>
<body class="zui">

<header class="J_header">
    <h1 class="J_header-title">插件下载</h1>
</header>

<div class="J_content">
    <p class="des">1，接口对接：提供ERP对接工具，通过插件获取门店销售数据并将数据导入三行品类管理系统。</p>
    <p class="des">2，EXCEL导入：通过插件获取数据并导出EXCEL格式文件。手动将文件导入三行品类管理系统。</p>
    <p class="des">3，点击下载按钮，或者复制下方链接地址，安装插件。</p>
    <a href="javascript:;" class="weui-btn weui-btn_primary">立即下载</a>
    <p class="link">http://192.168.96.68:8080/pluginDownload.do</p>
</div>

</body>
</html>