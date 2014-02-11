package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.ta.NormalizedSeriesIndicator
import com.netnumeri.server.finance.ta.SSAComponentsIndicator
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.utils.StockUtils

import java.text.SimpleDateFormat

public class SSASignal extends Strategy {

    Instrument asset
    TradeEnum lastTrade
    List<Stock> stocksList = new ArrayList<Stock>()

    public SSASignal(String name, Stock asset, Date firstDate, Date lastDate) {
        super(name, asset, firstDate, lastDate, 0);
        this.asset = asset
    }

    public void evaluateInstrumentOnDate(Date date) {

        Date da = DateUtils.dateNYearsAgo(date, 1);
        Date a = date

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd")

        Stock stock = new Stock(asset.name, format.format(date));

        StockUtils.refreshDaily(stock as Stock, da, a);

        TimeSeries closeSeries = stock.buildCloseSeries()
        closeSeries.normalize()

        Indicator normalized = new NormalizedSeriesIndicator(closeSeries, "SSA-01");
        Indicator trend = new SSAComponentsIndicator(closeSeries, "SSA-0", 50, [0])
        Indicator comp1 = new SSAComponentsIndicator(closeSeries, "SSA-12", 50, [1])

        stock.indicators.put("normalized", normalized)
        stock.indicators.put("trend", trend)
        stock.indicators.put("comp1", comp1)

        stock.lastDate()
//
        if (!(DateUtils.isGreater(date, trend.firstDate) && DateUtils.isGreater(date, comp1.firstDate)))
            return

        Date previousDate
        Date prevPreviousDate

        try {
            previousDate = comp1.getPrevDate(date)
            prevPreviousDate = comp1.getPrevDate(previousDate)
        } catch (Throwable th) {
            return
        }

        if (previousDate == null || prevPreviousDate == null)
            return

        Double todaySSA0 = trend.getData(date)
        Double yesterdaySSA0 = trend.getData(previousDate)

        Double todaySSA1 = comp1.getData(date)
        Double yesterdaySSA1 = comp1.getData(previousDate)
        Double twoDaysBeforeSSA1 = comp1.getData(prevPreviousDate)

        boolean isATop = twoDaysBeforeSSA1 <  yesterdaySSA1 && yesterdaySSA1 > todaySSA1
        boolean isALow = twoDaysBeforeSSA1 >  yesterdaySSA1 && yesterdaySSA1 < todaySSA1
        boolean inATrade = (lastTrade == null)
        boolean trendingDown = ((yesterdaySSA0 > todaySSA0))

        if (isATop) signals.add(new Signal(date, TradeEnum.SELL, asset, asset.value(date, FinConstants.CLOSE)))
        if (isALow) signals.add(new Signal(date, TradeEnum.BUY, asset, asset.value(date, FinConstants.CLOSE)))

        stocksList.add(stock)

    }
}



