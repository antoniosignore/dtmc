package com.dtmc.trading

import arrested.ArrestedUser
import com.dtmc.finance.finpojo.Persistable


class StrategySubscription extends Persistable{

    static belongsTo = [user: ArrestedUser, strategy: StrategyCatalog]

}
