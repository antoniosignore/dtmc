package com.netnumeri.server.finance.finpojo

import org.springframework.dao.DataIntegrityViolationException

class TransactionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [transactionInstanceList: Trade.list(params), transactionInstanceTotal: Trade.count()]
    }

    def create() {
        [transactionInstance: new Trade(params)]
    }

    def save() {
        def transactionInstance = new Trade(params)
        if (!transactionInstance.save(flush: true)) {
            render(view: "create", model: [transactionInstance: transactionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'transaction.label', default: 'Trade'), transactionInstance.id])
        redirect(action: "show", id: transactionInstance.id)
    }

    def show(Long id) {
        def transactionInstance = Trade.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Trade'), id])
            redirect(action: "list")
            return
        }

        [transactionInstance: transactionInstance]
    }

    def edit(Long id) {
        def transactionInstance = Trade.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Trade'), id])
            redirect(action: "list")
            return
        }

        [transactionInstance: transactionInstance]
    }

    def update(Long id, Long version) {
        def transactionInstance = Trade.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Trade'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (transactionInstance.version > version) {
                transactionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'transaction.label', default: 'Trade')] as Object[],
                        "Another user has updated this Trade while you were editing")
                render(view: "edit", model: [transactionInstance: transactionInstance])
                return
            }
        }

        transactionInstance.properties = params

        if (!transactionInstance.save(flush: true)) {
            render(view: "edit", model: [transactionInstance: transactionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'transaction.label', default: 'Trade'), transactionInstance.id])
        redirect(action: "show", id: transactionInstance.id)
    }

    def delete(Long id) {
        def transactionInstance = Trade.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Trade'), id])
            redirect(action: "list")
            return
        }

        try {
            transactionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'transaction.label', default: 'Trade'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'transaction.label', default: 'Trade'), id])
            redirect(action: "show", id: id)
        }
    }
}
