package com.netnumeri.server.finance.finpojo

import com.dtmc.club.Member

class Persistable implements Serializable {

    static mapping = {
        tablePerHierarchy false
    }

    static belongsTo = [user: Member]

    String name

    Date dateCreated
    Date lastUpdated

//    SecUser author
//    transient jmsService
//    transient afterInsert = {
//        jmsService.send 'grailstwitter.status', author.username
//    }

}
