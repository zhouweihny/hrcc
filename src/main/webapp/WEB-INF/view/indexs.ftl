<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>三行药店品类管理系统</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6">
<link rel="stylesheet" href="${request.contextPath}/css/font-awesome.min.css?v=4.4.0">
<link rel="stylesheet" href="${request.contextPath}/css/animate.css">
<link rel="stylesheet" href="${request.contextPath}/css/style.css?v=4.1.0">
<link rel="stylesheet" href="${request.contextPath}/css/plugins/toastr/toastr.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/MetroJs/9.77/MetroJs.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/introjs/introjs.css">
<link rel="stylesheet" href="${request.contextPath}/css/index.css">

<script src="${request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${request.contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${request.contextPath}/js/my.js?v=201806271124"></script>
<script src="${request.contextPath}/js/jquery.form.js?v=1.0.0"></script>
<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<script src="${request.contextPath}/js/plugins/MetroJs/9.77/MetroJs.min.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/introjs/intro.js"></script>
<style>
#zuIframe.iosIpad {
	overflow: auto; 
	-webkit-overflow-scrolling: touch;
}
body.full-height-layout {
	height: auto;
}
body.full-height-layout #page-wrapper.zuiPageWrapper {
	padding-top: 60px;
	height: 100%;
}
#zwrapper {
	width: 1020px;
	margin: 100px auto 0;
	background: #fff;
	height: auto;
}
.J_class_items .items {
	margin-bottom: 10px;
}
.J_class_items h2 {
	font-size: 16px;
	color: #4A4A4A;
	height: 30px;
	line-height: 30px;
	font-weight: 600;
}
.J_class_items .big {
	width: 330px;
	height: 135px;
}
.J_class_items .small {
	width: 158px;
	height: 135px;
}
.J_class_items .live-tile {
	margin-left: 0;
	margin-right: 14px;
	margin-bottom: 15px;
}
.J_class_items .live-tile.last {
	margin-right: 0;
}
.J_class_items .live-tile .tile-title {
	font-size: 20px;
	left: 80px;
	top: 48px;
}
.J_class_items .live-tile a {
	display: block;
	height: 100%;
	width: 100%;
	text-decoration: none;
}
.J_class_items .exclude p img {
	margin: 0 auto;
	display: block;
}
.J_class_items .live-tile.des a {
	top: 7px;
}
.J_class_items .live-tile.des .tile-title {
	top: 28px;
	left: 20px;
	font-size: 16px;
	width: 170px;
	height: 20px;
}
.J_class_items .live-tile.des p {
	width: 190px;
	margin: 51px 0 0 10px;
	height: 52px;
	line-height: 20px;
	display: -webkit-box;
	font-size: 12px;
}
.J_class_items .live-tile .fa {
	position: absolute;
	right: 21px;
	top: 30px;
	font-size: 70px;
}
.J_class_items .live-tile.small .tile-title {
	font-size: 17px;
    top: 97px;
    width: 158px;
    left: 0;
    text-align: center;
}
.J_class_items .live-tile.small .fa {
	top: 15px;
    width: 158px;
    right: 0;
    text-align: center;
}
.zui-header {
    z-index: 119891019;
    position: fixed;
    top: 0;
    left: 0;
}
.zuIframe {
	position: relative;
	padding: 70px 0 10px;
	width: 100%;
	height: 100%;
}
.J_closeIframe {
	position: absolute;
	right: 15px;
	top: 7px;
	font-size: 40px;
	cursor: pointer;
	transition: all 0.6s ease-out;
	color: #fff;
}
.J_closeIframe:hover {
	transform: rotate(180deg) scale(1.02);
}
.zuIframe .zui_wrap {
	width: 100%;
	height: 100%;
}
.J_indexsModal {
	margin-top: 60px;
}
.zui-header .zhead .logo .logowrap {
	width: auto;
}
#toast-container {
	top: 65px;
}
.J_class_items .live-tile a .tit {
	font-size: 20px;
	left: 20px;
	top: 33px;
}
.J_class_items .live-tile a .curstore {
	font-size: 14px;
    left: 21px;
    top: 72px;
    border-bottom: 2px solid #fff;
    height: 28px;
    line-height: 28px;
    width: 210px;
}
.J_class_items .items .live-tile.disable {
	background: #aaa!important;
}
.J_class_items .items .live-tile.disable a {
	cursor: default;
}
.J_class_items .items.showMoreItems {
	height: 215px;
	overflow: hidden;
}
.J_class_items .big.bigS {
	width: 500px;
}
.J_class_items .bigS.live-tile a .curstore {
	width: 360px;
}
.J_shoppingCartIndex {
    display: block;
    cursor: pointer;
    color: #fff;
    height: 30px;
    line-height: 30px;
    padding: 0 4px 0 30px;
    position: relative;
}
.J_shoppingCartIndex em {
	position: absolute;
	top: 16px;
	right: 18px;
	display: block;
	border: 1px solid #E95B19;
	background: #E95B19;
	border-radius: 35px;
	width: 35px;
	height: 35px;
	color: #fff;
	text-align: center;
	line-height: 35px;
	font-style: normal;
}
.J_shoppingCartIndex .fa {
    font-size: 16px;
    display: inline-block;
    margin: 0 5px 0 -4px;
    vertical-align: 0;
}
.J_class_items .J_indexsNumImgWrap.small {
	width: 244px;
}
.J_class_items .J_indexsNumImgWrap.small .tile-title {
	left: 83px;
}
.J_class_items .J_indexsNumImgWrap.small .J_indexsNumImg {
	width: 120px;
	margin: 8px 0 0 0;
}
.J_class_items .J_indexsNumImgWrap.big {
	width: 330px;
}
.J_class_items .J_indexsNumImgWrap.big .tile-title {
	left: 118px;
}
.J_class_items .J_indexsNumImgWrap.big .J_indexsNumImg {
	width: 120px;
	margin: 2px 0 0 0;
}
</style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg zui">

