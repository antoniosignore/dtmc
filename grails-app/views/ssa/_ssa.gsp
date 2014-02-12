<%@ page import="com.netnumeri.server.finance.ta.Indicator; com.netnumeri.server.utils.StockUtils" %>

<script class="code" language="javascript" type="text/javascript">
    $(document).ready(function () {
        var plot2 = $.jqplot('chart2-${StockUtils.lastDate(stockInstance)}',
                [normalized${StockUtils.lastDate(stockInstance)},
                    trend${StockUtils.lastDate(stockInstance)},
                    comp1${StockUtils.lastDate(stockInstance)}], {
                    seriesDefaults: {yaxis: 'yaxis'},
                    title: {
                        text: '${StockUtils.lastDate(stockInstance)}',
                        show: true
                    }, axes: {
                        xaxis: {
                    renderer: $.jqplot.DateAxisRenderer,
                    tickOptions: {formatString: '%b %e'}
                },
                yaxis: {
                    tickOptions: {formatString: '%#.5f'}
                }
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

</script>

<div class="item" id="chart2-${StockUtils.lastDate(stockInstance)}" style="height:300px; width:100%;"></div>

%{--<table>--}%
%{--<tr>--}%
%{--<td>--}%
%{--${StockUtils.lastDate(stockInstance)}--}%
%{--</td>--}%
%{--</tr>--}%
%{--<tr>--}%
%{--<td>--}%
%{--<div id="chart2-${StockUtils.lastDate(stockInstance)}" style="height:300px; width:50%;"></div>--}%
%{--</td>--}%
%{--</tr>--}%
%{--</table>--}%
