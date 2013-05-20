<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>



<div class="fieldcontain ${hasErrors(bean: portfolioInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="portfolio.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${portfolioInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: portfolioInstance, field: 'items', 'error')} ">
    <label for="items">
        <g:message code="portfolio.items.label" default="Items"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${portfolioInstance?.items ?}" var="i">
            <li><g:link controller="portfolioItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="portfolioItem" action="create"
                    params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: portfolioInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="portfolio.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${portfolioInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: portfolioInstance, field: 'transactions', 'error')} ">
    <label for="transactions">
        <g:message code="portfolio.transactions.label" default="Transactions"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${portfolioInstance?.transactions ?}" var="t">
            <li><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="transaction" action="create"
                    params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>
        </li>
    </ul>

</div>

