package com.netnumeri.server.finance.indicator

import com.dtmc.club.SecUser
import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.ta.Indicator

public class UserIndicators implements Serializable {

    static belongsTo = [user: SecUser]

    String name
    IndicatorEnum type

    static transients = ["indicator"]
    Indicator indicator;

    Integer integer1
    Integer integer2
    Integer integer3

    String components

    Double double1
    Double double2

    @Override
    public String toString() {
        return "UserIndicators{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indicator=" + indicator +
                ", version=" + version +
                ", user=" + user +
                '}';
    }
}
