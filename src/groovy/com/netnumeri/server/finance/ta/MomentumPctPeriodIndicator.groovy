package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class MomentumPctPeriodIndicator extends Indicator {

    int period;

    public MomentumPctPeriodIndicator(Instrument instrument, String name, Integer period) {
        super(instrument, name);
        this.period = period;
        build();
    }

    public void build() {
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = Momentum.momentumPctPeriod(closes, period);
        copyBackwords(ar);
    }

}


