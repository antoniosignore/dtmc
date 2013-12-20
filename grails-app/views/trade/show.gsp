
<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kickstart">
		<g:set var="entityName" value="${message(code: 'trade.label', default: 'Trade')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		%{--<a href="#show-trade" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%
		<div id="show-trade" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list trade">
			
				<g:if test="${tradeInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="trade.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${tradeInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tradeInstance?.cost}">
				<li class="fieldcontain">
					<span id="cost-label" class="property-label"><g:message code="trade.cost.label" default="Cost" /></span>
					
						<span class="property-value" aria-labelledby="cost-label"><g:fieldValue bean="${tradeInstance}" field="cost"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tradeInstance?.portfolio}">
				<li class="fieldcontain">
					<span id="portfolio-label" class="property-label"><g:message code="trade.portfolio.label" default="Portfolio" /></span>
					
						<span class="property-value" aria-labelledby="portfolio-label"><g:link controller="portfolio" action="show" id="${tradeInstance?.portfolio?.id}">${tradeInstance?.portfolio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tradeInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="trade.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${tradeInstance}" field="price"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tradeInstance?.tradeAction}">
				<li class="fieldcontain">
					<span id="tradeAction-label" class="property-label"><g:message code="trade.tradeAction.label" default="Trade Action" /></span>
					
						<span class="property-value" aria-labelledby="tradeAction-label"><g:fieldValue bean="${tradeInstance}" field="tradeAction"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tradeInstance?.transactionDate}">
				<li class="fieldcontain">
					<span id="transactionDate-label" class="property-label"><g:message code="trade.transactionDate.label" default="Transaction Date" /></span>
					
						<span class="property-value" aria-labelledby="transactionDate-label"><g:formatDate date="${tradeInstance?.transactionDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tradeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tradeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
