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

    <table class="table table-striped table-bordered table-condensed table-hover">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.deviation.label"
                                                     default="Deviation"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "double1")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.k.label"
                                                     default="K"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "double2")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.length.label"
                                                     default="Length"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "length")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.method.label"
                                                     default="Method"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "method")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.name.label"
                                                     default="Name"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "name")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.indicatorOrder.label"
                                                     default="Order"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "indicatorOrder")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.period.label"
                                                     default="Period"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "period")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.smoothing1.label"
                                                     default="Smoothing1"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "integer1")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.smoothing2.label"
                                                     default="Smoothing2"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "integer2")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.smoothing3.label"
                                                     default="Smoothing3"/></td>

            <td valign="top" class="value">${fieldValue(bean: userIndicatorsInstance, field: "integer3")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.type.label"
                                                     default="Type"/></td>

            <td valign="top" class="value">${userIndicatorsInstance?.type?.encodeAsHTML()}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userIndicators.user.label"
                                                     default="User"/></td>

            <td valign="top" class="value"><g:link controller="userBean" action="show"
                                                   id="${userIndicatorsInstance?.user?.id}">${userIndicatorsInstance?.user?.encodeAsHTML()}</g:link></td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
