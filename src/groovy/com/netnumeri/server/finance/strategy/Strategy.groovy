package com.netnumeri.server.finance.strategy

import com.dtmc.finance.finpojo.Instrument
import com.dtmc.trading.Signal
import com.netnumeri.server.finance.utils.DateUtils

public abstract class Strategy {

    String name
    double wealth = 0;
    Instrument asset;
    List<Signal> signals = new ArrayList<>()

    def final Date firstDate;
    def final Date lastDate;

    abstract public void evaluateInstrumentOnDate(Date date);

    public Strategy(String name, Instrument asset, Date firstDate, Date lastDate, double wealth) {
        this.name = name
        this.asset = asset
        this.wealth = wealth;
        this.firstDate = firstDate
        this.lastDate = lastDate
    }

    public void execute() {
        Date day = firstDate
        Date lastDay = lastDate
        while (DateUtils.isLessEqual(day, lastDay)) {
            if (asset.isDataAvailable(day)) evaluateInstrumentOnDate(day)
            day = DateUtils.nextDay(day);
        }
    }
}
