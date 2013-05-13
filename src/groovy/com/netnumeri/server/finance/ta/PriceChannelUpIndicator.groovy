package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.utils.IndicatorUtils

public class PriceChannelUpIndicator extends Indicator {

    int order;
    double k;

    public PriceChannelUpIndicator(TimeSeries series, String name, Integer param1, double k) {
        super(series, name);
        order = param1;
        this.k = k;
        title = "Price channel Indicator up";
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


