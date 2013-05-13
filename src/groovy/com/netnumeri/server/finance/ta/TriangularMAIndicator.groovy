package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class TriangularMAIndicator extends Indicator {

    int smoothing;

    public TriangularMAIndicator() {
    }

    public TriangularMAIndicator(TimeSeries series, String name, Integer param1) {
        super(series, name);
        smoothing = param1;
        title = "Triangular moving average";
        build();
    }

    public void build() {
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = MovingAverage.triangularMovingAverage(closes, smoothing);
        copyBackwords(ar);
    }

}


