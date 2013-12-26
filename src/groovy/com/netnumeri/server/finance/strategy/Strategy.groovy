package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.data.TransactionSeries
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
    public TransactionSeries transactionSeries = null;
    PortfolioService portfolioService = new PortfolioService()

    def final Date firstDate;
    def final Date lastDate;

    abstract public void evaluateInstrumentOnDate(Date date, Instrument asset);

    protected Strategy() {
    }

    public Strategy(String name, final Portfolio portfolio, Date firstDate, Date lastDate, double wealth) {
      //  this.transactionSeries = new TransactionSeries();
        this.wealth = wealth;
        this.portfolio = portfolio
        this.name = name
        this.portfolio = portfolio;
        tester = new BackTest(this, wealth);
        this.firstDate = firstDate
        this.lastDate = lastDate
    }

    public void add(Transaction transaction) {
//        transactionSeries.add(transaction);
        portfolioService.add(portfolio, transaction);
    }

    public void add(Instrument instrument, TradeEnum action, int amount, double price, Date date) {
        add(new Transaction(instrument, action, amount, price, date));
    }

    public void test(Date firsDate, Date lasDate) {
        tester.test(firsDate, lasDate, FinConstants.kInvestOnDate);
    }

    public void run() {

        Date day = portfolioService.firstDay(portfolio)
        Date lastDay = portfolioService.latestDay(portfolio)

        while (DateUtils.isLessEqual(day, lastDay)) {

            portfolio.items.each {

                Instrument asset = it.instrument
                if (asset.isDataAvailable(day)) {
                    evaluateInstrumentOnDate(day, asset);
                }

            }

//            for (int i = 0; i < portfolio.items.size(); i++) {
//                Instrument asset = portfolioService.getInstrument(portfolio, i);
//                if (asset.isDataAvailable(day)) {
//                    evaluateInstrumentOnDate(day, asset);
//                }
//            }
            day = DateUtils.nextDay(day);
        }
    }
}
