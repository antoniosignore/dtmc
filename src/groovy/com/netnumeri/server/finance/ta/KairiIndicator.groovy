package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class KairiIndicator extends Indicator {

    int smoothing;

    public KairiIndicator() {
    }

    public KairiIndicator(Instrument instrument, String name, Integer smoothing) {
        super(instrument, name);
        this.smoothing = smoothing;
        build();
    }

    public void build() {
        double[] closes = instrument.closeSeries().convertToArray();
        double[] ar = MovingAverage.kairi(closes, (int) smoothing);
        copyBackwords(ar);
    }

}


