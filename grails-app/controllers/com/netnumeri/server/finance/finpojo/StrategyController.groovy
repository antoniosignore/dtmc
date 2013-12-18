package com.netnumeri.server.finance.finpojo

import com.netnumeri.server.finance.indicator.UserIndicators

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StrategyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def list() {
        redirect(action: "index", params: params)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Strategy.list(params), model:[strategyInstanceCount: Strategy.count()]
    }

    def show(Strategy strategyInstance) {
        respond strategyInstance
    }

    def create() {
        respond new Strategy(params), model:[userIndicatorsInstanceList: UserIndicators.list()]
    }

    @Transactional
    def save(Strategy strategyInstance) {
        if (strategyInstance == null) {
            notFound()
            return
        }

        if (strategyInstance.hasErrors()) {
            respond strategyInstance.errors, view:'create'
            return
        }

        strategyInstance.save flush:true, failOnError: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'strategyInstance.label', default: 'Strategy'), strategyInstance.id])
                redirect strategyInstance
            }
            '*' { respond strategyInstance, [status: CREATED] }
        }
    }

    def edit(Strategy strategyInstance) {
        respond strategyInstance
    }

    @Transactional
    def update(Strategy strategyInstance) {
        if (strategyInstance == null) {
            notFound()
            return
        }

        if (strategyInstance.hasErrors()) {
            respond strategyInstance.errors, view:'edit'
            return
        }

        strategyInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Strategy.label', default: 'Strategy'), strategyInstance.id])
                redirect strategyInstance
            }
            '*'{ respond strategyInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Strategy strategyInstance) {

        if (strategyInstance == null) {
            notFound()
            return
        }

        strategyInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Strategy.label', default: 'Strategy'), strategyInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'strategyInstance.label', default: 'Strategy'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
