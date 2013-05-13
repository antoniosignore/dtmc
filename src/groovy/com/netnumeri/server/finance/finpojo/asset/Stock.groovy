package com.netnumeri.server.finance.finpojo.asset

import com.netnumeri.server.utils.OptionsChain
import com.netnumeri.server.finance.utils.YahooInstantSnapshot

class Stock extends Asset implements Serializable {

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
