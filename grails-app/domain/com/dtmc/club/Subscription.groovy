package com.dtmc.club

import com.dtmc.security.UserBean


class Subscription {

    static belongsTo = [user: UserBean]

    Date evaluationDate
    Double value

    static mapping = {
    }

    static constraints = {
    }

}
