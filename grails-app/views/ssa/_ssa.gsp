<%@ page import="com.netnumeri.server.finance.ta.Indicator; com.netnumeri.server.utils.StockUtils" %>

<script class="code" language="javascript" type="text/javascript">
    $(document).ready(function () {
        var plot2 = $.jqplot('chart2', [normalized, trend, comp01], {
            seriesDefaults: {yaxis: 'yaxis'},
            axes: {
                xaxis: {
                    renderer: $.jqplot.DateAxisRenderer,
                    tickOptions: {formatString: '%b %e'}
                },
                yaxis: {
                    tickOptions: {formatString: '%d'}
                }
            },
            // To make a candle stick chart, set the "candleStick" option to true.
            series: [
                {
                    lineWidth:1, showMarker: false,
                    showLine:true
                },
                {
                    lineWidth:1, showMarker: false,
                    showLine:true
                },
                {
                    lineWidth:1, showMarker: false,
                    showLine:true
                }
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

    normalized = ${stockInstance.indicators.get("normalized").jqPlot}
    trend= ${stockInstance.indicators.get("trend").jqPlot}
    comp01= ${stockInstance.indicators.get("comp01").jqPlot}

</script>

<div id="chart2" style="height:600px; width:100%;"></div>
