package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class MoneyFlowOverPeriodIndicator extends Indicator {

    int period

    public MoneyFlowOverPeriodIndicator(Instrument instrument, String name, Integer period) {
        super(instrument, name);
        this.instrument = instrument;
        this.period = period;
        title = "Money Flow over period";
        build();
    }

    public void build() {
        double[] ad = Oscillators.moneyFlowIndexOverPeriod(
                instrument.getHighSeries().convertToArray(),
                instrument.getLowSeries().convertToArray(),
                instrument.getCloseSeries().convertToArray(),
                instrument.getVolumeSeries().convertToArray(),
                (int) period);
        copyBackwords(ad);
    }

}


