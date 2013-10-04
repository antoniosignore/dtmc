package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class MarketFacilitationIndexIndicator extends Indicator {

    public MarketFacilitationIndexIndicator() {
    }

    public MarketFacilitationIndexIndicator(Instrument instrument, String name) {
        super(instrument, name);
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] volumes = instrument.getVolumeSeries().convertToArray();
        double[] ar = MarketStrength.marketFacilitationIndexOverPeriod(highs, lows, volumes);
        copyBackwords(ar);
    }

}


