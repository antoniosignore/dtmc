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

    <div class="updateDatesForm">
        <g:formRemote url="[controller: 'stock', action: 'show']" update="messages" name="updateDatesForm">
            <div class="control-group required">
                <label for="range" class="control-label"><g:message
                        code="dates.range.label"
                        default="Dates range"/></label>
                <g:render template="/common/datetimepicker"/>
            </div>

            <g:hiddenField name="id" value="${stockInstance.id}"/>
            <g:hiddenField name="ayax" value="true"/>
            <g:submitButton name="Refresh"/>
        </g:formRemote>
    </div>

    <g:render template="/common/charts"/>

</section>

<div id="container" class="js-masonry"
data-masonry-options='{ "columnWidth": 200, "itemSelector": ".item" }'>
<dtmc:indicators indicators="${indicators}"/>
</div>

</body>

</html>
