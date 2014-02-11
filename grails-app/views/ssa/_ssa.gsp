<%@ page import="com.netnumeri.server.finance.ta.Indicator; com.netnumeri.server.utils.StockUtils" %>

<hr/>

${StockUtils.lastDate(stockInstance)}

<script class="code" language="javascript" type="text/javascript">
    $(document).ready(function () {
        var plot2 = $.jqplot('chart2-${StockUtils.lastDate(stockInstance)}', [normalized${StockUtils.lastDate(stockInstance)}, trend${StockUtils.lastDate(stockInstance)}, comp1${StockUtils.lastDate(stockInstance)}], {
            seriesDefaults: {yaxis: 'yaxis'},
            axes: {
                xaxis: {
                    renderer: $.jqplot.DateAxisRenderer,
                    tickOptions: {formatString: '%b %e'}
                },
                yaxis: {
                    tickOptions: {formatString: '%d'}
                },
                y3axis:{min:-1, max:+1}
            },
            series: [
                {
                    lineWidth:1, showMarker: false,
                    color: '#000000',
                    showLine:true
                },
                {
                    lineWidth:1, showMarker: false,
                    showLine:true
                },
                {
                    lineWidth:1, showMarker: false,
                    showLine:true
                },
                {
                    lineWidth:1,
                    color: 'red',
                    showMarker: false,
                    showLine:true,
                    yaxis:'y3axis'}
            ],
            highlighter: {
                show: true
            },
            cursor: {
                show: true
            },
            grid:{
                borderColor:'transparent',
                shadow:false,
                drawBorder:false
            }
        });
    });

    normalized${StockUtils.lastDate(stockInstance)} = ${stockInstance.indicators.get("normalized").jqPlot}
            trend${StockUtils.lastDate(stockInstance)} = ${stockInstance.indicators.get("trend").jqPlot}
                    comp1${StockUtils.lastDate(stockInstance)} =
    ${stockInstance.indicators.get("comp1").jqPlot}
    %{--comp2${StockUtils.lastDate(stockInstance)} =--}%
    %{--${stockInstance.indicators.get("comp12").jqPlot}--}%

</script>

<div id="chart2-${StockUtils.lastDate(stockInstance)}" style="height:600px; width:100%;"></div>
