
<%@ page import="com.netnumeri.server.finance.indicator.Indicators" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'indicators.label', default: 'Indicators')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-indicators" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>
            
            <g:sortableColumn property="indicator"
                              title="${message(code: 'indicators.indicator.label', default: 'Indicator')}"/>
            
            <g:sortableColumn property="name"
                              title="${message(code: 'indicators.name.label', default: 'Name')}"/>
            
            <th><g:message code="indicators.user.label" default="User"/></th>
            
        </tr>
        </thead>
        <tbody>
        <g:each in="${indicatorsInstanceList}" status="i" var="indicatorsInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                
                <td><g:link action="show"
                            id="${indicatorsInstance.id}">${fieldValue(bean: indicatorsInstance, field: "indicator")}</g:link></td>
                
                <td>${fieldValue(bean: indicatorsInstance, field: "name")}</td>
                
                <td>${fieldValue(bean: indicatorsInstance, field: "user")}</td>
                
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${indicatorsInstanceTotal}"/>
    </div>
</section>

</body>

</html>
