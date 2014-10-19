package com

class DtmcTagLib {
    static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

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
}
