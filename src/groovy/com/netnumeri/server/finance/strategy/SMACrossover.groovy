package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.utils.DateUtils

public class SMACrossover extends Strategy {

    Instrument asset
    private int sma1 = 0;
    private int sma2 = 0;

    private boolean foundABUY = false;

    public SMACrossover(String name,
                        Stock asset,
                        Date firstDate,
                        Date lastDate,
                        double wealth) {
        super(name, asset, firstDate, lastDate, wealth);
        this.asset = asset
    }

    public void evaluateInstrumentOnDate(Date date) {

        println "evaluateInstrumentOnDate date = $date"

        int amount = 1000;

        TradeEnum signal = null;

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

        if (todayUpper < todayLower && yesterdayUpper > yesterdayLower)
            signal = TradeEnum.BUY

        if (todayUpper > todayLower && yesterdayUpper < yesterdayLower)
            signal = TradeEnum.SELL

        if (signal == TradeEnum.SELL) {
            if (foundABUY) {
                System.out.println("SELL on transactionDate: " + date.toGMTString());
                Transaction transaction = new Transaction(asset, TradeEnum.SELL, amount, asset.close(date), date);
                add(transaction);
                foundABUY = false;
            }
        } else if (signal == TradeEnum.BUY) {
            System.out.println("BUY on transactionDate: " + date.toGMTString());
            Transaction transaction = new Transaction(asset, TradeEnum.BUY, amount, asset.close(date), date);
            add(transaction);
            foundABUY = true;
        }
    }

}



