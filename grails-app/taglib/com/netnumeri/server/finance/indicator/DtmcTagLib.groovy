package com.netnumeri.server.finance.indicator

class DtmcTagLib {
    static namespace = 'dtmc'

    def indicators = { attrs ->
        def indicators = attrs.indicators
        indicators.eachWithIndex { indicator, counter ->
            out << g.render(template: '/common/indicator', model: [indicator: indicator, indicatorCounter: counter])
        }
    }

    def stocks = { attrs ->
        def stockList = attrs.list
        stockList.eachWithIndex { stock, counter ->
            out << g.render(template: '/ssa/ssa', model: [stockInstance: stock, indicatorCounter: counter])
        }
    }

    def users = { attrs ->
        def indicators = attrs.users
        users.eachWithIndex { user, counter ->
            out << g.render(template: '/stock/users', model: [user: user, userCounter: counter])
        }
    }


}
