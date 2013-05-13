package com.netnumeri.server.finance.indicator

import com.netnumeri.server.finance.beans.TimeSeries;

public class BollingerUpper {


    public static double calculate(TimeSeries qh, Date date, int length, double deviations) {
        int lastBar = qh.matrix.getIndex(date);

//		if (sizeLimitHistory != 0) {
//			lastBar = sizeLimitHistory - 1;
//		}

        int firstBar = lastBar - length + 1;
        double squareSum = 0;
        double sum = 0;

        for (int bar = firstBar; bar <= lastBar; bar++) {
            double barClose = qh.matrix.getValue(bar);
            sum += barClose;
            squareSum += barClose * barClose;
        }

        double stDev = length * squareSum - sum * sum;
        stDev /= length * (length - 1);
        stDev = Math.sqrt(stDev);

        return sum / length + deviations * stDev;
    }

}
