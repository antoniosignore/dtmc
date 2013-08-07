<%@ page import="com.netnumeri.server.finance.finpojo.asset.Stock" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    <r:external uri="/js/morris.js"/>
    <r:external uri="/css/morris.js"/>

</head>

<body>

<section id="show-stock" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.dateCreated.label" default="Date Created"/></td>

            <td valign="top" class="value"><g:formatDate date="${stockInstance?.dateCreated}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.description.label" default="Description"/></td>

            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "description")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.name.label" default="Name"/></td>
            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "name")}</td>
        </tr>

        </tbody>
    </table>


    <div id="graph"></div>

    <script>
        var day_data =
                ${javascript};

        Morris.Line({
            element: 'graph',
            data: day_data,
            xkey: 'period',
            ykeys: ['licensed', 'sorned'],
            labels: ['Licensed', 'SORN'],
            xLabelAngle: 60
        });

    </script>

</section>

</body>

</html>
