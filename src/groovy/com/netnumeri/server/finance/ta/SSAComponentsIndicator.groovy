package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ssa.*

public class SSAComponentsIndicator extends Indicator {

    public SSAComponentsIndicator(TimeSeries series, String name, Integer window, List<Integer> components) {
        super (series, name)
        List<SSAItem> itemList = SSAStudy.analyze(series.convertToArray(), window)
        copyBackwords(SSAStudy.getColumn (itemList.get(0).reconstructed, 0));
    }

}


