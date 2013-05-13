package com.netnumeri.server.finance.strategy.bearish

import com.netnumeri.server.finance.finpojo.Bet
import com.netnumeri.server.finance.finpojo.Forecast
import com.netnumeri.server.finance.strategy.OptionStrategy

import com.netnumeri.server.finance.finpojo.asset.Stock

import com.netnumeri.server.finance.finpojo.derivative.equity.Option
import com.netnumeri.server.finance.strategy.StrategyHelper
import com.netnumeri.server.entity.OptionType
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.strategy.ForecastType

class LongPut implements OptionStrategy {

    @Override
    List<Bet> analyze(Forecast forecast, Stock instrument) {
        List<Bet> bets = new ArrayList<Bet>()

        List<Option> options = StrategyHelper.getAtTheMoneyList(instrument, OptionType.PUT);

        for (int i = 0; i < options.size(); i++) {
            Option option = options.get(i);
            Transaction transaction = convertOptionToTransaction(option);
            Bet bet = new Bet();
            bet.name = "Long Put"
            bet.description = "Long Put"
            bet.transactions.add(transaction)
            bets.add(bet)
        }
        return bets
    }

    Transaction convertOptionToTransaction(Option option) {

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
