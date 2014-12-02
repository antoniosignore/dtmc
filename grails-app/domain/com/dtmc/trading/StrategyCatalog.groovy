package com.dtmc.trading

import com.dtmc.finance.finpojo.Persistable

public class StrategyCatalog extends Persistable {

    static hasMany = [subscriptions: StrategySubscription]

}
