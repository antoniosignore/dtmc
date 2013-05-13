package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class ChaikinMoneyFlowOverPeriodIndicator extends Indicator {

    int period;

    public ChaikinMoneyFlowOverPeriodIndicator() {
    }

    public ChaikinMoneyFlowOverPeriodIndicator(Instrument instrument, String name, Integer period) {
        super(instrument, name);
        this.period = period;
        title = "Chaikin Money Flow over period";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] vols = instrument.getVolumeSeries().convertToArray();
        double[] ad = AccumulateDistribute.chaikinMoneyFlowOverPeriod(highs, lows, closes, vols, period);
        copyBackwords(ad);
    }

}


