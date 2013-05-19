package com.netnumeri.server.finance.finpojo.derivative.equity

import org.springframework.dao.DataIntegrityViolationException

class OptionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [optionInstanceList: Option.list(params), optionInstanceTotal: Option.count()]
    }

    def create() {
        [optionInstance: new Option(params)]
    }

    def save() {
        def optionInstance = new Option(params)
        if (!optionInstance.save(flush: true)) {
            render(view: "create", model: [optionInstance: optionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'option.label', default: 'Option'), optionInstance.id])
        redirect(action: "show", id: optionInstance.id)
    }

    def show(Long id) {
        def optionInstance = Option.get(id)
        if (!optionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'option.label', default: 'Option'), id])
            redirect(action: "list")
            return
        }

        [optionInstance: optionInstance]
    }

    def edit(Long id) {
        def optionInstance = Option.get(id)
        if (!optionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'option.label', default: 'Option'), id])
            redirect(action: "list")
            return
        }

        [optionInstance: optionInstance]
    }

    def update(Long id, Long version) {
        def optionInstance = Option.get(id)
        if (!optionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'option.label', default: 'Option'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (optionInstance.version > version) {
                optionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'option.label', default: 'Option')] as Object[],
                        "Another user has updated this Option while you were editing")
                render(view: "edit", model: [optionInstance: optionInstance])
                return
            }
        }

        optionInstance.properties = params

        if (!optionInstance.save(flush: true)) {
            render(view: "edit", model: [optionInstance: optionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'option.label', default: 'Option'), optionInstance.id])
        redirect(action: "show", id: optionInstance.id)
    }

    def delete(Long id) {
        def optionInstance = Option.get(id)
        if (!optionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'option.label', default: 'Option'), id])
            redirect(action: "list")
            return
        }

        try {
            optionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'option.label', default: 'Option'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'option.label', default: 'Option'), id])
            redirect(action: "show", id: id)
        }
    }
}
