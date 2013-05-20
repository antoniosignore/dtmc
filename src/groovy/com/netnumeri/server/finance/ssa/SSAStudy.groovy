package com.netnumeri.server.finance.ssa

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.ta.SSAComponentsIndicator
import com.netnumeri.server.finance.ta.SSASeriesPredictionIndicator


class SSAStudy {


    public static study(Stock stock, int window) {

        println "pippo 98"

        TimeSeries closes = stock.getSeries(FinConstants.CLOSE);

        println "pippo 99"

        SSAAnalysis analysis = new SSAAnalysis(closes.convertToList(), window)

        println "pippo 100"

        def components = [0]
        Indicator ssa0 = new SSAComponentsIndicator(closes, "SSA-0", analysis, components);
        stock.indicators.put(ssa0.name, ssa0)

        components = [1]
        Indicator ssa1 = new SSAComponentsIndicator(closes, "SSA-1", analysis, components);
        stock.indicators.put(ssa1.name, ssa1)


        components = [0, 1]
        Indicator ssa01 = new SSAComponentsIndicator(closes, "SSA-01", analysis, components);
        stock.indicators.put(ssa01.name, ssa01)

        components = [2]
        Indicator ssa2 = new SSAComponentsIndicator(closes, "SSA-2", analysis, components);
        stock.indicators.put(ssa2.name, ssa2)

        components = [3]
        Indicator ssa3 = new SSAComponentsIndicator(closes, "SSA-3", analysis, components);
        stock.indicators.put(ssa3.name, ssa3)

        components = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
        Indicator ssa05 = new SSAComponentsIndicator(closes, "SSA-05", analysis, components);
        stock.indicators.put(ssa05.name, ssa05)

        Indicator ssa1Predict = new SSASeriesPredictionIndicator(closes, "SSA-0-predict", window, 3, components, 16);
        stock.indicators.put(ssa1Predict.name, ssa1Predict)

    }
}
