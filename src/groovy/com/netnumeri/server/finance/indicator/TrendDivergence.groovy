package com.netnumeri.server.finance.indicator;


import com.netnumeri.server.finance.beans.TimeSeries;

/**
 * Trend divergence indicator
 */
public class TrendDivergence {

    public static double calculate(TimeSeries qh, Date date, int shorterPeriod, int longerPeriod) {
        double shorterRSI = new RSI(qh, shorterPeriod).calculate();
        double longerRSI = new RSI(qh, longerPeriod).calculate();
        double value = shorterRSI - longerRSI;
        return value;
    }
}
