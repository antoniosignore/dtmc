package com.netnumeri.server.finance.finpojo

import com.netnumeri.server.finance.beans.Daily
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.GenericTimeSeries
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.utils.DateUtils

class Instrument extends Persistable implements Serializable {

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

    public Double bid;
    public Double ask;
    public Double lastQuote;

    double marketSpotShift = 1.0;
    double marketVolatilityShift = 1.0;

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

    public Date getFirstDate() {
        if (lowerRangeDate == null) return null;
        return lowerRangeDate;
    }

    public Date getLastDate() {
        if (upperRangeDate == null) return null;
        return upperRangeDate;
    }

    public Instrument() {
        init();
    }

    void init() {
        initFields();
        initSeries();
    }

    void initFields() {
        isSpotFixed = false;
        isVolatilityFixed = false;
    }

    void initSeries() {
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

    public Date tempToday() {
        return tempToday;
    }

    public void setTempToday(Date tempToday) {
        this.tempToday = tempToday;
    }

    public double getTempSpot() {
        return tempSpot;
    }

    public void setTempSpot(double tempSpot) {
        this.tempSpot = tempSpot;
    }

    public double getTempVolatility() {
        return tempVolatility;
    }

    public void setTempVolatility(double tempVolatility) {
        this.tempVolatility = tempVolatility;
    }

    public boolean isTempSpotFixed() {
        return isTempSpotFixed;
    }

    public void setTempSpotFixed(boolean tempSpotFixed) {
        this.isTempSpotFixed = tempSpotFixed;
    }

    public boolean isTempVolatilityFixed() {
        return isTempVolatilityFixed;
    }

    public void setTempVolatilityFixed(boolean tempVolatilityFixed) {
        this.isTempVolatilityFixed = tempVolatilityFixed;
    }


    public boolean isVolatilityFixed() {
        return isVolatilityFixed;
    }

    public double getFixedSpot() {
        return spot;
    }

    public double getFixedVolatility() {
        return volatility;
    }

    public double S() {
        return spot();
    }

    public double s() {
        return volatility();
    }

    public void setFixedSpot(double Spot) {
        spot = Spot;
    }

    public void setFixedVolatility(double Volatility) {
        volatility = Volatility;
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

    public boolean isSpotFixed() {
        return isSpotFixed;
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

//    public boolean dataAvailable() {
//        return isDataAvailable(-1);
//    }

//    public boolean isDataAvailable(int index) {
//        if (index == -1) {
//            index = getLastIndex();
//        }
//        return dataAvailable(index);
//    }

    public boolean isDataAvailable(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return dataAvailable(date);
    }

//    public int getFirstIndex() {
//        return dailyarray.index(firstDate());
//    }
//
//    public int getLastIndex() {
//        return dailyarray.index(lastDate());
//    }

    /**
     * Return historical data, if Index == -1, return last historical data Index Note that data must be copied,
     * otherwise they will be overwritten at any further operation with fDaily
     */
//    public Daily getDaily() {
//        return getDaily(-1, false);
//    }

//    public Daily getDaily(int index) {
//        return getDaily(index, false);
//    }
//
//    public Daily getDaily(int index, boolean neww) {
//        return daily(index, neww);
//    }

    public Daily getDaily(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }
        return daily(date);
    }

//    public int getPrevIndex(int index) {
//        return prevIndex(index);
//    }
//
//    public int getNextIndex(int index) {
//        return nextIndex(index);
//    }
//
//    public int getIndex(Date date) {
//        if (date == null) throw new IllegalArgumentException("date cannot be null");
//        return dailyarray.index(date);
//    }

//    public Date getDate(int index) {
//        return date(index);
//    }

    public double premium() {
        return getPrice(-1, FinConstants.TYPICALPRICE);
    }

//    public double getPrice(int index) {
//        return price(index, FinConstants.TYPICALPRICE);
//    }
//
//    public double getPrice(int index, int option) {
//        return price(index, option);
//    }
//
//    public double getLow() {
//        return getLow(-1);
//    }
//
//    public double getLow(int index) {
//        // Return low, if Index == -1, return low from last Index
//        return low(index);
//    }
//
//    public double getHigh() {
//        return getHigh(-1);
//    }
//
//    public double getHigh(int index) {
//        // Return high, if Index == -1, return high from last Index
//        return high(index);
//    }
//
//    public double getOpen() {
//        return getOpen(-1);
//    }
//
//    public double getOpen(int index) {
//        // Return open premium, if Index == -1, return close from last Index
//        return open(index);
//    }
//
//    public double getClose() {
//        return getClose(-1);
//    }
//
//    public double getClose(int index) {
//        // Return close premium, if Index == -1, return close from last Index
//        return close(index);
//    }
//
//
//    public double getReturn() {
//        return getReturn(-1);
//    }
//
//    public double getReturn(int index) {
//        return fReturn(index);
//    }
//
//    public double getLogReturn() {
//        return getLogReturn(-1);
//    }
//
//    public double getLogReturn(int index) {
//        // getAndRemove log return, if Index == -1, getAndRemove return from last Index
//        return logReturn(index);
//    }

    public double getPrice(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return getPrice(date, FinConstants.TYPICALPRICE);
    }

    /**
     *  Option TYPICALPRICE, MEDIANPRICE , WEIGHTEDPRICE ,AVERAGEPRICE, LOGAVERAGEPRICE;
     */
    public double getPrice(Date date, int Option) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return get(date, Option);
    }

    public double getLow(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return dailyarray.get(date).low;
    }

    public double getHigh(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return dailyarray.get(date).high;
    }

    public double getOpen(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return dailyarray.get(date).openprice;
    }

    public double getClose(Date date) {
        return dailyarray.get(date).closeprice;
    }

    public double getReturn(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return getReturn(date);
    }

    public double getLogReturn(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return getLogReturn(date);
    }

    public int getVolume(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return dailyarray.get(date).volume;
    }

    public double get(Date index, int option) {
        switch (option) {
            case FinConstants.HIGH:
                return getHigh(index);
            case FinConstants.LOW:
                return getLow(index);
            case FinConstants.OPEN:
                return getOpen(index);
            case FinConstants.CLOSE:
                return getClose(index);
            case FinConstants.VOLUME:
                return getVolume(index);
            case FinConstants.PRICE:
                return getPrice(index);
            case FinConstants.MEDIANPRICE:
                return getPrice(index, option);
            case FinConstants.TYPICALPRICE:
                return getPrice(index, option);
            case FinConstants.WEIGHTEDPRICE:
                return getPrice(index, option);
            case FinConstants.RETURN:
                return getReturn(index);
            case FinConstants.LOGRETURN:
                return getLogReturn(index);
        }
        return 0;
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

    public double getLast(String date, String pattern) {
        return getLast(date, pattern);
    }

    public TimeSeries getPriceSeries() {
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

    public TimeSeries getSeries(int what) {
        return getSeries(what, null, null);
    }

    public TimeSeries getSeries(int what, Date firstDate, Date lastDate) {
        if (firstDate == null) {
            firstDate = firstDate();
        }
        if (lastDate == null) {
            lastDate = lastDate();
        }
        switch (what) {
            case FinConstants.HIGH:
                return getHighSeries(firstDate, lastDate);
            case FinConstants.LOW:
                return getLowSeries(firstDate, lastDate);
            case FinConstants.OPEN:
                return getOpenSeries(firstDate, lastDate);
            case FinConstants.CLOSE:
                return getCloseSeries(firstDate, lastDate);
            case FinConstants.VOLUME:
                return getVolumeSeries(firstDate, lastDate);
        }
        return null;
    }


    public TimeSeries getHighSeries() {
        return getHighSeries(null, null);
    }

    // Return high time series
    public TimeSeries getHighSeries(Date firstDate, Date lastDate) {
        if (firstDate != null) {
            if (lastDate == null) {
                lastDate = lastDay();
            }
            return highSeries(firstDate, lastDate);
        }
        firstDate = firstDate();
        lastDate = lastDate();
        //   if (highSeriesChanged)
        highSeries = highSeries(firstDate, lastDate);
        //       highSeriesChanged = false;
        return highSeries;
    }


    public TimeSeries getLowSeries() {
        return getLowSeries(null, null);
    }

    public TimeSeries getLowSeries(Date firstDate, Date lastDate) {
        if (firstDate != null) {
            if (lastDate == null) {
                lastDate = lastDay();
            }
            return lowSeries(firstDate, lastDate);
        }
        firstDate = firstDate();
        lastDate = lastDate();
        //    if (lowSeriesChanged)
        lowSeries = lowSeries(firstDate, lastDate);
        //        lowSeriesChanged = false;
        return lowSeries;
    }


    public TimeSeries getOpenSeries() {
        return getOpenSeries(null, null);
    }

    public TimeSeries getOpenSeries(Date firstDate, Date lastDate) {
        if (firstDate != null) {
            if (lastDate == null) {
                lastDate = lastDay();
            }
            return openSeries(firstDate, lastDate);
        }
        firstDate = firstDate();
        lastDate = lastDate();
        //  if (openSeriesChanged)
        openSeries = openSeries(firstDate, lastDate);
        //      openSeriesChanged = false;
        return openSeries;
    }


    public TimeSeries getCloseSeries() {
        return getCloseSeries(null, null);
    }

    public TimeSeries getCloseSeries(Date firstDate, Date lastDate) {
        if (firstDate == null) firstDate = firstDailyDate();
        if (lastDate == null) lastDate = lastDay();
        closeSeries = closeSeries(firstDate, lastDate);
        return closeSeries;
    }


    public TimeSeries getVolumeSeries() {
        return getVolumeSeries(null, null);
    }

    public TimeSeries getVolumeSeries(Date firstDate, Date lastDate) {
        if (firstDate != null) {
            if (lastDate == null) {
                lastDate = lastDay();
            }
            return volumeSeries(firstDate, lastDate);
        }
        firstDate = firstDate();
        lastDate = lastDate();
        volumeSeries = volumeSeries(firstDate, lastDate);
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


    public TimeSeries getVolumeReturnSeries() {
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

    public TimeSeries getVolumeLogReturnSeries() {
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

    public double getExpectedReturn(int option) {
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

    public double getVariance(int option) {
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

    public double getStandardDeviation(int option) {
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

    public double getCovariance(Instrument instrument, int option) {
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

//    public int getPrevIndex(Date date) {
//        return getPrevIndex(getIndex(date));
//    }
//
//    public int getNextIndex(Date date) {
//        return getNextIndex(getIndex(date));
//    }

    public Date getPrevDate(Date date) {
        return prevDate(date);
    }

    public Date getNextDate(Date date) {
        return nextDate(date);
    }

//    public Daily getPrevDaily(int Index) {
//        return getPrevDaily(Index, false);
//    }
//
//    public Daily getPrevDaily(int Index, boolean New) {
//        return prevDaily(Index, New);
//    }

//    public Daily getPrevDaily(Date date) {
//        return getPrevDaily(date, false);
//    }

    public Daily getPrevDaily(Date date) {
        return prevDaily(date);
    }

//    public Daily getPrevDaily(Daily daily) {
//        return dailyarray.getPrevValue(daily.getDailydate())
//    }

//    public Daily getNextDaily(int Index) {
//        return getNextDaily(Index, false);
//    }
//
//    public Daily getNextDaily(int Index, boolean New) {
//        return nextDaily(Index, New);
//    }

//    public Daily getNextDaily(Date date) {
//        return getNextDaily(date, false);
//    }

    public Daily getNextDaily(Date date) {
        return nextDaily(date);
    }

    public void setMarketSpotShift(double Shift) {
        marketSpotShift = Shift;
    }

    public void setMarketVolatilitySpotShift(double Shift) {
        marketVolatilityShift = Shift;
    }

    public double getMarketSpotShift() {
        return marketSpotShift;
    }

    public double getMarketVolatilityShift() {
        return marketVolatilityShift;
    }

    public void setSpotFixed(boolean spotFixed) {
        isSpotFixed = spotFixed;
    }

    public void setVolatilityFixed(boolean volatilityFixed) {
        isVolatilityFixed = volatilityFixed;
    }

    public void setPriceSeries(TimeSeries priceSeries) {
        this.priceSeries = priceSeries;
    }

    public void setReturnSeries(TimeSeries returnSeries) {
        this.returnSeries = returnSeries;
    }

    public void setLogReturnSeries(TimeSeries logReturnSeries) {
        this.logReturnSeries = logReturnSeries;
    }

    public void setHighSeries(TimeSeries highSeries) {
        this.highSeries = highSeries;
    }

    public void setLowSeries(TimeSeries lowSeries) {
        this.lowSeries = lowSeries;
    }

    public void setOpenSeries(TimeSeries openSeries) {
        this.openSeries = openSeries;
    }

    public void setCloseSeries(TimeSeries closeSeries) {
        this.closeSeries = closeSeries;
    }

    public void setVolumeSeries(TimeSeries volumeSeries) {
        this.volumeSeries = volumeSeries;
    }

    public void setVolumeReturnSeries(TimeSeries volumeReturnSeries) {
        this.volumeReturnSeries = volumeReturnSeries;
    }

    public void setVolumeLogReturnSeries(TimeSeries volumeLogReturnSeries) {
        this.volumeLogReturnSeries = volumeLogReturnSeries;
    }

    public void setMarketVolatilityShift(double marketVolatilityShift) {
        this.marketVolatilityShift = marketVolatilityShift;
    }

    public Date firstDate() {
        return firstDailyDate();
    }

    public Date lastDate() {
        return lastDailyDate();
    }

//    public Daily daily() {
//        return daily(-1, false);
//    }
//
//    public Daily daily(int index) {
//        if (index == -1) {
//            Daily daily = daily(this.lastIndex(), false);
//            if (daily != null) {
//                return daily;
//            }
//        } else {
//            return daily(index, false);
//        }
//        return null;
//    }

    public Daily daily(Date date) {
        return dailyarray.get(date);
    }

//    public boolean dataAvailable(int index) {
//        if (index == -1) {
//            index = lastIndex();
//        }
//        Daily daily = daily(index);
//        return (daily.getState() != FinConstants.NOTAVAILABLE);
//    }

    public boolean dataAvailable(Date date) {
        Daily daily = dailyarray.get(date);
        if (daily == null) return false;
        return daily.getState() != FinConstants.NOTAVAILABLE;
    }

//    public int prevIndex(int index) {
//        int FirstIndex = firstIndex();
//        int PrevIndex = index - 1;
//        while (PrevIndex >= FirstIndex) {
//            if (daily(PrevIndex).valid()) {
//                break;
//            } else {
//                PrevIndex--;
//            }
//        }
//        if (PrevIndex >= FirstIndex) {
//            return PrevIndex;
//        } else {
//            return -1;
//        }
//    }

    /**
     * Return next Index with available historical data Return -1 if there is no such Index fDaily points to the next
     * Index data
     */
//    public int nextIndex(int Index) {
//        int LastIndex = lastIndex();
//        int NextIndex = Index + 1;
//        while (NextIndex <= LastIndex) {
//            if (daily(NextIndex).valid()) {
//                break;
//            } else {
//                NextIndex++;
//            }
//        }
//        if (NextIndex <= LastIndex) {
//            return NextIndex;
//        } else {
//            return -1;
//        }
//    }

    public Date prevDate(Date date) {
        return dailyarray.getPrevDate(date);
    }

    public Date nextDate(Date date) {
        return dailyarray.getNextDate(date);
    }

//    public Daily prevDaily(int Index) {
//        return prevDaily(Index, false);
//    }
//
//    public Daily prevDaily(int Index, boolean New) {
//        int PrevIndex = prevIndex(Index);
//        if (PrevIndex == -1) {
//            return null;
//        } else {
//            return daily(PrevIndex, New);
//        }
//    }

//    public Daily prevDaily(Date date) {
//        return prevDaily(date, false);
//    }

    public Daily prevDaily(Date date) {
        Date prevDate = prevDate(date);
        if (prevDate == null)
            return null;
        else
            return daily(prevDate);
    }

//    public Daily nextDaily(int Index) {
//        return nextDaily(Index, false);
//    }

//    public Daily nextDaily(Date date) {
//        return nextDaily(date, false);
//    }

    public Daily nextDaily(Date date) {
        Date nextDate = nextDate(date);
        if (nextDate == null)
            return null;
        else
            return daily(nextDate);
    }

    /**
     * Return date, if Index == -1, return date of last Index
     */
//    public Date date() {
//        return date(-1);
//    }
//
//    public Date date(int Index) {
//        Daily daily = daily(Index);
//        if (daily != null)
//            return daily.getDailydate();
//        return null;
//    }

    /**
     * Return premium, if Index == -1, return premium from last Index
     */
//    public double price() {
//        return price(-1, FinConstants.TYPICALPRICE);
//    }
//
//    public double price(int Index) {
//        return price(Index, FinConstants.TYPICALPRICE);
//    }
//
//    public double price(int Index, int Option) {
//        Daily daily = daily(Index);
//        if (daily != null) return daily.getPrice(Option);
//        return -1;
//    }

    /**
     * Return low, if Index == -1, return low from last Index
     */
//    public double low() {
//        return low(-1);
//    }
//
//    public double low(int Index) {
//        Daily daily = daily(Index);
//        if (daily != null) return daily.getLow();
//        return -1;
//    }

//    public double high() {
//        return high(-1);
//    }

//    public double high(int Index) {
//        Daily daily = daily(Index);
//        if (daily != null) return daily.getHigh();
//        return -1;
//    }

    /**
     * Return open premium, if Index == -1, return close from last Index
     */
//    public double open() {
//        return open(-1);
//    }
//
//    public double open(int Index) {
//        Daily daily = daily(Index);
//        if (daily != null) return daily.getOpenprice();
//        else
//            throw new IllegalArgumentException("getOpenprice No Index " + Index);
//    }

    /**
     * Return close premium, if Index == -1, return close from last Index
     *
     * @return
     */
//    public double close() {
//        return close(-1);
//    }
//
//    public double close(int Index) {
//        Daily daily = daily(Index);
//        if (daily != null)
//            return daily.getCloseprice();
//        else
//            throw new IllegalStateException("getCloseprice No Index " + Index);
//    }

    /**
     * Return volume, if Index == -1, return volume from last enter
     */
//    public int volume() {
//        return getVolume(-1);
//    }
//
//    public int getVolume(int Index) {
//        Daily daily = daily(Index);
//        if (daily != null)
//            return daily.getVolume();
//        else
//            throw new IllegalArgumentException("getVolume No Index " + Index);
//    }

    /**
     * getAndRemove return, if Index == -1, getAndRemove return from last Index
     */
    public double freturn() {
        return fReturn(null);
    }

//    public double fReturn(int Index) {
//        if (Index == firstIndex())
//            return 1;
//        if (Index == -1)
//            Index = lastIndex();
//        Daily daily = daily(Index);
//        if (!daily.valid())
//            return 1;
//        double Price = daily.getPrice();
//        int PrevIndex = prevIndex(Index);
//        if (PrevIndex != -1)
//            return Price / price(PrevIndex);
//        else
//            return 1;
//    }


    public double fReturn(Date date) {
        if (date == firstDate()) return 1;
        if (date == null) date = lastDate();
        Daily daily = daily(date);
        if (!daily.valid()) return 1;
        double Price = daily.getPrice();
        Daily prevDaily = this.prevDaily(date);
        if (prevDaily != null)
            return Price / prevDaily.price;
        else
            return 1;
    }

    public double logReturn() {
        return logReturn(null);
    }

    public double logReturn(Date date) {
        return Math.log(fReturn(date));
    }

    public double price(Date date) {
        return price(date, FinConstants.TYPICALPRICE);
    }

    public double price(Date date, int Option) {
        return get(date, Option)
    }

    public double low(Date date) {
//        return low(index(date));
        return dailyarray.get(date).getLow();
    }

    public double high(Date date) {
//        return high(index(date));
        return dailyarray.get(date).getHigh();
    }

    public double open(Date date) {
//        return open(index(date));
        return dailyarray.get(date).getOpenprice();
    }

    public double close(Date date) {
        return dailyarray.get(date).getCloseprice();
    }

//    public double fReturn(Date date) {
//        return fReturn(index(date));
//    }

    public int volume(Date date) {
        return dailyarray.get(date).getVolume();
    }

    public int openInterest(Date date) {
        return dailyarray.get(date).getOpenInterest();
    }

//    public double get(Date date, int Option) {
//        return get(index(date), Option);
//    }

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
                value = daily.get(What);
                if (value != 0) {
                    series.add(daily.getDailydate(), value);
                }
            }
        }
        return series;
    }

    public TimeSeries highSeries(Date firstDate, Date lastDate) {
        return timeSeries(FinConstants.HIGH, firstDate, lastDate);
    }

    public TimeSeries lowSeries(Date firstDate, Date lastDate) {
        return timeSeries(FinConstants.LOW, firstDate, lastDate);
    }

    public TimeSeries openSeries(Date firstDate, Date lastDate) {
        return timeSeries(FinConstants.OPEN, firstDate, lastDate);
    }

    public TimeSeries closeSeries(Date firstDate, Date lastDate) {
        return timeSeries(FinConstants.CLOSE, firstDate, lastDate);
    }

    public TimeSeries volumeSeries(Date firstDate, Date lastDate) {
        return timeSeries(FinConstants.VOLUME, firstDate, lastDate);
    }

    public TimeSeries highSeries() {
        return timeSeries(FinConstants.HIGH, null, null);
    }

    public TimeSeries lowSeries() {
        return timeSeries(FinConstants.LOW, null, null);
    }

    public TimeSeries openSeries() {
        return timeSeries(FinConstants.OPEN, null, null);
    }

    public TimeSeries closeSeries() {
        return timeSeries(FinConstants.CLOSE, null, null);
    }

    public TimeSeries logAverageSeries() {
        return timeSeries(FinConstants.LOGAVERAGEPRICE, null, null);
    }

    public TimeSeries volumeSeries() {
        return timeSeries(FinConstants.VOLUME, null, null);
    }

    public boolean dailyData(Date date) {
        if (dailyarray == null) {
            return false;
        }
        if (date == null) {
            if (dailyarray.size() == 0) {
                return false;
            } else return true;
        }
        return dataAvailable(date);
    }

//    public int firstIndex() {
//        return 0;
//    }
//
//    public int lastIndex() {
//        return dailyarray.size() - 1;
//    }

    public int length() {
        return dailyarray.size();
    }

    public Date firstDailyDate() {
        if (dailyarray == null || dailyarray.isEmpty())
            throw new IllegalStateException("no daily values present for instrument: " + getName());
        Daily daily = (Daily) dailyarray.getFirstValue();
        if (daily != null)
            return daily.getDailydate();
        else
            return null;
    }

    public Date lastDailyDate() {
        return dailyarray.getLastDate();
    }

//    public int index(Date date) {
//        if (date == null) throw new IllegalArgumentException("date cannot be null");
//        Daily daily = dailyarray.get(date);
//        if (daily == null) throw new RuntimeException("no price associated to this date");
//        final Date firstKey = dailyarray.firstDate();
//        return dailyarray().subMap(firstKey, date).size();
//    }

    public void add(Daily daily) {
        dailyarray.put(daily.getDailydate(), daily);
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

    // Return pointer to daily data for index
    // If New flag is set, newInstance and return new daily object.
//    public Daily daily(int index, boolean isNew) {
//        if (index == -1) index = lastIndex();
//        Daily daily = (Daily) dailyarray.get(index);
//        if (daily == null) {
//            daily = new Daily();
//            daily.setState(FinConstants.NOTAVAILABLE);
//            return daily;
//        }
//        if (isNew)
//            return (Daily) daily.clone();
//        else
//            return daily;
//    }

//    public Daily daily(Date date) {
//        Daily newdaily;
//        newdaily = dailyarray.get(date);
//    }

    public double getSpot() {
        return spot;
    }

    public double getVolatility() {
        return volatility;
    }

    public Date getTempToday() {
        return tempToday;
    }

    public TimeSeries getReturnSeries() {
        return returnSeries;
    }

    public TimeSeries getLogReturnSeries() {
        return logReturnSeries;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
}
