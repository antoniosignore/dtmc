<table>
    <tr>
        <td>
            <table class="table table-striped table-bordered table-condensed table-hover">
                <tr><td>Last Price</td><td>${stockInstance.snapshot.lastPrice}</td></tr>
                <tr><td>Symbol</td><td>${stockInstance.snapshot.symbol}</td></tr>
                <tr><td>CompanyName</td><td>${stockInstance.snapshot.companyName}</td></tr>
                <tr><td>LastPrice</td><td>${stockInstance.snapshot.lastPrice}</td></tr>
                <tr><td>LastTradeDate</td><td>${stockInstance.snapshot.lastTradeDate}</td></tr>
                <tr><td>LastTradeTime</td><td>${stockInstance.snapshot.lastTradeTime}</td></tr>
            </table>
        </td>
        <td>
            <table class="table table-striped table-bordered table-condensed table-hover">
                <tr><td>Change</td><td>${stockInstance.snapshot.change}</td></tr>
                <tr><td>PercentChange</td><td>${stockInstance.snapshot.percentChange}</td></tr>
                <tr><td>Volume</td><td>${stockInstance.snapshot.volume}</td></tr>
                <tr><td>AverageDailyVol</td><td>${stockInstance.snapshot.averageDailyVol}</td></tr>
                <tr><td>Bid</td><td>${stockInstance.snapshot.bid}</td></tr>
                <tr><td>Ask</td><td>${stockInstance.snapshot.ask}</td></tr>
            </table>
        </td>
        <td>
            <table class="table table-striped table-bordered table-condensed table-hover">
                <tr><td>PreviousClose</td><td>${stockInstance.snapshot.previousClose}</td></tr>
                <tr><td>TodaysOpen</td><td>${stockInstance.snapshot.todaysOpen}</td></tr>
                <tr><td>DaysRange</td><td>${stockInstance.snapshot.daysRange}</td></tr>
                <tr><td>YearWeekRange</td><td>${stockInstance.snapshot.yearWeekRange}</td></tr>
                <tr><td>EarningsperShare</td><td>${stockInstance.snapshot.earningsperShare}</td></tr>
                <tr><td>this.PERatio</td><td>${stockInstance.snapshot.PERatio}</td></tr>
            </table>
        </td>
        <td valign="top">
            <table class="table table-striped table-bordered table-condensed table-hover">
                <tr><td>DividendPayDate</td><td>${stockInstance.snapshot.dividendPayDate}</td></tr>
                <tr><td>DividendperShare</td><td>${stockInstance.snapshot.dividendperShare}</td></tr>
                <tr><td>DividendYield</td><td>${stockInstance.snapshot.dividendYield}</td></tr>
                <tr><td>MarketCapitalization</td><td>${stockInstance.snapshot.marketCapitalization}</td></tr>
                <tr><td>StockExchange</td><td>${stockInstance.snapshot.stockExchange}</td></tr>
            </table>
        </td>
    </tr>
</table>
