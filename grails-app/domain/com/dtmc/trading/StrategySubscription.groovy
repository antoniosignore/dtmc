package com.dtmc.trading

import arrested.ArrestedUser


class StrategySubscription {

    static belongsTo = [user: ArrestedUser, strategy: StrategyCatalog]

    Date dateCreated
    Date lastUpdated

}
