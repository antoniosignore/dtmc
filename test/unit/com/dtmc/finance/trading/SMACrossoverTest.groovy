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

    @Test
    public void testStrategy() throws IOException, ParseException {

        Date da = DateUtils.Date("1/1/2007");
        Date a = DateUtils.today();


        Instrument stock = YahooUtils.downloadYahooData("AAPL", "Apple Computers", da, a, false);

        dailyService.dailyFromYahoo (instance)


        TimeSeries closes = stock.series(FinConstants.CLOSE);
        stock.indicators.put("upper", new SMAIndicator(closes, "SMA-" + 50, 50))
        stock.indicators.put("lower", new SMAIndicator(closes, "SMA-" + 10, 10))
        Strategy strategy = new SMACrossoverSignal("test", stock, da, a);
        strategy.execute();
        def signals = strategy.signals

        for (int li = 0; li < signals.size(); li++) {
            Signal signal =  signals.get(li)
            println "signal = $signal"
        }
    }
}
