package com.netnumeri.server.finance.indicator

import com.netnumeri.server.finance.beans.TimeSeries;

public class BollingerMiddle {

    public static double calculate(TimeSeries qh, Date date, int length) {

        int lastBar = qh.matrix.getIndex(date);

        int firstBar = lastBar - length + 1;
        double sum = 0;
        for (int bar = firstBar; bar <= lastBar; bar++) {
            sum += qh.matrix.getValue(bar);
        }

        return sum / length;
    }

}
