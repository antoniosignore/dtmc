<%@ page import="com.netnumeri.server.finance.indicator.Indicators" %>



<div class="control-group fieldcontain ${hasErrors(bean: indicatorsInstance, field: 'attributes', 'error')} ">
    <label for="attributes" class="control-label"><g:message
            code="indicators.attributes.label"
            default="Attributes"/></label>

    <div class="controls">
        
<ul class="one-to-many">
<g:each in="${indicatorsInstance?.attributes?}" var="a">
    <li><g:link controller="attributes" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="attributes" action="create" params="['indicators.id': indicatorsInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'attributes.label', default: 'Attributes')])}</g:link>
</li>
</ul>

        <span class="help-inline">${hasErrors(bean: indicatorsInstance, field: 'attributes', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: indicatorsInstance, field: 'indicator', 'error')} ">
    <label for="indicator" class="control-label"><g:message
            code="indicators.indicator.label"
            default="Indicator"/></label>

    <div class="controls">
        <g:select name="indicator" from="${com.netnumeri.server.finance.beans.IndicatorEnum?.values()}" keys="${com.netnumeri.server.finance.beans.IndicatorEnum.values()*.name()}" value="${indicatorsInstance?.indicator?.name()}" noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: indicatorsInstance, field: 'indicator', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: indicatorsInstance, field: 'name', 'error')} ">
    <label for="name" class="control-label"><g:message
            code="indicators.name.label"
            default="Name"/></label>

    <div class="controls">
        <g:textField name="name" value="${indicatorsInstance?.name}"/>
        <span class="help-inline">${hasErrors(bean: indicatorsInstance, field: 'name', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: indicatorsInstance, field: 'user', 'error')} ">
    <label for="user" class="control-label"><g:message
            code="indicators.user.label"
            default="User"/></label>

    <div class="controls">
        <g:select id="user" name="user.id" from="${com.dtmc.security.UserBean.list()}" optionKey="id" value="${indicatorsInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: indicatorsInstance, field: 'user', 'error')}</span>
    </div>
</div>

