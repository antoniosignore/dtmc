package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class SMAIndicator extends Indicator {

    int smoothing;

    public SMAIndicator(TimeSeries series, String name, Integer smoothing) {
        super(series, name);
        this.smoothing = smoothing;
        title = "Simple moving average";
        build();
    }

    public void build() {
        double[] ad = MovingAverage.simpleMovingAverage(series.convertToArray(), smoothing);
        copyBackwords(ad);
    }
}


