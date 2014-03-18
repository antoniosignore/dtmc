package com.netnumeri.server.finance.finpojo.derivative;

import com.netnumeri.server.finance.finpojo.Instrument
import com.netnumeri.server.finance.utils.DateUtils

public abstract class Derivative extends Instrument implements Serializable {

    static mapping = {
        tablePerHierarchy false
    }

    public abstract Instrument underlying();

    public abstract double interestRate();

    public abstract Date expiration();

    public abstract double strike();

    public abstract double premium();

    public abstract int contractSize();

    public abstract int openInterest();

    protected double alpha = 0;
    protected double beta = 0;
    protected double theta = 0;
    protected double rho = 0;
    protected double vega = 0;

    public abstract double alpha(int model);

    public abstract double beta(int model);

    public abstract double theta(int model);

    public abstract double rho(int model);

    public abstract double vega(int model);

    public Derivative() {
    }

    public double getInterestRate() {
        return interestRate() / 100;
    }

    public void setUnderlying(double p) {
        setSpot(p);
    }

    public double spot() {
        if (isSpotFixed()) {
            return super.spot();
        }
        if (underlying() != null) {
            return underlying().spot();
        } else {
            System.out.println("getSpot. No underlyer");
            return 0;
        }
    }

    public double volatility() {
        if (isVolatilityFixed())
            return super.volatility() / 100;
        if (underlying() != null)
            return underlying().volatility() / 100;
        else
            throw new RuntimeException("Volatility. No underliyer");
    }

    public double S() {
        return spot();
    }

    public double X() {
        return strike();
    }

    public double P() {
        return premium();
    }

    public double t() {
        return (double) (expirationDays()) / 365;
    }

    public double s() {
        return volatility();
    }

    public double r() {
        return interestRate() / 100;
    }

    public long expirationDays() {
        return expirationDays(null);
    }

    /**
     * Returns number of days between Date and expiration transactionDate If Date == 0, return number of days betwen current
     * transactionDate and expiration transactionDate
     */
    public long expirationDays(Date date) {
        if (date == null)
            date = new Date();
        return DateUtils.diffDays(date, expiration());
    }

}