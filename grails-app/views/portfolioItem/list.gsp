<%@ page import="com.netnumeri.server.finance.finpojo.PortfolioEntry" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'portfolioItem.label', default: 'PortfolioItem')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-portfolioItem" class="first">

    <table class="table table-striped table-bordered table-condensed table-hover">
        <thead>
        <tr>
            <g:sortableColumn property="amount"
                              title="${message(code: 'portfolioItem.amount.label', default: 'Amount')}"/>

            <th><g:message code="portfolioItem.instrument.label" default="Instrument"/></th>

            <th><g:message code="portfolioItem.portfolio.label" default="Portfolio"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${portfolioItemInstanceList}" status="i" var="portfolioItemInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${portfolioItemInstance.id}">${fieldValue(bean: portfolioItemInstance, field: "amount")}</g:link></td>

                <td>${fieldValue(bean: portfolioItemInstance, field: "instrument")}</td>

                <td>${fieldValue(bean: portfolioItemInstance, field: "portfolio")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${portfolioItemInstanceTotal}"/>
    </div>
</section>

</body>

</html>
