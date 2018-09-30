var common_js_basPath = '';

(function(){
	MyFun = {}; 
	MyFun.urlFun=function(surl,name,value){
		   var params =[];
    	   if (surl.indexOf("?") != -1) {   
    	      var str =surl.split("?")[1] ;   
    	      strs = str.split("&");   
    	      for(var i = 0; i < strs.length; i ++) {   
    	     	var param ={};
    	     	param.name=strs[i].split("=")[0];
    	     	param.value=strs[i].split("=")[1];
    	        if(param.name!= name) 
    	     		params.push(param);
    	      }   
    	   }   
    	  var param ={}; 
    	  param.name=name;
    	  param.value=value;
    	  params.push(param);
  		  var url=surl.split("?")[0] ;
  	      var queryurl="";
  		  for (var i=0;i<params.length;i++){
  			  if(i==0)
  				  queryurl=queryurl+params[i].name+"="+params[i].value;
  			  else
  				  queryurl=queryurl+"&"+params[i].name+"="+params[i].value;
  		  }
		return url+"?"+queryurl;
	}
	
	MyFun.objSave=function(formid,url,callback){
		var arguments1=Array.prototype.slice.call(arguments).slice(3);
		var _formId = $("#"+formid);
		_formId.find("input").each(function(){
			$(this).val($(this).val().replace(/(^\s+)|(\s+$)/g,""));
		})
		if(_formId.hasClass("J_judgeChage")){
			//判断是否修改
			var str = _formId.formSerialize(),
				zui_oldFormStr = _formId.data("zui_oldFormStr");
			if(str === zui_oldFormStr){
				$("#info-form").modal('hide');
				MyFun.to.i("无更改");
				return false;
			}
		}
		var options = {
				type:'post',
				url:url,
	            success: function (data) {
	            	if(data.code=='0000'){
	            		MyFun.to.s(data.message);
	            		 if(callback){
	            				var cn = eval('('+callback+')');
	            				if(typeof cn == 'function'){
	            					cn(arguments1);
	            				}
	            		 }
	            		$("#info-form").modal('hide');
	            	}else
	            		MyFun.to.e(data.message);
	            }
	        };
		if($("#"+formid).validate().form())
			$("#"+formid).ajaxSubmit(options);
	};

	
	MyFun.objOperate=function(url,title,callback){
		var arguments1=Array.prototype.slice.call(arguments).slice(3);
		layer.confirm(title, {
			  btn: ['确定','取消'] //按钮
			}, function(){
				layer.closeAll('dialog');
				  createBlock(".wrapper");	
				  $.ajax({
			             type: "GET",
			             url: url,
			             dataType: "json",
			             success: function(data){
			            	 	if(data.code=='0000'){
			            	 		MyFun.to.s(data.message);
			            	 		 if(callback){
				            				var cn = eval('('+callback+')');
				            				if(typeof cn == 'function'){
				            					cn(arguments1);
				            				}
				            		 }
			            	 	}else
				            		MyFun.to.e(data.message);
			             },
							complete:function(XMLHttpRequest,status){ 
								unBlock(".wrapper");
							}
			         });
			} );
	};
	
	MyFun.objDel=function(url,callback){
		var arguments1=Array.prototype.slice.call(arguments).slice(2);
		layer.confirm('确定删除吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				layer.closeAll('dialog');
				createBlock(".wrapper");
				  $.ajax({
			             type: "GET",
			             url: url,
			             dataType: "json",
			             success: function(data){
			            	 	if(data.code=='0000'){
			            	 		MyFun.to.s(data.message);
			            	 		 if(callback){
				            				var cn = eval('('+callback+')');
				            				if(typeof cn == 'function'){
				            					cn(arguments1);
				            				}
				            		 }
			            	 	}else
				            		MyFun.to.e(data.message);
			             },
							complete:function(XMLHttpRequest,status){ 
								unBlock(".wrapper");
							}
			         });
			} );
	};
	
	MyFun.ajaxRefreshTable=function(divid,url,callback){
		  if(!url)
			  url= $('#'+divid).data("url");
		  url= MyFun.urlFun(url,"_",new Date().getTime());
		  var data = "{";
	      var str =url.split("?")[1] ;   
	      var strs = str.split("&");   
	      for(var i = 0; i < strs.length; i ++) {   
	    	  data += "'"+strs[i].split("=")[0]+"'" + ":"+"'"+strs[i].split("=")[1]+"'"+",";
	      }
	      data += "}";
	      data = eval('('+data+')');
	      $.ajax({
	          	url: url.split("?")[0],
	          	data: data,
	          	dataType: "json",
	          	type: 'post',
	          	async: false,
				complete:function(XMLHttpRequest,status){
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if(sessionstatus && sessionstatus == "timeout") {
					    //如果超时就处理 ，指定要跳转的页面(比如登陆页)
					    setTimeout(function(){
						    MyFun.to.e("连接超时，请重新登录！");
					    }, 1000)
					    window.location.href = common_js_basPath+"/login.do";
					}
					if(XMLHttpRequest.statusText == 'error'){
                		MyFun.to.e("系统错误，请稍后再试！");
                	}else if(XMLHttpRequest.statusText.indexOf("NetworkError") !== -1){
                		MyFun.to.e("网络连接错误，请稍后再试！");
                	}
					$('#'+divid).empty();
					$('#'+divid).append(XMLHttpRequest.responseText);    
					$('#'+divid).data("url",url);
					if(typeof callback === 'function')
						callback();
				}
	      })

		  // $('#'+divid).find(".dataTables-example.dataTable").colResizable();
	};	
	
	
	MyFun.ajaxPageTable=function(divid,pagesize,currentpage){
		 var url= $('#'+divid).data("url");
		 if(pagesize&&pagesize!='')
			 url= MyFun.urlFun(url,"pageSize",pagesize);
		 if(currentpage&&currentpage!='')
			 url= MyFun.urlFun(url,"currentPage",currentpage);
    	 url= MyFun.urlFun(url,"_",new Date().getTime());
    	 MyFun.ajaxRefreshTable(divid,url);
	};	
	
	MyFun.ajaxJumpPageTable=function(divid,pagesize){
		 var url= $('#'+divid).data("url");
		 var currentpage= $("#pagejumpinput").val();
		 if(pagesize&&pagesize!='')
			 url= MyFun.urlFun(url,"pageSize",pagesize);
		 if(currentpage&&currentpage!='')
			 url= MyFun.urlFun(url,"currentPage",currentpage);
   	 url= MyFun.urlFun(url,"_",new Date().getTime());
   	 MyFun.ajaxRefreshTable(divid,url);
	};	
	
	MyFun.ajaxOrderTable=function(divid,sfield,stype){
		 var url= $('#'+divid).data("url");
		 if(sfield&&stype!=''){
			 url= MyFun.urlFun(url,"sfield",sfield);
		 	 url= MyFun.urlFun(url,"stype",stype);
		 	 url= MyFun.urlFun(url,"_",new Date().getTime());
		 	 MyFun.ajaxRefreshTable(divid,url);
		 }
	};	
	
	
	MyFun.search=function(formid,divid){
		   var a = $('#'+formid).serializeArray();
		   var url = $('#'+divid).data("url");

		   if(url.indexOf("currentPage") !== -1){
		   	url = zchangeURLPar(url, 'currentPage', '1');
		   }

	   	   var params =[];
	   	   $.each(a, function () {
		    	url=MyFun.urlFun(url,this.name,this.value);
	   	   });
	   	   MyFun.ajaxRefreshTable(divid,url);
	};
	
	MyFun.exportFile=function(divid){
		  var  url= $('#'+divid).data("url");
		  url= url.replace("table","exportfile")
		  url= MyFun.urlFun(url,"_",new Date().getTime());
		   var form = $('<form method="POST" action="' + url + '">');
           $('body').append(form);
           form.submit(); //自动提交
	};
	
	MyFun.searchParams=function(divid){
		   var url = $('#'+divid).data("url");
	   	   if(arguments.length>1){
	   		   for(var i=1 ;i<arguments.length;i=i+2){
	   			   url=MyFun.urlFun(url,arguments[i],arguments[i+1]);
	   		   }
	   	   }
	   	   MyFun.ajaxRefreshTable(divid,url);
	};
	
	
	MyFun.ajaxUrl=function(url){
		createBlock(".wrapper");
		  $.ajax({
	             type: "post",
	             url: url,
	             dataType: "json",
	             success: function(data){
	            	 	if(data.code=='0000'){
	            	 		MyFun.to.s(data.message);
	            	 	}else
		            		MyFun.to.e(data.message);
	             },
							complete:function(XMLHttpRequest,status){ 
								unBlock(".wrapper");
							}
	         });
	};

	
	
	MyFun.appendAjaxHtml=function(url,divid,callback){
		  var arguments1=Array.prototype.slice.call(arguments).slice(3);
		  if( $("#"+divid) ){
			  var _$divid = $("#"+divid);
			  _$divid.empty();
			  var htmlobj=$.ajax({url:url,async:false});
			  _$divid.append(htmlobj.responseText); 
			  
			  if(_$divid.find("form").hasClass("J_judgeChage")){
				  //如果需要判断是否修改
				  var str = _$divid.find("form").formSerialize();
				  _$divid.find("form").data("zui_oldFormStr", str);
			  }
		  }else{
			  var parentdiv=$('<div id="' +divid+  '" ></div>');  
			  var htmlobj=$.ajax({url:url,async:false});
			  parentdiv.append(htmlobj.responseText); 
			  $(document.body).append(parentdiv);  
		  }
      if(callback){
		var cn = eval('('+callback+')');
		if(typeof cn == 'function'){
			cn(arguments1);
		}
      }
	};
	
	

 
	
	var to={};
	MyFun.to=to;
	MyFun.to.s=function(msg){
		toastr.success(msg);
	};
	
	MyFun.to.i=function(msg){
		toastr.info(msg);
	};
	
	MyFun.to.e=function(msg){
		toastr.error(msg);
	};

	MyFun.isiFrame=function(){
		 if (self != top) 
			 return true;
		 else
			 return false;
	};
	
	
	
})();


