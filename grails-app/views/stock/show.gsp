<%@ page import="com.netnumeri.server.utils.StockUtils" %>


<div data-ng-controller="StockCtrl">
    UserId: {{stock.name}}<br/>
    <candlestick-chart ng-model="name"></candlestick-chart>
    <div id="chart" style="height:250px; width:100%;"></div>
</div>


