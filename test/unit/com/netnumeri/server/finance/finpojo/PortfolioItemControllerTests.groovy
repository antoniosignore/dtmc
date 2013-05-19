package com.netnumeri.server.finance.finpojo

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(PortfolioItemController)
@Mock(PortfolioItem)
class PortfolioItemControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/portfolioItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.portfolioItemInstanceList.size() == 0
        assert model.portfolioItemInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.portfolioItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.portfolioItemInstance != null
        assert view == '/portfolioItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/portfolioItem/show/1'
        assert controller.flash.message != null
        assert PortfolioItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/portfolioItem/list'

        populateValidParams(params)
        def portfolioItem = new PortfolioItem(params)

        assert portfolioItem.save() != null

        params.id = portfolioItem.id

        def model = controller.show()

        assert model.portfolioItemInstance == portfolioItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/portfolioItem/list'

        populateValidParams(params)
        def portfolioItem = new PortfolioItem(params)

        assert portfolioItem.save() != null

        params.id = portfolioItem.id

        def model = controller.edit()

        assert model.portfolioItemInstance == portfolioItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/portfolioItem/list'

        response.reset()

        populateValidParams(params)
        def portfolioItem = new PortfolioItem(params)

        assert portfolioItem.save() != null

        // test invalid parameters in update
        params.id = portfolioItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/portfolioItem/edit"
        assert model.portfolioItemInstance != null

        portfolioItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/portfolioItem/show/$portfolioItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        portfolioItem.clearErrors()

        populateValidParams(params)
        params.id = portfolioItem.id
        params.version = -1
        controller.update()

        assert view == "/portfolioItem/edit"
        assert model.portfolioItemInstance != null
        assert model.portfolioItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/portfolioItem/list'

        response.reset()

        populateValidParams(params)
        def portfolioItem = new PortfolioItem(params)

        assert portfolioItem.save() != null
        assert PortfolioItem.count() == 1

        params.id = portfolioItem.id

        controller.delete()

        assert PortfolioItem.count() == 0
        assert PortfolioItem.get(portfolioItem.id) == null
        assert response.redirectedUrl == '/portfolioItem/list'
    }
}
