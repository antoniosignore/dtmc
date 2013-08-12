package com.netnumeri.server.finance.indicator

import org.springframework.dao.DataIntegrityViolationException

class AttributesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [attributesInstanceList: Attributes.list(params), attributesInstanceTotal: Attributes.count()]
    }

    def create() {
        [attributesInstance: new Attributes(params)]
    }

    def save() {
        def attributesInstance = new Attributes(params)
        if (!attributesInstance.save(flush: true)) {
            render(view: "create", model: [attributesInstance: attributesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'attributes.label', default: 'Attributes'), attributesInstance.id])
        redirect(action: "show", id: attributesInstance.id)
    }

    def show(Long id) {
        def attributesInstance = Attributes.get(id)
        if (!attributesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attributes.label', default: 'Attributes'), id])
            redirect(action: "list")
            return
        }

        [attributesInstance: attributesInstance]
    }

    def edit(Long id) {
        def attributesInstance = Attributes.get(id)
        if (!attributesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attributes.label', default: 'Attributes'), id])
            redirect(action: "list")
            return
        }

        [attributesInstance: attributesInstance]
    }

    def update(Long id, Long version) {
        def attributesInstance = Attributes.get(id)
        if (!attributesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attributes.label', default: 'Attributes'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (attributesInstance.version > version) {
                attributesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'attributes.label', default: 'Attributes')] as Object[],
                        "Another user has updated this Attributes while you were editing")
                render(view: "edit", model: [attributesInstance: attributesInstance])
                return
            }
        }

        attributesInstance.properties = params

        if (!attributesInstance.save(flush: true)) {
            render(view: "edit", model: [attributesInstance: attributesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'attributes.label', default: 'Attributes'), attributesInstance.id])
        redirect(action: "show", id: attributesInstance.id)
    }

    def delete(Long id) {
        def attributesInstance = Attributes.get(id)
        if (!attributesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attributes.label', default: 'Attributes'), id])
            redirect(action: "list")
            return
        }

        try {
            attributesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'attributes.label', default: 'Attributes'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'attributes.label', default: 'Attributes'), id])
            redirect(action: "show", id: id)
        }
    }
}
