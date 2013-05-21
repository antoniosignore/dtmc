package dtmc

import Jama.Matrix
import com.netnumeri.server.finance.beans.Daily
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.beans.TradeEnum
import com.netnumeri.server.finance.data.TransactionSeries
import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.finpojo.Portfolio
import com.netnumeri.server.finance.finpojo.PortfolioItem
import com.netnumeri.server.finance.finpojo.Transaction
import com.netnumeri.server.finance.finpojo.asset.Asset
import com.netnumeri.server.finance.finpojo.derivative.Derivative
import com.netnumeri.server.finance.utils.DateUtils
import com.netnumeri.server.finance.utils.YahooUtils

class TradeService {

    public void clear(Portfolio portfolio) {
        portfolio.items.clear();
        portfolio.transactions.clear();
    }

    boolean isEmpty(Portfolio portfolio) {
        if (portfolio.items.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Date getFirstDay(Portfolio portfolio) {
        if (portfolio.firstDailyDate != null) {
            return portfolio.firstDailyDate;
        }
        Instrument instrument
        for (int i = 0; i < portfolio.items.size(); i++) {
            instrument = getInstrument(portfolio, i);
            if (instrument instanceof Asset) {
                if (instrument.firstDay() != null) {
                    portfolio.firstDailyDate =
                        DateUtils.max(portfolio.firstDailyDate, instrument.firstDay());
                }
            }
        }
        return portfolio.firstDailyDate;
    }

    public Date getLastDay(Portfolio portfolio) {
        Instrument instrument;
        if (portfolio.lastDailyDate != null) {
            return portfolio.lastDailyDate;
        }
        for (int i = 0; i < portfolio.items.size(); i++) {
            instrument = getInstrument(portfolio, i);
            if (instrument instanceof Asset) {
                if (instrument.lastDay() != null) {
                    portfolio.lastDailyDate = DateUtils.min(portfolio.lastDailyDate,
                            instrument.lastDay());
                }
            }
        }
        return portfolio.lastDailyDate;
    }

    public void add(Portfolio portfolio, PortfolioItem entry) {

        if (getEntry(portfolio, entry.instrument) != null) {
            System.out.println("addEntry. Instrument: " + entry.instrument.name + " already exists in portfolio " + portfolio.getName());
            return;
        }

        if (entry.instrument instanceof Asset)
            portfolio.setRangeBounds(entry.instrument.lowerRangeDate, entry.instrument.getUpperRangeDate());

        portfolio.items.add(entry);
    }


    public void add(Portfolio portfolio, Instrument instrument) {
        if (instrument == null) throw new IllegalArgumentException("instrument cannot be null");
        add(portfolio, instrument, 0);
    }

    public void add(Portfolio portfolio, Instrument instrument, int Amount) {
        PortfolioItem item = new PortfolioItem(instrument, Amount, portfolio);
        item.instrument = instrument;

        if (!portfolio.isTesting)
            if (item.instrument.getId() != null) {
//                PortfolioItemDao dao = new PortfolioItemDao();
//                dao.put(item);
            }
        add(portfolio, item);
    }

    public void add(Portfolio portfolio, Transaction transaction) {
        if (transaction == null) throw new IllegalArgumentException("transactions cannot be null");

        Instrument instrument = transaction.instrument;
        PortfolioItem entry = getEntry(portfolio, instrument);

        if (entry == null) {
            entry = new PortfolioItem(instrument, portfolio);
            if (transaction.getAction() == TradeEnum.BUY) {
                entry.setAmount(transaction.getAmount());
            } else if (transaction.getAction() == TradeEnum.SELL) {
                System.out.println("addTransaction. No long position on sell for " + transaction.instrument.name + " in " + portfolio.getName());
                return;
            } else if (transaction.getAction() == TradeEnum.SELLSHORT) {
                entry.setAmount(-transaction.getAmount());
            } else if (transaction.getAction() == TradeEnum.BUYSHORT) {
                System.out.println("addTransaction. No short position on buy short for " + transaction.instrument.name + " in " + portfolio.getName());
                return;
            }
            portfolio.transactions.add(transaction);
            add(portfolio, entry); ;
        } else {
            int amount = 0;
            if (transaction.getAction() == TradeEnum.BUY) {
                if (entry.getAmount() < 0) {
                    System.out.println("addTransaction. Short position on buy for " + transaction.instrument.name + " in " + portfolio.getName());
                    return;
                }
                amount = entry.getAmount() + transaction.getAmount();
            } else if (transaction.getAction() == TradeEnum.SELL) {
                amount = entry.getAmount() - transaction.getAmount();
                if (amount < 0) {
                    System.out.println("addTransaction. Sell amount larger than long position for" + transaction.instrument.name + " in " + portfolio.getName());
                    return;
                }
            } else if (transaction.getAction() == TradeEnum.SELLSHORT) {
                if (entry.getAmount() > 0) {
                    System.out.println("addTransaction. Long position in instrument on sell short: " + portfolio.getName());
                    return;
                }
                amount = entry.getAmount() - transaction.getAmount();
            } else if (transaction.getAction() == TradeEnum.BUYSHORT) {
                if (entry.getAmount() > 0) {
                    System.out.println("addTransaction. Long position on buy short for " + transaction.instrument.name + " in " + portfolio.getName());
                    return;
                }
                amount = entry.getAmount() + transaction.getAmount();
                if (amount > 0) {
                    System.out.println("addTransaction. Buy short amount larger than short position: " + portfolio.getName());
                    return;
                }
            }
            portfolio.transactions.add(transaction);

            if (amount == 0) {
                remove(portfolio, instrument);
            } else {
                entry.setAmount(amount);
            }
        }
    }

    // add series of trade transactions
    public void add(Portfolio portfolio, TransactionSeries series) {
        if (series == null) throw new IllegalArgumentException("series cannot be null");
        int N = series.getN();
        for (int i = 0; i < N; i++) {
            add(portfolio, series.getTransaction(i));
        }
    }

    // Return pointer to portfolio entry holding instrument
    // Return null if there is no such entry in portfolio
    public PortfolioItem getEntry(Portfolio portfolio, Instrument instrument) {
        if (instrument == null) throw new IllegalArgumentException("instrument cannot be null");
        PortfolioItem entry;
        if (portfolio.items != null)
            for (int i = 0; i < portfolio.items.size(); i++) {
                entry = item(portfolio, i);
                if (entry.instrument.id == instrument.id) {
                    return entry;
                }
            }
        return null;
    }

    public PortfolioItem getEntry(Portfolio portfolio, String Name) {
        PortfolioItem entry;
        for (int i = 0; i < portfolio.items.size(); i++) {
            entry = item(portfolio, i);
            if (entry.getInstrument().getName().compareToIgnoreCase(Name) >= 0) {
                return entry;
            }
        }
        return null;
    }

    public void invest(Portfolio portfolio, double wealth) {
        invest(portfolio, wealth, null);
    }

    // Invest wealth into portfolio according to current portfolio weights
    public void invest(Portfolio portfolio, double wealth, Date date) {

        if (portfolio.items == null || portfolio.items.isEmpty())
            throw new RuntimeException("no instruments to invest money into");
        for (int i = 0; i < portfolio.items.size(); i++) {
            Instrument asset = getInstrument(portfolio, i);
            double price = 0;
            if (asset.isDataAvailable(date)) {
                price = asset.getLast();
            } else {
                price = YahooUtils.getLastTradedValue(asset.name)
            }
            setAmount(portfolio, i, (int) (getItemAmount(portfolio, i) + wealth * getWeight(portfolio, i) / price));
        }
    }

    public Transaction buy(Portfolio portfolio, Instrument instrument, int amount) {
        return buy(portfolio, instrument, amount, null);
    }

    public Transaction buy(Portfolio portfolio, Instrument instrument, int amount, Date date) {
        if (date == null) date = new Date();
        Transaction transaction = new Transaction(instrument, TradeEnum.BUY, amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    public Transaction sell(Portfolio portfolio, Instrument instrument, int amount) {
        return sell(portfolio, instrument, amount, new Date());
    }

    public Transaction sell(Portfolio portfolio, Instrument instrument, int amount, Date date) {
        if (date == null) date = new Date();
        Transaction transaction = new Transaction(instrument, TradeEnum.SELL, amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    public Transaction sellShort(Portfolio portfolio, Instrument instrument, int amount) {
        return sellShort(portfolio, instrument, amount, null);
    }

    public Transaction sellShort(Portfolio portfolio, Instrument instrument, int Amount, Date date) {
        if (date == null) date = new Date();
        Transaction transaction = new Transaction(instrument, TradeEnum.SELLSHORT, Amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    public Transaction buyShort(Portfolio portfolio, Instrument instrument, int Amount) {
        return buyShort(portfolio, instrument, Amount, new Date());
    }

    // Buy short
    public Transaction buyShort(Portfolio portfolio, Instrument instrument, int Amount, Date date) {
        if (date == null) date = new Date();
        Transaction transaction = new Transaction(instrument, TradeEnum.BUYSHORT, Amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    public Transaction sell(Portfolio portfolio, Instrument instrument) {
        return sell(portfolio, instrument, new Date());
    }

    // Sell everything
    public Transaction sell(Portfolio portfolio, Instrument instrument, Date date) {
        int amount;
        if (getEntry(portfolio, instrument) != null) {
            amount = getAmount(portfolio, instrument);
        } else {
            return null;
        }
        if (date == null) date = new Date();
        Transaction transaction = new Transaction(instrument, TradeEnum.SELL, amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    // delete instrument from portfolio
    public void remove(Portfolio portfolio, Instrument instrument) {
        for (int i = 0; i < portfolio.items.size(); i++) {
            PortfolioItem entry = portfolio.items.get(i);
            if (entry.instrument == null) throw new RuntimeException("entry cannot have null name");
            if (entry.instrument.id == instrument.id) {
                portfolio.items.remove(entry);
                normalizeWeights(portfolio);
                break;
            }
        }
    }

    // Return weight of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio

    public double getWeight(Portfolio portfolio, Instrument instrument) {
        PortfolioItem entry = getEntry(portfolio, instrument);
        if (entry != null) {
            return entry.getWeight();
        } else {
            return 0;
        }
    }

    // Return position of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio

    public int getPosition(Portfolio portfolio, Instrument instrument) {
        PortfolioItem entry = getEntry(portfolio, instrument);
        if (entry != null) {
            return entry.getPosition();
        } else {
            return 0;
        }
    }

    // Return amount of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio
    public int getAmount(Portfolio portfolio, Instrument instrument) {
        PortfolioItem entry = getEntry(portfolio, instrument);
        if (entry != null) {
            return entry.getAmount();
        } else {
            return 0;
        }
    }


    public double getAmount(Portfolio portfolio) {
        // Return amount of all items in portfolio
        int Amount = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            Amount += getItemAmount(portfolio, i);
        }
        return Amount;
    }

    public double getWealth(Portfolio portfolio, int i) {
        return getWealth(portfolio, i, null);
    }

    // Return wealth for i-th asset in portfolio
    public double getWealth(Portfolio portfolio, int i, Date date) {
        Instrument asset = getInstrument(portfolio, i);
        double price = 0;
        if (asset.isDataAvailable(date)) {
            price = asset.premium();
        } else {
            price = YahooUtils.getLastTradedValue(asset.name)
        }
        return price * getItemAmount(portfolio, i);
    }


    public double getWealth(Portfolio portfolio) {
        return getWealth(portfolio, null);
    }

    // Return wealth of portfolio
    public double getWealth(Portfolio portfolio, Date date) {
        double Wealth = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            Wealth += getWealth(portfolio, i, date);
        }
        return Wealth;
    }

    // Normalize weigts of all stock in portfolio to keep
    // weight sum equal to unity and satisfy boundary conditions
    // Note that we exclude stock with zero weights from the portfolio,
    // meaning that such stock will have zero weight after normalization
    public void normalizeWeights(Portfolio portfolio) {
        double WeightSum = 0;
        int i = 0;
        for (i = 0; i < portfolio.items.size(); i++) {
            WeightSum += getWeight(portfolio, i);
        }
        for (i = 0; i < portfolio.items.size(); i++) {
            setWeight(portfolio, i, getWeight(portfolio, i) / WeightSum);
        }
        WeightSum = 1;
        boolean InBounds = true;
        for (i = 0; i < portfolio.items.size(); i++) {
            if (getWeight(portfolio, i) != 0) {
                if (getWeight(portfolio, i) < lowerBound(portfolio, i)) {
                    InBounds = false;
                    break;
                }
            }
        }
        if (!InBounds) {
            double LowerBoundSum = 0;
            for (i = 0; i < portfolio.items.size(); i++) {
                if (getWeight(portfolio, i) != 0) {
                    LowerBoundSum += lowerBound(portfolio, i);
                }
            }
            for (i = 0; i < portfolio.items.size(); i++) {
                if (getWeight(portfolio, i) != 0) {
                    setWeight(portfolio, i, lowerBound(portfolio, i) + getWeight(portfolio, i) * (1 - LowerBoundSum) / WeightSum);
                }
            }
        }
    }

    public void normalize(Portfolio portfolio, int Option) {
        if (Option == FinConstants.kFixedWeight) {
            normalizeWeights(portfolio);
        } else {
            double Wealth = getWealth(portfolio);
            for (int i = 0; i < portfolio.items.size(); i++) {
                setWeight(portfolio, i, getWealth(portfolio, i) / Wealth);
            }
        }
    }

    // Check boundary conditions. Return true if feasible
    public boolean checkBounds(Portfolio portfolio) {
        double lowsum = 0;
        double upsum = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            lowsum += lowerBound(portfolio, i);
            upsum += upperBound(portfolio, i);
            if (lowerBound(portfolio, i) >= upperBound(portfolio, i)) {
                System.out.println("CheckBounds LowerBound >= UpperBound for parameter " + i);
                return false;
            }
        }
        if (lowsum >= 1) {
            System.out.println("CheckBounds LowerBoundSum >= 1");
            return false;
        }
        if (upsum <= 1) {
            System.out.println("CheckBounds UpperBoundSum <= 1");
            return false;
        }
        return true;
    }

    // Calculate models premium of portfolio

    public double getModelPrice(Portfolio portfolio, String Model, String printMode) {
        double price = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            price += getInstrument(portfolio, i).premium() * getItemAmount(portfolio, i) * getPosition(portfolio, i);
        }
        return price;
    }

    // Return marked to market portfolio premium
    // If we consider a portfolio as one
    // financial instrument, its premium is
    // equal to its value

    public double getPrice(Portfolio portfolio, int Entry) {
        return getValue(portfolio, Entry);
    }

    // Return marked to market portfolio premium If we consider a portfolio as one
    // financial instrument, its premium is equal to its value
    public double getPrice(Portfolio portfolio, Date date) {
        return getValue(portfolio, date);
    }

    // Return portfolio premium If we consider a portfolio as one
    // financial instrument, its premium is equal to its value
    public double premium(Portfolio portfolio) {
        return getValue(portfolio);
    }

    // Return marked to market portfolio value

    public double getValue(Portfolio portfolio, int index) {
        Instrument instrument;
        Daily daily;
        int amount;
        double value = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            amount = getItemAmount(portfolio, i);
            instrument = getInstrument(portfolio, i);
            daily = instrument.getDaily(index);
            if (!daily.valid()) {
                daily = instrument.getPrevDaily(index);
            }
            if (daily != null) {
                value += daily.getCloseprice() * amount;
            } else {
                System.out.println("getName. Out of data range :" + index);
                return 0;
            }
        }
        return value;
    }

    /**
     * Mark 2 Market portfolio value
     *
     * @param date
     */
    public double getValue(Portfolio portfolio, Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }
        Instrument instrument;
        Daily daily;
        int amount;
        double value = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            instrument = getInstrument(portfolio, i);
            amount = getItemAmount(portfolio, i);
            if (instrument instanceof Asset) {
                daily = instrument.getDaily(date);
                if (daily == null || !daily.valid()) {
                    daily = instrument.getPrevDaily(date);
                }
                if (daily != null) {
                    value += daily.getCloseprice() * amount;
                } else {
                    System.out.println("getValue. Out of data range");
                    return 0;
                }
            }
            if (instrument instanceof Derivative) {
                value += instrument.premium() * amount;
            }
        }
        return value;
    }

    // Return portfolio value
    public double getValue(Portfolio portfolio) {
        double Value = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            Value += item(portfolio, i).getValue();
        }
        return Value;
    }

    public double getReturn(Portfolio portfolio, Date date) {
        // getAndRemove marked to market daily return
        double price = getPrice(portfolio, date);
        double previousPrice;
        if (price == 0) {
            return 1;
        }
        Date previousDate = getInstrument(portfolio, 0).getPrevDate(date);
        if (previousDate == null) {
            return 1;
        } else {
            previousPrice = getPrice(portfolio, previousDate);
            if (previousPrice == 0) {
                return 1;
            } else {
                return price / previousPrice;
            }
        }
    }

    public double getLogReturn(Portfolio portfolio, Date date) {
        return Math.log(getReturn(portfolio, date));
    }

    public TimeSeries logReturnSeries(Portfolio portfolio) {
        TimeSeries logReturnSeries = new TimeSeries();
        logReturnSeries.setOption(FinConstants.LOGRETURN);
        Date firstDate = getInstrument(portfolio, 0).firstDate();
        Date lastDate = getInstrument(portfolio, 0).lastDate();
        for (Date date = firstDate;
        DateUtils.isLessEqual(date, lastDate);
        date = DateUtils.nextDay(date)) {
            logReturnSeries.add(date, getLogReturn(portfolio, date));
        }
        return logReturnSeries;
    }

    public double minReturn(Portfolio portfolio) {
        // Return min return
        double MinReturn = getInstrument(portfolio, 0).expectedReturn();
        for (int i = 0; i < portfolio.items.size(); i++) {
            if (getInstrument(portfolio, i).expectedReturn() < MinReturn) {
                MinReturn = getInstrument(portfolio, i).expectedReturn();
            }
        }

        return MinReturn;
    }

    public double maxReturn(Portfolio portfolio) {
        // Return max return

        double MaxReturn = getInstrument(portfolio, 0).expectedReturn();
        for (int i = 0; i < portfolio.items.size(); i++) {
            if (getInstrument(portfolio, i).expectedReturn() > MaxReturn) {
                MaxReturn = getInstrument(portfolio, i).expectedReturn();
            }
        }

        return MaxReturn;
    }

    public double minVariance(Portfolio portfolio) {
        // Return min variance
        double MinVariance = getInstrument(portfolio, 0).getVariance(FinConstants.LOGRETURN);
        for (int i = 0; i < portfolio.items.size(); i++) {
            if (getInstrument(portfolio, i).getVariance(FinConstants.LOGRETURN) < MinVariance) {
                MinVariance = getInstrument(portfolio, i).getVariance(FinConstants.LOGRETURN);
            }
        }
        return MinVariance;
    }

    public double maxVariance(Portfolio portfolio) {
        // Return max variance
        double MaxVariance = getInstrument(portfolio, 0).getVariance(FinConstants.LOGRETURN);
        for (int i = 0; i < portfolio.items.size(); i++) {
            if (getInstrument(portfolio, i).getVariance(FinConstants.LOGRETURN) > MaxVariance) {
                MaxVariance = getInstrument(portfolio, i).getVariance(FinConstants.LOGRETURN);
            }
        }
        return MaxVariance;
    }

    public double expectedReturn(Portfolio portfolio) {
        // Calculate portfolio expected return
        double ExpectedReturn = 0;
        double Weight = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            Weight = getWeight(portfolio, i);
            if (Weight != 0) {
                ExpectedReturn += getInstrument(portfolio, i).expectedReturn() * Weight;
            }
        }
        return ExpectedReturn;
    }

    public void setNHoldAsset(Portfolio portfolio, int NHoldAsset) {
        portfolio.assetsToHold = NHoldAsset;
    }

    public void setInstrument(Portfolio portfolio, int i, Instrument instrument) {
        item(portfolio, i).setInstrument(instrument);
    }

    public void setAmount(Portfolio portfolio, int i, int amount) {
        item(portfolio, i).setAmount(amount);
    }


    public void setWealth(Portfolio portfolio, double Wealth) {
        portfolio.wealth = Wealth;
    }

    public double variance(Portfolio portfolio) {
        // Calculate portfolio variance
        Matrix matrix = covarianceMatrix(portfolio);
        double Variance = 0;
        double Weight1 = 0;
        double Weight2 = 0;
        for (int i1 = 0; i1 < portfolio.items.size(); i1++) {
            Weight1 = getWeight(portfolio, i1);
            if (Weight1 != 0) {
                for (int i2 = 0; i2 < portfolio.items.size(); i2++) {
                    Weight2 = getWeight(portfolio, i2);
                    if (Weight2 != 0)
                    //      Variance += covarianceMatrix[i1][i2]*Weight1*Weight2;
                    {
                        Variance += matrix.get(i1, i2) * Weight1 * Weight2;
                    }
                }
            }
        }
        return Variance;
    }

    // Calculate portfolio standard deviation

    public double standardDeviation(Portfolio portfolio) {
        return Math.sqrt(variance(portfolio));
    }

    // Return annual expected return

    public double annualExpectedReturn(Portfolio portfolio) {
        return (Math.pow(expectedReturn(portfolio), 365) - 1);
    }

    public double annualVariance(Portfolio portfolio) {
        // Calculate annual variance
        return variance(portfolio) * 365.0;
    }

    public double annualStandardDeviation(Portfolio portfolio) {
        return standardDeviation(portfolio) * Math.sqrt(365.0);
    }

    // Calculate CAPM beta with Benchmark representing market portfolio
    public double getBeta(Portfolio portfolio, Portfolio index) {
        double Beta = 0;
        for (int i = 0; i < portfolio.items.size(); i++) {
            Portfolio p = (Portfolio) getInstrument(portfolio, i);
            double beta = getBeta(portfolio, index);
            Beta += beta * getWeight(portfolio, i);
        }
        return Beta;
    }

    // Claculate CAPM expected return excess with Benchmark representing market portfolio
    public double excess(Portfolio portfolio, Portfolio index, double InterestRate) {
        return InterestRate + getBeta(portfolio, index) * (index.annualExpectedReturn() - InterestRate);
    }

    public double getSharpeIndex(Portfolio portfolio, double rate) {
        return (annualExpectedReturn(portfolio) - rate) / annualStandardDeviation(portfolio);
    }

    public double getTreynorIndex(Portfolio portfolio, Portfolio index, double rate) {
        return (annualExpectedReturn(portfolio) - rate) / getBeta(portfolio, index);
    }


    public double getDelta(Portfolio portfolio) {
        for (int i = 0; i < portfolio.items.size(); i++) {
            portfolio.delta += getInstrument(portfolio, i).getDelta() * getItemAmount(portfolio, i);
        }
        return portfolio.delta;
    }

    /**
     * Calculate CAPM beta with Benchmark representing market portfolio
     */
    public double getBeta(Portfolio portfolio, Asset index) {
        return getCovariance(portfolio, index) / index.variance();
    }


    private double modelPrice(Portfolio portfolio) {
        return 0;
    }

    public Matrix covarianceMatrix(Portfolio portfolio) {
        portfolio.covarianceMatrix = new Matrix(portfolio.items.size(), portfolio.items.size());
        for (int i1 = 0; i1 < portfolio.items.size(); i1++) {
            for (int i2 = i1; i2 < portfolio.items.size(); i2++) {
                portfolio.covarianceMatrix.set(i1, i2, getInstrument(portfolio, i1).getCovariance(getInstrument(portfolio, i2), FinConstants.LOGRETURN));
                portfolio.covarianceMatrix.set(i2, i1, portfolio.covarianceMatrix.get(i1, i2));
            }
        }
        return portfolio.covarianceMatrix;
    }

    // Build correlation matrix
    public Matrix correlationMatrix(Portfolio portfolio) {
        portfolio.correlationMatrix = new Matrix(portfolio.items.size(), portfolio.items.size());
        portfolio.covarianceMatrix = covarianceMatrix(portfolio);
        for (int i1 = 0; i1 < portfolio.items.size(); i1++) {
            for (int i2 = i1; i2 < portfolio.items.size(); i2++) {
                portfolio.correlationMatrix.set(i1, i2, portfolio.covarianceMatrix.get(i1, i2) /
                        (getInstrument(portfolio, i1).getStandardDeviation(FinConstants.LOGRETURN) *
                                getInstrument(portfolio, i2).getStandardDeviation(FinConstants.LOGRETURN)));
                portfolio.correlationMatrix.set(i2, i1, portfolio.correlationMatrix.get(i1, i2));
            }
        }
        return portfolio.correlationMatrix;
    }

    public void onNewTrade(Portfolio portfolio) {
        System.out.println("onNewTrade");
        System.out.println("Price: " + premium(portfolio));
    }

    public int nTransactions(Portfolio portfolio) {
        return portfolio.transactions.size();
    }

    public Transaction getTransaction(Portfolio portfolio, int i) {
        return portfolio.transactions.get(i);
    }

    public PortfolioItem item(Portfolio portfolio, int i) {
        return portfolio.items.get(i);
    }

    public int nentries(Portfolio portfolio) {
        return portfolio.items.size();
    }

    public int nHoldAsset(Portfolio portfolio) {
        return portfolio.assetsToHold;
    }

    public Instrument getInstrument(Portfolio portfolio, int i) {
        return portfolio.items.get(i).instrument
    }

    public int getPosition(Portfolio portfolio, int i) {
        return item(portfolio, i).getPosition();
    }

    public int getItemAmount(Portfolio portfolio, int i) {
        return item(portfolio, i).getAmount();
    }

    public double getWeight(Portfolio portfolio, int i) {
        return item(portfolio, i).getWeight();
    }

    public double getModelPrice(Portfolio portfolio, int i) {
        return item(portfolio, i).getModelPrice();
    }

    public Date getModelDate(Portfolio portfolio, int i) {
        return item(portfolio, i).getModelDate();
    }

//    public  void randomize() {
//        randomizeWeights();
//    }

    public double getCovariance(Portfolio portfolio, int Row, int Col) {
        return portfolio.covarianceMatrix.get(Row, Col);
    }

    public double getCorrelation(Portfolio portfolio, int Row, int Col) {
        return portfolio.correlationMatrix.get(Row, Col);
    }

    public List<PortfolioItem> getInstruments(Portfolio portfolio) {
        return portfolio.items;
    }

    public void setItems(Portfolio portfolio, List<PortfolioItem> items) {
        portfolio.items = items;
    }

    public Matrix toMatrixLogReturns(Portfolio portfolio) {
        int dimension = getInstruments(portfolio).size();
        Matrix ret = null;
        List entries = getInstruments(portfolio);
        for (int i = 0; i < entries.size(); i++) {
            PortfolioItem item = (PortfolioItem) entries.get(i);
            Instrument instrument = item.getInstrument();
            double[] series = instrument.logReturnSeries().convertToArray();
            if (ret == null) {
                ret = new Matrix(dimension, series.length);
            }
            for (int x = 0; x < series.length; x++) {
                double v = series[x];
                ret.set(i, x, v);
            }
        }
        return ret;
    }

    public Matrix toMatrixReturns(Portfolio portfolio) {
        int dimension = getInstruments(portfolio).size();
        Matrix ret = null;
        List entries = getInstruments(portfolio);
        for (int i = 0; i < entries.size(); i++) {
            PortfolioItem item = (PortfolioItem) entries.get(i);
            Instrument instrument = item.getInstrument();
            double[] serie = instrument.returnSeries().convertToArray();
            if (ret == null) {
                ret = new Matrix(dimension, serie.length);
            }
            for (int x = 0; x < serie.length; x++) {
                double v = serie[x];
                ret.set(i, x, v);
            }
        }
        return ret;
    }

}
