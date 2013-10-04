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
            UserIndicators ui = list.get(i);
            if (ui.type == IndicatorEnum.SimpleMovingAverage) {

                ui.indicator = new SMAIndicator(closes, "SMA-" + ui.smoothing1, ui.smoothing1);

            } else if (ui.type == IndicatorEnum.WeightedMovingAverage) {

                ui.indicator = new WMAIndicator(closes, "SMA-" + ui.smoothing1, ui.smoothing1);

            } else if (ui.type == IndicatorEnum.SingularSpectrumTrend) {

            } else if (ui.type == IndicatorEnum.SingularSpectrumPrediction) {

            } else if (ui.type == IndicatorEnum.PriceChannelUpper) {

                ui.indicator = new
                PriceChannelUpIndicator(closes, "PC-Upper-" + ui.order + "-" + ui.k,
                        ui.order,
                        ui.k);

            } else if (ui.type == IndicatorEnum.PriceChannelLower) {

                ui.indicator = new
                PriceChannelLowerIndicator(closes, "PC-Lower-" + ui.order + "-" + ui.k,
                        ui.order,
                        ui.k);

            } else if (ui.type == IndicatorEnum.UpperBollingerBand) {

                ui.indicator = new
                BollingerBandUpIndicator(closes, "BB-Upper" + ui.length + "-" + ui.deviation,
                        ui.length,
                        ui.deviation);

            } else if (ui.type == IndicatorEnum.LowerBollingerBand) {

                ui.indicator = new
                BollingerBandLowerIndicator(closes, "BB-Lower" + ui.length + "-" + ui.deviation,
                        ui.length,
                        ui.deviation);

            } else if (ui.type == IndicatorEnum.SimpleMovingVariance) {

                ui.indicator = new SimpleMovingVarianceIndicator(
                        closes, "Simple Moving variance-" + ui.order,
                        ui.order);

            } else if (ui.type == IndicatorEnum.Momentum) {

                ui.indicator =
                    new MomentumPctPeriodIndicator(stockInstance, "Momentum-" + ui.period, ui.period)

            } else if (ui.type == IndicatorEnum.MACD) {

                ui.indicator =
                    new MACDIndicator(closes, "MACD-" + ui.period,
                            ui.smoothing1, ui.smoothing2)

            } else if (ui.type == IndicatorEnum.kMACDSignal) {

                ui.indicator =
                    new MACDSignal(closes, "MACD-Signal" + ui.period,
                            ui.smoothing1, ui.smoothing2, ui.smoothing3)

            } else if (ui.type == IndicatorEnum.RateOfChange) {

                ui.indicator =
                    new RateOfChangeOverPeriodIndicator(stockInstance, "MACD-" + ui.period, ui.period)

            } else if (ui.type == IndicatorEnum.RelativeStrengthIndex) {

                ui.indicator =
                    new RSIIndicator(closes, "RSI-" + ui.period,
                            ui.period)

            } else if (ui.type == IndicatorEnum.RelativeStrengthIndex2) {

                ui.indicator =
                    new RSI2Indicator(closes, "RSI-" + ui.period, ui.period)

            } else if (ui.type == IndicatorEnum.CommodityChannelIndicator) {

                ui.indicator =
                    new CommodityChannelIndicator(closes, "CCI-" + ui.order, ui.order)

            } else if (ui.type == IndicatorEnum.Oscillator) {

//                userIndicator.indicator =
//                    new Os(closes, "PCI-" + userIndicator.order + "-" + userIndicator.k, userIndicator.order, userIndicator.k)

            } else if (ui.type == IndicatorEnum.PriceChannelUpper) {

                ui.indicator =
                    new PriceChannelUpIndicator(closes, "PCI-" + ui.order + "-" + ui.k, ui.order, ui.k)

            } else if (ui.type == IndicatorEnum.PriceChannelLower) {

                ui.indicator =
                    new PriceChannelLowerIndicator(closes, "PCI-" + ui.order + "-" + ui.k, ui.order, ui.k)

            } else if (ui.type == IndicatorEnum.TrueChange) {

                ui.indicator =
                    new TrueRangeOverPeriodIndicator(closes, "TrueRange-" + ui.period)

//            } else if (ui.type == IndicatorEnum.AverageTrueChange) {
//
//                ui.indicator =
//                    new TrueRangeOverPeriodIndicator(closes, "TrueRange-" + ui.period)

            } else if (ui.type == IndicatorEnum.MoneyFlowOverPeriod) {

                ui.indicator =
                    new MoneyFlowOverPeriodIndicator(stockInstance, "Money flow-" + ui.period, ui.period)

            } else if (ui.type == IndicatorEnum.AccumulateDistributionOverPeriod) {

                ui.indicator =
                    new AccumulateDistributionOverPeriodIndicator(stockInstance, "Accumulate Distribution-" + ui.smoothing1, ui.smoothing1)

            } else if (ui.type == IndicatorEnum.ChaikinOscillatorOverPeriod) {

                ui.indicator =
                    new ChaikinOscillatorOverPeriodIndicator(stockInstance, "ChaikinOscillatorOverPeriodIndicator-" + ui.smoothing1, ui.smoothing1)


            } else if (ui.type == IndicatorEnum.ChaikinMoneyFlowOverPeriod) {

                ui.indicator =
                    new ChaikinMoneyFlowOverPeriodIndicator(stockInstance, "ChaikinMoneyFlowOverPeriodIndicator-" + ui.smoothing1, ui.smoothing1)

            } else if (ui.type == IndicatorEnum.AroonOscillatorOverPeriod) {

                ui.indicator =
                    new AaronOscillatorOverPeriodIndicator(stockInstance, "AaronOscillatorOverPeriodIndicator-" + ui.period, ui.period)

            } else if (ui.type == IndicatorEnum.AroonDownOverPeriod) {

                ui.indicator =
                    new AaronDownOverPeriodIndicator(stockInstance, "Aaron Down Over Period Indicator-" + ui.period, ui.period)

            } else if (ui.type == IndicatorEnum.AroonUpOverPeriod) {

                ui.indicator =
                    new AaronUpOverPeriodIndicator(stockInstance, "Aaron Up Over Period Indicator-" + ui.period, ui.period)

            } else if (ui.type == IndicatorEnum.TrueRangePeriod) {

                ui.indicator =
                    new TrueRangeOverPeriodIndicator(stockInstance, "true Range Over Period Indicator-" + ui.period)

            } else if (ui.type == IndicatorEnum.kFastStochasticPeriod) {

            } else if (ui.type == IndicatorEnum.dStochastic) {

            } else if (ui.type == IndicatorEnum.DStochasticSmoothed) {

            } else if (ui.type == IndicatorEnum.ChaikinVolatility) {

            } else if (ui.type == IndicatorEnum.kMACDSignal) {

            } else if (ui.type == IndicatorEnum.PlusDirectionalMovementPeriod) {

            } else if (ui.type == IndicatorEnum.RateOfChangePeriod) {

            } else if (ui.type == IndicatorEnum.RateOfChangePeriod) {

            } else if (ui.type == IndicatorEnum.Kairi) {

            } else if (ui.type == IndicatorEnum.ExponentiallyWeightedMovingAverage) {

            } else if (ui.type == IndicatorEnum.TriangularMovingAverage) {

            } else if (ui.type == IndicatorEnum.LinearlyWeightedMovingAverage) {

            } else if (ui.type == IndicatorEnum.GeometricMovingAverage) {

            } else if (ui.type == IndicatorEnum.MomentumPctPeriod) {

            } else if (ui.type == IndicatorEnum.CommodityChannelIndexOverPeriod) {

            } else if (ui.type == IndicatorEnum.MarketFacilitationIndexOverPeriod) {

            } else if (ui.type == IndicatorEnum.BalanceOfPowerOverPeriod) {

            } else if (ui.type == IndicatorEnum.PriceActionOverPeriod) {

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
