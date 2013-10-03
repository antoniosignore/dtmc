package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class TrueRangeOverPeriodIndicator extends Indicator {

    public TrueRangeOverPeriodIndicator() {
    }

    public TrueRangeOverPeriodIndicator(Instrument instrument, String name) {
        super(instrument, name);
        title = "True Range";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] ar = DirectionalMovementIndicator.trueRangePeriod(highs, lows, closes);
        copyBackwords(ar);
    }

}