<div class="zui-header">
	<div class="zhead fn-clear">
		<div class="logo">
			<div class="logowrap">
				<img src="${request.contextPath}/img/logo.png">
				<span>三行药店品类管理系统</span>
			</div>
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
						<!-- <dd>
							<a href="javascript:;" id="J_index_showIntro"><i class="fa fa-bell-o"></i>观看引导</a>
						</dd> -->
						<!-- <dd>
							<a id="J_shoppingCartIndex" class="J_shoppingCartIndex" href="javascript:;" title="我的购物车">
								<i class="fa fa-shopping-cart" aria-hidden="true"></i>我的购物车
								<em></em>
							</a>
						</dd> -->
						<dd>
							<a href="${request.contextPath}/logout.do" id="J_index_loginOut"><i class="fa fa-sign-out"></i>退出</a>
						</dd>
					</dl>
				</li>
				<li>
					<a class="J_demoDownload" href="${request.contextPath}/helpDoc.doc" target="_blank" title="点击下载帮助文档">帮助文档</a>

				</li>
			</ul>
			<i id="J_closeIframe" class="fa fa-close J_closeIframe fn-hide" aria-hidden="true"></i>
		</div>
	</div>
</div>

<div id="zwrapper">

	<div class="J_class_items" id="J_class_items">
		<!-- <div class="items fn-clear">
			<h2>门店、文件选择</h2>
			        <div class="live-tile exclude big" style="background: #66B281;">
			        	<a href="javascript:;" hidefocus="true" data-type='11'>
		    	    <span class="tile-title tit">门店选择</span>
		    	    <span class="tile-title curstore" id="J_curstore">请选择</span>
		    	    <i class="fa fa-podcast" aria-hidden="true"></i>
			        	</a>
			        </div>
		            <div class="live-tile exclude big bigS" style="background: #2D8BEF;">
		            	<a href="javascript:;" hidefocus="true" data-type='12'>
		    	    	    <span class="tile-title tit">文件选择</span>
		    	    	    <span class="tile-title curstore" id="J_curstoreFile">请选择</span>
		    	    	    <i class="fa fa-file-excel-o" aria-hidden="true"></i>
		            	</a>
		            </div>
		</div> -->

		<div class="items fn-clear">
			<h2>基础维护</h2>
	        <div class="live-tile exclude small J_indexsNumImgWrap" style="background: #7b5454;">
	        	<a href="javascript:;" hidefocus="true" data-type='9' data-url='${request.contextPath}/store/list.do?indexs=1'>
		    	    <span class="tile-title">门店管理</span>
		    	    <i class="fa fa-address-card" aria-hidden="true"></i>
		    	    <img src="${request.contextPath}/img/indexsnum/1.png" class="J_indexsNumImg">
	        	</a>
	        </div>
	        <div class="live-tile exclude small J_indexsNumImgWrap" style="background: #D07BE0;">
	        	<a href="javascript:;" hidefocus="true" data-type='0' data-url='${request.contextPath}/fxsalefile/list.do?indexs=1'>
		    	    <span class="tile-title">导入销售明细</span>
		    	    <i class="fa fa-eercast" aria-hidden="true"></i>
		    	    <img src="${request.contextPath}/img/indexsnum/2.png" class="J_indexsNumImg">
	        	</a>
	        </div>
	        <div class="live-tile exclude small" style="background: #887CDE;">
	        	<a href="javascript:;" hidefocus="true" data-type='8' data-url='${request.contextPath}/management/list.do?indexs=1'>
		    	    <span class="tile-title">经营目录</span>
		    	    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
	        	</a>
	        </div>
	        <div class="live-tile exclude small" style="background: #ce9654;">
	        	<a href="javascript:;" hidefocus="true" data-type='24' data-url='${request.contextPath}/user/list.do?indexs=1'>
		    	    <span class="tile-title">用户管理</span>
		    	    <i class="fa fa-vcard" aria-hidden="true"></i>
	        	</a>
	        </div>
	        <div class="live-tile exclude small" style="background: #64b3f4;">
	        	<a href="javascript:;" hidefocus="true" data-type='27' data-url='${request.contextPath}/storedy/list.do?indexs=1'>
		    	    <span class="tile-title">店员管理</span>
		    	    <i class="fa fa-bandcamp" aria-hidden="true"></i>
	        	</a>
	        </div>
	        <div class="live-tile exclude small" style="background: #537895;">
	        	<a href="javascript:;" hidefocus="true" data-type='28' data-url='${request.contextPath}/pxs/list.do?indexs=1'>
		    	    <span class="tile-title">培训师管理</span>
		    	    <i class="fa fa-ioxhost" aria-hidden="true"></i>
	        	</a>
	        </div>
		</div>

		<div class="items fn-clear">
			<h2>经营目录管理</h2>
		    <div class="live-tile exclude small J_indexsNumImgWrap" style="background: #027194;">
			    <a href="javascript:;" hidefocus="true" data-type='7' data-url='${request.contextPath}/analysis/list.do?indexs=1'>
			    	<span class="tile-title">消费者分析</span>
			    	<i class="fa fa-sheqel" aria-hidden="true"></i>
		    	    <img src="${request.contextPath}/img/indexsnum/3.png" class="J_indexsNumImg">
			    </a>
		    </div>
		    <div class="live-tile exclude small J_indexsNumImgWrap" style="background: #3d775e;">
	        	<a href="javascript:;" hidefocus="true" data-type='14' data-url='${request.contextPath}/fxtreestore/zlist.do?indexs=1'>
		    	    <span class="tile-title">自定义经营目录</span>
		    	    <i class="fa fa-balance-scale" aria-hidden="true"></i>
		    	    <img src="${request.contextPath}/img/indexsnum/4.png" class="J_indexsNumImg">
	        	</a>
	        </div>
		    <div class="live-tile exclude small J_indexsNumImgWrap" style="background: #bd526a;">
	        	<a href="javascript:;" hidefocus="true" data-type='20' data-url='${request.contextPath}/fxtreestore/managementlist.do?indexs=1'>
		    	    <span class="tile-title">经营品种</span>
		    	    <i class="fa fa-wpforms" aria-hidden="true"></i>
		    	    <img src="${request.contextPath}/img/indexsnum/5.png" class="J_indexsNumImg">
	        	</a>
	        </div>
		    <div class="live-tile exclude small" style="background: #51afaf;">
	        	<a href="javascript:;" hidefocus="true" data-type='25' data-url='${request.contextPath}/fxtreestore/zlists.do?indexs=1'>
		    	    <span class="tile-title">采购目录</span>
		    	    <i class="fa fa-bar-chart" aria-hidden="true"></i>
	        	</a>
	        </div>
		</div>

		<div class="items fn-clear">
			<h2>订单管理</h2>
		    <div class="live-tile exclude big" style="background: #66B281;">
			    <a id="J_shoppingCartIndex" class="J_shoppingCartIndex" href="javascript:;" hidefocus="true" data-type='22' data-url='${request.contextPath}/purchase/shoppingCart.do?indexs=1'>
			    	<span class="tile-title">我的购物车</span>
			    	<i class="fa fa-shopping-cart" aria-hidden="true"></i>
			    	<em></em>
			    </a>
		    </div>
		    <div class="live-tile exclude small" style="background: #2D8BEF;">
	        	<a href="javascript:;" hidefocus="true" data-type='23' data-url='${request.contextPath}/bill/list.do?indexs=1'>
		    	    <span class="tile-title">我的订单</span>
		    	    <i class="fa fa-btc" aria-hidden="true"></i>
	        	</a>
	        </div>
		</div>

		<div class="items showMoreItems fn-clear">
			<h2>报表分析</h2>
			
	        <div class="live-tile exclude small" style="background: #965f86;">
			    <a href="javascript:;" hidefocus="true" data-type='5' data-url='${request.contextPath}/fxorderfx/list2.do?indexs=1&type=2'>
			    	<span class="tile-title">联合用药综合分析</span>
			    	<i class="fa fa-pie-chart" aria-hidden="true"></i>
			    </a>
		    </div>
			
			<div class="live-tile exclude small" style="background: #81b7d2;">
			    <a href="javascript:;" hidefocus="true" data-type='5' data-url='${request.contextPath}/fxorderfx/list.do?indexs=1&type=2'>
			    	<span class="tile-title">联合用药分析</span>
			    	<i class="fa fa-fire" aria-hidden="true"></i>
			    </a>
		    </div>
		    <div class="live-tile exclude small" style="background: #1fab97;">
			    <a href="javascript:;" hidefocus="true" data-type='5' data-url='${request.contextPath}/fxsaleanalysis/list.do?indexs=1&type=2'>
			    	<span class="tile-title">销售分析</span>
			    	<i class="fa fa-diamond" aria-hidden="true"></i>
			    </a>
		    </div>
		    <div class="live-tile exclude small" style="background: #f5576c;">
			    <a href="javascript:;" hidefocus="true" data-type='26' data-url='${request.contextPath}/fxtreetask/list.do?indexs=1'>
			    	<span class="tile-title">培训管理</span>
			    	<i class="fa fa-compass" aria-hidden="true"></i>
			    </a>
		    </div>
            <div class="live-tile exclude small" style="background: #4e4c4c;">
            	<a href="javascript:;" hidefocus="true" data-type='21' data-url=''>
    	    	    <span class="tile-title">展开更多</span>
    	    	    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
            	</a>
            </div>
	        <div class="live-tile exclude small" style="background: #F6BD0B;">
	        	<a href="javascript:;" hidefocus="true" data-type='17' data-url='${request.contextPath}/fxsaleanalysis/gmllist.do?indexs=1'>
		    	    <span class="tile-title">千人购买率</span>
		    	    <i class="fa fa-area-chart" aria-hidden="true"></i>
	        	</a>
	        </div>
		    <div class="live-tile exclude small" style="background: #3e3994;">
	        	<a href="javascript:;" hidefocus="true" data-type='16' data-url='${request.contextPath}/fxsaleanalysis/scorelist.do?indexs=1'>
		    	    <span class="tile-title">品种分析</span>
		    	    <i class="fa fa-cubes" aria-hidden="true"></i>
	        	</a>
	        </div>
	        <div class="live-tile exclude small" style="background: #04AADF;">
   				<a href="javascript:;" hidefocus="true" data-type='6' data-url='${request.contextPath}/fxsaleanalysis/zlist.do?indexs=1&type=2'>
		    	    <span class="tile-title">综合分析</span>
		    	    <i class="fa fa-connectdevelop" aria-hidden="true"></i>
	        	</a>
	        </div>
            <div class="live-tile exclude small" style="background: #EE4D91;">
            	<a href="javascript:;" hidefocus="true" data-type='19' data-url='${request.contextPath}/fxsaleanalysis/xklist.do?indexs=1'>
        	    	<span class="tile-title">集客品种分析</span>
        	    	<i class="fa fa-gear" aria-hidden="true"></i>
        	    </a>
            </div>
		    <div class="live-tile exclude small" style="background: #7496F6;">
			    <a href="javascript:;" hidefocus="true" data-type='13' data-url='${request.contextPath}/analysis/diseaselist.do?indexs=1'>
			    	<span class="tile-title">病种配方树报表</span>
			    	<i class="fa fa-gg" aria-hidden="true"></i>
			    </a>
		    </div>
    	    <div class="live-tile exclude small" style="background: #BE1E4A;">
    		    <a href="javascript:;" hidefocus="true" data-type='2' data-url='${request.contextPath}/fxsaleanalysis/pricebandlist.do?indexs=1&type=2'>
    		    	<span class="tile-title">价格带分析</span>
    		    	<i class="fa fa-superpowers" aria-hidden="true"></i>
    		    </a>
    	    </div>
            <div class="live-tile exclude small" style="background: #00A05D;">
            	<a href="javascript:;" hidefocus="true" data-type='2' data-url='${request.contextPath}/fxsaleanalysis/priceband2list.do?indexs=1&type=4'>
    	    	    <span class="tile-title">价格带占比分析</span>
    	    	    <i class="fa fa-bell" aria-hidden="true"></i>
            	</a>
            </div>
            <div class="live-tile exclude small" style="background: #98D760;">
            	<a href="javascript:;" hidefocus="true" data-type='18' data-url='${request.contextPath}/fxsaleanalysis/customerbandlist.do?indexs=1'>
    	    	    <span class="tile-title">顾客群分析</span>
    	    	    <i class="fa fa-hourglass-2" aria-hidden="true"></i>
            	</a>
            </div>
            <div class="live-tile exclude small" style="background: #DB562D;">
            	<a href="javascript:;" hidefocus="true" data-type='2' data-url='${request.contextPath}/fxsaleanalysis/eliminatelist.do?indexs=1&type=1'>
    	    	    <span class="tile-title">淘汰分析</span>
    	    	    <i class="fa fa-sitemap" aria-hidden="true"></i>
            	</a>
            </div>
		</div>
	</div>
	
