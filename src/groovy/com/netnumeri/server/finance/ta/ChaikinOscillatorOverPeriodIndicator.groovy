package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class ChaikinOscillatorOverPeriodIndicator extends Indicator {

    int smoothing;

    public ChaikinOscillatorOverPeriodIndicator() {
    }

    public ChaikinOscillatorOverPeriodIndicator(Instrument instrument, String name, Integer smoothing) {
        super(instrument, name);
        this.smoothing = smoothing;
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


