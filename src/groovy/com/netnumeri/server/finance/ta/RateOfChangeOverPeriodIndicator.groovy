package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class RateOfChangeOverPeriodIndicator extends Indicator {

    int period

    public RateOfChangeOverPeriodIndicator() {
    }

    public RateOfChangeOverPeriodIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        period = param1;
        title = "Rate of Change";
        build();
    }

    public void build() {
        double[] ad = Oscillators.rateOfChangePeriod(
                instrument.getCloseSeries().convertToArray(),
                (int) period);
        copyBackwords(ad);
    }

}


