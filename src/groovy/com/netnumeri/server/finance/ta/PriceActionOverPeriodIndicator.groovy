package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class PriceActionOverPeriodIndicator extends Indicator {


    public PriceActionOverPeriodIndicator() {
    }

    public PriceActionOverPeriodIndicator(Instrument instrument, String name) {
        super(instrument, name);
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] opens = instrument.getOpenSeries().convertToArray();
        double[] ad = Filters.priceActionOverPeriod(highs, lows, closes, opens);
        copyBackwords(ad);
    }

}


