package com.netnumeri.server.finance.strategy

import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.finpojo.Instrument

class Signal {

    private final Date day
    private final TradeEnum direction
    private final Instrument instrument

    Signal(Date day, TradeEnum direction, Instrument instrument) {
        println "SIGNAL = $day"
        println "direction = $direction.value"
        println "instrument = $instrument.name"
        this.day = day
        this.direction = direction
        this.instrument = instrument
    }
}
