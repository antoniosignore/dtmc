<%@ page import="com.netnumeri.server.utils.StockUtils; com.netnumeri.server.finance.finpojo.asset.Stock" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    %{--<r:external uri="/css/metro-bootstrap.css"/>--}%

    <r:external uri="/css/jquery.jqplot.css"/>
    <r:external uri="/js/jquery.jqplot.js"/>
    <r:external uri="/js/plugins/jqplot.dateAxisRenderer.min.js"/>
    <r:external uri="/js/plugins/jqplot.ohlcRenderer.min.js"/>
    <r:external uri="/js/plugins/jqplot.highlighter.min.js"/>

</head>

<body>

<section id="show-stock" class="first">

    <table class="table table-striped table-bordered table-condensed table-hover">
        <tbody>
        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.name.label" default="Name"/></td>
            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "name")}</td>
        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.description.label" default="Description"/></td>
            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "description")}</td>

        </tr>
        </tbody>
    </table>

    <g:render template="snapshot"/>
    <g:render template="candlestick"/>

</section>

%{--<div id="container" class="js-masonry"--}%
%{--data-masonry-options='{ "columnWidth": 200, "itemSelector": ".item" }'>--}%
%{--<dtmc:indicators indicators="${indicators}"/>--}%
%{--</div>--}%


<dtmc:indicators indicators="${indicators}"/>

</body>

</html>
