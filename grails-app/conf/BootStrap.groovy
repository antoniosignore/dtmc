import com.dtmc.club.Club
import com.dtmc.security.Role
import com.dtmc.security.UserBean
import com.dtmc.security.UserRole
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.indicator.Attributes
import com.netnumeri.server.finance.indicator.Indicators
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
                Indicators sma = new Indicators(code: "SimpleMovingAverage").save(flush: true)
                new Indicators(code: "WeightedMovingAverage").save(flush: true)
                new Indicators(code: "SingularSpectrumTrend").save(flush: true)
                new Indicators(code: "SingularSpectrumPrediction").save(flush: true)
                new Indicators(code: "PriceChannelUpper").save(flush: true)
                new Indicators(code: "PriceChannelLower").save(flush: true)
                new Indicators(code: "UpperBollingerBand").save(flush: true)
                new Indicators(code: "LowerBollingerBand").save(flush: true)
                new Indicators(code: "kVHF").save(flush: true)
                new Indicators(code: "SimpleMovingVariance").save(flush: true)
                new Indicators(code: "kSMD").save(flush: true)
                new Indicators(code: "kSMC").save(flush: true)
                new Indicators(code: "Momentum").save(flush: true)
                new Indicators(code: "To").save(flush: true)
                new Indicators(code: "Macd").save(flush: true)
                new Indicators(code: "RateOfChange").save(flush: true)
                new Indicators(code: "RSI").save(flush: true)
                new Indicators(code: "RSI2").save(flush: true)
                new Indicators(code: "kCCI").save(flush: true)
                new Indicators(code: "kKRI").save(flush: true)
                new Indicators(code: "kOSC").save(flush: true)
                new Indicators(code: "kPCR").save(flush: true)
                new Indicators(code: "kFI").save(flush: true)
                new Indicators(code: "kD").save(flush: true)
                new Indicators(code: "kK").save(flush: true)
                new Indicators(code: "kVA").save(flush: true)
                new Indicators(code: "kVAI").save(flush: true)
                new Indicators(code: "kCHO").save(flush: true)
                new Indicators(code: "TrueChange").save(flush: true)
                new Indicators(code: "AverageTrueChange").save(flush: true)
                new Indicators(code: "MoneyFlowOverPeriod").save(flush: true)
                new Indicators(code: "AccumulateDistributionOverPeriod").save(flush: true)
                new Indicators(code: "ChaikinOscillatorOverPeriod").save(flush: true)
                new Indicators(code: "ChaikinMoneyFlowOverPeriod").save(flush: true)
                new Indicators(code: "AroonOscillatorOverPeriod").save(flush: true)
                new Indicators(code: "AroonDownOverPeriod").save(flush: true)
                new Indicators(code: "AroonUpOverPeriod").save(flush: true)
                new Indicators(code: "TrueRangePeriod").save(flush: true)
                new Indicators(code: "kFastStochasticPeriod").save(flush: true)
                new Indicators(code: "dStochastic(").save(flush: true)
                new Indicators(code: "DStochasticSmoothed(").save(flush: true)
                new Indicators(code: "ChaikinVolatility(").save(flush: true)
                new Indicators(code: "kMACDSignal(").save(flush: true)
                new Indicators(code: "PlusDirectionalMovementPeriod").save(flush: true)
                new Indicators(code: "MinusDirectionalMovementPeriod").save(flush: true)
                new Indicators(code: "PriceActionOverPeriod").save(flush: true)
                new Indicators(code: "BalanceOfPowerOverPeriod").save(flush: true)
                new Indicators(code: "MarketFacilitationIndexOverPeriod").save(flush: true)
                new Indicators(code: "CommodityChannelIndexOverPeriod").save(flush: true)
                new Indicators(code: "MomentumPctPeriod").save(flush: true)
                new Indicators(code: "GeometricMovingAverage").save(flush: true)
                new Indicators(code: "LinearlyWeightedMovingAverage").save(flush: true)
                new Indicators(code: "TriangularMovingAverage").save(flush: true)
                new Indicators(code: "ExponentiallyWeightedMovingAverage").save(flush: true)
                new Indicators(code: "Kairi").save(flush: true)
                new Indicators(code: "RateOfChangePeriod").save(flush: true)

                UserIndicators ui = new UserIndicators(user: adminUser, indicator: sma, name: "sma10");
                ui.addToAttributes(new Attributes(name: "smoothing", value: 10))
                ui.save(flush: true, failOnError: true)

                UserIndicators ui2 = new UserIndicators(user: adminUser, indicator: sma, name: "rsi14");
                ui2.addToAttributes(new Attributes(name: "smoothing", value: 14))
                ui2.save(flush: true, failOnError: true)
            }
        }
    }
}