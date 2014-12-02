package com.dtmc.trading

import com.dtmc.finance.finpojo.Instrument
import com.dtmc.finance.finpojo.Persistable
import com.netnumeri.server.finance.beans.TradeEnum

class Signal extends Persistable {

    Date day
    TradeEnum direction
    Instrument instrument
    Double value

    static belongsTo = [instrument: Instrument, strategyExecution: StrategyExecution]

    @Override
    public String toString() {
        return "Signal{" +
                "id=" + id +
                ", day=" + day +
                ", direction=" + direction +
                ", instrument=" + instrument.name +
                ", value=" + value +
                '}';
    }
}
