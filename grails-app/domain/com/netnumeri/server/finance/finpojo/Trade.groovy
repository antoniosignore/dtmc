package com.netnumeri.server.finance.finpojo

import com.netnumeri.server.enums.TradeEnum
import com.netnumeri.server.finance.beans.Daily
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.utils.FormatUtils

public class Trade implements Serializable {

    TradeEnum tradeAction;

    static belongsTo = [portfolio: Portfolio]

    Integer amount = 0;
    Double price = 0.0;
    Double cost = 0;
    Date transactionDate;

    public Trade() {
    }

    public Trade(Instrument instrument,
                 TradeEnum tradeAction,
                 int amount,
                 double price,
                 Date transactionDate) {
        if (transactionDate == null) throw new IllegalArgumentException("transactionDate cannot be null");
        this.transactionDate = transactionDate;
        this.instrument = instrument;
        this.tradeAction = tradeAction;
        this.amount = amount;
        this.price = price;
        this.cost = 0
    }

    public Trade(Instrument instrument,
                 TradeEnum tradeAction,
                 int amount,
                 double price,
                 Date transactionDate,
                 Double cost) {
        if (transactionDate == null) throw new IllegalArgumentException("transactionDate cannot be null");
        this.instrument = instrument;
        this.transactionDate = transactionDate;
        this.tradeAction = tradeAction;
        this.amount = amount;
        this.price = price;
        this.cost = cost;
    }

    public Trade(Instrument instrument,
                 TradeEnum tradeAction,
                 int amount,
                 double price,
                 Date d,
                 int t,
                 Double cost) {
        if (d == null) throw new IllegalArgumentException("d cannot be null");
        if (cost == null) throw new IllegalArgumentException("cost cannot be null");
        this.transactionDate = d;
        this.instrument = instrument;
        this.tradeAction = tradeAction;
        this.amount = amount;
        this.price = price;
        this.cost = cost;
    }

    public Trade(Instrument instrument,
                 TradeEnum tradeAction,
                 int amount,
                 Date transactionDate) {
        if (transactionDate == null) throw new IllegalArgumentException("transactionDate cannot be null");
        if (instrument == null) throw new IllegalArgumentException("instrument cannot be null");
        this.instrument = instrument;

        Daily daily = instrument.getDaily(transactionDate);
        if (daily != null && daily.valid()) {
            this.price = daily.get(FinConstants.CLOSE);
        } else {
            System.out.println("Trade. No valid daily data available for " + instrument.getName() + " " + transactionDate.toString());
            this.price = 0;
        }
        this.transactionDate = transactionDate;
        this.tradeAction = tradeAction;
        this.amount = amount;
        this.price = 0;
        this.cost = 0
    }

    public Trade(Instrument instrument,
                 TradeEnum tradeAction,
                 int Amount,
                 Date transactionDate,
                 Double cost,
                 FinConstants Option) {
        if (transactionDate == null) throw new IllegalArgumentException("transactionDate cannot be null");
        if (cost == null) throw new IllegalArgumentException("cost cannot be null");
        this.instrument = instrument;
        Daily daily = instrument.getDaily(transactionDate);
        if (daily.valid()) {
            this.price = daily.get(Option);
        } else {
            System.out.println("Trade. No valid daily data available for " + instrument.getName() + " " + transactionDate.toString());
            this.price = 0;
        }
        this.transactionDate = transactionDate;
        this.tradeAction = tradeAction;
        this.amount = Amount;
        this.cost = cost;
    }

    public String print() {
        StringBuffer sb = new StringBuffer();
        sb.append(getTransactionDate().toString() + ": " + FormatUtils.actionToSting(tradeAction));
        sb.append(" # " + amount);
        sb.append(" of " + instrument.getName());
        sb.append(" @ " + price);
        return sb.toString();
    }

    public double getValue() {
        return amount * price;
    }
}
