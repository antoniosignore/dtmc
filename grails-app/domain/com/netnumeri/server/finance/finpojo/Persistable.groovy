package com.netnumeri.server.finance.finpojo

import com.dtmc.club.Club
import com.dtmc.club.Member

//import com.dtmc.security.UserBean

class Persistable {

    static mapping = { tablePerHierarchy false }

    Member user
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
