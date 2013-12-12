
<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kickstart">
		<g:set var="entityName" value="${message(code: 'portfolio.label', default: 'Portfolio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		%{--<a href="#show-portfolio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%
		<div id="show-portfolio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list portfolio">
			
				<g:if test="${portfolioInstance?.club}">
				<li class="fieldcontain">
					<span id="club-label" class="property-label"><g:message code="portfolio.club.label" default="Club" /></span>
					
						<span class="property-value" aria-labelledby="club-label"><g:link controller="club" action="show" id="${portfolioInstance?.club?.id}">${portfolioInstance?.club?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="portfolio.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${portfolioInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="portfolio.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${portfolioInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.firstDailyDate}">
				<li class="fieldcontain">
					<span id="firstDailyDate-label" class="property-label"><g:message code="portfolio.firstDailyDate.label" default="First Daily Date" /></span>
					
						<span class="property-value" aria-labelledby="firstDailyDate-label"><g:formatDate date="${portfolioInstance?.firstDailyDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.items}">
				<li class="fieldcontain">
					<span id="items-label" class="property-label"><g:message code="portfolio.items.label" default="Items" /></span>
					
						<g:each in="${portfolioInstance.items}" var="i">
						<span class="property-value" aria-labelledby="items-label"><g:link controller="portfolioItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.lastDailyDate}">
				<li class="fieldcontain">
					<span id="lastDailyDate-label" class="property-label"><g:message code="portfolio.lastDailyDate.label" default="Last Daily Date" /></span>
					
						<span class="property-value" aria-labelledby="lastDailyDate-label"><g:formatDate date="${portfolioInstance?.lastDailyDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="portfolio.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${portfolioInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="portfolio.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${portfolioInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.transactions}">
				<li class="fieldcontain">
					<span id="transactions-label" class="property-label"><g:message code="portfolio.transactions.label" default="Transactions" /></span>
					
						<g:each in="${portfolioInstance.transactions}" var="t">
						<span class="property-value" aria-labelledby="transactions-label"><g:link controller="trade" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="portfolio.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="member" action="show" id="${portfolioInstance?.user?.id}">${portfolioInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${portfolioInstance?.wealth}">
				<li class="fieldcontain">
					<span id="wealth-label" class="property-label"><g:message code="portfolio.wealth.label" default="Wealth" /></span>
					
						<span class="property-value" aria-labelledby="wealth-label"><g:fieldValue bean="${portfolioInstance}" field="wealth"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:portfolioInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${portfolioInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
