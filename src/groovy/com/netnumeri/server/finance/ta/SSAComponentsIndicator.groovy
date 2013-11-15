package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ssa.SSAAnalysis

public class SSAComponentsIndicator extends Indicator {

    public SSAComponentsIndicator(TimeSeries series, String name, Integer window, List<Integer> components) {
        setName(name);
        SSAAnalysis analysis = new SSAAnalysis(series.convertToList(), window)
        this.series = series
        copyBackwords(analysis.getEigenComponents(components));
    }

}


