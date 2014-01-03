package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.utils.DateUtils

public class SMACrossoverSignal extends Strategy {

    Instrument asset
    private int sma1 = 0;
    private int sma2 = 0;

    private boolean foundABUY = false;

    public SMACrossoverSignal(String name, Stock asset, Date firstDate, Date lastDate) {
        super(name, asset, firstDate, lastDate, 0);
        this.asset = asset
    }

    public void evaluateInstrumentOnDate(Date date, Instrument asset) {

        println "evaluateInstrumentOnDate date = $date"

        Indicator lower = asset.indicators.get("lower");
        println "lower = $lower.name"

        Indicator upper = asset.indicators.get("upper");
        println "upper = $upper.name"

        if (!(DateUtils.isGreater(date, lower.firstDate) && DateUtils.isGreater(date, upper.firstDate)))
            return

        double todayLower = lower.getData(date)
        Date previousDate = lower.getPrevDate(date)
        println "d = $previousDate"

        double yesterdayLower = lower.getData(previousDate)

        double todayUpper = upper.getData(date)
        Date ud = lower.getPrevDate(date)
        double yesterdayUpper = upper.getData(ud)

        if (todayUpper < todayLower && yesterdayUpper > yesterdayLower){
            signals.add(new Signal (date, TradeEnum.BUY, asset, asset.value(date, FinConstants.CLOSE)))
        }
        else if (todayUpper > todayLower && yesterdayUpper < yesterdayLower) {
            signals.add(new Signal (date, TradeEnum.SELL, asset, asset.value(date, FinConstants.CLOSE)))
        }
    }
}


