<%@ page import="com.netnumeri.server.finance.finpojo.Trade" %>



<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'amount', 'error')} ">
    <label for="amount" class="control-label"><g:message
            code="trade.amount.label"
            default="Amount"/></label>

    <div class="controls">
        <g:field type="number" name="amount" value="${tradeInstance.amount}"/>
        <span class="help-inline">${hasErrors(bean: tradeInstance, field: 'amount', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'cost', 'error')} ">
    <label for="cost" class="control-label"><g:message
            code="trade.cost.label"
            default="Cost"/></label>

    <div class="controls">
        <g:field type="number" name="cost" step="any" value="${tradeInstance.cost}"/>
        <span class="help-inline">${hasErrors(bean: tradeInstance, field: 'cost', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'instrument', 'error')} ">
    <label for="instrument" class="control-label"><g:message
            code="trade.instrument.label"
            default="Instrument"/></label>

    <div class="controls">
        <g:select id="instrument" name="instrument.id" from="${com.netnumeri.server.finance.finpojo.Instrument.list()}"
                  optionKey="id" value="${tradeInstance?.instrument?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: tradeInstance, field: 'instrument', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'price', 'error')} ">
    <label for="price" class="control-label"><g:message
            code="trade.price.label"
            default="Price"/></label>

    <div class="controls">
        <g:field type="number" name="price" step="any" value="${tradeInstance.price}"/>
        <span class="help-inline">${hasErrors(bean: tradeInstance, field: 'price', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'tradeAction', 'error')} ">
    <label for="tradeAction" class="control-label"><g:message
            code="trade.tradeAction.label"
            default="Trade Action"/></label>

    <div class="controls">
        <g:select name="tradeAction" from="${com.netnumeri.server.finance.beans.TradeEnum?.values()}"
                  keys="${com.netnumeri.server.finance.beans.TradeEnum.values()*.name()}"
                  value="${tradeInstance?.tradeAction?.name()}" noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: tradeInstance, field: 'tradeAction', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: tradeInstance, field: 'transactionDate', 'error')} ">
    <label for="transactionDate" class="control-label"><g:message
            code="trade.transactionDate.label"
            default="Transaction Date"/></label>

    <div class="controls">
        <bs:datePicker name="transactionDate" precision="day" value="${tradeInstance?.transactionDate}" default="none"
                       noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: tradeInstance, field: 'transactionDate', 'error')}</span>
    </div>
</div>

