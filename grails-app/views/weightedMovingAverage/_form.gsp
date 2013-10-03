<%@ page import="com.netnumeri.server.finance.indicator.WeightedMovingAverage" %>



<div class="control-group fieldcontain ${hasErrors(bean: weightedMovingAverageUserIndicatorInstance, field: 'name', 'error')} ">
    <label for="name" class="control-label"><g:message
            code="weightedMovingAverageUserIndicator.name.label"
            default="Name"/></label>

    <div class="controls">
        <g:textField name="name" value="${weightedMovingAverageUserIndicatorInstance?.name}"/>
        <span class="help-inline">${hasErrors(bean: weightedMovingAverageUserIndicatorInstance, field: 'name', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: weightedMovingAverageUserIndicatorInstance, field: 'smoothing', 'error')} ">
    <label for="smoothing" class="control-label"><g:message
            code="weightedMovingAverageUserIndicator.smoothing.label"
            default="Smoothing"/></label>

    <div class="controls">
        <g:field type="number" name="smoothing" value="${weightedMovingAverageUserIndicatorInstance.smoothing}"/>
        <span class="help-inline">${hasErrors(bean: weightedMovingAverageUserIndicatorInstance, field: 'smoothing', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: weightedMovingAverageUserIndicatorInstance, field: 'user', 'error')} ">
    <label for="user" class="control-label"><g:message
            code="weightedMovingAverageUserIndicator.user.label"
            default="User"/></label>

    <div class="controls">
        <g:select id="user" name="user.id" from="${com.dtmc.security.UserBean.list()}" optionKey="id"
                  value="${weightedMovingAverageUserIndicatorInstance?.user?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: weightedMovingAverageUserIndicatorInstance, field: 'user', 'error')}</span>
    </div>
</div>

