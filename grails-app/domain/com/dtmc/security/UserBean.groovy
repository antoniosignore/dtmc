package com.dtmc.security

import com.dtmc.club.Club
import com.dtmc.club.UserProperties

class UserBean {

    transient springSecurityService

    static belongsTo = [club: Club]

    String username
    String password

    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    UserProperties properties

    Date lastLoginDate
    Date registerDate

    // Constraints used for validations
    static constraints = {
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
