package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class AaronDownOverPeriodIndicator extends Indicator {

    int period;

    public AaronDownOverPeriodIndicator() {
    }

    public AaronDownOverPeriodIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        period = param1;
        title = "Aaron down over period";
        build();
    }

    public void build() {
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] ad = Aroon.aroonDownOverPeriod(lows, period);
        copyBackwords(ad);
    }

}


