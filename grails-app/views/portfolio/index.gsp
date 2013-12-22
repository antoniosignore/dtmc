<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="kickstart">
    <g:set var="entityName" value="${message(code: 'portfolio.label', default: 'Portfolio')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
%{--<a href="#list-portfolio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
%{--<div class="nav" role="navigation">--}%
%{--<ul>--}%
%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
%{--</ul>--}%
%{--</div>--}%
<div id="list-portfolio" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-striped table-bordered table-condensed table-hover">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'portfolio.name.label', default: 'Name')}"/>

            %{--<th><g:message code="portfolio.club.label" default="Club"/></th>--}%

            <g:sortableColumn property="dateCreated" title="${message(code: 'portfolio.dateCreated.label', default: 'Date Created')}"/>

            <g:sortableColumn property="description" title="${message(code: 'portfolio.description.label', default: 'Description')}"/>

            <g:sortableColumn property="firstDailyDate" title="${message(code: 'portfolio.firstDailyDate.label', default: 'First Daily Date')}"/>

            <g:sortableColumn property="lastDailyDate" title="${message(code: 'portfolio.lastDailyDate.label', default: 'Last Daily Date')}"/>

            <g:sortableColumn property="lastUpdated" title="${message(code: 'portfolio.lastUpdated.label', default: 'Last Updated')}"/>

        </tr>
        </thead>

        <tbody>
        <g:each in="${portfolioInstanceList}" status="i" var="portfolioInstance">

            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${portfolioInstance.id}">${fieldValue(bean: portfolioInstance, field: "name")}</g:link></td>

                %{--<td><g:link action="show" id="${portfolioInstance.id}">${fieldValue(bean: portfolioInstance, field: "club.name")}</g:link></td>--}%

                <td><g:formatDate date="${portfolioInstance.dateCreated}"/></td>

                <td>${fieldValue(bean: portfolioInstance, field: "description")}</td>

                <td><g:formatDate date="${portfolioInstance.firstDailyDate}"/></td>

                <td><g:formatDate date="${portfolioInstance.lastDailyDate}"/></td>

                <td><g:formatDate date="${portfolioInstance.lastUpdated}"/></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${portfolioInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
