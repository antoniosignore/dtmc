package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class DStochasticIndicator extends Indicator {

    int period;
    int method;
    int smoothing;

    public DStochasticIndicator(Instrument instrument, String name, Integer param1, Integer method, Integer smoothing) {
        super(instrument, name);
        period = param1;
        method = method;
        smoothing = smoothing;
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


