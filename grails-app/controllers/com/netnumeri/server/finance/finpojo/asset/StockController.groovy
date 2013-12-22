package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.enums.PortfolioEnum
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.data.TransactionSeries
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.indicator.UserIndicators
import com.netnumeri.server.finance.strategy.BackTest
import com.netnumeri.server.finance.strategy.SMACrossover
import com.netnumeri.server.finance.strategy.Strategy
import com.netnumeri.server.finance.ta.*
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import com.netnumeri.server.utils.StockUtils
import dtmc.PortfolioService
import org.springframework.dao.DataIntegrityViolationException

class StockController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def gsonBuilder

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [stockInstanceList: Stock.list(params), stockInstanceTotal: Stock.count()]
    }

    def create() {
        [stockInstance: new Stock(params)]
    }

    def save() {
        def stockInstance = new Stock(params)
        if (!stockInstance.save(flush: true)) {
            render(view: "create", model: [stockInstance: stockInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stock.label', default: 'Stock'), stockInstance.id])
        redirect(action: "show", id: stockInstance.id)
    }

    def show() {
        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "list")
            return
        }

        Date da = DateUtils.Date("11/1/2012");
        Date a = DateUtils.today();

        // last year
        StockUtils.refreshDaily(stockInstance);

        // todo - add user
        List<UserIndicators> list = UserIndicators.list()

        TimeSeries closes = stockInstance.getSeries(FinConstants.CLOSE);

        for (int i = 0; i < list.size(); i++) {
            UserIndicators ui = list.get(i);
            if (ui.type == IndicatorEnum.SimpleMovingAverage) {

                ui.indicator = new SMAIndicator(closes, "Simple Moving Average", ui.integer1);

            } else if (ui.type == IndicatorEnum.PriceChannelUpper) {

                ui.indicator = new PriceChannelUpIndicator(closes, "PCUpper", ui.integer1, ui.double2);

            } else if (ui.type == IndicatorEnum.PriceChannelLower) {

                ui.indicator = new PriceChannelLowerIndicator(closes, "PCLower", ui.integer1, ui.double2);

            } else if (ui.type == IndicatorEnum.WeightedMovingAverage) {

                ui.indicator = new WMAIndicator(closes, "Weighted Moving Average", ui.integer1);

            } else if (ui.type == IndicatorEnum.SingularSpectrumFirstComponent) {

                List<Integer> components = [0]
                ui.indicator = new SSAComponentsIndicator(closes, "SSA-0", ui.integer1, components);

            } else if (ui.type == IndicatorEnum.SingularSpectrumSecondComponent) {

                List<Integer> components = [1]
                ui.indicator = new SSAComponentsIndicator(closes, "SSA-0", ui.integer1, components);

            } else if (ui.type == IndicatorEnum.SingularSpectrumFirstSecondComponent) {

                List<Integer> components = [0, 1]
                ui.indicator = new SSAComponentsIndicator(closes, "SSA-01", ui.integer1, components);

            } else if (ui.type == IndicatorEnum.SingularSpectrumPrediction) {

            } else if (ui.type == IndicatorEnum.UpperBollingerBand) {

                ui.indicator = new BollingerBandUpIndicator(closes, "BB-Upper", ui.integer1, ui.double1);

            } else if (ui.type == IndicatorEnum.LowerBollingerBand) {

                ui.indicator = new BollingerBandLowerIndicator(closes, "BB-Lower", ui.integer1, ui.double1);

            } else if (ui.type == IndicatorEnum.SimpleMovingVariance) {

                ui.indicator = new SimpleMovingVarianceIndicator(closes, "Simple Moving variance", ui.integer1);

            } else if (ui.type == IndicatorEnum.Momentum) {

                ui.indicator = new MomentumPctPeriodIndicator(closes, "Momentum", ui.integer1)

            } else if (ui.type == IndicatorEnum.MACD) {

                ui.indicator = new MACDIndicator(closes, "MACD", ui.integer1, ui.integer2)

            } else if (ui.type == IndicatorEnum.MACDSignal) {

                ui.indicator = new MACDSignal(closes, "MACD-Signal", ui.integer1, ui.integer2, ui.integer3)

            } else if (ui.type == IndicatorEnum.RateOfChange) {

                ui.indicator = new RateOfChangeOverPeriodIndicator(closes, "Rate of Change", ui.integer1)

            } else if (ui.type == IndicatorEnum.RelativeStrengthIndex) {

                ui.indicator = new RSIIndicator(closes, "RSI", ui.integer1)

            } else if (ui.type == IndicatorEnum.RelativeStrengthIndex2) {

                ui.indicator = new RSI2Indicator(closes, "RSI", ui.integer1)

            } else if (ui.type == IndicatorEnum.CommodityChannelIndicator) {

                ui.indicator = new CommodityChannelIndicator(closes, "CCI", ui.integer1)

            } else if (ui.type == IndicatorEnum.Oscillator) {

//                userIndicator.indicator =
//                    new Os(closes, "PCI-" + userIndicator.order + "-" + userIndicator.k, userIndicator.order, userIndicator.k)

            } else if (ui.type == IndicatorEnum.PriceChannelUpper) {

                ui.indicator = new PriceChannelUpIndicator(closes, "PCI", ui.integer1, ui.double2)

            } else if (ui.type == IndicatorEnum.PriceChannelLower) {

                ui.indicator = new PriceChannelLowerIndicator(closes, "PCI", ui.integer1, ui.double2)

            } else if (ui.type == IndicatorEnum.TrueChange) {

                ui.indicator = new TrueRangeOverPeriodIndicator(stockInstance, "TrueRange")

//            } else if (ui.type == IndicatorEnum.AverageTrueChange) {
//
//                ui.indicator =
//                    new TrueRangeOverPeriodIndicator(closes, "TrueRange")

            } else if (ui.type == IndicatorEnum.MoneyFlowOverPeriod) {

                ui.indicator = new MoneyFlowOverPeriodIndicator(stockInstance, "Money flow", ui.integer1)

            } else if (ui.type == IndicatorEnum.AccumulateDistributionOverPeriod) {

                ui.indicator = new AccumulateDistributionOverPeriodIndicator(stockInstance, "Accumulate Distribution", ui.integer1)

            } else if (ui.type == IndicatorEnum.ChaikinOscillatorOverPeriod) {

                ui.indicator = new ChaikinOscillatorOverPeriodIndicator(stockInstance, "ChaikinOscillatorOverPeriodIndicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.ChaikinMoneyFlowOverPeriod) {

                ui.indicator = new ChaikinMoneyFlowOverPeriodIndicator(stockInstance, "ChaikinMoneyFlowOverPeriodIndicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.AroonOscillatorOverPeriod) {

                ui.indicator = new AaronOscillatorOverPeriodIndicator(stockInstance, "AaronOscillatorOverPeriodIndicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.AroonDownOverPeriod) {

                ui.indicator = new AaronDownOverPeriodIndicator(stockInstance, "Aaron Down Over Period Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.AroonUpOverPeriod) {

                ui.indicator = new AaronUpOverPeriodIndicator(stockInstance, "Aaron Up Over Period Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.TrueRangePeriod) {

                ui.indicator = new TrueRangeOverPeriodIndicator(stockInstance, "true Range Over Period Indicator")

            } else if (ui.type == IndicatorEnum.kFastStochasticPeriod) {

                ui.indicator = new KFastStochasticIndicator(stockInstance, "K Fast Stochastic Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.dStochastic) {

                ui.indicator = new DStochasticIndicator(stockInstance, "D Stochastic Indicator", ui.integer1, ui.integer2, ui.integer3)

            } else if (ui.type == IndicatorEnum.DStochasticSmoothed) {

                ui.indicator = new DStochasticSmoothedIndicator(stockInstance, "D Stochastic Smoothed Indicator", ui.integer1, ui.integer2)

            } else if (ui.type == IndicatorEnum.ChaikinVolatility) {

                ui.indicator = new ChaikinVolatilityIndicator(stockInstance, "Chaikin Volatility Indicator", ui.integer1, ui.integer2)

            } else if (ui.type == IndicatorEnum.MACDSignal) {

                ui.indicator = new MACDSignal(closes, "MACD Signal Indicator", ui.integer1, ui.integer2, ui.integer3)

            } else if (ui.type == IndicatorEnum.PlusDirectionalMovementPeriod) {

                ui.indicator = new PlusDMIndicator(stockInstance, "Plus DMI Indicator")

            } else if (ui.type == IndicatorEnum.RateOfChangePeriod) {

                ui.indicator = new RateOfChangeOverPeriodIndicator(closes, "Rate of Change Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.Kairi) {

                ui.indicator = new KairiIndicator(stockInstance, "Kairi Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.MomentumPctPeriod) {

                ui.indicator = new MomentumPctPeriodIndicator(closes, "Momentum Pct Period Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.CommodityChannelIndexOverPeriod) {

                ui.indicator = new CommodityChannelIndicator(closes, "CCI", ui.integer1)

            } else if (ui.type == IndicatorEnum.MarketFacilitationIndexOverPeriod) {

                ui.indicator = new MarketFacilitationIndexIndicator(stockInstance, "Market Facilitation Index Indicator")

            } else if (ui.type == IndicatorEnum.BalanceOfPowerOverPeriod) {

                ui.indicator = new BalanceOfPowerIndicator(stockInstance, "Balance of Power Indicator")

            } else if (ui.type == IndicatorEnum.PriceActionOverPeriod) {

                ui.indicator = new PriceActionOverPeriodIndicator(stockInstance, "Price action over integer1 indicator")

            }
        }

        stockInstance.snapshot = YahooUtils.getCompanySnapshot(stockInstance.name);

//        TimeSeries series = stockInstance.closeSeries()

        Portfolio portfolio = new Portfolio("SMA crossing", "Description", 10000);
        portfolio.portfolioType = PortfolioEnum.Strategy
        portfolio.save(flush: true)

//        Date da = DateUtils.Date("1/1/2007");
//        Date a = DateUtils.today();

//        Instrument stock = YahooUtils.downloadYahooData("AAPL", "Apple Computers", da, a);

//        TimeSeries closes = stock.getSeries(FinConstants.CLOSE);
//        FileUtils.writeStringToFile(new File(dir + "stock.txt"), closes.getTimeplotSeries())

        stockInstance.indicators.put("upper", new SMAIndicator(closes, "SMA-" + 50, 50))
        stockInstance.indicators.put("lower", new SMAIndicator(closes, "SMA-" + 10, 10))

        PortfolioService tradeService = new PortfolioService();
        tradeService.add(portfolio, stockInstance);

        Strategy strategy = new SMACrossover("test", portfolio, da, a, 10000);
        strategy.run();

        TransactionSeries series = strategy.transactionSeries

        BackTest trader = new BackTest(strategy.transactionSeries, portfolio, 100000);
        double value = trader.test();

        println trader.toXMLString()

        portfolio.save(flush: true)


        // todo date in jqplot format
        String plot = StockUtils.getJqPlot(stockInstance)
        [
                startDate: da,
                endDate: a,
                stockInstance: stockInstance,
                indicators: list
        ]

    }

    def edit() {
        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "list")
            return
        }

        [stockInstance: stockInstance]
    }

    def update() {
        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (stockInstance.version > version) {
                stockInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'stock.label', default: 'Stock')] as Object[],
                        "Another user has updated this Stock while you were editing")
                render(view: "edit", model: [stockInstance: stockInstance])
                return
            }
        }

        stockInstance.properties = params

        if (!stockInstance.save(flush: true)) {
            render(view: "edit", model: [stockInstance: stockInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stock.label', default: 'Stock'), stockInstance.id])
        redirect(action: "show", id: stockInstance.id)
    }

    def delete() {
        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "list")
            return
        }

        try {
            stockInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def quotes() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);

        String s = null;
        try {
            s = YahooUtils.proxyYahooData("IBM", cal.getTime(), new Date());
        } catch (Exception e) {
        }

        render s
    }

}
