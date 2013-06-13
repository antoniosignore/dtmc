<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>



			<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'description', 'error')} ">
				<label for="description" class="control-label"><g:message code="portfolio.description.label" default="Description" /></label>
				<div class="controls">
					<g:textField name="description" value="${portfolioInstance?.description}"/>
					<span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'description', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'items', 'error')} ">
				<label for="items" class="control-label"><g:message code="portfolio.items.label" default="Items" /></label>
				<div class="controls">
					
<ul class="one-to-many">
<g:each in="${portfolioInstance?.items?}" var="i">
    <li><g:link controller="portfolioItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="portfolioItem" action="create" params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem')])}</g:link>
</li>
</ul>

					<span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'items', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'name', 'error')} ">
				<label for="name" class="control-label"><g:message code="portfolio.name.label" default="Name" /></label>
				<div class="controls">
					<g:textField name="name" value="${portfolioInstance?.name}"/>
					<span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'name', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'transactions', 'error')} ">
				<label for="transactions" class="control-label"><g:message code="portfolio.transactions.label" default="Transactions" /></label>
				<div class="controls">
					
<ul class="one-to-many">
<g:each in="${portfolioInstance?.transactions?}" var="t">
    <li><g:link controller="trade" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="trade" action="create" params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'trade.label', default: 'Trade')])}</g:link>
</li>
</ul>

					<span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'transactions', 'error')}</span>
				</div>
			</div>

