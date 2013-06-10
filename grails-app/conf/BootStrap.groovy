import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils

class BootStrap {

    def portfolioService

    def init = { servletContext ->
        createData()
    }

    def destroy = {
    }

    private void createData() {

        Portfolio portfolio = new Portfolio("SMA crossing", "desc", 10000)
        portfolio.save(failOnError: true, insert: true, flush: true)

        println "portfolio.id = $portfolio.id"

        Date da = DateUtils.Date("1/1/2007");
        Date a = DateUtils.today();
        Instrument stock = YahooUtils.downloadYahooData("AAPL", "desc", da, a);
        stock.save(failOnError: true, insert: true, flush: true)

        println "stock.id = $stock.id"

        portfolioService.buy(portfolio, stock, 100);

        portfolio.save(failOnError: true, insert: true, flush: true);

    }
}
