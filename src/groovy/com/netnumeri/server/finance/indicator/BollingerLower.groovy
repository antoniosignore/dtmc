package com.netnumeri.server.finance.indicator

import com.netnumeri.server.finance.beans.TimeSeries;

/**
 * Lower Bollinger Band
 */
public class BollingerLower {
//	private final int length;
//	private final double deviations;
//	private int sizeLimitHistory = 0;
//

    public static double calculate(TimeSeries qh, Date date, int length, double deviations) {

        int lastBar = qh.matrix.getIndex(date);

        double squareSum = 0;
        double sum = 0;

        int firstBar = lastBar - length + 1;

        //qh.matrix.getValue(bar)
        for (int bar = firstBar; bar <= lastBar; bar++) {
            double barClose = qh.matrix.getValue(bar);
            sum += barClose;
            squareSum += barClose * barClose;
        }

        double stDev = length * squareSum - sum * sum;
        stDev /= length * (length - 1);
        stDev = Math.sqrt(stDev);

        return sum / length - deviations * stDev;
    }
}
