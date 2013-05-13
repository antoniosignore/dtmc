package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.indicator.RSI2

public class RSI2Indicator extends Indicator {

    int periodLength;

    public RSI2Indicator() {
    }

    public RSI2Indicator(TimeSeries series, String name, Integer param1) {

        super(series, name);
        periodLength = param1;
        title = "RSI";
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

                add(date, RSI2.calculate(series, date, periodLength))
            }
            date = series.getNextDate(date)
        }

    }


    public int getFirstIndicatorIndex() {

        return (int) periodLength + 1;

    }

}


