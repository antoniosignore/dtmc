package com.dtmc.club

import com.dtmc.security.UserBean

class Club {

    String name

    static hasMany = [members: UserBean, uv: MonthlyUnitValue]

    Date dateCreated
    Date lastUpdated

    Double netAssetValue


    static mapping = {
    }

    static constraints = {
    }

}
