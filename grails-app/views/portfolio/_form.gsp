<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>



<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'club', 'error')} ">
	<label for="club">
		<g:message code="portfolio.club.label" default="Club" />
		
	</label>
	<g:select id="club" name="club.id" from="${com.dtmc.club.Club.list()}" optionKey="id" value="${portfolioInstance?.club?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="portfolio.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${portfolioInstance?.description}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'firstDate', 'error')} ">
	<label for="firstDailyDate">
		<g:message code="portfolio.firstDailyDate.label" default="First Daily Date" />
		
	</label>
	<g:datePicker name="firstDailyDate" precision="day"  value="${portfolioInstance?.firstDailyDate}" default="none" noSelection="['': '']" />
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="portfolio.items.label" default="Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${portfolioInstance?.items?}" var="i">
    <li><g:link controller="portfolioItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="portfolioItem" action="create" params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem')])}</g:link>
</li>
</ul>

</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'lastDate', 'error')} ">
	<label for="lastDailyDate">
		<g:message code="portfolio.lastDailyDate.label" default="Last Daily Date" />
		
	</label>
	<g:datePicker name="lastDailyDate" precision="day"  value="${portfolioInstance?.lastDailyDate}" default="none" noSelection="['': '']" />
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="portfolio.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${portfolioInstance?.name}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'transactions', 'error')} ">
	<label for="transactions">
		<g:message code="portfolio.transactions.label" default="Transactions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${portfolioInstance?.transactions?}" var="t">
    <li><g:link controller="trade" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="trade" action="create" params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'trade.label', default: 'Trade')])}</g:link>
</li>
</ul>

</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="portfolio.user.label" default="User" />
		
	</label>
	<g:select id="user" name="user.id" from="${com.dtmc.club.Member.list()}" optionKey="id" value="${portfolioInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'wealth', 'error')} required">
	<label for="wealth">
		<g:message code="portfolio.wealth.label" default="Wealth" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="wealth" value="${fieldValue(bean: portfolioInstance, field: 'wealth')}" required=""/>
</div>