/**
 * zw
**/
function openZuiframe(url, callback){
	if(url.indexOf("?") !== -1)
		url += "&_d="+(new Date().getTime());
	else
		url += "?_d="+(new Date().getTime());
	var _frame = '<iframe class="J_printWrap fn-hide" id="J_printWrapLogiIFR" src="" width="464" style="display: none;"> </iframe>';
	$(document.body).append(_frame).find("#J_printWrapLogiIFR").attr({"src": url}).on("load", function(){
		if(typeof callback == 'function')
			callback();
	});
}

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
                timeout: 60000,
				beforeSend: function (xhr) {
				    xhr.overrideMimeType("text/plain; charset=utf-8");
				},
                success: function(json) {
                	callback("success", json);
                },
                error: function(e) {
                	if(e.statusText == 'timeout'){
                		callback("error", {"rtnCode": "-100", "rtnMsg": "连接超时！"});
                	}else if(e.status == 500){
                		callback("error", null);
                		alert(0, "服务器错误");
                	}else if(e.statusText == 'error'){
                		MyFun.to.e("系统错误，请稍后再试！");
                		callback("error", null);
                	}else if(e.statusText.indexOf("NetworkError") !== -1){
                		MyFun.to.e("网络连接错误，请稍后再试！");
                	}else{
                		callback("error", null);
                	}
                },
				complete:function(XMLHttpRequest,status){ 
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if(sessionstatus && sessionstatus == "timeout") {
					    //如果超时就处理 ，指定要跳转的页面(比如登陆页)
					    setTimeout(function(){
						    MyFun.to.e("连接超时，请重新登录！");
					    }, 1000)
					    window.location.href = common_js_basPath+"/login.do";
					}
				}
            });
        }
    });
})(jQuery);

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


