var conf = 1; //控制服务，0表示本地假数据，1表示远程服务器数据
var srvMap = (function(){
    //var srcPref = ["ui/json/", "http://localhost:8080/wgw/"];//本机
    // var srcPref = ["ui/json/", "http://192.168.96.68:8080/"];//
    var srcPref = ["ui/json/", ""];//  远程服务器配置为空即可
    var dataArray = [
         {

         },
         {

         }
    ];
    
    return {
        add: function(uid, mockSrc, srvSrc) {
            dataArray[0][uid] = srcPref[conf] + mockSrc;
            dataArray[1][uid] = srcPref[conf] + srvSrc;
        },
        get: function(uid) {
            return dataArray[conf][uid];
        },
        getAppPath:function(){
            return srcPref[conf];
        },
        dataArrays:function(){
            return dataArray[conf];
        }
    };
})(jQuery);
window.dataArray = srvMap.dataArrays();

/*
* 扩展jQuery的Ajax方法，用于异步获取数据
* get方式，post方式，json返回
* 2个比较重要：GetJson  , PostJson
*/
(function($){
    $.extend({
        /*
        * post方式提交数据，适用于大数据提交。返回的JSON要符合规范。
        * 引号不能去掉。完整写法：{"key" , "value"}
        */
        PostJson: function(url, datas , callback, tokenFlag) {
            $.ajax({
                url: url,
                type: "POST",
                data : datas +"&_=" + (new Date()).getTime(),
                cache: false,
                dataType: "json",
                timeout: 20000,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=utf-8");
                    if(Util.getlSToken()){
                        xhr.setRequestHeader("token", Util.getlSToken());
                    }
                },
                success: function(json) {
                    if(window.conf == 0){
                        setTimeout(function(){
                            callback("success", json);
                        }, 1000)
                    }else{
                        callback("success", json);
                    }
                },
                error: function(e) {
                    if(e.statusText == 'timeout'){
                        callback("error", {"rtnCode": "-100", "rtnMsg": "连接超时！"});
                    }else{
                        callback("error", null);
                    }
                }
            });
        },
        AjaxHtml: function(url, datas , callback) {
            $.ajax({
                url: url,
                type: "GET",
                data : datas +"&_=" + (new Date()).getTime(),
                cache: false,
                dataType: "html",
                timeout: 60000,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=utf-8");
                },
                success: function(html) {
                    if(window.conf == 0){
                        setTimeout(function(){
                            callback("success", html);
                        }, 1000)
                    }else{
                        callback("success", html);
                    }
                },
                error: function(e) {
                    if(e.statusText == 'timeout'){
                        callback("error", {"rtnCode": "-100", "rtnMsg": "连接超时！"});
                    }else{
                        callback("error", null);
                    }
                },
                complete:function(XMLHttpRequest,status){ 
                    
                }
            });
        }
    });
})(jQuery);

/*
 * 获取url参数，多个参数
 * //Get object of URL parameters
 * var allVars = $.getUrlVars();
 * //Getting URL var by its nam
 * var getName = $.getUrlVar('name');
 */
(function($){
    $.extend({
        getUrlVars: function(){
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++)
            {
              hash = hashes[i].split('=');
              vars.push(hash[0]);
              vars[hash[0]] = hash[1];
            }
            return vars;
        },
        getUrlVar: function(name){
            return $.getUrlVars()[name];
        }
    });
})(jQuery)

