<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>${data.title}</title>
<style>
html {
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%;
}
body {
	line-height: 1.6;
	font-family: -apple-system-font, "Helvetica Neue", sans-serif;
}
* {
	margin: 0;
	padding: 0;
}
img {
	max-width: 100%;
}
a img {
	border: 0;
}
a {
	text-decoration: none;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/  
::-webkit-scrollbar {  
    width: 3px;  
    height: 3px;  
    background-color: #F5F5F5;  
}
/*定义滚动条轨道 内阴影+圆角*/  
::-webkit-scrollbar-track {  
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);  
    border-radius: 3px;  
    background-color: #F5F5F5;  
}
/*定义滑块 内阴影+圆角*/  
::-webkit-scrollbar-thumb {  
    border-radius: 3px;  
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);  
    background-color: #bbb;  
}
.zui {
	margin: 0;
	padding: 0;
}
.zui .J_wrap {
	position: relative;
	width: 300px;
	height: 630px;
	background: url(${request.contextPath}/img/iphone.png) 0 0 no-repeat;
	background-size: 100%;
	display: block;
	margin: 0 auto;
}
.zui .J_wrap .J_content {
	position: absolute;
    top: 76px;
    left: 15px;
    width: 234px;
    height: 428px;
    overflow: auto;
    background: #fff;
    padding: 20px 15px 15px;
}
</style>
</head>
<body class="zui">

<div class="J_wrap">
	<div class="J_content">
		${data.content}
	</div>
</div>

<script src="${request.contextPath}/js/plugins/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/ueditor/dialogs/ueditor.parse.js"></script>
<script>

$(function(){
	uParse('.J_content',{
	    rootPath : '${request.contextPath}/js/plugins/ueditor/',
	    chartContainerHeight:500
	})

	$(".J_content").find("img").each(function(){
		var _src = $(this).attr("src");
		if(_src.indexOf("template/default/temp") !== -1){
			var newArr = _src.split('template/default/temp');
			if(newArr.length == 2){
				_src = '${request.contextPath}/template/default/temp'+newArr[1];
				$(this).attr("src", _src);
			}
		}
	})
})

</script>
</body>
</html>