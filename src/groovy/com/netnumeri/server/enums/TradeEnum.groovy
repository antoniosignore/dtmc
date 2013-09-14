package com.netnumeri.server.enums

public enum TradeEnum {

    SELLSHORT("Sell Short"),
    SELL("Sell"),
    HOLD("Sold"),
    BUY("Buy"),
    BUYSHORT("Buy Short")

    final String value

    TradeEnum(String value) { this.value = value }

    String toString() { value }

    String getKey() { name() }
}