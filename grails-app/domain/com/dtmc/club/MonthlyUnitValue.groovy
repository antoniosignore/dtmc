package com.dtmc.club


class MonthlyUnitValue {

    static belongsTo = [club: Club]
    Date evaluationDate
    Double value

    static mapping = {
    }

    static constraints = {
    }

}
