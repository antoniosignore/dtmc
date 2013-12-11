package com.netnumeri.server.finance.finpojo



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TradeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Trade.list(params), model:[tradeInstanceCount: Trade.count()]
    }

    def show(Trade tradeInstance) {
        respond tradeInstance
    }

    def create() {
        respond new Trade(params)
    }

    @Transactional
    def save(Trade tradeInstance) {
        if (tradeInstance == null) {
            notFound()
            return
        }

        if (tradeInstance.hasErrors()) {
            respond tradeInstance.errors, view:'create'
            return
        }

        tradeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tradeInstance.label', default: 'Trade'), tradeInstance.id])
                redirect tradeInstance
            }
            '*' { respond tradeInstance, [status: CREATED] }
        }
    }

    def edit(Trade tradeInstance) {
        respond tradeInstance
    }

    @Transactional
    def update(Trade tradeInstance) {
        if (tradeInstance == null) {
            notFound()
            return
        }

        if (tradeInstance.hasErrors()) {
            respond tradeInstance.errors, view:'edit'
            return
        }

        tradeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Trade.label', default: 'Trade'), tradeInstance.id])
                redirect tradeInstance
            }
            '*'{ respond tradeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Trade tradeInstance) {

        if (tradeInstance == null) {
            notFound()
            return
        }

        tradeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Trade.label', default: 'Trade'), tradeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tradeInstance.label', default: 'Trade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
