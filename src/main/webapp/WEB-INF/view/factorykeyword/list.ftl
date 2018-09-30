<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 数据表格</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico"> <link href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
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
<script src="${request.contextPath}/js/plugins/BootstrapMenu/BootstrapMenu.min.js"></script>
<script src="${request.contextPath}/js/plugins/rangy/rangy-core.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<style type="text/css">
.dropdown-menu {
  position: absolute;
  top: 100%;
  left: 0;
  z-index: 1000;
  display: none;
  float: left;
  min-width: 160px;
  padding: 5px 0;
  margin: 2px 0 0;
  font-size: 14px;
  text-align: left;
  list-style: none;
  background-color: #fff;
  -webkit-background-clip: padding-box;
  background-clip: padding-box;
  border: 1px solid #ccc;
  border: 1px solid rgba(0,0,0,.15);
  border-radius: 4px;
  -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  box-shadow: 0 6px 12px rgba(0,0,0,.175);
}
.dropdown-menu>li>a {
  display: block;
  padding: 6px 20px;
  clear: both;
  font-weight: 400;
  line-height: 1.42857143;
  color: #333;
  white-space: nowrap;
  font-size: 15px;
}
</style>
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
                        <div class="form-group col-md-3">
                            <label for="J_name">关键词：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入关键词" name="word">
                        </div>
                       <div class="form-group col-md-3">
                           <label for="J_name">状态：</label>
                 		   <select   name="status" class="form-control input-sm">
                 		  	 <option value="">所有</option>
                 		  	 <option value="1">已添加</option>
                 		  	 <option value="0">未添加</option>
                  		   </select>
                        </div>
                        
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','FactoryKeywordList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                         <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/factorykeyword/byid.do','info-form')">新增</a>
                    </div>
                    <div id="FactoryKeywordList" data-url="${request.contextPath}/factorykeyword/table.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<script>
var rangySelectionText = "",
  clickFlag = 0;

  function refreshFactoryKeywordList(){
   	MyFun.ajaxRefreshTable("FactoryKeywordList");
  }

  $(function(){

     MyFun.ajaxRefreshTable("FactoryKeywordList", "", function(){
       var menu = new BootstrapMenu('.J_fac_keywords', {
        fetchElementData: function(rowElem) {
            return $(rowElem);
        },
         actionsGroups: [
           ['add']
         ],
         actions: {
           add: {
             name: '新增关键字',
             onClick: function(row) {
               var code = row.data("code");
               if(!code){
                MyFun.to.i("数据错误");
                return false;
               }
               handleSelection(code);
             }
           }
         }
       });
     });
  })

function handleSelection(code){
  rangySelectionText = rangy.getSelection().toString().replace(/[\r\n]/g,"").replace(/\s+/g, "");
  if(!rangySelectionText){
    MyFun.to.i("请选择关键字");
    return false;
  }

  if(clickFlag)
    return false;
  clickFlag = 1;
  createBlock(".wrapper", '正在努力加载数据...');
  $.PostJson('${request.contextPath}/factorykeyword/save.do', "word="+rangySelectionText+'&code='+code, function(state, json){
      if(state == 'success'){
          if(json && json.code == '0000'){
              MyFun.to.s(json.message || "操作成功");
              refreshFactoryKeywordList();
          }else{
              MyFun.to.e(json.message || "操作失败");
          }
      }
      clickFlag = 0;
      unBlock(".wrapper");
  })
}
</script>
</body>
</html>