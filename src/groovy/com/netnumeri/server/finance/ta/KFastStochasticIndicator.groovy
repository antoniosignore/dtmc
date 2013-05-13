package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class KFastStochasticIndicator extends Indicator {

    int period;

    public KFastStochasticIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        period = param1;
        title = "k Fast Stochastic";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = Stochastics.kFastStochasticPeriod(highs, lows, closes, period);
        copyBackwords(ar);
    }

}


