package com.netnumeri.server.finance.finpojo

import Jama.Matrix
import com.netnumeri.server.finance.finpojo.asset.Asset

class Portfolio extends Asset implements Serializable {

    static constraints = {
    }

    static mapping = {
        id generator: 'hilo',
                params: [table: 'hi_value', column: 'next_value', max_lo: 100]
    }

    String description

    static hasMany = [items: PortfolioItem, transactions: Trade]

    static transients = [
            "assetsToHold",
            "tempWeights",
            "wealth",
            "correlationMatrix",
            "covarianceMatrix",
            "firstDailyDate",
            "lastDailyDate"]

    int assetsToHold = 0;
    double wealth = 0;
    double[] tempWeights;
    Matrix covarianceMatrix
    Matrix correlationMatrix
    Date firstDailyDate;
    Date lastDailyDate;

    Portfolio(String name, String description) {
        setName(name);
        this.description = description
    }

    Portfolio(String name, String description, double wealth) {
        setName(name);
        this.wealth = wealth;
        this.description = description
    }

    PortfolioItem portfolioItemByName(String name) {
        PortfolioItem item = null;
        if (items != null)
            for (int i = 0; i < items.size(); i++) {
                PortfolioItem portfolioItem = items.get(i);
                if (portfolioItem.instrument.name.equalsIgnoreCase(name))
                    item = portfolioItem;
            }
        return item;
    }

    // todo - remove dependency from PortfolioService
    Portfolio clone() {
        Portfolio p = new Portfolio(getName(), "clone");
        for (int i = 0; i < items.size(); i++) {
            PortfolioItem portfolioItem = items.get(i);
            //     TradeService.add(p, portfolioItem);
        }

        this.transactions
        for (int i = 0; i < transactions.size(); i++) {
            Trade t = transactions.get(i);
            p.transactions.add(t)
        }

        p.assetsToHold = assetsToHold;
        p.wealth = wealth;
        p.tempWeights = tempWeights;
        if (covarianceMatrix != null) p.covarianceMatrix = (Matrix) covarianceMatrix.clone();
        if (correlationMatrix != null) p.correlationMatrix = (Matrix) correlationMatrix.clone();
        p.firstDailyDate = firstDailyDate;
        p.lastDailyDate = lastDailyDate;
        return p;
    }

}
