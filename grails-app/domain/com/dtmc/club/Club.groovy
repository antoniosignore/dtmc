package com.dtmc.club

import com.netnumeri.server.enums.RiskEnum

class Club implements Serializable {

    String name

    Integer yearsTimeSpan

    Date inauguralMeeting

    RiskEnum risk

    static hasMany = [members: Member]

    Double monthlySubscription
    Double joiningFee

    Date dateCreated
    Date lastUpdated

    Banker banker
    Broker broker
    LegalStructure legalStructure

    byte[] agreement

    static mapping = {
    }

    static constraints = {
    }

}
