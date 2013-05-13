package com.netnumeri.server.finance.data

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.GenericTimeSeries
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

    public Transaction getPair(Date date, FinConstants action) {

        if (action == FinConstants.BUY) {
            Transaction transaction = transactionArray.getNextValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == FinConstants.SELL)
                return transaction
            else
                return getPair(transaction.date, action)
        }

        if (action == FinConstants.SELL) {
            Transaction transaction = transactionArray.getPrevValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == FinConstants.BUY)
                return transaction
            else
                return getPair(transaction.date, action)
        }

        if (action == FinConstants.SELLSHORT) {
            Transaction transaction = transactionArray.getNextValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == FinConstants.BUYSHORT)
                return transaction
            else
                return getPair(transaction.date, action)
        }

        if (action == FinConstants.SELLSHORT) {
            Transaction transaction = transactionArray.getPrevValue(date)
            if (transaction == null)
                return null
            else if (transaction.action == FinConstants.BUYSHORT)
                return transaction
            else
                return getPair(transaction.date, action)
        }

    }

    // Return a "pair" of i-th transactions
    // Return next sell transactions for the same Instrument of buy transactions
    // Return previous buy transactions for the same Instrument of sell transactions
    // Return null if there is no pair transactions
//    public Transaction getPair(Transaction transaction , Date date) {
//
//        Instrument instrument = getInstrument(date);
//        FinConstants act = getAction(date);
//        return getPair(date, act)

//        switch (act) {
//            case FinConstants.BUY:
//                Transaction get = transactionArray.getNextValue(date)
//                while (get != null){
//                    get = transactionArray.getNextValue(date)
//                }
//                for (int count = date + 1; count < getNTransactions(); count++) {
//                    if (getInstrument(count).equals(instrument) && getAction(count) == FinConstants.SELL) {
//                        return getTransaction(count);
//                    }
//                }
//                break;
//            case FinConstants.SELL:
//                for (int count = date - 1; count >= 0; count--) {
//                    if (getInstrument(count).equals(instrument) && getAction(count) == FinConstants.BUY) {
//                        return getTransaction(count);
//                    }
//                }
//                break;
//            case FinConstants.SELLSHORT:
//                for (int count = date + 1; count < getNTransactions(); count++) {
//                    if (getInstrument(count).equals(instrument) && getAction(count) == FinConstants.BUYSHORT) {
//                        return getTransaction(count);
//                    }
//                }
//                break;
//            case FinConstants.BUYSHORT:
//                for (int count = date - 1; count >= 0; count--) {
//                    if (getInstrument(count).equals(instrument) && getAction(count) == FinConstants.SELLSHORT) {
//                        return getTransaction(count);
//                    }
//                }
//                break;
//        }
//        return null;
//    }

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