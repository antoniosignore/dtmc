
<%@ page import="com.netnumeri.server.finance.finpojo.SMACrossover" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'SMACrossover.label', default: 'SMACrossover')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-SMACrossover" class="first">

    <table class="table table-striped table-bordered table-condensed table-hover">
        <tbody>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.dateCreated.label"
                                                     default="Date Created"/></td>
            
            <td valign="top" class="value"><g:formatDate date="${SMACrossoverInstance?.dateCreated}"/></td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.higherMA.label"
                                                     default="Higher MA"/></td>
            
            <td valign="top" class="value">${fieldValue(bean: SMACrossoverInstance, field: "higherMA")}</td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.lastUpdated.label"
                                                     default="Last Updated"/></td>
            
            <td valign="top" class="value"><g:formatDate date="${SMACrossoverInstance?.lastUpdated}"/></td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.lowerMA.label"
                                                     default="Lower MA"/></td>
            
            <td valign="top" class="value">${fieldValue(bean: SMACrossoverInstance, field: "lowerMA")}</td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.member.label"
                                                     default="Member"/></td>
            
            <td valign="top" class="value"><g:link controller="member" action="show"
                                                   id="${SMACrossoverInstance?.member?.id}">${SMACrossoverInstance?.member?.username?.encodeAsHTML()}</g:link></td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.name.label"
                                                     default="Name"/></td>
            
            <td valign="top" class="value">${fieldValue(bean: SMACrossoverInstance, field: "name")}</td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.portfolio.label"
                                                     default="Portfolio"/></td>
            
            <td valign="top" class="value"><g:link controller="portfolio" action="show"
                                                   id="${SMACrossoverInstance?.portfolio?.id}">${SMACrossoverInstance?.portfolio?.encodeAsHTML()}</g:link></td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.strategyEnum.label"
                                                     default="Strategy Enum"/></td>
            
            <td valign="top" class="value">${SMACrossoverInstance?.strategyEnum?.encodeAsHTML()}</td>
            
        </tr>
        
        <tr class="prop">
            <td valign="top" class="name"><g:message code="SMACrossover.when.label"
                                                     default="When"/></td>
            
            <td valign="top" class="value"><g:formatDate date="${SMACrossoverInstance?.when}"/></td>
            
        </tr>
        
        </tbody>
    </table>
</section>

</body>

</html>