</div>

<div id="info-form" class="modal fade J_indexsModal" aria-hidden="true"  ></div>
<div id="info-formS" class="modal fade J_indexsModal" aria-hidden="true"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	        <div class="ibox float-e-margins">
	            <div class="ibox-title">
	                <h5>门店选择<span class="J_quick_dura"></span></h5>
	                <div class="ibox-tools">
	                    <a class="close-link"  data-dismiss="modal" >
	                        <i class="fa fa-times"></i>
	                    </a>
	                </div>
	            </div>
	            <div class="ibox-content">
	                <div class="tabs-container" id="J_formBind">
	                    <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tbl" aria-describedby="DataTables_Table_0_info">
	                        <thead>
	                            <tr role="row">
	                                <th>门店名称</th>
	                                <th>地址</th>
	                                <th>操作</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
<div id="info-formSF" class="modal fade J_indexsModal" aria-hidden="true"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	        <div class="ibox float-e-margins">
	            <div class="ibox-title">
	                <h5>门店文件选择<span class="J_quick_dura"></span></h5>
	                <div class="ibox-tools">
	                    <a class="close-link"  data-dismiss="modal" >
	                        <i class="fa fa-times"></i>
	                    </a>
	                </div>
	            </div>
	            <div class="ibox-content">
	                <div class="tabs-container" id="J_formBind">
	                    <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tblF" aria-describedby="DataTables_Table_0_info">
	                        <thead>
	                            <tr role="row">
	                                <th>文件名称</th>
	                                <th>销售时间</th>
	                                <th>操作</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>

