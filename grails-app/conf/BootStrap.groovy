import com.dtmc.club.Club
import com.dtmc.security.Role
import com.dtmc.security.UserBean
import com.dtmc.security.UserRole
import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.indicator.Attributes
import com.netnumeri.server.finance.indicator.UserIndicators
import com.netnumeri.server.finance.utils.DateUtils

class BootStrap {

    def portfolioService

    def init = { servletContext ->
        createData()
    }

    def destroy = {
    }

    private void createData() {

        if (Club.getAll() == null || Club.getAll().size() == 0) {

            Club club = new Club(name: 'mioclub').save(flush: true)

            Portfolio portfolio = new Portfolio("SMA crossing", "desc", 10000)
            portfolio.club = club
            portfolio.save(failOnError: true, insert: true, flush: true)

            println "portfolio.id = $portfolio.id"

            Date da = DateUtils.Date("1/1/2007");
            Date a = DateUtils.today();
//            Instrument stock = YahooUtils.downloadYahooData("AAPL", "desc", da, a);
            Stock aapl = new Stock("AAPL", "Apple Computers");
            aapl.save(failOnError: true, insert: true, flush: true)

            //        portfolioService.buy(portfolio, stock, 100);
            portfolio.save(failOnError: true, insert: true, flush: true);

            def superuserRole = new Role(authority: 'ROLE_SUPERUSER').save(flush: true)
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
            def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

            def superUser = new UserBean(username: 'superuser', enabled: true, password: 'cheese')
            superUser.save(flush: true, failOnError: true)
            UserRole.create superUser, superuserRole, true

            def adminUser = new UserBean(username: 'admin', enabled: true, password: 'cheese')
            adminUser.save(flush: true, failOnError: true)
            adminUser.club = club
            UserRole.create adminUser, adminRole, true

            def user = new UserBean(username: 'user', enabled: true, password: 'cheese')
            user.club = club
            user.save(flush: true, failOnError: true)
            UserRole.create user, userRole, true

            assert UserBean.count() == 3
            assert Role.count() == 3
            assert UserRole.count() == 3

            if (UserIndicators.getAll() == null || UserIndicators.getAll().size() == 0) {

                UserIndicators ui = new UserIndicators(user: adminUser, type: IndicatorEnum.SimpleMovingAverage, name: "sma10");
                ui.addToAttributes(new Attributes(name: "smoothing", value: 10))
                ui.save(flush: true, failOnError: true)

                UserIndicators ui2 = new UserIndicators(user: adminUser, type: IndicatorEnum.SimpleMovingAverage, name: "rsi14");
                ui2.addToAttributes(new Attributes(name: "smoothing", value: 14))
                ui2.save(flush: true, failOnError: true)
            }
        }
    }
}