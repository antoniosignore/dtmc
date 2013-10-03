package com.netnumeri.server.finance.indicator

import org.springframework.dao.DataIntegrityViolationException

class SimpleMovingAverageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [simpleMovingAverageUserIndicatorInstanceList: SimpleMovingAverage.list(params), simpleMovingAverageUserIndicatorInstanceTotal: SimpleMovingAverage.count()]
    }

    def create() {
        [simpleMovingAverageUserIndicatorInstance: new SimpleMovingAverage(params)]
    }

    def save() {
        def simpleMovingAverageUserIndicatorInstance = new SimpleMovingAverage(params)
        if (!simpleMovingAverageUserIndicatorInstance.save(flush: true)) {
            render(view: "create", model: [simpleMovingAverageUserIndicatorInstance: simpleMovingAverageUserIndicatorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), simpleMovingAverageUserIndicatorInstance.id])
        redirect(action: "show", id: simpleMovingAverageUserIndicatorInstance.id)
    }

    def show(Long id) {
        def simpleMovingAverageUserIndicatorInstance = SimpleMovingAverage.get(id)
        if (!simpleMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), id])
            redirect(action: "list")
            return
        }

        [simpleMovingAverageUserIndicatorInstance: simpleMovingAverageUserIndicatorInstance]
    }

    def edit(Long id) {
        def simpleMovingAverageUserIndicatorInstance = SimpleMovingAverage.get(id)
        if (!simpleMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), id])
            redirect(action: "list")
            return
        }

        [simpleMovingAverageUserIndicatorInstance: simpleMovingAverageUserIndicatorInstance]
    }

    def update(Long id, Long version) {
        def simpleMovingAverageUserIndicatorInstance = SimpleMovingAverage.get(id)
        if (!simpleMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (simpleMovingAverageUserIndicatorInstance.version > version) {
                simpleMovingAverageUserIndicatorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage')] as Object[],
                        "Another user has updated this SimpleMovingAverage while you were editing")
                render(view: "edit", model: [simpleMovingAverageUserIndicatorInstance: simpleMovingAverageUserIndicatorInstance])
                return
            }
        }

        simpleMovingAverageUserIndicatorInstance.properties = params

        if (!simpleMovingAverageUserIndicatorInstance.save(flush: true)) {
            render(view: "edit", model: [simpleMovingAverageUserIndicatorInstance: simpleMovingAverageUserIndicatorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), simpleMovingAverageUserIndicatorInstance.id])
        redirect(action: "show", id: simpleMovingAverageUserIndicatorInstance.id)
    }

    def delete(Long id) {
        def simpleMovingAverageUserIndicatorInstance = SimpleMovingAverage.get(id)
        if (!simpleMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), id])
            redirect(action: "list")
            return
        }

        try {
            simpleMovingAverageUserIndicatorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'simpleMovingAverageUserIndicator.label', default: 'SimpleMovingAverage'), id])
            redirect(action: "show", id: id)
        }
    }
}
