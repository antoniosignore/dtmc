package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.utils.IndicatorUtils

public class MOMIndicator extends Indicator {

    int order;

    public MOMIndicator() {
    }

    public MOMIndicator(TimeSeries series, String name, Integer param1) {

        super(series, name);
        order = param1;
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

                add(date, IndicatorUtils.MOM(series, date, order));

            }
            date = series.getNextDate(date)
        }

    }


    public int getFirstIndicatorIndex() {

        return (int) order + 1;

    }

}


