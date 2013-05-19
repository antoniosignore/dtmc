package com.netnumeri.server.finance.finpojo

import org.springframework.dao.DataIntegrityViolationException

class InstrumentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [instrumentInstanceList: Instrument.list(params), instrumentInstanceTotal: Instrument.count()]
    }

    def create() {
        [instrumentInstance: new Instrument(params)]
    }

    def save() {
        def instrumentInstance = new Instrument(params)
        if (!instrumentInstance.save(flush: true)) {
            render(view: "create", model: [instrumentInstance: instrumentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'instrument.label', default: 'Instrument'), instrumentInstance.id])
        redirect(action: "show", id: instrumentInstance.id)
    }

    def show(Long id) {
        def instrumentInstance = Instrument.get(id)
        if (!instrumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), id])
            redirect(action: "list")
            return
        }

        [instrumentInstance: instrumentInstance]
    }

    def edit(Long id) {
        def instrumentInstance = Instrument.get(id)
        if (!instrumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), id])
            redirect(action: "list")
            return
        }

        [instrumentInstance: instrumentInstance]
    }

    def update(Long id, Long version) {
        def instrumentInstance = Instrument.get(id)
        if (!instrumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (instrumentInstance.version > version) {
                instrumentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'instrument.label', default: 'Instrument')] as Object[],
                        "Another user has updated this Instrument while you were editing")
                render(view: "edit", model: [instrumentInstance: instrumentInstance])
                return
            }
        }

        instrumentInstance.properties = params

        if (!instrumentInstance.save(flush: true)) {
            render(view: "edit", model: [instrumentInstance: instrumentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'instrument.label', default: 'Instrument'), instrumentInstance.id])
        redirect(action: "show", id: instrumentInstance.id)
    }

    def delete(Long id) {
        def instrumentInstance = Instrument.get(id)
        if (!instrumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), id])
            redirect(action: "list")
            return
        }

        try {
            instrumentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'instrument.label', default: 'Instrument'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'instrument.label', default: 'Instrument'), id])
            redirect(action: "show", id: id)
        }
    }
}
