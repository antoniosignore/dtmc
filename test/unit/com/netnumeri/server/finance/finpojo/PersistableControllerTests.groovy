package com.netnumeri.server.finance.finpojo

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(PersistableController)
@Mock(Persistable)
class PersistableControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/persistable/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.persistableInstanceList.size() == 0
        assert model.persistableInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.persistableInstance != null
    }

    void testSave() {
        controller.save()

        assert model.persistableInstance != null
        assert view == '/persistable/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/persistable/show/1'
        assert controller.flash.message != null
        assert Persistable.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/persistable/list'

        populateValidParams(params)
        def persistable = new Persistable(params)

        assert persistable.save() != null

        params.id = persistable.id

        def model = controller.show()

        assert model.persistableInstance == persistable
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/persistable/list'

        populateValidParams(params)
        def persistable = new Persistable(params)

        assert persistable.save() != null

        params.id = persistable.id

        def model = controller.edit()

        assert model.persistableInstance == persistable
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/persistable/list'

        response.reset()

        populateValidParams(params)
        def persistable = new Persistable(params)

        assert persistable.save() != null

        // test invalid parameters in update
        params.id = persistable.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/persistable/edit"
        assert model.persistableInstance != null

        persistable.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/persistable/show/$persistable.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        persistable.clearErrors()

        populateValidParams(params)
        params.id = persistable.id
        params.version = -1
        controller.update()

        assert view == "/persistable/edit"
        assert model.persistableInstance != null
        assert model.persistableInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/persistable/list'

        response.reset()

        populateValidParams(params)
        def persistable = new Persistable(params)

        assert persistable.save() != null
        assert Persistable.count() == 1

        params.id = persistable.id

        controller.delete()

        assert Persistable.count() == 0
        assert Persistable.get(persistable.id) == null
        assert response.redirectedUrl == '/persistable/list'
    }
}
