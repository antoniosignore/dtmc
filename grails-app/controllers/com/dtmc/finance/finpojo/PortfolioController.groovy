package com.dtmc.finance.finpojo

import arrested.ArrestedUser

//
import com.netnumeri.server.enums.PortfolioTypeEnum
import grails.converters.JSON
import grails.converters.XML
import arrested.ArrestedController
import java.text.SimpleDateFormat

class PortfolioController extends ArrestedController {

    def grailsApplication

    static allowedMethods = [show: "GET", list: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    def listing() {
        withFormat {
            html {
                render(view: "list")
            }
        }
    }

    def edit() {}

    def show(Long id) {
        if (id) {
            Portfolio instance = Portfolio.get(id)
            if (instance) {
                withFormat {
                    xml {
                        render instance as XML
                    }
                    json {
                        render instance as JSON
                    }
                }
            } else {
                renderNotFound(id, "${message(code: 'default.Portfolio.notfound.label', default: 'Portfolio not found')}")

            }
        } else {
            renderMissingParam("${message(code: 'default.id.missing.label', default: 'id missing')}")
        }
    }

    def list() {
        def instances = Portfolio.list()
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
            Portfolio instance = new Portfolio()

            if (data.description) instance.description = data.description
            if (data.name) instance.name = data.name
            if (data.portfolioType) instance.portfolioType = PortfolioTypeEnum.valueOfName(data.portfolioType.get("name"))
            if (data.user) instance.user = ArrestedUser.get(data.user.id as Long)
            if (data.wealth) instance.wealth = data.wealth

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
            renderMissingParam("${message(code: 'default.Portfolio.missing.label', default: 'Portfolio missing')}")
        }
    }

    def update() {
        if (params.instance) {
            def data = JSON.parse(params.instance)
            Portfolio instance = Portfolio.get(data.id as Long)
            if (instance) {
                if (data.description) instance.description = data.description
                if (data.name) instance.name = data.name

                if (data.portfolioType) instance.portfolioType = PortfolioTypeEnum.valueOfName(data.portfolioType.get("name"))
                if (data.user) instance.user = ArrestedUser.get(data.user.id as Long)

                if (data.wealth) instance.wealth = data.wealth
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
                renderNotFound(data.id, "${message(code: 'default.Portfolio.notfound.label', default: 'Portfolio not found')}")
            }
        } else {
            renderMissingParam("${message(code: 'default.Portfolio.missing.label', default: 'Portfolio missing')}")
        }
    }

    def delete(Long id) {
        if (id) {
            Portfolio instance = Portfolio.get(id)
            if (instance) {
                instance.delete(flush: true)
                renderSuccess(id, "${message(code: 'default.Portfolio.deleted.label', default: 'Portfolio deleted')}")
            } else {
                renderNotFound(id, "${message(code: 'default.Portfolio.notfound.label', default: 'Portfolio not found')}")
            }
        } else {
            renderMissingParam("${message(code: 'default.id.missing.label', default: 'id missing')}")
        }
    }

    private setDate(String d) {

        String dFormat = 'yyyy-MM-dd'
        return (new SimpleDateFormat(dFormat)).parse(d)
    }
}
