package com.netnumeri.server.finance.strategy.bearish

import com.netnumeri.server.finance.finpojo.Bet
import com.netnumeri.server.finance.finpojo.Forecast
import com.netnumeri.server.finance.strategy.OptionStrategy

import com.netnumeri.server.finance.finpojo.asset.Stock

import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.strategy.ForecastType

class ShortShares implements OptionStrategy {

    @Override
    List<Bet> analyze(Forecast forecast, Stock instrument) {
        List<Bet> bets = new ArrayList<Bet>()
        Date date = DateUtils.today();
        Transaction transaction = new Transaction(instrument, FinConstants.SHORT, 100, instrument.getPrice(instrument.getLastDate()), date);
        Bet bet = new Bet();
        bet.transactions.add(transaction)
        bet.name = "Short Shares"
        bet.description = "Short Shares"
        bets.add(bet)
        return bets
    }

    @Override
    String getImage() {
        return "images/long-call.gif"
    }

    @Override
    boolean isUnlimited() {
        return true
    }

    @Override
    Double maximumLoss() {
        return null
    }

    @Override
    boolean isForRetail() {
        return true
    }

    @Override
    Double breakevenPoint() {
        return null
    }

    @Override
    Double profit() {
        return null
    }

    @Override
    ForecastType getType() {
        return ForecastType.bearish
    }
}
