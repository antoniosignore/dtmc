package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ssa.SSAMath

public class SSASeriesPredictionIndicator extends Indicator {

    List<Integer> components

    public SSASeriesPredictionIndicator(TimeSeries series,
                                        String name,
                                        int window,
                                        int order,
                                        List<Integer> components,
                                        Integer noFuture) {
        setName(name);
        this.series = series;
        title = "Singular Spectrum Forecast"
        double[] prediction = SSAMath.computeSeriesForecast(series, noFuture, window, order, components)
        copyInTheFuture(prediction)
    }

}


