package com.dtmc.club

class Banker implements Serializable {

    String name

    static belongsTo = [club: Club]

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }
}
