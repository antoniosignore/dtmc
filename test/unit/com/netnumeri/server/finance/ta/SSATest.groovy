package com.netnumeri.server.finance.ta

import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.ssa.SSAAnalysis
import com.netnumeri.server.finance.ssa.SSAStudy
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import com.netnumeri.server.utils.StockUtils
import junit.framework.TestCase
import org.apache.commons.io.FileUtils
import org.junit.Test

import java.text.ParseException

public class MyTest extends TestCase {

    String dir = "/home/antonio/timeplot/"
    Instrument stock
    TimeSeries closes

    @Test
    public void setUp() throws IOException, ParseException {

    }


    public static void main(String[] args) {

        def components = [0]
        List<Double> input = new ArrayList<Double>();
        input.add(1.0135518)
        input.add(-0.7113242 as Double)
        input.add(-0.3906069 as Double)
        input.add(1.565203)
        input.add(0.0439317)
        input.add(-1.1656093 as Double)
        input.add(1.0701692)
        input.add(1.0825379)
        input.add(-1.2239744 as Double)
        input.add(-0.0321446 as Double)
        input.add(1.1815997)
        input.add(-1.4969448 as Double)
        input.add(-0.7455299 as Double)
        input.add(1.0973884)
        input.add(-0.2188716 as Double)
        input.add(-1.0719573 as Double)
        input.add(0.9922009)
        input.add(0.4374216)
        input.add(-1.6880219 as Double)
        input.add(0.2609807 as Double)

        SSAAnalysis analysis = new SSAAnalysis(input, 4)

        List<Double> components1 = analysis.getEigenComponents(components);

        println "components1 = $components1"

    }

    @Test
    public void testSSAIndicators() throws IOException, ParseException {


        process "MMM"
        process "AA"
        process "AXP"
        process "T"
        process "BAC"
        process "BA"
        process "CAT"
        process "CVX"
        process "KO"
        process "CSCO"
        process "DIS"
        process "DD"
        process "XOM"
        process "GE"
        process "HPQ"
        process "HD"
        process "IBM"
        process "INTC"
        process "JNJ"
        process "JPM"
        process "KFT"
        process "MCD"
        process "MRK"
        process "MSFT"
        process "PFE"
        process "PG"
        process "TRV"
        process "UTX"
        process "VZ"
        process "WMT"
        process "BUD"
        process "UNH"

    }

    private void process(String ticker) {
        Date da = DateUtils.Date("11/1/2009");
        Date a = DateUtils.today();

        stock = YahooUtils.downloadYahooData(ticker, "", da, a);

        closes = stock.getSeries(FinConstants.CLOSE);
//        stock.indicators.put("SMA-" + 50, new SMAIndicator(closes, "SMA-" + 50, 50, -1, -1, -1, -1))

        String dir = "/home/antonio/timeplot/" + stock.name + "/"

        FileUtils.writeStringToFile(new File(dir + "stock.txt"), closes.getTimeplotSeries())
        FileUtils.writeStringToFile(new File(dir + "stock.raw"), closes.getNoDateSeries())

        SSAStudy.study(stock, 50);
        StockUtils.printTimeplotIndicatorOnFile(dir + "/", stock)

        copyAllHtml(dir)
    }

    void copyAllHtml(String s) {


        List<File> files = getHtmlFiles()

        for (int i = 0; i < files.size(); i++) {
            File o = files.get(i);


        }


    }

    private List<File> getHtmlFiles() {
        List<File> list = Arrays.asList(new File(dir).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".html");
            }
        }));
        return list;
    }


}