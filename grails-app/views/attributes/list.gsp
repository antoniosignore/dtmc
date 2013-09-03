<%@ page import="com.netnumeri.server.finance.indicator.Attributes" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'attributes.label', default: 'Attributes')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-attributes" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <th><g:message code="attributes.userIndicator.label" default="User Indicator"/></th>

            <g:sortableColumn property="name"
                              title="${message(code: 'attributes.name.label', default: 'Name')}"/>

            <g:sortableColumn property="value"
                              title="${message(code: 'attributes.value.label', default: 'Value')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${attributesInstanceList}" status="i" var="attributesInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${attributesInstance.id}">${fieldValue(bean: attributesInstance, field: "userIndicator.name")}</g:link></td>

                <td>${fieldValue(bean: attributesInstance, field: "name")}</td>



                <td>${fieldValue(bean: attributesInstance, field: "value")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${attributesInstanceTotal}"/>
    </div>
</section>

</body>

</html>
