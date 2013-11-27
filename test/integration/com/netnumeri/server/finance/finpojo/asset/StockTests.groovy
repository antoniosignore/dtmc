package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.ta.*
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import com.netnumeri.server.utils.StockUtils
import org.junit.After
import org.junit.Before
import org.junit.Test

class StockTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {

        Date da = DateUtils.Date("11/1/2012");
        Date a = DateUtils.today();

        Instrument stockInstance = YahooUtils.downloadYahooData("AAPL", da, a);

        // last year
        StockUtils.refreshDaily(stockInstance);

        TimeSeries closes = stockInstance.getSeries(FinConstants.CLOSE);

        new SMAIndicator(closes, "Simple Moving Average", ui.smoothing1);
        new WMAIndicator(closes, "Weighted Moving Average", ui.smoothing1);

        List<Integer> components = [0]
        new SSAComponentsIndicator(closes, "SSA-0", ui.period, components);

        components = [1]
        new SSAComponentsIndicator(closes, "SSA-1", ui.period, components);

        components = [0, 1]
        new SSAComponentsIndicator(closes, "SSA-01", ui.period, components);
        new PriceChannelUpIndicator(closes, "PC-Upper", ui.indicatorOrder, ui.k);
        new PriceChannelLowerIndicator(closes, "PC-Lower", ui.indicatorOrder, ui.k);
        new BollingerBandUpIndicator(closes, "BB-Upper", ui.length, ui.deviation);
        new BollingerBandLowerIndicator(closes, "BB-Lower", ui.length, ui.deviation);
        new SimpleMovingVarianceIndicator(closes, "Simple Moving variance", ui.indicatorOrder);
        new MomentumPctPeriodIndicator(stockInstance, "Momentum", ui.period)
        new MACDIndicator(closes, "MACD", ui.smoothing1, ui.smoothing2)
        new MACDSignal(closes, "MACD-Signal", ui.smoothing1, ui.smoothing2, ui.smoothing3)
        new RateOfChangeOverPeriodIndicator(stockInstance, "MACD", ui.period)
        new RSIIndicator(closes, "RSI", ui.period)
        new RSI2Indicator(closes, "RSI", ui.period)
        new CommodityChannelIndicator(closes, "CCI", ui.indicatorOrder)
        new PriceChannelUpIndicator(closes, "PCI", ui.indicatorOrder, ui.k)
        new PriceChannelLowerIndicator(closes, "PCI", ui.indicatorOrder, ui.k)
        new TrueRangeOverPeriodIndicator(stockInstance, "TrueRange")
        new MoneyFlowOverPeriodIndicator(stockInstance, "Money flow", ui.period)
        new AccumulateDistributionOverPeriodIndicator(stockInstance, "Accumulate Distribution", ui.smoothing1)
        new ChaikinOscillatorOverPeriodIndicator(stockInstance, "ChaikinOscillatorOverPeriodIndicator", ui.smoothing1)
        new ChaikinMoneyFlowOverPeriodIndicator(stockInstance, "ChaikinMoneyFlowOverPeriodIndicator", ui.smoothing1)
        new AaronOscillatorOverPeriodIndicator(stockInstance, "AaronOscillatorOverPeriodIndicator", ui.period)
        new AaronDownOverPeriodIndicator(stockInstance, "Aaron Down Over Period Indicator", ui.period)
        new AaronUpOverPeriodIndicator(stockInstance, "Aaron Up Over Period Indicator", ui.period)
        new TrueRangeOverPeriodIndicator(stockInstance, "true Range Over Period Indicator")
        new KFastStochasticIndicator(stockInstance, "K Fast Stochastic Indicator", ui.period)
        new DStochasticIndicator(stockInstance, "D Stochastic Indicator", ui.period, ui.method, ui.smoothing1)
        new DStochasticSmoothedIndicator(stockInstance, "D Stochastic Smoothed Indicator", ui.period, ui.smoothing1)
        new ChaikinVolatilityIndicator(stockInstance, "Chaikin Volatility Indicator", ui.smoothing1, ui.period)
        new MACDSignal(closes, "MACD Signal Indicator", ui.smoothing1, ui.smoothing2, ui.smoothing3)
        new PlusDMIndicator(stockInstance, "Plus DMI Indicator")
        new RateOfChangeOverPeriodIndicator(stockInstance, "Rate of Change Indicator", ui.period)
        new KairiIndicator(stockInstance, "Kairi Indicator", ui.smoothing1)
        new MomentumPctPeriodIndicator(stockInstance, "Momentum Pct Period Indicator", ui.period)
        new CommodityChannelIndicator(closes, "CCI", ui.indicatorOrder)
        new MarketFacilitationIndexIndicator(stockInstance, "Market Facilitation Index Indicator")
        new BalanceOfPowerIndicator(stockInstance, "Balance of Power Indicator")
        new PriceActionOverPeriodIndicator(stockInstance, "Price action over period indicator")

    }
}
