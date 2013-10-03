package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class BollingerBandLowerIndicator extends Indicator {

    int lenght;
    double deviation;

    public BollingerBandLowerIndicator() {
    }

    public BollingerBandLowerIndicator(TimeSeries series, String name, Integer param1, double deviation) {

        super(series, name);
        lenght = param1;
        this.deviation = deviation;
        title = "Bollinger Band Lower";
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

                add(date, calculate(series, date, lenght, deviation));
            }
            date = series.getNextDate(date)
        }

    }

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

    public int getFirstIndicatorIndex() {

        return (int) lenght + 1;

    }

}


