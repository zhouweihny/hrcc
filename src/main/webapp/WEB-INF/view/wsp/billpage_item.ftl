<#include "../tools/select.ftl"  /> 
<#list data.data as item >
<div class="item" data-id='${item.id!""}'>
    <div class="weui-panel__hd">
        <div class="weui-flex">
            <div class="weui-flex__item">订单号：${item.no!""}</div>
        	<#if item.status==-1 >
            <span class="status s_0">待发送</span>
            </#if>
            <#if item.status==0 >
            <span class="status s_0">新订单</span>
            </#if>
            <#if item.status==1 >
            <span class="status s_1">已接收</span>
            </#if>
            <#if item.status==2 >
            <span class="status s_2">已确认</span>
            </#if>
            <#if item.status==3 >
            <span class="status s_3">已发货</span>
            </#if>
        </div>
    </div>
    <div class="weui-panel__bd">
        <div class="weui-media-box__bd">
            <div class="weui-media-box__desc">
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>日期</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail">${item.createtime?string("yyyy-MM-dd")}</p>
                    </div>
                </div>
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>供应商</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail"><#if item.supplierid?? ><@_user id=item.supplierid>${user.company!""}</@_user></#if></p>
                    </div>
                </div>
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>业务员</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail"><#if item.agentid?? ><@_agent id=item.agentid>${agent.name!""}</@_agent></#if></p>
                    </div>
                </div>
                <div class="weui-flex">
                    <div class="item_r">
                        <p class="des"><span>品种数</span>：</p>
                    </div>
                    <div class="weui-flex__item">
                        <p class="detail">
                            ${item.num!""}
                            <span class="remark"></span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="weui-panel__ft">
        <p>预计到货时间：<#if item.arrivaltime ??>${item.arrivaltime?string("yyyy-MM-dd")}</#if></p>
    </div>
</div>
</#list>