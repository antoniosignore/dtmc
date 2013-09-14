package com.dtmc.club

import com.dtmc.security.UserBean


class ReturnOnInvestment {

    static belongsTo = [user: UserBean]

    Date evaluationDate
    Double value

    static mapping = {
    }

    static constraints = {
    }

}
