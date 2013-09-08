package com.netnumeri.server.finance.indicator

class IndicatorsTagLib {
    static namespace = 'dtmc'

    def renderIndicators = { attrs ->
        def indicators = attrs.indicators
        indicators.eachWithIndex { indicator, counter ->
            out << g.render(template: '/stock/indicator', model: [indicator: indicator, indicatorCounter: counter])
        }
    }
}
