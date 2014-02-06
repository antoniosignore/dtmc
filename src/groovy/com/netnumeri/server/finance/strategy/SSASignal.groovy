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

public class SSASignal extends Strategy {

    Instrument asset
    TradeEnum lastTrade

    public SSASignal(String name, Stock asset, Date firstDate, Date lastDate) {
        super(name, asset, firstDate, lastDate, 0);
        this.asset = asset
    }

    public void evaluateInstrumentOnDate(Date date) {

        Date da = DateUtils.dateNYearsAgo(date, 1);
        Date a = date

        Stock stock = new Stock(asset.name, "clone");

        StockUtils.refreshDaily(stock as Stock, da, a);

        TimeSeries closeSeries = stock.buildCloseSeries()
        closeSeries.normalize()

//        stockInstance.indicators.put("normalized", new NormalizedSeriesIndicator(closeSeries, "Normalized"))

        List<Integer> components = [0]
        Indicator trend = new SSAComponentsIndicator(closeSeries, "SSA-0", 50, components)

        List<Integer> components1 = [1]
        Indicator comp1 = new SSAComponentsIndicator(closeSeries, "SSA-12", 50, components1)

//        Indicator trend = asset.indicators.get("trend");
//        Indicator comp1 = asset.indicators.get("comp1");
//        Indicator comp1 = asset.indicators.get("comp12");
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

        if (isATop){
//            if (!inATrade)  {
//                if (trendingDown){
                    signals.add(new Signal (date, TradeEnum.SELL, asset, asset.value(date, FinConstants.CLOSE)))
//                    lastTrade = TradeEnum.SELL
//                }
//            }
//            else {
//                if (lastTrade == TradeEnum.BUY){
//                    signals.add(new Signal (date, TradeEnum.SELL, asset, asset.value(date, FinConstants.CLOSE)))
//                    lastTrade = null
//                }
//            }
        }
        else

        if (isALow){
//            if (!inATrade)  {
//                if (!trendingDown){
                    signals.add(new Signal (date, TradeEnum.BUY, asset, asset.value(date, FinConstants.CLOSE)))
//                    lastTrade = TradeEnum.BUY
//                }
//            }
//            else {
//                if (lastTrade == TradeEnum.SELL){
//                    signals.add(new Signal (date, TradeEnum.BUY, asset, asset.value(date, FinConstants.CLOSE)))
//                    lastTrade = null
//                }
//            }
        }

         /*
            boolean takeit = false
            if (lastTrade == null)
                takeit = true

            else if (lastTrade == TradeEnum.BUY) {
                signals.add(new Signal (date, TradeEnum.SELL, asset, asset.value(date, FinConstants.CLOSE)))
                lastTrade = null
                return
            }

            else if (yesterdaySSA0 > todaySSA0)
                takeit = true

            if (takeit){
                signals.add(new Signal (date, TradeEnum.SELL, asset, asset.value(date, FinConstants.CLOSE)))
                lastTrade = TradeEnum.SELL
            }

        }
        if (isALow){
            boolean takeit = false

            if (lastTrade == null)
                takeit = true

            else if (lastTrade == TradeEnum.SELL)  {
                signals.add(new Signal (date, TradeEnum.BUY, asset, asset.value(date, FinConstants.CLOSE)))
                lastTrade = null
                return
            }

            else if (yesterdaySSA0 > todaySSA0)
                takeit = true

            if (takeit){
                signals.add(new Signal (date, TradeEnum.BUY, asset, asset.value(date, FinConstants.CLOSE)))
                lastTrade = TradeEnum.BUY
            }
        }
           */
    }
}



