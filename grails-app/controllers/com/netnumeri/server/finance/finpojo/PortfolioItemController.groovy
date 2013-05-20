package com.netnumeri.server.finance.finpojo

import org.springframework.dao.DataIntegrityViolationException

class PortfolioItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [portfolioItemInstanceList: PortfolioItem.list(params), portfolioItemInstanceTotal: PortfolioItem.count()]
    }

    def create() {
        [portfolioItemInstance: new PortfolioItem(params)]
    }

    def save() {
        def portfolioItemInstance = new PortfolioItem(params)
        if (!portfolioItemInstance.save(flush: true)) {
            render(view: "create", model: [portfolioItemInstance: portfolioItemInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), portfolioItemInstance.id])
        redirect(action: "show", id: portfolioItemInstance.id)
    }

    def show(Long id) {
        def portfolioItemInstance = PortfolioItem.get(id)
        if (!portfolioItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), id])
            redirect(action: "list")
            return
        }

        [portfolioItemInstance: portfolioItemInstance]
    }

    def edit(Long id) {
        def portfolioItemInstance = PortfolioItem.get(id)
        if (!portfolioItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), id])
            redirect(action: "list")
            return
        }

        [portfolioItemInstance: portfolioItemInstance]
    }

    def update(Long id, Long version) {
        def portfolioItemInstance = PortfolioItem.get(id)
        if (!portfolioItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (portfolioItemInstance.version > version) {
                portfolioItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'portfolioItem.label', default: 'PortfolioItem')] as Object[],
                          "Another user has updated this PortfolioItem while you were editing")
                render(view: "edit", model: [portfolioItemInstance: portfolioItemInstance])
                return
            }
        }

        portfolioItemInstance.properties = params

        if (!portfolioItemInstance.save(flush: true)) {
            render(view: "edit", model: [portfolioItemInstance: portfolioItemInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), portfolioItemInstance.id])
        redirect(action: "show", id: portfolioItemInstance.id)
    }

    def delete(Long id) {
        def portfolioItemInstance = PortfolioItem.get(id)
        if (!portfolioItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), id])
            redirect(action: "list")
            return
        }

        try {
            portfolioItemInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'portfolioItem.label', default: 'PortfolioItem'), id])
            redirect(action: "show", id: id)
        }
    }
}
