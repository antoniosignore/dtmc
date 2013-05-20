package com.netnumeri.server.finance.finpojo

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.finpojo.asset.Asset
import com.netnumeri.server.finance.finpojo.derivative.Derivative

public class PortfolioItem implements Serializable {

    static constraints = {
    }

    Instrument instrument
    Portfolio portfolio

    Integer amount = 0;

    public PortfolioItem() {
    }

    private void init() {
        this.amount = 0;
    }

    public PortfolioItem(Instrument instrument, Portfolio portfolio) {
        this.instrument = instrument;
        this.portfolio = portfolio;
    }

    // amount < 0 means taking short position in instrument
    public PortfolioItem(Instrument instrument, Integer amount, Portfolio portfolio) {
        this.instrument = instrument;
        this.amount = amount;
        this.portfolio = portfolio;
    }

    public FinConstants position() {
        if (amount >= 0)
            return FinConstants.LONG;
        else
            return FinConstants.SHORT;
    }

    public double price() {
        if (instrument instanceof Asset) {
            return instrument.getLast();
        } else if (instrument instanceof Derivative) {
            return instrument.premium();
        }
        return 0;
    }

    public double value() {
        return price() * amount;
    }

}