//阻止事件冒泡
function stopEvent(e){
    if(e && e.stopPropagation ){
        e.stopPropagation();
    }else{
        window.event.cancelBubble = true;
    }
}
var NiceTools = {
    /*
     * 功能:删除数组元素.
     * 参数:dx删除元素的下标.
     * 返回:在原数组上删除后的数组
     */
    removeByIndex : function(arrays , dx){
        if(isNaN(dx)||dx>arrays.length){return false;}
        for(var i=0,n=0;i<arrays.length;i++)
        {
            if(arrays[i]!=arrays[dx])
            {
                arrays[n++]=arrays[i]
            }
        }
        arrays.length-=1
        return arrays;
    },
    //删除指定的item,根据数组中的值
    removeByValue : function(arrays, item ){
        for( var i = 0 ; i < arrays.length ; i++ ){
            if( item == arrays[i] )
            {
                break;
            }
        }
        if( i == arrays.length ){
            return;
        }
        for( var j = i ; j < arrays.length - 1 ; j++ ){
            arrays[ j ] = arrays[ j + 1 ];
        }
        arrays.length--;
        return arrays;
    }
}
/*
    handlebars扩展
    eg:
    $('#content').temp( $('#template'),  { name: "Alan" } );
    $('#content').temp( 'string' ,  { name: "Alan" } );
*/
;(function($){
    var compiled = {};
    $.fn.temp = function(template, data) {
        if(template instanceof jQuery){
            template = template.val();
        }
        compiled[template] = Handlebars.compile(template);
        this.html(compiled[template](data));
        return this;
    };

    $.fn.tempAppend = function(template, data) {
        if(template instanceof jQuery){
            template = template.val();
        }
        compiled[template] = Handlebars.compile(template);
        this.append(compiled[template](data));
        return this;
    };

    $.fn.tempPrepend = function(template, data) {
        if(template instanceof jQuery){
            template = template.val();
        }
        compiled[template] = Handlebars.compile(template);
        this.prepend(compiled[template](data));
        return this;
    };
})(jQuery);

/**
 * 设置当前时间为默认时间
 * type为true表示带时分秒
**/
function setCurDate(obj, type){
    var cDate = new Date();
    cDate = getCurDate(cDate, type);
    obj.val(cDate);
    obj.blur(function(){
        if(!obj.val()){
            setCurDate(obj, type);
        }
    })
}
/**
 * 设置当前时间为默认时间往前
 * type为true表示带时分秒
 * day 表示天数
**/
function setCurDateBefore(obj, day, type){
    var cDate = new Date().getTime();
    cDate = cDate - (parseInt(day, 10) * 24 * 60 * 60 * 1000);
    cDate = getCurDate(new Date(cDate), type);
    obj.val(cDate);
    obj.blur(function(){
        if(!obj.val()){
            setCurDateBefore(obj, day, type);
        }
    })
}
/**
 * 获取当前时间
 * type为true表示带时分秒
**/
function getCurDate(cDate, type){
    if(type && type == '2'){
        cDate = cDate.formatDD( "yyyy-MM-DD hh:mm:ss");
    }else if(type){
        cDate = cDate.formatDD( "yyyy-MM-DD hh:mm");
    }else{
        cDate = cDate.formatDD( "yyyy-MM-DD");
    }
    return cDate;
}
Date.prototype.formatDD = function( formatStr){ 
    var date = this;
    var str = formatStr; 
    str=str.replace(/yyyy|YYYY/,date.getFullYear()); 
    str=str.replace(/yy|YY/,(date.getYear() % 100)>9?(date.getYear() % 100).toString():"0" + (date.getYear() % 100)); 
    str=str.replace(/MM/,date.getMonth()>8?(date.getMonth()+1).toString():"0" + (date.getMonth()+1)); 
    str=str.replace(/M/g,date.getMonth()+1); 
    str=str.replace(/dd|DD/,date.getDate()>9?date.getDate().toString():"0" + date.getDate()); 
    str=str.replace(/d|D/g,date.getDate()); 
    str=str.replace(/hh|HH/,date.getHours()>9?date.getHours().toString():"0" + date.getHours()); 
    str=str.replace(/h|H/g,date.getHours()); 
    str=str.replace(/mm/,date.getMinutes()>9?date.getMinutes().toString():"0" + date.getMinutes()); 
    str=str.replace(/m/g,date.getMinutes()); 
    str=str.replace(/ss|SS/,date.getSeconds()>9?date.getSeconds().toString():"0" + date.getSeconds()); 
    str=str.replace(/s|S/g,date.getSeconds()); 
    return str; 
}
/**
 * 毫秒转日期
 *format(new Date().getTime(), 'yyyy-MM-dd HH:mm:ss')
**/
var format = function(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
//加法
function accAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2))
    return (arg1*m+arg2*m)/m;
}
//减法
function Subtr(arg1,arg2){
    var r1,r2,m,n;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    n=(r1>=r2)?r1:r2;
    return ((arg1*m-arg2*m)/m).toFixed(n);
}

