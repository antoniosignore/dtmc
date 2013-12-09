package com.netnumeri.server.utils

import com.dtmc.gson.DailyGSON
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.beans.Daily
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.GenericTimeSeries
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.indicator.UserIndicators
import com.netnumeri.server.finance.ta.*
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils
import org.apache.commons.io.FileUtils

import java.text.DecimalFormat
import java.text.SimpleDateFormat

class StockUtils {

    public static final String BEGIN = "&lt;"
    public static final String END = "&gt;"

    public static enclose(String html) {
        return BEGIN + html + END
    }

    public static void refreshData(Stock stock) {
        refreshDaily(stock)
        refreshChain(stock)
        stock.snapshot = YahooUtils.getCompanySnapshot(stock.name);
    }

    public static void refreshDaily(Stock stock) {
        YahooUtils.refreshDailyData(stock, DateUtils.todayOneYearAgo(), DateUtils.today());
    }

    public static void refreshChain(Stock stock) {
        stock.chain = YahooOptions.loadOptionChain(stock.name)
    }

    public static void printTimeplotIndicatorOnFile(String dir, Stock stock) {
        Map<String, Indicator> indicators = stock.indicators
        Set<String> keys = indicators.keySet();
        for (Iterator it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            Indicator indicator = indicators.get(key)
            FileUtils.writeStringToFile(new File(dir + indicator.name + ".txt"), indicator.getTimeplotSeries())
        }
    }

    static List<Double> toList(double[] doubles) {
        List<Double> list = new ArrayList<Double>()
        for (int i = 0; i < doubles.length; i++) {
            double d = doubles[i];
            list.add(d)
        }
        return list
    }

    public static void printRawIndicatorOnFile(String dir, Stock stock) {
        Map<String, Indicator> indicators = stock.indicators
        Set<String> keys = indicators.keySet();
        for (Iterator it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            Indicator indicator = indicators.get(key)
            FileUtils.writeStringToFile(new File(dir + indicator.name + ".raw"), indicator.getNoDateSeries())
        }
    }

    public static void printArrayOnFile(double[] series, String name) {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < series.length; i++) {
            double val = series[i];
            sb.append("[" + i + ", " + val + "],")
        }
        sb.append("]")
        FileUtils.writeStringToFile(new File(name + ".txt"), sb.toString())
    }

