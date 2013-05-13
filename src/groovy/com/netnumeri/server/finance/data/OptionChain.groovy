package com.netnumeri.server.finance.data;


import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.finpojo.derivative.equity.Option

public class OptionChain {
    private Vector options;
    private Stock stock;

    public OptionChain(Stock stock) {
        this.stock = stock;
    }

    public Vector getOptions() {
        return options;
    }

    public void setOptions(Vector options) {
        this.options = options;
    }

    public void addOption(Option option) {
        options.add(option);
    }

}
