<%@ page import="com.netnumeri.server.finance.finpojo.asset.Stock" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-stock" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="dateCreated"
                              title="${message(code: 'stock.dateCreated.label', default: 'Date Created')}"/>

            <g:sortableColumn property="description"
                              title="${message(code: 'stock.description.label', default: 'Description')}"/>

            <g:sortableColumn property="name" title="${message(code: 'stock.name.label', default: 'Name')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${stockInstanceList}" status="i" var="stockInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${stockInstance.id}">${fieldValue(bean: stockInstance, field: "dateCreated")}</g:link></td>

                <td>${fieldValue(bean: stockInstance, field: "description")}</td>

                <td>${fieldValue(bean: stockInstance, field: "name")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${stockInstanceTotal}"/>
    </div>
</section>

</body>

</html>
