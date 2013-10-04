package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.indicator.UserIndicators
import com.netnumeri.server.finance.ta.*
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import com.netnumeri.server.utils.StockUtils
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
            UserIndicators userIndicator = list.get(i);
            if (userIndicator.type == IndicatorEnum.SimpleMovingAverage) {

                userIndicator.indicator = new SMAIndicator(closes, "SMA-" + userIndicator.smoothing1, userIndicator.smoothing1);

            } else if (userIndicator.type == IndicatorEnum.WeightedMovingAverage) {

                userIndicator.indicator = new WMAIndicator(closes, "SMA-" + userIndicator.smoothing1, userIndicator.smoothing1);

            } else if (userIndicator.type == IndicatorEnum.SingularSpectrumTrend) {

            } else if (userIndicator.type == IndicatorEnum.SingularSpectrumPrediction) {

            } else if (userIndicator.type == IndicatorEnum.PriceChannelUpper) {

                userIndicator.indicator = new
                PriceChannelUpIndicator(closes, "PC-Upper-" + userIndicator.order + "-" + userIndicator.k,
                        userIndicator.order,
                        userIndicator.k);

            } else if (userIndicator.type == IndicatorEnum.PriceChannelLower) {

                userIndicator.indicator = new
                PriceChannelLowerIndicator(closes, "PC-Lower-" + userIndicator.order + "-" + userIndicator.k,
                        userIndicator.order,
                        userIndicator.k);

            } else if (userIndicator.type == IndicatorEnum.UpperBollingerBand) {

                userIndicator.indicator = new
                BollingerBandUpIndicator(closes, "BB-Upper" + userIndicator.length + "-" + userIndicator.deviation,
                        userIndicator.length,
                        userIndicator.deviation);

            } else if (userIndicator.type == IndicatorEnum.LowerBollingerBand) {

                userIndicator.indicator = new
                BollingerBandLowerIndicator(closes, "BB-Lower" + userIndicator.length + "-" + userIndicator.deviation,
                        userIndicator.length,
                        userIndicator.deviation);

            } else if (userIndicator.type == IndicatorEnum.SimpleMovingVariance) {

                userIndicator.indicator = new SimpleMovingVarianceIndicator(
                        closes, "Simple Moving variance-" + userIndicator.order,
                        userIndicator.order);

            } else if (userIndicator.type == IndicatorEnum.Momentum) {

                userIndicator.indicator =
                    new MomentumPctPeriodIndicator(stockInstance, "Momentum-" + userIndicator.period, userIndicator.period)

            } else if (userIndicator.type == IndicatorEnum.MACD) {

                userIndicator.indicator =
                    new MACDIndicator(closes, "MACD-" + userIndicator.period,
                            userIndicator.smoothing1, userIndicator.smoothing2)

            } else if (userIndicator.type == IndicatorEnum.kMACDSignal) {

                userIndicator.indicator =
                    new MACDSignal(closes, "MACD-" + userIndicator.period,
                            userIndicator.smoothing1, userIndicator.smoothing2, userIndicator.smoothing3)

            } else if (userIndicator.type == IndicatorEnum.RateOfChange) {

            } else if (userIndicator.type == IndicatorEnum.RelativeStrengthIndex) {

            } else if (userIndicator.type == IndicatorEnum.RelativeStrengthIndex2) {

            } else if (userIndicator.type == IndicatorEnum.CommodityChannelIndicator) {

            } else if (userIndicator.type == IndicatorEnum.Oscillator) {

            } else if (userIndicator.type == IndicatorEnum.PriceChannelR) {

            } else if (userIndicator.type == IndicatorEnum.TrueChange) {

            } else if (userIndicator.type == IndicatorEnum.AverageTrueChange) {

            } else if (userIndicator.type == IndicatorEnum.MoneyFlowOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.AccumulateDistributionOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.ChaikinOscillatorOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.ChaikinMoneyFlowOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.AroonOscillatorOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.AroonDownOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.AroonUpOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.TrueRangePeriod) {

            } else if (userIndicator.type == IndicatorEnum.kFastStochasticPeriod) {

            } else if (userIndicator.type == IndicatorEnum.dStochastic) {

            } else if (userIndicator.type == IndicatorEnum.DStochasticSmoothed) {

            } else if (userIndicator.type == IndicatorEnum.ChaikinVolatility) {

            } else if (userIndicator.type == IndicatorEnum.kMACDSignal) {

            } else if (userIndicator.type == IndicatorEnum.PlusDirectionalMovementPeriod) {

            } else if (userIndicator.type == IndicatorEnum.RateOfChangePeriod) {

            } else if (userIndicator.type == IndicatorEnum.RateOfChangePeriod) {

            } else if (userIndicator.type == IndicatorEnum.Kairi) {

            } else if (userIndicator.type == IndicatorEnum.ExponentiallyWeightedMovingAverage) {

            } else if (userIndicator.type == IndicatorEnum.TriangularMovingAverage) {

            } else if (userIndicator.type == IndicatorEnum.LinearlyWeightedMovingAverage) {

            } else if (userIndicator.type == IndicatorEnum.GeometricMovingAverage) {

            } else if (userIndicator.type == IndicatorEnum.MomentumPctPeriod) {

            } else if (userIndicator.type == IndicatorEnum.CommodityChannelIndexOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.MarketFacilitationIndexOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.BalanceOfPowerOverPeriod) {

            } else if (userIndicator.type == IndicatorEnum.PriceActionOverPeriod) {

            }
        }

        stockInstance.snapshot = YahooUtils.getCompanySnapshot(stockInstance.name);

        TimeSeries series = stockInstance.closeSeries()

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