//阻止事件冒泡
function stopEvent(e){
  if(e && e.stopPropagation ){
      e.stopPropagation();
  }else{
      window.event.cancelBubble = true;
  }
}

//Loading提示
function createBlock(obj, txt){
	if(obj.indexOf(".") !== -1 || obj.indexOf("#") !== -1){
		if( $(obj).find(".blockElement").length<1 ){
			var text = txt || '正在努力加载数据...';
		    $(obj).block({ 
		        message: '<div class="fn-loading">'+text+'</div>', 
		        css: { border: '1px solid #DDD' , padding:"10px 20px" , textAlign: "left" ,width:'auto' } ,
		        overlayCSS:{ 
		            backgroundColor: '#333', 
		            opacity:  0.2, 
		            cursor: 'wait' 
		        }
		    });
		}
	}else{
		if( $("#"+obj).find(".blockElement").length<1 ){
			var text = txt || '正在努力加载数据...';
		    $("#"+obj).block({ 
		        message: '<div class="fn-loading">'+text+'</div>', 
		        css: { border: '1px solid #DDD' , padding:"10px 20px" , textAlign: "left" ,width:'auto' } ,
		        overlayCSS:{ 
		            backgroundColor: '#333', 
		            opacity:  0.2, 
		            cursor: 'wait' 
		        }
		    });
		}
	}
}
function unBlock(obj){
	if(obj.indexOf(".") !== -1 || obj.indexOf("#") !== -1){
		$(obj).unblock(); 
	}else{
	    $("#"+obj).unblock(); 
	}
}

