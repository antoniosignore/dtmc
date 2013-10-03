<%@ page import="com.netnumeri.server.finance.indicator.SimpleMovingAverage" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName"
           value="${message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-simpleMovingAverageUserIndicator" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="name"
                              title="${message(code: 'simpleMovingAverageUserIndicator.name.label', default: 'Name')}"/>

            <g:sortableColumn property="smoothing"
                              title="${message(code: 'simpleMovingAverageUserIndicator.smoothing.label', default: 'Smoothing')}"/>

            <th><g:message code="simpleMovingAverageUserIndicator.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${simpleMovingAverageUserIndicatorInstanceList}" status="i"
                var="simpleMovingAverageUserIndicatorInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${simpleMovingAverageUserIndicatorInstance.id}">${fieldValue(bean: simpleMovingAverageUserIndicatorInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: simpleMovingAverageUserIndicatorInstance, field: "smoothing")}</td>

                <td>${fieldValue(bean: simpleMovingAverageUserIndicatorInstance, field: "user")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${simpleMovingAverageUserIndicatorInstanceTotal}"/>
    </div>
</section>

</body>

</html>
