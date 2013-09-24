<%@ page import="com.netnumeri.server.finance.indicator.UserIndicators" %>




<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'indicator', 'error')} ">
    <label for="indicator" class="control-label"><g:message
            code="userIndicators.indicator.label"
            default="Indicator"/></label>

    <div class="controls">
        <g:select id="indicator" name="indicator.id" from="${com.netnumeri.server.finance.indicator.Indicators.list()}"
                  optionKey="id" value="${userIndicatorsInstance?.indicator?.id}" class="many-to-one" optionValue="code"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'indicator', 'error')}</span>
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

<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'user', 'error')} ">
    <label for="user" class="control-label"><g:message
            code="userIndicators.user.label"
            default="User"/></label>

    <div class="controls">
        <g:select id="user" name="user.id" from="${com.dtmc.security.UserBean.list()}" optionKey="id"
                  optionValue="username"
                  value="${userIndicatorsInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'user', 'error')}</span>
    </div>
</div>

