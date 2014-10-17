<%@ page import="com.netnumeri.server.utils.StockUtils" %>

<div data-ng-controller="StockCtrl" data-ng-init="candle(instance)">

</div>

<script class="code" language="javascript" type="text/javascript">


$(window).resize(function () {
    plot2.replot({ resetAxes: true });
});

</script>

<div id="chart" style="height:400px; width:100%;"></div>


