package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class EWMAIndicator extends Indicator {

    int smoothing;
    int i;

    public EWMAIndicator() {
    }

    public EWMAIndicator(TimeSeries series, String name, Integer param1, Integer i) {
        super(series, name);
        smoothing = param1;
        this.i = i;
        title = "Exponential weighted moving average";
        build();
    }

    public void build() {
        double[] ad = MovingAverage.exponentiallyWeightedMovingAverage(series.convertToArray(),
                (int) smoothing, i);
        copyBackwords(ad);
    }

}


