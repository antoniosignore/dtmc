package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class WMAIndicator extends Indicator {

    int smoothing;

    public WMAIndicator(TimeSeries series, String name, Integer param1) {
        super(series, name);
        smoothing = param1;
        build();
    }

    public void build() {
        double[] ad = MovingAverage.weightedXDayMovingAverage(series.convertToArray(), smoothing);
        copyBackwords(ad);
    }
}


