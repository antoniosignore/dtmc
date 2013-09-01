package com.netnumeri.server.finance.indicator;


public class Attributes implements Serializable {

    static belongsTo = [userIndicator: UserIndicators]

    String name
    Double value

}
