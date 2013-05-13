package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class MACDIndicator extends Indicator {

    int smoothing;
    int smoothing2;

    public MACDIndicator() {
    }

    public MACDIndicator(TimeSeries series, String name, Integer param1, Integer param2) {
        super(series, name);
        smoothing = param1;
        smoothing2 = param2;
        title = "MACD";
        build();
    }

    public void build() {
        double[] closes = series.convertToArray();
        double[] ma12 = MovingAverage.simpleMovingAverage(closes, smoothing);
        double[] ma26 = MovingAverage.simpleMovingAverage(closes, smoothing2);

        double[] macdSignal = new double[ma26.length];
        for (int i = 0; i < ma26.length; i++) {
            macdSignal[i] = ma12[ma26.length - 1 - i] - ma26[ma26.length - 1 - i];
        }

        copyBackwords(macdSignal);
    }

}


