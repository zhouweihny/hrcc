var maxWidth = 0,
  navTabWidth = 0,
  zuiFlowNewFlag = false,
  basepath = '';

$(function(){
	maxWidth = $(window).width() - $("#side-menu").width() - 100 - 6;

    zuiFlowNewFlag = $(".J_nav_item .curuser").data("zuiflownewflag");
    basepath = $(".J_nav_item .curuser").data("basepath");

	$("#J_navTabShowMore").on("click", function(){
	  if($(this).find(".fa").hasClass("fa-angle-down")){
	    $(this).find(".fa").addClass("fa-angle-up").removeClass("fa-angle-down");
	  }else{
	    $(this).find(".fa").removeClass("fa-angle-up").addClass("fa-angle-down");
	  }
	  $("#min_title_list").toggleClass("showmore");
	})

    $("#side-menu").on("click", ".J_parent_li", function(){
        var _this = $(this),
            _dl = _this.find("dl"),
            _arrow = _this.find(".J_tithead .fa-angle-left"),
            _siblingsLi = _this.siblings(".J_parent_li"),
            cfindFlag = 0;

        _siblingsLi.each(function(){
            var _dthis = $(this);
            if(_dthis.hasClass("zactive")){
                cfindFlag = 1;
                _dthis.find("dl").slideUp(0, function(){
                    _dthis.find(".J_tithead .fa-angle-down").addClass("fa-angle-left").removeClass("fa-angle-down").end().removeClass("zactive");

                    _this.addClass("zactive");
                    _arrow.removeClass("fa-angle-left").addClass("fa-angle-down");
                    _dl.slideDown();
                })
            }
        })

        if(!cfindFlag){
            _this.addClass("zactive");
            _arrow.removeClass("fa-angle-left").addClass("fa-angle-down");
            _dl.slideDown();
        }
    })

    //菜单点击
    $(".J_menuItem").on('click',function(){
    	navTabWidth = 0;
    	var findFlag = 0;
        var _this = $(this),
            url = _this.data('href'),
        	tit = _this.text(),
        	iconClass = _this.find("i.fa").attr("class") ? _this.find("i.fa").attr("class") : _this.parent().parent().siblings("a").find("i.fa").attr("class");

        $("#content-main").find("iframe").each(function(){
        	var _curl = $(this).attr("src");
        	if(url == _curl){
        		var index = $(this).index();
        		$(this).removeClass("fn-hide").siblings("iframe").addClass("fn-hide");
        		$("#min_title_list").find("li").eq(index).addClass("active").siblings("li").removeClass("active");
        		document.title = tit;
        		findFlag = 1;
        		return false;
        	}
        })
        if(findFlag){
            $(".J_menuItem").removeClass("active");
            _this.addClass("active");
        	return false;
        }

        var _iframe = '<iframe width="100%" height="100%" src="'+url+'" frameborder="0" data-id="" seamless></iframe>';
        $("#content-main").find("iframe").addClass("fn-hide").end().append(_iframe).find("iframe:last").on("load", function(){
        	document.title = tit;

            if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){

                $(".zui-header").css({"position": "fixed"});
                $(".zui-navbar").css({"top": "60px"});
                $("body.full-height-layout #page-wrapper").addClass("zuiPageWrapper");

                $(this).contents().find("body").css({"overflow": "auto"});
                // $(this).contents().find(".wrapper .table.dataTables-example.dataTable").css({"width": "2000px"});
                // $(this).contents().find(".wrapper .table.dataTables-example.dataTable").next(".row").css({"width": "2000px"});

                $(this).contents().find(".modal .modal-dialog").css("cssText", "width:auto !important;");
            }
        });

        //创建tab
        var _navTab = '<li class="active" data-url="'+url+'"> <i class="'+iconClass+'"></i> <span>'+tit+'</span> <i class="fa fa-close"></i> </li>';
        $("#min_title_list").find("li").removeClass("active").end().append(_navTab).off("click").on("click", "li", function(e){
        	stopEvent(e);
        	var index = $(this).index(),
        		tit = $(this).find("span").text(),
                url = $(this).data("url");
        	$(this).addClass("active").siblings("li").removeClass("active");
        	$("#content-main").find("iframe").addClass("fn-hide").eq(index).removeClass("fn-hide");
        	document.title = tit;

            $("#side-menu .J_menuItem").each(function(){
                var _this = $(this),
                    _url = _this.data('href'),
                    _li = _this.parents(".J_parent_li"),
                    _siblingsLi = _li.siblings(".J_parent_li"),
                    _dl = _li.find("dl"),
                    _arrow = _li.find(".J_tithead .fa-angle-left");
                if(url === _url){
                    if(_li.hasClass("zactive")){
                        $(".J_menuItem").removeClass("active");
                        _this.addClass("active");
                    }else{
                        _siblingsLi.each(function(){
                            var _dthis = $(this);
                            if(_dthis.hasClass("zactive")){
                                _dthis.find("dl").slideUp(0, function(){
                                    _dthis.find(".J_tithead .fa-angle-down").addClass("fa-angle-left").removeClass("fa-angle-down").end().removeClass("zactive");

                                    _li.addClass("zactive").find(".J_menuItem").removeClass("active");;
                                    _arrow.removeClass("fa-angle-left").addClass("fa-angle-down");
                                    _dl.slideDown();
                                    _this.addClass("active");
                                })
                            }
                        })
                    }
                }
            })


        }).on("click", ".fa-close", function(e){
        	stopEvent(e);
        	var _li = $(this).parent(),
        		index = _li.index(),
                _url = _li.data("url");
            if(zuiFlowNewFlag && _url.indexOf("/space/zworkflow.do") !== -1){
                layer.confirm("下次登录系统是否不再直接显示该页面？", {
                  btn: ['确定','取消'] //按钮
                }, function(){
                    layer.closeAll('dialog');
                    $.PostJson(basepath + "/closenew.do", "", function(state, json){
                        if(state == "success"){
                            if(json && json.code == '0000'){
                                MyFun.to.s("下次登录系统将不再自动显示新手帮助页面。如有需要，请在药品管理目录中点击新手帮助菜单按钮。");
                            }else{
                                MyFun.to.e(json.message || "操作失败");
                            }
                        }
                        removeTab(_li, index, true);
                    })
                }, function(){
                    removeTab(_li, index, true);
                });
            }else{
                removeTab(_li, index);
            }
        });

        $("#min_title_list").find("li").each(function(){
          navTabWidth += $(this).width() + 20;
        })
        if(navTabWidth > maxWidth){
          $("#J_navTabShowMore").removeClass("fn-hide");
        }
        $(".J_menuItem").removeClass("active");
        _this.addClass("active");
        return false;
    });
    
	setTimeout(function(){
        $("#side-menu .J_parent_li").eq(0).click();
        if(zuiFlowNewFlag){
            $(".J_menuItem[href*='/space/zworkflow.do']").click();
        }else{
            $(".J_menuItem:eq(0)").click();
        }

        $.cookie('purchase_currentPage', '1');
        $.cookie('plan_currentPage', '1');
        $.cookie('bill_currentPage', '1');
	}, 300)

    $("#J_shoppingCartIndex").on("click", function(){
        openShoppingCart();
    })
});

