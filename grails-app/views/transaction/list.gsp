<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-transaction" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="amount"
                              title="${message(code: 'transaction.amount.label', default: 'Amount')}"/>

            <g:sortableColumn property="cost"
                              title="${message(code: 'transaction.cost.label', default: 'Cost')}"/>

            <th><g:message code="transaction.instrument.label" default="Instrument"/></th>

            <th><g:message code="transaction.portfolio.label" default="Portfolio"/></th>

            <g:sortableColumn property="price"
                              title="${message(code: 'transaction.price.label', default: 'Price')}"/>

            <g:sortableColumn property="tradeAction"
                              title="${message(code: 'transaction.tradeAction.label', default: 'Trade Action')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${transactionInstanceList}" status="i" var="transactionInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${transactionInstance.id}">${fieldValue(bean: transactionInstance, field: "amount")}</g:link></td>

                <td>${fieldValue(bean: transactionInstance, field: "cost")}</td>

                <td>${fieldValue(bean: transactionInstance, field: "instrument")}</td>

                <td>${fieldValue(bean: transactionInstance, field: "portfolio")}</td>

                <td>${fieldValue(bean: transactionInstance, field: "price")}</td>

                <td>${fieldValue(bean: transactionInstance, field: "tradeAction")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${transactionInstanceTotal}"/>
    </div>
</section>

</body>

</html>
