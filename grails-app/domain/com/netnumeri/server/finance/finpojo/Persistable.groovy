package com.netnumeri.server.finance.finpojo

import org.example.SecUser

class Persistable {

    static mapping = {
        tablePerHierarchy false
    }

    String name;
    String description = "xx";
    Date dateCreated

    SecUser author
//    transient jmsService
//    transient afterInsert = {
//        jmsService.send 'grailstwitter.status', author.username
//    }

}
