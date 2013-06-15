package com.dtmc.security

import org.springframework.dao.DataIntegrityViolationException

class UserBeanController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userBeanInstanceList: UserBean.list(params), userBeanInstanceTotal: UserBean.count()]
    }

    def create() {
        [userBeanInstance: new UserBean(params)]
    }

    def save() {
        def userBeanInstance = new UserBean(params)
        if (!userBeanInstance.save(flush: true)) {
            render(view: "create", model: [userBeanInstance: userBeanInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userBean.label', default: 'UserBean'), userBeanInstance.id])
        redirect(action: "show", id: userBeanInstance.id)
    }

    def show(Long id) {
        def userBeanInstance = UserBean.get(id)
        if (!userBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userBean.label', default: 'UserBean'), id])
            redirect(action: "list")
            return
        }

        [userBeanInstance: userBeanInstance]
    }

    def edit(Long id) {
        def userBeanInstance = UserBean.get(id)
        if (!userBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userBean.label', default: 'UserBean'), id])
            redirect(action: "list")
            return
        }

        [userBeanInstance: userBeanInstance]
    }

    def update(Long id, Long version) {
        def userBeanInstance = UserBean.get(id)
        if (!userBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userBean.label', default: 'UserBean'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userBeanInstance.version > version) {
                userBeanInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'userBean.label', default: 'UserBean')] as Object[],
                        "Another user has updated this UserBean while you were editing")
                render(view: "edit", model: [userBeanInstance: userBeanInstance])
                return
            }
        }

        userBeanInstance.properties = params

        if (!userBeanInstance.save(flush: true)) {
            render(view: "edit", model: [userBeanInstance: userBeanInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userBean.label', default: 'UserBean'), userBeanInstance.id])
        redirect(action: "show", id: userBeanInstance.id)
    }

    def delete(Long id) {
        def userBeanInstance = UserBean.get(id)
        if (!userBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userBean.label', default: 'UserBean'), id])
            redirect(action: "list")
            return
        }

        try {
            userBeanInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userBean.label', default: 'UserBean'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userBean.label', default: 'UserBean'), id])
            redirect(action: "show", id: id)
        }
    }
}
