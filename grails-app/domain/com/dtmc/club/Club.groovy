package com.dtmc.club

//import com.dtmc.security.UserBean

class Club {

    String name

    static hasMany = [members: Member]

    Date dateCreated
    Date lastUpdated

    static mapping = {
    }

    static constraints = {
    }

}
