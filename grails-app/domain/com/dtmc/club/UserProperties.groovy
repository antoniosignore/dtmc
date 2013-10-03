package com.dtmc.club

import com.dtmc.security.UserBean

class UserProperties {

    static belongsTo = [userBean: UserBean]

    String fname
    String lname
    String address1
    String address2
    String city
    String state
    String country
    String company
    String email
    String phone
    String mobile

    Double allocatedUnits

    // Constraints used for validations
    static constraints = {
        fname(size: 1..30, blank: true)
        lname(size: 1..30, blank: true)
        email(email: true, blank: true)
        phone(size: 6..15, blank: true)
        mobile(size: 6..15, blank: true)
        city(size: 1..20, blank: true)
        state(size: 1..20, blank: true)
        country(size: 1..20, blank: true)
        address1(size: 5..200, blank: true)
    }

    static mapping = {
    }

}
