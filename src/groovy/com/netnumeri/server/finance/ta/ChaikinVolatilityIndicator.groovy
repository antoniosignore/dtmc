package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class ChaikinVolatilityIndicator extends Indicator {

    int period
    int smoothing

    public ChaikinVolatilityIndicator() {
    }

    public ChaikinVolatilityIndicator(Instrument instrument, String name, Integer smoothing, Integer period) {
        super(instrument, name);
        this.smoothing = smoothing;
        this.period = period;
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] ad = Volatility.chaikinVolatility(highs, lows, (int) smoothing, period);
        copyBackwords(ad);
    }

}


