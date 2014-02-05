package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ssa.*

public class SSAComponentsIndicator extends Indicator {

    public SSAComponentsIndicator(TimeSeries series, String name, Integer window, List<Integer> components) {
        super (series, name)

        println "com.netnumeri.server.finance.ta.SSAComponentsIndicator.SSAComponentsIndicator"

        series.normalize()
        println "series.firstDate = $series.firstDate"
        println "series.lastDate = $series.lastDate"

        double[] arrayOfDoubles = series.convertToArray()
        SSAStudy ssa = new SSAStudy(arrayOfDoubles)
        List<SSAItem> items = ssa.analyze(window)
        double[] group = ssa.reconstructedGroup(items, components)
        copyBackwords group
    }

}


