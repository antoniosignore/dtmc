package com.ibaralf


class StockQuoteTagLib {

	static namespace = 'yahoofinance'

	def yahooFinanceYQLService

	def quote = { attrs ->
		log.debug "\n\nINTAGLIB ${attrs.symbol} && ${attrs.data}"
		out << yahooFinanceYQLService.taglibQuote(attrs.symbol, attrs.stat)		
	}
}
