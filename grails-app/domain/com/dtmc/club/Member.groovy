package com.dtmc.club

class Member extends SecUser{

    static belongsTo = [club: Club]

    String firstname
    String lastname
    String address1
    String address2
    String city
    String state
    String country
    String company
    String email
    String phone
    String mobile
    String twitter
    String facebook
    String google
    String linkedin

    ImageNode small

    static constraints = {

        firstname(size: 1..30, blank: true)
        lastname(size: 1..30, blank: true)
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
