package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ssa.SSAItem
import com.netnumeri.server.finance.ssa.SSAStudy
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import junit.framework.TestCase
import org.apache.commons.io.FileUtils
import org.junit.Ignore
import org.junit.Test

import java.text.ParseException

public class SSATest extends TestCase {

    Stock stock

    @Test
    public void setUp() throws IOException, ParseException {

        Date da = DateUtils.Date("11/1/2011");
        Date a = DateUtils.today();

        stock = YahooUtils.downloadYahooData("AAPL", "", da, a)

//        closes = stock.getCloseSeries()
//        stock.indicators.put("SMA-" + 50, new SMAIndicator(closes, "SMA-" + 50, 50, -1, -1, -1, -1))


    }

    @Ignore
    @Test
    public void test1() {

        double[] input = new double[20]
        input[0] = (1.0135518)
        input[1] =(-0.7113242 as Double)
        input[2] =(-0.3906069 as Double)
        input[3] =(1.565203)
        input[4] =(0.0439317)
        input[5] =(-1.1656093 as Double)
        input[6] =(1.0701692)
        input[7] =(1.0825379)
        input[8] =(-1.2239744 as Double)
        input[9] =(-0.0321446 as Double)
        input[10] =(1.1815997)
        input[11] =(-1.4969448 as Double)
        input[12] =(-0.7455299 as Double)
        input[13] =(1.0973884)
        input[14] =(-0.2188716 as Double)
        input[15] =(-1.0719573 as Double)
        input[16] =(0.9922009)
        input[17] =(0.4374216)
        input[18] =(-1.6880219 as Double)
        input[19] =(0.2609807 as Double)

        List<SSAItem> analyze = SSAStudy.analyze(input, 4)
        println "===> " + SSAStudy.getColumn (analyze.get(0).reconstructed, 0)

    }

    @Test
    public void test2() {

        TimeSeries returns = stock.buildReturnSeries(null, null)

        println "returns.getTimeplotSeries() = " + returns.getTimeplotSeries()

        List<SSAItem> analyze = SSAStudy.analyze(returns.convertToArray(), 50)
        println "===> " + SSAStudy.getColumn (analyze.get(0).reconstructed, 0)


//        double[] input = new double[20]
//        input[0] = 10
//        input[1] = 12
//        input[2] = 11
//        input[3] = 14
//        input[4] = 15
//        input[5] = 16
//        input[6] = 15
//        input[7] = 11
//        input[8] = 11
//        input[9] = 10
//        input[10] = 9
//        input[11] = 8
//        input[12] = 13
//        input[13] = 14
//        input[14] = 15
//        input[15] = 16
//        input[16] = 15
//        input[17] = 16
//        input[18] = 17
//        input[19] = 18
//
//        List<SSAItem> analyze = SSAStudy.analyze(input, 4)
//        println "===> " + SSAStudy.getColumn (analyze.get(0).reconstructed, 0)

    }

}