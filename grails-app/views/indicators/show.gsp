
<%@ page import="com.netnumeri.server.finance.indicator.Indicators" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'indicators.label', default: 'Indicators')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-indicators" class="first">

    <table class="table">
        <tbody>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="indicators.code.label"
                                                     default="Code"/></td>
            
            <td valign="top" class="value">${fieldValue(bean: indicatorsInstance, field: "code")}</td>
            
        </tr>
        
        </tbody>
    </table>
</section>

</body>

</html>
