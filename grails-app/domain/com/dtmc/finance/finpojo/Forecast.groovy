package com.dtmc.finance.finpojo

import arrested.ArrestedUser
import com.dtmc.finance.finpojo.asset.Stock

class Forecast extends Persistable implements Serializable {

    static belongsTo = [user: ArrestedUser]

    Stock ticker
    Double percent
    Date when

    Bet bet
}
