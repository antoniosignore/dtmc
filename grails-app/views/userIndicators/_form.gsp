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


<div class="control-group fieldcontain ${hasErrors(bean: userIndicatorsInstance, field: 'attributes', 'error')} ">
    <label for="attributes" class="control-label"><g:message
            code="userIndicators.attributes.label"
            default="Attributes"/></label>

    <div class="controls">

        <ul class="one-to-many">
            <g:each in="${userIndicatorsInstance?.attributes ?}" var="a">
                <li><g:link controller="attributes" action="show" id="${a.id}">${a?.name.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="attributes" action="create"
                        params="['userIndicators.id': userIndicatorsInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'attributes.label', default: 'Attributes')])}</g:link>
            </li>
        </ul>

        <span class="help-inline">${hasErrors(bean: userIndicatorsInstance, field: 'attributes', 'error')}</span>
    </div>
</div>