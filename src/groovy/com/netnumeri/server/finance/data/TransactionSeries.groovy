package com.netnumeri.server.finance.data

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.GenericTimeSeries
import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Transaction

public class TransactionSeries implements Serializable {

    protected GenericTimeSeries<Transaction> transactionArray = new GenericTimeSeries<Transaction>();

    public TransactionSeries() {
    }

    public void add(Transaction transaction) {
        transactionArray.put(transaction.getDate(), transaction);
    }

    public int getN() {
        return getNTransactions();
    }

    public int getNTransactions() {
        return transactionArray.size();
    }

    public Transaction getTransaction(Date date) {
        return transactionArray.get(date);
    }

    public Instrument getInstrument(Date date) {
        return getTransaction(date).getInstrument();
    }

    public FinConstants getAction(Date date) {
        return getTransaction(date).getAction();
    }

    public double getAmount(Date date) {
        return getTransaction(date).getAmount();
    }

    public double getPrice(Date date) {
        return getTransaction(date).getPrice();
    }

    public Date getDateTime(Date date) {
        return getTransaction(date).getDate();
    }

    public Date getDate(Date date) {
        return getTransaction(date).getDate();
    }

    public void clear() {
        transactionArray.clear()
    }

    public void delete() {
        transactionArray.clear()
    }

    public Transaction getFirstTransaction() {
        return transactionArray.getFirstValue()
    }

    public Transaction getLastTransaction() {
        return transactionArray.getLastValue()
    }

    public Date getFirstDate() {
        return transactionArray.getFirstDate()
    }

    public Date getLastDate() {
        return transactionArray.getLastDate()
    }

    // Return a "pair" of i-th transactions
    // Return next sell transactions for the same Instrument of buy transactions
    // Return previous buy transactions for the same Instrument of sell transactions
    // Return null if there is no pair transactions
    public Transaction getPair(Date date, TradeEnum action) {

        if (action == TradeEnum.BUY) {
            Transaction transaction = transactionArray.getNextValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == TradeEnum.SELL)
                return transaction
            else
                return getPair(transaction.date, action)
        }

        if (action == TradeEnum.SELL) {
            Transaction transaction = transactionArray.getPrevValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == TradeEnum.BUY)
                return transaction
            else
                return getPair(transaction.date, action)
        }

        if (action == TradeEnum.SELLSHORT) {
            Transaction transaction = transactionArray.getNextValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == TradeEnum.BUYSHORT)
                return transaction
            else
                return getPair(transaction.date, action)
        }

        if (action == TradeEnum.SELLSHORT) {
            Transaction transaction = transactionArray.getPrevValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == TradeEnum.BUYSHORT)
                return transaction
            else
                return getPair(transaction.date, action)
        }
    }

    public Object clone() {
        TransactionSeries ts = new TransactionSeries();
        transactionArray.iterator();
        for (Iterator<Transaction> iterator = transactionArray.iterator(); iterator.hasNext();) {
            Transaction transaction = iterator.next();
            ts.transactionArray.put(transaction.getDate(), transaction);
        }
        return ts;
    }
}