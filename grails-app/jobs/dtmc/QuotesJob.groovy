package dtmc

import com.dtmc.finance.finpojo.asset.Stock
import com.dtmc.trading.Signal
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.strategy.SMACrossoverSignal
import com.netnumeri.server.finance.strategy.Strategy
import com.netnumeri.server.finance.ta.SMAIndicator
import com.netnumeri.server.finance.utils.DateUtils

class QuotesJob {

    def dailyService


    static triggers = {
        simple repeatInterval: 10000l // execute job once in 5 seconds
    }

    def execute() {

        Date startDate = new GregorianCalendar(2011, Calendar.NOVEMBER, 1).getTime()
        Date endDate = DateUtils.today();

        def listOfStocks = Stock.list()

        listOfStocks.each { stock ->

            println "stock.name = $stock.name"

            dailyService.dailyFromYahoo (stock, startDate, endDate)

            TimeSeries closes = stock.series(FinConstants.CLOSE);
            stock.indicators.put("upper", new SMAIndicator(closes, "SMA-" + 50, 50))
            stock.indicators.put("lower", new SMAIndicator(closes, "SMA-" + 10, 10))
            Strategy strategy = new SMACrossoverSignal("test", stock, startDate, endDate);
            strategy.execute();
            def signals = strategy.signals

            for (int li = 0; li < signals.size(); li++) {
                Signal signal =  signals.get(li)
                println "signal = $signal"
            }
        }


//        def yahooFinanceYQLService
//        List listOfStandardStats = yahooFinanceYQLService.quote("YHOO")
//        List multipleStocks = yahooFinanceYQLService.quote("YHOO, MSFT")
//        List specificStats = yahooFinanceYQLService.quote("HNR", "daysrange, volume")
//        List historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", 30)
//        historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", "1-1-2011", "1-31-2013")
//        def sdate = new GregorianCalendar(201, Calendar.NOVEMBER, 1).getTime()
//        def edate = new GregorianCalendar(2012, Calendar.OCTOBER, 1).getTime()
//        List pluto = yahooFinanceYQLService.getHistoricalQuotes("YHOO", sdate, edate)

//        def historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", "1-1-2011", "1-31-2013")

//        def all = Stock.list(fetch:[dailiarray:"eager"])
//        all.each { stock ->
//            println "stock.name = $stock.name"
//            def dailyarray = stock.getDailyarray();
//            stock.dailyarray = Daily.findAllByInstrument(stock, [sort: "dailydate", order: "asc"])
//            println "numero di dailies ---> " + dailyarray.size()
//        }
//       dailyService.updateDailyDatabase()

    }
}
