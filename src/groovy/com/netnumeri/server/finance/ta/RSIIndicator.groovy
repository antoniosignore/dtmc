package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.indicator.RSI

public class RSIIndicator extends Indicator {

    int periodLength

    public RSIIndicator() {
    }

    public RSIIndicator(TimeSeries series, String name, Integer param1) {

        super(series, name);
        periodLength = param1;
        title = "RelativeStrengthIndex";
        build();

    }

    public void build() {

        Date date = series.getFirstDate()
        int index = 0;
        while (index < getFirstIndicatorIndex()) {
            date = series.getNextDate(date);
            index++;
        }

        while (date != null) {

            if (!series.isEmpty(date)) {

                add(date, calculate(series, date, periodLength))
            }
            date = series.getNextDate(date)
        }

    }

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


    public int getFirstIndicatorIndex() {

        return (int) periodLength + 1;

    }

}


