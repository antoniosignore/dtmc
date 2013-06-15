<%@ page import="com.netnumeri.server.finance.finpojo.Trade" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'trade.label', default: 'Trade')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-trade" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="amount"
                              title="${message(code: 'trade.amount.label', default: 'Amount')}"/>

            <g:sortableColumn property="cost"
                              title="${message(code: 'trade.cost.label', default: 'Cost')}"/>

            <th><g:message code="trade.instrument.label" default="Instrument"/></th>

            <g:sortableColumn property="price"
                              title="${message(code: 'trade.price.label', default: 'Price')}"/>

            <g:sortableColumn property="tradeAction"
                              title="${message(code: 'trade.tradeAction.label', default: 'Trade Action')}"/>

            <g:sortableColumn property="transactionDate"
                              title="${message(code: 'trade.transactionDate.label', default: 'Transaction Date')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${tradeInstanceList}" status="i" var="tradeInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${tradeInstance.id}">${fieldValue(bean: tradeInstance, field: "amount")}</g:link></td>

                <td>${fieldValue(bean: tradeInstance, field: "cost")}</td>

                <td>${fieldValue(bean: tradeInstance, field: "instrument")}</td>

                <td>${fieldValue(bean: tradeInstance, field: "price")}</td>

                <td>${fieldValue(bean: tradeInstance, field: "tradeAction")}</td>

                <td><g:formatDate date="${tradeInstance.transactionDate}"/></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${tradeInstanceTotal}"/>
    </div>
</section>

</body>

</html>
