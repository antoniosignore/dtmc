package com.dtmc.club

import com.netnumeri.server.finance.indicator.Indicators

class Club {

    String name

    static hasMany = [indicators: Indicators]

    Date dateCreated
    Date lastUpdated

    static mapping = {
    }

    static constraints = {
    }

}
