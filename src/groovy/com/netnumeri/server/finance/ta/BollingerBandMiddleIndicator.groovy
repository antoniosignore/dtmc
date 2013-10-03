package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class BollingerBandMiddleIndicator extends Indicator {

    int lenght;
    double deviation;

    public BollingerBandMiddleIndicator(TimeSeries series, String name, Integer param1, double deviation) {

        super(series, name);
        lenght = param1;
        this.deviation = deviation;
        title = "Bollinger Band Middle";
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

                add(date, calculate(series, date, lenght));
            }
            date = series.getNextDate(date)
        }

    }

    public static double calculate(TimeSeries qh, Date date, int length) {

        int lastBar = qh.matrix.getIndex(date);

        int firstBar = lastBar - length + 1;
        double sum = 0;
        for (int bar = firstBar; bar <= lastBar; bar++) {
            sum += qh.matrix.getValue(bar);
        }

        return sum / length;
    }


    public int getFirstIndicatorIndex() {

        return (int) lenght + 1;

    }

}


