package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.finpojo.Instrument

public class AccumulateDistributionOverPeriodIndicator extends Indicator {

    int smoothing;

    public AccumulateDistributionOverPeriodIndicator() {
    }

    public AccumulateDistributionOverPeriodIndicator(Instrument instrument, String name, Integer smoothing) {
        super(instrument, name);
        this.smoothing = smoothing;
        build();
    }

    public void build() {
        double[] ad = AccumulateDistribute.accumulateDistributionOverPeriod(
                instrument.getHighSeries().convertToArray(),
                instrument.getLowSeries().convertToArray(),
                instrument.getVolumeSeries().convertToArray(), (int) parm.parameter[0]);
        copyBackwords(ad);
    }

}


