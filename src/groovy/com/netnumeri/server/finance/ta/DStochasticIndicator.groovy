package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class DStochasticIndicator extends Indicator {

    int period;
    int method;
    int smoothing;

    public DStochasticIndicator(Instrument instrument, String name, Integer period, Integer method, Integer smoothing) {
        super(instrument, name);
        this.period = period;
        this.method = method;
        this.smoothing = smoothing;
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = Stochastics.dStochastic(highs, lows, closes, period, method, smoothing);
        copyBackwords(ar);
    }

}


