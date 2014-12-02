package dtmc

import com.dtmc.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.utils.DateUtils
import grails.transaction.Transactional

@Transactional
class DailyService {

    def yahooFinanceYQLService

    /*
     *   def cacheloader = { key -> loadElement(key) } as CacheLoader def cache = CacheBuilder.newBuilder(). expireAfterWrite(2, TimeUnit.HOURS). maximumSize(1000). build(cacheloader) def get(key) { cache.get(key) }
     */

    public void dailyFromYahoo(Stock stock, Date startDate , Date endDate) {



        List quotes = yahooFinanceYQLService.getHistoricalQuotes(stock.name, startDate, endDate)
        Stack lines = new Stack()
        for (int i = 0; i < quotes.size(); i++) {            String[] quoteString = quotes.get(i);
            if (quoteString[0] == "Date") continue;
            lines.push(quoteString);
        }

        while (!lines.empty()) {
            String[] s3 = (String[]) lines.pop();
            String date = s3[0]
            println "date = $date"
            double open = Double.parseDouble(s3[1]);
            double high = Double.parseDouble(s3[2]);
            double low = Double.parseDouble(s3[3]);
            double close = Double.parseDouble(s3[4]);
            double vol = Double.parseDouble(s3[5]);
            Date yahoo = DateUtils.toYahoo(date)
            stock.addDaily(yahoo, high, low, open, close, (int) vol, 0);
        }
    }
}
