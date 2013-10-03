<%@ page import="com.netnumeri.server.finance.indicator.WeightedMovingAverage" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName"
           value="${message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-weightedMovingAverageUserIndicator" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="weightedMovingAverageUserIndicator.name.label"
                                                     default="Name"/></td>

            <td valign="top"
                class="value">${fieldValue(bean: weightedMovingAverageUserIndicatorInstance, field: "name")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="weightedMovingAverageUserIndicator.smoothing.label"
                                                     default="Smoothing"/></td>

            <td valign="top"
                class="value">${fieldValue(bean: weightedMovingAverageUserIndicatorInstance, field: "smoothing")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="weightedMovingAverageUserIndicator.user.label"
                                                     default="User"/></td>

            <td valign="top" class="value"><g:link controller="userBean" action="show"
                                                   id="${weightedMovingAverageUserIndicatorInstance?.user?.id}">${weightedMovingAverageUserIndicatorInstance?.user?.encodeAsHTML()}</g:link></td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
