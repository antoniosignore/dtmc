package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class AaronOscillatorOverPeriodIndicator extends Indicator {

    int period;

    public AaronOscillatorOverPeriodIndicator() {
    }

    public AaronOscillatorOverPeriodIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        period = param1;
        title = "Chaikin Oscillator over period";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] ad = Aroon.aroonOscillatorOverPeriod(highs, lows, period);
        copyBackwords(ad);
    }

}


