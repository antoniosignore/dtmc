<%@ page import="com.netnumeri.server.finance.finpojo.PortfolioItem" %>



<div class="fieldcontain ${hasErrors(bean: portfolioItemInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="portfolioItem.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" type="number" value="${portfolioItemInstance.amount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: portfolioItemInstance, field: 'instrument', 'error')} required">
	<label for="instrument">
		<g:message code="portfolioItem.instrument.label" default="Instrument" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="instrument" name="instrument.id" from="${com.netnumeri.server.finance.finpojo.Instrument.list()}" optionKey="id" required="" value="${portfolioItemInstance?.instrument?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: portfolioItemInstance, field: 'portfolio', 'error')} required">
	<label for="portfolio">
		<g:message code="portfolioItem.portfolio.label" default="Portfolio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="portfolio" name="portfolio.id" from="${com.netnumeri.server.finance.finpojo.Portfolio.list()}" optionKey="id" required="" value="${portfolioItemInstance?.portfolio?.id}" class="many-to-one"/>
</div>

