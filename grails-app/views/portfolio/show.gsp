<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'portfolio.label', default: 'Portfolio')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-portfolio" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolio.dateCreated.label" default="Date Created"/></td>

            <td valign="top" class="value"><g:formatDate date="${portfolioInstance?.dateCreated}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolio.dateCreated.label" default="Last Updated"/></td>

            <td valign="top" class="value"><g:formatDate date="${portfolioInstance?.lastUpdated}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolio.description.label" default="Description"/></td>

            <td valign="top" class="value">${fieldValue(bean: portfolioInstance, field: "description")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolio.items.label" default="Items"/></td>

            <td valign="top" style="text-align: left;" class="value">
                <ul>
                    <g:each in="${portfolioInstance.items}" var="i">
                        <li><g:link controller="portfolioItem" action="show"
                                    id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
                    </g:each>

                </ul>
            </td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolio.name.label" default="Name"/></td>

            <td valign="top" class="value">${fieldValue(bean: portfolioInstance, field: "name")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="portfolio.transactions.label" default="Transactions"/></td>

            <td valign="top" style="text-align: left;" class="value">
                <ul>
                    <g:each in="${portfolioInstance.transactions}" var="t">
                        <li><g:link controller="trade" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                    </g:each>
                </ul>
            </td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
