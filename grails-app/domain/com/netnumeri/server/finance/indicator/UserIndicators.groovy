package com.netnumeri.server.finance.indicator

import com.dtmc.security.UserBean
import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.ta.Indicator

public class UserIndicators implements Serializable {

    static belongsTo = [user: UserBean]

    String name

    IndicatorEnum type

    Indicator indicator;

    static hasMany = [attributes: Attributes]

    static transients = ["indicator"]

    @Override
    public java.lang.String toString() {
        return "UserIndicators{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indicator=" + indicator +
                ", version=" + version +
                ", attributes=" + attributes +
                ", user=" + user +
                '}';
    }
}
