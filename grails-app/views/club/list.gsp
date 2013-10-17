<%@ page import="com.dtmc.club.Club" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-club" class="first">

    <table class="table table-striped table-bordered table-condensed table-hover">
        <thead>
        <tr>

        </tr>
        </thead>
        <tbody>
        <g:each in="${clubInstanceList}" status="i" var="clubInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${clubInstanceTotal}"/>
    </div>
</section>

</body>

</html>
