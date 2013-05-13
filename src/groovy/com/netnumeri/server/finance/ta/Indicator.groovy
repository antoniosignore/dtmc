package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.utils.DateUtils

public abstract class Indicator extends TimeSeries {

    protected FinConstants indicator;
    protected String name;
    protected String title;
    protected TimeSeries series;
    protected TimeSeries series1;
    protected TimeSeries series2;
    protected Instrument instrument;

    private static final double DOUBLE100 = 100.0

    public Indicator() {
    }

    public Indicator(TimeSeries series, String name) {
        super(name);
        this.series = series;
    }

    public Indicator(Instrument instrument, String name) {
        super(name);
        this.instrument = instrument;
    }


    protected void copyBackwords(double[] ad) {
        Date date = series.getLastDate();
        for (int i = 0; i < ad.length; i++) {
            double value = ad[ad.length - i - 1];
            add(date, value);
            date = series.getPrevDate(date);
        }
    }

    protected void copyBackwords(List<Double> ad) {
        Date date = series.getLastDate();
        for (int i = 0; i < ad.size(); i++) {
            double value = ad.get(ad.size() - i - 1)
            add(date, value)
            date = series.getPrevDate(date)
        }
    }

    protected void copyInTheFuture(double[] ad) {
        Date date = series.getLastDate();
        for (int i = 0; i < ad.length; i++) {
            double value = ad[i];
            date = DateUtils.addDays(date, 1);
            add(date, value);
        }
    }


}


