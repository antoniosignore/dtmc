package com.netnumeri.server.finance.finpojo

import com.netnumeri.server.finance.beans.Daily
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.GenericTimeSeries
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.utils.DateUtils

class Instrument extends Persistable implements Serializable {

    static mapping = { tablePerHierarchy false
    }

    static transients = [
            "indicators",
            "priceSeries",
            "returnSeries",
            "logReturnSeries",
            "highSeries",
            "lowSeries",
            "openSeries",
            "closeSeries",
            "volumeSeries",
            "volumeReturnSeries",
            "priceSeries",
            "volumeLogReturnSeries",
            "dailyarray",
            "lastQuote",
            "marketSpotShift",
            "marketVolatilityShift",
            "delta",
            "spot",
            "volatility",
            "isSpotFixed",
            "isVolatilityFixed",
            "tempToday",
            "tempSpot",
            "tempVolatility",
            "isTempSpotFixed",
            "isTempVolatilityFixed",
            "lowerRangeDate",
            "upperRangeDate"
    ]

    Map<String, Indicator> indicators = new HashMap<>();
    TimeSeries priceSeries = null;
    TimeSeries returnSeries = null;
    TimeSeries logReturnSeries = null;
    TimeSeries highSeries = null;
    TimeSeries lowSeries = null;
    TimeSeries openSeries = null;
    TimeSeries closeSeries = null;
    TimeSeries volumeSeries = null;
    TimeSeries volumeReturnSeries = null;
    TimeSeries volumeLogReturnSeries = null;
    GenericTimeSeries<Daily> dailyarray = new GenericTimeSeries<Daily>();

    Double lastQuote;
    double marketSpotShift = 1.0;
    double marketVolatilityShift = 1.0;
    def delta = 1D;
    double spot;
    double volatility;
    boolean isSpotFixed;
    boolean isVolatilityFixed = false;
    def tempToday = new Date();
    double tempSpot;
    double tempVolatility;
    def isTempSpotFixed = false
    def isTempVolatilityFixed = false;

    def lowerRangeDate;
    def upperRangeDate;

    public void setRangeBounds(Date lowerDate, Date upperDate) {

        if (lowerRangeDate == null) lowerRangeDate = lowerDate;
        if (upperRangeDate == null) upperRangeDate = upperDate;

        if (lowerDate < this.lowerRangeDate) lowerRangeDate = lowerDate;
        if (upperDate > this.upperRangeDate) upperRangeDate = upperDate;
    }

    public boolean inRange(Date date) {
        boolean b1 = DateUtils.isGreaterEqual(date, lowerRangeDate);
        boolean b2 = DateUtils.isLessEqual(date, upperRangeDate);
        boolean b3 = b1 && b2;
        return b3;
    }

    public Instrument() {
        isSpotFixed = false;
        isVolatilityFixed = false;
        priceSeries = new TimeSeries("PriceSeries");
        returnSeries = new TimeSeries("ReturnSeries");
        logReturnSeries = new TimeSeries("LogReturnSeries");
        highSeries = new TimeSeries("HighSeries");
        lowSeries = new TimeSeries("LowSeries");
        openSeries = new TimeSeries("OpenSeries");
        closeSeries = new TimeSeries("CloseSeries");
        volumeSeries = new TimeSeries("VolumeSeries");
        volumeReturnSeries = new TimeSeries("VolumeReturnSeries");
        volumeLogReturnSeries = new TimeSeries("VolumeLogReturnSeries");
    }

    public double S() {
        return spot();
    }

    public double s() {
        return volatility();
    }

    public double historicalSpot() {
        double ret = 0;
        ret = historicalSpot(new Date());
        return ret;
    }

    public double historicalSpot(Date d) {
        return getPrice(d);
    }

    public double historicalVolatility() {
        return getStandardDeviation(FinConstants.LOGRETURN) * Math.sqrt(365.0);
    }