<textarea class="fn-hide" id="T_bottom_tbl">
{{#each this}}
<tr>
	<td>{{name}}</td>
	<td>{{address}}</td>
	<td>
		<a class="J_choseStoreBtn" href="javascript:;" onclick="choseStore(this, '{{id}}', '{{name}}');">选择</a>
	</td>
</tr>
{{/each}}
</textarea>
<textarea class="fn-hide" id="T_bottom_tblF">
{{#each this}}
<tr>
	<td>{{name}}</td>
	<td>{{retIndexFiledate startdate enddate}}</td>
	<td>
		<a class="J_choseStoreFileBtn" href="javascript:;" onclick="choseStoreFile(this, '{{id}}', '{{name}}', '{{startdate}}', '{{enddate}}');">选择</a>
	</td>
</tr>
{{/each}}
</textarea>

<div id="zuIframe" class="zuIframe fn-hide"></div>

<script>

var iframeObj = null,
	layerWin = null,
	curStoreIdIndex = '',
	curSaleFileIndex = '',
	ajaxFlag = 0,
	storeTotal = 0,
	saleFileTotal = 0,
	curItemType = '',
	cookieIndexs = null,
	findStoreFlag = 0,
	findFileFlag = 0,
	showMoreFlag = 0,
	zuiIntro = null;

var userId = '${Session.USER.id}';

$(function () {

	if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){
		$("#zuIframe").addClass("iosIpad");
		$("#zwrapper").css({"width": "900px"});
	}


	$("#J_class_items").find(".live-tile, .flip-list").not(".exclude").liveTile();
	if(userId == 'D47BCD149F544752A5E0DCF1AAAB11D3'){
	}else{
		// window.location.href = '${request.contextPath}/index.do';
		// return false;
	}

	/*cookieIndexs = $.cookie('cookieIndexs');
	if(cookieIndexs){
		cookieIndexs = JSON.parse(cookieIndexs);
	}else{
		cookieIndexs = {
			storeid: '',
			storename: '',
			fileid: '',
			filename: ''
		}
	}

	getStores(true);*/

	initPage();

});

function getStores(flag){
	findStoreFlag = 0;
	findFileFlag = 0;
	createBlock("#zwrapper", '正在努力加载数据...');
	$.PostJson("${request.contextPath}/store/stores.do", "", function(state, json){
	    if(state == 'success'){
	        if(json && json.totalRows !== 0){
	        	storeTotal = json.totalRows;
	            $("#J_bottom_tbl tbody").temp($("#T_bottom_tbl").val(), json.data);

	            if(cookieIndexs.storeid){
	            	for(var i=0,len=json.data.length; i<len; i++){
	            		if(cookieIndexs.storeid == json.data[i].id){

	            			findStoreFlag = 1;

	            			curStoreIdIndex = cookieIndexs.storeid;
	            			cookieIndexs.storename = json.data[i].name;
	            			$("#J_curstore").text(cookieIndexs.storename).parent().attr({"title": cookieIndexs.storename});
	            			$(".J_choseStoreBtn").eq(i).text("已选择");
	            		}
	            	}
	            }

	            if(!findStoreFlag){
	            	curStoreIdIndex = json.data[0].id;
	            	$("#J_curstore").text(json.data[0].name).parent().attr({"title": json.data[0].name});
	            	$(".J_choseStoreBtn").eq(0).text("已选择");

	            	cookieIndexs.storeid = curStoreIdIndex;
	            	cookieIndexs.storename = json.data[0].name;
	            	setCookieIndexs();
	            }
	        }else{
	            MyFun.to.e(json.message || "查询药店列表失败");
	        }
	    }
	    unBlock("#zwrapper");

	    if(flag){
		    setTimeout(function(){
		    	getStoreFile();
		    }, 300)
	    }
	})
}

function getStoreFile(){
	createBlock("#zwrapper", '正在努力加载数据...');
	$.PostJson("${request.contextPath}/fxsalefile/combobox.do", "storeid="+curStoreIdIndex, function(state, json){
	    if(state == 'success'){
	        if(json && json.totalRows !== 0){
	        	saleFileTotal = json.totalRows;
	            $("#J_bottom_tblF tbody").temp($("#T_bottom_tblF").val(), json.data);

	            if(cookieIndexs.fileid){
	            	for(var i=0,len=json.data.length; i<len; i++){
	            		if(cookieIndexs.fileid == json.data[i].id){

	            			findFileFlag = 1;

	            			curSaleFileIndex = cookieIndexs.fileid;
	            			var startdate = json.data[i].startdate,
		            			enddate = json.data[i].enddate;
		            		startdate = new Date(startdate).formatDD( "yyyy-MM-DD");
		            		enddate = new Date(enddate).formatDD( "yyyy-MM-DD");
	            			cookieIndexs.filename = json.data[i].name+'（'+startdate+' 至 '+enddate+'）';
	            			$("#J_curstoreFile").text(cookieIndexs.filename).parent().attr({"title": cookieIndexs.filename});
	            			$(".J_choseStoreFileBtn").eq(i).text("已选择");
	            		}
	            	}
	            }

	            if(!findFileFlag){
	            	curSaleFileIndex = json.data[0].id;
	            	// var name = json.data[0].name+' - '+json.data[0].imptype;
        			var startdate = json.data[0].startdate,
            			enddate = json.data[0].enddate;
            		startdate = new Date(startdate).formatDD( "yyyy-MM-DD");
            		enddate = new Date(enddate).formatDD( "yyyy-MM-DD");
        			var name = json.data[0].name+'（'+startdate+' 至 '+enddate+'）';
	            	$("#J_curstoreFile").text(name).parent().attr({"title": name});
	            	$(".J_choseStoreFileBtn").eq(0).text("已选择");

	            	cookieIndexs.fileid = curSaleFileIndex;
	            	cookieIndexs.filename = name;
	            	setCookieIndexs();
	            }
	        }else if(json.totalRows == 0){
	        	saleFileTotal = 0;
	        	$("#J_bottom_tblF tbody").html('');
	        	curSaleFileIndex = '';
	        	$("#J_curstoreFile").text('请选择').parent().attr({"title": '请选择'});
	        	MyFun.to.e("暂无文件信息，请先上传");

	        	cookieIndexs.fileid = '';
	        	cookieIndexs.filename = '';
	        	setCookieIndexs();
	        }else{
	        	curSaleFileIndex = '';
	            MyFun.to.e(json.message || "查询药店文件列表失败");

	            cookieIndexs.fileid = '';
	            cookieIndexs.filename = '';
	            setCookieIndexs();
	        }
	    }
	    unBlock("#zwrapper");
	    if(!ajaxFlag){
	    	ajaxFlag = 1;
		    initPage();
	    }
	})
}

function initPage(){
	$(".live-tile").on("click", "a", function(){
		var _this = $(this),
			type = _this.data("type"),
			url = _this.data("url") || 'javascript:;',
			tit = _this.find(".tile-title").text();
		type = parseInt(type, 10);
		curItemType = type;

		/*if(type == 2){
			if(!curStoreIdIndex){
				MyFun.to.i("请选择门店");
				return false;
			}
			if(!curSaleFileIndex){
				MyFun.to.i("请选择文件");
				return false;
			}
		}

		if(type == 5){
			if(!curSaleFileIndex){
				MyFun.to.i("请选择文件");
				return false;
			}
		}

		if(type == 10){
			if(!curStoreIdIndex){
				MyFun.to.i("请选择门店");
				return false;
			}
		}

		if(type == 11){
			if(storeTotal){
				showStores();
			}else{
				MyFun.to.i("暂无门店信息，请先添加");
				setTimeout(function(){
					$('a[data-type="9"]').click();
				}, 500)
			}
			return false;
		}

		if(type == 12){
			if(!curStoreIdIndex){
				MyFun.to.i("请选择门店");
				return false;
			}

			if(saleFileTotal){
				showStoreFile();
			}else{
				MyFun.to.i("暂无文件信息，请先上传");
				setTimeout(function(){
					$('a[data-type="0"]').click();
				}, 500)
			}
			return false;
		}*/

		if(type == 21){
			var _p = _this.parent().parent();
			showMoreFlag = !showMoreFlag;
			if(showMoreFlag){
				_p.animate({"height": "515px"}, 300, function(){
					_this.find(".tile-title").text("收起更多");
				});
			}else{
				_p.animate({"height": "215px"}, 300, function(){
					_this.find(".tile-title").text("展开更多");
				});
			}
			return false;
		}

		document.title = tit;
		$(".logowrap span").text('三行药店品类管理系统 — '+tit);

		var _str = '<div class="zui_wrap"> <iframe id="J_iframe" width="100%" height="100%" src="'+url+'" frameborder="0" data-id="" seamless></iframe> </div>';

		$("#zuIframe").html(_str).css({"height": "auto"});
		$("#J_iframe").on("load", function(){
        	var _this = $(this);

            if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){
                $(".zui-header").css({"position": "fixed"});
                $(".zui-navbar").css({"top": "60px"});
                $("body.full-height-layout #page-wrapper").addClass("zuiPageWrapper");

                _this.contents().find("body").css({"overflow": "auto"});
                _this.contents().find(".modal .modal-dialog").css("cssText", "width:auto !important;");
            }
            _this.contents().find(".wrapper.wrapper-content").css("cssText", "margin-bottom:0 !important;");
            $("#zuIframe").css({"height": $(window).height()});
        });

		layerWin = layer.open({
			type: 1,
			fixed: false, //不固定
			maxmin: false,
			title: false,
			closeBtn: 0,
			content: $("#zuIframe"),
			end: function(){
				$("#zuIframe").html('');
			}
		});
		layer.full(layerWin);

		$("#J_closeIframe").removeClass("fn-hide");
		$(".J_nav_item ul").addClass("fn-hide");
		return false;
	})

	$("#J_closeIframe").on("click", function(){
		if(layerWin){
			layer.close(layerWin);
			$(this).addClass("fn-hide");

			$(".J_nav_item ul").removeClass("fn-hide");
			document.title = "三行药店品类管理系统";
			$(".logowrap span").text('三行药店品类管理系统');
		}
	})

	var showIntroFlag = $.cookie('cookieIndexShowIntro');
	if(showIntroFlag && showIntroFlag !== 'null' &&  showIntroFlag !== 'undefined'){
		$.cookie('cookieIndexShowIntro', '1', { expires: 7 });
	}else{
		// initIntro();
	}

	$("#J_index_showIntro").on("click", function(){
		$.cookie('cookieIndexShowIntro', null);
		if(zuiIntro){
			zuiIntro.start();
		}else{
			initIntro();
		}
	})

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

	/*$("#J_shoppingCartIndex").on("click", function(){
		var url = '${request.contextPath}/purchase/shoppingCart.do?indexs=1',
			tit = '我的购物车';
		document.title = tit;
		$(".logowrap span").text('三行药店品类管理系统 — '+tit);

		var _str = '<div class="zui_wrap"> <iframe id="J_iframe" width="100%" height="100%" src="'+url+'" frameborder="0" data-id="" seamless></iframe> </div>';

		$("#zuIframe").html(_str).css({"height": "auto"});
		$("#J_iframe").on("load", function(){
        	var _this = $(this);

            if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){
                $(".zui-header").css({"position": "fixed"});
                $(".zui-navbar").css({"top": "60px"});
                $("body.full-height-layout #page-wrapper").addClass("zuiPageWrapper");

                _this.contents().find("body").css({"overflow": "auto"});
                _this.contents().find(".modal .modal-dialog").css("cssText", "width:auto !important;");
            }
            _this.contents().find(".wrapper.wrapper-content").css("cssText", "margin-bottom:0 !important;");
            $("#zuIframe").css({"height": $(window).height()});
        });

		layerWin = layer.open({
			type: 1,
			fixed: false, //不固定
			maxmin: false,
			title: false,
			closeBtn: 0,
			content: $("#zuIframe"),
			end: function(){
				$("#zuIframe").html('');
			}
		});
		layer.full(layerWin);

		$("#J_closeIframe").removeClass("fn-hide");
		$(".J_nav_item ul").addClass("fn-hide");
		return false;
	})*/
}

function initIntro() {
    zuiIntro = introJs();
    zuiIntro.setOptions({
    	nextLabel: "下一个 &rarr;",
    	prevLabel: "&larr; 上一个",
    	skipLabel: "跳过引导",
    	doneLabel: "结束",
    	showProgress: true,
    	exitOnOverlayClick: false,
        steps: [
            {
                element: $('a[data-type="9"]').parent().get(0),
                intro: "点击选择一个门店，如果暂未添加门店，将自动跳转至门店管理界面。"
            },
            {
                element: $('a[data-type="8"]').parent().get(0),
                intro: "点击选择门店销售文件，如果暂未添加销售文件，将自动跳转至销售管理界面。"
            },
            {
                element: '#step3',
                intro: 'More features, more fun.',
                position: 'left'
            }
        ]
    });

    zuiIntro.start();
    zuiIntro.onexit(function(){
    	$.cookie('cookieIndexShowIntro', '1', { expires: 7 });
    });
}

function showStores(){
	$("#info-formS").modal('show');
	$('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
		$("#J_bottom_tbl tbody").html('');
	})

	$('#info-formS').off('shown.bs.modal').on('shown.bs.modal', function (e) {
		getStores();
	})
}

function showStoreFile(){
	$("#info-formSF").modal('show');
	$('#info-formSF').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
	})

	$('#info-formSF').off('shown.bs.modal').on('shown.bs.modal', function (e) {
	})
}

