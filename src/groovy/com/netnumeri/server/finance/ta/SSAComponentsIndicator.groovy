package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ssa.SSAAnalysis

public class SSAComponentsIndicator extends Indicator {

    int window

    public SSAComponentsIndicator(TimeSeries series, String name, SSAAnalysis analysis, List<Integer> components) {
        setName(name);
        this.series = series
        this.window = window
        title = "Singular Spectrum Component";
        copyBackwords(analysis.getEigenComponents(components));
    }

}


