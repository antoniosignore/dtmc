package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.utils.DateUtils

public class SMACrossover extends Strategy {

    private int sma1 = 0;
    private int sma2 = 0;

    private boolean foundABUY = false;

    public SMACrossover(String name,
                        Portfolio portfolio,
                        Date firstDate,
                        Date lastDate,
                        double wealth) {
        super(name, portfolio, firstDate, lastDate, wealth);
    }

    public void evaluateInstrumentOnDate(Date date, Instrument asset) {
        int amount = 1000;

        TradeEnum signal = null;
        Indicator lower = asset.indicators.get("lower");
        Indicator upper = asset.indicators.get("upper");

        if (!DateUtils.isGreater(date, lower.firstDate) ||
                !DateUtils.isGreater(date, upper.firstDate))
            return

        double todayLower = lower.getData(date)
        Date d = lower.getPrevDate(date)
        double yesterdayLower = lower.getData(d)

        double todayUpper = upper.getData(date)
        Date ud = lower.getPrevDate(date)
        double yesterdayUpper = upper.getData(ud)

        if (todayUpper < todayLower && yesterdayUpper > yesterdayLower)
            signal = TradeEnum.BUY

        if (todayUpper > todayLower && yesterdayUpper < yesterdayLower)
            signal = TradeEnum.SELL

        if (signal == TradeEnum.SELL) {
            if (foundABUY) {
                System.out.println("SELL on date: " + date.toGMTString());
                Transaction transaction = new Transaction(asset, TradeEnum.SELL, amount, asset.getClose(date), date);
                add(transaction);
                foundABUY = false;
            }
        } else if (signal == TradeEnum.BUY) {
            System.out.println("BUY on date: " + date.toGMTString());
            Transaction transaction = new Transaction(asset, TradeEnum.BUY, amount, asset.getClose(date), date);
            add(transaction);
            foundABUY = true;
        }
    }

}



