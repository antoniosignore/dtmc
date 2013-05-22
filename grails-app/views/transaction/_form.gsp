<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="transaction.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" type="number" value="${transactionInstance.amount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'cost', 'error')} required">
	<label for="cost">
		<g:message code="transaction.cost.label" default="Cost" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cost" value="${fieldValue(bean: transactionInstance, field: 'cost')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="transaction.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${transactionInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'instrument', 'error')} required">
	<label for="instrument">
		<g:message code="transaction.instrument.label" default="Instrument" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="instrument" name="instrument.id" from="${com.netnumeri.server.finance.finpojo.Instrument.list()}" optionKey="id" required="" value="${transactionInstance?.instrument?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="transaction.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: transactionInstance, field: 'price')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'tradeAction', 'error')} required">
	<label for="tradeAction">
		<g:message code="transaction.tradeAction.label" default="Trade Action" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tradeAction" optionKey="key" from="${com.netnumeri.server.finance.beans.TradeEnum?.values()}" keys="${com.netnumeri.server.finance.beans.TradeEnum.values()*.name()}" required="" value="${transactionInstance?.tradeAction?.name()}"/>
</div>

