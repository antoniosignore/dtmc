<%@ page import="com.netnumeri.server.finance.ta.Indicator; com.netnumeri.server.utils.StockUtils" %>


<script class="code" language="javascript" type="text/javascript">
    $(document).ready(function () {
        var plot2 = $.jqplot('chart2', [ohlc, buysignals, sellsignals, lower, upper], {
            seriesDefaults: {yaxis: 'y2axis'},
            axes: {
                xaxis: {
                    renderer: $.jqplot.DateAxisRenderer,
                    tickOptions: {formatString: '%b %e'}
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
                },
                {
//                    renderer: $.jqplot.DateAxisRenderer,
                    showLine:false,
                    markerOptions: { size: 10, style:"filledCircle",color: '#008000' }
                },
                {
//                    renderer: $.jqplot.DateAxisRenderer,
                    showLine:false,
                    markerOptions: { size: 10, style:"filledCircle",color: '#FF0000' }
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
                show: true,
                showMarker: false,
                pointLabels:{ show:true, location:'s', ypadding:3 },
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

    ohlc = ${StockUtils.getCandleStickPlot(stockInstance)}
    buysignals = ${StockUtils.getBuySignals(strategyInstance)}
    sellsignals = ${StockUtils.getSellSignals(strategyInstance)}
    lower= ${stockInstance.indicators.get("lower").jqPlot}
    upper= ${stockInstance.indicators.get("upper").jqPlot}
</script>

<div id="chart2" style="height:600px; width:800px;"></div>
