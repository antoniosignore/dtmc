package com.netnumeri.server.finance.indicator

import com.dtmc.security.UserBean

public class UserIndicators implements Serializable {

    static belongsTo = [user: UserBean]

    String name

    Indicators indicator;

    static hasMany = [attributes: Attributes]

}