//    public static void printTransactionsOnFile(GenericTimeSeries<Trade> transactions, String dir, String filename) {
//        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<data>");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
//        Set<Date> set = transactions.map().keySet()
//        for (Iterator it = set.iterator(); it.hasNext();) {
//            Date transactionDate = (Date) it.next();
//            Trade transaction = transactions.treeMap.get(transactionDate)
//            sb.append("<event start=\"" + sdf.format(transaction.transactionDate) + "\" title=\"" + transaction.action.toString() + "\"/>\n");
//        }
//        sb.append("</data>")
//        FileUtils.writeStringToFile(new File(dir + filename), sb.toString())
//    }

    public static void printOnFile(String dir, List<TradeListEntry> list) {
        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<data>");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        for (int i = 0; i < list.size(); i++) {
            TradeListEntry tradeEntry = list.get(i);
            List<TradeInfo> tradeInfoList = tradeEntry.tradeInfoList
            for (int j = 0; j < tradeInfoList.size(); j++) {
                TradeInfo tradeInfo = tradeInfoList.get(j);
                sb.append("<event start=\"" + sdf.format(tradeInfo.date) + "\"" +
                        "  end=\"" + sdf.format(tradeInfo.closeDate) +
                        "\" title=\"" + " " + "\">\n");
                sb.append(enclose("table"));
                sb.append(enclose("tr"));
                sb.append(enclose("td") + "Trade Type")
                sb.append(enclose("/td"));
                sb.append(enclose("td") + tradeInfo.tradeType);
                sb.append(enclose("/td"));
                sb.append(enclose("/tr"));

                sb.append(enclose("tr"));
                sb.append(enclose("td") + "Trade Open")
                sb.append(enclose("/td"));
                sb.append(enclose("td") + tradeInfo.tradeOpen);
                sb.append(enclose("/td"));
                sb.append(enclose("/tr"));

                sb.append(enclose("tr"));
                sb.append(enclose("td") + "Trade Close")
                sb.append(enclose("/td"));
                sb.append(enclose("td") + tradeInfo.tradeClose);
                sb.append(enclose("/td"));
                sb.append(enclose("/tr"));

                sb.append(enclose("tr"));
                sb.append(enclose("td") + "Trade Result")
                sb.append(enclose("/td"));
                sb.append(enclose("td") + tradeInfo.tradeResult);
                sb.append(enclose("/td"));
                sb.append(enclose("/tr"));
                sb.append(enclose("/table"));
                sb.append("</event>");
            }
        }
        sb.append("</data>")
        FileUtils.writeStringToFile(new File(dir + "trades.xml"), sb.toString())
    }

    static def printTimeSeriesOnFile(TimeSeries timeSeries, String dir) {
        FileUtils.writeStringToFile(new File(dir + timeSeries.name + ".txt"), timeSeries.getTimeplotSeries())
    }

    static void printArrayOnFile(List<Double> reconstructionList, String name) {

        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < reconstructionList.size(); i++) {
            double val = reconstructionList.get(i);
            sb.append("[" + i + ", " + val + "],")
        }
        sb.append("]")
        FileUtils.writeStringToFile(new File(name + ".txt"), sb.toString())

    }

    /*
<script class="code" language="javascript" type="text/javascript">
ohlc = [
['06/15/2009 16:00:00', 136.01, 139.5, 134.53, 139.48],
['09/15/2008 16:00:00', 142.03, 147.69, 120.68, 140.91]
];
</script>
 */

    static String getJqPlot(Stock stock) {
        GenericTimeSeries<Daily> dailyarray = stock.dailyarray;
        Date startDate = dailyarray.getFirstDate()
        Date last = dailyarray.getLastDate()
        Date dateIndex = startDate

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
//        StringBuffer sb = new StringBuffer("<script class=\"code\" language=\"javascript\" type=\"text/javascript\">\n");
//        sb.append("ohlc = [\n")
        StringBuffer sb = new StringBuffer("[\n");
        while (dateIndex != null) {
            Daily daily = dailyarray.get(dateIndex)
            if (dateIndex.getTime() == last.getTime())
                sb.append("['" + sdf.format(dateIndex) + "'," + daily.openprice + "," + daily.high + "," + daily.low + "," + daily.closeprice + "]\n")
            else
                sb.append("['" + sdf.format(dateIndex) + "'," + daily.openprice + "," + daily.high + "," + daily.low + "," + daily.closeprice + "],\n")
            dateIndex = dailyarray.getNextDate(dateIndex)
        }
        sb.append("];\n")
//        sb.append("</script>\n")
        return sb.toString()
    }

    static DailyGSON getDailyGSON(Stock stock) {

        TimeSeries series = stock.getCloseSeries()

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#.####");

        List<DailyGSON> ds = new ArrayList<DailyGSON>();
        ds.add(buildDaily("2013-01-01", "1"));
        ds.add(buildDaily("2013-01-02", "2"));
        ds.add(buildDaily("2013-01-03", "3"));
        ds.add(buildDaily("2013-01-04", "4"));
        ds.add(buildDaily("2013-01-05", "5"));
        ds.add(buildDaily("2013-01-06", "4"));
        ds.add(buildDaily("2013-01-07", "3"));
        ds.add(buildDaily("2013-01-08", "4"));
        ds.add(buildDaily("2013-01-09", "1.3"));
        ds.add(buildDaily("2013-01-10", "1.5"));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(ds);
        System.out.println("json = " + jsonOutput);
    }

    private static DailyGSON buildDaily(String day, String val) {
        DailyGSON d1 = new DailyGSON();
        d1.period = day;
        d1.setClose(val);
        return d1;
    }

    public static List<UserIndicators> get(Stock stockInstance) {

        List<UserIndicators> list = UserIndicators.list()

        TimeSeries closes = stockInstance.getSeries(FinConstants.CLOSE);
        Indicator indicator = null;

        for (int i = 0; i < list.size(); i++) {
            UserIndicators ui = list.get(i);
            if (ui.type == IndicatorEnum.SimpleMovingAverage) {

                indicator = new SMAIndicator(closes, "Simple Moving Average", ui.integer1);

            } else if (ui.type == IndicatorEnum.PriceChannelUpper) {

                indicator = new PriceChannelUpIndicator(closes, "PCUpper", ui.integer1, ui.double2);

            } else if (ui.type == IndicatorEnum.PriceChannelLower) {

                indicator = new PriceChannelLowerIndicator(closes, "PCLower", ui.integer1, ui.double2);

            } else if (ui.type == IndicatorEnum.WeightedMovingAverage) {

                indicator = new WMAIndicator(closes, "Weighted Moving Average", ui.integer1);

            } else if (ui.type == IndicatorEnum.SingularSpectrumFirstComponent) {

                List<Integer> components = [0]
                indicator = new SSAComponentsIndicator(closes, "SSA-0", ui.integer1, components);

            } else if (ui.type == IndicatorEnum.SingularSpectrumSecondComponent) {

                List<Integer> components = [1]
                indicator = new SSAComponentsIndicator(closes, "SSA-0", ui.integer1, components);

            } else if (ui.type == IndicatorEnum.SingularSpectrumFirstSecondComponent) {

                List<Integer> components = [0, 1]
                indicator = new SSAComponentsIndicator(closes, "SSA-01", ui.integer1, components);

            } else if (ui.type == IndicatorEnum.SingularSpectrumPrediction) {

            } else if (ui.type == IndicatorEnum.UpperBollingerBand) {

                indicator = new BollingerBandUpIndicator(closes, "BB-Upper", ui.integer1, ui.double1);

            } else if (ui.type == IndicatorEnum.LowerBollingerBand) {

                indicator = new BollingerBandLowerIndicator(closes, "BB-Lower", ui.integer1, ui.double1);

            } else if (ui.type == IndicatorEnum.SimpleMovingVariance) {

                indicator = new SimpleMovingVarianceIndicator(closes, "Simple Moving variance", ui.integer1);

            } else if (ui.type == IndicatorEnum.Momentum) {

                indicator = new MomentumPctPeriodIndicator(closes, "Momentum", ui.integer1)

            } else if (ui.type == IndicatorEnum.MACD) {

                indicator = new MACDIndicator(closes, "MACD", ui.integer1, ui.integer2)

            } else if (ui.type == IndicatorEnum.MACDSignal) {

                indicator = new MACDSignal(closes, "MACD-Signal", ui.integer1, ui.integer2, ui.integer3)

            } else if (ui.type == IndicatorEnum.RateOfChange) {

                indicator = new RateOfChangeOverPeriodIndicator(closes, "Rate of Change", ui.integer1)

            } else if (ui.type == IndicatorEnum.RelativeStrengthIndex) {

                indicator = new RSIIndicator(closes, "RSI", ui.integer1)

            } else if (ui.type == IndicatorEnum.RelativeStrengthIndex2) {

                indicator = new RSI2Indicator(closes, "RSI", ui.integer1)

            } else if (ui.type == IndicatorEnum.CommodityChannelIndicator) {

                indicator = new CommodityChannelIndicator(closes, "CCI", ui.integer1)

            } else if (ui.type == IndicatorEnum.Oscillator) {

//                userIndicator.indicator =
//                    new Os(closes, "PCI-" + userIndicator.order + "-" + userIndicator.k, userIndicator.order, userIndicator.k)

            } else if (ui.type == IndicatorEnum.PriceChannelUpper) {

                indicator = new PriceChannelUpIndicator(closes, "PCI", ui.integer1, ui.double2)

            } else if (ui.type == IndicatorEnum.PriceChannelLower) {

                indicator = new PriceChannelLowerIndicator(closes, "PCI", ui.integer1, ui.double2)

            } else if (ui.type == IndicatorEnum.TrueChange) {

                indicator = new TrueRangeOverPeriodIndicator(stockInstance, "TrueRange")

//            } else if (ui.type == IndicatorEnum.AverageTrueChange) {
//
//                indicator =
//                    new TrueRangeOverPeriodIndicator(closes, "TrueRange")

            } else if (ui.type == IndicatorEnum.MoneyFlowOverPeriod) {

                indicator = new MoneyFlowOverPeriodIndicator(stockInstance, "Money flow", ui.integer1)

            } else if (ui.type == IndicatorEnum.AccumulateDistributionOverPeriod) {

                indicator = new AccumulateDistributionOverPeriodIndicator(stockInstance, "Accumulate Distribution", ui.integer1)

            } else if (ui.type == IndicatorEnum.ChaikinOscillatorOverPeriod) {

                indicator = new ChaikinOscillatorOverPeriodIndicator(stockInstance, "ChaikinOscillatorOverPeriodIndicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.ChaikinMoneyFlowOverPeriod) {

                indicator = new ChaikinMoneyFlowOverPeriodIndicator(stockInstance, "ChaikinMoneyFlowOverPeriodIndicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.AroonOscillatorOverPeriod) {

                indicator = new AaronOscillatorOverPeriodIndicator(stockInstance, "AaronOscillatorOverPeriodIndicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.AroonDownOverPeriod) {

                indicator = new AaronDownOverPeriodIndicator(stockInstance, "Aaron Down Over Period Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.AroonUpOverPeriod) {

                indicator = new AaronUpOverPeriodIndicator(stockInstance, "Aaron Up Over Period Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.TrueRangePeriod) {

                indicator = new TrueRangeOverPeriodIndicator(stockInstance, "true Range Over Period Indicator")

            } else if (ui.type == IndicatorEnum.kFastStochasticPeriod) {

                indicator = new KFastStochasticIndicator(stockInstance, "K Fast Stochastic Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.dStochastic) {

                indicator = new DStochasticIndicator(stockInstance, "D Stochastic Indicator", ui.integer1, ui.integer2, ui.integer3)

            } else if (ui.type == IndicatorEnum.DStochasticSmoothed) {

                indicator = new DStochasticSmoothedIndicator(stockInstance, "D Stochastic Smoothed Indicator", ui.integer1, ui.integer2)

            } else if (ui.type == IndicatorEnum.ChaikinVolatility) {

                indicator = new ChaikinVolatilityIndicator(stockInstance, "Chaikin Volatility Indicator", ui.integer1, ui.integer2)

            } else if (ui.type == IndicatorEnum.MACDSignal) {

                indicator = new MACDSignal(closes, "MACD Signal Indicator", ui.integer1, ui.integer2, ui.integer3)

            } else if (ui.type == IndicatorEnum.PlusDirectionalMovementPeriod) {

                indicator = new PlusDMIndicator(stockInstance, "Plus DMI Indicator")

            } else if (ui.type == IndicatorEnum.RateOfChangePeriod) {

                indicator = new RateOfChangeOverPeriodIndicator(closes, "Rate of Change Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.Kairi) {

                indicator = new KairiIndicator(stockInstance, "Kairi Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.MomentumPctPeriod) {

                indicator = new MomentumPctPeriodIndicator(closes, "Momentum Pct Period Indicator", ui.integer1)

            } else if (ui.type == IndicatorEnum.CommodityChannelIndexOverPeriod) {

                indicator = new CommodityChannelIndicator(closes, "CCI", ui.integer1)

            } else if (ui.type == IndicatorEnum.MarketFacilitationIndexOverPeriod) {

                indicator = new MarketFacilitationIndexIndicator(stockInstance, "Market Facilitation Index Indicator")

            } else if (ui.type == IndicatorEnum.BalanceOfPowerOverPeriod) {

                indicator = new BalanceOfPowerIndicator(stockInstance, "Balance of Power Indicator")

            } else if (ui.type == IndicatorEnum.PriceActionOverPeriod) {

                indicator = new PriceActionOverPeriodIndicator(stockInstance, "Price action over integer1 indicator")

            }
        }
        return list
    }
}
