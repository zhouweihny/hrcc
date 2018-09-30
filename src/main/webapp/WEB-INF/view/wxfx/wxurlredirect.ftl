<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>跳转</title>
<script src="${request.contextPath}/js/wx/js/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/wx/js/handlebars.js"></script>
<script src="${request.contextPath}/js/wx/js/wxzui_common.js"></script>
<script>

$(function(){

    var redirec = getQueryString("zuiurlrec") || 0;
    redirec = parseInt(redirec, 10);

    var url = '';

    switch(redirec){
        case 1: 
            // 消费者分析
            url = '${request.contextPath}/wxfx/wxxfzfx.do#/J_router_main';
            break;
        case 2: 
            // 消费者分析排名
            url = '${request.contextPath}/wxfx/wxxfzfxpm.do#/J_router_main';
            break;
        case 3: 
            // 联合用药进展
            url = '${request.contextPath}/wxfx/wxlhyyjz.do#/J_router_main';
            break;
        case 4: 
            // 联合用药评估
            url = '${request.contextPath}/wxfx/wxlhyyspxq.do#/J_router_main';
            break;
        case 5: 
            // 下载
            url = '${request.contextPath}/wxfx/wxxz.do#/J_router_main';
            break;
        case 6: 
            // 评估
            url = '${request.contextPath}/wxfx/wxhy.do#/J_router_main';
            break;
        default:
            url = '${request.contextPath}/indexs.do';
            break;
    }

    setTimeout(function(){
        window.location.href = url;
    }, 30)
})

</script>
</body>
</html>