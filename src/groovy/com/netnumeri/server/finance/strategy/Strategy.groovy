package com.netnumeri.server.finance.strategy

import com.netnumeri.server.enums.PortfolioEnum
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.utils.DateUtils
import dtmc.PortfolioService

public abstract class Strategy {

    String name
    BackTest tester;
    Portfolio portfolio;
    double wealth = 0;
    Instrument asset;
    List<Signal> signals = new ArrayList<>()

    PortfolioService portfolioService = new PortfolioService()

    def final Date firstDate;
    def final Date lastDate;

    abstract public void evaluateInstrumentOnDate(Date date);

    public Strategy(String name, Instrument asset, Date firstDate, Date lastDate, double wealth) {

        portfolio = new Portfolio(name, "Strategy Portfolio", wealth);
        portfolio.portfolioType = PortfolioEnum.Strategy

        this.asset = asset
        this.wealth = wealth;
        this.portfolio = portfolio
        this.name = name
        tester = new BackTest(this, wealth);
        this.firstDate = firstDate
        this.lastDate = lastDate
    }

    public void add(Transaction transaction) {
        portfolioService.add(portfolio, transaction);
    }

    public void test() {
        tester.test(firstDate, lastDate, FinConstants.kInvestOnDate);
    }

    public void run() {
        Date day = firstDate
        Date lastDay = lastDate
        while (DateUtils.isLessEqual(day, lastDay)) {
            if (asset.isDataAvailable(day)) {
                println "evaluate day = $day"
                evaluateInstrumentOnDate(day);
            }
            day = DateUtils.nextDay(day);
        }
    }
}
