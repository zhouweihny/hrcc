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
<style>
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
.J_separate {

}
.J_separate span {
  display: inline-block;
  padding: 5px 10px;
  border: 1px solid #ddd;
  margin: 0 5px 10px;
  cursor: default;
  color: #525151;
}
</style>
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>关键字检测</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="J_name">品名：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入品名" name="name">
                        </div>
                        <div class="form-group col-md-3">   
                            <label for="J_name">是否匹配：</label>           
                            <select id="iskeywords" name="iskeywords" class="form-control input-sm">  
                                <option value="" selected>所有</option>  
                                <option value="1">匹配</option>  
                                <option value="0">不匹配</option>  
                            </select>  
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','DrugikwordsList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                        
                    </div>
                    <div id="DrugikwordsList" data-url="${request.contextPath}/drugikwords/table.do">
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
                    <h5>分词信息<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <div class="J_separate" id="J_separate">
                          
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
var rangySelectionText = "",
  clickFlag = 0;

function refreshDrugikwordsList(){
  MyFun.ajaxRefreshTable("DrugikwordsList", "", function(){

  });
}

$(function(){

  MyFun.ajaxRefreshTable("DrugikwordsList", "", function(){
    var menu = new BootstrapMenu('#J_DrugikwordsList', {
      actionsGroups: [
        ['add'], ['del']
      ],
      actions: {
        add: {
          name: '新增关键字',
          onClick: function() {
            handleSelection("1");
          }
        }, 
        del: {
          name: '删除关键字',
          onClick: function() {
            handleSelection("2");
          }
        }
      }
    });
  });

})

function handleSelection(type){
  rangySelectionText = rangy.getSelection().toString().replace(/[\r\n]/g,"").replace(/\s+/g, "");
  if(!rangySelectionText){
    MyFun.to.i("请选择关键字");
    return false;
  }

  if(clickFlag)
    return false;
  clickFlag = 1;
  var url = '${request.contextPath}/keywords/save.do';
  if(type == '2'){
    url = '${request.contextPath}/keywords/delete.do';
  }
  createBlock(".wrapper", '正在努力加载数据...');
  $.PostJson(url, "word="+rangySelectionText, function(state, json){
      if(state == 'success'){
          if(json && json.code == '0000'){
              MyFun.to.s(json.message || "操作成功");
          }else{
              MyFun.to.e(json.message || "操作失败");
          }
      }
      clickFlag = 0;
      unBlock(".wrapper");
  })
}

function scankeywords(text){
  var text = text.replace(/[\r\n]/g,"").replace(/\s+/g, "");
  if(!text){
    MyFun.to.i("请选择关键字");
    return false;
  }

  if(clickFlag)
    return false;
  clickFlag = 1;
  var url = '${request.contextPath}/keywords/scankeywords.do';
  createBlock(".wrapper", '正在努力加载数据...');
  $.PostJson(url, "smart=&word="+text, function(state, json){
      if(state == 'success'){
          if(json && json.code == '0000'){
              if(json.data && json.data.length){
                var arr = [];
                for(var i=0, len=json.data.length; i<len; i++){
                  arr.push('<span>'+json.data[i]+'</span>');
                }
                $("#J_separate").html(arr.join(""));
                $("#info-formS").modal('show');
              }else{
                MyFun.to.i(json.message || "未查询到分词信息");
              }
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