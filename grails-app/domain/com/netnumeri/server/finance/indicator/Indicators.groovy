package com.netnumeri.server.finance.indicator

import com.dtmc.security.UserBean
import com.netnumeri.server.finance.beans.IndicatorEnum

public class Indicators implements Serializable {

    IndicatorEnum indicator;
    String name;

    static hasMany = [attributes: Attributes]
    static belongsTo = [user: UserBean]

}
