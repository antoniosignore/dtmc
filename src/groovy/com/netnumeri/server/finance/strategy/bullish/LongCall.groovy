package com.netnumeri.server.finance.strategy.bullish

import com.netnumeri.server.finance.finpojo.Forecast
import com.netnumeri.server.finance.finpojo.Bet
import com.netnumeri.server.finance.strategy.OptionStrategy

import com.netnumeri.server.finance.finpojo.asset.Stock

import com.netnumeri.server.finance.strategy.StrategyHelper
import com.netnumeri.server.finance.finpojo.derivative.equity.Option
import com.netnumeri.server.entity.OptionType
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.strategy.ForecastType
import com.netnumeri.server.finance.beans.FinConstants


class LongCall implements OptionStrategy {

    @Override
    List<Bet> analyze(Forecast forecast, Stock instrument) {
        List<Bet> bets = new ArrayList<Bet>()

        List<Option> options = StrategyHelper.getAtTheMoneyList(instrument, OptionType.CALL);

        for (int i = 0; i < options.size(); i++) {
            Option option = options.get(i);
            Transaction transaction = convertOptionToTransaction(option, instrument);
            Bet bet = new Bet();
            bet.name = "LongCall"
            bet.description = "Long Call"
            bet.transactions.add(transaction)
            bets.add(bet)
        }
        return bets
    }

    Transaction convertOptionToTransaction(Option option, Stock instrument) {
        return new Transaction(option, FinConstants.BUY, 100, new Date());
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
        return ForecastType.bullish
    }
}