function choseStore(obj, storeid, storename){
	curStoreIdIndex = storeid;
	$("#J_curstore").text(storename).parent().attr({"title": storename});
	$("#info-formS").modal('hide');
	$("#J_bottom_tbl").find(".J_choseStoreBtn").text("选择");
	$(obj).text("已选择");

	cookieIndexs.storeid = storeid;
	cookieIndexs.storename = storename;
	setCookieIndexs();

	getStoreFile();
}

function choseStoreFile(obj, fileId, name, s, e){
	curSaleFileIndex = fileId;

	var filename = '',
		startdate = s,
		enddate = e;
	startdate = new Date(startdate).formatDD( "yyyy-MM-DD");
	enddate = new Date(enddate).formatDD( "yyyy-MM-DD");
	filename = name+'（'+startdate+' 至 '+enddate+'）';

	$("#J_curstoreFile").text(filename).parent().attr({"title": filename});
	$("#info-formSF").modal('hide');
	$("#J_bottom_tblF").find(".J_choseStoreFileBtn").text("选择");
	$(obj).text("已选择");

	cookieIndexs.fileid = fileId;
	cookieIndexs.filename = filename;
	setCookieIndexs();
}

function W_p_getStoreId(){
	return curStoreIdIndex;
}
function W_p_getSaleFileId(){
	return curSaleFileIndex;
}

