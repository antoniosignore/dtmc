package com.netnumeri.server.finance.finpojo

import Jama.Matrix
import com.netnumeri.server.finance.finpojo.asset.Asset
import com.netnumeri.server.finance.math.PortfolioService

class Portfolio extends Asset implements Serializable {

    static constraints = {
    }

    boolean isTesting = false

    Portfolio() {
        init("Unnamed");
    }

//    public List<PortfolioItem> items;

    static hasMany = [items: PortfolioItem, transactions: Transaction]

    int assetsToHold = 0;
    double wealth = 0;
    double[] tempWeights;

    Matrix covarianceMatrix;

    Matrix correlationMatrix;

    Date firstDailyDate;

    Date lastDailyDate;

    double modelPrice(int model) {
        return 0;
    }

    Portfolio(String name) {
        init(name);
    }

    Portfolio(String name, double wealth) {
        init(name);
        this.wealth = wealth;
    }


    void init(String name) {
        setName(name);
        assetsToHold = 0;
        tempWeights = null;
        firstDailyDate = null;
        lastDailyDate = null;
//        MersenneTwister engine = new MersenneTwister();
//        dist = new Uniform(engine);
        //    items = new ArrayList<PortfolioItem>();
        transactions = new ArrayList<Transaction>();
        items = new ArrayList<PortfolioItem>();
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
