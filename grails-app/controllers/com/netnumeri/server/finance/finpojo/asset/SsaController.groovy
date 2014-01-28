package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.indicator.UserIndicators
import com.netnumeri.server.finance.ssa.SSAItem
import com.netnumeri.server.finance.ssa.SSAStudy
import com.netnumeri.server.finance.strategy.SMACrossoverSignal
import com.netnumeri.server.finance.strategy.Strategy
import com.netnumeri.server.finance.ta.*
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import com.netnumeri.server.utils.StockUtils
import org.springframework.dao.DataIntegrityViolationException

class SsaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "show", params: params)
    }

    def show() {

        println "params.id = $params.id"

        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "show")
            return
        }

        Date da = DateUtils.Date("11/1/2012");
        Date a = DateUtils.Date("8/16/2013");

//        Date a = DateUtils.today();

        // last year
        StockUtils.refreshDaily(stockInstance, da, a);

        TimeSeries closeSeries = stockInstance.buildCloseSeries()
        closeSeries.normalize()

        stockInstance.indicators.put("normalized", new NormalizedSeriesIndicator(closeSeries, "Normalized"))

        List<Integer> components = [0]
        stockInstance.indicators.put("trend", new SSAComponentsIndicator(closeSeries, "SSA-0", 50, components))

        List<Integer> components1 = [1]
        stockInstance.indicators.put("comp1", new SSAComponentsIndicator(closeSeries, "SSA-1", 50, components1))

        List<Integer> components01 = [0,1]
        stockInstance.indicators.put("comp01", new SSAComponentsIndicator(closeSeries, "SSA-01", 50, components01))

        [
                startDate: da,
                endDate: a,
                stockInstance: stockInstance
        ]
    }
}
