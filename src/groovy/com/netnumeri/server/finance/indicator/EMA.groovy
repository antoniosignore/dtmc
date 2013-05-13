package com.netnumeri.server.finance.indicator

import com.netnumeri.server.finance.beans.TimeSeries;

public class EMA {

    public static double calculate(TimeSeries qh, Date date, int length) {
        double multiplier = 2 / (length + 1);
        int lastBar = qh.matrix.getIndex(date);
        int firstBar = lastBar - 2 * length + 1;
        double ema = qh.matrix.getValue(firstBar)
        for (int bar = firstBar; bar <= lastBar; bar++) {
            double barClose = qh.matrix.getValue(bar)
            ema += (barClose - ema) * multiplier;
        }
        return ema;
    }
}
