package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.utils.IndicatorUtils

public class PriceChannelUpIndicator extends Indicator {

    int order
    double k

    public PriceChannelUpIndicator(TimeSeries series, String name, Integer order, double k) {
        super(series, name);
        this.order = order;
        this.k = k;
        build();
    }

    public void build() {

        Date date = series.getFirstDate()
        int index = 0;
        while (index < getFirstIndicatorIndex()) {
            date = series.getNextDate(date);
            index++;
        }

        while (date != null) {
            if (!series.isEmpty(date)) {
                add(date, IndicatorUtils.PCU(series, date, order, k));
            }
            date = series.getNextDate(date)
        }
    }


    public int getFirstIndicatorIndex() {

        return (int) order;

    }

}


