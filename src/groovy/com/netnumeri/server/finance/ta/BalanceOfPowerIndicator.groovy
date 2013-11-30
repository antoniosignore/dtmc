package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class BalanceOfPowerIndicator extends Indicator {

    public BalanceOfPowerIndicator() {
    }

    public BalanceOfPowerIndicator(Instrument instrument, String name) {
        super(instrument, name);
        build();
    }

    public void build() {
        double[] highs = instrument.highSeries().convertToArray();
        double[] lows = instrument.lowSeries().convertToArray();
        double[] closes = instrument.closeSeries().convertToArray();
        double[] opens = instrument.getOpenSeries().convertToArray();
        double[] ar = MarketStrength.balanceOfPowerOverPeriod(highs, lows, closes, opens);
        copyBackwords(ar);
    }

}


