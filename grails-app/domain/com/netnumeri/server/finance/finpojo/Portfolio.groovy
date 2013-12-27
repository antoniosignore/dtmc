package com.netnumeri.server.finance.finpojo

import Jama.Matrix
import com.dtmc.club.Member
import com.netnumeri.server.enums.PortfolioEnum
import com.netnumeri.server.finance.finpojo.asset.Asset

class Portfolio extends Asset implements Serializable {

    static constraints = {
    }

    static belongsTo = [user: Member]

    static hasMany = [items: PortfolioEntry, transactions: Transaction]

//    List<PortfolioItem> items = new ArrayList<PortfolioItem>()
//    List<Transaction> transactions = new ArrayList<Transaction>()

    static mapping = {
        id generator: 'hilo',
                params: [table: 'hi_value', column: 'next_value', max_lo: 100]

        transactions cascade: 'all-delete-orphan'
        items cascade: 'all-delete-orphan'
    }

    String description

    static transients = [
            "assetsToHold",
            "tempWeights",
            "correlationMatrix",
            "covarianceMatrix"]

    int assetsToHold = 0;
    double wealth = 0;
    double[] tempWeights;
    Matrix covarianceMatrix
    Matrix correlationMatrix
    Date firstDate;
    Date lastDate;
    PortfolioEnum portfolioType

    Portfolio(String name, String description) {
        setName(name);
        this.description = description
    }

    Portfolio(String name, String description, double wealth) {
        setName(name);
        this.wealth = wealth;
        this.description = description
    }

    PortfolioEntry portfolioItemByName(String name) {
        PortfolioEntry item = null;
        if (items != null)
            items.each {
                PortfolioEntry portfolioItem = it
                if (portfolioItem.instrument.name.equalsIgnoreCase(name))
                    item = portfolioItem;
            }
        return item;
    }

    Portfolio clone() {
        Portfolio p = new Portfolio(getName(), "clone");

        items.each{item ->
            p.addToItems(item)
        }

        transactions.each{transaction ->
            p.addToTransactions(transaction)
        }

        p.assetsToHold = assetsToHold;
        p.wealth = wealth;
        p.tempWeights = tempWeights;
        if (covarianceMatrix != null) p.covarianceMatrix = (Matrix) covarianceMatrix.clone();
        if (correlationMatrix != null) p.correlationMatrix = (Matrix) correlationMatrix.clone();
        p.firstDate = firstDate;
        p.lastDate = lastDate;
        return p;
    }

}