//阻止事件冒泡
function stopEvent(e){
    if(e && e.stopPropagation ){
        e.stopPropagation();
    }else{
        window.event.cancelBubble = true;
	}
}

function removeTab(_li, index, cflag){
    if(_li.hasClass("active")){
        if(index == 0){
            if(_li.next().length || cflag){
                _li.next().addClass("active");
                $("#content-main").find("iframe").eq(index+1).removeClass("fn-hide");
            }else{
                toastr.info("至少保留一个吧^ ^");
                return false;
            }
        }else{
            if(_li.prev().length){
                _li.prev().addClass("active");
                $("#content-main").find("iframe").eq(index-1).removeClass("fn-hide");
            }
        }
    }
    _li.remove();
    $("#content-main").find("iframe").eq(index).remove();
}

/**
 * 购物车
**/

//设置cookie
function setCartCookie(cartCookie, userId){
    var goodsTotal = cartCookie.length;
    if(goodsTotal){
        $("#J_shoppingCartIndex").find("em").text(goodsTotal).show();
    }else{
        $("#J_shoppingCartIndex").find("em").text(0).hide();
    }
    
    $.cookie('parent_cartCookie_'+userId, JSON.stringify(cartCookie), { expires: 7 });

    //触发子页面刷新
    if($("iframe[src*='purchase/shoppingCart.do']")){
        try{
            $("iframe[src*='purchase/shoppingCart.do']").get(0).contentWindow.refreshzPage();
        }catch(e){
            console.log(e);
        }
    }
}

