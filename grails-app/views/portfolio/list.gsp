
<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'portfolio.label', default: 'Portfolio')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
	
<section id="list-portfolio" class="first">

	<table class="table table-bordered">
		<thead>
			<tr>
			
				<g:sortableColumn property="dateCreated" title="${message(code: 'portfolio.dateCreated.label', default: 'Date Created')}" />
			
				<g:sortableColumn property="description" title="${message(code: 'portfolio.description.label', default: 'Description')}" />
			
				<g:sortableColumn property="name" title="${message(code: 'portfolio.name.label', default: 'Name')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${portfolioInstanceList}" status="i" var="portfolioInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${portfolioInstance.id}">${fieldValue(bean: portfolioInstance, field: "dateCreated")}</g:link></td>
			
				<td>${fieldValue(bean: portfolioInstance, field: "description")}</td>
			
				<td>${fieldValue(bean: portfolioInstance, field: "name")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<bs:paginate total="${portfolioInstanceTotal}" />
	</div>
</section>

</body>

</html>
