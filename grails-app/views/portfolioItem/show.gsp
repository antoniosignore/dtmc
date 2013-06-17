<%@ page import="com.netnumeri.server.finance.finpojo.PortfolioItem" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'portfolioItem.label', default: 'PortfolioItem')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-portfolioItem" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolioItem.amount.label"
                                                     default="Amount"/></td>

            <td valign="top" class="value">${fieldValue(bean: portfolioItemInstance, field: "amount")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolioItem.instrument.label"
                                                     default="Instrument"/></td>

            <td valign="top" class="value"><g:link controller="instrument" action="show"
                                                   id="${portfolioItemInstance?.instrument?.id}">${portfolioItemInstance?.instrument?.encodeAsHTML()}</g:link></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolioItem.portfolio.label"
                                                     default="Portfolio"/></td>

            <td valign="top" class="value"><g:link controller="portfolio" action="show"
                                                   id="${portfolioItemInstance?.portfolio?.id}">${portfolioItemInstance?.portfolio?.encodeAsHTML()}</g:link></td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