function createLoading(txt){
    if(!txt)
        txt = '正在加载数据...';
    $.weui.loading(txt);
}

function unblockLoading(){
    $.weui.hideLoading();
}

var zuiTotastInter;
function zuiTotast(txt, time, flag){
    var _txt = txt || '提示',
        time = time || 2000;

    if(zuiTotastInter)
        clearInterval(zuiTotastInter);

    if(!$(".zui-totast").length){
        $("body").append('<div class="zui-totast">'+_txt+'</div>');
    }else{
        $(".zui-totast").html(_txt);
    }
    $(".zui-totast").addClass("enter-up-bounce");

    var _w = $(".zui-totast").width() + 45,
        _h = $(".zui-totast").height() + 20;
    $(".zui-totast").css({"marginLeft": -_w/2, "marginTop": -_h/2});

    if(!flag){
        zuiTotastInter = setTimeout(function(){
            $(".zui-totast").removeClass("enter-up-bounce");
        }, time)
    }
}

function zuiAlert(txt, callback){
    $.weui.alert(txt, function () {
        if(typeof callback == 'function')
            callback();
   });
}

function zuiConfirm(txt, succ, erro){
    $.weui.confirm(txt, function () {
        if(typeof succ == 'function')
            succ();
    }, function () {
        if(typeof erro == 'function')
            erro();
    });
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var url = decodeURI(window.location);
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
window.Util = {};
Util.lStorage = {
    /*
    localStorage只支持字符串，如果要放json，请先使用JSON.stringify()转换
    读取使用JSON.parse()读取
    */
    setParam: function(name, value) {
        localStorage.setItem(name, value);
    },
    getParam: function(name) {
        return localStorage.getItem(name);
    },
    removeParam:function(name){
        localStorage.removeItem(name);
    },
    clearParam:function(){
        //清除所有的存储，谨慎使用
        localStorage.clear();
    },
    paramSize:function(){
        return localStorage.length;
    },
    /*
        离线缓存管理器
    */
    cacheManager:new CacheManager(window.cacheCfg)
}

Util.sStorage = {
    /*
    sessionStorage只支持字符串，如果要放json，请先使用JSON.stringify()转换
    读取使用JSON.parse()读取
    */
    setParam: function(name, value) {
        sessionStorage.setItem(name, value);
    },
    getParam: function(name) {
        return sessionStorage.getItem(name);
    },
    removeParam:function(name){
        sessionStorage.removeItem(name);
    },
    clearParam:function(){
        //清除所有的存储，谨慎使用
        sessionStorage.clear();
    },
    paramSize:function(){
        return sessionStorage.length;
    }
}
/*
    离线缓存管理器
*/
function CacheManager(config){
    this.config=config;
}
/*
    从离线缓存中获取数据,当前方法有两个功能：
    1、从后台获取数据，第二个参数是一个回调函数，
    当离线缓存中没有要获取的数据时 或 当请求后台的入参值改变时，
    调用update方法从后台获取新数据，并覆盖旧数据
    2、获取本地插入的数据，第二个参数是一个字符串，
    当离线缓存中没有数据时直接返回空
*/
CacheManager.prototype.get=function(name,callback,param){
    var target=this.config[name];
    var cacheKey=target.key;
    var json=Util.lStorage.getParam(cacheKey);
    //当callback是方法时，表示从后台获取数据，需要使用回调处理数据
    if(typeof callback == 'function'&&target['url']){
        //realTime是在config对象中配置，
        //如果realTime配置true,表示每次都从后台取数据
        if(json&&!target.realTime){
            json=JSON.parse(json);
            if(json.param==param){
                callback('success',json);
                return;
            }
        }
        this.update(name,callback,param);
        return;
    }else{
        //callback不是方法的时候，即时返回数据
        return json;
    }
}
CacheManager.prototype.update=function(name,callback,param){
    var target=this.config[name];
    var cacheKey=target.key;
    //当callback是方法时，表示从后台刷新数据
    if(typeof callback == 'function'&&target.url){
        var _self = this;
        $.PostJson(target.url,param,function(state,json){
            if(state=='success'&&json.returnCode=='0'){
                json.param=param;
                Util.lStorage.setParam(cacheKey,JSON.stringify(json));
            }else{
                _self.del(name);
            }
            callback(state,json);
        },true);
    }else{
        //此时callback是字符串
        Util.lStorage.setParam(cacheKey,callback);
    }
}
CacheManager.prototype.del=function(name){
    var target=this.config[name];
    var cacheKey=target.key;
    Util.lStorage.removeParam(cacheKey);
}
CacheManager.prototype.clearAll=function(){
    for(var attr in this.config){
        var target=this.config[attr];
        var cacheKey=target.key;
        Util.lStorage.removeParam(cacheKey);
    }
}

//获取token
Util.getlSToken = function(){
    var token = Util.sStorage.getParam("userToken") || "";
    if(token)
        return token;
    else
        return null;
}

Util.getlSUserid = function(){
    var loginuser = Util.sStorage.getParam("loginuser") || "";
    loginuser = JSON.parse(loginuser);
    if(loginuser)
        return loginuser.id;
    else
        return null;
}

$(function(){
    /*var new_script = document.createElement("script");
    new_script.src = 'ui/js/weui/vconsole.min.js';
    document.getElementsByTagName("head")[0].appendChild(new_script);*/

    var ua = navigator.userAgent.toLowerCase();  
    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
        // createSign();
    }

    var _url = window.location.href;
    // Util.sStorage.removeParam("userToken");
    if( Util.getlSToken()){
        if(typeof globInit == 'function')
            checkJsApi();
    }else{

        if(
            _url.indexOf("index.html") != '-1' //首页
            || _url.indexOf("allMed.html") != '-1' //所有药品
            || _url.indexOf("myMed.html") != '-1' //收藏
            || _url.indexOf("choseDep.html") != '-1' //所有药品
            || _url.indexOf("baseInfo.html") != '-1' //收藏
            || _url.indexOf("editInfo.html") != '-1' //所有药品
            || _url.indexOf("changeMed.html") != '-1' //最新动态
        ){
            checkLogin();
        }
    }
})
function checkLogin(){
    srvMap.add("isLogin", "success.json", "/weixin2/login.do");//校验登录

    //检测登录
    var code =  getQueryString("code") ? getQueryString("code") : "";
    if(code){
        $.PostJson(srvMap.get("isLogin"), "code="+code, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    Util.sStorage.setParam("userToken", json.data.token);
                    Util.sStorage.setParam("loginuser", JSON.stringify(json.data.loginuser));

                    if(json.data.loginuser.districtid){
                        checkJsApi();
                    }else{
                        checkJsApiS();
                        // checkJsApi();
                    }
                }else{
                    zuiAlert(json.message || "校验登录失败！");
                    return false;
                }
            }
        })
    }else{
        checkJsApi();
    }
}

