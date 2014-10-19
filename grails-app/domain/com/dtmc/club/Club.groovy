package com.dtmc.club

import arrested.ArrestedUser

class Club implements Serializable {

    static hasMany = [members: ArrestedUser]

    String name
    Integer yearsTimeSpan
    Date inauguralMeeting
    Double monthlySubscription
    Double joiningFee
    Date dateCreated
    Date lastUpdated
    byte[] agreement

    static constraints = {
        agreement blank: true, nullable: true
        inauguralMeeting blank: true, nullable: true
        joiningFee blank: true, nullable: true
        monthlySubscription blank: true, nullable: true
        yearsTimeSpan blank: true, nullable: true
    }

    static mapping = {
        members lazy: 'join'
    }

    @Override
    public String toString() {
        return name
    }

//    def toObject() {
//        return [
//                id         : id,
//                name       : name,
//                members    : member
//        ]
//    }
}
