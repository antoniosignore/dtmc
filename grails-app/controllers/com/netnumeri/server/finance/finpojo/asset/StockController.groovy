package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
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

        Stock stock = YahooUtils.downloadYahooData(stockInstance.name, "", da, a);
        TimeSeries series = stock.closeSeries()

//        DailyGSON  dailygson = StockUtils.getDailyGSON(stockInstance)
//        def gson = gsonBuilder.setPrettyPrinting().create()
//        String json = gson.toJson(dailygson)

        [stockInstance: stockInstance, javascript: series.getJsonSeries()]

//        "[\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-01\",\n" +
//                "    \"close\": \"1\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-02\",\n" +
//                "    \"close\": \"2\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-03\",\n" +
//                "    \"close\": \"3\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-04\",\n" +
//                "    \"close\": \"4\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-05\",\n" +
//                "    \"close\": \"5\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-06\",\n" +
//                "    \"close\": \"4\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-07\",\n" +
//                "    \"close\": \"3\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-08\",\n" +
//                "    \"close\": \"4\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-09\",\n" +
//                "    \"close\": \"1.3\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"period\": \"2013-01-10\",\n" +
//                "    \"close\": \"1.5\"\n" +
//                "  }\n" +
//                "]"]
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
