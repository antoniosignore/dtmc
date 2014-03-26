package com.dtmc.club

class Broker implements Serializable {

    String name

    static belongsTo = [club: Club]

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }
}
