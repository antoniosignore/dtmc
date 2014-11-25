package dtmc

import com.dtmc.finance.finpojo.Daily
import com.dtmc.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.beans.GenericTimeSeries

class QuotesJob {

    def dailyService
    def yahooFinanceYQLService

    static triggers = {
        simple repeatInterval: 10000l // execute job once in 5 seconds
    }

    def execute() {

        def listOfStandardStats = yahooFinanceYQLService.quote("YHOO")
// => returns List - symbol: "YHOO", name: "Yahoo Inc.", lastTrade: "15.50", ...
        def multipleStocks = yahooFinanceYQLService.quote("YHOO, MSFT")
// => returns List [symbol: "YHOO", name: "Yahoo Inc.", lastTrade: "15.50", ...],
//    [symbol: "MSFT", name: "Microsoft Inc.", lastTrade: "29.50", ...]
        def specificStats = yahooFinanceYQLService.quote("HNR", "daysrange, volume")
// => returns List - [symbol:HNR, name:Harvest Natural R, daysrange:5.35 - 5.42, volume:470356]

        def historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", 30)
// => Returns list of [Date, Open, High, Low, Close, Volume, Adj Close] for last 30 days

        historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", "1-1-2011", "1-31-2013")
// => returns last January historical data for stock YHOO

        def sdate = new GregorianCalendar(201, Calendar.NOVEMBER, 1).getTime()
        def edate = new GregorianCalendar(2012, Calendar.OCTOBER, 1).getTime()
        def pluto = yahooFinanceYQLService.getHistoricalQuotes("YHOO", sdate, edate)


        historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", "1-1-2011", "1-31-2013")

//        def all = Stock.list(fetch:[dailiarray:"eager"])
//
//        all.each { stock ->
//
//            println "stock.name = $stock.name"
//
//            def dailyarray = stock.getDailyarray();
//
//            stock.dailyarray = Daily.findAllByInstrument(stock, [sort: "dailydate", order: "asc"])
//
//            println "numero di dailies ---> " + dailyarray.size()
//
//        }

//       dailyService.updateDailyDatabase()

    }
}