function judgedep(){
    srvMap.add("judgedep", "success.json", "/weixin2/judgedep.do");//校验是否选择关注科室
    $.PostJson(srvMap.get("judgedep"), "", function(state, json){
        if(state == 'success'){
            if(json){
                if(json.data){
                    globInitS();
                }else{
                    window.location.href = 'choseDep.html';
                }
            }else{
                zuiAlert("获取关注科室失败！");
                return false;
            }
        }
    })
}

function checkJsApi(){
    var _url = window.location.href;
    if(_url.indexOf("choseDep.html") != -1 || _url.indexOf("registDoc.html") != -1 || _url.indexOf("editInfo.html") != -1 || _url.indexOf("baseInfo.html") != -1 || _url.indexOf("medDetail.html") != -1){
        globInit();
    }else{
        getMenu();
    }
    $(".J_back").not(".newurl").on("click", function(){
        window.history.back();
    })
}

function getMenu(){
    $.AjaxHtml("ui/tpl/D_menu.html", "", function(state, html){
        $(".zui").append(html);

        var _url = window.location.href;
        if(_url.indexOf("index.html") != -1 || _url.indexOf("storeMed.html") != -1){
            $(".weui_tabbar").find(".weui_tabbar_item").eq(0).addClass("weui_bar_item_on");
        }else if(_url.indexOf("allMed.html") != -1){
            $(".weui_tabbar").find(".weui_tabbar_item").eq(1).addClass("weui_bar_item_on");
        }else if(_url.indexOf("myMed.html") != -1){
            $(".weui_tabbar").find(".weui_tabbar_item").eq(2).addClass("weui_bar_item_on");
        }

        globInit();
    })
}

