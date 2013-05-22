
<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

    <div class="container">

        <div class="navbar">
            <div class="navbar-inner">
                <ul class="nav">
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                          args="[entityName]"/></g:link></li>
                </ul>
            </div>
        </div>

		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%

		<div id="list-transaction" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'transaction.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="cost" title="${message(code: 'transaction.cost.label', default: 'Cost')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'transaction.date.label', default: 'Date')}" />
					
						<th><g:message code="transaction.instrument.label" default="Instrument" /></th>
					
						<g:sortableColumn property="price" title="${message(code: 'transaction.price.label', default: 'Price')}" />
					
						<g:sortableColumn property="tradeAction" title="${message(code: 'transaction.tradeAction.label', default: 'Trade Action')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${transactionInstanceList}" status="i" var="transactionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${transactionInstance.id}">${fieldValue(bean: transactionInstance, field: "amount")}</g:link></td>
					
						<td>${fieldValue(bean: transactionInstance, field: "cost")}</td>
					
						<td><g:formatDate date="${transactionInstance.date}" /></td>
					
						<td>${fieldValue(bean: transactionInstance, field: "instrument")}</td>
					
						<td>${fieldValue(bean: transactionInstance, field: "price")}</td>
					
						<td>${fieldValue(bean: transactionInstance, field: "tradeAction")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${transactionInstanceTotal}" />
			</div>
		</div>
    </div>
	</body>
</html>
