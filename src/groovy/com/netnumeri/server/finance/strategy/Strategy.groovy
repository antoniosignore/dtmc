package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.data.TransactionSeries
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.math.PortfolioService
import com.netnumeri.server.finance.utils.DateUtils

public abstract class Strategy {

    private Backtest tester;
    protected Portfolio portfolio;
    protected Portfolio strategyPortfolio;
    double wealth = 0;
    public TransactionSeries transactionSeries = null;

    def final Date firstDate;
    def final Date lastDate;

    abstract public void evaluateInstrumentOnDate(Date date, Instrument asset);

    protected Strategy() {
    }

    public Strategy(String name, final Portfolio portfolio, Date firstDate, Date lastDate, double wealth) {
        this.transactionSeries = new TransactionSeries();
        this.wealth = wealth;
        this.strategyPortfolio = portfolio.clone();
        this.portfolio = portfolio;
        tester = new Backtest(this, wealth);
        this.firstDate = firstDate
        this.lastDate = lastDate
    }

    public void add(Transaction transaction) {
        transactionSeries.add(transaction);
        PortfolioService.add(strategyPortfolio, transaction);
    }

    public void add(Instrument instrument, FinConstants action, int amount, double price, Date date) {
        add(new Transaction(instrument, action, amount, price, date));
    }

    public void test(Date firsDate, Date lasDate) {
        tester.test(firsDate, lasDate, FinConstants.kInvestOnDate);
    }

    public TimeSeries getProfitLossSeries() {
        return tester.getPnLSeries();
    }

    public TimeSeries getWealthSeries() {
        return tester.getWealthSeries();
    }

    public void run() {

        Date day = PortfolioService.getFirstDay(portfolio)
        Date lastDay = PortfolioService.getLastDay(portfolio)

        while (DateUtils.isLessEqual(day, lastDay)) {

            for (int i = 0; i < portfolio.items.size(); i++) {
                Instrument asset = PortfolioService.getInstrument(portfolio, i);
                if (asset.isDataAvailable(day)) {
                    evaluateInstrumentOnDate(day, asset);
                }
            }
            day = DateUtils.nextDay(day);
        }
    }
}
