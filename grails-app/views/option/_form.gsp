<%@ page import="com.netnumeri.server.finance.finpojo.derivative.equity.Option" %>



<div class="fieldcontain ${hasErrors(bean: optionInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="option.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${optionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: optionInstance, field: 'interestRate', 'error')} required">
    <label for="interestRate">
        <g:message code="option.interestRate.label" default="Interest Rate"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="interestRate" value="${fieldValue(bean: optionInstance, field: 'interestRate')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: optionInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="option.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${optionInstance?.name}"/>
</div>