    /**
     * Return spot premium. In case of derivative instruments usually return spot premium of the underlying instrument
     *
     * @return Return fixed value if spot premium is set with setSpot() call else return last historical spot value
     */
    public double spot() {
        if (isSpotFixed()) {
            return marketSpotShift * getFixedSpot();
        } else {
            return marketSpotShift * historicalSpot();
        }
    }

    /**
     * Return volatility. In case of derivative instruments usually return volatility of the underlying instrument
     * Volatility is calculated as annualized standard deviation of instrument daily log returns
     *
     * @return Return fixed value if volatility is set with setVolatility() call else return historical volatility
     */
    public double volatility() {
        if (isVolatilityFixed()) {
            return marketVolatilityShift * getFixedVolatility();
        } else {
            return marketVolatilityShift * historicalVolatility();
        }
    }

    /**
     * Set spot value
     *
     * @param spot value
     */
    public void setSpot(double spot) {
        this.spot = spot;
        isSpotFixed = true;
    }

    /**
     * Set volatility value
     */
    public void setVolatility(double v) {
        volatility = v;
        isVolatilityFixed = true;
    }

    public void resetSpot() {
        isSpotFixed = false;
    }

    public void resetVolatility() {
        isVolatilityFixed = false;
    }

    public void storeSettings() {
        tempToday = new Date();
        tempSpot = spot;
        tempVolatility = volatility;
        isTempSpotFixed = isSpotFixed;
        isTempVolatilityFixed = isVolatilityFixed;
    }

    public void restoreSettings() {
        DateUtils.setToday(tempToday);
        spot = tempSpot;
        volatility = tempVolatility;
        isSpotFixed = isTempSpotFixed;
        isVolatilityFixed = isTempVolatilityFixed;
    }

    public void addDaily(int index, Daily daily) {
        add(this, index, daily.dailydate,
                daily.getHigh(), daily.getLow(), daily.getOpenprice(), daily.getCloseprice(), daily.getVolume(),
                daily.getOpenInterest(), FinConstants.VALID);
        setLowerRangeDate(DateUtils.min(daily.dailydate, getLowerRangeDate()));
        setUpperRangeDate(DateUtils.max(daily.dailydate, getUpperRangeDate()));
    }

    public void addDaily(int index, Date date, double high, double low, double open, double close, int volume, int openInterest, FinConstants option) {
        add(this, index, date, high, low, open, close, volume, openInterest, option);
        setLowerRangeDate(DateUtils.min(date, getLowerRangeDate()));
        setUpperRangeDate(DateUtils.max(date, getUpperRangeDate()));
    }

    public boolean isDataAvailable(Date date) {
        if (date == null) throw new IllegalArgumentException("transactionDate cannot be null");
        return dataAvailable(date);
    }


