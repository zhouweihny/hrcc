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
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<script src="${request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>基本 <small>分类，查找</small></h5>
				</div>
				<div class="ibox-content">
					<form class="form-inline row" id="J_formSearch" action="javascript:;">
						<input  type="hidden" value="desc" name="stype" >
						<input  type="hidden" value="createtime" name="sfield" >
						
					    <#if planid ??>
                         	<input  type="hidden" value="${planid}" name="planid" />
                        <#else>
                       	<#if Session.COMPANY.roleids?seq_contains("B1CC34941707470D9BF361D1CEF2B08E")==false>
						<div class="form-group col-md-3">
							<label for="J_name">供应商：</label>
							<input type="text" class="form-control" id="J_name" placeholder="输入供应商" name="supplier">
						</div>
						</#if>
						<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")==false>
						<div class="form-group col-md-3">
							<label for="J_name">客户：</label>
							<input type="text" class="form-control" id="J_name" placeholder="输入客户" name="purchaser">
						</div>
						</#if>         
					<!--			<#if Session.COMPANY.roleids?seq_contains("D776C89900D74CACA93834FE68880540")==false>
						<div class="form-group col-md-3">
							<label for="J_name">代理：</label>
							<input type="text" class="form-control" id="J_name" placeholder="输入代理" name="agent">
						</div>
						</#if>    
                       -->  
						<div class="form-group col-md-3">
							<label for="J_name">订单状态：</label>
							<select   name="status" class="form-control input-sm">
								<option value="">所有</option>  
					            <#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")>
					            <option value="-1">待发送</option>  
					           	</#if>  
								<option value="0">新订单</option>  
								<option value="1">已接收</option>  
								<option value="2">已确定</option>  
								<option value="3">已发货</option> 
							</select>	 
						</div>

						<div class="form-group col-md-3">
							<button type="button" class="btn btn-w-m btn-info" onclick="zbtnSearch();"><i class="fa fa-search"></i>查 询</button>
						</div>
						</#if>
						
					</form>

					<div id="BillList" data-url="${request.contextPath}/bill/table.do">
					</div>    
				</div>
			</div>
		</div>
	</div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>
<div id="info-formS" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>预计到货时间<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <div class="J_separate" id="J_separate">
	                        <label style="margin-bottom: 0;vertical-align: middle;">请选择日期：</label>
                        	<input style="vertical-align: middle;cursor: pointer;" class="form-control layer-date" id="startdate" name="startdate" readonly  >
                        </div>
                    </div>
                </div>
            </div>
	        <div class="modal-footer">
		        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary" onclick="saveFtiem();">保存</button>
	        </div>
        </div>
    </div>
</div>

<script>
var clickFlag = 0,
    btnClickFlag = 0,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    curBillId = '';

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
  	  if(window.self!= window.top){
     	 if(data.currentPage && !btnClickFlag){
        	window.parent.setCurPageCookie('bill_currentPage', data.currentPage);
    	  }else{
      	    data.currentPage = window.parent.getCurPageCookie('bill_currentPage');
    	  }
	  }
	  btnClickFlag = 0;
      var htmlobj=$.ajax({url:url.split("?")[0], data:data,dataType:"json",type:'post',async:false});
      $('#'+divid).empty();
      $('#'+divid).append(htmlobj.responseText);    
      $('#'+divid).data("url",url);

      if(typeof callback === 'function')
        callback();

    combinaTr();
};

function zbtnSearch(){
    btnClickFlag = 1;
    window.parent.setCurPageCookie('bill_currentPage', '1');
    MyFun.search('J_formSearch','BillList');
}

function refreshBillList(){
	MyFun.ajaxRefreshTable("BillList");
}

$(function(){

	if(indexsFlag && indexsFlag == '1'){
	    
	}else{
		var start = {
		    elem: '#startdate',
		    format: 'YYYY-MM-DD',
		    min: laydate.now(+0),
		    max: laydate.now(+14),
		    start: laydate.now(+0),  //开始日期
		    istime: false,
		    istoday: false,
		    choose: function (datas) {
		        setFtime(datas);
		    }
		};
		laydate(start);
	}
	MyFun.search('J_formSearch','BillList');

})

function combinaTr(){
	var _BillList = $("#BillList"),
		_tr = _BillList.find("tbody tr"),
		tempArr = [];
	_tr.each(function(k){
		var _this = $(this),
			planid = _this.data("planid"),
			createtime = _this.data("createtime"),
			findFlag = 0;
			tempAttr = {};

		for(var i=0,len=tempArr.length; i<len; i++){
			if(tempArr[i].planid == planid&&planid!=''){
				tempArr[i].count++;
				findFlag = 1;
			}
		}

		if(!findFlag){
			tempAttr.planid = planid;
			tempAttr.createtime = createtime;
			tempAttr.count = 1;
			tempAttr.index = k;
			tempArr.push(tempAttr);
		}
	})

	// console.log(JSON.stringify(tempArr));
	// [{"planid":"46F95C6D3AE24510A0D3945C49644F62","createtime":"2018-01-08 10:39","count":1,"index":0},{"planid":"","createtime":"2018-01-08 10:37","count":1,"index":1},{"planid":"B0E1BFA415424154AF5DDB79DA1527DE","createtime":"2018-01-02 16:55","count":1,"index":2},{"planid":"23F59515965D481FB9E52D49526E2A43","createtime":"2018-01-02 16:25","count":6,"index":3},{"planid":"20EA6385AC884F34B374403CC84EE5C5","createtime":"2018-01-02 16:25","count":1,"index":9}]
	var newArr = [];
	for(var j=0,jlen=tempArr.length; j<jlen; j++){
		var _arr = [];
		_arr.push(tempArr[j].index);
		_arr.push(tempArr[j].count);
		_arr.push(tempArr[j].createtime);
		newArr.push(_arr);
	}
	// console.log(JSON.stringify(newArr));
	//[[0,1,"2018-01-02 16:55"],[1,6,"2018-01-02 16:25"],[7,3,"2018-01-02 16:25"]]

	for(var m=0,mlen=newArr.length; m<mlen; m++){
		_tr.eq(newArr[m][0]).find("td:eq(0)").before('<td rowspan="'+newArr[m][1]+'">'+newArr[m][2]+'</td>');
	}
}

function sureBill(id){
	curBillId = id;
	$("#info-formS").modal('show');
}

function setFtime(date){
    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/bill/arrivaltime.do", "billid="+curBillId+"&arrivaltime="+date, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                // MyFun.to.s(json.message || "设置预计到货时间成功");
                clickFlag = 0;
                sureBills();
            }else{
                MyFun.to.e(json.message || "设置预计到货时间失败");
            }
        }else{
            MyFun.to.e("网络错误，请稍后重试");
        }
        clickFlag = 0;
        unBlock(".wrapper");
    })
}

function sureBills(){
	if(clickFlag)
	    return false;
	clickFlag = 1;
	createBlock(".modal-dialog", '正在努力加载数据...');
	$.PostJson("${request.contextPath}/bill/save.do", "id="+curBillId+"&status=2", function(state, json){
	    if(state == 'success'){
	        if(json && json.code == '0000'){
	            MyFun.to.s(json.message || "确认订单成功");

	            refreshBillList();
	            $("#info-formS").modal('hide');
	        }else{
	            MyFun.to.e(json.message || "确认订单失败");
	        }
	    }else{
	        MyFun.to.e("网络错误，请稍后重试");
	    }
	    clickFlag = 0;
	    unBlock(".wrapper");
	})
}
</script>
</body>
</html>