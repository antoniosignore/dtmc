<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>



<div class="control-group fieldcontain ${hasErrors(bean: transactionInstance, field: 'amount', 'error')} ">
    <label for="amount" class="control-label"><g:message
            code="transaction.amount.label"
            default="Amount"/></label>

    <div class="controls">
        <g:field type="number" name="amount" value="${transactionInstance.amount}"/>
        <span class="help-inline">${hasErrors(bean: transactionInstance, field: 'amount', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: transactionInstance, field: 'cost', 'error')} ">
    <label for="cost" class="control-label"><g:message
            code="transaction.cost.label"
            default="Cost"/></label>

    <div class="controls">
        <g:field type="number" name="cost" step="any" value="${transactionInstance.cost}"/>
        <span class="help-inline">${hasErrors(bean: transactionInstance, field: 'cost', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: transactionInstance, field: 'instrument', 'error')} ">
    <label for="instrument" class="control-label"><g:message
            code="transaction.instrument.label"
            default="Instrument"/></label>

    <div class="controls">
        <g:select id="instrument" name="instrument.id" from="${com.netnumeri.server.finance.finpojo.Instrument.list()}"
                  optionKey="id" value="${transactionInstance?.instrument?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: transactionInstance, field: 'instrument', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: transactionInstance, field: 'portfolio', 'error')} ">
    <label for="portfolio" class="control-label"><g:message
            code="transaction.portfolio.label"
            default="Portfolio"/></label>

    <div class="controls">
        <g:select id="portfolio" name="portfolio.id" from="${com.netnumeri.server.finance.finpojo.Portfolio.list()}"
                  optionKey="id" value="${transactionInstance?.portfolio?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: transactionInstance, field: 'portfolio', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: transactionInstance, field: 'price', 'error')} ">
    <label for="price" class="control-label"><g:message
            code="transaction.price.label"
            default="Price"/></label>

    <div class="controls">
        <g:field type="number" name="price" step="any" value="${transactionInstance.price}"/>
        <span class="help-inline">${hasErrors(bean: transactionInstance, field: 'price', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: transactionInstance, field: 'tradeAction', 'error')} ">
    <label for="tradeAction" class="control-label"><g:message
            code="transaction.tradeAction.label"
            default="Trade Action"/></label>

    <div class="controls">
        <g:select name="tradeAction" from="${com.netnumeri.server.finance.beans.TradeEnum?.values()}"
                  keys="${com.netnumeri.server.finance.beans.TradeEnum.values()*.name()}"
                  value="${transactionInstance?.tradeAction?.name()}" noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: transactionInstance, field: 'tradeAction', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: transactionInstance, field: 'transactionDate', 'error')} ">
    <label for="transactionDate" class="control-label"><g:message
            code="transaction.transactionDate.label"
            default="Transaction Date"/></label>

    <div class="controls">
        <bs:datePicker name="transactionDate" precision="day" value="${transactionInstance?.transactionDate}"
                       default="none" noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: transactionInstance, field: 'transactionDate', 'error')}</span>
    </div>
</div>

