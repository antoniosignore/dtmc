package com.netnumeri.server.finance.finpojo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PortfolioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def list() {
        redirect(action: "index", params: params)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Portfolio.list(params), model:[portfolioInstanceCount: Portfolio.count()]
    }

    def show(Portfolio portfolioInstance) {
        respond portfolioInstance
    }

    def create() {
        respond new Portfolio(params)
    }

    @Transactional
    def save(Portfolio portfolioInstance) {
        if (portfolioInstance == null) {
            notFound()
            return
        }

        if (portfolioInstance.hasErrors()) {
            respond portfolioInstance.errors, view:'create'
            return
        }

        portfolioInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'portfolioInstance.label', default: 'Portfolio'), portfolioInstance.id])
                redirect portfolioInstance
            }
            '*' { respond portfolioInstance, [status: CREATED] }
        }
    }

    def edit(Portfolio portfolioInstance) {
        respond portfolioInstance
    }

    @Transactional
    def update(Portfolio portfolioInstance) {
        if (portfolioInstance == null) {
            notFound()
            return
        }

        if (portfolioInstance.hasErrors()) {
            respond portfolioInstance.errors, view:'edit'
            return
        }

        portfolioInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Portfolio.label', default: 'Portfolio'), portfolioInstance.id])
                redirect portfolioInstance
            }
            '*'{ respond portfolioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Portfolio portfolioInstance) {

        if (portfolioInstance == null) {
            notFound()
            return
        }

        portfolioInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Portfolio.label', default: 'Portfolio'), portfolioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'portfolioInstance.label', default: 'Portfolio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
