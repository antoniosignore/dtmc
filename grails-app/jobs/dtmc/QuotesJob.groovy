package dtmc

class QuotesJob {

    def yahooFinanceYQLService

    static triggers = {
        simple repeatInterval: 10000l // execute job once in 5 seconds
    }

    def execute() {

//        List listOfStandardStats = yahooFinanceYQLService.quote("YHOO")
//        List multipleStocks = yahooFinanceYQLService.quote("YHOO, MSFT")
//        List specificStats = yahooFinanceYQLService.quote("HNR", "daysrange, volume")
//        List historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", 30)
//        historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", "1-1-2011", "1-31-2013")
//        def sdate = new GregorianCalendar(201, Calendar.NOVEMBER, 1).getTime()
//        def edate = new GregorianCalendar(2012, Calendar.OCTOBER, 1).getTime()
//        List pluto = yahooFinanceYQLService.getHistoricalQuotes("YHOO", sdate, edate)
        def historicalLast30days = yahooFinanceYQLService.getHistoricalQuotes("YHOO", "1-1-2011", "1-31-2013")

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
