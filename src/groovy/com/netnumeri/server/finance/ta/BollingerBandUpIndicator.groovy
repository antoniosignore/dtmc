package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.indicator.BollingerLower
import com.netnumeri.server.finance.indicator.BollingerUpper

public class BollingerBandUpIndicator extends Indicator {

    int lenght;
    double deviation;

    public BollingerBandUpIndicator() {
    }

    public BollingerBandUpIndicator(TimeSeries series, String name, Integer param1, double deviation) {

        super(series, name);
        lenght = param1;
        this.deviation = deviation;
        title = "Bollinger Band Upper";
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

                add(date, BollingerUpper.calculate(series, date, lenght, deviation));
            }
            date = series.getNextDate(date)
        }

    }


    public int getFirstIndicatorIndex() {

        return (int) lenght + 1;

    }

}


