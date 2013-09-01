<%@ page import="com.netnumeri.server.finance.indicator.Indicators" %>



<div class="control-group fieldcontain ${hasErrors(bean: indicatorsInstance, field: 'code', 'error')} ">
    <label for="code" class="control-label"><g:message
            code="indicators.code.label"
            default="Code"/></label>

    <div class="controls">
        <g:textField name="code" value="${indicatorsInstance?.code}"/>
        <span class="help-inline">${hasErrors(bean: indicatorsInstance, field: 'code', 'error')}</span>
    </div>
</div>

