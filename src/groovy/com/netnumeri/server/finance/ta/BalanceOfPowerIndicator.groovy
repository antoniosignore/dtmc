package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class BalanceOfPowerIndicator extends Indicator {

    public BalanceOfPowerIndicator() {
    }

    public BalanceOfPowerIndicator(Instrument instrument, String name) {
        super(instrument, name);
        title = "Balance Of Power";
        build();
    }

    public void build() {
        double[] highs = instrument.getHighSeries().convertToArray();
        double[] lows = instrument.getLowSeries().convertToArray();
        double[] closes = instrument.getCloseSeries().convertToArray();
        double[] opens = instrument.getOpenSeries().convertToArray();
        double[] ar = MarketStrength.balanceOfPowerOverPeriod(highs, lows, closes, opens);
        copyBackwords(ar);
    }

}


