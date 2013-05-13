package com.netnumeri.server.utils;


import com.netnumeri.server.finance.finpojo.derivative.equity.Option

public class OptionsChain implements Serializable {

    String ticker;

    public Map<Date, List<Option>> calls = new HashMap<Date, List<Option>>();
    public Map<Date, List<Option>> puts = new HashMap<Date, List<Option>>();


}
