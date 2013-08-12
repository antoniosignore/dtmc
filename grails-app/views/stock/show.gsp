<%@ page import="com.netnumeri.server.finance.finpojo.asset.Stock" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    <r:external uri="/js/morris.js"/>
    <r:external uri="/css/morris.css"/>

    <r:external uri="/css/jquery.jqplot.css"/>
    <r:external uri="/js/jquery.jqplot.js"/>
    <r:external uri="/js/plugins/jqplot.dateAxisRenderer.min.js"/>
    <r:external uri="/js/plugins/jqplot.ohlcRenderer.min.js"/>
    <r:external uri="/js/plugins/jqplot.highlighter.min.js"/>

</head>

<body>

<section id="show-stock" class="first">

    <table class="table">
        <tbody>
        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.dateCreated.label" default="Date Created"/></td>
            <td valign="top" class="value"><g:formatDate date="${stockInstance?.dateCreated}"/></td>
        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.description.label" default="Description"/></td>
            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "description")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.name.label" default="Name"/></td>
            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "name")}</td>
        </tr>
        </tbody>
    </table>

    <table>
        <tr>
            <td>
                <table class="table">
                    <tr><td>Last Price</td><td>${stockInstance.snapshot.lastPrice}</td></tr>
                    <tr><td>Symbol</td><td>${stockInstance.snapshot.symbol}</td></tr>
                    <tr><td>CompanyName</td><td>${stockInstance.snapshot.companyName}</td></tr>
                    <tr><td>LastPrice</td><td>${stockInstance.snapshot.lastPrice}</td></tr>
                    <tr><td>LastTradeDate</td><td>${stockInstance.snapshot.lastTradeDate}</td></tr>
                    <tr><td>LastTradeTime</td><td>${stockInstance.snapshot.lastTradeTime}</td></tr>
                </table>
            </td>
            <td>
                <table class="table">
                    <tr><td>Change</td><td>${stockInstance.snapshot.change}</td></tr>
                    <tr><td>PercentChange</td><td>${stockInstance.snapshot.percentChange}</td></tr>
                    <tr><td>Volume</td><td>${stockInstance.snapshot.volume}</td></tr>
                    <tr><td>AverageDailyVol</td><td>${stockInstance.snapshot.averageDailyVol}</td></tr>
                    <tr><td>Bid</td><td>${stockInstance.snapshot.bid}</td></tr>
                    <tr><td>Ask</td><td>${stockInstance.snapshot.ask}</td></tr>
                </table>
            </td>
            <td>
                <table class="table">
                    <tr><td>PreviousClose</td><td>${stockInstance.snapshot.previousClose}</td></tr>
                    <tr><td>TodaysOpen</td><td>${stockInstance.snapshot.todaysOpen}</td></tr>
                    <tr><td>DaysRange</td><td>${stockInstance.snapshot.daysRange}</td></tr>
                    <tr><td>YearWeekRange</td><td>${stockInstance.snapshot.yearWeekRange}</td></tr>
                    <tr><td>EarningsperShare</td><td>${stockInstance.snapshot.earningsperShare}</td></tr>
                    <tr><td>this.PERatio</td><td>${stockInstance.snapshot.PERatio}</td></tr>
                </table>
            </td>
            <td valign="top">
                <table class="table">
                    <tr><td>DividendPayDate</td><td>${stockInstance.snapshot.dividendPayDate}</td></tr>
                    <tr><td>DividendperShare</td><td>${stockInstance.snapshot.dividendperShare}</td></tr>
                    <tr><td>DividendYield</td><td>${stockInstance.snapshot.dividendYield}</td></tr>
                    <tr><td>MarketCapitalization</td><td>${stockInstance.snapshot.marketCapitalization}</td></tr>
                    <tr><td>StockExchange</td><td>${stockInstance.snapshot.stockExchange}</td></tr>
                </table>
            </td>
        </tr>
    </table>

    <script class="code" language="javascript" type="text/javascript">
        $(document).ready(function () {
            var plot2 = $.jqplot('chart2', [ohlc], {
                seriesDefaults: {yaxis: 'y2axis'},
                axes: {
                    xaxis: {
                        renderer: $.jqplot.DateAxisRenderer,
                        tickOptions: {formatString: '%b %e'},
                        min: "11-01-2012",
                        max: "08-08-2013",
                        tickInterval: "6 weeks",
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
    </script>

    ${ohlc}

    <ul class="nav nav-pills">
        <li class="active"><a href="#tabs4-pane1" data-toggle="tab">Candlestick</a></li>
        <li class=""><a href="#tabs4-pane2" data-toggle="tab">Line chart</a></li>
    </ul>

    <div class="tab-content">
        <div id="tabs4-pane1" class="tab-pane active">
            <div id="chart2" style="height:600px; width:800px;"></div>
        </div>

        <div id="tabs4-pane2" class="tab-pane">
            <div id="graph" style="height: 600px; width:800px;"></div>
        </div>
    </div>

    <script>
        var day_data =
                ${javascript};

        Morris.Line({
            element: 'graph',
            data: day_data,
            xkey: 'period',
            ykeys: ['close'],
            labels: ['Close'],
            lineWidth: 1,
            pointSize: 1,
            xLabelAngle: 60,
            ymin: 'auto',
            ymax: 'auto',
            axes: 'false'
        });

    </script>

</section>

</body>

</html>
