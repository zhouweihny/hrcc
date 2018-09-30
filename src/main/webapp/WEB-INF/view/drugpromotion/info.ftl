<#include "../tools/select.ftl"  /> 
        <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal J_judgeChage" id="save_form">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><#if data??>编辑<#else>新增</#if></h5>
                        <div class="ibox-tools">
                            <a class="close-link"  data-dismiss="modal" >
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
	 					  	<input  type="hidden" name="id" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">药品：</label>
                                <div class="col-sm-8">
 								<select id="drugid" name="drugid" class="form-control " style="width: 360px;" ></select>                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">促销内容：</label>
                                <div class="col-sm-8">
                                	 <textarea name="content" class="form-control" placeholder="请输入促销内容" aria-required="true" required="" ><#if data??><#if data.content??>${data.content}</#if></#if></textarea>
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">开始时间：</label>
                                <div class="col-sm-8">
                                    <input type="text" id="startdate" name="startdate" placeholder="请输入开始时间" class="form-control"  value="<#if data??><#if data.startdate??>${(data.startdate?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">结束时间：</label>
                                <div class="col-sm-8">
                                    <input type="text" id="enddate" name="enddate" placeholder="请输入结束时间" class="form-control"  value="<#if data??><#if data.enddate??>${(data.enddate?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="remark" placeholder="请输入备注" class="form-control"  value="<#if data??><#if data.remark??>${data.remark}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/drugpromotion/save.do','refreshDrugPromotionList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>

        
        
        
        <script>
        
   
$(function(){     
    $('#drugid').select2({
      ajax: {
          url: "${request.contextPath}/drug/drugs.do",
          type : "POST",
          dataType: 'json',
          delay: 250,
          data: function(params) {
              return {
                  name: params.term, // search term
                  page: params.page
              };
          },
          processResults: function(data, params) {
              params.page = params.page || 1;
              var _cdata = data.data,
                  // _cArr = [{"id": "-1", "text": "全部"}];
                  _cArr = [];
              if(_cdata.length){
                  for(var i=0, len=_cdata.length; i<len; i++){
                      var attr = {};
                      attr.id = _cdata[i].id;
                      attr.name = _cdata[i].name;
                      attr.text = _cdata[i].name+" / "+_cdata[i].specifications+" / "+_cdata[i].factory;
                      _cArr.push(attr);
                  }
              }
              return {
                  results: _cArr,
                  pagination: {
                      more: (params.page * 30) < data.data.totalRows
                  }
              };
          },
          cache: true
      },
      escapeMarkup: function(markup) {
          return markup;
      }, // let our custom formatter work
      minimumInputLength: 1,
      placeholder: "请输入药品名称",
      allowClear: true,
      language: "zh-CN",
      dropdownAutoWidth: true,
      templateSelection: function(data){
        if(data.id && data.id !== -1)
          return data.name;
        return "请输入药品名称";
      }
  })    
});

setTimeout(function () {
   //日期范围限制
        var startdate = {
            elem: '#startdate',
            format: 'YYYY-MM-DD hh:mm',
            max: laydate.now(+1),
            start: '2018-01-01',  //开始日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        laydate(startdate);
        
	  var enddate = {
            elem: '#enddate',
            format: 'YYYY-MM-DD hh:mm',
            start: '2018-01-01',  //开始日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        laydate(enddate);
        
  },1000);

    
  
</script>
        