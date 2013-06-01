package com.netnumeri.server.finance.beans;


import com.netnumeri.server.finance.finpojo.Instrument

class Daily implements Serializable {

    private Instrument instrument;

    int arrayindex;
    Date dailydate = null;
    int volume = 0;
    double high = 0;
    double low = 0;
    double openprice = 0;
    double closeprice = 0;
    FinConstants state = FinConstants.NOTAVAILABLE;

    double bid = 0;
    double ask = 0;
    int bidSize = 0;
    int askSize = 0;
    int openInterest;

    public Daily() {
        this.state = FinConstants.VALID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Daily data normal constructor
     */
    public Daily(Daily daily) {
        this.arrayindex = daily.arrayindex;
        this.dailydate = daily.dailydate;
        this.high = daily.high;
        this.low = daily.low;
        this.openprice = daily.openprice;
        this.closeprice = daily.closeprice;
        this.volume = daily.volume;
        this.state = daily.state;
        this.instrument = daily.instrument;
    }

    public Daily(Instrument instrument,
                 int arrayindex,
                 Date d,
                 double high,
                 double low,
                 double open,
                 double close,
                 int volume, int oint,
                 FinConstants state) {
        this.instrument = instrument;
        this.arrayindex = arrayindex;
        this.dailydate = d;
        this.high = high;
        this.low = low;
        this.openprice = open;
        this.closeprice = close;
        this.volume = volume;
        openInterest = oint;
        this.state = state;
    }

    public boolean valid() {
        if (state == FinConstants.VALID)
            return true;
        else
            return false;
    }

    public double price() {
        return price(FinConstants.TYPICALPRICE);
    }

    public double price(FinConstants Option) {
        switch (Option) {
            case FinConstants.HIGH:
                return high;
            case FinConstants.LOW:
                return low;
            case FinConstants.OPEN:
                return openprice;
            case FinConstants.CLOSE:
                return closeprice;
            case FinConstants.VOLUME:
                return volume;
            case FinConstants.MEDIANPRICE:
                return (high + low) / 2.0;
            case FinConstants.TYPICALPRICE:
                return (high + low + closeprice) / 3.0;
            case FinConstants.WEIGHTEDPRICE:
                return (high + low + 2.0 * closeprice) / 4.0;
            case FinConstants.AVERAGEPRICE:
                return (high + low + closeprice + openprice) / 4.0;
            case FinConstants.LOGAVERAGEPRICE:
                return Math.log((high + low + closeprice + openprice) / 4.0);
        }
        return 0;
    }

    public void set(Daily daily) {
        if (daily == null) {
            throw new IllegalArgumentException("Daily: set() " + "Argument is null");
        }
        arrayindex = daily.arrayindex;
        dailydate = daily.dailydate;
        volume = daily.volume;
        high = daily.high;
        low = daily.low;
        openprice = daily.openprice;
        closeprice = daily.closeprice;
        state = daily.state;
    }

    public Object clone() {
        Daily q = new Daily();
        q.arrayindex = this.arrayindex;
        q.volume = this.volume;
        q.high = this.high;
        q.low = this.low;
        q.openprice = this.openprice;
        q.closeprice = this.closeprice;
        q.openInterest = this.openInterest;
        q.state = this.state;
        q.dailydate = (Date) dailydate.clone();
        return q;
    }

    public Instrument getInstrument() {
        return this.instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