//打开购物车页面
function openShoppingCart(){
    var findFlag = 0,
        url = 'purchase/shoppingCart.do',
        tit = '我的购物车',
        iconClass = 'fa fa-shopping-cart';
    $("#content-main").find("iframe").each(function(){
        var _curl = $(this).attr("src");
        if(url == _curl){
            var index = $(this).index();
            $(this).removeClass("fn-hide").siblings("iframe").addClass("fn-hide");
            document.title = tit;
            findFlag = 1;

            $("#min_title_list li").eq(index).addClass("active").siblings("li").removeClass("active");
        }
    })

    if(!findFlag){
        var _iframe = '<iframe width="100%" height="100%" src="'+url+'" frameborder="0" data-id="" seamless></iframe>';
        $("#content-main").find("iframe").addClass("fn-hide").end().append(_iframe).find("iframe:last").on("load", function(){
            document.title = tit;
        });
        
        //创建tab
        var _navTab = '<li class="active" data-url="'+url+'"> <i class="'+iconClass+'"></i> <span>'+tit+'</span> <i class="fa fa-close"></i> </li>';
        $("#min_title_list").find("li").removeClass("active").end().append(_navTab).off("click").on("click", "li", function(e){
            stopEvent(e);
            var index = $(this).index(),
                tit = $(this).find("span").text(),
                url = $(this).data("url");
            $(this).addClass("active").siblings("li").removeClass("active");
            $("#content-main").find("iframe").addClass("fn-hide").eq(index).removeClass("fn-hide");
            document.title = tit;

            $("#side-menu .J_menuItem").each(function(){
                var _this = $(this),
                    _url = _this.data('href'),
                    _li = _this.parents(".J_parent_li"),
                    _siblingsLi = _li.siblings(".J_parent_li"),
                    _dl = _li.find("dl"),
                    _arrow = _li.find(".J_tithead .fa-angle-left");
                if(url === _url){
                    if(_li.hasClass("zactive")){
                        $(".J_menuItem").removeClass("active");
                        _this.addClass("active");
                    }else{
                        _siblingsLi.each(function(){
                            var _dthis = $(this);
                            if(_dthis.hasClass("zactive")){
                                _dthis.find("dl").slideUp(0, function(){
                                    _dthis.find(".J_tithead .fa-angle-down").addClass("fa-angle-left").removeClass("fa-angle-down").end().removeClass("zactive");

                                    _li.addClass("zactive");
                                    _arrow.removeClass("fa-angle-left").addClass("fa-angle-down");
                                    _dl.slideDown();
                                    _this.addClass("active");
                                })
                            }
                        })
                    }
                }
            })
        }).on("click", ".fa-close", function(e){
            stopEvent(e);
            var _li = $(this).parent(),
                index = _li.index(),
                _url = _li.data("url");
            removeTab(_li, index);
        });
    }
}

function setCurPageCookie(obj, page){
    $.cookie(obj+'', page);
}
function getCurPageCookie(obj){
    return $.cookie(obj+'') || "1";
}