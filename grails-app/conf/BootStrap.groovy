import com.dtmc.club.Club
import com.dtmc.security.Role
import com.dtmc.security.UserBean
import com.dtmc.security.UserRole
import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.asset.Stock
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

                UserIndicators userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.SimpleMovingAverage, name: "sma10", smoothing1: 10);
                userIndicators.save(flush: true, failOnError: true)

                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.SimpleMovingAverage, name: "sma50", smoothing1: 50);
                userIndicators.save(flush: true, failOnError: true)

                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.WeightedMovingAverage, name: "wma50", smoothing1: 50);
                userIndicators.save(flush: true, failOnError: true)

                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.SingularSpectrumFirstComponent, name: "ssa0", period: 50);
                userIndicators.save(flush: true, failOnError: true)

                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.SingularSpectrumSecondComponent, name: "ssa1", period: 50);
                userIndicators.save(flush: true, failOnError: true)

                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.SingularSpectrumFirstSecondComponent, name: "ssa01", period: 50);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new PriceChannelUpIndicator(closes, "PC-Upper", ui.indicatorOrder, ui.k);
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.PriceChannelUpper, name: "PC Upper", k: 50);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new PriceChannelLowerIndicator(closes, "PC-Lower", ui.indicatorOrder, ui.k);
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.PriceChannelLower, name: "PC Lower", k: 50);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new BollingerBandUpIndicator(closes, "BB-Upper", ui.length, ui.deviation);
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.UpperBollingerBand, name: "BB Upper", lenght: 10, deviation: 2);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new BollingerBandLowerIndicator(closes, "BB-Lower", ui.length, ui.deviation);
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.LowerBollingerBand, name: "BB Lower", lenght: 10, deviation: 2);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new SimpleMovingVarianceIndicator(closes, "Simple Moving variance", ui.indicatorOrder);
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.SimpleMovingVariance, name: "Simple Moving variance", indicatorOrder: 10);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new MomentumPctPeriodIndicator(stockInstance, "Momentum", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.Momentum, name: "Momentum", period: 10);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new MACDIndicator(closes, "MACD", ui.smoothing1, ui.smoothing2)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.MACD, name: "MACD", smoothing1: 13, smoothing2: 26);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new MACDSignal(closes, "MACD-Signal", ui.smoothing1, ui.smoothing2, ui.smoothing3)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.MACD, name: "MACD-Signal", smoothing1: 13, smoothing2: 26, smoothing3: 5);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new RateOfChangeOverPeriodIndicator(stockInstance, "MACD", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.MACDSignal, name: "MACD-Signal", smoothing1: 13, smoothing2: 26, smoothing3: 5);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new RSIIndicator(closes, "RSI", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.RelativeStrengthIndex, name: "Relative Strength Index", period: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new RSI2Indicator(closes, "RSI", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.RelativeStrengthIndex2, name: "Relative Strength Index 2", period: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new CommodityChannelIndicator(closes, "CCI", ui.indicatorOrder)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.CommodityChannelIndexOverPeriod, name: "Commodity Channel Index Over Period", indicatorOrder: 14);
                userIndicators.save(flush: true, failOnError: true)

                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.CommodityChannelIndicator, name: "Commodity Channel", indicatorOrder: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new TrueRangeOverPeriodIndicator(stockInstance, "TrueRange")

////                ui.indicator =
////                    new TrueRangeOverPeriodIndicator(closes, "TrueRange")

//                ui.indicator = new MoneyFlowOverPeriodIndicator(stockInstance, "Money flow", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.MoneyFlowOverPeriod, name: "MoneyFlow Over Period", period: 14);
                userIndicators.save(flush: true, failOnError: true)

                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.ChaikinMoneyFlowOverPeriod, name: "Chaikin MoneyFlow Over Period", smoothing1: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new AccumulateDistributionOverPeriodIndicator(stockInstance, "Accumulate Distribution", ui.smoothing1)

//                ui.indicator = new ChaikinOscillatorOverPeriodIndicator(stockInstance, "ChaikinOscillatorOverPeriodIndicator", ui.smoothing1)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.ChaikinOscillatorOverPeriod, name: "Chaikin Oscillator Over Period", smoothing1: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new AaronOscillatorOverPeriodIndicator(stockInstance, "AaronOscillatorOverPeriodIndicator", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.AroonOscillatorOverPeriod, name: "Aroon Oscillator Over Period", period: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new AaronDownOverPeriodIndicator(stockInstance, "Aaron Down Over Period Indicator", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.AroonDownOverPeriod, name: "Aaron Down Over Period", period: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new AaronUpOverPeriodIndicator(stockInstance, "Aaron Up Over Period Indicator", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.AroonUpOverPeriod, name: "Aaron Up Over Period", period: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new TrueRangeOverPeriodIndicator(stockInstance, "true Range Over Period Indicator")
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.TrueRangePeriod, name: "True Range Over Period");
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new KFastStochasticIndicator(stockInstance, "K Fast Stochastic Indicator", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.kFastStochasticPeriod, name: "K Fast Stochastic", period: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new DStochasticIndicator(stockInstance, "D Stochastic Indicator", ui.period, ui.method, ui.smoothing1)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.dStochastic, name: "D Stochastic Indicator", method: 1, period: 14, smoothing1: 10);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new DStochasticSmoothedIndicator(stockInstance, "D Stochastic Smoothed Indicator", ui.period, ui.smoothing1)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.DStochasticSmoothed, name: "D Stochastic Smoothed", period: 10, smoothing1: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new ChaikinVolatilityIndicator(stockInstance, "Chaikin Volatility Indicator", ui.smoothing1, ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.ChaikinVolatility, name: "Chaikin Volatility", smoothing1: 10, period: 14);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new PlusDMIndicator(stockInstance, "Plus DMI Indicator")
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.PlusDirectionalMovementPeriod, name: "Plus DMI Indicator");
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new RateOfChangeOverPeriodIndicator(stockInstance, "Rate of Change Indicator", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.RateOfChangePeriod, name: "Rate of Change Over Period", period: 10);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new KairiIndicator(stockInstance, "Kairi Indicator", ui.smoothing1)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.Kairi, name: "Kairi Indicator", smoothing1: 10);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new MomentumPctPeriodIndicator(stockInstance, "Momentum Pct Period Indicator", ui.period)
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.MomentumPctPeriod, name: "Momentum Pct Period Indicator", period: 10);
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new MarketFacilitationIndexIndicator(stockInstance, "Market Facilitation Index Indicator")
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.MarketFacilitationIndexOverPeriod, name: "Market Facilitation Index");
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new BalanceOfPowerIndicator(stockInstance, "Balance of Power Indicator")
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.BalanceOfPowerOverPeriod, name: "Balance of Power");
                userIndicators.save(flush: true, failOnError: true)

//                ui.indicator = new PriceActionOverPeriodIndicator(stockInstance, "Price action over period indicator")
                userIndicators = new UserIndicators(user: adminUser, type: IndicatorEnum.PriceActionOverPeriod, name: "Price action over period");
                userIndicators.save(flush: true, failOnError: true)


            }
        }
    }
}