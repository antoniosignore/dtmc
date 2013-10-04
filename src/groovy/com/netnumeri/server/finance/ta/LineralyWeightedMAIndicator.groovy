package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class LineralyWeightedMAIndicator extends Indicator {

    int smoothing;

    public LineralyWeightedMAIndicator() {
    }

    public LineralyWeightedMAIndicator(TimeSeries series, String name, Integer param1) {
        super(series, name);
        smoothing = param1;
        build();
    }

    public void build() {
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = MovingAverage.linearlyWeightedMovingAverage(closes, smoothing);
        copyBackwords(ar);
    }

}


