package com.dtmc.club

class LegalStructure implements Serializable {

    String name

    Date dateCreated
    Date lastUpdated

    static belongsTo = [club: Club]

    static constraints = {
    }
}
