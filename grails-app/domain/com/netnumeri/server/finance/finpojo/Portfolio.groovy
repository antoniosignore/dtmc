package com.netnumeri.server.finance.finpojo

import Jama.Matrix
import com.netnumeri.server.finance.finpojo.asset.Asset
import com.netnumeri.server.finance.math.PortfolioService
import org.grails.twitter.auth.Person

class Portfolio extends Asset implements Serializable {

    static constraints = {
    }

    Person author

    Portfolio() {}

    List items = []
    List transactions = []
    static hasMany = [items: PortfolioItem, transactions: Transaction]

    static transients = [
            "isTesting",
            "assetsToHold",
            "tempWeights",
            "wealth",
            "correlationMatrix",
            "covarianceMatrix",
            "firstDailyDate",
            "lastDailyDate"]

    boolean isTesting = false
    int assetsToHold = 0;
    double wealth = 0;
    double[] tempWeights;
    Matrix covarianceMatrix
    Matrix correlationMatrix
    Date firstDailyDate;
    Date lastDailyDate;

    Portfolio(String name) {
        setName(name);
    }

    Portfolio(String name, double wealth) {
        setName(name);
        this.wealth = wealth;
    }

    PortfolioItem portfolioItemByName(String name) {
        PortfolioItem item = null;
        if (items != null)
            for (int i = 0; i < items.size(); i++) {
                PortfolioItem portfolioItem = items.get(i);
                if (portfolioItem.name.equalsIgnoreCase(name))
                    item = portfolioItem;
            }
        return item;
    }

    Portfolio clone() {
        Portfolio p = new Portfolio(getName());
        for (int i = 0; i < items.size(); i++) {
            PortfolioItem portfolioItem = items.get(i);
            assert p != null;
            PortfolioService.add(p, portfolioItem);
        }

        this.transactions
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            p.transactions.add(t)
        }

        p.isTesting = true;
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