    public Daily getDaily(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("transactionDate cannot be null");
        }
        return daily(date);
    }

    public double premium() {
        return price(new Date(), FinConstants.TYPICALPRICE);
    }

    public double getPrice(Date date) {
        if (date == null) throw new IllegalArgumentException("transactionDate cannot be null");
        return getPrice(date, FinConstants.TYPICALPRICE);
    }

    /**
     *  Option TYPICALPRICE, MEDIANPRICE , WEIGHTEDPRICE ,AVERAGEPRICE, LOGAVERAGEPRICE;
     */
    public double getPrice(Date date, FinConstants Option) {
        if (date == null) throw new IllegalArgumentException("transactionDate cannot be null");
        return value(date, Option);
    }

    public double getClose(Date date) {
        return dailyarray.get(date).closeprice;
    }

    public double getReturn(Date date) {
        if (date == null) throw new IllegalArgumentException("transactionDate cannot be null");
        return re(date);
    }

    public double getLogReturn(Date date) {
        if (date == null) throw new IllegalArgumentException("transactionDate cannot be null");
        return logReturn(date);
    }

    public int getVolume(Date date) {
        if (date == null) throw new IllegalArgumentException("transactionDate cannot be null");
        return dailyarray.get(date).volume;
    }

    public double value(Date date, FinConstants option) {
        if (dailyarray.get(date) == null)
            date = getLast(date)
        switch (option) {
            case FinConstants.HIGH:
                return high(date);
            case FinConstants.LOW:
                return low(date);
            case FinConstants.OPEN:
                return open(date);
            case FinConstants.CLOSE:
                return close(date);
            case FinConstants.VOLUME:
                return volume(date);
            case FinConstants.PRICE:
                return price(date);
            case FinConstants.MEDIANPRICE:
            case FinConstants.WEIGHTEDPRICE:
            case FinConstants.TYPICALPRICE:
                return dailyarray.get(date).price(option);
            case FinConstants.RETURN:
                return getReturn(date);
            case FinConstants.LOGRETURN:
                return getLogReturn(date);
        }
    }

    public double getLast(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        Daily daily = getDaily(date);
        while (!daily.valid()) {
            daily = getPrevDaily(date);
        }
        if (daily != null) {
            return daily.getCloseprice();
        } else {
            return 0;
        }
    }

    public double getLast() {
        Date lastDailyDate = lastDay();
        return getLast(lastDailyDate);
    }

    public TimeSeries priceSeries() {
//        if (priceSeriesChanged)
        priceSeries.clear();
        int firstIndex = getFirstIndex();
        int lastIndex = getLastIndex();

        for (int i = firstIndex; i <= lastIndex; i++) {
            Daily daily = getDaily(i);
            Date date = daily.getDailydate();
            double price = daily.getPrice();
            if (daily != null && daily.valid() && price != 0) {
                priceSeries.add(date, price);
            }
            //          priceSeriesChanged = false;
        }
        return priceSeries;
    }

    public TimeSeries getSeries(FinConstants what) {
        return getSeries(what, null, null);
    }

    public TimeSeries getSeries(FinConstants what, Date firstD, Date lastD) {
        if (firstD == null) {
            firstD = firstDate();
        }
        if (lastD == null) {
            lastD = lastDate();
        }
        switch (what) {
            case FinConstants.HIGH:
                return highSeries(firstD, lastD);
            case FinConstants.LOW:
                return lowSeries(firstD, lastD);
            case FinConstants.OPEN:
                return openSeries(firstD, lastD);
            case FinConstants.CLOSE:
                return closeSeries(firstD, lastD);
            case FinConstants.VOLUME:
                return volumeSeries(firstD, lastD);
        }
        return null;
    }


    public TimeSeries highSeries() {
        return highSeries(null, null);
    }

    // Return high time series
    public TimeSeries highSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDay();
        if (lastDate == null) lastDate = lastDay();
        highSeries = buildHighSeries(firstDate, lastDate);
        //       highSeriesChanged = false;
        return highSeries;
    }


    public TimeSeries lowSeries() {
        return lowSeries(null, null);
    }

    public TimeSeries lowSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDay();
        if (lastDate == null) lastDate = lastDay();
        lowSeries = buildLowSeries(firstDate, lastDate);
        return lowSeries;
    }


    public TimeSeries closeSeries() {
        return closeSeries(null, null);
    }

    public TimeSeries closeSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDay();
        if (lastDate == null) lastDate = lastDay();
        closeSeries = buildCloseSeries(firstDate, lastDate);
        return closeSeries;
    }

    public TimeSeries openSeries() {
        return openSeries(null, null);
    }

    public TimeSeries openSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDay();
        if (lastDate == null) lastDate = lastDay();
        //  if (openSeriesChanged)
        openSeries = buildOpenSeries(firstDate, lastDate);
        //      openSeriesChanged = false;
        return openSeries;
    }

    public TimeSeries volumeSeries() {
        return volumeSeries(null, null);
    }

    public TimeSeries volumeSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDay();
        if (lastDate == null) lastDate = lastDay();
        volumeSeries = buildVolumeSeries(firstDate, lastDate);
        return volumeSeries;
    }

    public TimeSeries returnSeries() {
        //  if (returnSeriesChanged)
        returnSeries.clear();
        Date date = null;
        double price = 0;
        Daily daily = null;
        int firstIndex = getFirstIndex();
        int lastIndex = getLastIndex();
        daily = getDaily(firstIndex);
        if (daily != null && !daily.valid()) {
            firstIndex = getNextIndex(firstIndex);
        }
        double fLastPrice = daily.getCloseprice();
        for (int i = firstIndex + 1; i <= lastIndex; i++) {
            daily = getDaily(i);
            price = daily.getCloseprice();
            date = daily.getDailydate();
            if (daily != null && daily.valid() && price != 0) {
                returnSeries.add(date, price / fLastPrice);
                fLastPrice = price;
            }
//            returnSeriesChanged = false;
        }
        return returnSeries;
    }

    public TimeSeries logReturnSeries() {
        logReturnSeries.clear();
        Date date = null;
        double price = 0;
        Daily daily = null;
        int firstIndex = getFirstIndex();
        int lastIndex = getLastIndex();

        daily = getDaily(firstIndex);
        if (daily != null && !daily.valid()) {
            firstIndex = getNextIndex(firstIndex);
        }

        assert daily != null;
        double fLastPrice = daily.getCloseprice();
        for (int i = firstIndex + 1; i <= lastIndex; i++) {
            daily = getDaily(i);
            date = daily.getDailydate();
            price = daily.getCloseprice();
            if (daily.valid() && price != 0) {
                double logprice = Math.log(price / fLastPrice);
                logReturnSeries.add(date, logprice);
                fLastPrice = price;
            }
        }
        return logReturnSeries;
    }


    public TimeSeries volumeReturnSeries() {
        //    if (volumeReturnSeriesChanged)
        volumeReturnSeries.clear();
        Date date = null;
        double volume = 0;
        Daily daily = null;
        int firstIndex = getFirstIndex();
        int lastIndex = getLastIndex();
        daily = getDaily(firstIndex);
        if (daily != null && !daily.valid()) {
            firstIndex = getNextIndex(firstIndex);
        }
        double lastVolume = daily.getVolume();
        for (int i = firstIndex + 1; i <= lastIndex; i++) {
            daily = getDaily(i);
            date = daily.getDailydate();
            volume = daily.getVolume();
            if (daily != null && daily.valid() && volume != 0) {
                volumeReturnSeries.add(date, volume / lastVolume);
                lastVolume = volume;
            }
            //           volumeReturnSeriesChanged = false;
        }
        return volumeReturnSeries;
    }

    public TimeSeries volumeLogReturnSeries() {
        //     if (volumeLogReturnSeriesChanged)
        volumeLogReturnSeries.clear();
        Date date = null;
        double volume = 0;
        Daily daily = null;
        int firstIndex = getFirstIndex();
        int lastIndex = getLastIndex();
        daily = getDaily(firstIndex);
        if (daily != null && !daily.valid()) {
            firstIndex = getNextIndex(firstIndex);
        }
        double lastVolume = daily.getVolume();
        for (int i = firstIndex + 1; i <= lastIndex; i++) {
            daily = getDaily(i);
            date = daily.getDailydate();
            volume = daily.getVolume();
            if (daily != null && daily.valid() && volume != 0) {
                volumeLogReturnSeries.add(date, Math.log(volume / lastVolume));
                lastVolume = volume;
            }
            //        volumeLogReturnSeriesChanged = false;
        }
        return volumeLogReturnSeries;
    }

    public double expectedReturn() {
        return getExpectedReturn(FinConstants.RETURN);
    }

    public double getExpectedReturn(FinConstants option) {
        double Return = 0;
        switch (option) {
            case FinConstants.RETURN:
                Return = returnSeries().getMean();
                break;

            case FinConstants.LOGRETURN:
                Return = logReturnSeries().getMean();
                break;
        }
        return Return;
    }

    public double variance() {
        return getVariance(FinConstants.RETURN);
    }

    public double getVariance(FinConstants option) {
        double Variance = 0;
        switch (option) {
            case FinConstants.RETURN:
                Variance = returnSeries().getVariance();
                break;
            case FinConstants.LOGRETURN:
                Variance = logReturnSeries().getVariance();
                break;
        }
        return Variance;
    }

    public double standardDeviation() {
        return getStandardDeviation(FinConstants.RETURN);
    }

    public double getStandardDeviation(FinConstants option) {
        return Math.sqrt(getVariance(option));
    }

    public double annualExpectedReturn() {
        return (Math.pow(expectedReturn(), 365) - 1);
    }

    public double annualVariance() {
        return variance() * 365.0;
    }

    public double annualStandardDeviation() {
        return standardDeviation() * Math.sqrt(365.0);
    }

    public double getCovariance(Instrument instrument) {
        return getCovariance(instrument, FinConstants.RETURN);
    }

    public double getCovariance(Instrument instrument, FinConstants option) {
        double Covariance = 0;
        switch (option) {
            case FinConstants.RETURN:
                Covariance = returnSeries().getCovariance(instrument.returnSeries());
                break;
            case FinConstants.LOGRETURN:
                Covariance = logReturnSeries().getCovariance(instrument.logReturnSeries());
                break;
        }
        return Covariance;
    }

    public double getCorrelation(Instrument instrument) {
        return getCorrelation(instrument, FinConstants.RETURN);
    }

    public double getCorrelation(Instrument instrument, int option) {
        double Correlation = 0;
        switch (option) {
            case FinConstants.RETURN:
                Correlation = returnSeries().getCorrelation(instrument.returnSeries());
                break;
            case FinConstants.LOGRETURN:
                Correlation = logReturnSeries().getCorrelation(instrument.logReturnSeries());
                break;
        }
        return Correlation;
    }

    public int getNCorrelationPairs(Instrument instrument) {
        return returnSeries().getNCorrelationPairs(instrument.returnSeries());
    }

    public boolean isDailyData() {
        return isDailyData(null);
    }

    public boolean isDailyData(Date date) {
        return dailyData(date);
    }

    public Date firstDay() {
        return firstDailyDate();
    }

    public Date lastDay() {
        return lastDailyDate();
    }

    public int getNDaily() {
        return length();
    }

    public Date getPrevDate(Date date) {
        return prevDate(date);
    }

    public Date getNextDate(Date date) {
        return nextDate(date);
    }

    public Daily getPrevDaily(Date date) {
        return prevDaily(date);
    }

    public Date firstDate() {
        return firstDailyDate();
    }

    public Date lastDate() {
        return lastDailyDate();
    }

    public Daily daily(Date date) {
        return dailyarray.get(date);
    }

    public boolean dataAvailable(Date date) {
        Daily daily = dailyarray.get(date);
        if (daily == null) return false;
        return daily.state != FinConstants.NOTAVAILABLE;
    }

    public Date prevDate(Date date) {
        return dailyarray.getPrevDate(date);
    }

    public Date nextDate(Date date) {
        return dailyarray.getNextDate(date);
    }

    public Daily prevDaily(Date date) {
        Date prevDate = prevDate(date);
        if (prevDate == null)
            return null;
        else
            return daily(prevDate);
    }

    public Daily nextDaily(Date date) {
        Date nextDate = nextDate(date);
        if (nextDate == null)
            return null;
        else
            return daily(nextDate);
    }

    public double calculateReturn(Date date) {
        if (date == firstDate()) return 1;
        if (date == null) date = lastDate();
        Daily daily = daily(date);
        if (!daily.valid()) return 1;
        double Price = daily.price();
        Daily prevDaily = this.prevDaily(date);
        if (prevDaily != null)
            return Price / prevDaily.price();
        else
            return 1;
    }

    public double logReturn() {
        return logReturn(null);
    }

    public double logReturn(Date date) {
        return Math.log(calculateReturn(date));
    }

    public double re(Date date) {
        return calculateReturn(date);
    }

    public double price(Date date) {
        return price(date, FinConstants.TYPICALPRICE);
    }

    public double price(Date date, FinConstants Option) {
        return value(date, Option)
    }

    public double low(Date date) {
        return dailyarray.get(date).low;
    }

    public double high(Date date) {
        return dailyarray.get(date).high;
    }

    public double open(Date date) {
        return dailyarray.get(date).openprice;
    }

    public double close(Date date) {
        return dailyarray.get(date).closeprice;
    }

    public int volume(Date date) {
        return dailyarray.get(date).volume;
    }

    public int openInterest(Date date) {
        return dailyarray.get(date).openInterest;
    }

    public TimeSeries timeSeries(FinConstants What, Date firstDate, Date lastDate) {
        TimeSeries series = new TimeSeries();
        Date firstDailyDate = firstDailyDate();
        Date lastDailyDate = lastDailyDate();
        if (firstDate == null) firstDate = firstDailyDate;
        if (lastDate == null) lastDate = lastDailyDate;
        if (DateUtils.isLess(firstDate, firstDailyDate)) firstDate = firstDailyDate;
        if (DateUtils.isGreater(lastDate, lastDailyDate)) lastDate = lastDailyDate;
        double value;
        for (Date date = firstDate;
        DateUtils.isLessEqual(date, lastDate);
        date = DateUtils.addDays(date, 1)) {
            Daily daily = daily(date);
            if ((daily != null) && daily.valid()) {
                value = daily.price(What);
                if (value != 0) {
                    series.add(daily.dailydate, value);
                }
            }
        }
        return series;
    }

    public TimeSeries buildHighSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDailyDate();
        if (lastDate == null) lastDate = lastDay();
        return timeSeries(FinConstants.HIGH, firstDate, lastDate);
    }

    public TimeSeries buildLowSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDailyDate();
        if (lastDate == null) lastDate = lastDay();
        return timeSeries(FinConstants.LOW, firstDate, lastDate);
    }

    public TimeSeries buildOpenSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDailyDate();
        if (lastDate == null) lastDate = lastDay();
        return timeSeries(FinConstants.OPEN, firstDate, lastDate);
    }

    public TimeSeries buildCloseSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDailyDate();
        if (lastDate == null) lastDate = lastDay();
        return timeSeries(FinConstants.CLOSE, firstDate, lastDate);
    }

    public TimeSeries buildVolumeSeries(Date firstDate, Date lastDate) {
        return timeSeries(FinConstants.VOLUME, firstDate, lastDate);
    }

    public TimeSeries buildHighSeries() {
        return timeSeries(FinConstants.HIGH, null, null);
    }

    public TimeSeries buildLowSeries() {
        return timeSeries(FinConstants.LOW, null, null);
    }

    public TimeSeries buildOpenSeries() {
        return timeSeries(FinConstants.OPEN, null, null);
    }

    public TimeSeries buildCloseSeries() {
        return timeSeries(FinConstants.CLOSE, null, null);
    }

    public TimeSeries buildLogAverageSeries() {
        return timeSeries(FinConstants.LOGAVERAGEPRICE, null, null);
    }

    public TimeSeries buildVolumeSeries() {
        return timeSeries(FinConstants.VOLUME, null, null);
    }

    public boolean dailyData(Date date) {
        if (dailyarray == null) {
            return false;
        }
        if (date == null) {
            return dailyarray.treeMap.size() != 0;
        }
        return dataAvailable(date);
    }

    public int length() {
        return dailyarray.treeMap.size();
    }

    public Date firstDailyDate() {
        if (dailyarray == null || dailyarray.isEmpty())
            return null
        Daily daily = (Daily) dailyarray.getFirstValue();
        if (daily != null)
            return daily.getDailydate();
        else
            return null;
    }

    public Date lastDailyDate() {
        return dailyarray.getLastDate();
    }

    public void add(Daily daily) {
        dailyarray.put(daily.dailydate, daily);
    }

    public void add(Instrument instrument,
                    int index,
                    Date date,
                    double high,
                    double low,
                    double open,
                    double close,
                    int volume,
                    int openInterest,
                    FinConstants option) {
        Daily daily = new Daily(instrument, index, date, high, low, open, close, volume, openInterest, option);
        add(daily);
    }
}
