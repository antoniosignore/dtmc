package dtmc


class PageController {

    def springSecurityService
    def portfolioService

    def index = {
        def blah = springSecurityService.currentUser

        def portfolioList = portfolioService.list(springSecurityService.currentUser)

        render view: '/templates/homepage', model: [portfolioList: portfolioList]
    }
}
