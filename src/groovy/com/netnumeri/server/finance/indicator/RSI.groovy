package com.netnumeri.server.finance.indicator;

import com.netnumeri.server.finance.beans.TimeSeries;

/**
 * Relative Strength Index. Implemented up to this specification:
 * http://en.wikipedia.org/wiki/Relative_strength
 */
public class RSI {

    public static double calculate(TimeSeries qh, Date date, int periodLength) {

        int lastBar = qh.matrix.getIndex(date);
        int firstBar = lastBar - periodLength + 1;

        double gains = 0, losses = 0;

        for (int bar = firstBar + 1; bar <= lastBar; bar++) {

            double change = qh.matrix.getValue(bar) - qh.matrix.getValue(bar - 1);
            gains += Math.max(0, change);
            losses += Math.max(0, -change);
        }

        double change = gains + losses;

        double value = (change == 0) ? 50 : (100 * gains / change);

        println "value = $value"

        return value;
    }
}
