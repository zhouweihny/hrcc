<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title> </title>
<meta name="keywords" content="">
<meta name="description" content="">
<!--[if lt IE 9]>
<meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
<link rel="stylesheet" href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6">
<link rel="stylesheet" href="${request.contextPath}/css/font-awesome.min.css?v=4.4.0">
<link rel="stylesheet" href="${request.contextPath}/css/animate.css">
<link rel="stylesheet" href="${request.contextPath}/css/style.css?v=4.1.0">
<link rel="stylesheet" href="${request.contextPath}/css/plugins/toastr/toastr.min.css">
<link rel="stylesheet" href="${request.contextPath}/css/index.css">
<script src="${request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${request.contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${request.contextPath}/js/hAdmin.js?v=4.1.0"></script>
<script src="${request.contextPath}/js/plugins/pace/pace.min.js"></script>
<script src="${request.contextPath}/js/my.js?v=201803011601"></script>
<script src="${request.contextPath}/js/index.js?v=201803011601"></script>
<script src="${request.contextPath}/js/jquery.form.js?v=1.0.0"></script>
<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<style>
#content-main.J_mainContent.iosIpad {
	overflow: auto; 
	-webkit-overflow-scrolling: touch; 
}
body.full-height-layout #page-wrapper.zuiPageWrapper {
	padding-top: 60px;
	height: 100%;
}
</style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg zui" style="overflow:hidden">

<div class="zui-header">
	<div class="zhead fn-clear">
		<div class="logo">
			<div class="logowrap">
				<img src="${request.contextPath}/img/logo.png">
				<span>三行药品询报价系统</span>
			</div>
			<a class="navbar-minimalize minimalize-styl-2" href="javascript:;">
				<i class="fa fa-bars"></i>
			</a>
		</div>
		<div class="J_nav_item">
			<ul>
				<li>
					<a href="javascript:;" class="curuser" data-zuiflownewflag='${Session.USER.newflag?string('false','true')}' data-basepath="${request.contextPath}">
						<i class="fa fa-eercast"></i>
						当前登录人：${Session.USER.username}<#if Session.COMPANY.company ??>(${Session.COMPANY.company})</#if>
					</a>
					<dl>
						<dd>
							<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/user/modifypassword.do?id=${Session.USER.id} ','info-form')" id="J_index_changeInfo"><i class="fa fa-user-circle"></i>修改密码</a>
						</dd>
						<dd>
							<a href="${request.contextPath}/logout.do" id="J_index_loginOut"><i class="fa fa-sign-out"></i>退出</a>
						</dd>
					</dl>
				</li>
				<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")>
				<li>
					<a id="J_shoppingCartIndex" class="J_shoppingCartIndex" href="javascript:;" title="我的购物车">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i>我的购物车
						<em></em>
					</a>
				</li>
				</#if>
				<li>
					<a class="J_demoDownload" href="${request.contextPath}/helpDoc.doc" target="_blank" title="点击下载帮助文档">帮助文档</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<#assign indexurl="">     
<div id="wrapper">
	<!--左侧导航开始-->
	<nav class="navbar-default navbar-static-side zui-navbar" role="navigation">
		<div class="nav-close"><i class="fa fa-times-circle"></i></div>
		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">
				<#list menus as menu>
				<#if menu.parentid=="">
				<li class="J_parent_li">
					<div title="${menu.name}" class="J_tithead">
						<i class="<#if menu.icon?? && menu.icon!=''>${menu.icon}<#else>fa fa-desktop</#if> zpfa"></i>
						<span class="ng-scope">${menu.name}</span>
						<i class="fa fa-angle-left"></i>
					</div>
					<dl>
						<#list menus as menu2>
						<#if menu.id==menu2.parentid>
						<#if menu2.url!="">
						<#if indexurl=="" >
						<#assign indexurl=menu2.url>     
						</#if>
						<dd class="J_sub_dd">
							<a title="${menu2.name}" class="J_menuItem" href="javascript:;" data-href="${request.contextPath}${menu2.url}">
								<i class="<#if menu2.icon?? && menu2.icon!=''>${menu2.icon}<#else>fa fa-desktop</#if>"></i>
								<span class="nav-label">${menu2.name}</span>
							</a>
						</dd>
						</#if>
						</#if>
						</#list>
					</dl>
				</li>
				</#if>
				</#list>
			</ul>
		</div>
	</nav>
	<!--左侧导航结束-->

	<!--右侧部分开始-->
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="row border-bottom">
			<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
				<div class="navbar-header fn-clear">
					<div class="zui-navTab">
						<ul id="min_title_list" class="acrossTab">

						</ul>
						<a id="J_navTabShowMore" class="btn radius btn-default btn-small J_navTabShowMore fn-hide" href="javascript:;">
							<i class="fa fa-angle-down"></i>
						</a>
					</div>
				</div>
			</nav>
		</div>

		<div class="row J_mainContent" id="content-main">
			<!-- <iframe id="J_iframe" width="100%" height="100%" src="${request.contextPath}${indexurl}" frameborder="0" data-id="" seamless></iframe> -->
		</div>
	</div>
	<!--右侧部分结束-->
</div>

<div id="info-form" class="modal fade" aria-hidden="true"  ></div>

<script>

$(function () {

	var userId = '${Session.USER.id}';

	/*if(userId == 'D47BCD149F544752A5E0DCF1AAAB11D3'){
		window.location.href = 'indexs.do';
		return false;
	}*/

	setTimeout(function(){
		$('.sidebar-collapse').slimScroll({
			height: '100%',
			railOpacity: 0.9,
			alwaysVisible: false
		});

		//获取购物车数量
		var zcartCookie = $.cookie('parent_cartCookie_'+userId) || "";
		if(zcartCookie){
			zcartCookie = JSON.parse(zcartCookie);
		}
		var goodsTotal = zcartCookie.length;
		if(goodsTotal){
			$("#J_shoppingCartIndex").find("em").text(goodsTotal).show();
		}else{
			$("#J_shoppingCartIndex").find("em").text(0).hide();
		}
	}, 500)

	if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){
		$("#content-main").addClass("iosIpad");
	}
});
</script>
</body>
</html>