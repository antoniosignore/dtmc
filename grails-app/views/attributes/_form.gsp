<%@ page import="com.netnumeri.server.finance.indicator.Attributes" %>



<div class="control-group fieldcontain ${hasErrors(bean: attributesInstance, field: 'name', 'error')} ">
    <label for="name" class="control-label"><g:message
            code="attributes.name.label"
            default="Name"/></label>

    <div class="controls">
        <g:textField name="name" value="${attributesInstance?.name}"/>
        <span class="help-inline">${hasErrors(bean: attributesInstance, field: 'name', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: attributesInstance, field: 'userIndicator', 'error')} ">
    <label for="userIndicator" class="control-label"><g:message
            code="attributes.userIndicator.label"
            default="User Indicator"/></label>

    <div class="controls">
        <g:select id="userIndicator" name="userIndicator.id"
                  from="${com.netnumeri.server.finance.indicator.UserIndicators.list()}"
                  optionKey="id" optionValue="name" value="${attributesInstance?.userIndicator?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: attributesInstance, field: 'userIndicator', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: attributesInstance, field: 'value', 'error')} ">
    <label for="value" class="control-label"><g:message
            code="attributes.value.label"
            default="Value"/></label>

    <div class="controls">
        <g:field type="number" name="value" step="any" value="${attributesInstance.value}"/>
        <span class="help-inline">${hasErrors(bean: attributesInstance, field: 'value', 'error')}</span>
    </div>
</div>

