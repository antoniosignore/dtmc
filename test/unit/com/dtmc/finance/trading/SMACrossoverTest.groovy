package com.dtmc.finance.trading

import com.dtmc.finance.finpojo.Instrument
import com.dtmc.trading.Signal
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.strategy.SMACrossoverSignal
import com.netnumeri.server.finance.strategy.Strategy
import com.netnumeri.server.finance.ta.SMAIndicator
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import org.junit.Test

import java.text.ParseException

public class SMACrossoverTest {

    String dir = "/home/antonio/timeplot/"

    @Test
    public void testStrategy() throws IOException, ParseException {

//        Portfolio portfolio = new Portfolio("SMA crossing", "Description", 10000);
        Date da = DateUtils.Date("1/1/2007");
        Date a = DateUtils.today();

        Instrument stock = YahooUtils.downloadYahooData("AAPL", "Apple Computers", da, a, false);

        TimeSeries closes = stock.series(FinConstants.CLOSE);
        //FileUtils.writeStringToFile(new File(dir + "stock.txt"), closes.getTimeplotSeries())

        stock.indicators.put("upper", new SMAIndicator(closes, "SMA-" + 50, 50))
        stock.indicators.put("lower", new SMAIndicator(closes, "SMA-" + 10, 10))
        Strategy strategy = new SMACrossoverSignal("test", stock, da, a);
        strategy.run();
        def signals = strategy.signals

        for (int li = 0; li < signals.size(); li++) {
            Signal signal =  signals.get(li)

            println "signal = $signal"
        }

//        TransactionSeries series = strategy.transactionSeries

//        BackTest trader = new BackTest(strategy, 100000);
//        double value = strategy.tester.test();
//
//        System.out.println(trader.toXMLString())
//
//        FileUtils.writeStringToFile(new File(dir + "/table.html"), trader.toXMLString())
//
////        TransactionSeries transactions = strategy.transactionSeries;
//        GenericTimeSeries<Transaction> array = strategy.portfolio.transactions
//
//        List<TradeListEntry> list = trader.getTradeList();
//
//        StockUtils.printTimeplotIndicatorOnFile(dir, stock)
//
//        TimeSeries pnlSeries = trader.getPnLSeries();
//        StockUtils.printTimeSeriesOnFile(pnlSeries, dir)
//
//        TimeSeries wealthSeries = trader.getWealthSeries();
//        StockUtils.printTimeSeriesOnFile(wealthSeries, dir)
//
//        StockUtils.printOnFile(dir, list)

//        try {
//            System.out.println(trader.accountWealth);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }


    }


}