package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class DStochasticSmoothedIndicator extends Indicator {

    int period;
    int smoothing;

    public DStochasticSmoothedIndicator(Instrument instrument, String name, Integer period, Integer smoothing) {
        super(instrument, name);
        this.period = period;
        this.smoothing = smoothing;
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = Stochastics.kFastStochasticPeriod(highs, lows, closes, period);
        copyBackwords(MovingAverage.simpleMovingAverage(ar, smoothing));
    }

}


