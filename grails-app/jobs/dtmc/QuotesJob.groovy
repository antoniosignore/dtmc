package dtmc

import com.dtmc.finance.finpojo.Daily
import com.dtmc.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.beans.GenericTimeSeries

class QuotesJob {

    def dailyService

    static triggers = {
        simple repeatInterval: 1000l // execute job once in 5 seconds
    }

    def execute() {

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

       dailyService.updateDailyDatabase()

    }
}
