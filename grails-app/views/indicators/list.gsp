
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
            
            <g:sortableColumn property="code"
                              title="${message(code: 'indicators.code.label', default: 'Code')}"/>
            
        </tr>
        </thead>
        <tbody>
        <g:each in="${indicatorsInstanceList}" status="i" var="indicatorsInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                
                <td><g:link action="show"
                            id="${indicatorsInstance.id}">${fieldValue(bean: indicatorsInstance, field: "code")}</g:link></td>
                
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
