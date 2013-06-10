package dtmc


class PageController {

    def springSecurityService
    def portfolioService

    def index = {

        def portfolioList = portfolioService.listPortfolio()

        render view: '/templates/homepage', model: [portfolioList: portfolioList]
    }
}
