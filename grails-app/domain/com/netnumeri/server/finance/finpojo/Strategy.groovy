package com.netnumeri.server.finance.finpojo

import com.dtmc.club.Member
import com.netnumeri.server.enums.StrategyEnum

class Strategy {

    StrategyEnum strategyEnum

    String name

    Date when

    Portfolio portfolio
    Date	dateCreated
    Date	lastUpdated

    static belongsTo	= [member: Member]

    static constraints = {
    }

    static mapping = {
    }

}
