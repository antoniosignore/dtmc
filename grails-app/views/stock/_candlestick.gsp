<%@ page import="com.netnumeri.server.utils.StockUtils" %>

<script class="code" language="javascript" type="text/javascript">
    $(document).ready(function () {
        var plot2 = $.jqplot('chart2', [ohlc], {
            seriesDefaults: {yaxis: 'y2axis'},
            axes: {
                xaxis: {
                    renderer: $.jqplot.DateAxisRenderer,
                    tickOptions: {formatString: '%b %e'},
                    min: "11-01-2012",
                    max: "09-23-2013",
                    tickInterval: "6 weeks"
                },
                y2axis: {
                    tickOptions: {formatString: '$%d'}
                }
            },
            // To make a candle stick chart, set the "candleStick" option to true.
            series: [
                {
                    renderer: $.jqplot.OHLCRenderer,
                    rendererOptions: { candleStick: true }
                }
            ],
            highlighter: {
                show: true,
                showMarker: false,
                tooltipAxes: 'xy',
                yvalues: 4,
                formatString: '<table class="jqplot-highlighter"> \
                                      <tr><td>date:</td><td>%s</td></tr> \
                                      <tr><td>open:</td><td>%s</td></tr> \
                                      <tr><td>hi:</td><td>%s</td></tr> \
                                      <tr><td>low:</td><td>%s</td></tr> \
                                      <tr><td>close:</td><td>%s</td></tr>\
                                  </table>'
            }
        });
    });

    ohlc =
    ${StockUtils.getJqPlot(stockInstance)}
</script>

<div id="chart2" style="height:600px; width:800px;"></div>
