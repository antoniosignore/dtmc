import org.example.SecRole
import org.example.SecUser
import org.example.SecUserSecRole
import org.grails.twitter.auth.Person

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        if (!Person.count()) {
            createData()
        }
    }

    def destroy = {
    }

    private void createData() {
//        def userRole = new Authority(authority: 'ROLE_USER').save()

        //Configure Security Roles
        def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)

        String password = springSecurityService.encodePassword('password')

        //add an admin and default user
        def adminUser = SecUser.findByUsername('admin') ?: new SecUser(
                username: 'admin',
                password: 'admin',
                enabled: true).save(failOnError: true)

        def basicUser = SecUser.findByUsername('guest') ?: new SecUser(
                username: 'guest',
                password: 'guest',
                enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            SecUserSecRole.create adminUser, adminRole
        }
        if (!basicUser.authorities.contains(userRole)) {
            SecUserSecRole.create basicUser, userRole
        }

//        String password = springSecurityService.encodePassword('password')
//
//        [jeff: 'Jeff Brown', graeme: 'Graeme Rocher', burt: 'Burt Beckwith', peter: 'Peter Ledbrook'].each { userName, realName ->
//            def user = new Person(username: userName, realName: realName, password: password, enabled: true).save()
//            PersonAuthority.create user, userRole, true
//        }
    }
}
