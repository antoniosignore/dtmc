package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class ChaikinOverPeriodIndicator extends Indicator {

    int smoothing;

    public ChaikinOverPeriodIndicator() {
    }

    public ChaikinOverPeriodIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        smoothing = param1;
        title = "Chaikin Oscillator over period";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] vols = instrument.getVolumeSeries().convertToArray();
        double smoothing = (int) parm.parameter[0];
        double[] ad = AccumulateDistribute.chaikinOscillatorOverPeriod(highs, lows, vols, smoothing);
        copyBackwords(ad);
    }

}


