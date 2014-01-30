<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>



<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'amount', 'error')} ">
	<label for="amount">
		<g:message code="trade.amount.label" default="Amount" />
		
	</label>
	<g:field name="amount" type="number" value="${tradeInstance.amount}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'cost', 'error')} ">
	<label for="cost">
		<g:message code="trade.cost.label" default="Cost" />
		
	</label>
	<g:field name="cost" value="${fieldValue(bean: tradeInstance, field: 'cost')}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'portfolio', 'error')} ">
	<label for="portfolio">
		<g:message code="trade.portfolio.label" default="Portfolio" />
		
	</label>
	<g:select id="portfolio" name="portfolio.id" from="${com.netnumeri.server.finance.finpojo.Portfolio.list()}" optionKey="id" value="${tradeInstance?.portfolio?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'price', 'error')} ">
	<label for="price">
		<g:message code="trade.price.label" default="Price" />
		
	</label>
	<g:field name="price" value="${fieldValue(bean: tradeInstance, field: 'price')}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'tradeAction', 'error')} ">
	<label for="tradeAction">
		<g:message code="trade.tradeAction.label" default="Trade Action" />
		
	</label>
	<g:select name="tradeAction" from="${com.netnumeri.server.enums.TradeEnum?.values()}" keys="${com.netnumeri.server.enums.TradeEnum.values()*.name()}" value="${tradeInstance?.tradeAction?.name()}" noSelection="['': '']"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'transactionDate', 'error')} ">
	<label for="transactionDate">
		<g:message code="trade.transactionDate.label" default="Transaction Date" />
		
	</label>
    <g:datePicker name="transactionDate" precision="day" value="${tradeInstance?.transactionDate}" default="none"
                  noSelection="['': '']"/>
</div>

