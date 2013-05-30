package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import org.junit.After
import org.junit.Before
import org.junit.Test

class StockTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {
        Date da = DateUtils.Date("1/1/2007");
        Date a = DateUtils.today();
        Instrument stock = YahooUtils.downloadYahooData("AAPL", da, a);
        stock.save(failOnError: true, insert: true, flush: true)

        println "stock.id = $stock.id"
    }
}
