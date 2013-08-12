package com.netnumeri.server.finance.indicator;


public class Attributes implements Serializable {

    static belongsTo = [indicator: Indicators]

    String name
    Integer value

}
