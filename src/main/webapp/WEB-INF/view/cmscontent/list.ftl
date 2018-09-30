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
    <link href="${request.contextPath}/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.excheck.js"></script>
    <script src="${request.contextPath}/js/plugins/zeroclipboard/ZeroClipboard.js"></script>
    
    <script>
    window.UEDITOR_HOME_URL = '${request.contextPath}/js/plugins/ueditor/';
    </script>
    <script src="${request.contextPath}/js/my.js?v=1.0.0"></script>
    <script src="${request.contextPath}/js/plugins/ueditor/ueditor.config.js"></script>
    <script src="${request.contextPath}/js/plugins/ueditor/ueditor.all.js"></script>
    <script src="${request.contextPath}/js/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
    <script src="${request.contextPath}/js/plugins/colorpicker/colorpicker.min.js"></script>
    <script src="${request.contextPath}/js/plugins/jquery.nicescroll/jquery.nicescroll.min.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>内容管理</h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                         <label for="J_name">公共号：</label>
                         <@_syswxlist>
                         <@select id="wxid" datas=sysWxs headKey="" headText="所有"   defaultValue="" key="id" text="name" /> 
                         </@_syswxlist>
                        </div>
                      	<div class="form-group col-md-3">
                         <label for="J_name">状态：</label>
                         <@dictSelect  id="status"  code="cms.status" headKey="" headText="所有"   defaultValue="" />
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','CmsContentList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                         <!-- <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/cmscontent/byid.do','info-form')">新增</a> -->
                         <a data-toggle="modal" class="btn btn-primary"  data-backdrop="static" data-target="#info-formS" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/cmscontent/contentEidt.do','info-formS')">新增</a>
                    </div>
                    <div id="CmsContentList" data-url="${request.contextPath}/cmscontent/table.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>
<div id="info-formS" class="modal fade" aria-hidden="true" ></div>

<script>

  function refreshCmsContentList(){
    MyFun.ajaxRefreshTable("CmsContentList");
  }

  $(function(){
     MyFun.ajaxRefreshTable("CmsContentList");

  })
</script>
</body>
</html>

