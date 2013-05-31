import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import org.example.SecRole
import org.example.SecUser
import org.example.SecUserSecRole
import org.grails.twitter.auth.Person

class BootStrap {

    def springSecurityService
    def tradeService

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

        Portfolio portfolio = new Portfolio("SMA crossing", 10000)
        portfolio.save(failOnError: true, insert: true, flush: true)

        println "portfolio.id = $portfolio.id"

        Date da = DateUtils.Date("1/1/2007");
        Date a = DateUtils.today();
        Instrument stock = YahooUtils.downloadYahooData("AAPL", da, a);
        stock.save(failOnError: true, insert: true, flush: true)

        println "stock.id = $stock.id"

        tradeService.add(portfolio, stock);

        //String password = springSecurityService.encodePassword('password')

//        [jeff: 'Jeff Brown', graeme: 'Graeme Rocher', burt: 'Burt Beckwith', peter: 'Peter Ledbrook'].each { userName, realName ->
//            def user = new Person(username: userName, realName: realName, password: password, enabled: true).save()
//            PersonAuthority.create user, userRole, true
    }
}
