package com.netnumeri.server.finance.finpojo

import com.netnumeri.server.finance.beans.FinConstants

public class TransactionCost extends Persistable implements Serializable {

    FinConstants option;

    double costPerContract;
    double costPerTicket;
    double costPerValue;
    double costPerHoldingDay;

    public TransactionCost() {
    }

    public TransactionCost(double costPerContract, int option) {
        this.costPerContract = costPerContract;
        this.option = option;
    }

    public TransactionCost(double costPerContract, double costPerTicket, double costPerValue) {
        this.costPerContract = costPerContract;
        this.costPerTicket = costPerTicket;
        this.costPerValue = costPerValue;
        option = FinConstants.kMixed;
    }

    public TransactionCost(double costPerContract, double costPerTicket, double costPerValue, double costPerHoldingDay) {
        this.costPerContract = costPerContract;
        this.costPerTicket = costPerTicket;
        this.costPerValue = costPerValue;
        this.costPerHoldingDay = costPerHoldingDay;
        option = FinConstants.kComplete;
    }

    public double getCost(double amount) {
        switch (option) {
            case FinConstants.kPercent:
                return Math.abs(amount * costPerContract);
            case FinConstants.kFixed:
                return Math.abs(costPerContract);
            case FinConstants.kMixed:
                if (amount < costPerTicket) {
                    return Math.abs(costPerContract);
                } else {
                    return Math.abs(amount * costPerValue);
                }
        }
        return 0;
    }

    public double getCost(int amount,
                          double price,
                          double holdingPeriod) {
        return costPerContract +
                costPerTicket * Math.abs(amount) +
                costPerValue * Math.abs(amount) * price -
                costPerHoldingDay * holdingPeriod * amount * price;
    }

}


