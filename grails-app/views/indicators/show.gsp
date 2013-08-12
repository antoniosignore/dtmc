
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
            <td valign="top" class="name"><g:message code="indicators.attributes.label"
                                                     default="Attributes"/></td>
            
            <td valign="top" style="text-align: left;" class="value">
                <ul>
                    <g:each in="${indicatorsInstance.attributes}" var="a">
                        <li><g:link controller="attributes" action="show"
                                    id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                    </g:each>
                </ul>
            </td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="indicators.indicator.label"
                                                     default="Indicator"/></td>
            
            <td valign="top" class="value">${indicatorsInstance?.indicator?.encodeAsHTML()}</td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="indicators.name.label"
                                                     default="Name"/></td>
            
            <td valign="top" class="value">${fieldValue(bean: indicatorsInstance, field: "name")}</td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="indicators.user.label"
                                                     default="User"/></td>
            
            <td valign="top" class="value"><g:link controller="userBean" action="show"
                                                   id="${indicatorsInstance?.user?.id}">${indicatorsInstance?.user?.encodeAsHTML()}</g:link></td>
            
        </tr>
        
        </tbody>
    </table>
</section>

</body>

</html>
