package dtmc


class ProductController {

    static defaultAction = 'list'

    def list = {
        def productList = Product.list()
        render view: '/templates/portfoliolist', model: [productList: productList]
    }

    def view = {
        def productId = params.id
        def product = Product.read(productId)
        def productList = Product.list()
        render view: '/templates/portfolioview', model: [product: product, productList: productList]
    }
}
