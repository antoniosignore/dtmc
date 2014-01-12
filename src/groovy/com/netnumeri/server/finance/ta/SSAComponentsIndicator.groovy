package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ssa.SSA

public class SSAComponentsIndicator extends Indicator {

    public SSAComponentsIndicator(TimeSeries series, String name, Integer window, List<Integer> components) {
        setName(name);
        SSA analysis = new SSA(series.convertToArray(), window)
        this.series = series
        copyBackwords(analysis.getEigenComponent(0));
    }

}


