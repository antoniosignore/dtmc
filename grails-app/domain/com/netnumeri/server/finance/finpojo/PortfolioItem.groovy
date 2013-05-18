package com.netnumeri.server.finance.finpojo

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.finpojo.asset.Asset
import com.netnumeri.server.finance.finpojo.derivative.Derivative

public class PortfolioItem extends Persistable implements Serializable {

    static constraints = {
    }

    Instrument instrument
    Portfolio portfolio

    static mapping = {
        id composite: ['instrument', 'portfolio']
        version false
    }

    Integer amount = 0;
    Double weight;
    Double tempWeight;
    Double assetWeightLowerBound = -1;
    Double assetWeightUpperBound = -1;
    Date modelDate = new Date();
    Double modelPrice;

    public PortfolioItem() {
        init();
    }

    private void init() {
        this.amount = 0;
        this.weight = 1.0;
        this.tempWeight = 1.0;
        this.assetWeightLowerBound = 0.0;
        this.assetWeightUpperBound = 1.0;
        modelPrice = 0D;
    }

    public PortfolioItem(Instrument instrument, Portfolio portfolio) {
        init();
        this.instrument = instrument;
        this.portfolio = portfolio;
    }

    // amount < 0 means taking short position in instrument
    public PortfolioItem(Instrument instrument, Integer amount, Portfolio portfolio) {
        init();
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
