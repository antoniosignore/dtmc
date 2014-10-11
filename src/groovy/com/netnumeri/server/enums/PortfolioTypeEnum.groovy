package com.netnumeri.server.enums

public enum PortfolioTypeEnum {

    Main, Strategy, WatchList

    static PortfolioTypeEnum valueOfName( String name ) {
        if (name.equalsIgnoreCase('Main')) return Main
        if (name.equalsIgnoreCase('Strategy')) return Strategy
        if (name.equalsIgnoreCase('WatchList')) return WatchList
    }

}