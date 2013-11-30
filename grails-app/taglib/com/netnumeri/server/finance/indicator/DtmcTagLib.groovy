package com.netnumeri.server.finance.indicator

class DtmcTagLib {
    static namespace = 'dtmc'

    def indicators = { attrs ->
        def indicators = attrs.indicators
        indicators.eachWithIndex { indicator, counter ->
            out << g.render(template: '/stock/indicator', model: [indicator: indicator, indicatorCounter: counter])
        }
    }

    def users = { attrs ->
        def indicators = attrs.users
        users.eachWithIndex { user, counter ->
            out << g.render(template: '/stock/users', model: [user: user, userCounter: counter])
        }
    }


}