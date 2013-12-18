package com.netnumeri.server.finance.finpojo

import com.dtmc.club.Member
import com.netnumeri.server.enums.StrategyEnum

class Strategy {

    static mapping = {
        tablePerHierarchy false
    }

    StrategyEnum strategyEnum

    String name

    Date when

    Portfolio portfolio
    Date	dateCreated
    Date	lastUpdated

    static belongsTo	= [member: Member]

    static constraints = {
    }

}
