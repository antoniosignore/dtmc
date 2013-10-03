package com.netnumeri.server.finance.indicator

import org.springframework.dao.DataIntegrityViolationException

class WeightedMovingAverageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [weightedMovingAverageUserIndicatorInstanceList: WeightedMovingAverage.list(params), weightedMovingAverageUserIndicatorInstanceTotal: WeightedMovingAverage.count()]
    }

    def create() {
        [weightedMovingAverageUserIndicatorInstance: new WeightedMovingAverage(params)]
    }

    def save() {
        def weightedMovingAverageUserIndicatorInstance = new WeightedMovingAverage(params)
        if (!weightedMovingAverageUserIndicatorInstance.save(flush: true)) {
            render(view: "create", model: [weightedMovingAverageUserIndicatorInstance: weightedMovingAverageUserIndicatorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), weightedMovingAverageUserIndicatorInstance.id])
        redirect(action: "show", id: weightedMovingAverageUserIndicatorInstance.id)
    }

    def show(Long id) {
        def weightedMovingAverageUserIndicatorInstance = WeightedMovingAverage.get(id)
        if (!weightedMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), id])
            redirect(action: "list")
            return
        }

        [weightedMovingAverageUserIndicatorInstance: weightedMovingAverageUserIndicatorInstance]
    }

    def edit(Long id) {
        def weightedMovingAverageUserIndicatorInstance = WeightedMovingAverage.get(id)
        if (!weightedMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), id])
            redirect(action: "list")
            return
        }

        [weightedMovingAverageUserIndicatorInstance: weightedMovingAverageUserIndicatorInstance]
    }

    def update(Long id, Long version) {
        def weightedMovingAverageUserIndicatorInstance = WeightedMovingAverage.get(id)
        if (!weightedMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (weightedMovingAverageUserIndicatorInstance.version > version) {
                weightedMovingAverageUserIndicatorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage')] as Object[],
                        "Another user has updated this WeightedMovingAverage while you were editing")
                render(view: "edit", model: [weightedMovingAverageUserIndicatorInstance: weightedMovingAverageUserIndicatorInstance])
                return
            }
        }

        weightedMovingAverageUserIndicatorInstance.properties = params

        if (!weightedMovingAverageUserIndicatorInstance.save(flush: true)) {
            render(view: "edit", model: [weightedMovingAverageUserIndicatorInstance: weightedMovingAverageUserIndicatorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), weightedMovingAverageUserIndicatorInstance.id])
        redirect(action: "show", id: weightedMovingAverageUserIndicatorInstance.id)
    }

    def delete(Long id) {
        def weightedMovingAverageUserIndicatorInstance = WeightedMovingAverage.get(id)
        if (!weightedMovingAverageUserIndicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), id])
            redirect(action: "list")
            return
        }

        try {
            weightedMovingAverageUserIndicatorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'weightedMovingAverageUserIndicator.label', default: 'WeightedMovingAverage'), id])
            redirect(action: "show", id: id)
        }
    }
}
