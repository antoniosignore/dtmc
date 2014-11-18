package dtmc

import com.dtmc.finance.finpojo.Daily
import com.dtmc.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.utils.StockUtils
import grails.gorm.DetachedCriteria
import grails.transaction.Transactional

@Transactional
class DailyService {

    /*
     *   def cacheloader = { key -> loadElement(key) } as CacheLoader def cache = CacheBuilder.newBuilder(). expireAfterWrite(2, TimeUnit.HOURS). maximumSize(1000). build(cacheloader) def get(key) { cache.get(key) }
     */

    public void updateDailyDatabase() {

        Date da = DateUtils.todayOneYearAgo()
        Date a = DateUtils.today()

        def all = Stock.findAll()

        all.each { stock ->

            println "stock.name = $stock.name"

            List<Daily> dailies = Daily.findAllByInstrument(stock, [sort: "dailydate", order: "asc"])

            println "dailies = $dailies"

            if (dailies.size() > 0) da = stock.lastDate()

//            println "da = $da"
//            println "DateUtils.today() = " + DateUtils.today()
//
            if (!DateUtils.isEqual(da, DateUtils.today()))  {
                stock.dailyarray.clear()
                println "refresh: da " + da
                println "refresh: a " + a
                StockUtils.refreshDaily(stock, da, a);
            }
        }
    }

    public void dailyFromDatabase(Stock stock) {

        def criteria = new DetachedCriteria(Daily).build {
            eq('instrument.id', stock.getId())
        }

        List<Daily> slides = criteria.list(sort: "dailydate", order: "asc")
        for (int i = 0; i < slides.size(); i++) {
            Daily daily = slides.get(i);
            stock.dailyarray.put(daily.dailydate, daily)
        }
    }

}
