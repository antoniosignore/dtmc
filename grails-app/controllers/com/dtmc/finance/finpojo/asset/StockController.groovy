package com.dtmc.finance.finpojo.asset

import com.netnumeri.server.finance.utils.DateUtils
import grails.converters.JSON
import grails.converters.XML
import arrested.ArrestedController
import java.text.SimpleDateFormat

class StockController extends ArrestedController {

    def grailsApplication
    def dailyService

    static allowedMethods = [show: "GET", list: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    def listing() {
        withFormat {
            html {
                render(view: "list")
            }
        }
    }

    def showing() {
        withFormat {
            html {
                render(view: "show")
            }
        }
    }
    def edit() {}

    def show(Long id) {


        if (id) {
            Stock instance = Stock.get(id)

            Date startDate = new GregorianCalendar(2011, Calendar.NOVEMBER, 1).getTime()
            Date endDate = DateUtils.today();
            dailyService.dailyFromYahoo (instance, startDate, endDate)

            if (instance) {
                withFormat {
                    xml {
                        render instance as XML
                    }
                    json {
                        render instance.toObject() as JSON
                    }
                }
            } else {
                renderNotFound(id, "${message(code: 'default.Stock.notfound.label', default: 'Stock not found')}")

            }
        } else {
            renderMissingParam("${message(code: 'default.id.missing.label', default: 'id missing')}")
        }
    }

    def list() {

        JSON.registerObjectMarshaller(Stock) { Stock stock ->
            return [
                    id         : stock.id,
                    name       : stock.name,
                    description: stock.description
            ]
        }

        def instances = Stock.list()
                withFormat {
            xml {
                render instances as XML
            }
            json {
                render instances as JSON
            }
        }
    }

    def save() {
        if (request.JSON.instance) {
            def data = request.JSON.instance
            Stock instance = new Stock()

            if (data.name) instance.name = data.name
            if (data.description) instance.description = data.description

            if (instance.save(flush: true)) {
                withFormat {
                    xml {
                        response.status = 200
                        render instance as XML
                    }
                    json {
                        response.status = 200
                        render instance as JSON
                    }
                }
            } else {
                render409orEdit(instance)
            }
        } else {
            renderMissingParam("${message(code: 'default.Stock.missing.label', default: 'Stock missing')}")
        }
    }

    def update() {
        if (params.instance) {
            def data = JSON.parse(params.instance)
            Stock instance = Stock.get(data.id as Long)
            if (instance) {

                if (data.name) instance.name = data.name
                if (data.description) instance.description = data.description

                if (instance.save(flush: true)) {
                    withFormat {
                        xml {
                            response.status = 200
                            render instance as XML
                        }
                        json {
                            response.status = 200
                            render instance as JSON
                        }
                    }
                } else {
                    render409orEdit(instance)
                }
            } else {
                renderNotFound(data.id, "${message(code: 'default.Stock.notfound.label', default: 'Stock not found')}")
            }
        } else {
            renderMissingParam("${message(code: 'default.Stock.missing.label', default: 'Stock missing')}")
        }
    }

    def delete(Long id) {
        if (id) {
            Stock instance = Stock.get(id)
            if (instance) {
                instance.delete(flush: true)
                renderSuccess(id, "${message(code: 'default.Stock.deleted.label', default: 'Stock deleted')}")
            } else {
                renderNotFound(id, "${message(code: 'default.Stock.notfound.label', default: 'Stock not found')}")
            }
        } else {
            renderMissingParam("${message(code: 'default.id.missing.label', default: 'id missing')}")
        }
    }

    private setDate(String d) {
        String dFormat = grailsApplication?.config.arrested.dateFormat ?: 'dd/MM/yyyy'
        return (new SimpleDateFormat(dFormat)).parse(d)
    }
}
