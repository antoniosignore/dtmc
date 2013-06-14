import com.dtmc.security.Role
import com.dtmc.security.UserBean
import com.dtmc.security.UserRole

class BootStrap {

    def portfolioService

    def init = { servletContext ->
        createData()
    }

    def destroy = {
    }

    private void createData() {

//        Portfolio portfolio = new Portfolio("SMA crossing", "desc", 10000)
//        portfolio.save(failOnError: true, insert: true, flush: true)
//
//        println "portfolio.id = $portfolio.id"
//
//        Date da = DateUtils.Date("1/1/2007");
//        Date a = DateUtils.today();
//        Instrument stock = YahooUtils.downloadYahooData("AAPL", "desc", da, a);
//        stock.save(failOnError: true, insert: true, flush: true)
//
//        println "stock.id = $stock.id"
//
//        portfolioService.buy(portfolio, stock, 100);
//
//        portfolio.save(failOnError: true, insert: true, flush: true);


        def superuserRole = new Role(authority: 'ROLE_SUPERUSER').save(flush: true)
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def superUser = new UserBean(username: 'superuser', enabled: true, password: 'dtmc')
        superUser.save(flush: true)
        UserRole.create superUser, superuserRole, true

        def adminUser = new UserBean(username: 'admin', enabled: true, password: 'dtmc')
        adminUser.save(flush: true)
        UserRole.create adminUser, adminRole, true

        def user = new UserBean(username: 'user', enabled: true, password: 'dtmc')
        user.save(flush: true)
        UserRole.create user, userRole, true

        assert UserBean.count() == 3
        assert Role.count() == 3
        assert UserRole.count() == 3

    }
}
