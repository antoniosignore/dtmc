package com.dtmc.security

class UserBean {

    transient springSecurityService

    String username
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

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

    Date lastLoginDate
    Date registerDate

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
        username blank: false, unique: true
        password blank: false
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
