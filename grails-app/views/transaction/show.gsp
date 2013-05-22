
<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

    <div class="container">

        <div class="navbar">
        <div class="navbar-inner">
            <ul class="nav">
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="list"><g:message code="default.list.label"
                                                                  args="[entityName]"/></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                      args="[entityName]"/></g:link></li>
            </ul>
        </div>
    </div>

		%{--<a href="#show-transaction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%

		<div id="show-transaction" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list transaction">
			
				<g:if test="${transactionInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="transaction.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${transactionInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.cost}">
				<li class="fieldcontain">
					<span id="cost-label" class="property-label"><g:message code="transaction.cost.label" default="Cost" /></span>
					
						<span class="property-value" aria-labelledby="cost-label"><g:fieldValue bean="${transactionInstance}" field="cost"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="transaction.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${transactionInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.instrument}">
				<li class="fieldcontain">
					<span id="instrument-label" class="property-label"><g:message code="transaction.instrument.label" default="Instrument" /></span>
					
						<span class="property-value" aria-labelledby="instrument-label"><g:link controller="instrument" action="show" id="${transactionInstance?.instrument?.id}">${transactionInstance?.instrument?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="transaction.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${transactionInstance}" field="price"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.tradeAction}">
				<li class="fieldcontain">
					<span id="tradeAction-label" class="property-label"><g:message code="transaction.tradeAction.label" default="Trade Action" /></span>
					
						<span class="property-value" aria-labelledby="tradeAction-label"><g:fieldValue bean="${transactionInstance}" field="tradeAction"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${transactionInstance?.id}" />
					<g:link class="edit" action="edit" id="${transactionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
    </div>
	</body>
</html>
