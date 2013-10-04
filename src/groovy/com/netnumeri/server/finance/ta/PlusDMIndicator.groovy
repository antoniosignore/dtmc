package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class PlusDMIndicator extends Indicator {

    public PlusDMIndicator() {
    }

    public PlusDMIndicator(Instrument instrument, String name) {
        super(instrument, name);
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = DirectionalMovementIndicator.plusDirectionalMovementPeriod(highs, lows, closes);
        copyBackwords(ar);
    }

}


