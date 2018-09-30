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
	font-family: -apple-system-font, "Helvetica Neue", sans-serif;
}
img {
	max-width: 100%;
}
a {
	text-decoration: none;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
</style>
</head>
<body >

<div id="preview">
	${data.content}
</div>

<script src="${request.contextPath}/js/plugins/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/ueditor/dialogs/ueditor.parse.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/lib/wxzui_common.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
srvMap.add("zjsapi", "success.json", "${request.contextPath}/wx/<@_properties key='wx.store.code'>${propertie}</@_properties>/jsapiconfig.do");//获取微信jsapiconfig//获取微信权限

var _zdes = "",
  _zimg = "${basePath}/img/card3.jpg";

$(function(){
	uParse('#preview',{
	    rootPath : '${request.contextPath}/js/plugins/ueditor/',
	    chartContainerHeight:500
	})

	$("#preview").find("img").each(function(){
		var _src = $(this).attr("src");
		if(_src.indexOf("template/default/temp") !== -1){
			var newArr = _src.split('template/default/temp');
			if(newArr.length == 2){
				_src = '${request.contextPath}/template/default/temp'+newArr[1];
				$(this).attr("src", _src);
			}
		}
	})

  _zdes = $("#preview").text().substring(0, 50);

	$.PostJson(srvMap.get("zjsapi"), "url="+encodeURIComponent(location.href.split('#')[0])+"&jsapistr=onMenuShareTimeline,onMenuShareAppMessage,onMenuShareQQ,onMenuShareWeibo,onMenuShareQZone", function(state, json){
	    if(state == 'success'){
	        if(json){
	            // wx.config(json);
	            wx.config({
                debug: false,
                appId: json.appId,
                timestamp: json.timestamp,
                nonceStr: json.nonceStr,
                signature: json.signature,
                jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone']
	            });

	            wx.ready(function(){
            		initShare();
	            })
	        }else{
            alert( json.message || "获取微信权限失败");
	        }
	    }else{
        alert("获取微信权限失败");
	    }
	});
})

function initShare(){
  var cdata = {
    title: document.title,
    desc: _zdes,
    link: window.location.href,
    imgUrl: _zimg,
    fail: function (res) {
      alert(JSON.stringify(res));
    }
  }

	// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
  wx.onMenuShareAppMessage(cdata);

  // 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
  wx.onMenuShareTimeline(cdata);

	// 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
  wx.onMenuShareQQ(cdata);
	
	// 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
  wx.onMenuShareWeibo(cdata);

	// 2.5 监听“分享到QZone”按钮点击、自定义分享内容及分享接口
  wx.onMenuShareQZone(cdata);

}

</script>
</body>
</html>