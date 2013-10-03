package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.indicator.*
import com.netnumeri.server.finance.ta.CommodityChannelIndicator
import com.netnumeri.server.finance.ta.Momentum
import com.netnumeri.server.finance.ta.SMAIndicator
import com.netnumeri.server.finance.ta.WMAIndicator
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
            if (userIndicator instanceof SimpleMovingAverage) {

                userIndicator.indicator = new SMAIndicator(closes, "SMA-" + userIndicator.smoothing, userIndicator.smoothing);

            } else if (userIndicator instanceof WeightedMovingAverage) {

                userIndicator.indicator = new WMAIndicator(closes, "SMA-" + userIndicator.smoothing, userIndicator.smoothing);

            } else if (userIndicator instanceof SingularSpectrumTrend) {

            } else if (userIndicator instanceof SingularSpectrumPrediction) {

            } else if (userIndicator instanceof PriceChannelUpper) {

            } else if (userIndicator instanceof PriceChannelLower) {

            } else if (userIndicator instanceof UpperBollingerBand) {

            } else if (userIndicator instanceof LowerBollingerBand) {

            } else if (userIndicator instanceof SimpleMovingVariance) {

            } else if (userIndicator instanceof Momentum) {

            } else if (userIndicator instanceof Macd) {

            } else if (userIndicator instanceof RateOfChange) {

            } else if (userIndicator instanceof RelativeStrengthIndex) {

            } else if (userIndicator instanceof RelativeStrengthIndex2) {

            } else if (userIndicator instanceof CommodityChannelIndicator) {

            } else if (userIndicator instanceof Oscillator) {

            } else if (userIndicator instanceof PriceChannelR) {

            } else if (userIndicator instanceof TrueChange) {

            } else if (userIndicator instanceof AverageTrueChange) {

            } else if (userIndicator instanceof MoneyFlowOverPeriod) {

            } else if (userIndicator instanceof AccumulateDistributionOverPeriod) {

            } else if (userIndicator instanceof ChaikinOscillatorOverPeriod) {

            } else if (userIndicator instanceof ChaikinMoneyFlowOverPeriod) {

            } else if (userIndicator instanceof AroonOscillatorOverPeriod) {

            } else if (userIndicator instanceof AroonDownOverPeriod) {

            } else if (userIndicator instanceof AroonUpOverPeriod) {

            } else if (userIndicator instanceof TrueRangePeriod) {

            } else if (userIndicator instanceof FastStochasticPeriodK) {

            } else if (userIndicator instanceof StochasticD) {

            } else if (userIndicator instanceof StochasticDSmoothed) {

            } else if (userIndicator instanceof ChaikinVolatility) {

            } else if (userIndicator instanceof MACDSignalK) {

            } else if (userIndicator instanceof PlusDirectionalMovementPeriod) {

            } else if (userIndicator instanceof RateOfChangePeriod) {

            } else if (userIndicator instanceof RateOfChangePeriod) {

            } else if (userIndicator instanceof Kairi) {

            } else if (userIndicator instanceof ExponentiallyWeightedMovingAverage) {

            } else if (userIndicator instanceof TriangularMovingAverage) {

            } else if (userIndicator instanceof LinearlyWeightedMovingAverage) {

            } else if (userIndicator instanceof GeometricMovingAverage) {

            } else if (userIndicator instanceof MomentumPctPeriod) {

            } else if (userIndicator instanceof CommodityChannelIndexOverPeriod) {

            } else if (userIndicator instanceof MarketFacilitationIndexOverPeriod) {

            } else if (userIndicator instanceof BalanceOfPowerOverPeriod) {

            } else if (userIndicator instanceof PriceActionOverPeriod) {

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
