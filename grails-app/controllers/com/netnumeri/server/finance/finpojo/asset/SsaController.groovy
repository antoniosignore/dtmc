package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.strategy.SSASignal
import com.netnumeri.server.finance.strategy.Strategy
import com.netnumeri.server.finance.ta.*
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.utils.StockUtils

class SsaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "show", params: params)
    }

    def show() {

        println "params.id = $params.id"
        println "params.range = $params.range"

        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
            redirect(action: "show")
            return
        }

        Date da = DateUtils.Date("11/1/2012");
        Date a = DateUtils.today();

        // last year
        StockUtils.refreshDaily(stockInstance, da, a);

        TimeSeries closeSeries = stockInstance.buildCloseSeries()
        closeSeries.normalize()

        stockInstance.indicators.put("normalized", new NormalizedSeriesIndicator(closeSeries, "Normalized"))

        List<Integer> components = [0]
        stockInstance.indicators.put("trend", new SSAComponentsIndicator(closeSeries, "SSA-0", 50, components))

        List<Integer> components1 = [1]
        stockInstance.indicators.put("comp1", new SSAComponentsIndicator(closeSeries, "SSA-0", 50, components1))

        List<Integer> components01 = [0,1]
        stockInstance.indicators.put("comp01", new SSAComponentsIndicator(closeSeries, "SSA-0", 50, components01))

        Strategy strategy = new SSASignal("test", stockInstance, da, a);
//        strategy.run();

        [
                startDate: da,
                endDate: a,
                stockInstance: stockInstance,
                strategyInstance: strategy
        ]
    }
}
