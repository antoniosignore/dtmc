package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class MinusDMIndicator extends Indicator {

    public MinusDMIndicator() {
    }

    public MinusDMIndicator(Instrument instrument, String name) {
        super(instrument, name);
        title = "Minus DMI";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = DirectionalMovementIndicator.minusDirectionalMovementPeriod(highs, lows, closes);
        copyBackwords(ar);
    }

}


