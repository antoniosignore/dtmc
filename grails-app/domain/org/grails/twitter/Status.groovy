package org.grails.twitter

import org.example.SecUser


class Status {

    String message
    SecUser author
    Date dateCreated

    static constraints = {
    }
}