function checkJsApiS(){
    srvMap.add("zjsapi", "success.json", "/weixin2/jsapiconfig.do");//获取微信jsapiconfig//获取微信权限
    $.PostJson(srvMap.get("zjsapi"), "url="+encodeURIComponent(location.href.split('#')[0])+"&jsapistr=getLocation", function(state, json){
        if(state == 'success'){
            if(json){
                // wx.config(json);
                wx.config({
                    debug: false,
                    appId: json.appId,
                    timestamp: json.timestamp,
                    nonceStr: json.nonceStr,
                    signature: json.signature,
                    jsApiList: ['getLocation']
                });
                
                wx.ready(function(){
                    wx.getLocation({
                        type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                        success: function (res) {
                            var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                            var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                            gps2Bmap(longitude, latitude);
                        },
                        fail: function(res){
                            // zuiAlert(JSON.stringify(res));
                            zuiAlert("获取当前位置失败，将无法查看所有药品品种信息！");
                            checkJsApi();
                        }
                    });
                })
            }else{
                zuiTotast( json.message || "获取微信位置权限失败");
            }
        }else{
            zuiTotast("获取微信位置权限失败");
        }
    });
}

function gps2Bmap(x, y){
    // 百度地图API功能
    //GPS坐标
    var ggPoint = new BMap.Point(x,y);

    //坐标转换完之后的回调函数
    translateCallback = function (data){
        console.log(JSON.stringify(data))
        if(data.status === 0) {
            sendPosToback(data.points[0].lng, data.points[0].lat);
            // Bmap2Posi();
        }
    }

    var convertor = new BMap.Convertor();
    var pointArr = [];
    pointArr.push(ggPoint);
    convertor.translate(pointArr, 1, 5, translateCallback);
}

function Bmap2Posi(x, y){
    var str = 'ak=bILQe2xAq23X5cBglcRWiUDqCXE6rgKy&callback=renderReverse&location='+x+','+y+'&output=json';
    $.PostJson('http://api.map.baidu.com/geocoder/v2/', str, function(state, json){
        console.log("==================geocoder")
        console.log(JSON.stringify(json))
    });
}

function sendPosToback(x, y){
    srvMap.add("updatelatlng", "success.json", "/weixin2/updatedistrict.do");//更新患者位置
    $.PostJson(srvMap.get("updatelatlng"), "lng="+x+"&lat="+y, function(state, json){
        checkJsApi();
    });
}


function setTitle(title){
    var $body = $('body');
    document.title = title;
    // hack在微信等webview中无法修改document.title的情况
    var $iframe = $('<iframe src="ui/img/arrow.png" style="display:none;"></iframe>').on('load', function() {
    setTimeout(function() {
        $iframe.off('load').remove()
    }, 0)
    }).appendTo($body)
}

