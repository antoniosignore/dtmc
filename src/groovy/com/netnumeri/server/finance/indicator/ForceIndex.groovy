package com.netnumeri.server.finance.indicator

import com.netnumeri.server.finance.beans.TimeSeries;

/**
 * Implementation reference: http://en.wikipedia.org/wiki/Force_Index
 *
 * @author Florent Guiliani
 */
public class ForceIndex {


    public static double calculate(TimeSeries closes, TimeSeries volumes, Date date) {

        int lastBar = closes.matrix.getIndex(date);
        double previousBar = closes.matrix.getValue(lastBar - 1);

        double value = volumes.matrix.getValue(lastBar) * (closes.matrix.getValue(lastBar) - previousBar);
        return value;
    }
}
