<%@ page import="com.netnumeri.server.finance.indicator.SimpleMovingAverage" %>



<div class="control-group fieldcontain ${hasErrors(bean: simpleMovingAverageUserIndicatorInstance, field: 'name', 'error')} ">
    <label for="name" class="control-label"><g:message
            code="simpleMovingAverageUserIndicator.name.label"
            default="Name"/></label>

    <div class="controls">
        <g:textField name="name" value="${simpleMovingAverageUserIndicatorInstance?.name}"/>
        <span class="help-inline">${hasErrors(bean: simpleMovingAverageUserIndicatorInstance, field: 'name', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: simpleMovingAverageUserIndicatorInstance, field: 'smoothing', 'error')} ">
    <label for="smoothing" class="control-label"><g:message
            code="simpleMovingAverageUserIndicator.smoothing.label"
            default="Smoothing"/></label>

    <div class="controls">
        <g:field type="number" name="smoothing" value="${simpleMovingAverageUserIndicatorInstance.smoothing}"/>
        <span class="help-inline">${hasErrors(bean: simpleMovingAverageUserIndicatorInstance, field: 'smoothing', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: simpleMovingAverageUserIndicatorInstance, field: 'user', 'error')} ">
    <label for="user" class="control-label"><g:message
            code="simpleMovingAverageUserIndicator.user.label"
            default="User"/></label>

    <div class="controls">
        <g:select id="user" name="user.id" from="${com.dtmc.security.UserBean.list()}" optionKey="id"
                  value="${simpleMovingAverageUserIndicatorInstance?.user?.username}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: simpleMovingAverageUserIndicatorInstance, field: 'user', 'error')}</span>
    </div>
</div>

