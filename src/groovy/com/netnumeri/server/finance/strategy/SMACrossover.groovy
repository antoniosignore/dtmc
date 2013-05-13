package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.finpojo.asset.Stock
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

        FinConstants signal = null;
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
            signal = FinConstants.BUY

        if (todayUpper > todayLower && yesterdayUpper < yesterdayLower)
            signal = FinConstants.SELL

        if (signal == FinConstants.SELL) {
            if (foundABUY) {
                System.out.println("SELL on date: " + date.toGMTString());
                Transaction transaction = new Transaction(asset, FinConstants.SELL, amount, asset.getClose(date), date);
                add(transaction);
                foundABUY = false;
            }
        } else if (signal == FinConstants.BUY) {
            System.out.println("BUY on date: " + date.toGMTString());
            Transaction transaction = new Transaction(asset, FinConstants.BUY, amount, asset.getClose(date), date);
            add(transaction);
            foundABUY = true;
        }
    }

}



