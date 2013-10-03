<%@ page import="com.netnumeri.server.finance.indicator.WeightedMovingAverage" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName"
           value="${message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-weightedMovingAverageUserIndicator" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="name"
                              title="${message(code: 'weightedMovingAverageUserIndicator.name.label', default: 'Name')}"/>

            <g:sortableColumn property="smoothing"
                              title="${message(code: 'weightedMovingAverageUserIndicator.smoothing.label', default: 'Smoothing')}"/>

            <th><g:message code="weightedMovingAverageUserIndicator.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${weightedMovingAverageUserIndicatorInstanceList}" status="i"
                var="weightedMovingAverageUserIndicatorInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${weightedMovingAverageUserIndicatorInstance.id}">${fieldValue(bean: weightedMovingAverageUserIndicatorInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: weightedMovingAverageUserIndicatorInstance, field: "smoothing")}</td>

                <td>${fieldValue(bean: weightedMovingAverageUserIndicatorInstance, field: "user")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${weightedMovingAverageUserIndicatorInstanceTotal}"/>
    </div>
</section>

</body>

</html>
