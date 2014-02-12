<%@ page import="com.netnumeri.server.finance.finpojo.Transaction" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-transaction" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="transaction.amount.label"
                                                     default="Amount"/></td>

            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "amount")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="transaction.cost.label"
                                                     default="Cost"/></td>

            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "cost")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="transaction.instrument.label"
                                                     default="Instrument"/></td>

            <td valign="top" class="value"><g:link controller="instrument" action="show"
                                                   id="${transactionInstance?.instrument?.id}">${transactionInstance?.instrument?.encodeAsHTML()}</g:link></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="transaction.portfolio.label"
                                                     default="Portfolio"/></td>

            <td valign="top" class="value"><g:link controller="portfolio" action="show"
                                                   id="${transactionInstance?.portfolio?.id}">${transactionInstance?.portfolio?.encodeAsHTML()}</g:link></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="transaction.price.label"
                                                     default="Price"/></td>

            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "price")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="transaction.tradeAction.label"
                                                     default="Trade Action"/></td>

            <td valign="top" class="value">${transactionInstance?.tradeAction?.encodeAsHTML()}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="transaction.transactionDate.label"
                                                     default="Transaction Date"/></td>

            <td valign="top" class="value"><g:formatDate date="${transactionInstance?.transactionDate}"/></td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
