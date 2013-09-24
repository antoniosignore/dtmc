<%@ page import="com.netnumeri.server.finance.indicator.UserIndicators" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'userIndicators.label', default: 'UserIndicators')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-userIndicators" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.indicator.label" default="Indicator"/></td>

            <td valign="top" class="value"><g:link controller="indicators" action="show"
                                                   id="${userIndicatorsInstance?.indicator?.id}">${userIndicatorsInstance?.indicator?.code.encodeAsHTML()}</g:link></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.name.label" default="Name"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "name")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.user.label" default="User"/></td>

            <td valign="top" class="value"><g:link controller="userBean" action="show"
                                                   id="${userIndicatorsInstance?.user?.id}">${userIndicatorsInstance?.user?.username.encodeAsHTML()}</g:link></td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
