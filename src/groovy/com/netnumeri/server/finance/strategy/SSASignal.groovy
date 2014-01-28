package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.utils.DateUtils

public class SSASignal extends Strategy {

    Instrument asset

    TradeEnum lastTrade

    public SSASignal(String name, Stock asset, Date firstDate, Date lastDate) {
        super(name, asset, firstDate, lastDate, 0);
        this.asset = asset
    }

    public void evaluateInstrumentOnDate(Date date, Instrument asset) {

        Indicator trend = asset.indicators.get("trend");
        Indicator comp1 = asset.indicators.get("comp1");

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

        if ((twoDaysBeforeSSA1 <  yesterdaySSA1 && yesterdaySSA1 > todaySSA1) && (yesterdaySSA0 > todaySSA0)){
            signals.add(new Signal (date, TradeEnum.SELL, asset, asset.value(date, FinConstants.CLOSE)))
            lastTrade = TradeEnum.SELL
        }

        if ((twoDaysBeforeSSA1 >  yesterdaySSA1 && yesterdaySSA1 < todaySSA1) && (yesterdaySSA0 < todaySSA0)){
            signals.add(new Signal (date, TradeEnum.BUY, asset, asset.value(date, FinConstants.CLOSE)))
            lastTrade = TradeEnum.BUY
        }
    }
}


