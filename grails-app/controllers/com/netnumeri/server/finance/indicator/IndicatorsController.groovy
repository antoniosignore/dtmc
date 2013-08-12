package com.netnumeri.server.finance.indicator

import org.springframework.dao.DataIntegrityViolationException

class IndicatorsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [indicatorsInstanceList: Indicators.list(params), indicatorsInstanceTotal: Indicators.count()]
    }

    def create() {
        [indicatorsInstance: new Indicators(params)]
    }

    def save() {
        def indicatorsInstance = new Indicators(params)
        if (!indicatorsInstance.save(flush: true)) {
            render(view: "create", model: [indicatorsInstance: indicatorsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'indicators.label', default: 'Indicators'), indicatorsInstance.id])
        redirect(action: "show", id: indicatorsInstance.id)
    }

    def show(Long id) {
        def indicatorsInstance = Indicators.get(id)
        if (!indicatorsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicators.label', default: 'Indicators'), id])
            redirect(action: "list")
            return
        }

        [indicatorsInstance: indicatorsInstance]
    }

    def edit(Long id) {
        def indicatorsInstance = Indicators.get(id)
        if (!indicatorsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicators.label', default: 'Indicators'), id])
            redirect(action: "list")
            return
        }

        [indicatorsInstance: indicatorsInstance]
    }

    def update(Long id, Long version) {
        def indicatorsInstance = Indicators.get(id)
        if (!indicatorsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicators.label', default: 'Indicators'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (indicatorsInstance.version > version) {
                indicatorsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'indicators.label', default: 'Indicators')] as Object[],
                        "Another user has updated this Indicators while you were editing")
                render(view: "edit", model: [indicatorsInstance: indicatorsInstance])
                return
            }
        }

        indicatorsInstance.properties = params

        if (!indicatorsInstance.save(flush: true)) {
            render(view: "edit", model: [indicatorsInstance: indicatorsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicators.label', default: 'Indicators'), indicatorsInstance.id])
        redirect(action: "show", id: indicatorsInstance.id)
    }

    def delete(Long id) {
        def indicatorsInstance = Indicators.get(id)
        if (!indicatorsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicators.label', default: 'Indicators'), id])
            redirect(action: "list")
            return
        }

        try {
            indicatorsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'indicators.label', default: 'Indicators'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'indicators.label', default: 'Indicators'), id])
            redirect(action: "show", id: id)
        }
    }
}
