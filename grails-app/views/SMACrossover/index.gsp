
<%@ page import="com.netnumeri.server.finance.finpojo.SMACrossover" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kickstart">
		<g:set var="entityName" value="${message(code: 'SMACrossover.label', default: 'SMACrossover')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		%{--<a href="#list-SMACrossover" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%
		<div id="list-SMACrossover" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-striped table-bordered table-condensed table-hover">
			<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'SMACrossover.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="higherMA" title="${message(code: 'SMACrossover.higherMA.label', default: 'Higher MA')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'SMACrossover.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="lowerMA" title="${message(code: 'SMACrossover.lowerMA.label', default: 'Lower MA')}" />
					
						<th><g:message code="SMACrossover.member.label" default="Member" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'SMACrossover.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${SMACrossoverInstanceList}" status="i" var="SMACrossoverInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${SMACrossoverInstance.id}">${fieldValue(bean: SMACrossoverInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: SMACrossoverInstance, field: "higherMA")}</td>
					
						<td><g:formatDate date="${SMACrossoverInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: SMACrossoverInstance, field: "lowerMA")}</td>
					
						<td>${fieldValue(bean: SMACrossoverInstance, field: "member")}</td>
					
						<td>${fieldValue(bean: SMACrossoverInstance, field: "name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${SMACrossoverInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
