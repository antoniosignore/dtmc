package com.dtmc.finance.finpojo.asset

import arrested.ArrestedToken
import com.netnumeri.server.finance.utils.YahooInstantSnapshot
import com.netnumeri.server.utils.OptionsChain
import com.netnumeri.server.utils.StockUtils

class Stock extends Asset implements Serializable {

    static transients = [
            "chain",
            "snapshot"
    ]

    String description

    OptionsChain chain;
    YahooInstantSnapshot snapshot;

    public Stock(String name, String description) {
        this.name = name;
        this.description = description
    }

    public Double modelPrice(Integer model) {
        return 0;
    }

    public String toString(){
        return name
    }

    def toObject() {
        return [
                id         : id,
                name       : name,
                description: description,
                snapshot   : snapshot,
                ohlc       : StockUtils.candleStickPlot(this)
        ]
    }

}
