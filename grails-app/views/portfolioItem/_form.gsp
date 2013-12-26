<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio; com.netnumeri.server.finance.finpojo.Instrument; com.netnumeri.server.finance.finpojo.PortfolioEntry" %>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioItemInstance, field: 'amount', 'error')} ">
    <label for="amount" class="control-label"><g:message
            code="portfolioItem.amount.label"
            default="Amount"/></label>

    <div class="controls">
        <g:field type="number" name="amount" value="${portfolioItemInstance.amount}"/>
        <span class="help-inline">${hasErrors(bean: portfolioItemInstance, field: 'amount', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioItemInstance, field: 'instrument', 'error')} ">
    <label for="instrument" class="control-label"><g:message
            code="portfolioItem.instrument.label"
            default="Instrument"/></label>

    <div class="controls">
        <g:select id="instrument" name="instrument.id" from="${Instrument.list()}"
                  optionKey="id" value="${portfolioItemInstance?.instrument?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: portfolioItemInstance, field: 'instrument', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioItemInstance, field: 'portfolio', 'error')} ">
    <label for="portfolio" class="control-label"><g:message
            code="portfolioItem.portfolio.label"
            default="Portfolio"/></label>

    <div class="controls">
        <g:select id="portfolio" name="portfolio.id" from="${Portfolio.list()}"
                  optionKey="id" value="${portfolioItemInstance?.portfolio?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: portfolioItemInstance, field: 'portfolio', 'error')}</span>
    </div>
</div>

