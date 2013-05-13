package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class MomentumPctPeriodIndicator extends Indicator {

    int period;

    public MomentumPctPeriodIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        period = param1;
        title = "Momentum Pct Period";
        build();
    }

    public void build() {
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = Momentum.momentumPctPeriod(closes, period);
        copyBackwords(ar);
    }

}


