package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.finance.utils.YahooInstantSnapshot
import com.netnumeri.server.utils.OptionsChain

class Stock extends Asset implements Serializable {

    static transients = [
            "chain",
            "snapshot"
    ]

    OptionsChain chain;
    YahooInstantSnapshot snapshot;

    Stock() {
    }

    public Stock(String name) {
        this.name = name;
    }

    public double modelPrice(int model) {
        return 0;
    }

}
