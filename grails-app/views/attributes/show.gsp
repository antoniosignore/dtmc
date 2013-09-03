<%@ page import="com.netnumeri.server.finance.indicator.Attributes" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'attributes.label', default: 'Attributes')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-attributes" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="attributes.name.label"
                                                     default="Name"/></td>

            <td valign="top" class="value">${fieldValue(bean: attributesInstance, field: "name")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="attributes.userIndicator.label"
                                                     default="User Indicator"/></td>

            <td valign="top" class="value"><g:link controller="userIndicators" action="show"
                                                   id="${attributesInstance?.userIndicator?.id}">${attributesInstance?.userIndicator?.name.encodeAsHTML()}</g:link></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="attributes.value.label"
                                                     default="Value"/></td>

            <td valign="top" class="value">${fieldValue(bean: attributesInstance, field: "value")}</td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
