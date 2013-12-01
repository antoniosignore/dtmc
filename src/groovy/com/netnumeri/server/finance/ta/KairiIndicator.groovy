package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries

public class KairiIndicator extends Indicator {

    int smoothing;

    public KairiIndicator(TimeSeries closeSeries, String name, Integer smoothing) {
        super(instrument, name);
        this.smoothing = smoothing;
        double[] closes = closeSeries.convertToArray();
        double[] ar = MovingAverage.kairi(closes, (int) smoothing);
        copyBackwords(ar);
    }

}


