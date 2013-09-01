package com.netnumeri.server.finance.beans

enum IndicatorEnum {

    SimpleMovingAverage("Simple Moving Average"),
    WeightedMovingAverage("Weighted Moving Average"),
    SingularSpectrumTrend("Singular Spectrum Trend"),
    SingularSpectrumPrediction("Singular Spectrum Prediction"),
    PriceChannelUpper("Price Channel Upper"),
    PriceChannelLower("Price Channel Lower"),
    UpperBollingerBand("Upper Bollinger Band"),
    LowerBollingerBand("Lower Bollinger Band"),
    kVHF("Vertical Filter"),
    SimpleMovingVariance("Simple Moving Variance"),
    kSMD("Simple Moving Divergence"),
    kSMC("Simple Moving Covariance"),
    Momentum("Momentum"),
    To("To"),
    Macd("MACD"),
    RateOfChange("Rate of Change"),
    RSI("Relative Strength Index"),
    RSI2("Relative Strength Index 2"),
    kCCI("CCI"),
    kKRI("Kri"),
    kOSC("Oscillator"),
    kPCR("Price Channel R"),
    kFI("FI"),
    kD("D"),
    kK("K"),
    kVA("VA"),
    kVAI("VAI"),
    kCHO("Cho"),
    TrueChange("True Change"),
    AverageTrueChange("Average True Change"),
    MoneyFlowOverPeriod("Money Flow Over Period"),
    AccumulateDistributionOverPeriod("Accumulated Distribution Over Period"),
    ChaikinOscillatorOverPeriod("Chaikin Oscillator Over Period"),
    ChaikinMoneyFlowOverPeriod("Chaikin Money Flow Over Period"),
    AroonOscillatorOverPeriod("Aroon Oscillator Over Period"),
    AroonDownOverPeriod("Aroon Down Over Period"),
    AroonUpOverPeriod("Simple Moving Average"),
    TrueRangePeriod("Aroon Up Over Period"),
    kFastStochasticPeriod("k Fast Stochastic Period"),
    dStochastic("d Stochastic"),
    DStochasticSmoothed("D Stochastic Smoothed"),
    ChaikinVolatility("Chaikin Volatility"),
    kMACDSignal("MACD Signal"),
    PlusDirectionalMovementPeriod("Plus Directional Movement Period"),
    MinusDirectionalMovementPeriod("Minus Directional Movement Period"),
    PriceActionOverPeriod("Price Action Over Period"),
    BalanceOfPowerOverPeriod("Balance Of Power Over Period"),
    MarketFacilitationIndexOverPeriod("Market Facilitation Index Over Period"),
    CommodityChannelIndexOverPeriod("Commodity Channel Index Over Period"),
    MomentumPctPeriod("Momentum Pct Period"),
    GeometricMovingAverage("Geometric Moving Average"),
    LinearlyWeightedMovingAverage("Linearly Weighted Moving Average"),
    TriangularMovingAverage("Triangular Moving Average"),
    ExponentiallyWeightedMovingAverage("Exponentially Weighted Moving Average"),
    Kairi("Kairi"),
    RateOfChangePeriod("Rate Of Change Period");

    final String value

    IndicatorEnum(String value) { this.value = value }

    String toString() { value }

    String getKey() { name() }

}
