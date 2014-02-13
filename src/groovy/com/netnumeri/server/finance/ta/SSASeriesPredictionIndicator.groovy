package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ssa.SSAMath

public class SSASeriesPredictionIndicator extends Indicator {

    List<Integer> components

    public SSASeriesPredictionIndicator(TimeSeries timeseries,
                                        String name,
                                        int window,
                                        int order,
                                        List<Integer> components,
                                        Integer noFuture) {
        super(timeseries, name)
        series.normalize()

        double[] prediction = new double[noFuture];
        double[] seriesAsArray = series.convertToArray();
        double[] augmented = SSAMath.getAugmentedByMovingAverage(seriesAsArray, order)

        assert (seriesAsArray.length + 1) == augmented.length

        for (int i = 0; i < noFuture; i++) {
            double[] componentsForecastValues = new double[components.size()]

        }

//        double[] prediction = computeSeriesForecast(series, noFuture, window, order, components)
//
//        println "\n\n\n\n\n*********************prediction = $prediction"

        copyInTheFuture(prediction)
    }

}


