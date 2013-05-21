<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'action', 'error')} required">
    <label for="action">
        <g:message code="transaction.action.label" default="Action"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select name="action" from="${com.netnumeri.server.finance.beans.TradeEnum?.values()}"
              keys="${com.netnumeri.server.finance.beans.TradeEnum.values()*.name()}" required=""
              value="${transactionInstance?.action?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'amount', 'error')} required">
    <label for="amount">
        <g:message code="transaction.amount.label" default="Amount"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="amount" type="number" value="${transactionInstance.amount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'date', 'error')} required">
    <label for="date">
        <g:message code="transaction.date.label" default="Date"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="date" precision="day" value="${transactionInstance?.date}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="transaction.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${transactionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'instrument', 'error')} required">
    <label for="instrument">
        <g:message code="transaction.instrument.label" default="Instrument"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="instrument" name="instrument.id" from="${com.netnumeri.server.finance.finpojo.Instrument.list()}"
              optionKey="id" required="" value="${transactionInstance?.instrument?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="transaction.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${transactionInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'price', 'error')} required">
    <label for="price">
        <g:message code="transaction.price.label" default="Price"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="price" value="${fieldValue(bean: transactionInstance, field: 'price')}" required=""/>
</div>

