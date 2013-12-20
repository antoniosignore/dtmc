package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.GenericTimeSeries
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.data.TransactionSeries
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.SMACrossover
import com.netnumeri.server.finance.finpojo.Strategy
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.ta.SMAIndicator
import com.netnumeri.server.finance.ta.TradeListEntry
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import com.netnumeri.server.utils.StockUtils
import dtmc.PortfolioService
import org.apache.commons.io.FileUtils
import org.junit.Test

import java.text.ParseException

public class SMACrossoverTest {

    String dir = "/home/antonio/timeplot/"

    @Test
    public void testStrategy() throws IOException, ParseException {

        Portfolio portfolio = new Portfolio("SMA crossing", "Description", 10000);
        Date da = DateUtils.Date("1/1/2007");
        Date a = DateUtils.today();

        Instrument stock = YahooUtils.downloadYahooData("AAPL", "Apple Computers", da, a);

        TimeSeries closes = stock.getSeries(FinConstants.CLOSE);
        FileUtils.writeStringToFile(new File(dir + "stock.txt"), closes.getTimeplotSeries())

        stock.indicators.put("upper", new SMAIndicator(closes, "SMA-" + 50, 50))
        stock.indicators.put("lower", new SMAIndicator(closes, "SMA-" + 10, 10))

        PortfolioService tradeService = new PortfolioService();
        tradeService.add(portfolio, stock);

        Strategy strategy = new SMACrossover("test", portfolio, da, a, 10000);
        strategy.run();

        TransactionSeries series = strategy.transactionSeries

        Backtest trader = new Backtest(strategy.transactionSeries, portfolio, 100000);
        double value = trader.test();

        System.out.println(trader.toXMLString())

        FileUtils.writeStringToFile(new File(dir + "/table.html"), trader.toXMLString())

        TransactionSeries transactions = strategy.transactionSeries;
        GenericTimeSeries<Transaction> array = transactions.transactionArray

        List<TradeListEntry> list = trader.getTradeList();

        StockUtils.printTimeplotIndicatorOnFile(dir, stock)

        TimeSeries pnlSeries = trader.getPnLSeries();
        StockUtils.printTimeSeriesOnFile(pnlSeries, dir)

        TimeSeries wealthSeries = trader.getWealthSeries();
        StockUtils.printTimeSeriesOnFile(wealthSeries, dir)

        StockUtils.printOnFile(dir, list)

        try {
            System.out.println(trader.accountWealth);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeplot() throws IOException, ParseException {

        Date da = DateUtils.Date("1/1/2007");
        Date a = DateUtils.today();

        Instrument stock = YahooUtils.downloadYahooData("AAPL", da, a);
        TimeSeries closes = stock.getCloseSeries();

        String series = closes.getTimeplotSeries();
        println "series = $series"

    }

}
