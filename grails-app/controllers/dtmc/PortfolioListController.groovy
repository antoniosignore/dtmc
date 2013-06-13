package dtmc

import com.netnumeri.server.finance.finpojo.Portfolio


class PortfolioListController {

    def myview = {
        def productId = params.id
        def portfolio = Portfolio.read(productId)
        def portfolioList = Portfolio.list()
        render view: '/templates/portfolioview', model: [portfolio: portfolio, portfolioList: portfolioList]
    }
}
