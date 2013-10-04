package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class CCIOverPeriodIndicator extends Indicator {

    int period;

    public CCIOverPeriodIndicator() {
    }

    public CCIOverPeriodIndicator(Instrument instrument, String name, Integer param1) {
        super(instrument, name);
        period = param1;
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] volumes = instrument.getVolumeSeries().convertToArray();
        double[] ar = MeanReversion.commodityChannelIndexOverPeriod(highs, lows, volumes, period);
        copyBackwords(ar);
    }

}


