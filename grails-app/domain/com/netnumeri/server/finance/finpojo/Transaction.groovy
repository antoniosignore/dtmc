package com.netnumeri.server.finance.finpojo;

import com.netnumeri.server.finance.beans.Daily
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.utils.FormatUtils

public class Transaction extends Persistable implements Serializable {

    TradeEnum action;
    Instrument instrument;

    Integer amount = 0;
    Double price = 0.0;
    Double cost;
    Date date;

    public Transaction() {
    }

    public Transaction(Instrument instrument,
                       TradeEnum action,
                       int amount,
                       double price,
                       Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        this.date = date;
        this.instrument = instrument;
        this.action = action;
        this.amount = amount;
        this.price = price;
        this.cost = 0
    }

    public Transaction(Instrument instrument,
                       TradeEnum action,
                       int amount,
                       double price,
                       Date date,
                       Double cost) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        this.instrument = instrument;
        this.date = date;
        this.action = action;
        this.amount = amount;
        this.price = price;
        this.cost = cost;
    }

    public Transaction(Instrument instrument,
                       TradeEnum action,
                       int amount,
                       double price,
                       Date d,
                       int t,
                       Double cost) {
        if (d == null) throw new IllegalArgumentException("d cannot be null");
        if (cost == null) throw new IllegalArgumentException("cost cannot be null");
        this.date = d;
        this.instrument = instrument;
        this.action = action;
        this.amount = amount;
        this.price = price;
        this.cost = cost;
    }

    public Transaction(Instrument instrument,
                       TradeEnum action,
                       int amount,
                       Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        if (instrument == null) throw new IllegalArgumentException("instrument cannot be null");
        this.instrument = instrument;

        Daily daily = instrument.getDaily(date);
        if (daily != null && daily.valid()) {
            this.price = daily.get(FinConstants.CLOSE);
        } else {
            System.out.println("Transaction. No valid daily data available for " + instrument.getName() + " " + date.toString());
            this.price = 0;
        }
        this.date = date;
        this.action = action;
        this.amount = amount;
        this.price = 0;
        this.cost = 0
    }

    public Transaction(Instrument instrument,
                       TradeEnum Action,
                       int Amount,
                       Date date,
                       Double cost,
                       FinConstants Option) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        if (cost == null) throw new IllegalArgumentException("cost cannot be null");
        this.instrument = instrument;
        Daily daily = instrument.getDaily(date);
        if (daily.valid()) {
            this.price = daily.get(Option);
        } else {
            System.out.println("Transaction. No valid daily data available for " + instrument.getName() + " " + date.toString());
            this.price = 0;
        }
        this.date = date;
        this.action = Action;
        this.amount = Amount;
        this.cost = cost;
    }

    public double getCost() {
        if (cost != null) {
            return cost.getCost(amount * price);
        } else {
            return 0;
        }
    }

    public String print() {
        StringBuffer sb = new StringBuffer();
        sb.append(getDate().toString() + ": " + FormatUtils.actionToSting(action));
        sb.append(" # " + amount);
        sb.append(" of " + instrument.getName());
        sb.append(" @ " + price);
        return sb.toString();
    }

    public double getValue() {
        return amount * price;
    }
}
