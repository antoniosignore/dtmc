
<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kickstart">
		<g:set var="entityName" value="${message(code: 'trade.label', default: 'Trade')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		%{--<a href="#list-trade" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%
		<div id="list-trade" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-striped table-bordered table-condensed table-hover">
			<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'trade.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="cost" title="${message(code: 'trade.cost.label', default: 'Cost')}" />
					
						<th><g:message code="trade.portfolio.label" default="Portfolio" /></th>
					
						<g:sortableColumn property="price" title="${message(code: 'trade.price.label', default: 'Price')}" />
					
						<g:sortableColumn property="tradeAction" title="${message(code: 'trade.tradeAction.label', default: 'Trade Action')}" />
					
						<g:sortableColumn property="transactionDate" title="${message(code: 'trade.transactionDate.label', default: 'Transaction Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tradeInstanceList}" status="i" var="tradeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tradeInstance.id}">${fieldValue(bean: tradeInstance, field: "amount")}</g:link></td>
					
						<td>${fieldValue(bean: tradeInstance, field: "cost")}</td>
					
						<td>${fieldValue(bean: tradeInstance, field: "portfolio")}</td>
					
						<td>${fieldValue(bean: tradeInstance, field: "price")}</td>
					
						<td>${fieldValue(bean: tradeInstance, field: "tradeAction")}</td>
					
						<td><g:formatDate date="${tradeInstance.transactionDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tradeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
