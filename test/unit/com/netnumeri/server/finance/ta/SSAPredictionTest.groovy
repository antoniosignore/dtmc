package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ssa.SSAAnalysis
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import com.netnumeri.server.utils.StockUtils
import junit.framework.TestCase
import org.apache.commons.io.FileUtils
import org.junit.Test

import java.text.ParseException

public class SSAPredictionTest extends TestCase {

    public static final String STOCK_TICKER = "SSRI"
    public static final int WINDOW = 100
    String dir = "/home/antonio/timeplot/"
    Stock stock
    TimeSeries closes


    @Test
    public void setUp() throws IOException, ParseException {

        Date da = DateUtils.Date("11/1/2009");
        Date a = DateUtils.today();
        stock = YahooUtils.downloadYahooData(STOCK_TICKER, "", da, a);
        closes = stock.getCloseSeries();
      //  FileUtils.writeStringToFile(new File(dir + "stock.txt"), closes.getTimeplotSeries())

//        a = DateUtils.Date("11/30/2010");
//        Stock mystock = YahooUtils.downloadYahooData(STOCK_TICKER, da, a);
//        TimeSeries mycloses = mystock.getCloseSeries();
//        FileUtils.writeStringToFile(new File(dir + "stock-orig.txt"), mycloses.getTimeplotSeries())
    }

    @Test
    public void testSSAPrediction() throws IOException, ParseException {

        SSAAnalysis analysis = new SSAAnalysis(closes.convertToList(), WINDOW)

        stock.indicators.put("SMA-" + 256, new SMAIndicator(closes, "SMA-" + 256, 256))

        def components = [0]
        Indicator ssa0 = new SSAComponentsIndicator(closes, "SSA-0", analysis, components);
        stock.indicators.put(ssa0.name, ssa0)

        components = [1]
        Indicator ssa1 = new SSAComponentsIndicator(closes, "SSA-1", analysis, components);
        stock.indicators.put(ssa1.name, ssa1)

//        components = [0,1]
//        Indicator ssa01 = new SSAComponentsIndicator(closes,"SSAStrategy-01", analysis, components);
//        stock.indicators.put(ssa01.name, ssa01)

        components = [2]
        Indicator ssa2 = new SSAComponentsIndicator(closes, "SSA-2", analysis, components);
        stock.indicators.put(ssa2.name, ssa2)

        components = [3]
        Indicator ssa3 = new SSAComponentsIndicator(closes, "SSA-3", analysis, components);
        stock.indicators.put(ssa3.name, ssa3)

        components = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
        Indicator ssa05 = new SSAComponentsIndicator(closes, "SSA-05", analysis, components);
        stock.indicators.put(ssa05.name, ssa05)

        Indicator ssa1Predict = new SSASeriesPredictionIndicator(closes, "SSA-0-predict", WINDOW, 3, components, 10);
        stock.indicators.put(ssa1Predict.name, ssa1Predict)

        StockUtils.printTimeplotIndicatorOnFile(dir, stock)
        // StockUtils.printRawIndicatorOnFile(stock, dir)

    }

}