var zuiUploadProgressTimer = null,
	zuiUploadProgressConter = 0;
function createBlockUpload(obj, type){
	zuiUploadProgressConter = 0;
	clearInterval(zuiUploadProgressTimer);
	zuiUploadProgressTimer = null;
	var str = '<div class="zuiUploadProgress fn-loading">正在上传...<s>0%</s></div>',
		_width = 'auto';
	if(type){
		str = '<div class="zuiUploadProgress"> <div class="progress"> <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width: 10em;width: 0%;">正在上传...<s>0%</s></div> </div> </div>';
		_width = '50%';
	}
	if(obj.indexOf(".") !== -1 || obj.indexOf("#") !== -1){
		if( $(obj).find(".blockElement").length<1 ){
		    $(obj).block({ 
		    	message: str, 
		        css: { border: '1px solid #DDD' , padding:"10px 20px" , textAlign: "left" ,width:_width} ,
		        overlayCSS:{ 
		            backgroundColor: '#333', 
		            opacity:  0.2, 
		            cursor: 'wait' 
		        }
		    });
		}
	}else{
		if( $("#"+obj).find(".blockElement").length<1 ){
		    $("#"+obj).block({ 
		        message: str, 
		        css: { border: '1px solid #DDD' , padding:"10px 20px" , textAlign: "left" ,width:_width } ,
		        overlayCSS:{ 
		            backgroundColor: '#333', 
		            opacity:  0.2, 
		            cursor: 'wait' 
		        }
		    });
		}
	}

	zuiUploadProgressTimer = setInterval(function(){
		$.PostJson(common_js_basPath+"/upload/progress.do", "", function(state, json){
		    if(state == 'success'){
		        if(json && json.code == '0000'){
		            if(json.data && json.data.bytesRead){
		            	zuiUploadProgressConter = zuiaccMul(zuiaccDiv((json.data.bytesRead+''), (json.data.contentLength+'')), 100);
			            zuiUploadProgressConter = zuiUploadProgressConter.toFixed(2);
		            }else{
			            zuiUploadProgressConter += .5;
		            }
		            if(zuiUploadProgressConter >= 100){
		            	zuiUploadProgressConter = 100;
		            	clearInterval(zuiUploadProgressTimer);
		            	zuiUploadProgressTimer = null;
		            }
		            $(".zuiUploadProgress s").text(zuiUploadProgressConter+"%");
		            if(type){
		            	$(".zuiUploadProgress .progress-bar").css({"width": zuiUploadProgressConter+"%"});
		            }
		        }
		    }
		})
	}, 500)
}

//除法函数，用来得到精确的除法结果  
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。  
//调用：accDiv(arg1,arg2)  
//返回值：arg1除以arg2的精确结果  
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
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。  
function zuiaccMul(arg1,arg2) {  
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length}catch(e){}  
    try{m+=s2.split(".")[1].length}catch(e){}  
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);  
}  

/*
 * 智能机浏览器版本信息:
 *
 */
var zuiBrowser = {
    versions: function() {
        var u = navigator.userAgent,
            app = navigator.appVersion;
        return { //移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/) || u.indexOf('iPad') > -1, //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}

$(function(){
	$("html head").append('<link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon" />');

	if(!$.blockUI){
		var script = document.createElement("script");
		script.src = common_js_basPath +"/js/plugins/blockUI/jquery.blockUI.js";
		$("html head").append(script);
	}

})

// 设置指定url中param的值，返回处理后的url
function zchangeURLPar(url,param,value){
    if(url.indexOf('?') != -1){  
        var p = new RegExp("(\\?|&"+param+")=[^&]*");
        if(p.test(url)){
            url = url.replace(p,"$1="+value);
        }else{
            url = url+'&'+param+'='+value;
        }
    }else{
            url = url+'?'+param+'='+value;
    }
    return url;
}   

/**
 * 获取微信url参数
 * var code =  getQueryString("code") ? getQueryString("code") : "";
**/
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var url = decodeURI(window.location);
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

;(function($){
	// data.formatDD( "yyyy-MM-DD hh:mm:ss");
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
})(jQuery);