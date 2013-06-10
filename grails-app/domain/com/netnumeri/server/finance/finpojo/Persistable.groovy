package com.netnumeri.server.finance.finpojo


class Persistable {

    static mapping = {
        tablePerHierarchy false
    }

    String name;

    Date dateCreated

//    SecUser author
//    transient jmsService
//    transient afterInsert = {
//        jmsService.send 'grailstwitter.status', author.username
//    }

}
