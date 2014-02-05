<%@ page import="com.netnumeri.server.enums.IndicatorEnum; com.netnumeri.server.finance.indicator.UserIndicators" %>



<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'deviation', 'error')} ">
    <label for="deviation" class="control-label"><g:message
            code="userIndicators.deviation.label"
            default="Deviation"/></label>

    <div class="controls">
        <g:field type="number" name="deviation" step="any" value="${userIndicatorsInstance.deviation}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'deviation', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'k', 'error')} ">
    <label for="k" class="control-label"><g:message
            code="userIndicators.k.label"
            default="K"/></label>

    <div class="controls">
        <g:field type="number" name="k" step="any" value="${userIndicatorsInstance.k}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'k', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'length', 'error')} ">
    <label for="length" class="control-label"><g:message
            code="userIndicators.length.label"
            default="Length"/></label>

    <div class="controls">
        <g:field type="number" name="length" value="${userIndicatorsInstance.length}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'length', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'method', 'error')} ">
    <label for="method" class="control-label"><g:message
            code="userIndicators.method.label"
            default="Method"/></label>

    <div class="controls">
        <g:field type="number" name="method" value="${userIndicatorsInstance.method}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'method', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'name', 'error')} ">
    <label for="name" class="control-label"><g:message
            code="userIndicators.name.label"
            default="Name"/></label>

    <div class="controls">
        <g:textField name="name" value="${userIndicatorsInstance?.name}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'name', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'indicatorOrder', 'error')} ">
    <label for="indicatorOrder" class="control-label"><g:message
            code="userIndicators.indicatorOrder.label"
            default="Order"/></label>

    <div class="controls">
        <g:field type="number" name="order" value="${userIndicatorsInstance.indicatorOrder}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'indicatorOrder', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'period', 'error')} ">
    <label for="period" class="control-label"><g:message
            code="userIndicators.period.label"
            default="Period"/></label>

    <div class="controls">
        <g:field type="number" name="period" value="${userIndicatorsInstance.period}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'period', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'integer1', 'error')} ">
    <label for="smoothing1" class="control-label"><g:message
            code="userIndicators.smoothing1.label"
            default="Smoothing1"/></label>

    <div class="controls">
        <g:field type="number" name="smoothing1" value="${userIndicatorsInstance.smoothing1}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'integer1', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'integer2', 'error')} ">
    <label for="smoothing2" class="control-label"><g:message
            code="userIndicators.smoothing2.label"
            default="Smoothing2"/></label>

    <div class="controls">
        <g:field type="number" name="smoothing2" value="${userIndicatorsInstance.smoothing2}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'integer2', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'integer3', 'error')} ">
    <label for="smoothing3" class="control-label"><g:message
            code="userIndicators.smoothing3.label"
            default="Smoothing3"/></label>

    <div class="controls">
        <g:field type="number" name="smoothing3" value="${userIndicatorsInstance.smoothing3}"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'integer3', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'type', 'error')} ">
    <label for="type" class="control-label"><g:message
            code="userIndicators.type.label"
            default="Type"/></label>

    <div class="controls">
        <g:select name="type" from="${IndicatorEnum?.values()}"
                  keys="${IndicatorEnum.values()*.name()}"
                  value="${userIndicatorsInstance?.type?.name()}" noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'type', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'user', 'error')} ">
    <label for="user" class="control-label"><g:message
            code="userIndicators.user.label"
            default="User"/></label>

    <div class="controls">
        <g:select id="user" name="user.id" from="${com.dtmc.security.UserBean.list()}" optionKey="id"
                  value="${userIndicatorsInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'user', 'error')}</span>
    </div>
</div>

