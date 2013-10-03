<%@ page import="com.netnumeri.server.finance.indicator.SimpleMovingAverage" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName"
           value="${message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-simpleMovingAverageUserIndicator" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="simpleMovingAverageUserIndicator.name.label"
                                                     default="Name"/></td>

            <td valign="top"
                class="value">${fieldValue(bean: simpleMovingAverageUserIndicatorInstance, field: "name")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="simpleMovingAverageUserIndicator.smoothing.label"
                                                     default="Smoothing"/></td>

            <td valign="top"
                class="value">${fieldValue(bean: simpleMovingAverageUserIndicatorInstance, field: "smoothing")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="simpleMovingAverageUserIndicator.user.label"
                                                     default="User"/></td>

            <td valign="top" class="value"><g:link controller="userBean" action="show"
                                                   id="${simpleMovingAverageUserIndicatorInstance?.user?.id}">${simpleMovingAverageUserIndicatorInstance?.user?.username}</g:link></td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
