package dtmc

import com.mrhaki.layout.domain.Product

class PageController {

    def index = {
        def productList = Product.list()
        render view: '/templates/homepage', model: [productList: productList]
    }
}
