package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class ChaikinVolatilityIndicator extends Indicator {

    int period

    public ChaikinVolatilityIndicator() {
    }

    public ChaikinVolatilityIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        period = param1;
        title = "Chaikin Oscillator over period";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] ad = Volatility.chaikinVolatility(highs, lows, (int) parm.parameter[0], period);
        copyBackwords(ad);
    }

}


