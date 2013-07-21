package com.netnumeri.server.finance.finpojo

import com.dtmc.club.Club
import com.dtmc.security.UserBean

class Persistable {

    static mapping = {
        tablePerHierarchy false
    }

    UserBean user
    Club club
    String name

    Date dateCreated
    Date lastUpdated

//    SecUser author
//    transient jmsService
//    transient afterInsert = {
//        jmsService.send 'grailstwitter.status', author.username
//    }

}
