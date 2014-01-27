<%@ page import="com.netnumeri.server.finance.ta.Indicator; com.netnumeri.server.utils.StockUtils" %>

<script class="code" language="javascript" type="text/javascript">
    $(document).ready(function () {
        var plot2 = $.jqplot('chart2', [normalized, trend], {
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
                }
            ],
            highlighter: {
                show: true
            },
            cursor: {
                show: true
            }
        });
    });

    normalized = ${stockInstance.indicators.get("normalized").jqPlot}
    trend= ${stockInstance.indicators.get("trend").jqPlot}

</script>

<div id="chart2" style="height:300px; width:100%;"></div>
