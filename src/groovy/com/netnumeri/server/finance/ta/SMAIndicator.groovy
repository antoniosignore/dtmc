package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class SMAIndicator extends Indicator {

    int smoothing;

    public SMAIndicator() {
    }

    public SMAIndicator(TimeSeries series, String name, Integer param1) {
        super(series, name);
        smoothing = param1;
        title = "Simple moving average";
        build();
    }

    public void build() {
        double[] ad = MovingAverage.simpleMovingAverage(series.convertToArray(), smoothing);
        copyBackwords(ad);
    }

}


