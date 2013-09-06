<%@ page import="com.netnumeri.server.finance.indicator.UserIndicators" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'userIndicators.label', default: 'UserIndicators')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-userIndicators" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'userIndicators.name.label', default: 'Name')}"/>

            <th><g:message code="userIndicators.indicator.label" default="Indicator"/></th>

            <th><g:message code="userIndicators.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${userIndicatorsInstanceList}" status="i" var="userIndicatorsInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${userIndicatorsInstance.id}">${fieldValue(bean: userIndicatorsInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: userIndicatorsInstance, field: "indicator.code")}</td>

                <td>${fieldValue(bean: userIndicatorsInstance, field: "user.username")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${userIndicatorsInstanceTotal}"/>
    </div>
</section>

</body>

</html>
