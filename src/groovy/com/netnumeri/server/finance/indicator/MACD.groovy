package com.netnumeri.server.finance.indicator;


import com.netnumeri.server.finance.beans.TimeSeries;

/**
 * MACD
 */
public class MACD {

    public static double calculate(TimeSeries qh, Date date, int fastLength, int slowLength) {
        double fastEMA = new EMA(qh, date, fastLength).calculate();
        double slowEMA = new EMA(qh, date, slowLength).calculate();
        double value = fastEMA - slowEMA;

        return value;
    }
}
