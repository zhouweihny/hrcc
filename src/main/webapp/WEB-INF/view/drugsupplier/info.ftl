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
            <label class="col-sm-3 control-label">药品名：</label>
            <div class="col-sm-8">
              <select id="drugid" name="drugid" class="form-control " style="width: 360px;" ></select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">供应商：</label>
            <div class="col-sm-8">
              <select id="supplierid" name="supplierid" class="form-control " style="width: 360px;" ></select>
            </div>
          </div>
          
          
          <div class="form-group">
            <label class="col-sm-3 control-label">代理商：</label>
            <div class="col-sm-8">
              <select id="agentid" name="agentid" class="form-control " style="width: 360px;" ></select>
            </div>
          </div>
          
        </div>
      </div>
    </form>

    <div class="modal-footer">
      <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
      <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/drugsupplier/save.do','refreshDrugSupplierList')">保存</button>
    </div>
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
      placeholder: "请输入药品名称或编码",
      allowClear: true,
      language: "zh-CN",
      dropdownAutoWidth: true,
      templateSelection: function(data){
        if(data.id && data.id !== -1)
          return data.name;
        return "请输入药品名称或编码";
      }
  })


$('#supplierid').select2({
    ajax: {
        url: "${request.contextPath}/usersupplier/mysuppliers.do",
        type : "POST",
        dataType: 'json',
        delay: 250,
        data: function(params) {
            return {
                keyword: params.term, // search term
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
                    attr.text = _cdata[i].name;
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
    placeholder: "请输入供应商名称或者编码或者手机号",
    allowClear: true,
    language: "zh-CN",
    dropdownAutoWidth: true
})



$('#agentid').select2({
    ajax: {
        url: "${request.contextPath}/agent/agents.do",
        type : "POST",
        dataType: 'json',
        delay: 250,
        data: function(params) {
            return {
                keywords: params.term, // search term
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
                    attr.text = _cdata[i].name +_cdata[i].mobileno;
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
    placeholder: "请输入代理人名称或者手机号",
    allowClear: true,
    language: "zh-CN",
    dropdownAutoWidth: true
})


})

</script>