function setCookieIndexs(){
	//设置、更新cookie
	//7天后失效
	$.cookie('cookieIndexs', JSON.stringify(cookieIndexs), { expires: 7 });
}

function W_p_getWindowWh(){
	var _attr = {
		"w": $(window).width(),
		"h": $(window).height()
	}
	return _attr;
}

Handlebars.registerHelper('retIndexFiledate', function(s, e) {
	var startdate = s,
		enddate = e;
	startdate = new Date(startdate).formatDD( "yyyy-MM-DD");
	enddate = new Date(enddate).formatDD( "yyyy-MM-DD");
	return startdate+" 至 "+enddate;
});

/**
 * 购物车
**/

//设置cookie
function setCartCookie(cartCookie, userId){
    var goodsTotal = cartCookie.length;
    // console.log(goodsTotal)
    if(goodsTotal){
        $("#J_shoppingCartIndex").find("em").text(goodsTotal).show();
    }else{
        $("#J_shoppingCartIndex").find("em").text(0).hide();
    }
    
    $.cookie('parent_cartCookie_'+userId, JSON.stringify(cartCookie), { expires: 7 });

    /*//触发子页面刷新
    if($("iframe[src*='purchase/shoppingCart.do']")){
        try{
            $("iframe[src*='purchase/shoppingCart.do']").get(0).contentWindow.refreshzPage();
        }catch(e){
            console.log(e);
        }
    }*/
}

function setCurPageCookie(obj, page){
    $.cookie(obj+'', page);
}
function getCurPageCookie(obj){
    return $.cookie(obj+'') || "1";
}
</script>
</body>
</html>