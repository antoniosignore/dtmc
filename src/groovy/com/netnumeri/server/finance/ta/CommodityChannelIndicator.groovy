package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.utils.IndicatorUtils

public class CommodityChannelIndicator extends Indicator {

    int order

    public CommodityChannelIndicator() {
    }

    public CommodityChannelIndicator(TimeSeries series, String name, Integer order) {
        super(series, name);
        this.order = order;
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
                add(date, IndicatorUtils.CCI(series, date, order));
            }
            date = series.getNextDate(date)
        }
    }

    public int getFirstIndicatorIndex() {
        return (int) order;
    }
}


